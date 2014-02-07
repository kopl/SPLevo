package org.splevo.jamopp.extraction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.extraction.SoftwareModelExtractor;
import org.splevo.jamopp.extraction.cache.ReferenceCache;
import org.splevo.jamopp.extraction.resource.JavaSourceOrClassFileResourceCachingFactoryImpl;

import com.google.common.collect.Lists;

/**
 * Software Model Extractor for the Java technology based on the Java Model Parser and Printer
 * (JaMoPP).
 */
public class JaMoPPSoftwareModelExtractor implements SoftwareModelExtractor {

    private static Logger logger = Logger.getLogger(JaMoPPSoftwareModelExtractor.class);

    private static final String EXTRACTOR_ID = "JaMoPPSoftwareModelExtractor";
    private static final String EXTRACTOR_LABEL = "JaMoPP Software Model Extractor";

    /** Option to resolve all proxies in the model immediately after resource loading. */
    private boolean resolveProxiesImmediately = false;

    /** Use the reference resolution caching */
    private boolean useCache = true;

    /**
     * Extract the source model of a list of java projects. One project is the main project while a
     * list of additional projects to analyze can be specified. The reason for one main project is,
     * that this one is used for example for the naming of the root inventory produced etc.
     *
     * @param projectPaths
     *            Source Paths of the projects to be extracted.
     * @param monitor
     *            The monitor to report the progress to.
     * @return The set of resources containing the extracted model.
     * @throws SoftwareModelExtractionException
     *             Identifies the extraction was not successful.
     */
    public ResourceSet extractSoftwareModel(List<String> projectPaths, IProgressMonitor monitor)
            throws SoftwareModelExtractionException {
        return extractSoftwareModel(projectPaths, monitor, null);
    }

    /**
     * Extract all java files and referenced resources to a file named according to
     * {@link JaMoPPSoftwareModelExtractor#XMI_FILE_SEGMENT} within the provided targetURI.
     *
     * <p>
     * If the targetUri is null, the extracted model will not be persisted and the resourceSet of
     * the last projectURI will be returned.
     * </p>
     * {@inheritDoc}
     */
    @Override
    public ResourceSet extractSoftwareModel(List<String> projectPaths, IProgressMonitor monitor, String sourceModelPath)
            throws SoftwareModelExtractionException {

        if (useCache && sourceModelPath != null) {
            logger.info("Use cache file: " + sourceModelPath);
        }

        ResourceSet targetResourceSet = setUpResourceSet(sourceModelPath);
        JavaClasspath cp = JavaClasspath.get(targetResourceSet);

        for (String projectPath : projectPaths) {

            // FIXME Not sure of this line is necessary
            // cp.getURIMap().put(projectPathURI, URI.createURI(""));
            File srcFolder = new File(projectPath);
            try {
                srcFolder.isDirectory();
                int jarCount = addAllJarFilesToClassPath(srcFolder, cp);
                int javaCount = loadAllJavaFilesInResourceSet(srcFolder, targetResourceSet);
                logger.info(String.format("%d Java and %d Jar Files added to resource set", javaCount, jarCount));

            } catch (Exception e) {
                throw new SoftwareModelExtractionException("Failed to extract software model.", e);
            }
        }

        if (resolveProxiesImmediately) {
            logger.info("Resolve all proxies");
            if (!resolveAllProxies(targetResourceSet)) {
                handleFailedProxyResolution(targetResourceSet);
            }
        }

        triggerCacheSave(targetResourceSet);

        return targetResourceSet;
    }

    /**
     * Trigger the cache enabled JaMoPP resource cache to be saved.
     *
     * @param targetResourceSet
     *            The resource set to save the assigned cache in.
     */
    private void triggerCacheSave(ResourceSet targetResourceSet) {
        Map<String, Object> factoryMap = targetResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
        JavaSourceOrClassFileResourceCachingFactoryImpl factory = (JavaSourceOrClassFileResourceCachingFactoryImpl) factoryMap
                .get("java");
        ReferenceCache cache = factory.getReferenceCache();
        logger.debug("Resources not resolved from Cache: " + cache.getNotResolvedFromCacheCounter());
        cache.save();
    }

    /**
     * Resolve all proxies of the loaded sources.
     *
     * This is a slightly modified version of the JaMoPPCC proxy resolution. It is not called
     * recursively because the extractor ensures all available resources have been loaded in
     * advance.<br>
     * The code has also been cleaned up.
     *
     * Note: The same result could also be achieved by calling {@link EcoreUtil} resolveAll() but
     * this would not allow to track any non resolved proxies.
     *
     * @param rs
     *            The resource set to resolve the proxies in.
     * @return True/False if the resolving was successful or not.
     */
    private boolean resolveAllProxies(ResourceSet rs) {
        boolean failure = false;

        for (Iterator<Notifier> i = rs.getAllContents(); i.hasNext();) {
            Notifier next = i.next();
            if (next instanceof InternalEObject) {
                failure = resolveProxyReferences(rs, (InternalEObject) next);
            }
        }

        return !failure;
    }

    /**
     * Resolve the proxies of all cross referenced eobjects of an internal object.
     *
     * @param rs
     *            The resource set to resolve the proxies.
     * @param internalEObject
     *            The internal object to process the cross references of.
     * @return True if one or more proxy resolutions failed.
     */
    private boolean resolveProxyReferences(ResourceSet rs, InternalEObject internalEObject) {
        boolean failure = false;
        for (EObject crElement : internalEObject.eCrossReferences()) {
            if (crElement.eIsProxy()) {
                crElement = EcoreUtil.resolve(crElement, rs);
                if (crElement.eIsProxy()) {
                    failure = true;
                    logger.warn("Can not find referenced element in classpath: "
                            + ((InternalEObject) crElement).eProxyURI());
                }
            }
        }
        return failure;
    }

    /**
     * Handle any failed proxy resolution.
     *
     * @param rs
     *            The resource set to prove for any un-handled proxies.
     */
    private void handleFailedProxyResolution(ResourceSet rs) {
        logger.error("Resolution of some Proxies failed...");
        Iterator<Notifier> it = rs.getAllContents();
        while (it.hasNext()) {
            Notifier next = it.next();
            if (next instanceof EObject) {
                EObject o = (EObject) next;
                if (o.eIsProxy()) {
                    try {
                        it.remove();
                    } catch (UnsupportedOperationException e) {
                        e.printStackTrace();
                        logger.info("Error during unresolved proxy handling", e);
                    }
                }
            }
        }
    }

    /**
     * Register all jars contained in the project.
     *
     * @param projectDirectory
     *            The base directory to find jar files in.
     * @param classPath
     *            The virtual class path for the parser.
     * @return the number of jar files added to the Classpath
     * @throws IOException
     *             Any exception during jar access.
     */
    private int addAllJarFilesToClassPath(File projectDirectory, JavaClasspath classPath) throws IOException {
        Collection<File> jarFiles = FileUtils.listFiles(projectDirectory, new String[] { "jar" }, true);
        for (File jarPath : jarFiles) {
            if (jarPath.exists()) {
                classPath.registerClassifierJar(URI.createFileURI(jarPath.getCanonicalPath()));
            }
        }
        return jarFiles.size();
    }

    /**
     * Load a all files as resource in a specific folder and it's sub folders.
     *
     * @param rootFolder
     *            The root folder to recursively load all resources from.
     * @param rs
     *            The resource set to add it to.
     * @return The number of loaded java files.
     * @throws IOException
     *             An exception during resource access.
     */
    private int loadAllJavaFilesInResourceSet(File rootFolder, ResourceSet rs) throws IOException {
        Collection<File> javaFiles = FileUtils.listFiles(rootFolder, new String[] { "java" }, true);
        for (File javaFile : javaFiles) {
            parseResource(javaFile, rs);
        }
        return javaFiles.size();
    }

    /**
     * Load a specific resource.
     *
     * @param file
     *            The file object to load as resource.
     * @param rs
     *            The resource set to add it to.
     * @throws IOException
     *             An exception during resource access.
     */
    private void parseResource(File file, ResourceSet rs) throws IOException {
        loadResource(file.getCanonicalPath(), rs);
    }

    /**
     * Load a specific resource.
     *
     * @param filePath
     *            The path of the file to load.
     * @param rs
     *            The resource set to add it to.
     * @throws IOException
     *             An exception during resource access.
     */
    private void loadResource(String filePath, ResourceSet rs) throws IOException {
        loadResource(URI.createFileURI(filePath), rs);
    }

    /**
     * Load a specific resource.
     *
     * @param uri
     *            The uri of the resource.
     * @param rs
     *            The resource set to add it to.
     * @throws IOException
     *             An exception during resource access.
     */
    private void loadResource(URI uri, ResourceSet rs) throws IOException {
        rs.getResource(uri, true);
    }

    /**
     * Setup the JaMoPP resource set and prepare the parsing options for the java resource type.
     *
     * @param sourceModelDirectory
     *            The path to the directory assigned to the extracted copy.
     *
     * @return The initialized resource set.
     */
    private ResourceSet setUpResourceSet(String sourceModelDirectory) {
        ResourceSet rs = new ResourceSetImpl();
        Map<Object, Object> options = rs.getLoadOptions();
        options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.TRUE);
        options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.TRUE);
        options.put(JavaClasspath.OPTION_USE_LOCAL_CLASSPATH, Boolean.TRUE);
        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);

        ArrayList<String> directories = Lists.newArrayList();
        if (sourceModelDirectory != null) {
            directories.add(sourceModelDirectory);
        }
        prepareResourceSet(rs, directories);

        return rs;
    }

    @Override
    public String getId() {
        return EXTRACTOR_ID;
    }

    @Override
    public String getLabel() {
        return EXTRACTOR_LABEL;
    }

    @Override
    public void prepareResourceSet(ResourceSet resourceSet, List<String> sourceModelPaths) {
        Map<String, Object> factoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
        factoryMap.put("java", new JavaSourceOrClassFileResourceCachingFactoryImpl(sourceModelPaths));
    }

}

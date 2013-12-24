package org.splevo.jamopp.extraction;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.extraction.SoftwareModelExtractor;

/**
 * Software Model Extractor for the Java technology based on the Java Model Parser and Printer
 * (JaMoPP).
 */
public class JaMoPPSoftwareModelExtractor implements SoftwareModelExtractor {

    private static Logger logger = Logger.getLogger(JaMoPPSoftwareModelExtractor.class);

    private static final String EXTRACTOR_ID = "JaMoPPSoftwareModelExtractor";
    private static final String EXTRACTOR_LABEL = "JaMoPP Software Model Extractor";

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
    public ResourceSet extractSoftwareModel(List<URI> projectURIs, IProgressMonitor monitor, URI targetURI)
            throws SoftwareModelExtractionException {

        ResourceSet targetResourceSet = setUpResourceSet();
        JavaClasspath cp = JavaClasspath.get(targetResourceSet);

        for (URI projectPathURI : projectURIs) {

//            ResourceSet rs = setUpResourceSet();
//            JavaClasspath cp = JavaClasspath.get(rs);

            String projectPath = projectPathURI.toFileString();
            cp.getURIMap().put(projectPathURI, URI.createURI(""));
            File srcFolder = new File(projectPath);
            try {
                srcFolder.isDirectory();
                addAllJarFilesToClassPath(srcFolder, cp);
                loadAllFilesInResourceSet(srcFolder, ".java", targetResourceSet);

            } catch (Exception e) {
                throw new SoftwareModelExtractionException("Failed to extract software model.", e);
            }
        }

        if (!resolveAllProxies(0, targetResourceSet)) {
            handleFailedProxyResolution(targetResourceSet);
        }

        return targetResourceSet;
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
     * @throws IOException
     *             Any exception during jar access.
     */
    private void addAllJarFilesToClassPath(File projectDirectory, JavaClasspath classPath) throws IOException {

        Collection<File> jarFiles = FileUtils.listFiles(projectDirectory, new String[] { "jar" }, true);
        for (File jarPath : jarFiles) {
            if (jarPath.exists()) {
                classPath.registerClassifierJar(URI.createFileURI(jarPath.getCanonicalPath()));
            }
        }
    }

    /**
     * Load a all files as resource in a specific folder and it's sub folders.<br>
     * This ignores internal directories starting with ".".
     *
     * @param rootFolder
     *            The root folder to recursively load all resources from.
     * @param fileExtension
     *            The extension of files to load.
     * @param rs
     *            The resource set to add it to.
     * @throws IOException
     *             An exception during resource access.
     */
    private void loadAllFilesInResourceSet(File rootFolder, String fileExtension, ResourceSet rs) throws IOException {
        for (File member : rootFolder.listFiles()) {
            if (member.isFile()) {
                if (member.getName().endsWith(fileExtension)) {
                    parseResource(member, rs);
                }
            }
            if (member.isDirectory()) {
                if (!member.getName().startsWith(".")) {
                    loadAllFilesInResourceSet(member, fileExtension, rs);
                }
            }
        }
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
     * Resolve all proxies of the loaded sources.
     *
     * @param resourcesProcessedBefore
     *            The counter of processed resources for statistical reasons.
     * @param rs
     *            The resource set to resolve the proxies in.
     * @return True/False if the resolving was successful or not.
     */
    private boolean resolveAllProxies(int resourcesProcessedBefore, ResourceSet rs) {
        boolean failure = false;
        List<EObject> eobjects = new LinkedList<EObject>();
        for (Iterator<Notifier> i = rs.getAllContents(); i.hasNext();) {
            Notifier next = i.next();
            if (next instanceof EObject) {
                eobjects.add((EObject) next);
            }
        }
        int resourcesProcessed = rs.getResources().size();
        if (resourcesProcessed == resourcesProcessedBefore) {
            return true;
        }

        for (EObject next : eobjects) {
            InternalEObject nextElement = (InternalEObject) next;
            for (EObject crElement : nextElement.eCrossReferences()) {
                crElement = EcoreUtil.resolve(crElement, rs);
                if (crElement.eIsProxy()) {
                    failure = true;
                    logger.warn("Can not find referenced element in classpath: "
                            + ((InternalEObject) crElement).eProxyURI());
                }
            }
        }

        // call this method again, because the resolving might have triggered
        // loading
        // of additional resources that may also contain references that need to
        // be resolved.
        return !failure && resolveAllProxies(resourcesProcessed, rs);
    }

    /**
     * Setup the JaMoPP resource set and prepare the parsing options for the java resource type.
     *
     * @return The initialized resource set.
     */
    private ResourceSet setUpResourceSet() {
        ResourceSet rs = new ResourceSetImpl();
        Map<Object, Object> options = rs.getLoadOptions();
        options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.FALSE);
        options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);
        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);

        Map<String, Object> factoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
        factoryMap.put("java", new JavaSourceOrClassFileResourceFactoryImpl());
        factoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
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

}

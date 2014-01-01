package org.splevo.jamopp.extraction;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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

            String projectPath = projectPathURI.toFileString();
            cp.getURIMap().put(projectPathURI, URI.createURI(""));
            File srcFolder = new File(projectPath);
            try {
                srcFolder.isDirectory();
                addAllJarFilesToClassPath(srcFolder, cp);
                loadAllJavaFilesInResourceSet(srcFolder, targetResourceSet);

            } catch (Exception e) {
                throw new SoftwareModelExtractionException("Failed to extract software model.", e);
            }
        }

        logger.info("Java and Jar Files added to resource set");

        return targetResourceSet;
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
     * Load a all files as resource in a specific folder and it's sub folders.
     *
     * @param rootFolder
     *            The root folder to recursively load all resources from.
     * @param rs
     *            The resource set to add it to.
     * @throws IOException
     *             An exception during resource access.
     */
    private void loadAllJavaFilesInResourceSet(File rootFolder, ResourceSet rs) throws IOException {
        Collection<File> javaFiles = FileUtils.listFiles(rootFolder, new String[] { "java" }, true);
        for (File javaFile : javaFiles) {
            parseResource(javaFile, rs);
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

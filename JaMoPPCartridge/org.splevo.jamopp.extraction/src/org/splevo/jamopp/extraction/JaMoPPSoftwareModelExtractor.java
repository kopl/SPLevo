package org.splevo.jamopp.extraction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.commons.layout.LayoutPackage;
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

    private static final String XMI_FILE_SEGMENT = "jamopp_model.xmi";

    private static Logger logger = Logger.getLogger(JaMoPPSoftwareModelExtractor.class);

    private static final String EXTRACTOR_ID = "JaMoPPSoftwareModelExtractor";
    private static final String EXTRACTOR_LABEL = "JaMoPP Software Model Extractor";

    /**
     * Extract all java files and referenced resources to a file named according to
     * {@link JaMoPPSoftwareModelExtractor#XMI_FILE_SEGMENT} within the provided targetURI.
     * {@inheritDoc}
     */
    @Override
    public ResourceSet extractSoftwareModel(List<URI> projectURIs, IProgressMonitor monitor, URI targetURI)
            throws SoftwareModelExtractionException {
    	
        ResourceSet targetResourceSet = setUpResourceSet();
        JavaClasspath.get(targetResourceSet);

        for (URI projectPathURI : projectURIs) {

            ResourceSet rs = setUpResourceSet();
            JavaClasspath cp = JavaClasspath.get(rs);

            String projectPath = projectPathURI.toFileString();
            cp.getURIMap().put(projectPathURI, URI.createURI(""));
            File srcFolder = new File(projectPath);
            try {
            	srcFolder.isDirectory();
                addAllJarFilesToClassPath(srcFolder, cp);
                loadAllFilesInResourceSet(srcFolder, ".java", rs);

                if (!resolveAllProxies(0, rs)) {
                    handleFailedProxyResolution(rs);
                }
                
                saveToFolder(projectPathURI, targetURI, rs, targetResourceSet);
                
            } catch (Exception e) {
                throw new SoftwareModelExtractionException("Failed to extract software model.", e);
            }
        }

        return targetResourceSet;
    }

    /**
     * Take the source resource set, and move all contents to a new target resource set saved within
     * a single xmi file.
     * 
     * @param targetURI
     *            The directory to save the target file to.
     * @param rs
     *            The source resource set to load.
     * @return The resource set containing the target file and the according meta model.
     * @throws IOException
     *             failed to save the model.
     */
    @SuppressWarnings("unused")
	private ResourceSet saveToSingleXMIFile(URI targetURI, ResourceSet rs) throws IOException {

        File parentDir = new File(targetURI.toFileString());
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        URI outUri = targetURI.appendSegment(XMI_FILE_SEGMENT);

        ResourceSet targetResourceSet = new ResourceSetImpl();
        JavaClasspath.get(targetResourceSet);

        targetResourceSet.getLoadOptions().put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.TRUE);
        Resource xmiResource = targetResourceSet.createResource(outUri);
        for (Resource javaResource : new ArrayList<Resource>(rs.getResources())) {
            xmiResource.getContents().addAll(javaResource.getContents());
        }

        // save the metamodels (schemas) for dynamic loading
        serializeMetamodel(parentDir, targetResourceSet);

        saveXmiResource(xmiResource);

        return targetResourceSet;
    }

	private void saveToFolder(URI srcUri, URI targetURI, ResourceSet rs, ResourceSet targetResourceSet)
			throws IOException {

        File parentDir = new File(targetURI.toFileString());
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        URI outUri = URI.createFileURI(parentDir.getAbsolutePath());
        
		List<Resource> result = new ArrayList<Resource>();

		for (Resource javaResource : new ArrayList<Resource>(rs.getResources())) {
			URI srcURI = javaResource.getURI();
			srcURI = rs.getURIConverter().normalize(srcURI);
			URI outFileURI = outUri.appendSegments(
					srcURI.deresolve(srcUri.appendSegment("")).segments())
					.appendFileExtension("xmi");
			Resource xmiResource = rs.createResource(outFileURI);
			xmiResource.getContents().addAll(javaResource.getContents());
			result.add(xmiResource);
		}

		// save the metamodels (schemas) for dynamic loading
		//serializeMetamodel(parentDir, targetResourceSet);

		for (Resource xmiResource : result) {
			saveXmiResource(xmiResource);
			targetResourceSet.getResources().add(xmiResource);
		}
	}

    /**
     * Save a resource in the xmi resource format.
     * 
     * @param xmiResource
     *            The resource to save.
     * @throws IOException
     *             A failed saving.
     */
    private static void saveXmiResource(Resource xmiResource) throws IOException {
        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
        xmiResource.save(options);
    }

    /**
     * Serialize the meta model file with the model to ensure it can be loaded afterwards.
     * 
     * @param outFolder
     *            The folder to write to.
     * @param rs
     *            The resource set to create the meta model resource.
     * @throws IOException
     *             A failed saving process.
     */
    protected static void serializeMetamodel(File outFolder, ResourceSet rs) throws IOException {
        URI outUri = URI.createFileURI(outFolder.getCanonicalPath());

        URI javaEcoreURI = outUri.appendSegment("java.ecore");
        Resource javaEcoreResource = rs.createResource(javaEcoreURI);
        javaEcoreResource.getContents().addAll(JavaPackage.eINSTANCE.getESubpackages());

        javaEcoreResource.save(null);

        URI layoutEcoreURI = outUri.appendSegment("layout.ecore");
        Resource layoutEcoreResource = rs.createResource(layoutEcoreURI);
        layoutEcoreResource.getContents().add(LayoutPackage.eINSTANCE);

        layoutEcoreResource.save(null);
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

        // call this method again, because the resolving might have triggered loading
        // of additional resources that may also contain references that need to be resolved.
        return !failure && resolveAllProxies(resourcesProcessed, rs);
    }

    /**
     * Setup the JaMoPP resource set and prepare the parsing options for the java resource type.
     * 
     * @return The initialized resource set.
     */
    private ResourceSet setUpResourceSet() {
        ResourceSet rs = new ResourceSetImpl();
        rs.getLoadOptions().put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.FALSE);
        rs.getLoadOptions().put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);
        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);
        Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
		extensionToFactoryMap.put("java", new JavaSourceOrClassFileResourceFactoryImpl());
        extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
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

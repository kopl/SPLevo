package org.splevo.jamopp.JaMoPPVPM.software.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Base class for JaMoPPJavaSoftwareElement tests.
 */
public abstract class JaMoPPJavaSoftwareElementTest {

    /** The compilation unit of the test code. */
    protected CompilationUnit compilationUnit;
    
    /**
     * Initializes the test.
     */
    @BeforeClass
    public static void init() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }
    
    /**
     * Initialize the resources to test.
     *
     * @throws Exception
     *             An error during test initialization.
     */
    @Before
    public void setUp() throws Exception {
        ResourceSet rs = setUpResourceSet(parseLayoutInformation());
        JavaResource resource = (JavaResource) parseResource(getTestFile(), rs);
        compilationUnit = (CompilationUnit) resource.getContents().get(0);
    }

    /**
     * @return The file pointing to the java test file.
     */
    protected abstract File getTestFile();
    
    /**
     * @return True if layout information shall be parsed, False otherwise.
     */
    protected abstract boolean parseLayoutInformation();
    
    /**
     * Load a specific resource.
     *
     * @param file
     *            The file object to load as resource.
     * @param rs
     *            The resource set to add it to.
     * @return The parsed resource.
     * @throws IOException
     *             An exception during resource access.
     */
    private static Resource parseResource(File file, ResourceSet rs) throws IOException {
        String filePath = file.getCanonicalPath();
        URI uri = URI.createFileURI(filePath);
        return rs.getResource(uri, true);
    }

    /**
     * Setup the JaMoPP resource set and prepare the parsing options for the java resource type.
     *
     * @return The initialized resource set.
     */
    private static ResourceSet setUpResourceSet(boolean layoutInformation) {       
        ResourceSet rs = new ResourceSetImpl();
        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);
        Map<String, Object> extensionToFactoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
        extensionToFactoryMap.put("java", new JavaSourceOrClassFileResourceFactoryImpl());
        extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
        if (layoutInformation) {
            Map<Object, Object> options = rs.getLoadOptions();
            options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.FALSE);
            options.put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);            
        }
        return rs;
    }

}

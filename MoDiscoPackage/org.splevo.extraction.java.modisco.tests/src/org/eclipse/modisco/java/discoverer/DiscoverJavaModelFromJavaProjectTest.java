package org.eclipse.modisco.java.discoverer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.junit.Test;
import org.splevo.extraction.java.modisco.MoDiscoSoftwareModelExtractor;

/**
 * Test for the MoDisco based Java software model extractor.
 * 
 * MoDisco requires a running Eclipse workspace instance so the tests must be executed as plug-in tests.
 * 
 * In general, the tests copy a persisted test project from the "test/projects/" directory
 * to the running workspace of the Eclipse Plug-in test and ensures it to be a IJavaProject.
 * Next, this project is extracted by MoDisco and the result is written into the "tmp/" directory.
 */
public class DiscoverJavaModelFromJavaProjectTest {

    private static Logger logger = Logger.getLogger(DiscoverJavaModelFromJavaProjectTest.class);
    
    private static final String TEST_PROJECTS_DIR = "test/projects/";
    private static final String TARGET_MODEL_DIR = "tmp/";
    private static final String TEST_PROJECT = "org.splevo.tests.unresolved.a";

    /**
     * Test method for
     * {@link org.eclipse.modisco.java.discoverer.DiscoverJavaModelFromJavaProject#basicDiscoverElement(org.eclipse.jdt.core.IJavaProject, org.eclipse.core.runtime.IProgressMonitor)}
     * .
     */
    @Test
    public void testBasicDiscoverElementIJavaProjectIProgressMonitor() {

        IJavaProject javaProject = null;
        try {
            javaProject = createJavaProject(TEST_PROJECT);
        } catch (CoreException e) {
            fail("Unable to open test project");
        }

        URI targetURI = URI.createURI(TARGET_MODEL_DIR+TEST_PROJECT);
        MoDiscoSoftwareModelExtractor moDiscoSoftwareModelExtractor = new MoDiscoSoftwareModelExtractor();
        List<IJavaProject> additionalProjects = new ArrayList<IJavaProject>();
        IProgressMonitor monitor = new NullProgressMonitor();

        ResourceSet modelResourceSet = null;
        try {
            modelResourceSet = moDiscoSoftwareModelExtractor.extractProject(javaProject, additionalProjects, monitor, targetURI);
        } catch (Exception e1) {
            e1.printStackTrace();
            fail("Failed to extract the software model");
        }

        Resource modelResource = modelResourceSet.getResources().get(0);
        JavaApplication javaApp = (JavaApplication) modelResource.getContents().get(0);
        Model javaModel = javaApp.getJavaModel();
        assertNotNull("Java model not created.", javaModel);
        assertTrue("Unexpected unresolved Elements", javaModel.getUnresolvedItems().size() == 1);
        
        assertThat("", javaModel.getUnresolvedItems().size(), is(1));
    }

    /**
     * Copies a test project from the test project folder (test/projects/) to the workspace and
     * provides access to it as an IJavaProject.
     * 
     * Note: The project identified by the project name must exist in the test directory and have
     * the JavaNature assigned.
     * 
     * @param projectName
     *            The name of the project to copy and access.
     * @return The process reference.
     * @throws CoreException
     *             Identifies a failed project access.
     */
    private IJavaProject createJavaProject(String projectName) throws CoreException {

        // create the project handles
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        IProject project = root.getProject(projectName);
        project.create(null);
        project.open(null);

        // Copy the test project files to the test project
        String workingDirectory = System.getProperty("user.dir") + File.separator;
        String testWorkspaceDirectory = root.getRawLocation().makeAbsolute().toOSString() + File.separator;
        File source = new File(workingDirectory + TEST_PROJECTS_DIR + projectName);
        File target = new File(testWorkspaceDirectory + projectName);

        try {
            copyFolder(source, target);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to create source project.");
        }

        // refresh the project handler
        project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());

        // make it a java project
        IJavaProject javaProject = JavaCore.create(project);
        try {
            javaProject.open(new NullProgressMonitor());
        } catch (JavaModelException jme) {
            jme.printStackTrace();
            fail("Unable to open java project.");
        }

        return javaProject;
    }

    /**
     * Recursively copy a folder including contained files ans sub-directories.
     * 
     * The code for this method is inspired from
     * http://www.mkyong.com/java/how-to-copy-directory-in-java/
     * 
     * @param src
     *            The source directory or file.
     * @param dest
     *            The target directory.
     * @throws IOException
     *             An error during the copy process.
     */
    public static void copyFolder(File src, File dest) throws IOException {

        if (src.isDirectory()) {

            // if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
                logger.debug("Directory copied from " + src + "  to " + dest);
            }

            // list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                // construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                // recursive copy
                copyFolder(srcFile, destFile);
            }

        } else {
            // if file, then copy it
            // Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            // copy the file content in bytes
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            logger.debug("File copied from " + src + " to " + dest);
        }
    }

}

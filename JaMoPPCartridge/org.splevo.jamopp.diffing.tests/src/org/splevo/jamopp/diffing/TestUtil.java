/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.diffing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.mopp.JavaPrinter2;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.junit.BeforeClass;
import org.splevo.commons.emf.SPLevoResourceSet;
import org.splevo.extraction.SoftwareModelExtractionException;
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;

import com.google.common.collect.Lists;

/**
 * JaMoPP Test utility.
 */
public abstract class TestUtil {

    /** The logger for the test utility. */
    private static Logger logger = Logger.getLogger(TestUtil.class);

    /**
     * Get the default options to be used in the tests.
     *
     * @return A fresh map of diff options.
     */
    public static final Map<String, String> getDiffOptions() {
        Map<String, String> diffOptions = new LinkedHashMap<String, String>();
        StringBuilder builder = new StringBuilder();
        builder.append("java.*");
        builder.append(System.getProperty("line.separator"));
        builder.append("org.jscience.*");
        builder.append(System.getProperty("line.separator"));
        builder.append("javolution.*");
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, builder.toString());
        return diffOptions;
    }

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUp() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Extract the software model for a given source directory.
     *
     * @param path
     *            The base path of the implementation.
     * @return The resulting resource set.
     * @throws SoftwareModelExtractionException
     *             A failed extraction.
     */
    public static ResourceSet extractModel(String path) throws SoftwareModelExtractionException {
        NullProgressMonitor monitor = new NullProgressMonitor();
        String pathA = (new File(path)).getAbsolutePath();
        List<String> urisA = Lists.newArrayList(pathA);
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        return extractor.extractSoftwareModel(urisA, monitor, null);
    }

    /**
     * Save a project model to a specified file.
     *
     * @param comparisonModel
     *            The model to save.
     * @param relativeFilePath
     *            The eclipse workspace relative file path to save to.
     * @throws IOException
     *             identifies that the file could not be written.
     */
    public static void save(Comparison comparisonModel, String relativeFilePath) throws IOException {

        String fileExtension = getFileExtension(relativeFilePath);

        // try to write to the project file
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(fileExtension, new XMIResourceFactoryImpl());
        ResourceSet resSet = new SPLevoResourceSet();
        final Resource resource = resSet.createResource(URI.createFileURI(relativeFilePath));
        resource.getContents().add(comparisonModel);

        resource.save(Collections.EMPTY_MAP);
    }

    /**
     * Get the text representation of the changed software element contained referenced by a diff.
     *
     * @param diff
     *            The diff element to print.
     * @return the string representation of the elements code.
     */
    public static String printDiff(Diff diff) {

        EObject changedElement = null;
        if (diff instanceof StatementChange) {
            StatementChange change = (StatementChange) diff;
            changedElement = change.getChangedStatement();
        } else if (diff instanceof ImportChange) {
            ImportChange change = (ImportChange) diff;
            changedElement = change.getChangedImport();
        } else if (diff instanceof CompilationUnitChange) {
            CompilationUnitChange change = (CompilationUnitChange) diff;
            changedElement = change.getChangedCompilationUnit();

        } else if (diff instanceof ClassChange) {
            ClassChange change = (ClassChange) diff;
            changedElement = change.getChangedClass();

        } else {
            logger.warn("Unexpected diff class" + diff.getClass().getSimpleName());
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JavaPrinter2 printer = new JavaPrinter2(stream, new JavaResource());
        try {
            printer.print(changedElement);
        } catch (IOException e) {
            logger.warn("Failed to print: ", e);
        }
        return stream.toString();
    }

    /**
     * Get the file extension of a file.
     *
     * @param path
     *            The file object to get the extension for.
     * @return The file extension or null if none found.
     */
    private static String getFileExtension(String path) {
        if (path == null) {
            return null;
        }
        String fileExtension = path.substring(path.lastIndexOf('.') + 1);
        return fileExtension;
    }

    /**
     * Load the resource set for a set of java files.
     *
     * @param sourceFiles
     *            The source files to load in the resource set.
     * @return The prepared resource set.
     * @throws IOException
     *             A failure during file load.
     */
    public static ResourceSet loadResourceSet(Set<File> sourceFiles) throws IOException {
        ResourceSet rs = setUpResourceSet();
        JavaClasspath.get(rs);
        for (File sourceFile : sourceFiles) {
            parseResource(sourceFile, rs);
        }

        if (!resolveAllProxies(0, rs)) {
            handleFailedProxyResolution(rs);
        }

        return rs;
    }

    /**
     * Handle any failed proxy resolution.
     *
     * @param rs
     *            The resource set to prove for any un-handled proxies.
     */
    private static void handleFailedProxyResolution(ResourceSet rs) {
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
     * Resolve all proxies of the loaded sources.
     *
     * @param resourcesProcessedBefore
     *            The counter of processed resources for statistical reasons.
     * @param rs
     *            The resource set to resolve the proxies in.
     * @return True/False if the resolving was successful or not.
     */
    private static boolean resolveAllProxies(int resourcesProcessedBefore, ResourceSet rs) {
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
                }
            }
        }

        // call this method again, because the resolving might have triggered loading
        // of additional resources that may also contain references that need to be resolved.
        return !failure && resolveAllProxies(resourcesProcessed, rs);
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
    private static void parseResource(File file, ResourceSet rs) throws IOException {
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
    private static void loadResource(String filePath, ResourceSet rs) throws IOException {
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
    private static void loadResource(URI uri, ResourceSet rs) throws IOException {
        rs.getResource(uri, true);
    }

    /**
     * Setup the JaMoPP resource set and prepare the parsing options for the java resource type.
     *
     * @return The initialized resource set.
     */
    private static ResourceSet setUpResourceSet() {
        ResourceSet rs = new SPLevoResourceSet();
        rs.getLoadOptions().put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, Boolean.FALSE);
        rs.getLoadOptions().put(IJavaOptions.DISABLE_LOCATION_MAP, Boolean.FALSE);
        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);
        Map<String, Object> extensionToFactoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
        extensionToFactoryMap.put("java", new JavaSourceOrClassFileResourceFactoryImpl());
        extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
        return rs;
    }
}

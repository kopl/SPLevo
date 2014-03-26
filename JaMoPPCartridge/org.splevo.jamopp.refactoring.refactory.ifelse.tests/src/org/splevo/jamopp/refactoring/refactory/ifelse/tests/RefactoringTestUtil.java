/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.jamopp.refactoring.refactory.ifelse.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.refactoring.refactoring_specification.RefactoringSpecification;
import org.emftext.language.refactoring.rolemapping.RoleMapping;
import org.emftext.language.refactoring.rolemapping.RoleMappingModel;
import org.emftext.language.refactoring.roles.RoleModel;
import org.emftext.refactoring.registry.refactoringspecification.IRefactoringSpecificationRegistry;
import org.emftext.refactoring.registry.refactoringspecification.exceptions.RefSpecAlreadyRegisteredException;
import org.emftext.refactoring.registry.rolemapping.IRoleMappingRegistry;
import org.emftext.refactoring.registry.rolemapping.exceptions.RoleMappingAlreadyRegistered;
import org.emftext.refactoring.registry.rolemodel.IRoleModelRegistry;
import org.emftext.refactoring.registry.rolemodel.exceptions.RoleModelAlreadyRegisteredException;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Utility class for writing unit tests for refactory based refactorings.
 */
public final class RefactoringTestUtil {


    /** Disabled default constructor to force static utility usage. */
    private RefactoringTestUtil() {
    }

    /**
     * Initialize the variation point model to refactor. Extract, Diff and init VPM.
     *
     * @param basePath
     *            The base path of the code to load (must contain subdirectories a and b)
     * @return The initialized VPM based on the source code differences.
     * @throws Exception
     *             Failed to initialize the model.
     */
    public static VariationPointModel initializeVariationPointModel(String basePath) throws Exception {
        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        List<String> urisA = Lists.newArrayList(new File(basePath + "a").getAbsolutePath());
        List<String> urisB = Lists.newArrayList(new File(basePath + "b").getAbsolutePath());
        NullProgressMonitor monitor = new NullProgressMonitor();
        ResourceSet setA = extractor.extractSoftwareModel(urisA, monitor, null);
        ResourceSet setB = extractor.extractSoftwareModel(urisB, monitor, null);

        String ignorePackages = buildIgnorePackages();

        Map<String, String> diffOptions = Maps.newLinkedHashMap();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
        VariationPointModel vpm = builder.buildVPM(comparison, "leading", "integration");
        return vpm;
    }

    /**
     * Build the configuration string for the packages to ignore.
     *
     * @return The regular expressions for the packages to ignore.
     */
    private static String buildIgnorePackages() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.*");
        sb.append(System.getProperty("line.separator"));
        String ignorePackages = sb.toString();
        return ignorePackages;
    }

	/**
	 * Registers the role model of the given name.
	 *
	 * @param modelFile
	 *            The modelFile.
	 * @throws RoleModelAlreadyRegisteredException
	 *             If the model is already registered.
	 * @throws FileNotFoundException
	 *             thrown if no file was found at the given path.
	 */
	public static void registerRoleModel(File modelFile)
			throws RoleModelAlreadyRegisteredException, FileNotFoundException {
		RoleModel roleModel = getModelByType(modelFile, RoleModel.class);
		IRoleModelRegistry.INSTANCE.registerRoleModel(roleModel);
	}

	/**
	 * Registers the ref specs of the given name.
	 *
	 * @param modelFile
	 *            The modelFile.
	 * @throws RefSpecAlreadyRegisteredException
	 *             If the model is already registered.
	 * @throws FileNotFoundException
	 *             thrown if no file was found at the given path.
	 */
	public static void registerRefSpec(File modelFile)
			throws RefSpecAlreadyRegisteredException, FileNotFoundException {
		RefactoringSpecification refSpec = getModelByType(modelFile,
				RefactoringSpecification.class);
		IRefactoringSpecificationRegistry.INSTANCE.registerRefSpec(refSpec);
	}

	/**
	 * Registers the role mappings of a given name and related post processors.
	 *
	 * @param modelFile
	 *            The modelFile.
	 * @throws RoleMappingAlreadyRegistered
	 *             If the model is already registered.
	 * @throws FileNotFoundException
	 *             thrown if no file was found at the given path.
	 */
	public static void registerRoleMapping(File modelFile)
			throws RoleMappingAlreadyRegistered, FileNotFoundException {
		RoleMappingModel roleMappingModel = getModelByType(modelFile,
				RoleMappingModel.class);
		RoleMapping roleMapping = roleMappingModel.getMappings().get(0);
		IRoleMappingRegistry.INSTANCE.registerRoleMapping(roleMapping);
	}

	/**
	 * Gets a model of a specific type from a specified path.
	 *
	 * @param path
	 *            The path.
	 * @param type
	 *            The model type.
	 * @param <T>
	 *            The model type.
	 * @return The loaded model.
	 * @throws FileNotFoundException
	 *             thrown if no file was found at the given path.
	 */
	private static <T> T getModelByType(File modelFile, Class<T> type)
			throws FileNotFoundException {
		if (modelFile == null || type == null) {
			throw new IllegalArgumentException();
		}

		// check whether files exists; throw exception otherwise
		if (!modelFile.exists()) {
			throw new FileNotFoundException();
		}

		// get resource; throw exception if not found
		ResourceSet rs = new ResourceSetImpl();
		URI uri = URI.createFileURI(modelFile.getAbsolutePath());
		Resource resource = rs.getResource(uri, true);
		if (resource == null) {
			throw new NullPointerException("Resource couldn't be loaded");
		}

		EObject model = resource.getContents().get(0);
		if (model == null) {
			throw new NullPointerException("Resource is empty");
		}
		if (!type.isAssignableFrom(model.getClass())) {
			throw new NullPointerException("Model must be an instance of "
					+ type.getName());
		}

		return type.cast(model);
	}
}

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
package org.splevo.project.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.splevo.project.ProjectFactory;
import org.splevo.project.ProjectPackage;
import org.splevo.project.QualityGoal;
import org.splevo.project.SPLProfile;
import org.splevo.project.SPLevoProject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProjectPackageImpl extends EPackageImpl implements ProjectPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass spLevoProjectEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass differOptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass splProfileEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum qualityGoalEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.splevo.project.ProjectPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private ProjectPackageImpl() {
        super(eNS_URI, ProjectFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link ProjectPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static ProjectPackage init() {
        if (isInited) return (ProjectPackage)EPackage.Registry.INSTANCE.getEPackage(ProjectPackage.eNS_URI);

        // Obtain or create and register package
        ProjectPackageImpl theProjectPackage = (ProjectPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ProjectPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ProjectPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theProjectPackage.createPackageContents();

        // Initialize created meta-data
        theProjectPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theProjectPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ProjectPackage.eNS_URI, theProjectPackage);
        return theProjectPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSPLevoProject() {
        return spLevoProjectEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_Name() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_Description() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_SourceModelPathLeading() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_SourceModelPathIntegration() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_LeadingProjects() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_IntegrationProjects() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_Workspace() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_VariantNameLeading() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_VariantNameIntegration() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_DiffingModelPath() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(9);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_VpmModelPaths() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(10);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSPLevoProject_DiffingFilterRules() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(11);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPLevoProject_DifferIds() {
        return (EAttribute)spLevoProjectEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPLevoProject_DifferOptions() {
        return (EReference)spLevoProjectEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPLevoProject_SplProfile() {
        return (EReference)spLevoProjectEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDifferOption() {
        return differOptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDifferOption_Key() {
        return (EAttribute)differOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDifferOption_Value() {
        return (EAttribute)differOptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSPLProfile() {
        return splProfileEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPLProfile_RecommendedRefactoringIds() {
        return (EAttribute)splProfileEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPLProfile_QualityGoals() {
        return (EAttribute)splProfileEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getQualityGoal() {
        return qualityGoalEEnum;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ProjectFactory getProjectFactory() {
        return (ProjectFactory)getEFactoryInstance();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

	/**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        spLevoProjectEClass = createEClass(SP_LEVO_PROJECT);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__NAME);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__DESCRIPTION);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__LEADING_PROJECTS);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__INTEGRATION_PROJECTS);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__WORKSPACE);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__VARIANT_NAME_LEADING);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__DIFFING_MODEL_PATH);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__VPM_MODEL_PATHS);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__DIFFING_FILTER_RULES);
        createEAttribute(spLevoProjectEClass, SP_LEVO_PROJECT__DIFFER_IDS);
        createEReference(spLevoProjectEClass, SP_LEVO_PROJECT__DIFFER_OPTIONS);
        createEReference(spLevoProjectEClass, SP_LEVO_PROJECT__SPL_PROFILE);

        differOptionEClass = createEClass(DIFFER_OPTION);
        createEAttribute(differOptionEClass, DIFFER_OPTION__KEY);
        createEAttribute(differOptionEClass, DIFFER_OPTION__VALUE);

        splProfileEClass = createEClass(SPL_PROFILE);
        createEAttribute(splProfileEClass, SPL_PROFILE__RECOMMENDED_REFACTORING_IDS);
        createEAttribute(splProfileEClass, SPL_PROFILE__QUALITY_GOALS);

        // Create enums
        qualityGoalEEnum = createEEnum(QUALITY_GOAL);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

	/**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(spLevoProjectEClass, SPLevoProject.class, "SPLevoProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSPLevoProject_Name(), ecorePackage.getEString(), "name", null, 0, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_Description(), ecorePackage.getEString(), "description", null, 0, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_SourceModelPathLeading(), ecorePackage.getEString(), "sourceModelPathLeading", null, 1, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_SourceModelPathIntegration(), ecorePackage.getEString(), "sourceModelPathIntegration", null, 1, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_LeadingProjects(), ecorePackage.getEString(), "leadingProjects", null, 0, -1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_IntegrationProjects(), ecorePackage.getEString(), "integrationProjects", null, 0, -1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_Workspace(), ecorePackage.getEString(), "workspace", null, 1, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_VariantNameLeading(), ecorePackage.getEString(), "variantNameLeading", null, 1, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_VariantNameIntegration(), ecorePackage.getEString(), "variantNameIntegration", null, 1, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_DiffingModelPath(), ecorePackage.getEString(), "diffingModelPath", null, 0, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_VpmModelPaths(), ecorePackage.getEString(), "vpmModelPaths", null, 0, -1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_DiffingFilterRules(), ecorePackage.getEString(), "diffingFilterRules", "", 1, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLevoProject_DifferIds(), ecorePackage.getEString(), "differIds", null, 1, -1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSPLevoProject_DifferOptions(), this.getDifferOption(), null, "differOptions", null, 0, -1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSPLevoProject_SplProfile(), this.getSPLProfile(), null, "splProfile", null, 0, 1, SPLevoProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(differOptionEClass, Map.Entry.class, "DifferOption", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDifferOption_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDifferOption_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(splProfileEClass, SPLProfile.class, "SPLProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSPLProfile_RecommendedRefactoringIds(), ecorePackage.getEString(), "recommendedRefactoringIds", null, 0, -1, SPLProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPLProfile_QualityGoals(), this.getQualityGoal(), "qualityGoals", null, 0, -1, SPLProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(qualityGoalEEnum, QualityGoal.class, "QualityGoal");
        addEEnumLiteral(qualityGoalEEnum, QualityGoal.REDUCE_REDUNDANCY);
        addEEnumLiteral(qualityGoalEEnum, QualityGoal.REDUCE_COMPLEXITY);

        // Create resource
        createResource(eNS_URI);
    }

} //ProjectPackageImpl

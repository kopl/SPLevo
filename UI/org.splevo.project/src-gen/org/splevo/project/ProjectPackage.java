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
package org.splevo.project;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.splevo.project.ProjectFactory
 * @model kind="package"
 * @generated
 */
public interface ProjectPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "project";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://org.splevo.project/1.0";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "project";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ProjectPackage eINSTANCE = org.splevo.project.impl.ProjectPackageImpl.init();

	/**
     * The meta object id for the '{@link org.splevo.project.impl.SPLevoProjectImpl <em>SP Levo Project</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.splevo.project.impl.SPLevoProjectImpl
     * @see org.splevo.project.impl.ProjectPackageImpl#getSPLevoProject()
     * @generated
     */
	int SP_LEVO_PROJECT = 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__NAME = 0;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__DESCRIPTION = 1;

	/**
     * The feature id for the '<em><b>Source Model Path Leading</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING = 2;

	/**
     * The feature id for the '<em><b>Source Model Path Integration</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION = 3;

	/**
     * The feature id for the '<em><b>Leading Projects</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__LEADING_PROJECTS = 4;

	/**
     * The feature id for the '<em><b>Integration Projects</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__INTEGRATION_PROJECTS = 5;

	/**
     * The feature id for the '<em><b>Workspace</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__WORKSPACE = 6;

	/**
     * The feature id for the '<em><b>Variant Name Leading</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__VARIANT_NAME_LEADING = 7;

	/**
     * The feature id for the '<em><b>Variant Name Integration</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION = 8;

	/**
     * The feature id for the '<em><b>Diffing Model Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__DIFFING_MODEL_PATH = 9;

	/**
     * The feature id for the '<em><b>Vpm Model References</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_PROJECT__VPM_MODEL_REFERENCES = 10;

    /**
     * The feature id for the '<em><b>Diffing Filter Rules</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT__DIFFING_FILTER_RULES = 11;

    /**
     * The feature id for the '<em><b>Differ Ids</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_PROJECT__DIFFER_IDS = 12;

    /**
     * The feature id for the '<em><b>Differ Options</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_PROJECT__DIFFER_OPTIONS = 13;

    /**
     * The feature id for the '<em><b>Spl Profile</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_PROJECT__SPL_PROFILE = 14;

    /**
     * The feature id for the '<em><b>Fm Builder Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SP_LEVO_PROJECT__FM_BUILDER_ID = 15;

    /**
     * The number of structural features of the '<em>SP Levo Project</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SP_LEVO_PROJECT_FEATURE_COUNT = 16;

	/**
     * The meta object id for the '{@link org.splevo.project.impl.DifferOptionImpl <em>Differ Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.project.impl.DifferOptionImpl
     * @see org.splevo.project.impl.ProjectPackageImpl#getDifferOption()
     * @generated
     */
    int DIFFER_OPTION = 1;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIFFER_OPTION__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIFFER_OPTION__VALUE = 1;

    /**
     * The number of structural features of the '<em>Differ Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIFFER_OPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.splevo.project.impl.SPLProfileImpl <em>SPL Profile</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.project.impl.SPLProfileImpl
     * @see org.splevo.project.impl.ProjectPackageImpl#getSPLProfile()
     * @generated
     */
    int SPL_PROFILE = 2;

    /**
     * The feature id for the '<em><b>Recommended Refactoring Ids</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPL_PROFILE__RECOMMENDED_REFACTORING_IDS = 0;

    /**
     * The feature id for the '<em><b>Quality Goals</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPL_PROFILE__QUALITY_GOALS = 1;

    /**
     * The number of structural features of the '<em>SPL Profile</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPL_PROFILE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.splevo.project.impl.VPMModelReferenceImpl <em>VPM Model Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.project.impl.VPMModelReferenceImpl
     * @see org.splevo.project.impl.ProjectPackageImpl#getVPMModelReference()
     * @generated
     */
    int VPM_MODEL_REFERENCE = 3;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VPM_MODEL_REFERENCE__PATH = 0;

    /**
     * The feature id for the '<em><b>Refactoring Started</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VPM_MODEL_REFERENCE__REFACTORING_STARTED = 1;

    /**
     * The number of structural features of the '<em>VPM Model Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VPM_MODEL_REFERENCE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.splevo.project.QualityGoal <em>Quality Goal</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.splevo.project.QualityGoal
     * @see org.splevo.project.impl.ProjectPackageImpl#getQualityGoal()
     * @generated
     */
    int QUALITY_GOAL = 4;

    /**
     * Returns the meta object for class '{@link org.splevo.project.SPLevoProject <em>SP Levo Project</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>SP Levo Project</em>'.
     * @see org.splevo.project.SPLevoProject
     * @generated
     */
	EClass getSPLevoProject();

	/**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.splevo.project.SPLevoProject#getName()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_Name();

	/**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.splevo.project.SPLevoProject#getDescription()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_Description();

	/**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getSourceModelPathLeading <em>Source Model Path Leading</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source Model Path Leading</em>'.
     * @see org.splevo.project.SPLevoProject#getSourceModelPathLeading()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_SourceModelPathLeading();

	/**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getSourceModelPathIntegration <em>Source Model Path Integration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source Model Path Integration</em>'.
     * @see org.splevo.project.SPLevoProject#getSourceModelPathIntegration()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_SourceModelPathIntegration();

	/**
     * Returns the meta object for the attribute list '{@link org.splevo.project.SPLevoProject#getLeadingProjects <em>Leading Projects</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Leading Projects</em>'.
     * @see org.splevo.project.SPLevoProject#getLeadingProjects()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_LeadingProjects();

	/**
     * Returns the meta object for the attribute list '{@link org.splevo.project.SPLevoProject#getIntegrationProjects <em>Integration Projects</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Integration Projects</em>'.
     * @see org.splevo.project.SPLevoProject#getIntegrationProjects()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_IntegrationProjects();

	/**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getWorkspace <em>Workspace</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Workspace</em>'.
     * @see org.splevo.project.SPLevoProject#getWorkspace()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_Workspace();

	/**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getVariantNameLeading <em>Variant Name Leading</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Variant Name Leading</em>'.
     * @see org.splevo.project.SPLevoProject#getVariantNameLeading()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_VariantNameLeading();

	/**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getVariantNameIntegration <em>Variant Name Integration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Variant Name Integration</em>'.
     * @see org.splevo.project.SPLevoProject#getVariantNameIntegration()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_VariantNameIntegration();

	/**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getDiffingModelPath <em>Diffing Model Path</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Diffing Model Path</em>'.
     * @see org.splevo.project.SPLevoProject#getDiffingModelPath()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_DiffingModelPath();

	/**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getDiffingFilterRules <em>Diffing Filter Rules</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Diffing Filter Rules</em>'.
     * @see org.splevo.project.SPLevoProject#getDiffingFilterRules()
     * @see #getSPLevoProject()
     * @generated
     */
	EAttribute getSPLevoProject_DiffingFilterRules();

	/**
     * Returns the meta object for the attribute list '{@link org.splevo.project.SPLevoProject#getDifferIds <em>Differ Ids</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Differ Ids</em>'.
     * @see org.splevo.project.SPLevoProject#getDifferIds()
     * @see #getSPLevoProject()
     * @generated
     */
    EAttribute getSPLevoProject_DifferIds();

    /**
     * Returns the meta object for the map '{@link org.splevo.project.SPLevoProject#getDifferOptions <em>Differ Options</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Differ Options</em>'.
     * @see org.splevo.project.SPLevoProject#getDifferOptions()
     * @see #getSPLevoProject()
     * @generated
     */
    EReference getSPLevoProject_DifferOptions();

    /**
     * Returns the meta object for the containment reference '{@link org.splevo.project.SPLevoProject#getSplProfile <em>Spl Profile</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Spl Profile</em>'.
     * @see org.splevo.project.SPLevoProject#getSplProfile()
     * @see #getSPLevoProject()
     * @generated
     */
    EReference getSPLevoProject_SplProfile();

    /**
     * Returns the meta object for the attribute '{@link org.splevo.project.SPLevoProject#getFmBuilderId <em>Fm Builder Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fm Builder Id</em>'.
     * @see org.splevo.project.SPLevoProject#getFmBuilderId()
     * @see #getSPLevoProject()
     * @generated
     */
    EAttribute getSPLevoProject_FmBuilderId();

    /**
     * Returns the meta object for the containment reference list '{@link org.splevo.project.SPLevoProject#getVpmModelReferences <em>Vpm Model References</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Vpm Model References</em>'.
     * @see org.splevo.project.SPLevoProject#getVpmModelReferences()
     * @see #getSPLevoProject()
     * @generated
     */
    EReference getSPLevoProject_VpmModelReferences();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Differ Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Differ Option</em>'.
     * @see java.util.Map.Entry
     * @model keyDataType="org.eclipse.emf.ecore.EString"
     *        valueDataType="org.eclipse.emf.ecore.EString"
     * @generated
     */
    EClass getDifferOption();

    /**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getDifferOption()
     * @generated
     */
    EAttribute getDifferOption_Key();

    /**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getDifferOption()
     * @generated
     */
    EAttribute getDifferOption_Value();

    /**
     * Returns the meta object for class '{@link org.splevo.project.SPLProfile <em>SPL Profile</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SPL Profile</em>'.
     * @see org.splevo.project.SPLProfile
     * @generated
     */
    EClass getSPLProfile();

    /**
     * Returns the meta object for the attribute list '{@link org.splevo.project.SPLProfile#getRecommendedRefactoringIds <em>Recommended Refactoring Ids</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Recommended Refactoring Ids</em>'.
     * @see org.splevo.project.SPLProfile#getRecommendedRefactoringIds()
     * @see #getSPLProfile()
     * @generated
     */
    EAttribute getSPLProfile_RecommendedRefactoringIds();

    /**
     * Returns the meta object for the attribute list '{@link org.splevo.project.SPLProfile#getQualityGoals <em>Quality Goals</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Quality Goals</em>'.
     * @see org.splevo.project.SPLProfile#getQualityGoals()
     * @see #getSPLProfile()
     * @generated
     */
    EAttribute getSPLProfile_QualityGoals();

    /**
     * Returns the meta object for class '{@link org.splevo.project.VPMModelReference <em>VPM Model Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>VPM Model Reference</em>'.
     * @see org.splevo.project.VPMModelReference
     * @generated
     */
    EClass getVPMModelReference();

    /**
     * Returns the meta object for the attribute '{@link org.splevo.project.VPMModelReference#getPath <em>Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Path</em>'.
     * @see org.splevo.project.VPMModelReference#getPath()
     * @see #getVPMModelReference()
     * @generated
     */
    EAttribute getVPMModelReference_Path();

    /**
     * Returns the meta object for the attribute '{@link org.splevo.project.VPMModelReference#isRefactoringStarted <em>Refactoring Started</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Refactoring Started</em>'.
     * @see org.splevo.project.VPMModelReference#isRefactoringStarted()
     * @see #getVPMModelReference()
     * @generated
     */
    EAttribute getVPMModelReference_RefactoringStarted();

    /**
     * Returns the meta object for enum '{@link org.splevo.project.QualityGoal <em>Quality Goal</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Quality Goal</em>'.
     * @see org.splevo.project.QualityGoal
     * @generated
     */
    EEnum getQualityGoal();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	ProjectFactory getProjectFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.splevo.project.impl.SPLevoProjectImpl <em>SP Levo Project</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.splevo.project.impl.SPLevoProjectImpl
         * @see org.splevo.project.impl.ProjectPackageImpl#getSPLevoProject()
         * @generated
         */
		EClass SP_LEVO_PROJECT = eINSTANCE.getSPLevoProject();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__NAME = eINSTANCE.getSPLevoProject_Name();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__DESCRIPTION = eINSTANCE.getSPLevoProject_Description();

		/**
         * The meta object literal for the '<em><b>Source Model Path Leading</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING = eINSTANCE.getSPLevoProject_SourceModelPathLeading();

		/**
         * The meta object literal for the '<em><b>Source Model Path Integration</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION = eINSTANCE.getSPLevoProject_SourceModelPathIntegration();

		/**
         * The meta object literal for the '<em><b>Leading Projects</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__LEADING_PROJECTS = eINSTANCE.getSPLevoProject_LeadingProjects();

		/**
         * The meta object literal for the '<em><b>Integration Projects</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__INTEGRATION_PROJECTS = eINSTANCE.getSPLevoProject_IntegrationProjects();

		/**
         * The meta object literal for the '<em><b>Workspace</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__WORKSPACE = eINSTANCE.getSPLevoProject_Workspace();

		/**
         * The meta object literal for the '<em><b>Variant Name Leading</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__VARIANT_NAME_LEADING = eINSTANCE.getSPLevoProject_VariantNameLeading();

		/**
         * The meta object literal for the '<em><b>Variant Name Integration</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION = eINSTANCE.getSPLevoProject_VariantNameIntegration();

		/**
         * The meta object literal for the '<em><b>Diffing Model Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__DIFFING_MODEL_PATH = eINSTANCE.getSPLevoProject_DiffingModelPath();

		/**
         * The meta object literal for the '<em><b>Diffing Filter Rules</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SP_LEVO_PROJECT__DIFFING_FILTER_RULES = eINSTANCE.getSPLevoProject_DiffingFilterRules();

		/**
         * The meta object literal for the '<em><b>Differ Ids</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SP_LEVO_PROJECT__DIFFER_IDS = eINSTANCE.getSPLevoProject_DifferIds();

        /**
         * The meta object literal for the '<em><b>Differ Options</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SP_LEVO_PROJECT__DIFFER_OPTIONS = eINSTANCE.getSPLevoProject_DifferOptions();

        /**
         * The meta object literal for the '<em><b>Spl Profile</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SP_LEVO_PROJECT__SPL_PROFILE = eINSTANCE.getSPLevoProject_SplProfile();

        /**
         * The meta object literal for the '<em><b>Fm Builder Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SP_LEVO_PROJECT__FM_BUILDER_ID = eINSTANCE.getSPLevoProject_FmBuilderId();

        /**
         * The meta object literal for the '<em><b>Vpm Model References</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SP_LEVO_PROJECT__VPM_MODEL_REFERENCES = eINSTANCE.getSPLevoProject_VpmModelReferences();

        /**
         * The meta object literal for the '{@link org.splevo.project.impl.DifferOptionImpl <em>Differ Option</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.project.impl.DifferOptionImpl
         * @see org.splevo.project.impl.ProjectPackageImpl#getDifferOption()
         * @generated
         */
        EClass DIFFER_OPTION = eINSTANCE.getDifferOption();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIFFER_OPTION__KEY = eINSTANCE.getDifferOption_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIFFER_OPTION__VALUE = eINSTANCE.getDifferOption_Value();

        /**
         * The meta object literal for the '{@link org.splevo.project.impl.SPLProfileImpl <em>SPL Profile</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.project.impl.SPLProfileImpl
         * @see org.splevo.project.impl.ProjectPackageImpl#getSPLProfile()
         * @generated
         */
        EClass SPL_PROFILE = eINSTANCE.getSPLProfile();

        /**
         * The meta object literal for the '<em><b>Recommended Refactoring Ids</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPL_PROFILE__RECOMMENDED_REFACTORING_IDS = eINSTANCE.getSPLProfile_RecommendedRefactoringIds();

        /**
         * The meta object literal for the '<em><b>Quality Goals</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPL_PROFILE__QUALITY_GOALS = eINSTANCE.getSPLProfile_QualityGoals();

        /**
         * The meta object literal for the '{@link org.splevo.project.impl.VPMModelReferenceImpl <em>VPM Model Reference</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.project.impl.VPMModelReferenceImpl
         * @see org.splevo.project.impl.ProjectPackageImpl#getVPMModelReference()
         * @generated
         */
        EClass VPM_MODEL_REFERENCE = eINSTANCE.getVPMModelReference();

        /**
         * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VPM_MODEL_REFERENCE__PATH = eINSTANCE.getVPMModelReference_Path();

        /**
         * The meta object literal for the '<em><b>Refactoring Started</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VPM_MODEL_REFERENCE__REFACTORING_STARTED = eINSTANCE.getVPMModelReference_RefactoringStarted();

        /**
         * The meta object literal for the '{@link org.splevo.project.QualityGoal <em>Quality Goal</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.splevo.project.QualityGoal
         * @see org.splevo.project.impl.ProjectPackageImpl#getQualityGoal()
         * @generated
         */
        EEnum QUALITY_GOAL = eINSTANCE.getQualityGoal();

	}

} //ProjectPackage

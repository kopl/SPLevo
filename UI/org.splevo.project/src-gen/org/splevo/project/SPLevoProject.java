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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SP Levo Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.splevo.project.SPLevoProject#getName <em>Name</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getDescription <em>Description</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getSourceModelPathLeading <em>Source Model Path Leading</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getSourceModelPathIntegration <em>Source Model Path Integration</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getLeadingProjects <em>Leading Projects</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getIntegrationProjects <em>Integration Projects</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getWorkspace <em>Workspace</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getVariantNameLeading <em>Variant Name Leading</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getVariantNameIntegration <em>Variant Name Integration</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getDiffingModelPath <em>Diffing Model Path</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getVpmModelReferences <em>Vpm Model References</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getDiffingFilterRules <em>Diffing Filter Rules</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getDifferIds <em>Differ Ids</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getDifferOptions <em>Differ Options</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getSplProfile <em>Spl Profile</em>}</li>
 *   <li>{@link org.splevo.project.SPLevoProject#getFmBuilderId <em>Fm Builder Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.splevo.project.ProjectPackage#getSPLevoProject()
 * @model
 * @generated
 */
public interface SPLevoProject extends EObject {
	/**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_Description()
     * @model
     * @generated
     */
	String getDescription();

	/**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

	/**
     * Returns the value of the '<em><b>Source Model Path Leading</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Get the path to the source model of the leading implementation.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source Model Path Leading</em>' attribute.
     * @see #setSourceModelPathLeading(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_SourceModelPathLeading()
     * @model required="true"
     * @generated
     */
	String getSourceModelPathLeading();

	/**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getSourceModelPathLeading <em>Source Model Path Leading</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Model Path Leading</em>' attribute.
     * @see #getSourceModelPathLeading()
     * @generated
     */
	void setSourceModelPathLeading(String value);

	/**
     * Returns the value of the '<em><b>Source Model Path Integration</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The file path to the source model of the variant to integration.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source Model Path Integration</em>' attribute.
     * @see #setSourceModelPathIntegration(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_SourceModelPathIntegration()
     * @model required="true"
     * @generated
     */
	String getSourceModelPathIntegration();

	/**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getSourceModelPathIntegration <em>Source Model Path Integration</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Model Path Integration</em>' attribute.
     * @see #getSourceModelPathIntegration()
     * @generated
     */
	void setSourceModelPathIntegration(String value);

	/**
     * Returns the value of the '<em><b>Leading Projects</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The names of the projects with the leading implementation
     * <!-- end-model-doc -->
     * @return the value of the '<em>Leading Projects</em>' attribute list.
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_LeadingProjects()
     * @model
     * @generated
     */
	EList<String> getLeadingProjects();

	/**
     * Returns the value of the '<em><b>Integration Projects</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The names of the projects with the leading implementation
     * <!-- end-model-doc -->
     * @return the value of the '<em>Integration Projects</em>' attribute list.
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_IntegrationProjects()
     * @model
     * @generated
     */
	EList<String> getIntegrationProjects();

	/**
     * Returns the value of the '<em><b>Workspace</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The workspace directory to process the project in.
     * This directory has to follow a splevo specific structure of subdirectories. If they do not exist, they will be created automatically during the process.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Workspace</em>' attribute.
     * @see #setWorkspace(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_Workspace()
     * @model required="true"
     * @generated
     */
	String getWorkspace();

	/**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getWorkspace <em>Workspace</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Workspace</em>' attribute.
     * @see #getWorkspace()
     * @generated
     */
	void setWorkspace(String value);

	/**
     * Returns the value of the '<em><b>Variant Name Leading</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The working name of the leading variant.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Variant Name Leading</em>' attribute.
     * @see #setVariantNameLeading(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_VariantNameLeading()
     * @model required="true"
     * @generated
     */
	String getVariantNameLeading();

	/**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getVariantNameLeading <em>Variant Name Leading</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variant Name Leading</em>' attribute.
     * @see #getVariantNameLeading()
     * @generated
     */
	void setVariantNameLeading(String value);

	/**
     * Returns the value of the '<em><b>Variant Name Integration</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The working name of the variant to be integrated.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Variant Name Integration</em>' attribute.
     * @see #setVariantNameIntegration(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_VariantNameIntegration()
     * @model required="true"
     * @generated
     */
	String getVariantNameIntegration();

	/**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getVariantNameIntegration <em>Variant Name Integration</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variant Name Integration</em>' attribute.
     * @see #getVariantNameIntegration()
     * @generated
     */
	void setVariantNameIntegration(String value);

	/**
     * Returns the value of the '<em><b>Diffing Model Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The path to the diffing model. It is optional, because the model might only exist within a temporary instance for the initial vpm creation.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Diffing Model Path</em>' attribute.
     * @see #setDiffingModelPath(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_DiffingModelPath()
     * @model
     * @generated
     */
	String getDiffingModelPath();

	/**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getDiffingModelPath <em>Diffing Model Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Diffing Model Path</em>' attribute.
     * @see #getDiffingModelPath()
     * @generated
     */
	void setDiffingModelPath(String value);

	/**
     * Returns the value of the '<em><b>Diffing Filter Rules</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The rules to filter the packages during the diffing.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Diffing Filter Rules</em>' attribute.
     * @see #setDiffingFilterRules(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_DiffingFilterRules()
     * @model default="" required="true"
     * @generated
     */
	String getDiffingFilterRules();

	/**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getDiffingFilterRules <em>Diffing Filter Rules</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Diffing Filter Rules</em>' attribute.
     * @see #getDiffingFilterRules()
     * @generated
     */
	void setDiffingFilterRules(String value);

	/**
     * Returns the value of the '<em><b>Differ Ids</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Differ Ids</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Differ Ids</em>' attribute list.
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_DifferIds()
     * @model required="true"
     * @generated
     */
    EList<String> getDifferIds();

    /**
     * Returns the value of the '<em><b>Differ Options</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Differ Options</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Differ Options</em>' map.
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_DifferOptions()
     * @model mapType="org.splevo.project.DifferOption<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
     * @generated
     */
    EMap<String, String> getDifferOptions();

    /**
     * Returns the value of the '<em><b>Spl Profile</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Spl Profile</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Spl Profile</em>' containment reference.
     * @see #setSplProfile(SPLProfile)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_SplProfile()
     * @model containment="true"
     * @generated
     */
    SPLProfile getSplProfile();

    /**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getSplProfile <em>Spl Profile</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Spl Profile</em>' containment reference.
     * @see #getSplProfile()
     * @generated
     */
    void setSplProfile(SPLProfile value);

    /**
     * Returns the value of the '<em><b>Fm Builder Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fm Builder Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Fm Builder Id</em>' attribute.
     * @see #setFmBuilderId(String)
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_FmBuilderId()
     * @model required="true"
     * @generated
     */
    String getFmBuilderId();

    /**
     * Sets the value of the '{@link org.splevo.project.SPLevoProject#getFmBuilderId <em>Fm Builder Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fm Builder Id</em>' attribute.
     * @see #getFmBuilderId()
     * @generated
     */
    void setFmBuilderId(String value);

    /**
     * Returns the value of the '<em><b>Vpm Model References</b></em>' containment reference list.
     * The list contents are of type {@link org.splevo.project.VPMModelReference}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Vpm Model References</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vpm Model References</em>' containment reference list.
     * @see org.splevo.project.ProjectPackage#getSPLevoProject_VpmModelReferences()
     * @model containment="true"
     * @generated
     */
    EList<VPMModelReference> getVpmModelReferences();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Adds a new VPMModelReference at the end of the current list of references.
     * <!-- end-model-doc -->
     * @model pathRequired="true" refactoringStartedRequired="true"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='VPMModelReference reference = ProjectFactory.eINSTANCE.createVPMModelReference();\r\nreference.setPath(path);\r\nreference.setRefactoringStarted(refactoringStarted);\r\ngetVpmModelReferences().add(reference);'"
     * @generated
     */
    void addVPMModelReference(String path, boolean refactoringStarted);

} // SPLevoProject

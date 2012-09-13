/**
 */
package org.splevo.project.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.splevo.project.ProjectPackage;
import org.splevo.project.SPLevoProject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SP Levo Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.project.impl.SPLevoProjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.splevo.project.impl.SPLevoProjectImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.splevo.project.impl.SPLevoProjectImpl#getSourceModelPathLeading <em>Source Model Path Leading</em>}</li>
 *   <li>{@link org.splevo.project.impl.SPLevoProjectImpl#getSourceModelPathIntegration <em>Source Model Path Integration</em>}</li>
 *   <li>{@link org.splevo.project.impl.SPLevoProjectImpl#getLeadingProjects <em>Leading Projects</em>}</li>
 *   <li>{@link org.splevo.project.impl.SPLevoProjectImpl#getIntegrationProjects <em>Integration Projects</em>}</li>
 *   <li>{@link org.splevo.project.impl.SPLevoProjectImpl#getWorkspace <em>Workspace</em>}</li>
 *   <li>{@link org.splevo.project.impl.SPLevoProjectImpl#getVariantNameLeading <em>Variant Name Leading</em>}</li>
 *   <li>{@link org.splevo.project.impl.SPLevoProjectImpl#getVariantNameIntegration <em>Variant Name Integration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SPLevoProjectImpl extends EObjectImpl implements SPLevoProject {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceModelPathLeading() <em>Source Model Path Leading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceModelPathLeading()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_MODEL_PATH_LEADING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceModelPathLeading() <em>Source Model Path Leading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceModelPathLeading()
	 * @generated
	 * @ordered
	 */
	protected String sourceModelPathLeading = SOURCE_MODEL_PATH_LEADING_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceModelPathIntegration() <em>Source Model Path Integration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceModelPathIntegration()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_MODEL_PATH_INTEGRATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceModelPathIntegration() <em>Source Model Path Integration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceModelPathIntegration()
	 * @generated
	 * @ordered
	 */
	protected String sourceModelPathIntegration = SOURCE_MODEL_PATH_INTEGRATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeadingProjects() <em>Leading Projects</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadingProjects()
	 * @generated
	 * @ordered
	 */
	protected EList<String> leadingProjects;

	/**
	 * The cached value of the '{@link #getIntegrationProjects() <em>Integration Projects</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegrationProjects()
	 * @generated
	 * @ordered
	 */
	protected EList<String> integrationProjects;

	/**
	 * The default value of the '{@link #getWorkspace() <em>Workspace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkspace()
	 * @generated
	 * @ordered
	 */
	protected static final String WORKSPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkspace() <em>Workspace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkspace()
	 * @generated
	 * @ordered
	 */
	protected String workspace = WORKSPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVariantNameLeading() <em>Variant Name Leading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariantNameLeading()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIANT_NAME_LEADING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariantNameLeading() <em>Variant Name Leading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariantNameLeading()
	 * @generated
	 * @ordered
	 */
	protected String variantNameLeading = VARIANT_NAME_LEADING_EDEFAULT;

	/**
	 * The default value of the '{@link #getVariantNameIntegration() <em>Variant Name Integration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariantNameIntegration()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIANT_NAME_INTEGRATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariantNameIntegration() <em>Variant Name Integration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariantNameIntegration()
	 * @generated
	 * @ordered
	 */
	protected String variantNameIntegration = VARIANT_NAME_INTEGRATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SPLevoProjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProjectPackage.Literals.SP_LEVO_PROJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.SP_LEVO_PROJECT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.SP_LEVO_PROJECT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceModelPathLeading() {
		return sourceModelPathLeading;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceModelPathLeading(String newSourceModelPathLeading) {
		String oldSourceModelPathLeading = sourceModelPathLeading;
		sourceModelPathLeading = newSourceModelPathLeading;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING, oldSourceModelPathLeading, sourceModelPathLeading));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceModelPathIntegration() {
		return sourceModelPathIntegration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceModelPathIntegration(String newSourceModelPathIntegration) {
		String oldSourceModelPathIntegration = sourceModelPathIntegration;
		sourceModelPathIntegration = newSourceModelPathIntegration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION, oldSourceModelPathIntegration, sourceModelPathIntegration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getLeadingProjects() {
		if (leadingProjects == null) {
			leadingProjects = new EDataTypeUniqueEList<String>(String.class, this, ProjectPackage.SP_LEVO_PROJECT__LEADING_PROJECTS);
		}
		return leadingProjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getIntegrationProjects() {
		if (integrationProjects == null) {
			integrationProjects = new EDataTypeUniqueEList<String>(String.class, this, ProjectPackage.SP_LEVO_PROJECT__INTEGRATION_PROJECTS);
		}
		return integrationProjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkspace() {
		return workspace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkspace(String newWorkspace) {
		String oldWorkspace = workspace;
		workspace = newWorkspace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.SP_LEVO_PROJECT__WORKSPACE, oldWorkspace, workspace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVariantNameLeading() {
		return variantNameLeading;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariantNameLeading(String newVariantNameLeading) {
		String oldVariantNameLeading = variantNameLeading;
		variantNameLeading = newVariantNameLeading;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_LEADING, oldVariantNameLeading, variantNameLeading));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVariantNameIntegration() {
		return variantNameIntegration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariantNameIntegration(String newVariantNameIntegration) {
		String oldVariantNameIntegration = variantNameIntegration;
		variantNameIntegration = newVariantNameIntegration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION, oldVariantNameIntegration, variantNameIntegration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProjectPackage.SP_LEVO_PROJECT__NAME:
				return getName();
			case ProjectPackage.SP_LEVO_PROJECT__DESCRIPTION:
				return getDescription();
			case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING:
				return getSourceModelPathLeading();
			case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION:
				return getSourceModelPathIntegration();
			case ProjectPackage.SP_LEVO_PROJECT__LEADING_PROJECTS:
				return getLeadingProjects();
			case ProjectPackage.SP_LEVO_PROJECT__INTEGRATION_PROJECTS:
				return getIntegrationProjects();
			case ProjectPackage.SP_LEVO_PROJECT__WORKSPACE:
				return getWorkspace();
			case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_LEADING:
				return getVariantNameLeading();
			case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION:
				return getVariantNameIntegration();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ProjectPackage.SP_LEVO_PROJECT__NAME:
				setName((String)newValue);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING:
				setSourceModelPathLeading((String)newValue);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION:
				setSourceModelPathIntegration((String)newValue);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__LEADING_PROJECTS:
				getLeadingProjects().clear();
				getLeadingProjects().addAll((Collection<? extends String>)newValue);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__INTEGRATION_PROJECTS:
				getIntegrationProjects().clear();
				getIntegrationProjects().addAll((Collection<? extends String>)newValue);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__WORKSPACE:
				setWorkspace((String)newValue);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_LEADING:
				setVariantNameLeading((String)newValue);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION:
				setVariantNameIntegration((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ProjectPackage.SP_LEVO_PROJECT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING:
				setSourceModelPathLeading(SOURCE_MODEL_PATH_LEADING_EDEFAULT);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION:
				setSourceModelPathIntegration(SOURCE_MODEL_PATH_INTEGRATION_EDEFAULT);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__LEADING_PROJECTS:
				getLeadingProjects().clear();
				return;
			case ProjectPackage.SP_LEVO_PROJECT__INTEGRATION_PROJECTS:
				getIntegrationProjects().clear();
				return;
			case ProjectPackage.SP_LEVO_PROJECT__WORKSPACE:
				setWorkspace(WORKSPACE_EDEFAULT);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_LEADING:
				setVariantNameLeading(VARIANT_NAME_LEADING_EDEFAULT);
				return;
			case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION:
				setVariantNameIntegration(VARIANT_NAME_INTEGRATION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ProjectPackage.SP_LEVO_PROJECT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProjectPackage.SP_LEVO_PROJECT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_LEADING:
				return SOURCE_MODEL_PATH_LEADING_EDEFAULT == null ? sourceModelPathLeading != null : !SOURCE_MODEL_PATH_LEADING_EDEFAULT.equals(sourceModelPathLeading);
			case ProjectPackage.SP_LEVO_PROJECT__SOURCE_MODEL_PATH_INTEGRATION:
				return SOURCE_MODEL_PATH_INTEGRATION_EDEFAULT == null ? sourceModelPathIntegration != null : !SOURCE_MODEL_PATH_INTEGRATION_EDEFAULT.equals(sourceModelPathIntegration);
			case ProjectPackage.SP_LEVO_PROJECT__LEADING_PROJECTS:
				return leadingProjects != null && !leadingProjects.isEmpty();
			case ProjectPackage.SP_LEVO_PROJECT__INTEGRATION_PROJECTS:
				return integrationProjects != null && !integrationProjects.isEmpty();
			case ProjectPackage.SP_LEVO_PROJECT__WORKSPACE:
				return WORKSPACE_EDEFAULT == null ? workspace != null : !WORKSPACE_EDEFAULT.equals(workspace);
			case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_LEADING:
				return VARIANT_NAME_LEADING_EDEFAULT == null ? variantNameLeading != null : !VARIANT_NAME_LEADING_EDEFAULT.equals(variantNameLeading);
			case ProjectPackage.SP_LEVO_PROJECT__VARIANT_NAME_INTEGRATION:
				return VARIANT_NAME_INTEGRATION_EDEFAULT == null ? variantNameIntegration != null : !VARIANT_NAME_INTEGRATION_EDEFAULT.equals(variantNameIntegration);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", sourceModelPathLeading: ");
		result.append(sourceModelPathLeading);
		result.append(", sourceModelPathIntegration: ");
		result.append(sourceModelPathIntegration);
		result.append(", leadingProjects: ");
		result.append(leadingProjects);
		result.append(", integrationProjects: ");
		result.append(integrationProjects);
		result.append(", workspace: ");
		result.append(workspace);
		result.append(", variantNameLeading: ");
		result.append(variantNameLeading);
		result.append(", variantNameIntegration: ");
		result.append(variantNameIntegration);
		result.append(')');
		return result.toString();
	}

} //SPLevoProjectImpl

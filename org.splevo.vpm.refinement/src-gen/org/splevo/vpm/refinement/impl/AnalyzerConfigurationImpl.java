/**
 */
package org.splevo.vpm.refinement.impl;

import java.util.Collection;
import java.util.Map;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.splevo.vpm.refinement.AnalyzerConfiguration;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Analyzer Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.refinement.impl.AnalyzerConfigurationImpl#getAnalyzerID <em>Analyzer ID</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.AnalyzerConfigurationImpl#getSettings <em>Settings</em>}</li>
 *   <li>{@link org.splevo.vpm.refinement.impl.AnalyzerConfigurationImpl#getRefinements <em>Refinements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnalyzerConfigurationImpl extends EObjectImpl implements AnalyzerConfiguration {
	/**
	 * The default value of the '{@link #getAnalyzerID() <em>Analyzer ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnalyzerID()
	 * @generated
	 * @ordered
	 */
	protected static final String ANALYZER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAnalyzerID() <em>Analyzer ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnalyzerID()
	 * @generated
	 * @ordered
	 */
	protected String analyzerID = ANALYZER_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSettings() <em>Settings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSettings()
	 * @generated
	 * @ordered
	 */
	protected Map<String, String> settings;

	/**
	 * The cached value of the '{@link #getRefinements() <em>Refinements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefinements()
	 * @generated
	 * @ordered
	 */
	protected EList<Refinement> refinements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnalyzerConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefinementPackage.Literals.ANALYZER_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAnalyzerID() {
		return analyzerID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnalyzerID(String newAnalyzerID) {
		String oldAnalyzerID = analyzerID;
		analyzerID = newAnalyzerID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.ANALYZER_CONFIGURATION__ANALYZER_ID, oldAnalyzerID, analyzerID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, String> getSettings() {
		return settings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSettings(Map<String, String> newSettings) {
		Map<String, String> oldSettings = settings;
		settings = newSettings;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RefinementPackage.ANALYZER_CONFIGURATION__SETTINGS, oldSettings, settings));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Refinement> getRefinements() {
		if (refinements == null) {
			refinements = new EObjectContainmentWithInverseEList<Refinement>(Refinement.class, this, RefinementPackage.ANALYZER_CONFIGURATION__REFINEMENTS, RefinementPackage.REFINEMENT__ANALYZER);
		}
		return refinements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefinementPackage.ANALYZER_CONFIGURATION__REFINEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRefinements()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RefinementPackage.ANALYZER_CONFIGURATION__REFINEMENTS:
				return ((InternalEList<?>)getRefinements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RefinementPackage.ANALYZER_CONFIGURATION__ANALYZER_ID:
				return getAnalyzerID();
			case RefinementPackage.ANALYZER_CONFIGURATION__SETTINGS:
				return getSettings();
			case RefinementPackage.ANALYZER_CONFIGURATION__REFINEMENTS:
				return getRefinements();
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
			case RefinementPackage.ANALYZER_CONFIGURATION__ANALYZER_ID:
				setAnalyzerID((String)newValue);
				return;
			case RefinementPackage.ANALYZER_CONFIGURATION__SETTINGS:
				setSettings((Map<String, String>)newValue);
				return;
			case RefinementPackage.ANALYZER_CONFIGURATION__REFINEMENTS:
				getRefinements().clear();
				getRefinements().addAll((Collection<? extends Refinement>)newValue);
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
			case RefinementPackage.ANALYZER_CONFIGURATION__ANALYZER_ID:
				setAnalyzerID(ANALYZER_ID_EDEFAULT);
				return;
			case RefinementPackage.ANALYZER_CONFIGURATION__SETTINGS:
				setSettings((Map<String, String>)null);
				return;
			case RefinementPackage.ANALYZER_CONFIGURATION__REFINEMENTS:
				getRefinements().clear();
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
			case RefinementPackage.ANALYZER_CONFIGURATION__ANALYZER_ID:
				return ANALYZER_ID_EDEFAULT == null ? analyzerID != null : !ANALYZER_ID_EDEFAULT.equals(analyzerID);
			case RefinementPackage.ANALYZER_CONFIGURATION__SETTINGS:
				return settings != null;
			case RefinementPackage.ANALYZER_CONFIGURATION__REFINEMENTS:
				return refinements != null && !refinements.isEmpty();
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
		result.append(" (analyzerID: ");
		result.append(analyzerID);
		result.append(", settings: ");
		result.append(settings);
		result.append(')');
		return result.toString();
	}

} //AnalyzerConfigurationImpl

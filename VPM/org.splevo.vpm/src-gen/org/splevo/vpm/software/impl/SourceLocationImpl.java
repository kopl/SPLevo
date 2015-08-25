/**
 */
package org.splevo.vpm.software.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.splevo.vpm.software.SoftwarePackage;
import org.splevo.vpm.software.SourceLocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source Location</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.splevo.vpm.software.impl.SourceLocationImpl#getFilePath <em>File Path</em>}</li>
 *   <li>{@link org.splevo.vpm.software.impl.SourceLocationImpl#getStartLine <em>Start Line</em>}</li>
 *   <li>{@link org.splevo.vpm.software.impl.SourceLocationImpl#getStartPosition <em>Start Position</em>}</li>
 *   <li>{@link org.splevo.vpm.software.impl.SourceLocationImpl#getEndPosition <em>End Position</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SourceLocationImpl extends MinimalEObjectImpl.Container implements
		SourceLocation {
	/**
	 * The default value of the '{@link #getFilePath() <em>File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilePath()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilePath() <em>File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilePath()
	 * @generated
	 * @ordered
	 */
	protected String filePath = FILE_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartLine() <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartLine()
	 * @generated
	 * @ordered
	 */
	protected static final int START_LINE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStartLine() <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartLine()
	 * @generated
	 * @ordered
	 */
	protected int startLine = START_LINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartPosition() <em>Start Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartPosition()
	 * @generated
	 * @ordered
	 */
	protected static final int START_POSITION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStartPosition() <em>Start Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartPosition()
	 * @generated
	 * @ordered
	 */
	protected int startPosition = START_POSITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndPosition() <em>End Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndPosition()
	 * @generated
	 * @ordered
	 */
	protected static final int END_POSITION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEndPosition() <em>End Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndPosition()
	 * @generated
	 * @ordered
	 */
	protected int endPosition = END_POSITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SourceLocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwarePackage.Literals.SOURCE_LOCATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilePath(String newFilePath) {
		String oldFilePath = filePath;
		filePath = newFilePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					SoftwarePackage.SOURCE_LOCATION__FILE_PATH, oldFilePath,
					filePath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStartLine() {
		return startLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartLine(int newStartLine) {
		int oldStartLine = startLine;
		startLine = newStartLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					SoftwarePackage.SOURCE_LOCATION__START_LINE, oldStartLine,
					startLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStartPosition() {
		return startPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartPosition(int newStartPosition) {
		int oldStartPosition = startPosition;
		startPosition = newStartPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					SoftwarePackage.SOURCE_LOCATION__START_POSITION,
					oldStartPosition, startPosition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getEndPosition() {
		return endPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndPosition(int newEndPosition) {
		int oldEndPosition = endPosition;
		endPosition = newEndPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					SoftwarePackage.SOURCE_LOCATION__END_POSITION,
					oldEndPosition, endPosition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case SoftwarePackage.SOURCE_LOCATION__FILE_PATH:
			return getFilePath();
		case SoftwarePackage.SOURCE_LOCATION__START_LINE:
			return getStartLine();
		case SoftwarePackage.SOURCE_LOCATION__START_POSITION:
			return getStartPosition();
		case SoftwarePackage.SOURCE_LOCATION__END_POSITION:
			return getEndPosition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case SoftwarePackage.SOURCE_LOCATION__FILE_PATH:
			setFilePath((String) newValue);
			return;
		case SoftwarePackage.SOURCE_LOCATION__START_LINE:
			setStartLine((Integer) newValue);
			return;
		case SoftwarePackage.SOURCE_LOCATION__START_POSITION:
			setStartPosition((Integer) newValue);
			return;
		case SoftwarePackage.SOURCE_LOCATION__END_POSITION:
			setEndPosition((Integer) newValue);
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
		case SoftwarePackage.SOURCE_LOCATION__FILE_PATH:
			setFilePath(FILE_PATH_EDEFAULT);
			return;
		case SoftwarePackage.SOURCE_LOCATION__START_LINE:
			setStartLine(START_LINE_EDEFAULT);
			return;
		case SoftwarePackage.SOURCE_LOCATION__START_POSITION:
			setStartPosition(START_POSITION_EDEFAULT);
			return;
		case SoftwarePackage.SOURCE_LOCATION__END_POSITION:
			setEndPosition(END_POSITION_EDEFAULT);
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
		case SoftwarePackage.SOURCE_LOCATION__FILE_PATH:
			return FILE_PATH_EDEFAULT == null ? filePath != null
					: !FILE_PATH_EDEFAULT.equals(filePath);
		case SoftwarePackage.SOURCE_LOCATION__START_LINE:
			return startLine != START_LINE_EDEFAULT;
		case SoftwarePackage.SOURCE_LOCATION__START_POSITION:
			return startPosition != START_POSITION_EDEFAULT;
		case SoftwarePackage.SOURCE_LOCATION__END_POSITION:
			return endPosition != END_POSITION_EDEFAULT;
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (filePath: ");
		result.append(filePath);
		result.append(", startLine: ");
		result.append(startLine);
		result.append(", startPosition: ");
		result.append(startPosition);
		result.append(", endPosition: ");
		result.append(endPosition);
		result.append(')');
		return result.toString();
	}

} //SourceLocationImpl

package org.splevo.vpm.software.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwarePackage;
import org.splevo.vpm.software.SourceLocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Software Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class JavaSoftwareElementImpl extends MinimalEObjectImpl.Container implements JavaSoftwareElement {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected JavaSoftwareElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SoftwarePackage.Literals.JAVA_SOFTWARE_ELEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated not
     */
    @Override
    public abstract String getLabel();

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated not
     */
    @Override
    public abstract String getName();

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated not
     */
    public abstract SourceLocation getSourceLocation();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject getWrappedElement() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

} //JavaSoftwareElementImpl

package org.splevo.vpm.refinement.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementModel;
import org.splevo.vpm.refinement.RefinementPackage;
import org.splevo.vpm.refinement.RefinementReason;
import org.splevo.vpm.refinement.RefinementType;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class RefinementFactoryImpl extends EFactoryImpl implements RefinementFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static RefinementFactory init() {
        try {
            RefinementFactory theRefinementFactory = (RefinementFactory) EPackage.Registry.INSTANCE
                    .getEFactory(RefinementPackage.eNS_URI);
            if (theRefinementFactory != null) {
                return theRefinementFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new RefinementFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RefinementFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case RefinementPackage.REFINEMENT_MODEL:
            return createRefinementModel();
        case RefinementPackage.REFINEMENT:
            return createRefinement();
        case RefinementPackage.REFINEMENT_REASON:
            return createRefinementReason();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case RefinementPackage.REFINEMENT_TYPE:
            return createRefinementTypeFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case RefinementPackage.REFINEMENT_TYPE:
            return convertRefinementTypeToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Refinement createRefinement() {
        RefinementImpl refinement = new RefinementImpl();
        return refinement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RefinementReason createRefinementReason() {
        RefinementReasonImpl refinementReason = new RefinementReasonImpl();
        return refinementReason;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RefinementModel createRefinementModel() {
        RefinementModelImpl refinementModel = new RefinementModelImpl();
        return refinementModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RefinementType createRefinementTypeFromString(EDataType eDataType, String initialValue) {
        RefinementType result = RefinementType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                    + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertRefinementTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RefinementPackage getRefinementPackage() {
        return (RefinementPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static RefinementPackage getPackage() {
        return RefinementPackage.eINSTANCE;
    }

} // RefinementFactoryImpl

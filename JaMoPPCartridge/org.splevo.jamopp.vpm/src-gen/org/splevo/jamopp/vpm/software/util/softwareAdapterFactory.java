/**
 */
package org.splevo.jamopp.vpm.software.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.splevo.jamopp.vpm.software.*;

import org.splevo.vpm.software.JavaSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.splevo.jamopp.vpm.software.softwarePackage
 * @generated
 */
public class softwareAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static softwarePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public softwareAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = softwarePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected softwareSwitch<Adapter> modelSwitch = new softwareSwitch<Adapter>() {
		@Override
		public Adapter caseJaMoPPSoftwareElement(JaMoPPSoftwareElement object) {
			return createJaMoPPSoftwareElementAdapter();
		}

		@Override
		public Adapter caseSoftwareElement(SoftwareElement object) {
			return createSoftwareElementAdapter();
		}

		@Override
		public Adapter caseJavaSoftwareElement(JavaSoftwareElement object) {
			return createJavaSoftwareElementAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement
	 * <em>Ja Mo PP Software Element</em>}'. <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement
	 * @generated
	 */
	public Adapter createJaMoPPSoftwareElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.splevo.vpm.software.SoftwareElement <em>Element</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.splevo.vpm.software.SoftwareElement
	 * @generated
	 */
	public Adapter createSoftwareElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.splevo.vpm.software.JavaSoftwareElement
	 * <em>Java Software Element</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.splevo.vpm.software.JavaSoftwareElement
	 * @generated
	 */
	public Adapter createJavaSoftwareElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // softwareAdapterFactory
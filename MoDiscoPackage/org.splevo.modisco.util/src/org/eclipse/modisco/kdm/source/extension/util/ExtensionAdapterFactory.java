/*******************************************************************************
 * Copyright (c) 2011 Mia-Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Fabien Giquel (Mia-Software) - initial API and implementation
 *    Nicolas Bros (Mia-Software) - Bug 335003 - [Discoverer] : Existing Discoverers Refactoring based on new framework
 *******************************************************************************/
package org.eclipse.modisco.kdm.source.extension.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.omg.kdm.core.Element;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceRegion;
import org.eclipse.modisco.kdm.source.extension.ASTNodeSourceRegion;
import org.eclipse.modisco.kdm.source.extension.CodeUnit2File;
import org.eclipse.modisco.kdm.source.extension.ExtensionPackage;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * @see org.eclipse.modisco.kdm.source.extension.ExtensionPackage
 * @generated
 */
@SuppressWarnings("all")
public class ExtensionAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static ExtensionPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ExtensionAdapterFactory() {
		if (ExtensionAdapterFactory.modelPackage == null) {
			ExtensionAdapterFactory.modelPackage = ExtensionPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(final Object object) {
		if (object == ExtensionAdapterFactory.modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == ExtensionAdapterFactory.modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ExtensionSwitch<Adapter> modelSwitch = new ExtensionSwitch<Adapter>() {
			@Override
			public Adapter caseASTNodeSourceRegion(final ASTNodeSourceRegion object) {
				return createASTNodeSourceRegionAdapter();
			}
			@Override
			public Adapter caseCodeUnit2File(final CodeUnit2File object) {
				return createCodeUnit2FileAdapter();
			}
			@Override
			public Adapter caseElement(final Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseSourceRegion(final SourceRegion object) {
				return createSourceRegionAdapter();
			}
			@Override
			public Adapter defaultCase(final EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(final Notifier target) {
		return this.modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.modisco.kdm.source.extension.ASTNodeSourceRegion <em>AST Node Source Region</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.modisco.kdm.source.extension.ASTNodeSourceRegion
	 * @generated
	 */
	public Adapter createASTNodeSourceRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.modisco.kdm.source.extension.CodeUnit2File <em>Code Unit2 File</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.modisco.kdm.source.extension.CodeUnit2File
	 * @generated
	 */
	public Adapter createCodeUnit2FileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.omg.kdm.core.Element <em>Element</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.omg.kdm.core.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.gmt.modisco.omg.kdm.source.SourceRegion
	 * <em>Region</em>}'. <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a
	 * case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.omg.kdm.source.SourceRegion
	 * @generated
	 */
	public Adapter createSourceRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // ExtensionAdapterFactory

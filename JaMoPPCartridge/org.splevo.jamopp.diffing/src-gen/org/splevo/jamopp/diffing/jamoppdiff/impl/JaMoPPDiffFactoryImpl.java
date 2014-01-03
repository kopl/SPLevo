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
package org.splevo.jamopp.diffing.jamoppdiff.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.splevo.jamopp.diffing.jamoppdiff.ClassChange;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.diffing.jamoppdiff.EnumChange;
import org.splevo.jamopp.diffing.jamoppdiff.FieldChange;
import org.splevo.jamopp.diffing.jamoppdiff.ImportChange;
import org.splevo.jamopp.diffing.jamoppdiff.InterfaceChange;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffFactory;
import org.splevo.jamopp.diffing.jamoppdiff.JaMoPPDiffPackage;
import org.splevo.jamopp.diffing.jamoppdiff.MethodChange;
import org.splevo.jamopp.diffing.jamoppdiff.PackageChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JaMoPPDiffFactoryImpl extends EFactoryImpl implements
		JaMoPPDiffFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JaMoPPDiffFactory init() {
		try {
			JaMoPPDiffFactory theJaMoPPDiffFactory = (JaMoPPDiffFactory) EPackage.Registry.INSTANCE
					.getEFactory(JaMoPPDiffPackage.eNS_URI);
			if (theJaMoPPDiffFactory != null) {
				return theJaMoPPDiffFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new JaMoPPDiffFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JaMoPPDiffFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case JaMoPPDiffPackage.STATEMENT_CHANGE:
			return createStatementChange();
		case JaMoPPDiffPackage.IMPORT_CHANGE:
			return createImportChange();
		case JaMoPPDiffPackage.CLASS_CHANGE:
			return createClassChange();
		case JaMoPPDiffPackage.FIELD_CHANGE:
			return createFieldChange();
		case JaMoPPDiffPackage.PACKAGE_CHANGE:
			return createPackageChange();
		case JaMoPPDiffPackage.METHOD_CHANGE:
			return createMethodChange();
		case JaMoPPDiffPackage.ENUM_CHANGE:
			return createEnumChange();
		case JaMoPPDiffPackage.COMPILATION_UNIT_CHANGE:
			return createCompilationUnitChange();
		case JaMoPPDiffPackage.INTERFACE_CHANGE:
			return createInterfaceChange();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName()
					+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementChange createStatementChange() {
		StatementChangeImpl statementChange = new StatementChangeImpl();
		return statementChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportChange createImportChange() {
		ImportChangeImpl importChange = new ImportChangeImpl();
		return importChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassChange createClassChange() {
		ClassChangeImpl classChange = new ClassChangeImpl();
		return classChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldChange createFieldChange() {
		FieldChangeImpl fieldChange = new FieldChangeImpl();
		return fieldChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageChange createPackageChange() {
		PackageChangeImpl packageChange = new PackageChangeImpl();
		return packageChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodChange createMethodChange() {
		MethodChangeImpl methodChange = new MethodChangeImpl();
		return methodChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumChange createEnumChange() {
		EnumChangeImpl enumChange = new EnumChangeImpl();
		return enumChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnitChange createCompilationUnitChange() {
		CompilationUnitChangeImpl compilationUnitChange = new CompilationUnitChangeImpl();
		return compilationUnitChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InterfaceChange createInterfaceChange() {
		InterfaceChangeImpl interfaceChange = new InterfaceChangeImpl();
		return interfaceChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JaMoPPDiffPackage getJaMoPPDiffPackage() {
		return (JaMoPPDiffPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static JaMoPPDiffPackage getPackage() {
		return JaMoPPDiffPackage.eINSTANCE;
	}

} //JaMoPPDiffFactoryImpl

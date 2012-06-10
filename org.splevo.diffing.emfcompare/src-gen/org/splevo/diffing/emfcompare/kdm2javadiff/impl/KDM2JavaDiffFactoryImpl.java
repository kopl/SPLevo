/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.splevo.diffing.emfcompare.kdm2javadiff.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.splevo.diffing.emfcompare.kdm2javadiff.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KDM2JavaDiffFactoryImpl extends EFactoryImpl implements KDM2JavaDiffFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static KDM2JavaDiffFactory init() {
		try {
			KDM2JavaDiffFactory theKDM2JavaDiffFactory = (KDM2JavaDiffFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/compare/diff/kdm2java/1.0"); 
			if (theKDM2JavaDiffFactory != null) {
				return theKDM2JavaDiffFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KDM2JavaDiffFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KDM2JavaDiffFactoryImpl() {
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
			case KDM2JavaDiffPackage.STATEMENT_ORDER_CHANGE: return createStatementOrderChange();
			case KDM2JavaDiffPackage.STATEMENT_INSERT: return createStatementInsert();
			case KDM2JavaDiffPackage.STATEMENT_DELETE: return createStatementDelete();
			case KDM2JavaDiffPackage.STATEMENT_MOVE: return createStatementMove();
			case KDM2JavaDiffPackage.CLASS_INSERT: return createClassInsert();
			case KDM2JavaDiffPackage.CLASS_DELETE: return createClassDelete();
			case KDM2JavaDiffPackage.CLASS_MODIFIER_CHANGE: return createClassModifierChange();
			case KDM2JavaDiffPackage.IMPORT_INSERT: return createImportInsert();
			case KDM2JavaDiffPackage.IMPORT_DELETE: return createImportDelete();
			case KDM2JavaDiffPackage.CLASS_CHANGE: return createClassChange();
			case KDM2JavaDiffPackage.METHOD_CHANGE: return createMethodChange();
			case KDM2JavaDiffPackage.METHOD_MODIFIER_CHANGE: return createMethodModifierChange();
			case KDM2JavaDiffPackage.RETURN_TYPE_CHANGE: return createReturnTypeChange();
			case KDM2JavaDiffPackage.METHOD_PARAMETER_CHANGE: return createMethodParameterChange();
			case KDM2JavaDiffPackage.METHOD_INSERT: return createMethodInsert();
			case KDM2JavaDiffPackage.METHOD_DELETE: return createMethodDelete();
			case KDM2JavaDiffPackage.COMPILATION_UNIT_CHANGE: return createCompilationUnitChange();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementOrderChange createStatementOrderChange() {
		StatementOrderChangeImpl statementOrderChange = new StatementOrderChangeImpl();
		return statementOrderChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementInsert createStatementInsert() {
		StatementInsertImpl statementInsert = new StatementInsertImpl();
		return statementInsert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementDelete createStatementDelete() {
		StatementDeleteImpl statementDelete = new StatementDeleteImpl();
		return statementDelete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementMove createStatementMove() {
		StatementMoveImpl statementMove = new StatementMoveImpl();
		return statementMove;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassInsert createClassInsert() {
		ClassInsertImpl classInsert = new ClassInsertImpl();
		return classInsert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassDelete createClassDelete() {
		ClassDeleteImpl classDelete = new ClassDeleteImpl();
		return classDelete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassModifierChange createClassModifierChange() {
		ClassModifierChangeImpl classModifierChange = new ClassModifierChangeImpl();
		return classModifierChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportInsert createImportInsert() {
		ImportInsertImpl importInsert = new ImportInsertImpl();
		return importInsert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDelete createImportDelete() {
		ImportDeleteImpl importDelete = new ImportDeleteImpl();
		return importDelete;
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
	public MethodChange createMethodChange() {
		MethodChangeImpl methodChange = new MethodChangeImpl();
		return methodChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodModifierChange createMethodModifierChange() {
		MethodModifierChangeImpl methodModifierChange = new MethodModifierChangeImpl();
		return methodModifierChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReturnTypeChange createReturnTypeChange() {
		ReturnTypeChangeImpl returnTypeChange = new ReturnTypeChangeImpl();
		return returnTypeChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodParameterChange createMethodParameterChange() {
		MethodParameterChangeImpl methodParameterChange = new MethodParameterChangeImpl();
		return methodParameterChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodInsert createMethodInsert() {
		MethodInsertImpl methodInsert = new MethodInsertImpl();
		return methodInsert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDelete createMethodDelete() {
		MethodDeleteImpl methodDelete = new MethodDeleteImpl();
		return methodDelete;
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
	public KDM2JavaDiffPackage getKDM2JavaDiffPackage() {
		return (KDM2JavaDiffPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static KDM2JavaDiffPackage getPackage() {
		return KDM2JavaDiffPackage.eINSTANCE;
	}

} //KDM2JavaDiffFactoryImpl

package org.splevo.diffing.emfcompare.diff.transform;

import org.eclipse.emf.compare.diff.metamodel.ConflictingDiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChange;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.MoveModelElement;
import org.eclipse.emf.compare.diff.metamodel.ReferenceChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.util.DiffSwitch;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.ClassChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.CompilationUnitChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.ImportInsert;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.MethodChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange;
import org.splevo.diffing.emfcompare.kdm2javadiff.StatementMove;

/**
 * Class for transforming diff elements into more specific instances of the Kdm2Java meta-model.
 * Based on the type of difference detected by EMF Compare and the meta-class of the corresponding model element in the compared models,
 * refactoring patterns (e.g., from the Fluri paper) are generated. 
 */
public class DiffNodeTransformer extends DiffSwitch<DiffElement>{
	//currently these references are never reset, so it is assumed, that a package is traversed before a class etc
	private PackageChange lastPackageChange;
	private ClassChange lastClassChange;
	private MethodChange lastMethodChange;

	private CompilationUnitChange lastCompilationUnit;
	
	//temporary dummy elements to conform to model hierarchy
	private PackageChange dummyPackageChange = KDM2JavaDiffFactory.eINSTANCE.createPackageChange();
	private CompilationUnitChange dummyCompUnitChange = KDM2JavaDiffFactory.eINSTANCE.createCompilationUnitChange();
	
	{
		dummyPackageChange.setPackageLeft(JavaFactory.eINSTANCE.createPackage());
		dummyPackageChange.setPackageRight(JavaFactory.eINSTANCE.createPackage());
		dummyCompUnitChange.setPackageChange(dummyPackageChange);
		
	}
	
	@Override
	public DiffElement caseModelElementChange(ModelElementChange object) {
		// TODO Auto-generated method stub
		return super.caseModelElementChange(object);
	}

	@Override
	public DiffElement caseModelElementChangeLeftTarget(
			ModelElementChangeLeftTarget object) {
		// TODO Auto-generated method stub
		return super.caseModelElementChangeLeftTarget(object);
	}



	@Override
	public DiffElement caseReferenceChangeRightTarget(
			ReferenceChangeRightTarget object) {
		// TODO Auto-generated method stub
		return super.caseReferenceChangeRightTarget(object);
	}

	@Override
	public DiffElement caseDiffGroup(DiffGroup object) {
		
		if (object.getRightParent() instanceof org.eclipse.gmt.modisco.java.Package) {
			org.eclipse.gmt.modisco.java.Package origPackage = (org.eclipse.gmt.modisco.java.Package) object.getRightParent();
			
			PackageChange newContainer = KDM2JavaDiffFactory.eINSTANCE.createPackageChange();
			newContainer.setRightParent(object.getRightParent());
			newContainer.setPackageLeft(origPackage);
			newContainer.setPackageRight(null);
			
			
			lastPackageChange = newContainer;
			
			return newContainer;
		}
		if (object.getRightParent() instanceof ClassDeclaration) {
			
			ClassChange newContainer = KDM2JavaDiffFactory.eINSTANCE.createClassChange();
			newContainer.setRightParent(object.getRightParent());
			newContainer.setCompilationUnitChange(dummyCompUnitChange);
			newContainer.setRemote(object.isRemote());
			
			lastClassChange = newContainer;
			
			return newContainer;
		}
		if (object.getRightParent() instanceof MethodDeclaration) {
			MethodChange newContainer = KDM2JavaDiffFactory.eINSTANCE.createMethodChange();
			newContainer.setRightParent(object.getRightParent());
			newContainer.setClassChange(lastClassChange);
			
			lastMethodChange = newContainer;
			
			return newContainer;
		}
		if (object.getRightParent() instanceof CompilationUnit) {
			CompilationUnitChange newContainer = KDM2JavaDiffFactory.eINSTANCE.createCompilationUnitChange();
			newContainer.setRightParent(object.getRightParent());
			
			lastCompilationUnit = newContainer;
			
			return newContainer;
		}
		
		//just return same element without children
		DiffGroup newElem = DiffFactory.eINSTANCE.createDiffGroup();
		newElem.setRightParent(object.getRightParent());
		newElem.getSubDiffElements().clear();
		
		return newElem;
		//return null;
	}

	@Override
	public DiffElement caseDiffElement(DiffElement object) {
		return super.caseDiffElement(object);
	}

	@Override
	public DiffElement caseConflictingDiffElement(ConflictingDiffElement object) {
		return super.caseConflictingDiffElement(object);
	}

	@Override
	public DiffElement caseMoveModelElement(MoveModelElement object) {
		if (object.getLeftElement() instanceof ImportDeclaration) {
			ImportInsert importInsert = KDM2JavaDiffFactory.eINSTANCE.createImportInsert();
			importInsert.setImportLeft((ImportDeclaration) object.getLeftElement());
			importInsert.setImportRight(null);
			importInsert.setCompilationUnitChange(lastCompilationUnit);
			return importInsert;
		}
		if (object.getLeftElement() instanceof Statement && object.getRightElement() instanceof Statement) {
			Statement leftStatement = (Statement) object.getLeftElement();
			Statement rightStatement = (Statement) object.getRightElement();
			
			if (!isCorrespondingStatement(leftStatement, rightStatement)) {
				StatementMove newDiffElement = KDM2JavaDiffFactory.eINSTANCE.createStatementMove();
				newDiffElement.setStatementLeft(leftStatement);
				newDiffElement.setStatementRight(rightStatement);
				newDiffElement.setParentOld(leftStatement.getOriginalCompilationUnit());
				newDiffElement.setParentNew(rightStatement.getOriginalCompilationUnit());

				newDiffElement.setMethodChange(lastMethodChange);
				return newDiffElement;
			}
		}
		return super.caseMoveModelElement(object);
	}

	//temporary helper method since no value-based equals method is implemented in Modisco model
	private static boolean isCorrespondingStatement(Statement s1, Statement s2) {
		
		if (s1 instanceof VariableDeclarationStatement && s2 instanceof VariableDeclarationStatement) {
			VariableDeclarationStatement vds1 = (VariableDeclarationStatement) s1;
			VariableDeclarationStatement vds2 = (VariableDeclarationStatement) s2;
			
			Type type1 = vds1.getType().getType();
			Type type2 = vds2.getType().getType();
			if (type1 instanceof PrimitiveType && type2 instanceof PrimitiveType) {
				//simple name comparison for primitive type
				System.out.println("primitive types " + type1 + ", " + type2);
				if (!type1.getName().equals(type2.getName())) {
					return false;
				}
			} else if (type1 instanceof ClassDeclaration && type2 instanceof ClassDeclaration) {
				ClassDeclaration clDec1 = (ClassDeclaration) type1;
				ClassDeclaration clDec2 = (ClassDeclaration) type2;
				System.out.println("  class decs; " + clDec1 + ", " + clDec2);
				if (!clDec1.getPackage().getName().equals(clDec2.getPackage().getName())
						|| !clDec1.getName().equals(clDec2.getName())) {
					return false;
				}
			} else {
				System.out.println("weird type " + type1 + ", " + type2);
				return false;
			}
			if (!vds1.getModifier().getVisibility().equals(vds2.getModifier().getVisibility())) {
				return false;
			}
			if (vds1.getFragments().size() != vds2.getFragments().size()) {
				return false;
			}
			for (int i = 0; i < vds1.getFragments().size(); i++) {
				VariableDeclarationFragment frag1 = vds1.getFragments().get(i);
				VariableDeclarationFragment frag2 = vds2.getFragments().get(i);
				if (!frag1.getName().equals(frag2.getName())) {
					return false;
				}
			}
		}
		
		return true;
	}
}

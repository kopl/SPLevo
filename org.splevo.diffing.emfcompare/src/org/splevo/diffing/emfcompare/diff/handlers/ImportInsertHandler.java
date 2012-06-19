package org.splevo.diffing.emfcompare.diff.handlers;

import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffExtension;

public class ImportInsertHandler implements Kdm2JavaDiffHandler {

	@Override
	public boolean isValidSubtreeHandler(DiffElement diffElement) {
		if (diffElement instanceof ModelElementChangeLeftTarget) {
			ModelElementChangeLeftTarget leftTargetChange = (ModelElementChangeLeftTarget) diffElement;
//			System.out.println("found left target change " + leftTargetChange + " with left element " + leftTargetChange.getLeftElement() + " and meta class " + leftTargetChange.getLeftElement().eClass());
			return leftTargetChange.getLeftElement().eClass().equals(JavaPackage.eINSTANCE.getImportDeclaration());
		}
		return false;
	}

	@Override
	public KDM2JavaDiffExtension handleSubtree(DiffElement diffElement) {
		// TODO Auto-generated method stub
		return null;
	}

}

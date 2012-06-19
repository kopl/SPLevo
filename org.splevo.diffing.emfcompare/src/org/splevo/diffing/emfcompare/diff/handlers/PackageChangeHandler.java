package org.splevo.diffing.emfcompare.diff.handlers;

import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.splevo.diffing.emfcompare.kdm2javadiff.KDM2JavaDiffFactory;
import org.splevo.diffing.emfcompare.kdm2javadiff.PackageChange;

public class PackageChangeHandler implements Kdm2JavaDiffHandler {

	@Override
	public boolean isValidSubtreeHandler(DiffElement diffElement) {
		if (diffElement instanceof ModelElementChangeLeftTarget) {
			ModelElementChangeLeftTarget leftTargetChange = (ModelElementChangeLeftTarget) diffElement;
//			System.out.println("found left target change " + leftTargetChange + " with left element " + leftTargetChange.getLeftElement() + " and meta class " + leftTargetChange.getLeftElement().eClass());
//			System.out.println("right parent " + leftTargetChange.getRightParent());
			return leftTargetChange.getLeftElement().eClass().equals(JavaPackage.eINSTANCE.getPackage());
		}
		return false;
	}

	@Override
	public DiffElement handleSubtree(DiffElement diffElement) {
		ModelElementChangeLeftTarget leftTargetChange = (ModelElementChangeLeftTarget) diffElement;
		org.eclipse.gmt.modisco.java.Package pack = (Package) leftTargetChange.getLeftElement();
		PackageChange pChange = KDM2JavaDiffFactory.eINSTANCE.createPackageChange();
		pChange.setPackageLeft(pack);
		pChange.setPackageRight(pack); //just to make sure it's not null
		pChange.setRightParent(leftTargetChange.getRightParent());
		pChange.setRemote(diffElement.isRemote());
		//TODO: pChange is missing a compilation unit (and more) -> stack overflow
		return diffElement;
	}

}

package org.splevo.diffing.emfcompare.diff;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.util.JavaModelUtil;

/**
 * A visitor to check a java model element if it is located in a package that
 * should be ignored.
 * 
 * The visitor is based on a EMF generated switch of the java model to improve
 * the performance of the EObject analysis.
 * 
 */
public class PackageIgnoreVisitor extends JavaSwitch<Boolean> {

	/** The packages to be ignored. */
	private List<String> ignorePackages = new ArrayList<String>();

	/**
	 * Constructor requires to set the list of packages to be ignored.
	 * 
	 * @param ignorePackages
	 *            The list of packages to be ignored. Regular expressions are excepted.
	 */
	public PackageIgnoreVisitor(List<String> ignorePackages) {
		this.ignorePackages.addAll(ignorePackages);
	}

	/**
	 * Check a class declaration whether it is located in one of the packages to
	 * ignore.
	 * 
	 * @param object
	 *            The class declaration to check.
	 */
	@Override
	public Boolean caseClassDeclaration(ClassDeclaration object) {

		// get the full package path
		StringBuilder packagePathBuilder = new StringBuilder();
		JavaModelUtil.buildPackagePath(object.getPackage(), packagePathBuilder);
		String packagePath = packagePathBuilder.toString();
		
		for (String regex : ignorePackages) {
			if(packagePath.matches(regex)){
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}
	
	/**
	 * The default case is to not ignore the supplied element.
	 * @param object The object to check.
	 * @return Whether to ignore it (always false).
	 */
	@Override
	public Boolean defaultCase(EObject object) {
		return Boolean.FALSE;
	}

}

package org.splevo.modisco.util;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.junit.Test;

/**
 * Test Case for the source connector.
 * 
 * @author benjamin
 * 
 */
public class SourceConnectorTest {

	/** Source path to the native calculator implementation. */
	private static final File KDM_MODEL_FILE = new File(
			"../org.splevo.modisco.util.tests/testmodels/sourcemodels/Native/_java2kdm.xmi");

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(SourceConnector.class);

	/**
	 * Test the source region look up.
	 * @throws IOException Failed to read the input model.
	 */
	@Test
	public void testFindSourceRegion() throws IOException {
		JavaApplication javaAppModel = KDMUtil.loadKDMModel(KDM_MODEL_FILE);

		ASTNode targetASTNode = pickASTNoteFromModel(javaAppModel);
		assertNotNull("Required ASTNode not in the java app model",
				targetASTNode);

		SourceConnector sourceConnector = new SourceConnector(javaAppModel);
		JavaNodeSourceRegion sourceRegion = sourceConnector
				.findSourceRegion(targetASTNode);
		assertNotNull("SourceRegion not found", sourceRegion);
	}

	/**
	 * Pick up the ast node to look for from a java application model. This
	 * method looks up an ASTNote representing a method from the supplied java
	 * app model at the path: org.somox.examples.calculator.Calculator.gcd()
	 * 
	 * @param javaAppModel
	 *            The JavaApplication element to search in.
	 * @return The identified ASTNote or null if it is not contained in the
	 *         supplied model.
	 */
	private ASTNode pickASTNoteFromModel(JavaApplication javaAppModel) {
		EList<org.eclipse.gmt.modisco.java.Package> rootPackages = javaAppModel
				.getJavaModel().getOwnedElements();
		org.eclipse.gmt.modisco.java.Package currentPackage = null;
		currentPackage = getPackageForName(rootPackages, "org");
		currentPackage = getPackageForName(currentPackage.getOwnedPackages(),
				"splevo");
		currentPackage = getPackageForName(currentPackage.getOwnedPackages(),
				"examples");
		currentPackage = getPackageForName(currentPackage.getOwnedPackages(),
				"calculator");
		for (AbstractTypeDeclaration atd : currentPackage.getOwnedElements()) {
			if (atd instanceof ClassDeclaration) {
				ClassDeclaration classDecl = (ClassDeclaration) atd;
				if (classDecl.getName().equals("Calculator")) {
					for (BodyDeclaration bodyDecl : classDecl
							.getBodyDeclarations()) {
						if (bodyDecl.getName().equals("gcd")) {
							return bodyDecl;
						}
					}
				}
			}
		}
		logger.error("No ASTNode accessible at intended position");
		return null;
	}

	/**
	 * Get a package with a specific name from a list of packages.
	 * 
	 * @param packageList
	 *            The list of packages to search in.
	 * @param packageName
	 *            The name of the package to find.
	 * @return The found package or null if no matching one detected.
	 */
	private Package getPackageForName(EList<Package> packageList,
			String packageName) {
		for (Package currentPackage : packageList) {
			if (currentPackage.getName().equals(packageName)) {
				return currentPackage;
			}
		}
		logger.error("No package found for name " + packageName + " in "
				+ packageList);
		return null;
	}

}

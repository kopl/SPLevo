package org.splevo.jamopp.diffing.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.junit.Test;

/**
 * Test the utility functions.
 */
public class JaMoPPModelUtilTest {

	@Test
	public void testBuildPackagePath() {
		CompilationUnit cu = ContainersFactory.eINSTANCE.createCompilationUnit();
		cu.getNamespaces().add("java");
		cu.getNamespaces().add("lang");
		
		org.emftext.language.java.classifiers.Class bigIntClass = ClassifiersFactory.eINSTANCE.createClass();
		bigIntClass.setName("BigInteger");
		
		cu.getClassifiers().add(bigIntClass);
		
		
		String packagePath = JaMoPPModelUtil.buildNamespacePath(bigIntClass);
		
		assertThat("Package should match", packagePath, is("java.lang"));
		
	}

}

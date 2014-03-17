package org.splevo.refactoring.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.refactoring.DefaultRefactoringService;
import org.splevo.refactoring.RefactoringRegistry;

public class DefaultRefactoringServiceTest {
	
	DefaultRefactoringService testedObject = null;
	
	@BeforeClass
	public static void beforeClass(){
		RefactoringRegistry.registerRefactorings();
	}

	@AfterClass
	public static void afterClass(){
		RefactoringRegistry.unregisterRefactorings();
	}
	
	@Before
	public void before(){
		testedObject = new DefaultRefactoringService();
	}

	@After
	public void after(){
		testedObject = null;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testForNullParameter() {
		testedObject.buildSoftwareProductLine(null);
	}
}

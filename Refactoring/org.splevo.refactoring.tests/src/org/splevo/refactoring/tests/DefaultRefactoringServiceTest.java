package org.splevo.refactoring.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.splevo.refactoring.DefaultSoftwareProductLineBuildingService;
import org.splevo.refactoring.RefactoringRegistry;

public class DefaultRefactoringServiceTest {
	
	DefaultSoftwareProductLineBuildingService testedObject = null;
	
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
		testedObject = new DefaultSoftwareProductLineBuildingService();
	}

	@After
	public void after(){
		testedObject = null;
	}
}

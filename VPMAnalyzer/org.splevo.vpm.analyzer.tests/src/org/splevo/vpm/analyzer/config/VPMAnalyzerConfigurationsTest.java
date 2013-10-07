package org.splevo.vpm.analyzer.config;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the {@link VPMAnalyzerConfigurationSet} class.
 * 
 * @author Daniel Kojic
 * 
 */
public class VPMAnalyzerConfigurationsTest {

	/**
	 * The object under test.
	 */
	private VPMAnalyzerConfigurationSet testedObject;

	/**
	 * Re-initializes the test-object before every test.
	 */
	@Before
	public void beforeTest() {
		this.testedObject = new VPMAnalyzerConfigurationSet();
	}

	/**
	 * Tests whether the map-size is 0 after initialization.
	 */
	@Test
	public final void testVPMAnalyzerConfigurations() {
		int size = testedObject.getAllConfigurationsByGroupName().size();
		assertSame("Map size should be 0 after initialization.", 0, size);
	}

	/**
	 * Tests whether the map has the correct amount of elements.
	 */
	@Test
	public final void testAddConfigurationsAndGetAllConfigurationsByGroupName1() {
		testedObject.addConfigurations("Test", new BooleanConfiguration(
				"LABEL", "EXPLANATION", true), new BooleanConfiguration(
				"LABEL", "EXPLANATION", true), new BooleanConfiguration(
				"LABEL", "EXPLANATION", true));

		int size = testedObject.getAllConfigurationsByGroupName().size();
		assertSame("Map size should be 1.", 1, size);
		int numElements = testedObject.getAllConfigurationsByGroupName()
				.get("Test").size();
		assertSame("This key should have 3 elements.", 3, numElements);
	}

	/**
	 * Tests whether the map has the correct amount of elements.
	 */
	@Test
	public final void testAddConfigurationsAndGetAllConfigurationsByGroupName2() {
		testedObject.addConfigurations("Test1", new BooleanConfiguration(
				"LABEL", "EXPLANATION", true), new BooleanConfiguration(
				"LABEL", "EXPLANATION", true), new BooleanConfiguration(
				"LABEL", "EXPLANATION", true));
		testedObject.addConfigurations("Test2", new BooleanConfiguration(
				"LABEL", "EXPLANATION", true), new BooleanConfiguration(
				"LABEL", "EXPLANATION", true), new BooleanConfiguration(
				"LABEL", "EXPLANATION", true));

		int size = testedObject.getAllConfigurationsByGroupName().size();
		assertSame("Map size should be 1.", 2, size);
		int numElements1 = testedObject.getAllConfigurationsByGroupName()
				.get("Test1").size();
		int numElements2 = testedObject.getAllConfigurationsByGroupName()
				.get("Test2").size();
		assertSame("This key should have 3 elements.", 3, numElements1);
		assertSame("This key should have 3 elements.", 3, numElements2);
	}

}

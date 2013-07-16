package org.splevo.vpm.analyzer.semantic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit-Tests for the {@link VPLinkContainer} class.
 * 
 * @author Daniel Kojic
 *
 */
public class VPLinkContainerTest extends AbstractTest {
	
	/**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.VPLinkContainer.VPLinkContainerTest#addLink(String, String, String)}.
     */
	@Test
	public void testAddLink() {
		// Initialization
		VPLinkContainer testObject = new VPLinkContainer();
		
		// Execution
	    for (int i = 0; i < 100; i += 2) {
	    	testObject.addLink("id" + i, "id" + new Integer(i + 1), Integer.toString(i));
	    }
		
	    // Verification
	    int links = testObject.getAllLinks().size();
	    int nodes = testObject.getAllIds().length;
	    assertEquals("Wrong link count.", 50, links);
	    assertEquals("Wrong node count.", 100, nodes);
	}

	/**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.VPLinkContainer.VPLinkContainerTest#getExplanations(String, String)}.
     */
	@Test
	public void testGetExplanation() {
		// Initialization & Preparation
		VPLinkContainer testObject = new VPLinkContainer();
		testObject.addLink("1", "2", "12a");
		testObject.addLink("1", "2", "12b");
		testObject.addLink("2", "3", "23");
		testObject.addLink("3", "4", "34");
		testObject.addLink("4", "5", "45");
		testObject.addLink("6", "5", "65");
		
		// Execution		
		String[] explanation12 = testObject.getExplanations("2", "1");
		String[] explanation23 = testObject.getExplanations("3", "2");
		String[] explanation34 = testObject.getExplanations("3", "4");
		String[] explanation45 = testObject.getExplanations("4", "5");
		String[] explanation65 = testObject.getExplanations("5", "6");
		
		// Verification
		assertTrue("Wrong explanation returned.", (explanation12[0].contains("12a") && explanation12[1].contains("12b")) 
				|| explanation12[1].contains("12a") && explanation12[0].contains("12b"));
		assertTrue("Wrong explanation returned.", explanation23[0].contains("23"));
		assertTrue("Wrong explanation returned.", explanation34[0].contains("34"));
		assertTrue("Wrong explanation returned.", explanation45[0].contains("45"));
		assertTrue("Wrong explanation returned.", explanation65[0].contains("65"));
	}
	
	/**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.VPLinkContainer.VPLinkContainerTest#merge(VPLinkContainer, VPLinkContainer)}.
     */
	@Test
	public void testMerge() {
		// Initialization & Preparation
		VPLinkContainer m1 = new VPLinkContainer();
		m1.addLink("1", "2", "12");
		m1.addLink("2", "3", "23");

		VPLinkContainer m2 = new VPLinkContainer();
		m1.addLink("3", "4", "34");
		m1.addLink("4", "5", "45");
		
		// Execution		
		VPLinkContainer merge = VPLinkContainer.merge(m1, m2);
		
		// Verification
		int links = merge.getAllLinks().size();
		int nodes = merge.getAllIds().length;
		assertEquals("Wrong link count.", 4, links);
		assertEquals("Wrong node count.", 5, nodes);
	}
}

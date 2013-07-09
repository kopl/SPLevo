package org.splevo.vpm.analyzer.semantic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Unit-Tests for the {@link StructuredMap} class.
 * 
 * @author Daniel Kojic
 *
 */
public class StructuredMapTest extends AbstractTest {
	
	/**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.programstructure.StructuredMap#addLink(String, String, String)}.
     */
	@Test
	public void testAddLink() {
		// Initialization
		StructuredMap testObject = new StructuredMap();
		
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
     * {@link org.splevo.vpm.analyzer.programstructure.StructuredMap#addLinks(String, Set<String>)}.
     */
	@Test
	public void testAddLinks() {
		// Initialization
		StructuredMap testObject = new StructuredMap();
		
		// Execution
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < 10; i++) {
			set.add(Integer.toString(i));
		}
		
		testObject.addLinks("1", set);
		
		// Verification
		int links = testObject.getAllLinks().size();
		int nodes = testObject.getAllIds().length;
		assertEquals("Wrong link count.", 1, links);
		assertEquals("Wrong node count.", 10, nodes);
	}

	/**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.programstructure.StructuredMap#clear()}.
     */
	@Test
	public void testClear() {
		// Initialization & Preparation
		StructuredMap testObject = new StructuredMap();
		testObject.addLink("1", "2", "expl");
		
		// Execution		
		testObject.clear();
		
		// Verification
		int links = testObject.getAllLinks().size();
		int nodes = testObject.getAllIds().length;
		assertEquals("Wrong link count.", 0, links);
		assertEquals("Wrong node count.", 0, nodes);
	}

	/**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.programstructure.StructuredMap#getExplanation(String, String)}.
     */
	@Test
	public void testGetExplanation() {
		// Initialization & Preparation
		StructuredMap testObject = new StructuredMap();
		testObject.addLink("1", "2", "12a");
		testObject.addLink("1", "2", "12b");
		testObject.addLink("2", "3", "23");
		testObject.addLink("3", "4", "34");
		testObject.addLink("4", "5", "45");
		testObject.addLink("6", "5", "65");
		
		// Execution		
		String explanation12 = testObject.getExplanation("2", "1");
		String explanation23 = testObject.getExplanation("3", "2");
		String explanation34 = testObject.getExplanation("3", "4");
		String explanation45 = testObject.getExplanation("4", "5");
		String explanation65 = testObject.getExplanation("5", "6");
		
		// Verification
		assertTrue("Wrong explanation returned.", "12a\n12b\n".equals(explanation12) || "12b\n12a\n".equals(explanation12));
		assertEquals("Wrong explanation returned.", "23\n", explanation23);
		assertEquals("Wrong explanation returned.", "34\n", explanation34);
		assertEquals("Wrong explanation returned.", "45\n", explanation45);
		assertEquals("Wrong explanation returned.", "65\n", explanation65);
	}
	
	/**
     * Test method for 
     * {@link org.splevo.vpm.analyzer.programstructure.StructuredMap#merge(StructuredMap, StructuredMap)}.
     */
	@Test
	public void testMerge() {
		// Initialization & Preparation
		StructuredMap m1 = new StructuredMap();
		m1.addLink("1", "2", "12");
		m1.addLink("2", "3", "23");

		StructuredMap m2 = new StructuredMap();
		m1.addLink("3", "4", "34");
		m1.addLink("4", "5", "45");
		
		// Execution		
		StructuredMap merge = StructuredMap.merge(m1, m2);
		
		// Verification
		int links = merge.getAllLinks().size();
		int nodes = merge.getAllIds().length;
		assertEquals("Wrong link count.", 4, links);
		assertEquals("Wrong node count.", 5, nodes);
	}
}

package org.splevo.commons.registry;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegistryBaseTest {

    /** The logger for this test class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(RegistryBaseTest.class);

    private RegistryBase<Object> testRegistry;

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void init() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    @Before
    public void setUp() {
        testRegistry = new RegistryBase<Object>() {
        };
    }

    /**
     * Basic test for initialization.
     */
    @Test
    public void testEmptyAfterInitialization() {
        assertEquals("Registry is not empty after creation", 0, testRegistry.getElements().size());
    }

    /**
     * Basic test for object registration.
     */
    @Test
    public void testRegisteringObject() throws Exception {
        final Object element = new Object();

        testRegistry.registerElement(element);

        assertEquals("Registry contains more than one element", 1, testRegistry.getElements().size());
        assertSame("Registry contains wrong element", element, testRegistry.getElements().get(0));
    }
    
    /**
     * Basic test for object registration.
     */
    @Test
    public void testRegisteringObjects() {
        int numberOfElements = 10;
        List<Object> objects = new ArrayList<Object>();
        for (int i = 0; i < numberOfElements; ++i) {
            Object element = new Object();
            objects.add(element);
            testRegistry.registerElement(element);
        }

        assertEquals("Registry contains wrong number of elements", numberOfElements, testRegistry.getElements().size());

        for (int i = 0; i < numberOfElements; ++i) {
            assertSame(objects.get(i), testRegistry.getElements().get(i));
        }
    }
    
    /**
     * Corner case test for object registration of same object.
     */
    @Test
    public void testNoDuplicateRegistration() {
        final Object element = new Object();
        
        testRegistry.registerElement(element);
        testRegistry.registerElement(element);
        
        assertEquals("Registry contains more than one element", 1, testRegistry.getElements().size());
        assertSame("Registry contains wrong element", element, testRegistry.getElements().get(0));
    }
    
    /**
     * Corner case test for null registration.
     */
    @Test 
    public void testNoNullRegistration() {
        testRegistry.registerElement(null);
        
        assertEquals("Registry contains more than one element", 0, testRegistry.getElements().size());
    }
    
    /**
     * Base test for overriding the sorting method.
     */
    @Test
    public void testCustomSorting() {
        RegistryBase<Integer> sortingTestRegistry = new RegistryBase<Integer>() {
            @Override
            protected int compareElements(Integer element1, Integer element2) {
                return element1.compareTo(element2);
            }
        };
        
        Integer[] expected = new Integer[]{0,1,2};
        
        sortingTestRegistry.registerElement(expected[2]);
        sortingTestRegistry.registerElement(expected[0]);
        sortingTestRegistry.registerElement(expected[1]);
        
        assertArrayEquals(expected, sortingTestRegistry.getElements().toArray(new Integer[0]));        
    }
    
    /**
     * Base test for overriding the element equality method.
     */
    @Test
    public void testCustomObjectEquality() {
        RegistryBase<Integer> customEqualityTestRegistry = new RegistryBase<Integer>() {
            @Override
            protected boolean areElementsConsideredTheSame(Integer element1, Integer element2) {
                return Math.abs(element1.intValue() - element2.intValue()) <= 1;
            }
        };
        
        Integer[] expected = new Integer[]{0,2};
        
        customEqualityTestRegistry.registerElement(0);
        customEqualityTestRegistry.registerElement(1);
        customEqualityTestRegistry.registerElement(2);
        
        assertArrayEquals(expected, customEqualityTestRegistry.getElements().toArray(new Integer[0])); 
    }
}

package org.splevo.commons.registry;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.commons.interfaces.IdHavingElement;

public class IdBasedRegistryBaseTest {

    /** The logger for this test class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(RegistryBaseTest.class);

    private IdBasedRegistryBase<IdHavingElementMock, Integer> testRegistry;
    
    private class IdHavingElementMock implements IdHavingElement<Integer> {
        
        private final Integer id;
        
        public IdHavingElementMock(Integer id) {
            this.id = id;
        }
        
        @Override
        public Integer getId() {
            return id;
        }
    }

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
        testRegistry = new IdBasedRegistryBase<IdHavingElementMock, Integer>() {
        };
    }
    
    /**
     * Base test for getting an element via id.
     */
    @Test
    public void testGetElementById() {
        final IdHavingElementMock element = new IdHavingElementMock(0);
        testRegistry.registerElement(element);
        
        assertSame(element, testRegistry.getElementById(0));
    }
    
    /**
     * Corner case test for getting an element with a not registered id.
     */
    @Test
    public void testGetElementByNotRegisteredId() {
        final IdHavingElementMock element = new IdHavingElementMock(0);
        testRegistry.registerElement(element);
        
        assertNull(testRegistry.getElementById(1));
    }
    
    /**
     * Corner case test for object registration of same object id.
     */
    @Test
    public void testNoDuplicateIdRegistration() {
        final IdHavingElementMock element = new IdHavingElementMock(0);
        
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
     * Corner case test for null id registration.
     */
    @Test
    public void testNoNullIdRegistration() {
        final IdHavingElementMock element = new IdHavingElementMock(null);
        testRegistry.registerElement(element);
        
        assertEquals("Registry contains more than one element", 0, testRegistry.getElements().size());
    }
    
}

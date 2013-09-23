package org.splevo.diffing.java.modisco;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.splevo.diffing.JavaDiffer;
import org.splevo.diffing.java.modisco.Java2KDMDiffer;

/**
 * Abstract base class for diffing tests. 
 */
public abstract class AbstractDiffingTest {
	
	/** The differ to be used in the test. */
	protected Java2KDMDiffer differ = null;
	
	/** The default options to be used in the tests. */
	protected Map<String, Object> diffOptions = null;

	/**
	 * Prepare the test.
	 * Initializes a log4j logging environment.
	 */
	@Before
	public void setUp() {
		// set up a basic logging configuration for the test environment
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
		
		// prepare the default differ and options
		diffOptions = new LinkedHashMap<String, Object>();
		diffOptions.put(JavaDiffer.OPTION_JAVA_IGNORE_PACKAGES, Arrays.asList("java.*", "org.jscience.*", "javolution.*"));
        
        differ = new Java2KDMDiffer();
	}
}

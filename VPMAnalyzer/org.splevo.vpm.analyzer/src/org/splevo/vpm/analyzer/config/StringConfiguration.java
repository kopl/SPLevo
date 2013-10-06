package org.splevo.vpm.analyzer.config;

/**
 * This configuration is build for textual inputs.
 * 
 * @author Daniel Kojic
 *
 */
public class StringConfiguration extends AbstractVPMAnalyzerConfiguration<String> {

	/**
	 * This is the main constructor of this class.
	 * 
	 * @param label
	 *            The configurations' text label.
	 * @param explanation
	 *            A detailed description of the configuration.
	 * @param defaultValue
	 *            The default value for the configuration.
	 */
	public StringConfiguration(String label, String explanation,
			String defaultValue) {
		super(label, explanation, defaultValue);
	}	
}

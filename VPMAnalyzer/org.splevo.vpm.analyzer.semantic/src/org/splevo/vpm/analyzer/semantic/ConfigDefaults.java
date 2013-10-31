package org.splevo.vpm.analyzer.semantic;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

/**
 * This class contains configuration default values.
 * 
 * @author Daniel Kojic
 * 
 */
public final class ConfigDefaults {

	// Configuration labels

	/** The configuration label for the include comments configuration. */
	public static final String LABEL_INCLUDE_COMMENTS = "Include comments";

	/** The configuration label for the USE_RARE_FINDER configuration. */
	public static final String LABEL_USE_IMPORTANT_TERM_FINDER = "Use Important-Term-Search";

	/**
	 * The configuration label for the USE_OVERALL_SIMILARITY_FINDER
	 * configuration.
	 */
	public static final String LABEL_USE_OVERALL_SIMILARITY_FINDER = "Use Overall-Similarity-Search";

	/** The configuration label for the USE_TOP_N_TERM_FINDER configuration. */
	public static final String LABEL_USE_TOP_N_TERM_FINDER = "Use Top-N-Search";

	/** The configuration label for the STOPWORD configuration. */
	public static final String LABEL_STOP_WORDS = "Stop-Words";

	/** The configuration label for the similarity measure configuration. */
	public static final String LABEL_USE_SIMILARITY_MEASURE = "Use similarity metric";

	/** The configuration label for the minimum similarity configuration. */
	public static final String LABEL_MIN_SIMILARITY = "Minimum similarity";

	/** The configuration label for the N configuration. */
	public static final String LABEL_N = "N";

	/** The configuration label for the LEAST_DOC_FREQ configuration. */
	public static final String LABEL_LEAST_DOC_FREQ = "Least Doc. Frequency";

	/** The configuration label for the SPLIT_CAMEL_CASE configuration. */
	public static final String LABEL_SPLIT_CAMEL_CASE = "Split camel-case words";

	// Configuration explanations
	/** The explanation for the stop word configuration. */
	public static final String EXPL_STOP_WORDS = "Stop-Words are terms ignored by the analysis "
			+ "(e.g. words without any semantic meaning). Put in words separated by whitespace.";

	/** The explanation for the N configuration. */
	public static final String EXPL_N = "Calculates the N most frequent words and uses those for analysis.";

	/** The explanation for the least document frequency configuration. */
	public static final String EXPL_LEAST_DOC_FREQ = "Defines the share of all Variationpoints the "
			+ "top-N terms have to be part of.";

	/** The explanation for the similarity measure configuration. */
	public static final String EXPL_USE_SIMILARITY_MEASURE = "Activate to use the min similarity "
			+ "confguration. When deactivated, the minimum similarity option is ignored and "
			+ "variation points with at least one common term are linked.";

	/** The explanation for the minimum similarity configuration. */
	public static final String EXPL_MIN_SIMILARITY = "Defines how similar two Variationpoints have "
			+ "to be to be matched. One common term is the minimum for Variationpoints to be matched "
			+ "(even for a defined similarity of 0%).";

	// Default configuration values
	/**
	 * The default configuration for the RARE_TERM_MAX_PERCENTAGE configuration.
	 */
	public static final double DEFAULT_RARE_TERM_MAX_PERCENTAGE = 0.15d;

	/** The default configuration for the include comments configuration. */
	public static final boolean DEFAULT_INCLUDE_COMMENTS = false;

	/** The default configuration for the USE_RARE_FINDER configuration. */
	public static final boolean DEFAULT_USE_IMPORTANT_TERM_FINDER = true;

	/** The default configuration for the TOP_N_TERM_FINDER configuration. */
	public static final boolean DEFAULT_USE_TOP_N_TERM_FINDER = true;

	/**
	 * The default configuration for the USE_OVERALL_SIMILARITY_FINDER
	 * configuration.
	 */
	public static final boolean DEFAULT_USE_OVERALL_SIMILARITY_FINDER = true;

	/** The default configuration for the LEAST_DOC_FREQ configuration. */
	public static final double DEFAULT_LEAST_DOC_FREQ = 0.35d;

	/** The default configuration for the N configuration. */
	public static final int DEFAULT_N = 5;

	/** The default configuration for the SPLIT_CAMEL_CASE configuration. */
	public static final boolean DEFAULT_SPLIT_CAMEL_CASE = true;

	/** The default configuration for the similarity measure configuration. */
	public static final Boolean DEFAULT_USE_SIMILARITY_MEASURE = false;

	/** The default configuration for the minimum similarity configuration. */
	public static final double DEFAULT_MIN_SIMILARITY = 0.8d;

	// Analyzer configurations
	/** This {@link Analyzer} is used to store comments and annotations. */
	public static final Analyzer COMMENT_ANALYZER = new StandardAnalyzer(
			Version.LUCENE_43);

	/** The stop-word list for the analyzers. */
	public static final String DEFAULT_STOP_WORDS = "get set new case remove class type "
			+ "create arg default configure clear value misc fig panel list element label the";
}

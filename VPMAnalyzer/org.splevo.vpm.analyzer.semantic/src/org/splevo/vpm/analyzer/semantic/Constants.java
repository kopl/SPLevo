package org.splevo.vpm.analyzer.semantic;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

/**
 * This class contains all constant values for the semantic analyzer to ease the access. 
 * 
 * @author Daniel Kojic
 *
 */
public final class Constants {
	// SPLEVO relevant labels######################################################
	/** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_SEMANTIC = "Semantic";

    /** The displayed name of the analyzer. */
    public static final String DISPLAYED_NAME = "Semantic VPM Analyzer";

    
    // Index relevant variables####################################################	
	/** The field name the Variation Point ID is stored in the Lucene index. */
	public static final String INDEX_VARIATIONPOINT = "VP";

	/** The field name the content ID is stored in the Lucene index. */
	public static final String INDEX_CONTENT = "CONTENT";
	
	
	// Configuration labels########################################################
	/** The configuration label for the cosine minimum similarity configuration. */
	public static final String CONFIG_LABEL_MINIMUM_SIMILARITY = "Minimum Similarity";

	/** The configuration label for the RARE_TERM_FINDER_MAX_PERCENTAGE configuration. */
	public static final String CONFIG_LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE = "Rare-Terms Max. Percentage";

	/** The configuration label for the include comments configuration. */
	public static final String CONFIG_LABEL_INCLUDE_COMMENTS = "Include comments?";
	
	/** The configuration label for the USE_RARE_FINDER configuration. */
	public static final String CONFIG_LABEL_USE_RARE_FINDER = "Use Rare-Finder?";

	/** The configuration label for the USE_OVERALL_SIMILARITY_FINDER configuration. */
	public static final String CONFIG_LABEL_USE_OVERALL_SIMILARITY_FINDER = "Use Overall-Similarity-Finder?";
	
	/** The configuration label for the STOPWORD configuration. */
	public static final String CONFIG_LABEL_STOP_WORDS = "Stop-Words";
	
	
	// Default configuration values#################################################
	/** The default configuration for the minimum OVERALL_SIMILARITY configuration. */
	public static final double CONFIG_DEFAULT_OVERALL_MINIMUM_SIMILARITY = 0.7d;

	/** The default configuration for the RARE_TERM_MAX_PERCENTAGE configuration. */
	public static final double CONFIG_DEFAULT_RARE_TERM_MAX_PERCENTAGE = 0.3d;
	
	/** The default configuration for the include comments configuration. */
	public static final boolean CONFIG_DEFAULT_INCLUDE_COMMENTS = false;
	
	/** The default configuration for the USE_RARE_FINDER configuration. */
	public static final boolean CONFIG_DEFAULT_USE_RARE_FINDER = true;
	
	/** The default configuration for the USE_OVERALL_SIMILARITY_FINDER configuration. */
	public static final boolean CONFIG_DEFAULT_USE_OVERALL_SIMILARITY_FINDER = true;
	
	// Analyze configurations########################################################
	/** This {@link Analyzer} is used to store comments and annotations. */
	public static final Analyzer COMMENT_ANALYZER = new StandardAnalyzer(Version.LUCENE_43);

	/** The stop-word list for the analyzers. */
	public static final String[] DEFAULT_STOP_WORDS = new String[]{
		"get", 
		"set", 
		"default", 
		"configure", 
		"clear",
		"integer",
		"float",
		"double",
		"string",
		"value"
		};
}

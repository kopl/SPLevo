package org.splevo.vpm.analyzer.semantic;

/**
 * This class contains all constant values for the semantic analyzer to ease the access. 
 * 
 * @author Daniel Kojic
 *
 */
public final class Constants {
	/** The field name the Variation Point ID is stored in the index. */
	public static final String INDEX_VARIATIONPOINT = "VP";

	/** The field name the content ID is stored in the index. */
	public static final String INDEX_CONTENT = "CONTENT";
	
	/** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_SEMANTIC = "Semantic";

    /** The relationship label of the analyzer. */
    public static final String DISPLAYED_NAME = "Semantic VPM Analyzer";

	/** The configuration label for the cosine minimum similarity configuration. */
	public static final String CONFIG_MINIMUM_SIMILARITY_LABEL = "MINSIM";

	/** The configuration label for the include comments configuration. */
	public static final String CONFIG_INCLUDE_COMMENTS_LABEL = "INCLUDE_COMMENTS";

	/** The default value for the min. cosine similarity. */
	public static final double DEFAULT_MIN_SIMILARITY = 0.7d;
}

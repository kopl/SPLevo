package org.splevo.vpm.analyzer.semantic;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

/**
 * This class contains all constant values for the semantic analyzer to ease the access. 
 * 
 * @author Daniel Kojic
 *
 */
public final class Constants {
	/** The field name the Variation Point ID is stored in the Lucene index. */
	public static final String INDEX_VARIATIONPOINT = "VP";

	/** The field name the content ID is stored in the Lucene index. */
	public static final String INDEX_CONTENT = "CONTENT";
	
	/** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_SEMANTIC = "Semantic";

    /** The displayed name of the analyzer. */
    public static final String DISPLAYED_NAME = "Semantic VPM Analyzer";

	/** The configuration label for the cosine minimum similarity configuration. */
	public static final String CONFIG_MINIMUM_SIMILARITY_LABEL = "MINSIM";

	/** The configuration label for the include comments configuration. */
	public static final String CONFIG_INCLUDE_COMMENTS_LABEL = "INCLUDE_COMMENTS";

	/** The default value for the minimum cosine similarity. */
	public static final double DEFAULT_MIN_SIMILARITY = 0.7d;
	
	/** This {@link Analyzer} is used to store code fragments. */
	public static final Analyzer CONTENT_ANALYZER = new WhitespaceAnalyzer(Version.LUCENE_42);
	
	/** This {@link Analyzer} is used to store comments and annotations. */
	public static final Analyzer COMMENT_ANALYZER = new StandardAnalyzer(Version.LUCENE_42);
}

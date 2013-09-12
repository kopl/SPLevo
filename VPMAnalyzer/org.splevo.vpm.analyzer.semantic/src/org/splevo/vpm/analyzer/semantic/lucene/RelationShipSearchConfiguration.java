package org.splevo.vpm.analyzer.semantic.lucene;

/**
 * This is a container class that holds all necessary information to execute a search.
 * 
 * @author Daniel Kojic
 *
 */
public class RelationShipSearchConfiguration {

	/** Indicates whether to include comments for analysis or not. */
	private boolean matchComments;
	
	/** Determines whether to use RareTermFinder or not. */		 
	private boolean useRareTermFinder;
	
	/** Determines whether to use OverallSimilarityFinder or not. */
	private boolean useOverallSimilarityFinder;
	
	/** Determines whether to use TopNTermFinder or not. */
	private boolean useTopNFinder;
	
	/** The minimum overall similarity. */
	private double minSimilarity;
	
	/** The max. share of terms for the Rare-Finder. */
	private double maxPercentage;
	
	/** The minimum document frequency share. */
	private double leastDocFreq;
	
	/** Specifies the max. number of terms to search for. */
	private int n;
	
	/**
	 * Sets default values. As a default no finder is used, comments are ignored.
	 */
	public RelationShipSearchConfiguration() {
		this.matchComments = false;
		this.useOverallSimilarityFinder = false;
		this.useRareTermFinder = false;
		this.useTopNFinder = false;
	}
	
	/**
	 * Configures the Overall Finder.
	 * 
	 * @param use Determines whether to use the Overall Similarity Finder or not.
	 * @param minSimilarity The minimum percentage of terms that two similar documents have to share.
	 */
	public void configureOverallFinder(boolean use, double minSimilarity) {
		this.useOverallSimilarityFinder = use;
		this.minSimilarity = minSimilarity;
	}
	
	/**
	 * Configures the Rare Term Finder.
	 * 
	 * @param use Determines whether to use the Overall Similarity Finder or not.
	 * @param maxPercentage A term's occurrence in a document mustn't be higher than this.
	 */
	public void configureRareTermFinder(boolean use, double maxPercentage) {
		this.useRareTermFinder = use;
		this.maxPercentage = maxPercentage;
	}

	/**
	 * Configures the Top N Term Finder.
	 * 
	 * @param use Determines whether to use the Overall Similarity Finder or not.
	 * @param leastDocFreq The minimum document frequency share.
	 * @param n Specifies how much of the top-n-terms has to be matched.
	 */
	public void configureTopNFinder(boolean use, double leastDocFreq, int n) {
		this.useTopNFinder = use;
		this.leastDocFreq = leastDocFreq;
		this.n = n;
	}
	
	/**
	 * Indicates whether to use comments or not.
	 * 
	 * @param use True to use comments; False otherwise.
	 */
	public void useComments(boolean use) {
		this.matchComments = use;
	}
	
	/**
	 * @return the matchComments
	 */
	public boolean isMatchComments() {
		return matchComments;
	}

	/**
	 * @return the useRareTermFinder
	 */
	public boolean isUseRareTermFinder() {
		return useRareTermFinder;
	}

	/**
	 * @return the useOverallSimilarityFinder
	 */
	public boolean isUseOverallSimilarityFinder() {
		return useOverallSimilarityFinder;
	}

	/**
	 * @return the useTopNFinder
	 */
	public boolean isUseTopNFinder() {
		return useTopNFinder;
	}

	/**
	 * @return the minSimilarity
	 */
	public double getMinSimilarity() {
		return minSimilarity;
	}

	/**
	 * @return the maxPercentage
	 */
	public double getMaxPercentage() {
		return maxPercentage;
	}

	/**
	 * @return the leastDocFreq
	 */
	public double getLeastDocFreq() {
		return leastDocFreq;
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}
}
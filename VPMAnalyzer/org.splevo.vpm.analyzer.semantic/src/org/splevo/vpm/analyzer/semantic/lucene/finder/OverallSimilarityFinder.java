package org.splevo.vpm.analyzer.semantic.lucene.finder;

import java.util.Map;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

/**
 * This Finder matches documents with a specified minimum percentage of
 * equal terms.
 * 
 * @author Daniel Kojic
 *
 */
public class OverallSimilarityFinder extends AbstractLuceneQueryFinder {

	/** The minimum similarity. */
	private double minSimilarity;

	/**
	 * The default constructor.
	 * 
	 * @param reader The reader to be used by the Finder.
	 * @param matchComments Indicates whether to include comments for analysis or not.
	 */
	public OverallSimilarityFinder(DirectoryReader reader, boolean matchComments) {
		super(reader, matchComments);
	}

	/**
	 * Initializations. Queries the content of the
	 * given {@link DirectoryReader}. Documents having
	 * the specified minimum similarity are a match.
	 * 
	 * @param reader The reader to be used by the Finder.
	 * @param matchComments Indicates whether to include comments for analysis or not.
	 * @param minSimilarity The minimum similarity.
	 */
	public OverallSimilarityFinder(DirectoryReader reader, boolean matchComments, double minSimilarity) {
		super(reader, matchComments);
		this.minSimilarity = minSimilarity;
	}

	@Override
	protected Query buildQuery(String fieldName, Map<String, Integer> termFrequencies) {
		BooleanQuery.setMaxClauseCount(Integer.MAX_VALUE);
		BooleanQuery finalQuery = new BooleanQuery();
		
		// Add a TermQuery for each term in the document.
		for (String key : termFrequencies.keySet()) {
			Term t = new Term(fieldName, key);
			TermQuery termQuery = new TermQuery(t);
			finalQuery.add(termQuery, Occur.SHOULD);
		}
		
		// Set the minimal number of terms that a similar document should have.
		int numTerms = (int) (((float) termFrequencies.keySet().size() * minSimilarity) + 0.5f);
		if (numTerms == 0) {
			numTerms = 1;
		}
		
		finalQuery.setMinimumNumberShouldMatch(numTerms);
		
		return finalQuery;
	}

	@Override
	protected String getExplanation() {
		return "Overall Similarity higher than " + minSimilarity * 100.d + "%.";
	}
}

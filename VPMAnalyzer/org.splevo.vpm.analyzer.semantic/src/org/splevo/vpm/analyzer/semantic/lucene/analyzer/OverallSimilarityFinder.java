package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.util.Map;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.splevo.vpm.analyzer.semantic.Constants;

/**
 * This Finder matches documents with a specified minimum percentage of
 * equal terms.
 * 
 * @author Daniel Kojic
 *
 */
public class OverallSimilarityFinder extends AbstractBooleanQueryFinder {

	/** The minimum similarity. */
	private double minSimilarity;

	/**
	 * The default constructor.
	 * 
	 * @param reader The reader to be used by the Finder.
	 */
	public OverallSimilarityFinder(DirectoryReader reader) {
		super(reader);
	}

	/**
	 * Initializations. Queries the content of the
	 * given {@link DirectoryReader}. Documents having
	 * the specified minimum similarity are a match.
	 * 
	 * @param reader The reader to be used by the Finder.
	 * @param minSimilarity The minimum similarity.
	 */
	public OverallSimilarityFinder(DirectoryReader reader, double minSimilarity) {
		super(reader);
		this.minSimilarity = minSimilarity;
	}

	@Override
	protected Query buildQuery(Map<String, Integer> termFrequencies) {
		BooleanQuery finalQuery = new BooleanQuery();
		
		// Add a TermQuery for each term in the document.
		for (String key : termFrequencies.keySet()) {
			Term t = new Term(Constants.INDEX_CONTENT, key);
			TermQuery termQuery = new TermQuery(t);
			finalQuery.add(termQuery, Occur.SHOULD);
		}
		
		// Set the minimal percentage of terms that a similar document should have.
		int numTerms = (int) (((float) termFrequencies.keySet().size() * minSimilarity) + 0.5f);
		finalQuery.setMinimumNumberShouldMatch(numTerms);
		
		return finalQuery;
	}
}

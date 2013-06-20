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
 * This class analyzer calculates the Cosine Similarity.
 * See <a href="http://en.wikipedia.org/wiki/Cosine_similarity" />
 * 
 * @author Daniel Kojic
 *
 */
public class OverallSimilarityFinder extends AbstractBooleanQueryFinder {

	/**
	 * The default constructor.
	 * 
	 * @param reader The reader to be used by the Finder.
	 */
	public OverallSimilarityFinder(DirectoryReader reader) {
		super(reader);
	}

	@Override
	protected Query buildQuery(Map<String, Integer> termFrequencies) {
		BooleanQuery finalQuery = new BooleanQuery();
		for (String key : termFrequencies.keySet()) {
			Term t = new Term(Constants.INDEX_CONTENT, key);
			TermQuery termQuery = new TermQuery(t);
			finalQuery.add(termQuery, Occur.SHOULD);
		}
		int numTerms = (int) (((float) termFrequencies.keySet().size() * 0.7f) + 0.5f);
		finalQuery.setMinimumNumberShouldMatch(numTerms);
		return finalQuery;
	}
}

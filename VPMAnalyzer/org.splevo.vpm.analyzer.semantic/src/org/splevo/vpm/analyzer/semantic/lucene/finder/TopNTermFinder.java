package org.splevo.vpm.analyzer.semantic.lucene.finder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.splevo.vpm.analyzer.semantic.VPLinkContainer;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;

/**
 * In the first step the finder collects all terms of the index and their
 * frequencies. Terms that occur in less than leastDocFreq % of the documents
 * get filtered out. In the next step, the finder calculates the top n frequent
 * terms of that list. Each of that terms will be a cluster containing documents
 * having that term.
 * 
 * @author Daniel Kojic
 * 
 */
public class TopNTermFinder extends AbstractRelationshipFinder {

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(TopNTermFinder.class);

	/**
	 * Terms have to me in at least this percentage of documents to be part of
	 * the search.
	 */
	private double leastDocFreq;

	/** Max. number of terms to search for. */
	private int n;

	/**
	 * Initializations.
	 * 
	 * @param reader
	 *            The {@link DirectoryReader}.
	 * @param matchComments
	 *            Indicates whether to include comments for analysis or not.
	 * @param leastDocFreq
	 *            The minimum document frequency share.
	 * @param n
	 *            Specifies the max. number of terms to search for.
	 */
	public TopNTermFinder(DirectoryReader reader, boolean matchComments,
			double leastDocFreq, int n) {
		super(reader, matchComments);
		this.leastDocFreq = leastDocFreq;
		this.n = n;
	}

	/**
	 * Calculates the top n frequent terms.
	 * 
	 * @return A {@link Set} containing the terms.
	 */
	private Set<String> getTopTerms() {
		int numDocs = reader.maxDoc();
		Map<String, Integer> termFrequencies = new HashMap<String, Integer>();

		for (int i = 0; i < numDocs; i++) {
			Map<String, Integer> docFrequencies = getTermFrequencies(i,
					Indexer.INDEX_CONTENT);
			addDocFreqToOverallFreq(Indexer.INDEX_CONTENT, termFrequencies,
					docFrequencies);

			if (matchComments) {
				docFrequencies = getTermFrequencies(i, Indexer.INDEX_COMMENT);
				addDocFreqToOverallFreq(Indexer.INDEX_COMMENT, termFrequencies,
						docFrequencies);
			}
		}

		if (termFrequencies.size() == 0) {
			return new HashSet<String>();
		}

		int minFreq = calculateMinFreq(termFrequencies);

		HashSet<String> result = new HashSet<String>();
		for (String key : termFrequencies.keySet()) {
			if (minFreq <= termFrequencies.get(key)) {
				result.add(key);
			}
		}

		return result;
	}

	/**
	 * Adds given document frequencies to a result {@link Map} containing the
	 * index frequencies.
	 * 
	 * @param fieldName
	 *            The name of the index-field to extract doc. frequencies from.
	 * @param termFrequencies
	 *            The index term frequency {@link Map}.
	 * @param docFrequencies
	 *            The document term frequency {@link Map}.
	 */
	private void addDocFreqToOverallFreq(String fieldName,
			Map<String, Integer> termFrequencies,
			Map<String, Integer> docFrequencies) {
		for (String key : docFrequencies.keySet()) {
			try {

				int termFreq = reader.docFreq(new Term(fieldName, key));
				double threshold = (double) reader.maxDoc() * leastDocFreq;
				if (termFreq < threshold) {
					continue;
				}
			} catch (IOException e) {
				logger.error(
						"Error while reading index. Check for unclosed writers.",
						e);
			}

			if (termFrequencies.containsKey(key)) {
				Integer docTermFreq = docFrequencies.get(key);
				termFrequencies
						.put(key, termFrequencies.get(key) + docTermFreq);
			} else {
				termFrequencies.put(key, docFrequencies.get(key));
			}
		}
	}

	/**
	 * Calculates the frequency of the n-th frequent term.
	 * 
	 * @param termFrequencies
	 *            The {@link Map} containing the index frequencies.
	 * @return The frequency of the n-th term.
	 */
	private int calculateMinFreq(Map<String, Integer> termFrequencies) {
		if (termFrequencies.size() == 0) {
			throw new IllegalArgumentException();
		}

		ArrayList<Integer> list = new ArrayList<Integer>(
				termFrequencies.values());
		Collections.sort(list);
		int minFreq;

		if (list.size() <= n) {
			minFreq = list.get(0);
		} else {
			minFreq = list.get(list.size() - 1 - n);
		}
		return minFreq;
	}

	@Override
	public VPLinkContainer findSimilarEntries() {
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		VPLinkContainer result = new VPLinkContainer();
		Set<String> terms = getTopTerms();

		if (terms.size() == 0) {
			return result;
		}

		executeTopNSearch(indexSearcher, result, terms);

		return result;
	}

	/**
	 * Executes a TermQuery for all top-n terms and adds the results to a result
	 * map.
	 * 
	 * @param indexSearcher
	 *            The {@link IndexSearcher} to search the index with.
	 * @param result
	 *            The {@link VPLinkContainer} to add the results to.
	 * @param topTerms
	 *            The {@link Set} containing the top-n-terms.
	 */
	private void executeTopNSearch(IndexSearcher indexSearcher,
			VPLinkContainer result, Set<String> topTerms) {
		for (String term : topTerms) {
			try {
				ScoreDoc[] scoreDocs = executeQuery(term, indexSearcher);
				Set<String> cluster = new HashSet<String>();
				for (ScoreDoc scoreDoc : scoreDocs) {
					cluster.add(indexSearcher.doc(scoreDoc.doc).get(
							Indexer.INDEX_VARIATIONPOINT));
				}
				addClusterToMap(result, cluster, term);
			} catch (IOException e) {
				logger.error("Error while executing query in TopN-Search.");
			}
		}
	}

	/**
	 * Adds a {@link Set} of VP ids to a given {@link VPLinkContainer}.
	 * 
	 * @param result
	 *            The result {@link VPLinkContainer}.
	 * @param cluster
	 *            The {@link Set} containing the IDs.
	 * @param term
	 *            The term that made up the cluster. Used for explanation.
	 */
	private void addClusterToMap(VPLinkContainer result, Set<String> cluster,
			String term) {
		String[] clusterArray = cluster.toArray(new String[0]);
		for (int i = 0; i < clusterArray.length; i++) {
			for (int q = 0; q < clusterArray.length; q++) {
				if (i != q) {
					result.addLink(clusterArray[i], clusterArray[q],
							"Contains TopN-Term: " + term);
				}
			}
		}
	}

	/**
	 * Executes a {@link TermQuery} for the given term. Uses the given
	 * {@link IndexSearcher} to query the index.
	 * 
	 * @param term The term to search for.
	 *            The {@link Term}.
	 * @param indexSearcher
	 *            The {@link IndexSearcher}.
	 * @return The result of the query as {@link ScoreDoc} array.
	 * @throws IOException
	 *             If there are errors while executing the query.
	 */
	private ScoreDoc[] executeQuery(String term, IndexSearcher indexSearcher)
			throws IOException {
		BooleanQuery query = new BooleanQuery();
		query.setMinimumNumberShouldMatch(1);
		query.add(new TermQuery(new Term(Indexer.INDEX_CONTENT, term)),
				Occur.SHOULD);
		if (matchComments) {
			query.add(new TermQuery(new Term(Indexer.INDEX_COMMENT, term)),
					Occur.SHOULD);
		}

		TopScoreDocCollector collector = TopScoreDocCollector.create(
				reader.maxDoc(), true);
		indexSearcher.search(query, collector);
		return collector.topDocs().scoreDocs;
	}

}

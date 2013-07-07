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
import org.splevo.vpm.analyzer.semantic.Constants;
import org.splevo.vpm.analyzer.semantic.StructuredMap;

/**
 * In the first step the finder collects all terms of the index and their frequencies.
 * Terms that occur in less than leastDocFreq % of the documents get filtered out. In
 * the next step, the finder calculates the top n frequent terms of that list. Each of
 * that terms will be a cluster containing documents having that term.
 * 
 * @author Daniel Kojic
 *
 */
public class TopNTermFinder extends AbstractRelationshipFinder {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(TopNTermFinder.class);
    
    /** Terms have to me in at least this percentage of documents to be part of the search. */
    private double leastDocFreq;
    
    /** Max. number of terms to search for. */
    private int n;
	
	/**
	 * Initializations.
	 * 
	 * @param reader The {@link DirectoryReader}.
	 * @param matchComments Indicates whether to include comments for analysis or not.
	 * @param leastDocFreq The minimum document frequency share.
	 * @param n Specifies the max. number of terms to search for.
	 */
	public TopNTermFinder(DirectoryReader reader, boolean matchComments, double leastDocFreq, int n) {
		super(reader, matchComments);
		this.leastDocFreq = leastDocFreq;
		this.n = n;
	}


	/**
	 * Calculates the top n frequent terms.
	 * 
	 * @param fieldName The name of the field to search the terms in.
	 * @return A {@link Set} containing the terms.
	 */
	private Set<String> getTopTerms(String fieldName) {
		int numDocs = reader.maxDoc();
		Map<String, Integer> termFrequencies = new HashMap<String, Integer>();

		for (int i = 0; i < numDocs; i++) {
			Map<String, Integer> docFrequencies = getTermFrequencies(i, fieldName);
			addDocFreqToOverallFreq(fieldName, termFrequencies, docFrequencies);
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
	 * Adds given document frequencies to a result {@link Map} containing the index frequencies.
	 * 
	 * @param fieldName The name of the index-field to extract doc. frequencies from.
	 * @param termFrequencies The index term frequency {@link Map}.
	 * @param docFrequencies The document term frequency {@link Map}.
	 */
	private void addDocFreqToOverallFreq(String fieldName,
			Map<String, Integer> termFrequencies,
			Map<String, Integer> docFrequencies) {
		for (String key : docFrequencies.keySet()) {
			try {
				if (reader.docFreq(new Term(fieldName, key)) < (reader.maxDoc() * leastDocFreq)) {
					continue;
				}
			} catch (IOException e) {
				logger.error("Error while reading index. Check for unclosed writers.", e);
			}
			
			if (termFrequencies.containsKey(key)) {
				Integer docTermFreq = docFrequencies.get(key);
				termFrequencies.put(key, termFrequencies.get(key)
						+ docTermFreq);
			} else {
				termFrequencies.put(key, docFrequencies.get(key));
			}
		}
	}


	/**
	 * Calculates the frequency of the n-th frequent term.
	 * 
	 * @param termFrequencies The {@link Map} containing the index frequencies.
	 * @return The frequency of the n-th term.
	 */
	private int calculateMinFreq(Map<String, Integer> termFrequencies) {
		ArrayList<Integer> list = new ArrayList<Integer>(termFrequencies.values());
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
	public StructuredMap findSimilarEntries() {
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		StructuredMap result = new StructuredMap();
		Set<String> terms = getTopTerms(Constants.INDEX_CONTENT);
		executeTopNSearch(indexSearcher, Constants.INDEX_CONTENT, result, terms);
		
		if (matchComments) {
			Set<String> commentTerms = getTopTerms(Constants.INDEX_COMMENT);
			executeTopNSearch(indexSearcher, Constants.INDEX_COMMENT, result, commentTerms);
		}
		return result;		
	}


	/**
	 * Executes a TermQuery for all top-n terms and adds the results to a result map.
	 * 
	 * @param indexSearcher The {@link IndexSearcher} to search the index with.
	 * @param fieldName The name of the index-field to be considered while searching.
	 * @param result The {@link StructuredMap} to add the results to.
	 * @param topTerms The {@link Set} containing the top-n-terms.
	 */
	private void executeTopNSearch(IndexSearcher indexSearcher, String fieldName,
			StructuredMap result, Set<String> topTerms) {
		for (String term : topTerms) {
			try {
				Term t = new Term(fieldName, term);
				ScoreDoc[] scoreDocs = executeQuery(t, indexSearcher);
				Set<String> cluster = new HashSet<String>();
				for (ScoreDoc scoreDoc : scoreDocs) {
					cluster.add(indexSearcher.doc(scoreDoc.doc).get(Constants.INDEX_VARIATIONPOINT));
				}
				addClusterToMap(result, cluster, term);
			} catch (IOException e) {
				logger.error("Error while executing query in TopN-Search.");
			}
		}
	}


	/**
	 * Adds a {@link Set} of VP ids to a given {@link StructuredMap}.
	 * 
	 * @param result The result {@link StructuredMap}.
	 * @param cluster The {@link Set} containing the IDs.
	 * @param term The term that made up the cluster. Used for explanation.
	 */
	private void addClusterToMap(StructuredMap result, Set<String> cluster, String term) {
		String[] clusterArray = cluster.toArray(new String[0]);
		for (int i = 0; i < clusterArray.length; i++) {
			for (int q = 0; q < clusterArray.length; q++) {
				if (i != q) {
					result.addLink(clusterArray[i], clusterArray[q], "Contains TopN-Term: " + term);
				}				
			}
		}
	}


	/**
	 * Executes a {@link TermQuery} for the given term. Uses the given {@link IndexSearcher} to query the index.
	 * 
	 * @param t The {@link Term}.
	 * @param indexSearcher The {@link IndexSearcher}.
	 * @return The result of the query as {@link ScoreDoc} array.
	 * @throws IOException If there are errors while executing the query.
	 */
	private ScoreDoc[] executeQuery(Term t, IndexSearcher indexSearcher) throws IOException {
		BooleanQuery query = new BooleanQuery();
		query.add(new TermQuery(t), Occur.MUST);
		TopScoreDocCollector collector = TopScoreDocCollector.create(reader.maxDoc(), true);
		indexSearcher.search(query, collector);
		return collector.topDocs().scoreDocs;
	}

}

package org.splevo.vpm.analyzer.semantic.lucene.finder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.util.BytesRef;
import org.splevo.vpm.analyzer.semantic.VPLinkContainer;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;

/**
 * This is a special {@link AbstractRelationshipFinder} that allows similarity measurement
 * by querying the index.
 * 
 * @author Daniel Kojic
 *
 */
public abstract class AbstractLuceneQueryFinder extends AbstractRelationshipFinder {
	
	/**
	 * Initializations. Queries the content of the
	 * given {@link DirectoryReader}.
	 * 
	 * @param reader The {@link DirectoryReader}.
	 * @param matchComments Indicates whether to include comments for analysis or not.
	 */
	public AbstractLuceneQueryFinder(DirectoryReader reader, boolean matchComments) {
		super(reader, matchComments);
	}

	/** The logger for this class. */
    private Logger logger = Logger.getLogger(AbstractLuceneQueryFinder.class);
    
	@Override
	public VPLinkContainer findSimilarEntries() {
		VPLinkContainer result = new VPLinkContainer();
		try {
			IndexSearcher indexSearcher = new IndexSearcher(reader);
						
			// Iterate over all documents (VariationPoints).
			for (int i = 0; i < reader.maxDoc(); i++) {
				Document doc = indexSearcher.doc(i);
				
				if (doc.getField(Indexer.INDEX_CONTENT) != null) {
					buildQueryAndExecuteSearch(indexSearcher, result, Indexer.INDEX_CONTENT, i, doc);
				}	
				
				if (matchComments && doc.getField(Indexer.INDEX_COMMENT) != null) {
					buildQueryAndExecuteSearch(indexSearcher, result, Indexer.INDEX_COMMENT, i, doc);
				}				
			}
		} catch (IOException e) {
			logger.error("Failure while searching Lucene index.", e);
		}
		return result;
	}

	/**
	 * Builds the relevant arguments for a search and executes it. Add the search results to the given result parameter.
	 * 
	 * @param indexSearcher The {@link IndexSearcher} to execute the search on.
	 * @param result The result container.
	 * @param field The field to search on.
	 * @param docID The current document's ID.
	 * @param doc The current document.
	 * @throws IOException Thrown if there were problems during search.
	 */
	private void buildQueryAndExecuteSearch(IndexSearcher indexSearcher, VPLinkContainer result, String field, int docID,
			Document doc) throws IOException {
		Map<String, Integer> frequencies = getTermFrequencies(docID, field);
		Query query = buildQuery(field, frequencies);
		int maxDoc = reader.maxDoc();
		ScoreDoc[] hits = executeQuery(indexSearcher, maxDoc, query);
		String explanation = getExplanation();
		addHitsToStructuredMap(indexSearcher, result, hits, doc, explanation, query);
	}

	/**
	 * This Method builds the {@link Query} the Finder uses to
	 * search similarities.
	 * 
	 * @param fieldName The name of the field that should be searched.
	 * @param termFrequencies A {@link Map} that contains all terms and their frequencies.
	 * @return The {@link Query}.
	 */
	protected abstract Query buildQuery(String fieldName, Map<String, Integer> termFrequencies);
	
	/**
	 * Gets a explanation for the found results.
	 * 
	 * @return The text explanation.
	 */
	protected abstract String getExplanation();

	/**
	 * Adds given {@link ScoreDoc} to a {@link VPLinkContainer}.
	 * 
	 * @param indexSearcher The {@link IndexSearcher} to search the index with.
	 * @param result The {@link VPLinkContainer} to add the results to.
	 * @param hits The query hits to be added to the {@link VPLinkContainer}.
	 * @param doc The relevant document.
	 * @param explanation An explanation that explains the existence of the relationships.
	 * @param query The query that was searched for.
	 * @throws IOException If document doesn't exist in the given {@link IndexSearcher}.
	 */
	private void addHitsToStructuredMap(IndexSearcher indexSearcher,
			VPLinkContainer result, ScoreDoc[] hits, Document doc, String explanation, Query query)
			throws IOException {
	    
	    Set<Term> queryTerms = new HashSet<Term>();
        query.extractTerms(queryTerms);
	    
		for (int q = 0; q < hits.length; q++) {					
			String id1 = doc.get(Indexer.INDEX_VARIATIONPOINT);
			Document document = indexSearcher.doc(hits[q].doc);
			String id2 = document.get(Indexer.INDEX_VARIATIONPOINT);

			Set<String> sharedTerms = determineSharedTerms(queryTerms, document);
			String sharedTermsExplanation = "Shared terms: " + sharedTerms;
			if (sharedTerms.size() > 0) {
				result.addLink(id1, id2, sharedTermsExplanation);
			}			
			result.addLink(id1, id2, explanation);
		}
	}

    /**
     * Determine the terms shared by the related variation points
     * by looking up all terms included in the search query AND a found
     * document.
     * 
     * @param queryTerms The terms of the searched query.
     * @param document A specific document found by the query.
     * @return The {@link Set} of terms shared between the query and the document.
     * @throws IOException 
     */
    private Set<String> determineSharedTerms(Set<Term> queryTerms, Document document) throws IOException {
        Set<String> sharedTerms = new TreeSet<String>();
        Terms termVector = reader.getTermVector(0, Indexer.INDEX_CONTENT);
        TermsEnum termsEnum = null;
        TermsEnum iterator = termVector.iterator(termsEnum);
        BytesRef br = null;
        while ((br = iterator.next()) != null) {
			String term = br.utf8ToString();
			for (Term t : queryTerms) {
				if (t.text().equals(term)) {
	                sharedTerms.add(term);
	            }
			}
		}
        return sharedTerms;
    }
	
	/**
	 * Executes a query.
	 * 
	 * @param indexSearcher The {@link IndexSearcher} to be used.
	 * @param maxDoc The max. number of results.
	 * @param query The {@link Query} to be executed.
	 * @return The result of the search.
	 * @throws IOException If there were errors while executing the query.
	 */
	private ScoreDoc[] executeQuery(IndexSearcher indexSearcher, int maxDoc,
			Query query) throws IOException {
		TopScoreDocCollector collector = TopScoreDocCollector.create(maxDoc, true);
		indexSearcher.search(query, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		return hits;
	}
}

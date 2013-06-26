package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.splevo.vpm.analyzer.semantic.Constants;
import org.splevo.vpm.analyzer.semantic.StructuredMap;

/**
 * This is a special {@link AbstractRelationshipFinder} that allows similarity measurement
 * by querying the index.
 * 
 * @author Daniel Kojic
 *
 */
public abstract class AbstractBooleanQueryFinder extends AbstractRelationshipFinder {
	
	/**
	 * Initializations. Queries the content of the
	 * given {@link DirectoryReader}.
	 * 
	 * @param reader The {@link DirectoryReader}.
	 * @param matchComments Indicates whether to include comments for analysis or not.
	 */
	public AbstractBooleanQueryFinder(DirectoryReader reader, boolean matchComments) {
		super(reader, matchComments);
	}

	/** The logger for this class. */
    private Logger logger = Logger.getLogger(AbstractBooleanQueryFinder.class);
    
	@Override
	public StructuredMap findSimilarEntries() {
		StructuredMap result = new StructuredMap();
		try {
			IndexSearcher indexSearcher = new IndexSearcher(reader);
						
			// Iterate over all documents (VariationPoints).
			for (int i = 0; i < reader.maxDoc(); i++) {
				Document doc = indexSearcher.doc(i);
				
				if (doc.getField(Constants.INDEX_CONTENT) != null) {
					ScoreDoc[] contentHits = searchSimDocs(indexSearcher, i, doc, Constants.INDEX_CONTENT);
					addHitsToStructuredMap(indexSearcher, result, contentHits, doc);
				}				
				
				if (matchComments && doc.getField(Constants.INDEX_COMMENT) != null) {
					ScoreDoc[] commentHits = searchSimDocs(indexSearcher, i, doc, Constants.INDEX_COMMENT);
					addHitsToStructuredMap(indexSearcher, result, commentHits, doc);
				}				
			}
		} catch (IOException e) {
			logger.error("Failure while searching Lucene index.", e);
		}
		return result;
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
	 * Adds given {@link ScoreDoc} to a {@link StructuredMap}.
	 * 
	 * @param indexSearcher The {@link IndexSearcher} to search the index with.
	 * @param result The {@link StructuredMap} to add the results to.
	 * @param hits The query hits to be added to the {@link StructuredMap}.
	 * @param doc The relevant document.
	 * @throws IOException If document doesn't exist in the given {@link IndexSearcher}.
	 */
	private void addHitsToStructuredMap(IndexSearcher indexSearcher,
			StructuredMap result, ScoreDoc[] hits, Document doc)
			throws IOException {
		for (int q = 0; q < hits.length; q++) {					
			String id1 = doc.get(Constants.INDEX_VARIATIONPOINT);
			String id2 = indexSearcher.doc(hits[q].doc).get(Constants.INDEX_VARIATIONPOINT);
			// TODO: add reasonable explanation
			result.addLink(id1, id2, "Query matched Docs.");
		}
	}

	/**
	 * Searches for documents having similar fields.
	 * 
	 * @param indexSearcher The {@link IndexSearcher}.
	 * @param documentID The document id.
	 * @param document The {@link Document}.
	 * @param fieldName The field's name.
	 * @return The result of the query.
	 * @throws IOException If there were errors while executing the query.
	 */
	private ScoreDoc[] searchSimDocs(IndexSearcher indexSearcher,
			int documentID, Document document, String fieldName) throws IOException {
		int maxDoc = reader.maxDoc();
		Map<String, Integer> contentFrequencies = getTermFrequencies(documentID, fieldName);
		Query contentQuery = buildQuery(fieldName, contentFrequencies);
		ScoreDoc[] contentHits = executeQuery(indexSearcher, maxDoc, contentQuery);
		return contentHits;
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

package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.util.BytesRef;
import org.splevo.vpm.analyzer.semantic.Constants;
import org.splevo.vpm.analyzer.semantic.StructuredMap;

public abstract class AbstractBooleanQueryFinder extends AbstractRelationshipFinder{
	
	public AbstractBooleanQueryFinder(DirectoryReader reader) {
		super(reader);
	}

	/** The logger for this class. */
    private Logger logger = Logger.getLogger(AbstractBooleanQueryFinder.class);
    
	@Override
	public StructuredMap findSimilarEntries() {
		StructuredMap result = new StructuredMap();
		try {
			IndexSearcher indexSearcher = new IndexSearcher(reader);
			
			// Get max doc count
			int maxDoc = reader.maxDoc();
			
			// Iterate over all documents (VariationPoints).
			for (int i = 0; i < maxDoc; i++) {
				Document doc = indexSearcher.doc(i);
				Set<String> terms = new HashSet<String>();
				
				BytesRef[] binaryValues = doc.getBinaryValues(Constants.INDEX_CONTENT);
				
				// Add Terms to set.
				for (BytesRef bytesRef : binaryValues) {
					terms.add(bytesRef.utf8ToString());
				}
				
				Map<String, Integer> termFrequencies = getTermFrequencies(i, terms);
				
				// Build query and search.
				Query query = buildQuery(termFrequencies);
				TopScoreDocCollector collector = TopScoreDocCollector.create(maxDoc, true);
				indexSearcher.search(query, collector);
				ScoreDoc[] hits = collector.topDocs().scoreDocs;
				for (int q = 0; q < hits.length; q++) {
					if (hits[q].doc == i) {
						continue;
					}
					
					String id1 = doc.get(Constants.INDEX_VARIATIONPOINT);
					String id2 = indexSearcher.doc(hits[q].doc).get(Constants.INDEX_VARIATIONPOINT);
					result.addLink(id1, id2);
				}
			}
		} catch (IOException e) {
			logger.error("Failure while searching Lucene index.", e);
		}
		return result;
	}  

	protected abstract Query buildQuery(Map<String, Integer> termFrequencies);

}

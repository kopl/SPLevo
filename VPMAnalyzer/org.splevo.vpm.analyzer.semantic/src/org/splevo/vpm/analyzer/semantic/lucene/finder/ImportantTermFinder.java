package org.splevo.vpm.analyzer.semantic.lucene.finder;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.splevo.vpm.analyzer.semantic.VPLinkContainer;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;

/**
 * This {@link AbstractRelationshipFinder} uses rare terms in a
 * document and searches for different documents having the same term.
 * 
 * @author Daniel Kojic
 *
 */
public class ImportantTermFinder extends AbstractRelationshipFinder {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(ImportantTermFinder.class);

	/**
	 * Initializations. Queries the content of the
	 * given {@link DirectoryReader}.
	 * 
	 * @param reader The {@link DirectoryReader}.
	 * @param matchComments Indicates whether to include comments for analysis or not.
	 */
	public ImportantTermFinder(DirectoryReader reader, boolean matchComments) {
		super(reader, matchComments);
	}

	@Override
	public VPLinkContainer findSimilarEntries() {
		VPLinkContainer result = new VPLinkContainer();
		int numDocs = reader.maxDoc();
		MoreLikeThis mlt = new MoreLikeThis(reader);
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		
		if (matchComments) {
			mlt.setFieldNames(new String[]{Indexer.INDEX_CONTENT, Indexer.INDEX_COMMENT});
		} else {
			mlt.setFieldNames(new String[]{Indexer.INDEX_CONTENT});			
		}
		
		for (int i = 0; i < numDocs; i++) {			
			try {
				Query like = mlt.like(i);
				mlt.setBoost(true);
				mlt.setMinTermFreq(1);
				mlt.setMinDocFreq(1);
				TopScoreDocCollector collector = TopScoreDocCollector.create(reader.maxDoc(), true);
				indexSearcher.search(like, collector);
				ScoreDoc[] results = collector.topDocs().scoreDocs;
				for (ScoreDoc scoreDoc : results) {			
					String id1 = reader.document(i).get(Indexer.INDEX_VARIATIONPOINT);
					String id2 = indexSearcher.doc(scoreDoc.doc).get(Indexer.INDEX_VARIATIONPOINT);
					result.addLink(id1, id2, "Found by similarity search.");
				}
			} catch (IOException e) {
				logger.error("Error while creating mlt Query.", e);
			}
		}
		return result;
	}
}

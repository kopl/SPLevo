package org.splevo.vpm.analyzer.semantic;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;

/**
 * @author Daniel Kojic
 *
 */
public class Searcher {
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(Indexer.class);
    
    /** The Directory reader for the main index. */
	private DirectoryReader reader;

	public Searcher(){
		try {
			reader = Indexer.getInstance().getIndexReader();
		} catch (IOException e) {
			logger.error("Error while reading Index.");
		}
	}
	
	public List<String> getMaxFreqTerms(){
		//TermStats[] stats = HighFreqTerms.getHighFreqTerms(reader, Constants.MAX_NUMBER_TERMS_SEARCHED, Constants.CONTENT_INDEX_ID);
		return null;
	}
}

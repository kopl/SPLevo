package org.splevo.vpm.analyzer.semantic.lucene;

import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

/**
 * Use this class to get a Analyzer Wrapper.
 * 
 * @author Daniel Kojic
 *
 */
public class CustomPerFieldAnalyzerWrapper {
	
	/**
	 * This method retrieves a wrapper that handles all index fields with a specific {@link Analyzer}.
	 * 
	 * @param stopWords The stop-word list to be used by the {@link LuceneCodeAnalyzer}.
	 * @param splitCamelCase Specifies whether to split on case-change or not.
	 * @return A {@link AnalyzerWrapper}.
	 */
	public static PerFieldAnalyzerWrapper getWrapper(String[] stopWords, boolean splitCamelCase) {
		Map<String, Analyzer> analyzerPerField = new HashMap<String, Analyzer>();
		analyzerPerField.put(Indexer.INDEX_CONTENT, new LuceneCodeAnalyzer(
				stopWords, splitCamelCase));
		analyzerPerField.put(Indexer.INDEX_COMMENT, new StandardAnalyzer(
				Version.LUCENE_43));

		PerFieldAnalyzerWrapper aWrapper = new PerFieldAnalyzerWrapper(
				new LuceneCodeAnalyzer(stopWords, splitCamelCase), analyzerPerField);
		return aWrapper;
	}
}

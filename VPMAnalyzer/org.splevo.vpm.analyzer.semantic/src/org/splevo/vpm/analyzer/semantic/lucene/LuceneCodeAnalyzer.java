package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;
import org.splevo.vpm.analyzer.semantic.Constants;

import scala.actors.threadpool.Arrays;

public class LuceneCodeAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String field, Reader reader) {
		Tokenizer source = new WhitespaceTokenizer(Version.LUCENE_43, reader);		
	    TokenStream stopFilter = new StopFilter(Version.LUCENE_43, source, getStopWords());
	    Map<String, String> args = new HashMap<String, String>();
	    args.put("generateWordParts", "1"); 
	    args.put("generateNumberParts", "0"); 
	    args.put("catenateWords", "0"); 
	    args.put("catenateNumbers", "0"); 
	    args.put("catenateAll", "0"); 
	    args.put("splitOnCaseChange", "1"); 
	    WordDelimiterFilter filter = new WordDelimiterFilterFactory(args).create(stopFilter);
	    return new TokenStreamComponents(source, filter);
	}
	
	private CharArraySet getStopWords() {
		String[] stopWords = Constants.DEFAULT_STOP_WORDS;
		CharArraySet charArraySet = new CharArraySet(Version.LUCENE_43, Arrays.asList(stopWords), true);
		return charArraySet;
	}
}

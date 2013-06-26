package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.miscellaneous.LengthFilter;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

import scala.actors.threadpool.Arrays;

/**
 * This class is a custom Lucene-Analyzer. Tokenization is done by the
 * WhitespaceTokenizer. Stopwords which are specified in the Constants 
 * class get filtered out. Further, camel-case notation is beeing split 
 * up, too.  
 * 
 * @author Daniel Kojic
 *
 */
public class LuceneCodeAnalyzer extends Analyzer {
	
	/** The stop words. */
	private CharArraySet stopWords;

	/**
	 * Initializes the Analyzer. Filters the given stop words.
	 * 
	 * @param stopWords The stop-words.
	 */
	public LuceneCodeAnalyzer(String[] stopWords) {
		this.stopWords = transformToCharArray(stopWords);
	}

	@Override
	protected TokenStreamComponents createComponents(String field, Reader reader) {
		Tokenizer source = new WhitespaceTokenizer(Version.LUCENE_43, reader);		
	    Map<String, String> args = new HashMap<String, String>();
	    args.put("generateWordParts", "1"); 
	    args.put("generateNumberParts", "0"); 
	    args.put("catenateWords", "0"); 
	    args.put("catenateNumbers", "0"); 
	    args.put("catenateAll", "0"); 
	    args.put("splitOnCaseChange", "1"); 
	    WordDelimiterFilter filter = new WordDelimiterFilterFactory(args).create(source);
	    TokenStream stopFilter = new StopFilter(Version.LUCENE_43, filter, stopWords);
	    TokenStream lowerFilter = new LowerCaseFilter(Version.LUCENE_43, stopFilter);
	    TokenStream lengthFilter = new LengthFilter(true, lowerFilter, 3, Integer.MAX_VALUE);
	    return new TokenStreamComponents(source, lengthFilter);
	}
	
	/**
	 * Transforms the stop-word-list from the Constants class
	 * into a {@link CharArraySet}.
	 * 
	 * @param stopWords The stop-words.
	 * @return The {@link CharArraySet} containing the stop-words.
	 */
	private CharArraySet transformToCharArray(String[] stopWords) {
		CharArraySet charArraySet = new CharArraySet(Version.LUCENE_43, Arrays.asList(stopWords), true);
		return charArraySet;
	}
}

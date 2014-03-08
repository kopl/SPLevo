/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and/or initial documentation
 *    Benjamin Klatt
 *******************************************************************************/
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
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

/**
 * This class is a custom Lucene-Analyzer. Tokenization is done by the
 * WhitespaceTokenizer. Stopwords which are specified in the Constants
 * class get filtered out. Further, camel-case notation is beeing split
 * up, too.
 */
public class LuceneCodeAnalyzer extends Analyzer {

	/** The stop words. */
	private CharArraySet stopWords;

	/** Specifies whether to split on case-change or not. */
	private boolean splitCamelCase;

	/**
	 * Initializes the Analyzer. Filters the given stop words.
	 *
	 * @param stopWords The stop-words.
	 * @param splitCamelCase Specifies whether to split on case-change or not.
	 */
	public LuceneCodeAnalyzer(String[] stopWords, boolean splitCamelCase) {
		this.stopWords = transformToCharArray(stopWords);
		this.splitCamelCase = splitCamelCase;
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
	    args.put("splitOnCaseChange", splitCamelCase ? "1" : "0");
	    TokenStream currentStream = new WordDelimiterFilterFactory(args).create(source);

	    currentStream = new LowerCaseFilter(Version.LUCENE_43, currentStream);
	    currentStream = new LengthFilter(true, currentStream, 3, Integer.MAX_VALUE);
	    currentStream = new StopFilter(Version.LUCENE_43, currentStream, stopWords);
	    currentStream = new StandardFilter(Version.LUCENE_43, currentStream);

	    return new TokenStreamComponents(source, currentStream);
	}

	/**
	 * Transforms the stop-word-list from the Constants class
	 * into a {@link CharArraySet}.
	 *
	 * @param stopWords The stop-words.
	 * @return The {@link CharArraySet} containing the stop-words.
	 */
	private CharArraySet transformToCharArray(String[] stopWords) {
		CharArraySet charArraySet = new CharArraySet(Version.LUCENE_43, java.util.Arrays.asList(stopWords), true);
		return charArraySet;
	}
}

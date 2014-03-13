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
 * This class is a custom Lucene-Analyzer. Tokenization is done by the WhitespaceTokenizer.
 * Stopwords which are specified in the Constants class get filtered out. Further, camel-case
 * notation is beeing split up, too.
 */
public class LuceneCodeAnalyzer extends Analyzer {

    private static final Version LUCENE_VERSION = Version.LUCENE_47;

    /** The stop words. */
    private CharArraySet stopWords;

    /** Specifies whether to split on case-change or not. */
    private boolean splitCamelCase;

    private Stemming stemming;

    /**
     * Initializes the Analyzer. Filters the given stop words.
     *
     * @param stopWords
     *            The stop-words.
     * @param splitCamelCase
     *            Specifies whether to split on case-change or not.
     * @param stemming
     *            option to use stemming or not.
     */
    public LuceneCodeAnalyzer(String[] stopWords, boolean splitCamelCase, Stemming stemming) {
        this.stopWords = transformToCharArray(stopWords);
        this.splitCamelCase = splitCamelCase;
        this.stemming = stemming;
    }

    @SuppressWarnings("resource")
    @Override
    protected TokenStreamComponents createComponents(String field, Reader reader) {

        Tokenizer source = new WhitespaceTokenizer(LUCENE_VERSION, reader);

        Map<String, String> args = new HashMap<String, String>();
        args.put("generateWordParts", "1");
        args.put("generateNumberParts", "0");
        args.put("catenateWords", "0");
        args.put("catenateNumbers", "0");
        args.put("catenateAll", "0");
        args.put("splitOnCaseChange", splitCamelCase ? "1" : "0");
        TokenStream currentStream = new WordDelimiterFilterFactory(args).create(source);

        currentStream = new LowerCaseFilter(LUCENE_VERSION, currentStream);
        currentStream = new LengthFilter(LUCENE_VERSION, currentStream, 3, Integer.MAX_VALUE);
        currentStream = new StopFilter(LUCENE_VERSION, currentStream, stopWords);
        currentStream = Stemming.wrapStemmingFilter(currentStream, stemming);

        currentStream = new StandardFilter(LUCENE_VERSION, currentStream);

        return new TokenStreamComponents(source, currentStream);
    }

    /**
     * Transforms the stop-word-list from the Constants class into a {@link CharArraySet}.
     *
     * @param stopWords
     *            The stop-words.
     * @return The {@link CharArraySet} containing the stop-words.
     */
    private CharArraySet transformToCharArray(String[] stopWords) {
        CharArraySet charArraySet = new CharArraySet(LUCENE_VERSION, java.util.Arrays.asList(stopWords), true);
        return charArraySet;
    }
}

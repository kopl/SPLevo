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
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.miscellaneous.LengthFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

/**
 * This class is a custom Lucene-Analyzer. It processes as follows:
 * <ul>
 * <li>tokenize with the {@link CodeTokenizer}</li>
 * <li>lower-case transformation</li>
 * <li>remove stop words</li>
 * <li>stemming as specified</li>
 * <li>apply the {@link StandardFilter}</li>
 * </ul>
 */
public class LuceneCodeAnalyzer extends Analyzer {

    private static final Version LUCENE_VERSION = Version.LUCENE_47;

    /** The stop words. */
    private CharArraySet stopWords;

    /** Specifies whether to split on case-change or not. */
    private boolean splitCamelCase;

    private Stemming stemming;

    private Set<String> featureTermSet = null;

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

    /**
     * Initializes the Analyzer. Filters the given stop words.
     * 
     * @param stopWords
     *            The stop-words.
     * @param splitCamelCase
     *            Specifies whether to split on case-change or not.
     * @param stemming
     *            option to use stemming or not.
     * @param featureTermList
     *            A {@link Set} of {@link String}s that won't be split.
     */
    public LuceneCodeAnalyzer(String[] stopWords, boolean splitCamelCase, Stemming stemming, Set<String> featureTermList) {
        this(stopWords, splitCamelCase, stemming);
        this.featureTermSet = featureTermList;
    }

    @SuppressWarnings("resource")
    @Override
    protected TokenStreamComponents createComponents(String field, Reader reader) {
        Tokenizer tokenizer = new CodeTokenizer(reader, splitCamelCase, featureTermSet);
        TokenStream currentStream = new LowerCaseFilter(LUCENE_VERSION, tokenizer);
        currentStream = new LengthFilter(LUCENE_VERSION, currentStream, 3, Integer.MAX_VALUE);
        currentStream = new StopFilter(LUCENE_VERSION, currentStream, stopWords);
        currentStream = Stemming.wrapStemmingFilter(currentStream, stemming);
        currentStream = new StandardFilter(LUCENE_VERSION, currentStream);

        return new TokenStreamComponents(tokenizer, currentStream);
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

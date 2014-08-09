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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.miscellaneous.LengthFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

import com.google.common.collect.Sets;

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

    private static Logger logger = Logger.getLogger(LuceneCodeAnalyzer.class);

    private static final Version LUCENE_VERSION = Version.LUCENE_47;

    /** The stop words. */
    private CharArraySet stopWords;

    /** Specifies whether to split on case-change or not. */
    private boolean splitCamelCase;

    private Stemming stemming;

    private Set<String> featuredTerms = null;

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
        this.stopWords = stemAndTransformToCharArray(stopWords, stemming);
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
     * @param featuredTerms
     *            A {@link Set} of {@link String}s that won't be split.
     */
    public LuceneCodeAnalyzer(String[] stopWords, boolean splitCamelCase, Stemming stemming, Set<String> featuredTerms) {
        this(stopWords, splitCamelCase, stemming);
        this.featuredTerms = featuredTerms;
    }

    @SuppressWarnings("resource")
    @Override
    protected TokenStreamComponents createComponents(String field, Reader reader) {
        Tokenizer tokenizer = new CodeTokenizer(reader, splitCamelCase, featuredTerms);
        TokenStream currentStream = new LowerCaseFilter(LUCENE_VERSION, tokenizer);
        currentStream = new LengthFilter(LUCENE_VERSION, currentStream, 3, Integer.MAX_VALUE);
        currentStream = Stemming.wrapStemmingFilter(currentStream, stemming);
        currentStream = new StopFilter(LUCENE_VERSION, currentStream, stopWords);
        currentStream = new StandardFilter(LUCENE_VERSION, currentStream);

        return new TokenStreamComponents(tokenizer, currentStream);
    }

    /**
     * Transforms the stop-word-list from the Constants class into a {@link CharArraySet}.
     *
     * @param stopWords
     *            The stop-words.
     * @param stemming
     *            The stemmer to be used.
     * @return The {@link CharArraySet} containing the stop-words.
     */
    private CharArraySet stemAndTransformToCharArray(String[] stopWords, Stemming stemming) {
        stopWords = LuceneCodeAnalyzer.stemWords(stopWords, stemming);
        CharArraySet charArraySet = new CharArraySet(LUCENE_VERSION, java.util.Arrays.asList(stopWords), true);
        return charArraySet;
    }

    /**
     * Stem a list of words with a configured stemmer.
     *
     * @param words
     *            The list of words to stem.
     * @param stemming
     *            The stemmer to be used.
     * @return The stemmed list of words.
     */
    @SuppressWarnings("resource")
    public static String[] stemWords(String[] words, Stemming stemming) {
        Set<String> stemmedStopWords = Sets.newHashSet();

        for (String word : words) {
            TokenStream tokenStream = new StandardTokenizer(LUCENE_VERSION, new StringReader(word));
            tokenStream = Stemming.wrapStemmingFilter(tokenStream, stemming);

            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            try {
                tokenStream.reset();
                while (tokenStream.incrementToken()) {
                    String term = charTermAttribute.toString();
                    stemmedStopWords.add(term);
                }
            } catch (IOException e) {
                logger.error("Failed to stem a list of words", e);
            }
        }
        return stemmedStopWords.toArray(new String[] {});
    }
}

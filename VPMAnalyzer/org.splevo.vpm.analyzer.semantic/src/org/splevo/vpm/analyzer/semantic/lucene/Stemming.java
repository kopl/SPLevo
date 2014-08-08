/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic.lucene;

import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishMinimalStemFilter;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.tartarus.snowball.ext.EnglishStemmer;

import com.google.common.collect.Lists;

/**
 * Available stemming options to choose from.
 */
public enum Stemming {

    /** Use no stemming. */
    NONE,

    /** Use the porter stemming algorithm from the snowball project */
    SNOWBALL_PORTER,

    /** Use the porter stemming algorithm default implementation provided by lucene */
    PORTER,

    /** Use kstem stemming algorithm */
    KSTEM,

    /** Use the Pling stemming algorithm based on WORDNET lexical database. */
    PLING,

    /**
     *
     * Use the minimal english stemming provided by Lucene
     *
     * Quote from Lucene Doc: Minimal plural stemmer for English.
     * "This stemmer implements the "S-Stemmer" from How Effective Is Suffixing? Donna Harman."
     */
    SSTEMMER;

    /**
     * Get the name representations of the available stemming options.
     *
     * @return The string array of the available stemming names.
     */
    public static String[] getAvailableNames() {
        List<String> stemmingNames = Lists.newArrayList();
        for (Stemming stemming : Stemming.values()) {
            stemmingNames.add(stemming.name());
        }
        return stemmingNames.toArray(new String[] {});
    }

    /**
     * Wrap the current stream with the configured stemming option.
     *
     * @param stream
     *            The token stream to wrap.
     * @param stemming
     *            The stemming option selected.
     * @return The wrapped stream, or the stream itself in case of none configured.
     */
    public static TokenStream wrapStemmingFilter(TokenStream stream, Stemming stemming) {
        switch (stemming) {
        case SNOWBALL_PORTER:
            return new SnowballFilter(stream, new EnglishStemmer());

        case PORTER:
            return new PorterStemFilter(stream);

        case KSTEM:
            return new KStemFilter(stream);

        case SSTEMMER:
            return new EnglishMinimalStemFilter(stream);

        case PLING:
            return new PlingStemmingFilter(stream);

        case NONE:
        default:
            return stream;
        }
    }
}

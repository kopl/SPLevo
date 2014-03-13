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

import java.io.IOException;

import javatools.parsers.PlingStemmer;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * Lucene token filter to apply the PlingStemmer to indexed keywords.
 *
 * <p>
 * Keywords must be split before the
 * </p>
 *
 */
public class PlingStemmingFilter extends TokenFilter {

    private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);

    /**
     * Constructor to link the token stream to process.
     *
     * @param stream
     *            The stream to wrap.
     */
    public PlingStemmingFilter(TokenStream stream) {
        super(stream);
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (!input.incrementToken()) {
            return false;
        }

        String term = new String(termAttribute.toString());
        String stemmed = PlingStemmer.stem(term);

        termAttribute.setLength(stemmed.length());
        termAttribute.setEmpty().append(stemmed);

        return true;
    }

}

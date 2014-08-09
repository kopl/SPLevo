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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.splevo.vpm.analyzer.semantic.AbstractTest;

import com.google.common.collect.Lists;

/**
 * Test case for the code specific lucene analyzer.
 *
 */
public class LuceneCodeAnalyzerTest extends AbstractTest {

    /**
     * Test the stemming of a specific set of words.
     */
    @Test
    public void testStemWords() {
        String[] words = new String[]{"points", "ordered"};

        String[] stemmedWords = LuceneCodeAnalyzer.stemWords(words, Stemming.SNOWBALL_PORTER);

        assertThat(Lists.newArrayList(stemmedWords), hasItems("point", "order"));
    }

}

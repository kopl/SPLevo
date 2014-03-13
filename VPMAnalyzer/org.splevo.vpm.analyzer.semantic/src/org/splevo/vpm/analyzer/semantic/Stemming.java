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
package org.splevo.vpm.analyzer.semantic;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Available stemming options to choose from.
 */
public enum Stemming {

    /** Use no stemming. */
    NONE,

    /** Use the porter stemming algorithm */
    PORTER,

    /** Use the Pling stemming algorithm based on WORDNET lexical database. */
    PLING,

    /**
     *
     * Use the minimal english stemming provided by Lucene
     *
     * Quote from Lucene Doc: Minimal plural stemmer for English.
     * "This stemmer implements the "S-Stemmer" from How Effective Is Suffixing? Donna Harman."
     */
    MINIMALENGLISH;

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
}

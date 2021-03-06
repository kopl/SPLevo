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
package org.splevo.vpm.analyzer.semantic;

import org.splevo.vpm.analyzer.semantic.lucene.Stemming;


/**
 * Container for configuration default values.
 */
public final class Config {

    private static final String CONFIG_ID_BASE = "org.splevo.vpm.analyzer.semantic";

    /** Group identifier for general configurations. */
    public static final String CONFIG_GROUP_GENERAL = "General Configuations";

    /** Group identifier for general configurations. */
    public static final String CONFIG_GROUP_FEATURED_TERMS = "Featured Terms Configuations";

    /** Group Identifier for basic shared term analyzes. */
    public static final String CONFIG_GROUP_SHARED_TERM_FINDER = "Shared Term Detection";

    // --------------------------
    // Include Comments
    // --------------------------

    /** Identifier for the configuration to include comments in the analysis. */
    public static final String CONFIG_ID_INCLUDE_COMMENTS = CONFIG_ID_BASE + "INCLUDE_COMMENTS";

    /** The configuration label for the include comments configuration. */
    public static final String LABEL_INCLUDE_COMMENTS = "Include comments";

    /** The explanation for the stop word configuration. */
    public static final String EXPL_INCLUDE_COMMENTS = "Include what the technology specific cartridge returns as comment.";

    /** The default configuration for the include comments configuration. */
    public static final boolean DEFAULT_INCLUDE_COMMENTS = false;

    // --------------------------
    // Log Indexed Terms
    // --------------------------

    /** Identifier for the configuration to log all indexed terms during analysis. */
    public static final String CONFIG_ID_LOG_INDEXED_TERMS = CONFIG_ID_BASE + "LOG_INDEXED_TERMS";

    /** The configuration label for the log indexed terms configuration. */
    public static final String LABEL_LOG_INDEXED_TERMS = "Indexed terms log path";

    /** The explanation for the log indexed terms configuration. */
    public static final String EXPL_LOG_INDEXED_TERMS = "If a path is defined, all indexed terms are dump to a log file in this path's directory.";

    /** The default configuration for the log indexed terms configuration. */
    public static final String DEFAULT_LOG_INDEXED_TERMS = "";

    // --------------------------
    // Camel Case
    // --------------------------

    /** Identifier for the configuration to split camel case terms. */
    public static final String CONFIG_ID_SPLIT_CAMEL_CASE = CONFIG_ID_BASE + "SPLIT_CAMEL_CASE";

    /** The configuration label for the SPLIT_CAMEL_CASE configuration. */
    public static final String LABEL_SPLIT_CAMEL_CASE = "Split camel-case words";

    /** The explanation for the stop word configuration. */
    public static final String EXPL_SPLIT_CAMEL_CASE = "Split terms whenever the case of the term's characters change.";

    /** The default configuration for the SPLIT_CAMEL_CASE configuration. */
    public static final boolean DEFAULT_SPLIT_CAMEL_CASE = true;

    // --------------------------
    // Stemming
    // --------------------------

    /** Identifier for the configuration to split camel case terms. */
    public static final String CONFIG_ID_STEMMING = CONFIG_ID_BASE + "STEMMING";

    /** The configuration label for the SPLIT_CAMEL_CASE configuration. */
    public static final String LABEL_STEMMING = "Use Stemming";

    /** The explanation for the stop word configuration. */
    public static final String EXPL_STEMMING = "Activate stemming of terms such as plural to singular";

    /** The default configuration for STEMMING. */
    public static final String DEFAULT_STEMMING = Stemming.SNOWBALL_PORTER.name();

    /** The available stemming options derived from the Stemming enum. */
    public static final String[] AVAILABLEVALUES_STEMMING = Stemming.getAvailableNames();

    // --------------------------
    // Stop Words
    // --------------------------

    /** Identifier for the configuration of the stop words to filter. */
    public static final String CONFIG_ID_STOP_WORDS = CONFIG_ID_BASE + "STOP_WORDS";

    /** The configuration label for the STOPWORD configuration. */
    public static final String LABEL_STOP_WORDS = "Stop-Words";

    /** The explanation for the stop word configuration. */
    public static final String EXPL_STOP_WORDS = "Terms to filter and not detect any relationships for. Stop words are filtered before stemming is applied."
            + "Put in words separated by whitespace.";

    /** The stop-word list for the analyzers. */
    public static final String DEFAULT_STOP_WORDS = "get set is";

    /**
     * Default stop words according to Host et al. "The Programmer ��� s Lexicon , Volume I The Verbs"
     */
    public static final String DEFAULT_STOP_WORDS_HOST = "accept action add check clear close create do dump end equals find generate get handle has hash init initialize insert is load make new next parse print process read remove reset run set size start to update validate visit write";

    /**
     * Default stop words according to Caprile and Tonello
     * "Nomen est omen: analyzing the language of function identifiers"
     */
    public static final String DEFAULT_STOP_WORDS_CAPRILE = "get print set expand make copy list delete init search add write read put do parse free send find handle";

    // --------------------------
    // Shared Term Minimum
    // --------------------------

    /** Identifier for the configuration of the minimum similarity. */
    public static final String CONFIG_ID_SHARED_TERM_MINIMUM = CONFIG_ID_BASE + "MIN_SIMILARITY";

    /** The configuration label for the similarity measure configuration. */
    public static final String LABEL_SHARED_TERM_MINIMUM = "Minimum of shared terms";

    /** The explanation for the similarity measure configuration. */
    public static final String EXPL_SHARED_TERM_MINIMUM = "The minimum number of terms two variation points "
            + "have to share to get connected.";

    /** The default configuration for the minimum similarity configuration. */
    public static final int DEFAULT_SHARED_TERM_MINIMUM = 1;

    // --------------------------
    // Featured Terms
    // --------------------------

    /** Identifier for the configuration of the feature terms. */
    public static final String CONFIG_ID_FEATURE_TERMS = CONFIG_ID_BASE + "FEATURE_TERMS";

    /** The configuration label for the feature terms configuration. */
    public static final String LABEL_FEATURE_TERMS = "Featured Terms";

    /** The explanation for the feature terms configuration. */
    public static final String EXPL_FEATURE_TERMS = "Feature Terms will not be split or normalized during analysis. "
            + "Such terms should reflect semantics (e.g. feature names) that can be related to specific features. "
            + "Put in words separated by whitespace.";

    /** The default configuration for the feature terms configuration. */
    public static final String DEFAULT_FEATURE_TERMS = "";


    // --------------------------
    // Featured Terms only
    // --------------------------

    /** Identifier for the configuration to use featured terms only. */
    public static final String CONFIG_ID_FEATURE_TERMS_ONLY = CONFIG_ID_BASE + "FEATURE_TERMS_ONLY";

    /** The configuration label for the FEATURE_TERMS_ONLY configuration. */
    public static final String LABEL_FEATURE_TERMS_ONLY = "Featured terms only";

    /** The explanation for the FEATURE_TERMS_ONLY configuration. */
    public static final String EXPL_FEATURE_TERMS_ONLY = "If featured terms are defined, only those will be considered during the analysis.";

    /** The default configuration for the FEATURE_TERMS_ONLY configuration. */
    public static final boolean DEFAULT_FEATURE_TERMS_ONLY = true;


    // --------------------------
    // Similar Terms Set only
    // --------------------------

    /** Identifier for the configuration to connect only vps sharing the same terms with their neighbors only. */
    public static final String CONFIG_ID_SIMILAR_TERM_SET_ONLY = CONFIG_ID_BASE + "SIMILAR_TERM_SET_ONLY";

    /** The configuration label for the SIMILAR_TERM_SET_ONLY configuration. */
    public static final String LABEL_SIMILAR_TERM_SET_ONLY = "Similar term sets only";

    /** The explanation for the SIMILAR_TERM_SET_ONLY configuration. */
    public static final String EXPL_SIMILAR_TERM_SET_ONLY = "Include VPs only if they share the same terms with their related neighbours.";

    /** The default configuration for the SIMILAR_TERM_SET_ONLY configuration. */
    public static final boolean DEFAULT_SIMILAR_TERM_SET_ONLY = true;


    // --------------------------
    // One shared Term only
    // --------------------------

    /** Identifier for the configuration to connect only vps sharing exactly one similar term with their neighbors only. */
    public static final String CONFIG_ID_ONE_SHARED_TERM_ONLY = CONFIG_ID_BASE + "ONE_SHARED_TERM_ONLY";

    /** The configuration label for the ONE_SHARED_TERM_ONLY configuration. */
    public static final String LABEL_ONE_SHARED_TERM_ONLY = "One shared term only";

    /** The explanation for the ONE_SHARED_TERM_ONLY configuration. */
    public static final String EXPL_ONE_SHARED_TERM_ONLY = "Include VPs only if they share a single term only with their neighbors. Is used in combination with the Similar term sets only option only.";

    /** The default configuration for the ONE_SHARED_TERM_ONLY configuration. */
    public static final boolean DEFAULT_ONE_SHARED_TERM_ONLY = false;

}

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


/**
 * Container for configuration default values.
 */
public final class Config {

    private static final String CONFIG_ID_BASE = "org.splevo.vpm.analyzer.semantic";

    /** Group identifier for general configurations. */
    public static final String CONFIG_GROUP_GENERAL = "General Configuations";

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
    public static final String LABEL_LOG_INDEXED_TERMS = "Log indexed terms";

    /** The explanation for the log indexed terms configuration. */
    public static final String EXPL_LOG_INDEXED_TERMS = "Dump all indexed terms to the log file (info level).";

    /** The default configuration for the log indexed terms configuration. */
    public static final boolean DEFAULT_LOG_INDEXED_TERMS = true;

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
    // Stop Words
    // --------------------------

    /** Identifier for the configuration of the stop words to filter. */
    public static final String CONFIG_ID_STOP_WORDS = CONFIG_ID_BASE + "STOP_WORDS";

    /** The configuration label for the STOPWORD configuration. */
    public static final String LABEL_STOP_WORDS = "Stop-Words";

    /** The explanation for the stop word configuration. */
    public static final String EXPL_STOP_WORDS = "Terms to filter and not detect any relationships for. "
            + "Put in words separated by whitespace.";

    /** The stop-word list for the analyzers. */
    public static final String DEFAULT_STOP_WORDS = "get set is";

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

}

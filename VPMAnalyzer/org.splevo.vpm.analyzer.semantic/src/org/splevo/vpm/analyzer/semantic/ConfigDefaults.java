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

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

/**
 * Container for configuration default values.
 */
public final class ConfigDefaults {

    // --------------------------
    // Include Comments
    // --------------------------

    /** The configuration label for the include comments configuration. */
    public static final String LABEL_INCLUDE_COMMENTS = "Include comments";

    /** The explanation for the stop word configuration. */
    public static final String EXPL_INCLUDE_COMMENTS = "Include what the technology specific cartridge returns as comment.";

    /** The default configuration for the include comments configuration. */
    public static final boolean DEFAULT_INCLUDE_COMMENTS = false;

    // --------------------------
    // Camel Case
    // --------------------------

    /** The configuration label for the SPLIT_CAMEL_CASE configuration. */
    public static final String LABEL_SPLIT_CAMEL_CASE = "Split camel-case words";

    /** The explanation for the stop word configuration. */
    public static final String EXPL_SPLIT_CAMEL_CASE = "Split terms whenever the case of the term's characters change.";

    /** The default configuration for the SPLIT_CAMEL_CASE configuration. */
    public static final boolean DEFAULT_SPLIT_CAMEL_CASE = true;

    // --------------------------
    // Stop Words
    // --------------------------

    /** The configuration label for the STOPWORD configuration. */
    public static final String LABEL_STOP_WORDS = "Stop-Words";

    /** The explanation for the stop word configuration. */
    public static final String EXPL_STOP_WORDS = "Terms to filter and not detect any relationships for. "
            + "Put in words separated by whitespace.";

    /** The stop-word list for the analyzers. */
    public static final String DEFAULT_STOP_WORDS = "get set new case remove class type "
            + "create arg default configure clear value misc fig panel list element label the";

    // --------------------------
    // Shared Term Finder
    // --------------------------

    /** The configuration label for the USE_OVERALL_SIMILARITY_FINDER configuration. */
    public static final String LABEL_USE_SHARED_TERM_FINDER = "Use Standard Shared-Term-Finder";

    /** Explanation of the overall similarity option. */
    public static final String EXPL_USE_SHARED_TERM_FINDER = "Finds variation points sharing at"
            + " least a preconfigured number of terms.";

    /** The default configuration for the USE_OVERALL_SIMILARITY_FINDER configuration. */
    public static final boolean DEFAULT_USE_SHARED_TERM_FINDER = true;

    // --------------------------
    // Shared Term Minimum
    // --------------------------

    /** The configuration label for the similarity measure configuration. */
    public static final String LABEL_SHARED_TERM_MINIMUM = "Minimum of shared terms";

    /** The explanation for the similarity measure configuration. */
    public static final String EXPL_SHARED_TERM_MINIMUM = "The minimum number of terms two variation points have to share to get connected.";

    /** The default configuration for the minimum similarity configuration. */
    public static final int DEFAULT_SHARED_TERM_MINIMUM = 1;

    // --------------------------
    // Others
    // --------------------------

    // Analyzer configurations
    // TODO: This is not a configuration move it somewhere more reasonable
    /** This {@link Analyzer} is used to store comments and annotations. */
    public static final Analyzer COMMENT_ANALYZER = new StandardAnalyzer(Version.LUCENE_43);

}

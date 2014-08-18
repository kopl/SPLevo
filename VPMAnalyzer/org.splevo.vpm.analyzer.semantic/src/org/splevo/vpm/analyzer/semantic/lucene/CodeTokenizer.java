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
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.google.common.collect.Lists;

/**
 * This {@link Tokenizer} applies following steps to the stream:
 * <ul>
 * <li>Removes numeric values</li>
 * <li>Splits at whitespace characters according to the regex <code>\s</code></li>
 * <li>Splits camel case terms</li>
 * </ul>
 *
 * Has a list of terms that will be sustained during the splitting process.
 */
public class CodeTokenizer extends Tokenizer {

    private static Logger logger = Logger.getLogger(CodeTokenizer.class);

    private Set<String> featuredTerms = null;
    private boolean featuredTermsOnly = true;
    private boolean splitCamelCase = true;

    /**
     * Default constructor.
     *
     * @param input
     *            The {@link Reader}.
     * @param splitCamelCase
     *            Determines whether to split camel case words or not.
     */
    public CodeTokenizer(Reader input, boolean splitCamelCase) {
        super(input);
        this.splitCamelCase = splitCamelCase;
    }

    /**
     * Initializes the tokenizer with a list of feature terms that won't be split.
     *
     * @param input
     *            The {@link Reader}.
     * @param splitCamelCase
     *            Determines whether to split camel case words or not.
     * @param featuredTerms
     *            The {@link Set} containing the featured terms as {@link String}.
     * @param featuredTermsOnly
     *            Consider featured terms only if some has been defined.
     */
    public CodeTokenizer(Reader input, boolean splitCamelCase, Set<String> featuredTerms, boolean featuredTermsOnly) {
        this(input, splitCamelCase);
        this.featuredTerms = featuredTerms;
        this.featuredTermsOnly = featuredTermsOnly;
    }

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private LinkedList<String> tokens;

    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();

        // read whole stream
        StringBuilder sb = new StringBuilder();
        int currentValue = 0;
        while ((currentValue = input.read()) != -1) {
            sb.append((char) currentValue);
        }

        // remove numeric values and split at whitespaces
        if (sb.length() > 0) {
            String textWithoutNumericValues = sb.toString().replaceAll("[0-9]", "");
            String textWithFixedFeaturedTerms = fixFeaturedTerms(textWithoutNumericValues, featuredTerms);
            tokens = new LinkedList<String>(Arrays.asList(textWithFixedFeaturedTerms.split("[^\\p{Alpha}[\\s]]")));
        }

        if (tokens.size() == 0) {
            return false;
        }

        String token = tokens.getFirst();
        String featuredTerm = containsFeaturedTerm(token);
        if (featuredTerm != null) {
            String[] split = token.split(featuredTerm);
            if (split.length > 0) {
                tokens.addAll(Arrays.asList(split));
            }
            termAtt.append(featuredTerm);
        } else if (!featuredTermsOnly || featuredTerms == null || featuredTerms.size() == 0) {
            // Process only if non featured terms are allowed
            if (!splitCamelCase) {
                termAtt.append(token);
            } else {
                String[] camelCaseTokens = token.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
                for (int i = 0; i < camelCaseTokens.length; i++) {
                    if (i == 0) {
                        termAtt.append(camelCaseTokens[i]);
                        continue;
                    }
                    tokens.add(camelCaseTokens[i]);
                }
            }
        }
        tokens.remove(token);
        tokens.remove("");

        return true;
    }

    /**
     * Fix featured terms in a string when it occurs with not more than one separating character
     * between its defined characters.
     *
     * For example, a fixed term "myterm" representing as "my-term" in the input string
     * "helloworldmy-termtest" will be returned as "helloworld myterm test". It is assumed that the
     * output will later on be split at whitespaces and thus, the featured term will be treated
     * explicitly.
     *
     * @param string
     *            The string to fix.
     * @param terms
     *            The terms to test.
     * @return The resulting string with fixed featured terms.
     */
    public static String fixFeaturedTerms(String string, Set<String> terms) {

        if (terms == null) {
            return string;
        }

        for (String term : terms) {
            StringBuilder sb = new StringBuilder();
            for (char termChar : term.toCharArray()) {
                if (sb.length() > 0) {
                    sb.append(".?");
                }
                sb.append(termChar);
            }
            String pattern = sb.toString();

            // perform this logging in debug only as it might require
            // a lot of processing resources
            if (logger.isDebugEnabled()) {
                logFixedFeaturedString(string, term, pattern);
            }

            string = string.replaceAll(pattern, " " + term + " ");
        }

        return string.trim();
    }

    private static void logFixedFeaturedString(String string, String term, String pattern) {
        List<String> matchList = Lists.newArrayList();
        Pattern regex = Pattern.compile(pattern);
        Matcher regexMatcher = regex.matcher(string);
        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group());
        }
        if (matchList.size() > 0) {
            logger.debug("Featured term: " + term + "," + matchList);
        }
    }

    private String containsFeaturedTerm(String token) {
        if (featuredTerms == null || featuredTerms.size() == 0) {
            return null;
        }

        for (String featuredTerm : featuredTerms) {
            if (token.contains(featuredTerm)) {
                return featuredTerm;
            }
        }

        return null;
    }
}

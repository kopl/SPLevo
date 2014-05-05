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
import java.util.Set;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

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

    private Set<String> featureTermSet = null;
    private boolean splitCamelCase;

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
     * @param featureTermSet
     *            The {@link Set} containing the feature terms as {@link String}.
     */
    public CodeTokenizer(Reader input, boolean splitCamelCase, Set<String> featureTermSet) {
        this(input, splitCamelCase);
        this.featureTermSet = featureTermSet;
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
            tokens = new LinkedList<String>(Arrays.asList(textWithoutNumericValues.split("\\s")));
        }

        if (tokens.size() == 0) {
            return false;
        }

        String token = tokens.getFirst();
        String featureTerm = containsFeatureTerm(token);
        if (!splitCamelCase) {
            termAtt.append(token);
        } else if (featureTerm != null) {
            String[] split = token.split(featureTerm);
            if (split.length > 0) {
                tokens.addAll(Arrays.asList(split));
            }
            termAtt.append(featureTerm);
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
        tokens.remove(token);
        tokens.remove("");

        return true;
    }

    private String containsFeatureTerm(String token) {
        if (featureTermSet == null || featureTermSet.size() == 0) {
            return null;
        }

        for (String featureTerm : featureTermSet) {
            if (token.contains(featureTerm)) {
                return featureTerm;
            }
        }

        return null;
    }
}

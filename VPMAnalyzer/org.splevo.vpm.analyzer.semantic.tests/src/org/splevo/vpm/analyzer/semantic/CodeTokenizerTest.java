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
package org.splevo.vpm.analyzer.semantic;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import org.splevo.vpm.analyzer.semantic.lucene.CodeTokenizer;

import com.google.common.collect.Sets;

/**
 * Tests for the {@link CodeTokenizer}.
 */
public class CodeTokenizerTest extends AbstractTest {

    /**
     * <strong>Test Settings</strong><br>
     * <ul>
     * <li>SplitCamelCase: false</li>
     * <li>FeatureTerms:-</li>
     * </ul>
     *
     * <strong>Input</strong><br>
     * <code>UseCase doSth</code><br>
     *
     * <strong>Expected tokens</strong><br>
     * <ul>
     * <li>UseCase</li>
     * <li>doSth</li>
     * </ul>
     *
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testDontSplitCamelCase() throws Exception {

        StringReader inputReader = new StringReader("UseCase doSth");
        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, false);
        tokenizer.reset();

        Set<String> tokens = readTokens(tokenizer);

        assertThat(tokens.size(), is(2));
        assertThat(tokens, hasItem("UseCase"));
        assertThat(tokens, hasItem("doSth"));

        tokenizer.close();
    }

    /**
     * <strong>Test Settings</strong><br>
     * <ul>
     * <li>SplitCamelCase: true</li>
     * <li>FeatureTerms:-</li>
     * </ul>
     *
     * <strong>Input</strong><br>
     * <code>UseCase doSth</code><br>
     *
     * <strong>Expected tokens</strong><br>
     * <ul>
     * <li>Use</li>
     * <li>Case</li>
     * <li>do</li>
     * <li>Sth</li>
     * </ul>
     *
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testSplitCamelCase() throws Exception {

        StringReader inputReader = new StringReader("UseCase doSth");
        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, true);
        tokenizer.reset();

        Set<String> tokens = readTokens(tokenizer);

        assertThat(tokens.size(), is(4));
        assertThat(tokens, hasItem("Use"));
        assertThat(tokens, hasItem("Case"));
        assertThat(tokens, hasItem("do"));
        assertThat(tokens, hasItem("Sth"));

        tokenizer.close();
    }

    /**
     * <strong>Test Settings</strong><br>
     * <ul>
     * <li>SplitCamelCase: true</li>
     * <li>FeatureTerms: FeatureTerm</li>
     * </ul>
     *
     * <strong>Input</strong><br>
     * <code>UseCase featureTermVariable</code><br>
     *
     * <strong>Expected tokens</strong><br>
     * <ul>
     * <li>Use</li>
     * <li>Case</li>
     * <li>featureTerm</li>
     * <li>Variable</li>
     * </ul>
     *
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testSplitCamelCaseWithFeatureTerms() throws Exception {

        StringReader inputReader = new StringReader("UseCase featureTermVariable");
        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, true, Sets.newHashSet("featureTerm"), false);
        tokenizer.reset();

        Set<String> tokens = readTokens(tokenizer);

        assertThat(tokens.size(), is(4));
        assertThat(tokens, hasItem("Use"));
        assertThat(tokens, hasItem("Case"));
        assertThat(tokens, hasItem("featureTerm"));
        assertThat(tokens, hasItem("Variable"));

        tokenizer.close();
    }

    /**
     * <strong>Test Settings</strong><br>
     * <ul>
     * <li>SplitCamelCase: true</li>
     * <li>FeatureTerms: UseCase</li>
     * </ul>
     *
     * <strong>Input</strong><br>
     * <code>UseCase featureTermVariable</code><br>
     *
     * <strong>Expected tokens</strong><br>
     * <ul>
     * <li>UseCase</li>
     * <li>feature</li>
     * <li>Term</li>
     * <li>Variable</li>
     * </ul>
     *
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testSplitCamelCaseWithFeatureTermUseCase() throws Exception {

        StringReader inputReader = new StringReader("UseCase featureTermVariable");
        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, true, Sets.newHashSet("UseCase"), false);
        tokenizer.reset();

        Set<String> tokens = readTokens(tokenizer);

        assertThat(tokens.size(), is(4));
        assertThat(tokens, hasItem("UseCase"));
        assertThat(tokens, hasItem("feature"));
        assertThat(tokens, hasItem("Term"));
        assertThat(tokens, hasItem("Variable"));

        tokenizer.close();
    }

    /**
     * <strong>Test Settings</strong><br>
     * <ul>
     * <li>SplitCamelCase: true</li>
     * <li>FeatureTerms: UseCase</li>
     * </ul>
     *
     * <strong>Input</strong><br>
     * <code>UseCase featureTermVariable</code><br>
     *
     * <strong>Expected tokens</strong><br>
     * <ul>
     * <li>UseCase</li>
     * <li>feature</li>
     * <li>Term</li>
     * <li>Variable</li>
     * </ul>
     *
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testSpecialCharacters() throws Exception {

        StringReader inputReader = new StringReader("UseCase feature.termVariable_");
        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, true, Sets.newHashSet("UseCase"), false);
        tokenizer.reset();

        Set<String> tokens = readTokens(tokenizer);

        assertThat(tokens.size(), is(4));
        assertThat(tokens, hasItem("UseCase"));
        assertThat(tokens, hasItem("feature"));
        assertThat(tokens, hasItem("term"));
        assertThat(tokens, hasItem("Variable"));

        tokenizer.close();
    }

    /**
     * Test a featured term included in a larger term.
     *
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testFeaturedTermIncludedInLargerTerm() throws Exception {

        StringReader inputReader = new StringReader("UseCaseDiagramGraphModel");
        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, true, Sets.newHashSet("UseCase"), false);
        tokenizer.reset();

        Set<String> tokens = readTokens(tokenizer);

        assertThat(tokens.size(), is(4));
        assertThat(tokens, hasItem("UseCase"));
        assertThat(tokens, hasItem("Graph"));
        assertThat(tokens, hasItem("Model"));
        assertThat(tokens, hasItem("Diagram"));

        tokenizer.close();
    }

    /**
     * Test a featured term included in a larger term.
     *
     * @throws Exception
     *             An unexpected error occurred.
     */
    @Test
    public void testFeaturedTermContainingNonAlphaChar() throws Exception {

        StringReader inputReader = new StringReader("Use_CaseDiagramGraphModel");
        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, true, Sets.newHashSet("UseCase"), false);
        tokenizer.reset();

        Set<String> tokens = readTokens(tokenizer);

        assertThat(tokens.size(), is(4));
        assertThat(tokens, hasItem("UseCase"));
        assertThat(tokens, hasItem("Graph"));
        assertThat(tokens, hasItem("Model"));
        assertThat(tokens, hasItem("Diagram"));

        tokenizer.close();
    }

    /**
     * Test to fix featured terms which contain not more than one
     * separating character between it's expected characters.
     */
    @Test
    public void fixFeaturedTerms() {

        Set<String> terms = Sets.newHashSet("usecase");
        String fixedString = CodeTokenizer.fixFeaturedTerms("myuse_casehelloworld", terms);

        assertThat(fixedString, is("my usecase helloworld"));
    }

    /**
     * Let the tokenizer process its tokens until no more tokens available. Store the token of each
     * iteration and return all of them.
     *
     * @param tokenizer
     *            The tokenizer to execute.
     * @return The list of tracked tokens.
     * @throws IOException
     *             Any exception during the processing.
     */
    private Set<String> readTokens(CodeTokenizer tokenizer) throws IOException {
        CharTermAttribute attribute = tokenizer.getAttribute(CharTermAttribute.class);
        Set<String> tokens = new HashSet<String>();
        while (tokenizer.incrementToken()) {
            tokens.add(attribute.toString());
        }
        return tokens;
    }
}

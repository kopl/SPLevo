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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

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
public class CodeTokenizerTest {

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
     * @throws Exception An unexpected error occurred.
     */
    @Test
    public void testDontSplitCamelCase() throws Exception {
        StringReader inputReader = new StringReader("UseCase doSth");
        Set<String> tokens = new HashSet<String>();

        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, false);
        tokenizer.reset();
        CharTermAttribute attribute = tokenizer.getAttribute(CharTermAttribute.class);

        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(false));

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
     * @throws Exception An unexpected error occurred.
     */
    @Test
    public void testSplitCamelCase() throws Exception {
        StringReader inputReader = new StringReader("UseCase doSth");
        Set<String> tokens = new HashSet<String>();
        
        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, true);
        tokenizer.reset();
        CharTermAttribute attribute = tokenizer.getAttribute(CharTermAttribute.class);
        
        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(false));

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
     * @throws Exception An unexpected error occurred.
     */
    @Test
    public void testSplitCamelCaseWithFeatureTerms() throws Exception {
        StringReader inputReader = new StringReader("UseCase featureTermVariable");
        Set<String> tokens = new HashSet<String>();
        
        CodeTokenizer tokenizer = new CodeTokenizer(inputReader, true, Sets.newHashSet("featureTerm"));
        tokenizer.reset();
        CharTermAttribute attribute = tokenizer.getAttribute(CharTermAttribute.class);
        
        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(true));
        tokens.add(attribute.toString());
        assertThat(tokenizer.incrementToken(), is(false));
        
        assertThat(tokens, hasItem("Use"));
        assertThat(tokens, hasItem("Case"));
        assertThat(tokens, hasItem("featureTerm"));
        assertThat(tokens, hasItem("Variable"));
        
        tokenizer.close();
    }
}

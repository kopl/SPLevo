/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CONTRIBUTR_FIRST_AND_LAST_NAME - WORK_DONE (e.g. "initial API and implementation and/or initial documentation")
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.graphstream.graph.Node;
import org.junit.Test;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.config.StringConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContent;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProvider;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProviderRegistry;
import org.splevo.vpm.analyzer.semantic.extensionpoint.UnsupportedSoftwareElementException;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Test case to perform different semantic analysis with the overall similarity option and prove the
 * results.
 *
 * The tests are designed to use a mock semantic content provider.
 */
public class SharedTermSemanticAnalysisTest extends AbstractTest {

    /**
     * Test the semantic relationship detection for getMethods.
     *
     * @throws UnsupportedSoftwareElementException
     *             Failed test when thrown.
     */
    @Test
    public void testGetterSetter() throws UnsupportedSoftwareElementException {

        List<String> termsVp1 = Lists.newArrayList("getUseCase");
        List<String> termsVp2 = Lists.newArrayList("setUseCase");
        SemanticContentProvider provider = mockContentProviderForTermLists(termsVp1, termsVp2);
        SemanticContentProviderRegistry.getContentProviders().clear();
        SemanticContentProviderRegistry.registerConentProvider(provider);

        SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        StringConfiguration stopWordConfig = getStopWordConfig(analyzer);
        stopWordConfig.setCurrentValue("get set");

        VPMGraph vpmGraph = mock(VPMGraph.class);
        Node node1 = mockGraphNodeWithVP("NODE1", vpmGraph);
        Node node2 = mockGraphNodeWithVP("NODE2", vpmGraph);

        when(vpmGraph.getNodeCount()).thenReturn(2);
        when(vpmGraph.getNodeSet()).thenReturn(Lists.newArrayList(node1, node2));

        VPMAnalyzerResult result = analyzer.analyze(vpmGraph);
        assertThat("Wrong number of relationship edges", result.getEdgeDescriptors().size(), is(1));
    }

    /**
     * Test the semantic relationship detection for getMethods.
     *
     * @throws UnsupportedSoftwareElementException
     *             Failed test when thrown.
     */
    @Test
    public void testAllFiltered() throws UnsupportedSoftwareElementException {

        List<String> termsVp1 = Lists.newArrayList("getUseCase");
        List<String> termsVp2 = Lists.newArrayList("setUseCase");
        SemanticContentProvider provider = mockContentProviderForTermLists(termsVp1, termsVp2);
        SemanticContentProviderRegistry.getContentProviders().clear();
        SemanticContentProviderRegistry.registerConentProvider(provider);

        SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        StringConfiguration stopWordConfig = getStopWordConfig(analyzer);
        stopWordConfig.setCurrentValue("get set use case");

        VPMGraph vpmGraph = mock(VPMGraph.class);
        Node node1 = mockGraphNodeWithVP("NODE1", vpmGraph);
        Node node2 = mockGraphNodeWithVP("NODE2", vpmGraph);

        when(vpmGraph.getNodeCount()).thenReturn(2);
        when(vpmGraph.getNodeSet()).thenReturn(Lists.newArrayList(node1, node2));

        VPMAnalyzerResult result = analyzer.analyze(vpmGraph);
        assertThat("Wrong number of relationship edges", result.getEdgeDescriptors().size(), is(0));
    }

    /**
     * Mock a graph node with a variation point.
     * @param nodeId The id of the node.
     * @param vpmGraph The graph to mock an getNode() call to.
     * @return
     */
    private Node mockGraphNodeWithVP(String nodeId, VPMGraph vpmGraph) {
        VariationPoint vp = mock(VariationPoint.class);
        when(vp.getEnclosingSoftwareEntity()).thenReturn(mock(SoftwareElement.class));
        Node node = mock(Node.class);
        when(node.getId()).thenReturn(nodeId);
        when(node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class)).thenReturn(vp);

        when(vpmGraph.getNode(nodeId)).thenReturn(node);
        return node;
    }

    /**
     * Mock a content provider that returns for the first call to
     * {@link SemanticContentProvider#getRelevantContent(SoftwareElement, boolean)} the first list
     * of provided terms, and for the second call the second list.
     *
     * @param terms1
     *            The terms to return for the first call.
     * @param terms2
     *            The terms to return for the second call.
     * @return The prepared mock.
     * @throws UnsupportedSoftwareElementException
     */
    private SemanticContentProvider mockContentProviderForTermLists(List<String> terms1, List<String> terms2)
            throws UnsupportedSoftwareElementException {
        SemanticContent semantic1 = new SemanticContent();
        for (String term : terms1) {
            semantic1.addCode(term);
        }

        SemanticContent semantic2 = new SemanticContent();
        for (String term : terms2) {
            semantic2.addCode(term);
        }

        SemanticContentProvider provider = mock(SemanticContentProvider.class);
        when(provider.getRelevantContent(any(SoftwareElement.class), anyBoolean())).thenReturn(semantic1).thenReturn(
                semantic2);
        return provider;
    }

    private StringConfiguration getStopWordConfig(SemanticVPMAnalyzer analyzer) {
        VPMAnalyzerConfigurationSet configs = analyzer.getConfigurations();
        StringConfiguration stopWordConfig = (StringConfiguration) configs.getConfiguration(
                SemanticVPMAnalyzer.CONFIG_GROUP_GENERAL, SemanticVPMAnalyzer.CONFIG_ID_STOP_WORDS);
        return stopWordConfig;
    }

}

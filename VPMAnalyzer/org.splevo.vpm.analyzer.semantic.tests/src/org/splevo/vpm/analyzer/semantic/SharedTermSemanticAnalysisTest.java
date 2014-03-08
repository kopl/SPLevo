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
import org.mockito.stubbing.OngoingStubbing;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.config.StringConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContent;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProvider;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProviderRegistry;
import org.splevo.vpm.analyzer.semantic.extensionpoint.UnsupportedSoftwareElementException;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.variabilityFactory;

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

        List<List<String>> terms = Lists.newArrayList();
        terms.add(Lists.newArrayList("getUseCase"));
        terms.add(Lists.newArrayList("setUseCase"));
        registerContentProvider(terms);
        VPMGraph vpmGraph = mockVPMGraph(Lists.newArrayList("NODE1", "NODE2"));

        SemanticVPMAnalyzer analyzer = createAnalyzer("get set");

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

        List<List<String>> terms = Lists.newArrayList();
        terms.add(Lists.newArrayList("getUseCase"));
        terms.add(Lists.newArrayList("setUseCase"));
        registerContentProvider(terms);

        VPMGraph vpmGraph = mockVPMGraph(Lists.newArrayList("NODE1", "NODE2"));

        SemanticVPMAnalyzer analyzer = createAnalyzer("get set use case");

        VPMAnalyzerResult result = analyzer.analyze(vpmGraph);
        assertThat("Wrong number of relationship edges", result.getEdgeDescriptors().size(), is(0));
    }

    /**
     * Test the analyzer by adding more than two nodes but only two should match
     *
     * @throws UnsupportedSoftwareElementException
     *             Failed test when thrown.
     */
    @Test
    public void testNotAllVPsMatching() throws UnsupportedSoftwareElementException {

        List<List<String>> terms = Lists.newArrayList();
        terms.add(Lists.newArrayList("getMatch"));
        terms.add(Lists.newArrayList("setMatch"));
        terms.add(Lists.newArrayList("doesNotMaatch"));
        registerContentProvider(terms);

        VPMGraph vpmGraph = mockVPMGraph(Lists.newArrayList("NODE1", "NODE2", "NODE3"));

        SemanticVPMAnalyzer analyzer = createAnalyzer("get set");

        VPMAnalyzerResult result = analyzer.analyze(vpmGraph);

        assertThat("Wrong number of relationship edges", result.getEdgeDescriptors().size(), is(1));
    }

    /**
     * Test the analyzer by adding more than two nodes but only two should match
     *
     * @throws UnsupportedSoftwareElementException
     *             Failed test when thrown.
     */
    @Test
    public void testNotAllTermsMatching() throws UnsupportedSoftwareElementException {

        List<List<String>> terms = Lists.newArrayList();
        terms.add(Lists.newArrayList("getMatchMultipleTerms"));
        terms.add(Lists.newArrayList("setTermsMatch"));
        registerContentProvider(terms);

        VPMGraph vpmGraph = mockVPMGraph(Lists.newArrayList("NODE1", "NODE2"));

        SemanticVPMAnalyzer analyzer = createAnalyzer("get set");

        VPMAnalyzerResult result = analyzer.analyze(vpmGraph);

        assertThat("Wrong number of relationship edges", result.getEdgeDescriptors().size(), is(1));
        assertThat("Wrong sublabel", result.getEdgeDescriptors().get(0).getRelationshipSubLabel(), is("match terms"));
    }

    private VPMGraph mockVPMGraph(List<String> nodeIds) {
        List<Node> nodeList = Lists.newArrayList();
        VPMGraph vpmGraph = mock(VPMGraph.class);
        for (String nodeId : nodeIds) {
            Node node = mockGraphNodeWithVP(nodeId, vpmGraph, Lists.newArrayList("NewVariant"));
            nodeList.add(node);
        }
        when(vpmGraph.getNodeCount()).thenReturn(nodeList.size());
        when(vpmGraph.getNodeSet()).thenReturn(nodeList);
        return vpmGraph;
    }

    private SemanticVPMAnalyzer createAnalyzer(String stopWords) {
        SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        StringConfiguration stopWordConfig = getStopWordConfig(analyzer);
        stopWordConfig.setCurrentValue(stopWords);
        return analyzer;
    }

    private void registerContentProvider(List<List<String>> termLists) throws UnsupportedSoftwareElementException {
        SemanticContentProvider provider = mockContentProviderForTermLists(termLists);
        SemanticContentProviderRegistry.getContentProviders().clear();
        SemanticContentProviderRegistry.registerConentProvider(provider);
    }

    /**
     * Mock a graph node with a variation point. The variation point contains as much variants as
     * specified in the provided variant id list. The first one will be marked as leading.
     *
     * @param nodeId
     *            The id of the node.
     * @param vpmGraph
     *            The graph to mock an getNode() call to.
     * @return
     */
    private Node mockGraphNodeWithVP(String nodeId, VPMGraph vpmGraph, List<String> variantIds) {

        VariationPoint vp = variabilityFactory.eINSTANCE.createVariationPoint();
        vp.setEnclosingSoftwareEntity(mock(SoftwareElement.class));

        for (String id : variantIds) {
            Variant v = variabilityFactory.eINSTANCE.createVariant();
            v.setVariantId(id);
            v.getSoftwareEntities().add(mock(SoftwareElement.class));
            if (vp.getVariants().size() == 0) {
                v.setLeading(true);
            }
            vp.getVariants().add(v);
        }

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
    private SemanticContentProvider mockContentProviderForTermLists(List<List<String>> termLists)
            throws UnsupportedSoftwareElementException {

        SemanticContentProvider provider = mock(SemanticContentProvider.class);
        OngoingStubbing<SemanticContent> mockStub = when(provider.getRelevantContent(any(SoftwareElement.class),
                anyBoolean()));

        for (List<String> terms : termLists) {
            SemanticContent semantic = new SemanticContent();
            for (String term : terms) {
                semantic.addCode(term);
            }
            mockStub = mockStub.thenReturn(semantic);
        }

        return provider;
    }

    private StringConfiguration getStopWordConfig(SemanticVPMAnalyzer analyzer) {
        VPMAnalyzerConfigurationSet configs = analyzer.getConfigurations();
        StringConfiguration stopWordConfig = (StringConfiguration) configs.getConfiguration(
                Config.CONFIG_GROUP_GENERAL, Config.CONFIG_ID_STOP_WORDS);
        return stopWordConfig;
    }

}

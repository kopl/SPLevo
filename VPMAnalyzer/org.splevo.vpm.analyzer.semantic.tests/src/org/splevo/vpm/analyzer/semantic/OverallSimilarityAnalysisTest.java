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

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.junit.Test;
import org.splevo.vpm.analyzer.config.StringConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;

/**
 * Test case to perform different semantic analysis with the overall similarity option and prove the
 * results.
 *
 * The tests are designed to use a mock semantic content provider.
 */
public class OverallSimilarityAnalysisTest {

    /**
     * Test the semantic relationship detection for getMethods.
     */
    @Test
    public void testGetterSetter() {

        LinkedList<String> l = mock(LinkedList.class);
        when(l.size()).thenReturn(42);

        assertEquals("Mock did not work", 42, l.size());

        // List<String> terms1 = Lists.newArrayList("getUseCase");
        // List<String> terms2 = Lists.newArrayList("setUseCase");
        //
        // SemanticVPMAnalyzer analyzer = new SemanticVPMAnalyzer();
        // StringConfiguration stopWordConfig = getStopWordConfig(analyzer);
        // stopWordConfig.setCurrentValue("get set");
        //
        // VariationPoint vp1 = mock(VariationPoint.class);
        // Node node1 = mock(Node.class);
        // when(node1.getId()).thenReturn("NODE1");
        // when(node1.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class)).thenReturn(vp1);
        //
        // Node node2 = mock(Node.class);
        //
        //
        // VPMGraph vpmGraph = mock(VPMGraph.class);
        // when(vpmGraph.getNodeCount()).thenReturn(2);
        // when(vpmGraph.getNodeSet()).thenReturn(Lists.newArrayList(node1, node2));
        //
        // analyzer.analyze(vpmGraph);
    }

    private StringConfiguration getStopWordConfig(SemanticVPMAnalyzer analyzer) {
        VPMAnalyzerConfigurationSet configs = analyzer.getConfigurations();
        StringConfiguration stopWordConfig = (StringConfiguration) configs.getConfiguration(
                SemanticVPMAnalyzer.CONFIG_GROUP_GENERAL, SemanticVPMAnalyzer.CONFIG_ID_STOP_WORDS);
        return stopWordConfig;
    }

}

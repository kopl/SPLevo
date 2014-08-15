/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.clonedchange;

import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerException;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;

public class ClonedChangeAnalyzer extends AbstractVPMAnalyzer {
    
    private static final String ANALYZER_NAME = "Cloned Change Analyzer";
    private static final String RELATIONSHIP_LABEL = "ClonedChange";

    @Override
    public VPMAnalyzerResult analyze(VPMGraph vpmGraph) throws VPMAnalyzerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        return ANALYZER_NAME;
    }

    @Override
    public String getRelationshipLabel() {
        return RELATIONSHIP_LABEL;
    }

    @Override
    public VPMAnalyzerConfigurationSet getConfigurations() {
        return new VPMAnalyzerConfigurationSet();
    }

}

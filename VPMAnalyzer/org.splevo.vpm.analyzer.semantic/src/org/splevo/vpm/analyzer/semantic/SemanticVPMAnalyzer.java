package org.splevo.vpm.analyzer.semantic;

import java.util.Map;

import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerConfigurationType;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;

public class SemanticVPMAnalyzer extends AbstractVPMAnalyzer{
	
	/** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_SEMANTIC = "Semantic";

    /** The relationship label of the analyzer. */
    public static final String DISPLAYED_NAME = "Semantic VPM Analyzer";
    
	@Override
	public VPMAnalyzerResult analyze(VPMGraph vpmGraph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, VPMAnalyzerConfigurationType> getAvailableConfigurations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getConfigurationLabels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getConfigurations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return DISPLAYED_NAME;
	}

	@Override
	public String getRelationshipLabel() {
		return RELATIONSHIP_LABEL_SEMANTIC;
	}

}

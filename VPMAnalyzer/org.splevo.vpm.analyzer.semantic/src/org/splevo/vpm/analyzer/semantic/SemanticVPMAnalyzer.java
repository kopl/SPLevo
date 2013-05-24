package org.splevo.vpm.analyzer.semantic;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerConfigurationType;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPoint;

public class SemanticVPMAnalyzer extends AbstractVPMAnalyzer{
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(SemanticVPMAnalyzer.class);
	
	/** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_SEMANTIC = "Semantic";

    /** The relationship label of the analyzer. */
    public static final String DISPLAYED_NAME = "Semantic VPM Analyzer";
    
	@Override
	public VPMAnalyzerResult analyze(VPMGraph vpmGraph) {
		
		// Container for the result.
		VPMAnalyzerResult result = new VPMAnalyzerResult(this);
		
		logger.info("Filling index...");
		fillIndex(vpmGraph);
		logger.info("Indexing done.");

		logger.info("Analyzing...");
		findRelationships(result);
		logger.info("Analysis done.");

		return result;
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

	/**
	 * Writes all necessary data from the {@link VPMGraph} into the Index.
	 * 
	 * @param vpmGraph The {@link VPMGraph} containing the information to be indexed. 
	 */
	private void fillIndex(VPMGraph vpmGraph){
		// TODO Auto-generated method stub
		for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            if (vp != null) {
                ASTNode astNode = vp.getEnclosingSoftwareEntity();
                // TODO: find out how to extract code.
            }

        }
	}
	
	/**
	 * Finds semantic relationships between the variation points.
	 * 
	 * @param result Contains all relationships found.
	 */
	private void findRelationships(VPMAnalyzerResult result) {
		// TODO Auto-generated method stub	
	}
}

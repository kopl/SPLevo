package org.splevo.vpm.analyzer.semantic;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.graphstream.graph.Node;
import org.splevo.modisco.util.SourceConnector;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerConfigurationType;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.variability.VariationPoint;

/**
 * TODO: Write a description of the semantic analyzer.
 * 
 * @author Daniel Kojic
 *
 */
public class SemanticVPMAnalyzer extends AbstractVPMAnalyzer{
	
	/** The logger for this class. */
    private Logger logger = Logger.getLogger(SemanticVPMAnalyzer.class);

	private IndexASTNodeSwitch indexASTNodeSwitch;
    
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
		return Constants.DISPLAYED_NAME;
	}

	@Override
	public String getRelationshipLabel() {
		return Constants.RELATIONSHIP_LABEL_SEMANTIC;
	}

	/**
	 * Writes all necessary data from the {@link VPMGraph} into the Index.
	 * 
	 * @param vpmGraph The {@link VPMGraph} containing the information to be indexed. 
	 */
	private void fillIndex(VPMGraph vpmGraph){
		// Get the indexer instance.
		
		// Iterate through the graph.
		for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            if (vp != null) {
            	ASTNode astNode = vp.getEnclosingSoftwareEntity();
            	
            	if(astNode != null){
            		indexASTNodeSwitch = new IndexASTNodeSwitch();
            		indexASTNodeSwitch.doSwitch(astNode);
            	}            	
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

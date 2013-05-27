package org.splevo.vpm.analyzer.semantic;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerConfigurationType;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;
import org.splevo.vpm.analyzer.semantic.lucene.Searcher;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.CosineSimilarityAnalyzer;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.IRelationshipAnalyzer;
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

    /** The internal configurations map. */
    private Map<String, Object> configurations = new HashMap<String, Object>();
    
	/** The {@link Map} that links the ids to their {@link VariationPoint}s. */
	private Map<String, VariationPoint> vpIndex;
		
	/** The {@link Map} that links the {@link VariationPoint}s to their {@link Node}s. */
	private Map<String, Node> nodeIndex;
    
	private Indexer indexer;
	
	public SemanticVPMAnalyzer(){
		indexer = Indexer.getInstance();
	}
	
	@Override
	public VPMAnalyzerResult analyze(VPMGraph vpmGraph) {
		
		// Container for the result.
		VPMAnalyzerResult result = new VPMAnalyzerResult(this);
		
		logger.info("Filling index...");
		fillIndex(vpmGraph);
		indexer.printIndexContents();
		//fillTestIndex();
		logger.info("Indexing done.");
		
		logger.info("Analyzing...");
		findRelationships(result);
		logger.info("Analysis done.");
		
		Indexer.getInstance().clearIndex();

		return result;
	}

	@Override
	public Map<String, VPMAnalyzerConfigurationType> getAvailableConfigurations() {
		Map<String, VPMAnalyzerConfigurationType> availableConfigurations = new HashMap<String, VPMAnalyzerConfigurationType>();
		availableConfigurations.put(Constants.MINIMUM_SIMILARITY_CONFIG, VPMAnalyzerConfigurationType.STRING);
        return availableConfigurations;
	}

	@Override
	public Map<String, String> getConfigurationLabels() {
		Map<String, String> configurationLabels = new HashMap<String, String>();
		configurationLabels.put(Constants.MINIMUM_SIMILARITY_CONFIG, "Minimal Similarity");
        return configurationLabels;
	}

	@Override
	public Map<String, Object> getConfigurations() {
		return configurations;
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
		vpIndex = new HashMap<String, VariationPoint>();
		nodeIndex = new HashMap<String, Node>();
		IndexASTNodeSwitch indexASTNodeSwitch = new IndexASTNodeSwitch();
		int idCounter = 0;
		
		// Iterate through the graph.
		for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            
            if (vp != null) {
            	ASTNode astNode = vp.getEnclosingSoftwareEntity();
            	if(astNode != null){        		
            		indexASTNodeSwitch.doSwitch(astNode);
            		String id = "VP" + idCounter++;            
                    vpIndex.put(id, vp);
                    nodeIndex.put(id, node);
                    indexer.addToIndex(id, indexASTNodeSwitch.getContent());
            	}
            }
            
            indexASTNodeSwitch.clear();
        }
	}
	
	/**
	 * Finds semantic relationships between the variation points.
	 * 
	 * @param result Contains all relationships found.
	 */
	private void findRelationships(VPMAnalyzerResult result) {
		Double similarity;
		try{
			similarity= Double.parseDouble((String)configurations.get(Constants.MINIMUM_SIMILARITY_CONFIG));
		} catch(Exception e) {
			similarity = 0.5d;
		}
		IRelationshipAnalyzer analyzer = new CosineSimilarityAnalyzer();
		
		Map<String, String> similars = Searcher.getInstance().findSemanticRelationships(analyzer, similarity);
		
		for (String key : similars.keySet()) {
			String value = similars.get(key);
			
			Node sourceNode = nodeIndex.get(key);
			Node targetNode = nodeIndex.get(value);
			
			// TODO: label
			VPMEdgeDescriptor descriptor = buildEdgeDescriptor(sourceNode, targetNode, "SemanticRelationship");
            if (descriptor != null) {
                result.getEdgeDescriptors().add(descriptor);
            }
		}
		
	}
}

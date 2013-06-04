package org.splevo.vpm.analyzer.semantic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
    
	/** The {@link Map} that links the IDs to their {@link VariationPoint}s. */
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
		logger.info("Indexing done.");
		
		logger.info("Analyzing...");
		findRelationships(result);
		logger.info("Analysis done.");
		
		try {
			Indexer.getInstance().clearIndex();
		} catch (IOException e) {
			logger.error("Failure while trying to empty main index.");
		}
		
		return result;
	}

	@Override
	public Map<String, VPMAnalyzerConfigurationType> getAvailableConfigurations() {
		Map<String, VPMAnalyzerConfigurationType> availableConfigurations = new HashMap<String, VPMAnalyzerConfigurationType>();
		availableConfigurations.put(Constants.CONFIG_MINIMUM_SIMILARITY_LABEL, VPMAnalyzerConfigurationType.STRING);
		availableConfigurations.put(Constants.CONFIG_INCLUDE_COMMENTS_LABEL, VPMAnalyzerConfigurationType.BOOLEAN);
        return availableConfigurations;
	}

	@Override
	public Map<String, String> getConfigurationLabels() {
		Map<String, String> configurationLabels = new HashMap<String, String>();
		configurationLabels.put(Constants.CONFIG_MINIMUM_SIMILARITY_LABEL, "Minimal Similarity");
		configurationLabels.put(Constants.CONFIG_INCLUDE_COMMENTS_LABEL, "Include Comments");
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
		int idCounter = 0;
		boolean indexComments = Boolean.getBoolean((String)configurations.get(Constants.CONFIG_INCLUDE_COMMENTS_LABEL));
		
		// Iterate through the graph.
		for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            String id = "VP" + idCounter;
            if(indexStructurally(id, node, vp, indexComments)){
        		idCounter++;
                vpIndex.put(id, vp);
                nodeIndex.put(id, node);
        	}
        }
	}

	private boolean indexStructurally(String id, Node node, VariationPoint vp, boolean indexComments) {
		if(id == null || node == null || vp == null){
			throw new IllegalArgumentException();
		}
		
		boolean result = false;
		IndexASTNodeSwitch indexASTNodeSwitch = new IndexASTNodeSwitch();
		ASTNode astNode = vp.getEnclosingSoftwareEntity();
		if (astNode == null) {
			return false;
		}
					
		TreeIterator<EObject> allContents = EcoreUtil.getAllContents(astNode.eContents());
		while (allContents.hasNext()) {
			EObject next = allContents.next();
			try {
				indexASTNodeSwitch.doSwitch(next);
			} catch (Throwable e) {
				// TODO: WHY IS THIS?
				return false;
			}
			
		}
		
		String content = indexASTNodeSwitch.getContent();
		String comment = indexASTNodeSwitch.getComments();
		indexASTNodeSwitch.clear();
		
		if(comment.length() > 0 && indexComments){
			indexer.addCommentToIndex(id, comment);
			result = true;
		}
		
		if(content.length() > 0) {
			indexer.addCodeToIndex(id, content);
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Finds semantic relationships between the variation points.
	 * 
	 * @param result Contains all relationships found.
	 */
	private void findRelationships(VPMAnalyzerResult result) {
		Double similarity;
		try{
			similarity= Double.parseDouble((String)configurations.get(Constants.CONFIG_MINIMUM_SIMILARITY_LABEL));
		} catch(Exception e) {
			similarity = Constants.DEFAULT_MIN_SIMILARITY;
		}
		IRelationshipAnalyzer analyzer = new CosineSimilarityAnalyzer();
		
		Map<String, String> similars = Searcher.getInstance().findSemanticRelationships(analyzer, similarity);
		
		for (String key : similars.keySet()) {
			String value = similars.get(key);
			
			Node sourceNode = nodeIndex.get(key);
			Node targetNode = nodeIndex.get(value);
			
			String label = vpIndex.get(value).getEnclosingSoftwareEntity().getClass().getSimpleName();
			VPMEdgeDescriptor descriptor = buildEdgeDescriptor(sourceNode, targetNode, label);
            if (descriptor != null){
                result.getEdgeDescriptors().add(descriptor);
            }
		}	
	}
}

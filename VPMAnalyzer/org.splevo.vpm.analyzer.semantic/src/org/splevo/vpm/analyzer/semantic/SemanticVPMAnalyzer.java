package org.splevo.vpm.analyzer.semantic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.index.IndexWriter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.BooleanChoiceConfigDefintion;
import org.splevo.vpm.analyzer.config.ConfigDefinition;
import org.splevo.vpm.analyzer.config.StringConfigDefinition;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;
import org.splevo.vpm.analyzer.semantic.lucene.Searcher;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.CosineSimilarityAnalyzer;
import org.splevo.vpm.analyzer.semantic.lucene.analyzer.IRelationshipAnalyzer;
import org.splevo.vpm.variability.VariationPoint;

/**
 ***** What it does.*********************************************** The semantic relationship
 * VPMAnalazer analyzer is able to find semantic relationships between several
 * {@link VariationPoint}s. To sort out low similarities the analyzer offers a configuration to
 * specify a minimum similarity between the VPs.
 * 
 ***** How does that work?***************************************** As a first step, the analyzer
 * extracts all relevant content from a VPMGraph and stores that within a Lucene index. Through
 * storing additional informations about the indexed text, Lucene provides the ability to extract
 * vectors from given index content. By calculating the cosine similarity for all possible links
 * (cross product of all VPs), semantic dependencies will be found. Those results can be displayed
 * within the VPMGraph or the Refinement Browser.
 ***************************************************************** 
 * 
 * @author Daniel Kojic
 * 
 */
public class SemanticVPMAnalyzer extends AbstractVPMAnalyzer {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SemanticVPMAnalyzer.class);

    /** The internal configurations map. */
    private Map<String, Object> configurations = new HashMap<String, Object>();

    /** The {@link Map} that links the IDs to their {@link VariationPoint}s. */
    private Map<String, VariationPoint> vpIndex;

    /** The {@link Map} that links the {@link VariationPoint}s to their {@link Node}s. */
    private Map<String, Node> nodeIndex;

    /** The indexer instance. */
    private Indexer indexer;

    public SemanticVPMAnalyzer() {
        indexer = Indexer.getInstance();
    }

    @Override
    public VPMAnalyzerResult analyze(VPMGraph vpmGraph) {
        if (vpmGraph == null) {
            throw new IllegalArgumentException();
        }

        // Container for the result.
        VPMAnalyzerResult result = new VPMAnalyzerResult(this);

        // Fill the index.
        logger.info("Filling index...");
        fillIndex(vpmGraph);
        logger.info("Indexing done.");

        // Find relationships.
        logger.info("Analyzing...");
        try {
            findRelationships(result);
            logger.info("Analysis done.");
        } catch (IOException e) {
            logger.error("Cannot read Index. Close all open IndexWriters first.");
        }

        // Clean up the content of the indexer.
        try {
            Indexer.getInstance().clearIndex();
        } catch (IOException e) {
            logger.error("Failure while trying to empty main index.");
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getAvailableConfigurations()
     */
    @Override
    public Map<String, ConfigDefinition> getAvailableConfigurations() {
        Map<String, ConfigDefinition> availableConfigurations = new HashMap<String, ConfigDefinition>();
        availableConfigurations.put(Constants.CONFIG_MINIMUM_SIMILARITY_LABEL, new StringConfigDefinition());
        availableConfigurations.put(Constants.CONFIG_INCLUDE_COMMENTS_LABEL, new BooleanChoiceConfigDefintion());
        return availableConfigurations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getConfigurationLabels()
     */
    @Override
    public Map<String, String> getConfigurationLabels() {
        Map<String, String> configurationLabels = new HashMap<String, String>();
        configurationLabels.put(Constants.CONFIG_MINIMUM_SIMILARITY_LABEL, "Minimal Similarity");
        configurationLabels.put(Constants.CONFIG_INCLUDE_COMMENTS_LABEL, "Include Comments");
        return configurationLabels;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getConfigurations()
     */
    @Override
    public Map<String, Object> getConfigurations() {
        return configurations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getName()
     */
    @Override
    public String getName() {
        return Constants.DISPLAYED_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getRelationshipLabel()
     */
    @Override
    public String getRelationshipLabel() {
        return Constants.RELATIONSHIP_LABEL_SEMANTIC;
    }

    /**
     * Writes all necessary data from the {@link VPMGraph} into the Index.
     * 
     * @param vpmGraph
     *            The {@link VPMGraph} containing the information to be indexed.
     */
    private void fillIndex(VPMGraph vpmGraph) {
        if (vpmGraph == null) {
            throw new IllegalArgumentException();
        }

        // Use Maps to save the mapping of the IDs to the VPs and the Nodes.
        vpIndex = new HashMap<String, VariationPoint>();
        nodeIndex = new HashMap<String, Node>();

        // This counter is used to create the IDs.
        int idCounter = 0;

        // Get the configuration that determines if the comments should be indexed as well.
        boolean indexComments = Boolean
                .getBoolean((String) configurations.get(Constants.CONFIG_INCLUDE_COMMENTS_LABEL));

        // Iterate through the graph.
        for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            String id = "VP" + idCounter;

            // Index the given VariationPoints content.
            // Updates the counter and the maps if something was indexed.
            if (indexNode(id, node, vp, indexComments)) {
                idCounter++;
                vpIndex.put(id, vp);
                nodeIndex.put(id, node);
            }
        }
    }

    /**
     * This method uses the IndexASTNodeSwitch to extract the text from the given Node. It iterates
     * through all child elemenzts.
     * 
     * @param id
     *            The ID used to store the text in the Lucene index.
     * @param node
     *            The corresponding {@link Node}.
     * @param vp
     *            The corresponding {@link VariationPoint}.
     * @param indexComments
     *            Determines if comments should be indexed or ignored.
     * @return True if something was added to the index; False otherwise.
     */
    private boolean indexNode(String id, Node node, VariationPoint vp, boolean indexComments) {
        if (id == null || node == null || vp == null) {
            throw new IllegalArgumentException();
        }

        // The result to be returned
        boolean result = false;

        ASTNode astNode = vp.getEnclosingSoftwareEntity();
        if (astNode == null) {
            return false;
        }

        // Iterate through all child elements.
        TreeIterator<EObject> allContents = EcoreUtil.getAllContents(astNode.eContents());

        while (allContents.hasNext()) {
            EObject next = allContents.next();

            // Use the IndexASTNodeSwitch to extract the relevant content.
            IndexASTNodeSwitch indexASTNodeSwitch = new IndexASTNodeSwitch();
            try {
                indexASTNodeSwitch.doSwitch(next);
            } catch (Throwable e) {
                // TODO: WHY IS THIS?
                logger.error("TODO: Why does IndexASTNodeSSwitch throw exception?");
            }

            // Store the found text.
            String content = indexASTNodeSwitch.getContent();
            String comment = indexASTNodeSwitch.getComments();

            // Add comments if configured and not empty.
            if (comment.length() > 0 && indexComments) {
                indexer.addCommentToIndex(id, comment);
                result = true;
            }

            // Add content if not empty.
            if (content.length() > 0) {
                indexer.addCodeToIndex(id, content);
                result = true;
            }
        }

        return result;
    }

    /**
     * Finds semantic relationships between the variation points.
     * 
     * @param result
     *            Contains all relationships found.
     * @throws IOException
     *             Throws an {@link IOException} when there is already an open {@link IndexWriter}.
     */
    private void findRelationships(VPMAnalyzerResult result) throws IOException {
        if (result == null) {
            throw new IllegalArgumentException();
        }

        Double similarity = getMinimumSimilarity();

        IRelationshipAnalyzer analyzer = new CosineSimilarityAnalyzer();

        // Use the searcher to search for semantic relationships
        // with the given minimum similarity.
        Map<String, Set<String>> similars = Searcher.findSemanticRelationships(analyzer, similarity);

        // Iterate through the VariationPoint pairs and add them to the result.
        for (String key : similars.keySet()) {
            Set<String> values = similars.get(key);

            for (String value : values) {
                Node sourceNode = nodeIndex.get(key);
                Node targetNode = nodeIndex.get(value);

                String label = vpIndex.get(value).getEnclosingSoftwareEntity().getClass().getSimpleName();
                if (label.length() == 0) {
                    label = "semantic";
                }
                VPMEdgeDescriptor descriptor = buildEdgeDescriptor(sourceNode, targetNode, label);
                if (descriptor != null) {
                    result.getEdgeDescriptors().add(descriptor);
                }
            }
        }
    }

    /**
     * Gets the similarity. Use the configuration if specified, otherwise use the default value
     * stored in the Constants class.
     * 
     * @return The minimum similarity value.
     */
    private Double getMinimumSimilarity() {
        Double similarity;
        try {
            similarity = Double.parseDouble((String) configurations.get(Constants.CONFIG_MINIMUM_SIMILARITY_LABEL));
        } catch (Exception e) {
            similarity = Constants.DEFAULT_MIN_SIMILARITY;
        }
        return similarity;
    }
}

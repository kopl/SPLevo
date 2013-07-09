package org.splevo.vpm.analyzer.semantic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
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
import org.splevo.vpm.analyzer.config.DoubleConfigDefinition;
import org.splevo.vpm.analyzer.config.IntegerConfigDefinition;
import org.splevo.vpm.analyzer.config.StringConfigDefinition;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;
import org.splevo.vpm.analyzer.semantic.lucene.Searcher;
import org.splevo.vpm.analyzer.semantic.lucene.finder.RareTermFinder;
import org.splevo.vpm.variability.VariationPoint;

/**
 ***** What it does.*********************************************** 
 * The semantic relationship VPMAnalazer analyzer is able to find 
 * semantic relationships between several {@link VariationPoint}s. 
 * Several configurations allow a customized search, just as needed.
 * 
 ***** How does that work?***************************************** 
 * As a first step, the analyzer extracts all relevant content from 
 * a VPMGraph and stores that within a Lucene index. Through storing 
 * additional informations about the indexed text, Lucene provides the 
 * ability to extract vectors from given index content. The Analyzer
 * uses several Finders to search for semantic dependencies. Those 
 * results can be displayed within the VPMGraph or the Refinement 
 * Browser.
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

    /** The indexer instance. */
    private Indexer indexer;

    /**
     * The default constructor for this class.
     */
    public SemanticVPMAnalyzer() {
    	setDefaultConfigurations();
        indexer = Indexer.getInstance();
    }

    @Override
    public VPMAnalyzerResult analyze(VPMGraph vpmGraph) {
        if (vpmGraph == null) {
            throw new IllegalArgumentException();
        }

        // Container for the result.
        VPMAnalyzerResult result = new VPMAnalyzerResult(this);

        // Fill the index.################################################################
        try {
        	logger.info("Filling index...");
        	fillIndex(vpmGraph);
        	logger.info("Indexing done.");
		} catch (Exception e) {
			logger.error("Cannot write Index. Close all open IndexWriters first.", e);
		}

        // Find relationships.############################################################
        try {
        	logger.info("Analyzing...");
            findRelationships(vpmGraph, result);
            logger.info("Analysis done.");
        } catch (IOException e) {
            logger.error("Cannot read Index. Close all open IndexWriters first.", e);
        }

        // CLEAN-UP.######################################################################
        try {
            Indexer.getInstance().clearIndex();
            logger.info("Clean-Up done.");
        } catch (IOException e) {
            logger.error("Failure while trying to empty main index.", e);
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
        availableConfigurations.put(Constants.CONFIG_LABEL_INCLUDE_COMMENTS, 
        		new BooleanChoiceConfigDefintion(Constants.CONFIG_DEFAULT_INCLUDE_COMMENTS));
        availableConfigurations.put(Constants.CONFIG_LABEL_USE_RARE_FINDER, 
        		new BooleanChoiceConfigDefintion(Constants.CONFIG_DEFAULT_USE_RARE_FINDER));
        availableConfigurations.put(Constants.CONFIG_LABEL_USE_OVERALL_SIMILARITY_FINDER, 
        		new BooleanChoiceConfigDefintion(Constants.CONFIG_DEFAULT_USE_OVERALL_SIMILARITY_FINDER));
        availableConfigurations.put(Constants.CONFIG_LABEL_MINIMUM_SIMILARITY, 
        		new DoubleConfigDefinition(Constants.CONFIG_DEFAULT_OVERALL_MINIMUM_SIMILARITY));
        availableConfigurations.put(Constants.CONFIG_LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE, 
        		new DoubleConfigDefinition(Constants.CONFIG_DEFAULT_RARE_TERM_MAX_PERCENTAGE));
        availableConfigurations.put(Constants.CONFIG_LABEL_STOP_WORDS, 
        		new StringConfigDefinition(Arrays.deepToString(Constants.DEFAULT_STOP_WORDS).replace(", ", " ").replace("[", "").replace("]", "")));
        availableConfigurations.put(Constants.CONFIG_LABEL_USE_TOP_N_TERM_FINDER, 
        		new BooleanChoiceConfigDefintion(Constants.CONFIG_DEFAULT_TOP_N_TERM_FINDER));
        availableConfigurations.put(Constants.CONFIG_LABEL_LEAST_DOC_FREQ, 
        		new DoubleConfigDefinition(Constants.CONFIG_DEFAULT_LEAST_DOC_FREQ));
        availableConfigurations.put(Constants.CONFIG_LABEL_N, 
        		new IntegerConfigDefinition(Constants.CONFIG_DEFAULT_N));
        availableConfigurations.put(Constants.CONFIG_LABEL_SPLIT_CAMEL_CASE, 
        		new BooleanChoiceConfigDefintion(Constants.CONFIG_DEFAULT_SPLIT_CAMEL_CASE));
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
        configurationLabels.put(Constants.CONFIG_LABEL_INCLUDE_COMMENTS, 
        		Constants.CONFIG_LABEL_INCLUDE_COMMENTS);
        configurationLabels.put(Constants.CONFIG_LABEL_USE_RARE_FINDER, 
        		Constants.CONFIG_LABEL_USE_RARE_FINDER);
        configurationLabels.put(Constants.CONFIG_LABEL_USE_OVERALL_SIMILARITY_FINDER, 
        		Constants.CONFIG_LABEL_USE_OVERALL_SIMILARITY_FINDER);
        configurationLabels.put(Constants.CONFIG_LABEL_MINIMUM_SIMILARITY, 
        		Constants.CONFIG_LABEL_MINIMUM_SIMILARITY);
        configurationLabels.put(Constants.CONFIG_LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE, 
        		Constants.CONFIG_LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE);
        configurationLabels.put(Constants.CONFIG_LABEL_STOP_WORDS, 
        		Constants.CONFIG_LABEL_STOP_WORDS);
        configurationLabels.put(Constants.CONFIG_LABEL_USE_TOP_N_TERM_FINDER, 
        		Constants.CONFIG_LABEL_USE_TOP_N_TERM_FINDER);
        configurationLabels.put(Constants.CONFIG_LABEL_LEAST_DOC_FREQ, 
        		Constants.CONFIG_LABEL_LEAST_DOC_FREQ);
        configurationLabels.put(Constants.CONFIG_LABEL_N, 
        		Constants.CONFIG_LABEL_N);
        configurationLabels.put(Constants.CONFIG_LABEL_SPLIT_CAMEL_CASE, 
        		Constants.CONFIG_LABEL_SPLIT_CAMEL_CASE);
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

        // Get the user-configurations.
        boolean indexComments = (Boolean) configurations.get(Constants.CONFIG_LABEL_INCLUDE_COMMENTS);
        boolean splitCamelCase = (Boolean) configurations.get(Constants.CONFIG_LABEL_SPLIT_CAMEL_CASE); 
        String stopWords = (String) configurations.get(Constants.CONFIG_LABEL_STOP_WORDS);
        
        this.indexer.splitCamelCase(splitCamelCase);
                
        if (stopWords != null) {
        	if (stopWords.length() > 0) {
        		this.indexer.setStopWords(stopWords.split(" "));
        	} else {
        		this.indexer.setStopWords(new String[0]);
			}
        }
        
        // Iterate through the graph.
        for (Node node : vpmGraph.getNodeSet()) {
            VariationPoint vp = node.getAttribute(VPMGraph.VARIATIONPOINT, VariationPoint.class);
            indexNode(node.getId(), node, vp, indexComments);
        }
    }

    /**
     * This method uses the IndexASTNodeSwitch to extract the text from the given Node. It iterates
     * through all child elements.
     * 
     * @param id
     *            The ID used to store the text in the Lucene index.
     * @param node
     *            The corresponding {@link Node}.
     * @param vp
     *            The corresponding {@link VariationPoint}.
     * @param indexComments
     *            Determines if comments should be indexed or ignored.
     */
    private void indexNode(String id, Node node, VariationPoint vp, boolean indexComments) {
        if (id == null || node == null || vp == null) {
            throw new IllegalArgumentException();
        }

        ASTNode astNode = vp.getEnclosingSoftwareEntity();
        if (astNode == null) {
            throw new IllegalStateException();
        }

        // Iterate through all child elements.
        TreeIterator<EObject> allContents = EcoreUtil.getAllContents(astNode.eContents());
        
        IndexASTNodeSwitch indexASTNodeSwitch = new IndexASTNodeSwitch(indexComments);

        while (allContents.hasNext()) {
            EObject next = allContents.next();
            indexASTNodeSwitch.doSwitch(next);
        }
        
        // Add content and comment.
        String content = indexASTNodeSwitch.getContent();
    	
        try {
        	if (indexComments) {
        		String comment = indexASTNodeSwitch.getComments();
        		this.indexer.addToIndex(id, content, comment);
        	} else {
        		this.indexer.addToIndex(id, content, null);
    		}
		} catch (IOException e) {
			logger.error("Failure while adding node to index.", e);
		}
    	
    }

    /**
     * Finds semantic relationships between the variation points.
     * 
     * @param graph The {@link VPMGraph} to extract the IDs of the result nodes from.
     * @param result
     *            Contains all relationships found.
     * @throws IOException
     *             Throws an {@link IOException} when there is already an open {@link IndexWriter}.
     */
    private void findRelationships(VPMGraph graph, VPMAnalyzerResult result) throws IOException {
        if (result == null) {
            throw new IllegalArgumentException();
        }
        
        // Get the configurations
        boolean includeComments = (Boolean) configurations.get(Constants.CONFIG_LABEL_INCLUDE_COMMENTS);
        boolean useRareFinder = (Boolean) configurations.get(Constants.CONFIG_LABEL_USE_RARE_FINDER);
        boolean useOverallSimFinder = (Boolean) configurations.get(Constants.CONFIG_LABEL_USE_OVERALL_SIMILARITY_FINDER);
        boolean useTopNTermFinder = (Boolean) configurations.get(Constants.CONFIG_LABEL_USE_TOP_N_TERM_FINDER);
        Double minSimilarity = (Double) configurations.get(Constants.CONFIG_LABEL_MINIMUM_SIMILARITY);
        Double maxPercentage = (Double) configurations.get(Constants.CONFIG_LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE);
        Double leastDocFreq = (Double) configurations.get(Constants.CONFIG_LABEL_LEAST_DOC_FREQ);
        Integer n = (Integer) configurations.get(Constants.CONFIG_LABEL_N);
        
        // Use the searcher to search for semantic relationships.
        StructuredMap similars = Searcher.findSemanticRelationships(
        		includeComments, 
        		useRareFinder, 
        		useOverallSimFinder, 
        		useTopNTermFinder, 
        		minSimilarity, 
        		maxPercentage, 
        		leastDocFreq, 
        		n);

        // Iterate through the VariationPoint pairs and add them to the result.
        for (String key : similars.getAllLinks().keySet()) {
            Set<String> values = similars.getAllLinks().get(key);

            for (String value : values) {
                Node sourceNode = graph.getNode(key);
                Node targetNode = graph.getNode(value);
                String explanation = similars.getExplanation(key, value);
                
                if (explanation != null) {
                	logger.info("Semantic Link: " + sourceNode.getId() + "<-->" + targetNode.getId() + "\n" + "Reason: " + explanation);
                } else {
                	explanation = Constants.RELATIONSHIP_LABEL_SEMANTIC;
				}
                
                VPMEdgeDescriptor descriptor = buildEdgeDescriptor(sourceNode, targetNode, explanation);
                if (descriptor != null) {
                    result.getEdgeDescriptors().add(descriptor);
                }
            }
        }
    }
}

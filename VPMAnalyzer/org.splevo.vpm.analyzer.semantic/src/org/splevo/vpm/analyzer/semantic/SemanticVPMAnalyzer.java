package org.splevo.vpm.analyzer.semantic;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
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
import org.splevo.vpm.analyzer.semantic.lucene.RelationShipSearchConfiguration;
import org.splevo.vpm.analyzer.semantic.lucene.Searcher;
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
	
	/** The relationship label of the analyzer. */
    private static final String RELATIONSHIP_LABEL_SEMANTIC = "Semantic";

    /** The displayed name of the analyzer. */
    private static final String DISPLAYED_NAME = "Semantic VPM Analyzer";
    
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
        
        VPMAnalyzerResult result = null;

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
            VPLinkContainer similars = findRelationships(vpmGraph);
            result = addSimilarsToAnalyzerResultSet(vpmGraph, similars);
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
        Map<String, ConfigDefinition> availableConfigurations = new LinkedHashMap<String, ConfigDefinition>();
        
        availableConfigurations.put(ConfigDefaults.LABEL_INCLUDE_COMMENTS, 
        		new BooleanChoiceConfigDefintion(ConfigDefaults.DEFAULT_INCLUDE_COMMENTS));
        availableConfigurations.put(ConfigDefaults.LABEL_STOP_WORDS, 
        		new StringConfigDefinition(
        				Arrays.deepToString(ConfigDefaults.DEFAULT_STOP_WORDS).replace(", ", " ").replace("[", "").replace("]", "")));
        availableConfigurations.put(ConfigDefaults.LABEL_SPLIT_CAMEL_CASE, 
        		new BooleanChoiceConfigDefintion(ConfigDefaults.DEFAULT_SPLIT_CAMEL_CASE));
        
        availableConfigurations.put(ConfigDefaults.LABEL_USE_OVERALL_SIMILARITY_FINDER, 
        		new BooleanChoiceConfigDefintion(ConfigDefaults.DEFAULT_USE_OVERALL_SIMILARITY_FINDER));
        availableConfigurations.put(ConfigDefaults.LABEL_MINIMUM_SIMILARITY, 
        		new DoubleConfigDefinition(ConfigDefaults.DEFAULT_OVERALL_MINIMUM_SIMILARITY));
        
        availableConfigurations.put(ConfigDefaults.LABEL_USE_RARE_FINDER, 
        		new BooleanChoiceConfigDefintion(ConfigDefaults.DEFAULT_USE_RARE_FINDER));
        availableConfigurations.put(ConfigDefaults.LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE, 
        		new DoubleConfigDefinition(ConfigDefaults.DEFAULT_RARE_TERM_MAX_PERCENTAGE));
        
        availableConfigurations.put(ConfigDefaults.LABEL_USE_TOP_N_TERM_FINDER, 
        		new BooleanChoiceConfigDefintion(ConfigDefaults.DEFAULT_TOP_N_TERM_FINDER));        
        availableConfigurations.put(ConfigDefaults.LABEL_LEAST_DOC_FREQ, 
        		new DoubleConfigDefinition(ConfigDefaults.DEFAULT_LEAST_DOC_FREQ));
        availableConfigurations.put(ConfigDefaults.LABEL_N, 
        		new IntegerConfigDefinition(ConfigDefaults.DEFAULT_N));

        return availableConfigurations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getConfigurationLabels()
     */
    @Override
    public Map<String, String> getConfigurationLabels() {
        Map<String, String> configurationLabels = new LinkedHashMap<String, String>();
        configurationLabels.put(ConfigDefaults.LABEL_INCLUDE_COMMENTS, 
        		ConfigDefaults.LABEL_INCLUDE_COMMENTS);
        configurationLabels.put(ConfigDefaults.LABEL_STOP_WORDS, 
        		ConfigDefaults.LABEL_STOP_WORDS);
        configurationLabels.put(ConfigDefaults.LABEL_SPLIT_CAMEL_CASE, 
        		ConfigDefaults.LABEL_SPLIT_CAMEL_CASE);
        
        configurationLabels.put(ConfigDefaults.LABEL_USE_OVERALL_SIMILARITY_FINDER, 
        		ConfigDefaults.LABEL_USE_OVERALL_SIMILARITY_FINDER);
        configurationLabels.put(ConfigDefaults.LABEL_MINIMUM_SIMILARITY, 
        		ConfigDefaults.LABEL_MINIMUM_SIMILARITY);
        
        configurationLabels.put(ConfigDefaults.LABEL_USE_RARE_FINDER, 
        		ConfigDefaults.LABEL_USE_RARE_FINDER);
        configurationLabels.put(ConfigDefaults.LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE, 
        		ConfigDefaults.LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE);
        
        configurationLabels.put(ConfigDefaults.LABEL_USE_TOP_N_TERM_FINDER, 
        		ConfigDefaults.LABEL_USE_TOP_N_TERM_FINDER);
        configurationLabels.put(ConfigDefaults.LABEL_LEAST_DOC_FREQ, 
        		ConfigDefaults.LABEL_LEAST_DOC_FREQ);
        configurationLabels.put(ConfigDefaults.LABEL_N, 
        		ConfigDefaults.LABEL_N);
        
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
        return DISPLAYED_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getRelationshipLabel()
     */
    @Override
    public String getRelationshipLabel() {
        return RELATIONSHIP_LABEL_SEMANTIC;
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
        boolean indexComments = (Boolean) configurations.get(ConfigDefaults.LABEL_INCLUDE_COMMENTS);
        boolean splitCamelCase = (Boolean) configurations.get(ConfigDefaults.LABEL_SPLIT_CAMEL_CASE); 
        String stopWords = (String) configurations.get(ConfigDefaults.LABEL_STOP_WORDS);
        
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
        
        // Get content and comment from switch.
        String content = indexASTNodeSwitch.getContent();
        String comment = indexASTNodeSwitch.getComments();
    	
        // Add to index.
        try {
        	this.indexer.addToIndex(id, content, comment);
		} catch (IOException e) {
			logger.error("Failure while adding node to index.", e);
		}
    	
    }

    /**
     * Finds semantic relationships between the variation points.
     * 
     * @param graph The {@link VPMGraph} to extract the IDs of the result nodes from.
     * @return A {@link VPLinkContainer} containing the search results.
     * @throws IOException
     *             Throws an {@link IOException} when there is already an open {@link IndexWriter}.
     */
    private VPLinkContainer findRelationships(VPMGraph graph) throws IOException {
        if (graph == null) {
            throw new IllegalArgumentException();
        }
        
        // Get the configurations
        boolean includeComments = (Boolean) configurations.get(ConfigDefaults.LABEL_INCLUDE_COMMENTS);
        boolean useRareFinder = (Boolean) configurations.get(ConfigDefaults.LABEL_USE_RARE_FINDER);
        boolean useOverallSimFinder = (Boolean) configurations.get(ConfigDefaults.LABEL_USE_OVERALL_SIMILARITY_FINDER);
        boolean useTopNTermFinder = (Boolean) configurations.get(ConfigDefaults.LABEL_USE_TOP_N_TERM_FINDER);
        Double minSimilarity = (Double) configurations.get(ConfigDefaults.LABEL_MINIMUM_SIMILARITY);
        Double maxPercentage = (Double) configurations.get(ConfigDefaults.LABEL_RARE_TERM_FINDER_MAX_PERCENTAGE);
        Double leastDocFreq = (Double) configurations.get(ConfigDefaults.LABEL_LEAST_DOC_FREQ);
        Integer n = (Integer) configurations.get(ConfigDefaults.LABEL_N);
        
        // Setup the config object.
        RelationShipSearchConfiguration searchConfig = new RelationShipSearchConfiguration();
        searchConfig.useComments(includeComments);
        searchConfig.configureOverallFinder(useOverallSimFinder, minSimilarity);
        searchConfig.configureRareTermFinder(useRareFinder, maxPercentage);
        searchConfig.configureTopNFinder(useTopNTermFinder, leastDocFreq, n);
        
        // Use the searcher to search for semantic relationships.
        return Searcher.findSemanticRelationships(searchConfig);
    }

	/**
	 * Transforms the links from the {@link VPLinkContainer} to {@link VPMAnalyzerResult}. 
	 * 
	 * @param graph The related graph.
	 * @param similars The search results.
	 * @return A {@link VPMAnalyzerResult} containing the edge descriptors.
	 */
	private VPMAnalyzerResult addSimilarsToAnalyzerResultSet(VPMGraph graph,
			VPLinkContainer similars) {
        VPMAnalyzerResult result = new VPMAnalyzerResult(this);
		for (String key : similars.getAllLinks().keySet()) {
            Set<String> values = similars.getAllLinks().get(key);

            for (String value : values) {
                Node sourceNode = graph.getNode(key);
                Node targetNode = graph.getNode(value);
                String[] explanations = similars.getExplanations(key, value);
                
                if (explanations == null) {
                	explanations = new String[]{RELATIONSHIP_LABEL_SEMANTIC};
				}
                
                for (String explanation : explanations) {
                	logAnalysisInfo(sourceNode.getId(), targetNode.getId(), "", "", explanation);
				}                
                
                VPMEdgeDescriptor descriptor = buildEdgeDescriptor(sourceNode, targetNode, Arrays.deepToString(explanations));
                if (descriptor != null) {
                    result.getEdgeDescriptors().add(descriptor);
                }
            }
        }
		return result;
	}
}

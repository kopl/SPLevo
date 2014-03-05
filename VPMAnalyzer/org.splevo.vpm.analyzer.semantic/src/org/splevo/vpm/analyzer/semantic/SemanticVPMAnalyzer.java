package org.splevo.vpm.analyzer.semantic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.BooleanConfiguration;
import org.splevo.vpm.analyzer.config.NumericConfiguration;
import org.splevo.vpm.analyzer.config.StringConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContent;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProvider;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProviderRegistry;
import org.splevo.vpm.analyzer.semantic.extensionpoint.UnsupportedSoftwareElementException;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;
import org.splevo.vpm.analyzer.semantic.lucene.RelationShipSearchConfiguration;
import org.splevo.vpm.analyzer.semantic.lucene.Searcher;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.VariationPoint;

/**
 * <h1>What it does</h1>
 * <p>
 * The semantic relationship VPMAnalazer analyzer is able to find semantic relationships between
 * several {@link VariationPoint}s. Several configurations allow a customized search, just as
 * needed.
 * </p>
 *
 * <h1>How does that work?</h1>
 * <p>
 * As a first step, the analyzer extracts all relevant content from a VPMGraph and stores that
 * within a Lucene index. Through storing additional informations about the indexed text, Lucene
 * provides the ability to extract vectors from given index content. The Analyzer uses several
 * Finders to search for semantic dependencies. Those results can be displayed within the VPMGraph
 * or the Refinement Browser.
 * </p>
 *
 * @author Daniel Kojic
 *
 */
public class SemanticVPMAnalyzer extends AbstractVPMAnalyzer {

    /** Group identifier for general configurations. */
    public static final String CONFIG_GROUP_GENERAL = "General Configuations";

    /** Group Identifier for basic shared term analyzes. */
    public static final String CONFIG_GROUP_OVERALL_SIMILARITY = "Overall Similarity Search";

    /** Group Identifier for least frequent term searches. */
    public static final String CONFIG_GROUP_IMPORTANT_TERM = "Important Term Search";

    /** Group Identifier for most frequent term searches. */
    public static final String CONFIG_GROUP_TOP_N = "Top N Term Search";

    private static final String CONFIG_ID_BASE = "org.splevo.vpm.analyzer.semantic";

    /** Identifier for the configuration to include comments in the analysis. */
    public static final String CONFIG_ID_INCLUDE_COMMENTS = CONFIG_ID_BASE + "INCLUDE_COMMENTS";

    /** Identifier for the configuration to split camel case terms. */
    public static final String CONFIG_ID_SPLIT_CAMEL_CASE = CONFIG_ID_BASE + "SPLIT_CAMEL_CASE";

    /** Identifier for the configuration of the stop words to filter. */
    public static final String CONFIG_ID_STOP_WORDS = CONFIG_ID_BASE + "STOP_WORDS";

    /** Identifier for the configuration to use the overall similarity finder. */
    public static final String CONFIG_ID_USE_OVERALL_SIMILARITY_FINDER = CONFIG_ID_BASE
            + "USE_OVERALL_SIMILARITY_FINDER";

    /** Identifier for the configuration to use the important term finder. */
    public static final String CONFIG_ID_USE_IMPORTANT_TERM_FINDER = CONFIG_ID_BASE + "USE_IMPORTANT_TERM_FINDER";

    /** Identifier for the configuration to use the top term finder. */
    public static final String CONFIG_ID_USE_TOP_N_TERM_FINDER = CONFIG_ID_BASE + "USE_TOP_N_TERM_FINDER";

    /** Identifier for the configuration of the minimum similarity. */
    public static final String CONFIG_ID_MIN_SIMILARITY = CONFIG_ID_BASE + "MIN_SIMILARITY";

    /** Identifier for the configuration to use the similarity measure. */
    public static final String CONFIG_ID_USE_SIMILARITY_MEASURE = CONFIG_ID_BASE + "USE_SIMILARITY_MEASURE";

    /** Identifier for the configuration of .... */
    public static final String CONFIG_ID_LEAST_DOC_FREQ = CONFIG_ID_BASE + "LEAST_DOC_FREQ";

    /** Identifier for the configuration of .... */
    public static final String CONFIG_ID_N = CONFIG_ID_BASE + "N";

    /** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_SEMANTIC = "Semantic";

    /** The displayed name of the analyzer. */
    private static final String DISPLAYED_NAME = "Semantic VPM Analyzer";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SemanticVPMAnalyzer.class);

    /** The indexer instance. */
    private Indexer indexer;

    /** The configuration-object for the include comments configuration. */
    private BooleanConfiguration includeCommentsConfig;

    /** The configuration-object for the split on case change configuration. */
    private BooleanConfiguration splitCamelCaseConfig;

    /** The configuration-object for the stop words configuration. */
    private StringConfiguration stopWordsConfig;

    /** The configuration-object for the use overall sim finder configuration. */
    private BooleanConfiguration useOverallSimFinderConfig;

    /**
     * The configuration-object for the use important term finder configuration.
     */
    private BooleanConfiguration useimportantTermFinderConfig;

    /** The configuration-object for the use top n finder configuration. */
    private BooleanConfiguration useTopNFinderConfig;

    /** The configuration-object for the minimum similarity configuration. */
    private NumericConfiguration minSimConfig;

    /** The configuration-object for the minimum similarity configuration. */
    private BooleanConfiguration oneCommonTermConfig;

    /** The configuration-object for the least document frequency configuration. */
    private NumericConfiguration topNLeastDocFreqConfig;

    /** The configuration-object for the N configuration. */
    private NumericConfiguration topNNConfig;

    /**
     * The default constructor for this class.
     */
    public SemanticVPMAnalyzer() {
        indexer = Indexer.getInstance();

        includeCommentsConfig = new BooleanConfiguration(CONFIG_ID_INCLUDE_COMMENTS,
                ConfigDefaults.LABEL_INCLUDE_COMMENTS, null, ConfigDefaults.DEFAULT_INCLUDE_COMMENTS);
        splitCamelCaseConfig = new BooleanConfiguration(CONFIG_ID_SPLIT_CAMEL_CASE,
                ConfigDefaults.LABEL_SPLIT_CAMEL_CASE, null, ConfigDefaults.DEFAULT_SPLIT_CAMEL_CASE);
        stopWordsConfig = new StringConfiguration(CONFIG_ID_STOP_WORDS, ConfigDefaults.LABEL_STOP_WORDS,
                ConfigDefaults.EXPL_STOP_WORDS, ConfigDefaults.DEFAULT_STOP_WORDS);

        useOverallSimFinderConfig = new BooleanConfiguration(CONFIG_ID_USE_OVERALL_SIMILARITY_FINDER,
                ConfigDefaults.LABEL_USE_OVERALL_SIMILARITY_FINDER, ConfigDefaults.EXPL_OVERALL_SIMILARITY_FINDER,
                ConfigDefaults.DEFAULT_USE_OVERALL_SIMILARITY_FINDER);
        useimportantTermFinderConfig = new BooleanConfiguration(CONFIG_ID_USE_IMPORTANT_TERM_FINDER,
                ConfigDefaults.LABEL_USE_IMPORTANT_TERM_FINDER, ConfigDefaults.EXPL_IMPORTANT_TERM_FINDER,
                ConfigDefaults.DEFAULT_USE_IMPORTANT_TERM_FINDER);
        useTopNFinderConfig = new BooleanConfiguration(CONFIG_ID_USE_TOP_N_TERM_FINDER,
                ConfigDefaults.LABEL_USE_TOP_N_TERM_FINDER, ConfigDefaults.EXPL_TOP_N_TERM_FINDER,
                ConfigDefaults.DEFAULT_USE_TOP_N_TERM_FINDER);

        minSimConfig = new NumericConfiguration(CONFIG_ID_MIN_SIMILARITY, ConfigDefaults.LABEL_MIN_SIMILARITY,
                ConfigDefaults.EXPL_MIN_SIMILARITY, ConfigDefaults.DEFAULT_MIN_SIMILARITY, 0.01d, 0.d, 1.d, 2);

        oneCommonTermConfig = new BooleanConfiguration(CONFIG_ID_USE_SIMILARITY_MEASURE,
                ConfigDefaults.LABEL_USE_SIMILARITY_MEASURE, ConfigDefaults.EXPL_USE_SIMILARITY_MEASURE,
                ConfigDefaults.DEFAULT_USE_SIMILARITY_MEASURE);

        topNLeastDocFreqConfig = new NumericConfiguration(CONFIG_ID_LEAST_DOC_FREQ,
                ConfigDefaults.LABEL_LEAST_DOC_FREQ, ConfigDefaults.EXPL_LEAST_DOC_FREQ,
                ConfigDefaults.DEFAULT_LEAST_DOC_FREQ, 0.01d, 0.d, 1.d, 2);
        topNNConfig = new NumericConfiguration(CONFIG_ID_N, ConfigDefaults.LABEL_N, ConfigDefaults.EXPL_N,
                ConfigDefaults.DEFAULT_N, 1.d, 1.d, 100.d, 0);
    }

    @Override
    public VPMAnalyzerResult analyze(VPMGraph vpmGraph) {
        if (vpmGraph == null) {
            throw new IllegalArgumentException();
        }

        VPMAnalyzerResult result = null;

        if (vpmGraph.getNodeCount() == 0) {
            logger.info("Got empty VPM Graph. No analysis executed.");
            return result;
        }

        // Fill the index.
        try {
            logger.info("Filling index...");
            fillIndex(vpmGraph);
            logger.info("Indexing done.");
        } catch (Exception e) {
            logger.error("Cannot write Index. Close all open IndexWriters first.", e);
        }

        // Find relationships.
        try {
            logger.info("Analyzing...");
            VPLinkContainer similars = findRelationships(vpmGraph);
            result = addSimilarsToAnalyzerResultSet(vpmGraph, similars);
            logger.info("Analysis done.");
        } catch (IOException e) {
            logger.error("Cannot read Index. Close all open IndexWriters first.", e);
        }

        // CLEAN-UP.
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
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getConfigurations()
     */
    @Override
    public VPMAnalyzerConfigurationSet getConfigurations() {
        VPMAnalyzerConfigurationSet configurations = new VPMAnalyzerConfigurationSet();
        configurations.addConfigurations(CONFIG_GROUP_GENERAL, includeCommentsConfig, splitCamelCaseConfig,
                stopWordsConfig);
        configurations.addConfigurations(CONFIG_GROUP_OVERALL_SIMILARITY, useOverallSimFinderConfig,
                oneCommonTermConfig, minSimConfig);
        configurations.addConfigurations(CONFIG_GROUP_IMPORTANT_TERM, useimportantTermFinderConfig);
        configurations.addConfigurations(CONFIG_GROUP_TOP_N, useTopNFinderConfig, topNLeastDocFreqConfig, topNNConfig);

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
        boolean indexComments = includeCommentsConfig.getCurrentValue();
        boolean splitCamelCase = splitCamelCaseConfig.getCurrentValue();
        String stopWords = stopWordsConfig.getCurrentValue();

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
            indexNode(node.getId(), vp, indexComments);
        }
    }

    /**
     * This method uses the IndexASTNodeSwitch to extract the text from the given Node. It iterates
     * through all child elements.
     *
     * @param id
     *            The ID used to store the text in the Lucene index.
     * @param vp
     *            The corresponding {@link VariationPoint}.
     * @param matchComments
     *            Determines if comments should be indexed or ignored.
     */
    private void indexNode(String id, VariationPoint vp, boolean matchComments) {
        if (id == null || vp == null) {
            throw new IllegalArgumentException();
        }

        SoftwareElement softwareElement = vp.getEnclosingSoftwareEntity();
        if (softwareElement == null) {
            throw new IllegalStateException();
        }

        List<String> code = new LinkedList<String>();
        List<String> comments = new LinkedList<String>();
        List<SemanticContentProvider> semanticContentProviders = SemanticContentProviderRegistry.getContentProviders();
        for (SemanticContentProvider semanticContentProvider : semanticContentProviders) {
            SemanticContent relevantContent;
            try {
                relevantContent = semanticContentProvider.getRelevantContent(softwareElement, matchComments);
            } catch (UnsupportedSoftwareElementException e) {
                continue;
            }
            code.addAll(relevantContent.getCode());
            comments.addAll(relevantContent.getComments());
        }

        // Get content and comment from switch.
        String codeString = convertListToString(code);
        String commentString = convertListToString(comments);

        // Add to index.
        try {
            this.indexer.addToIndex(id, codeString, commentString);
        } catch (IOException e) {
            logger.error("Failure while adding node to index.", e);
        }

    }

    /**
     * Transforms a list that stores strings to a string.
     *
     * @param list
     *            The list.
     * @return The string representation.
     */
    private String convertListToString(List<String> list) {
        return Arrays.toString(list.toArray()).replaceAll("\\[|\\]", "").replaceAll(", ", " ");
    }

    /**
     * Finds semantic relationships between the variation points.
     *
     * @param graph
     *            The {@link VPMGraph} to extract the IDs of the result nodes from.
     * @return A {@link VPLinkContainer} containing the search results.
     * @throws IOException
     *             Throws an {@link IOException} when there is already an open {@link IndexWriter}.
     */
    private VPLinkContainer findRelationships(VPMGraph graph) throws IOException {
        if (graph == null) {
            throw new IllegalArgumentException();
        }

        // Get the configurations
        boolean includeComments = includeCommentsConfig.getCurrentValue();
        boolean useRareFinder = useimportantTermFinderConfig.getCurrentValue();
        boolean useOverallSimFinder = useOverallSimFinderConfig.getCurrentValue();
        boolean useTopNTermFinder = useTopNFinderConfig.getCurrentValue();
        Double minSimilarity = minSimConfig.getCurrentValue();
        boolean oneCommonTerm = oneCommonTermConfig.getCurrentValue();
        Double leastDocFreq = topNLeastDocFreqConfig.getCurrentValue();
        Integer n = topNNConfig.getIntegerValue();

        // Setup the configuration object.
        RelationShipSearchConfiguration searchConfig = new RelationShipSearchConfiguration();
        searchConfig.useComments(includeComments);
        searchConfig.configureOverallFinder(useOverallSimFinder, oneCommonTerm, minSimilarity);
        searchConfig.configureImportantTermFinder(useRareFinder);
        searchConfig.configureTopNFinder(useTopNTermFinder, leastDocFreq, n);

        // Use the searcher to search for semantic relationships.
        return Searcher.findSemanticRelationships(searchConfig);
    }

    /**
     * Transforms the links from the {@link VPLinkContainer} to {@link VPMAnalyzerResult}.
     *
     * @param graph
     *            The related graph.
     * @param similars
     *            The search results.
     * @return A {@link VPMAnalyzerResult} containing the edge descriptors.
     */
    private VPMAnalyzerResult addSimilarsToAnalyzerResultSet(VPMGraph graph, VPLinkContainer similars) {
        VPMAnalyzerResult result = new VPMAnalyzerResult(this);
        List<String> edgeRegistry = new ArrayList<String>();
        for (String key : similars.getAllLinks().keySet()) {
            Set<String> values = similars.getAllLinks().get(key);

            for (String value : values) {
                Node sourceNode = graph.getNode(key);
                Node targetNode = graph.getNode(value);
                String[] explanations = similars.getExplanations(key, value);

                if (explanations == null) {
                    explanations = new String[] { RELATIONSHIP_LABEL_SEMANTIC };
                }

                for (String explanation : explanations) {
                    logAnalysisInfo(sourceNode.getId(), targetNode.getId(), "", "", explanation);
                }

                VPMEdgeDescriptor descriptor = buildEdgeDescriptor(sourceNode, targetNode,
                        Arrays.deepToString(explanations), edgeRegistry);
                if (descriptor != null) {
                    result.getEdgeDescriptors().add(descriptor);
                }
            }
        }
        return result;
    }
}

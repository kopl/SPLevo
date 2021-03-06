/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and/or initial documentation
 *    Benjamin Klatt
 *    Christian Busch
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.SlowCompositeReaderWrapper;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.util.BytesRef;
import org.graphstream.graph.Node;
import org.splevo.vpm.analyzer.AbstractVPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerResult;
import org.splevo.vpm.analyzer.VPMEdgeDescriptor;
import org.splevo.vpm.analyzer.config.BooleanConfiguration;
import org.splevo.vpm.analyzer.config.ChoiceConfiguration;
import org.splevo.vpm.analyzer.config.NumericConfiguration;
import org.splevo.vpm.analyzer.config.Range;
import org.splevo.vpm.analyzer.config.StringConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;
import org.splevo.vpm.analyzer.graph.VPMGraph;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContent;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProvider;
import org.splevo.vpm.analyzer.semantic.extensionpoint.SemanticContentProviderRegistry;
import org.splevo.vpm.analyzer.semantic.extensionpoint.UnsupportedSoftwareElementException;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;
import org.splevo.vpm.analyzer.semantic.lucene.Stemming;
import org.splevo.vpm.analyzer.semantic.lucene.finder.SharedTermFinder;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

/**
 * <h2>What it does</h2>
 * <p>
 * The semantic relationship VPMAnalazer analyzer is able to find semantic relationships between
 * several {@link VariationPoint}s. Several configurations allow a customized search, just as
 * needed.
 * </p>
 *
 * <h2>How it works</h2>
 * <p>
 * As a first step, the analyzer extracts all relevant content from a VPMGraph and stores that
 * within a Lucene index. Through storing additional informations about the indexed text, Lucene
 * provides the ability to extract vectors from given index content. The Analyzer uses several
 * Finders to search for semantic dependencies. Those results can be displayed within the VPMGraph
 * or the Refinement Browser.
 * </p>
 */
public class SemanticVPMAnalyzer extends AbstractVPMAnalyzer {

    /** The relationship label of the analyzer. */
    private static final String RELATIONSHIP_LABEL_SEMANTIC = "Semantic";

    /** The displayed name of the analyzer. */
    private static final String DISPLAYED_NAME = "Semantic VPM Analyzer";

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SemanticVPMAnalyzer.class);

    /** The indexer instance. */
    private Indexer indexer = Indexer.getInstance();

    /** The configuration-object for the include comments configuration. */
    private BooleanConfiguration includeCommentsConfig = new BooleanConfiguration(Config.CONFIG_ID_INCLUDE_COMMENTS,
            Config.LABEL_INCLUDE_COMMENTS, null, Config.DEFAULT_INCLUDE_COMMENTS);

    /** The configuration-object for the split on case change configuration. */
    private BooleanConfiguration splitCamelCaseConfig = new BooleanConfiguration(Config.CONFIG_ID_SPLIT_CAMEL_CASE,
            Config.LABEL_SPLIT_CAMEL_CASE, null, Config.DEFAULT_SPLIT_CAMEL_CASE);

    /** The configuration-object for the stemming to be used. */
    private ChoiceConfiguration stemmingConfig = new ChoiceConfiguration(Config.CONFIG_ID_STEMMING,
            Config.LABEL_STEMMING, null, Config.DEFAULT_STEMMING, Config.AVAILABLEVALUES_STEMMING);

    /** The configuration-object for the stop words configuration. */
    private StringConfiguration stopWordsConfig = new StringConfiguration(Config.CONFIG_ID_STOP_WORDS,
            Config.LABEL_STOP_WORDS, Config.EXPL_STOP_WORDS, Config.DEFAULT_STOP_WORDS_HOST, false);

    /** The configuration-object for the minimum number of shared terms configuration. */
    private NumericConfiguration minSharedTermConfig = new NumericConfiguration(Config.CONFIG_ID_SHARED_TERM_MINIMUM,
            Config.LABEL_SHARED_TERM_MINIMUM, Config.EXPL_SHARED_TERM_MINIMUM, Config.DEFAULT_SHARED_TERM_MINIMUM, 1,
            new Range<Integer>(1, Integer.MAX_VALUE));

    /** The configuration-object for the log indexed terms configuration. */
    private StringConfiguration logIndexedTermsConfig = new StringConfiguration(Config.CONFIG_ID_LOG_INDEXED_TERMS,
            Config.LABEL_LOG_INDEXED_TERMS, Config.EXPL_LOG_INDEXED_TERMS, Config.DEFAULT_LOG_INDEXED_TERMS, true);

    /** The configuration-object for the feature term configuration. */
    private StringConfiguration featureTermConfig = new StringConfiguration(Config.CONFIG_ID_FEATURE_TERMS,
            Config.LABEL_FEATURE_TERMS, Config.EXPL_FEATURE_TERMS, Config.DEFAULT_FEATURE_TERMS, false);

    /** The configuration-object for the featured terms only configuration. */
    private BooleanConfiguration featuredTermsOnlyConfig = new BooleanConfiguration(
            Config.CONFIG_ID_FEATURE_TERMS_ONLY, Config.LABEL_FEATURE_TERMS_ONLY, Config.EXPL_FEATURE_TERMS_ONLY,
            Config.DEFAULT_FEATURE_TERMS_ONLY);

    /** The configuration-object for the similar term sets only configuration. */
    private BooleanConfiguration similarTermSetOnlyConfig = new BooleanConfiguration(
            Config.CONFIG_ID_SIMILAR_TERM_SET_ONLY, Config.LABEL_SIMILAR_TERM_SET_ONLY,
            Config.EXPL_SIMILAR_TERM_SET_ONLY, Config.DEFAULT_SIMILAR_TERM_SET_ONLY);

    /** The configuration-object for the one shared term only configuration. */
    private BooleanConfiguration oneSharedTermOnlyConfig = new BooleanConfiguration(
            Config.CONFIG_ID_ONE_SHARED_TERM_ONLY, Config.LABEL_ONE_SHARED_TERM_ONLY, Config.EXPL_ONE_SHARED_TERM_ONLY,
            Config.DEFAULT_ONE_SHARED_TERM_ONLY);

    @Override
    public VPMAnalyzerResult analyze(VPMGraph vpmGraph) {
        if (vpmGraph == null) {
            throw new IllegalArgumentException();
        }
        if (vpmGraph.getNodeCount() == 0) {
            logger.info("Got empty VPM Graph. No analysis executed.");
            return null;
        }

        // Fill the index.
        try {
            fillIndex(vpmGraph);
        } catch (Exception e) {
            logger.error("Cannot write Index. Close all open IndexWriters first.", e);
            return null;
        }

        if (!Strings.isNullOrEmpty(logIndexedTermsConfig.getCurrentValue())) {
            logIndexedTerms();
        }

        // Find relationships.
        VPMAnalyzerResult result = null;
        try {
            result = findRelationships(vpmGraph);
        } catch (IOException e) {
            logger.error("Cannot read Index. Close all open IndexWriters first.", e);
        } finally {
            cleanUp();
        }

        return result;
    }

    private void logIndexedTerms() {

        Map<String, Integer> indexedTerms = getTermsFromIndex();

        List<String> lines = Lists.newLinkedList();
        lines.add("Term,VPCount");
        for (String term : indexedTerms.keySet()) {
            lines.add(term + "," + indexedTerms.get(term));
        }

        String logDirectory = getCurrentLogSubDirectory();
        File logFile = new File(logDirectory + "indexed-terms.csv");

        try {
            FileUtils.writeLines(logFile, lines);
        } catch (IOException e) {
            logger.error("Failed to write term log", e);
        }
    }

    /**
     * Get the current run specific log directory. A timestamp specific sub directory of the
     * configured path will be used.
     *
     * @return The base log directory concatenated with a timestamp specific sub directory.
     */
    private String getCurrentLogSubDirectory() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        Calendar cal = Calendar.getInstance();
        String timeStamp = dateFormat.format(cal.getTime());
        String logDirectory = logIndexedTermsConfig.getCurrentValue() + File.separator + timeStamp + File.separator;
        return logDirectory;
    }

    private void cleanUp() {
        try {
            Indexer.getInstance().clearIndex();
        } catch (IOException e) {
            logger.error("Failure while trying to empty main index.", e);
        }
    }

    private Map<String, Integer> getTermsFromIndex() {
        Map<String, Integer> indexedTerms = Maps.newLinkedHashMap();
        try {
            DirectoryReader indexReader = indexer.getIndexReader();
            Terms terms = SlowCompositeReaderWrapper.wrap(indexReader).terms(Indexer.INDEX_CONTENT);
            if (terms == null) {
                return indexedTerms;
            }

            TermsEnum termEnum = terms.iterator(null);
            BytesRef byteRef = null;

            while ((byteRef = termEnum.next()) != null) {
                String term = byteRef.utf8ToString();
                int count = indexReader.docFreq(new Term(Indexer.INDEX_CONTENT, byteRef));
                indexedTerms.put(term, Integer.valueOf(count));
            }
            indexReader.close();
        } catch (Exception e) {
            logger.error("Failed to dump index", e);
        }
        return indexedTerms;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.splevo.vpm.analyzer.VPMAnalyzer#getConfigurations()
     */
    @Override
    public VPMAnalyzerConfigurationSet getConfigurations() {
        VPMAnalyzerConfigurationSet configurations = new VPMAnalyzerConfigurationSet();
        configurations.addConfigurations(Config.CONFIG_GROUP_GENERAL, stopWordsConfig, stemmingConfig,
                similarTermSetOnlyConfig, oneSharedTermOnlyConfig);
        configurations
                .addConfigurations(Config.CONFIG_GROUP_FEATURED_TERMS, featuredTermsOnlyConfig, featureTermConfig);
        configurations.addConfigurations(Config.CONFIG_GROUP_SHARED_TERM_FINDER, includeCommentsConfig,
                splitCamelCaseConfig, minSharedTermConfig, logIndexedTermsConfig);
        return configurations;
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
        String featureTerms = featureTermConfig.getCurrentValue();
        boolean featuredTermsOnly = featuredTermsOnlyConfig.getCurrentValue();

        String stemmingString = stemmingConfig.getCurrentValue();
        Stemming stemming = Stemming.valueOf(stemmingString);

        this.indexer.setSplitCamelCase(splitCamelCase);
        this.indexer.setStemming(stemming);

        if (stopWords != null) {
            this.indexer.setStopWords(stopWords.split(" "));
        }

        if (featureTerms != null) {
            if (featureTerms.length() > 0) {
                this.indexer.setFeatureTermSet(new HashSet<String>(Arrays.asList(featureTerms.split(" "))));                
            } else {
                this.indexer.setFeatureTermSet(new HashSet<String>());
            }
        }
        this.indexer.setFeaturedTermsOnly(featuredTermsOnly);

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

        List<String> codeTerms = Lists.newLinkedList();
        List<String> comments = Lists.newLinkedList();

        for (Variant variant : vp.getVariants()) {
            for (SoftwareElement softwareElement : variant.getImplementingElements()) {
                loadTermsForSoftwareElement(matchComments, softwareElement, codeTerms, comments);
            }
        }

        // Get content and comment from switch.
        String codeString = convertListToString(codeTerms);
        String commentString = convertListToString(comments);

        // Add to index.
        try {
            this.indexer.addToIndex(id, codeString, commentString);
        } catch (IOException e) {
            logger.error("Failure while adding node to index.", e);
        }

    }

    /**
     * Get the semantic relevant terms for a software element from the registered semantic content
     * providers and store them in the code respectively comment lists.
     *
     * @param matchComments
     *            The flag if comments should be considered.
     * @param softwareElement
     *            The software element to get the terms for.
     * @param codeTerms
     *            The list to store code terms in.
     * @param commentTerms
     *            The list to store comment terms in. Nothing added if comments not considered.
     */
    private void loadTermsForSoftwareElement(boolean matchComments, SoftwareElement softwareElement,
            List<String> codeTerms, List<String> commentTerms) {
        List<SemanticContentProvider> semanticContentProviders = SemanticContentProviderRegistry.getInstance().getElements();
        for (SemanticContentProvider semanticContentProvider : semanticContentProviders) {
            SemanticContent relevantContent;
            try {
                relevantContent = semanticContentProvider.getRelevantContent(softwareElement, matchComments);
            } catch (UnsupportedSoftwareElementException e) {
                continue;
            }
            codeTerms.addAll(relevantContent.getCode());
            commentTerms.addAll(relevantContent.getComments());
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
        return Joiner.on(" ").skipNulls().join(list);
    }

    /**
     * Finds semantic relationships between the variation points.
     *
     * @param graph
     *            The {@link VPMGraph} to extract the IDs of the result nodes from.
     * @return A {@link VPMAnalyzerResult} containing the search results.
     * @throws IOException
     *             Throws an {@link IOException} when there is already an open index writer.
     */
    private VPMAnalyzerResult findRelationships(VPMGraph graph) throws IOException {

        // Get the configurations
        boolean includeComments = includeCommentsConfig.getCurrentValue();
        int minSharedTerms = 1;
        if (minSharedTermConfig.getCurrentValue() != null) {
            minSharedTerms = minSharedTermConfig.getCurrentValue().intValue();
        }

        DirectoryReader reader = Indexer.getInstance().getIndexReader();
        SharedTermFinder finder = new SharedTermFinder(reader, includeComments, minSharedTerms);
        Table<String, String, Set<String>> sharedTermTable = finder.findSimilarEntries();
        reader.close();

        Set<String> vpFilter = buildImpreciseVPFilter(sharedTermTable);

        VPMAnalyzerResult result = new VPMAnalyzerResult(this);
        ArrayList<String> edgeRegistry = Lists.newArrayList();
        for (Cell<String, String, Set<String>> cell : sharedTermTable.cellSet()) {
            String id1 = cell.getRowKey();
            String id2 = cell.getColumnKey();

            if (vpFilter.contains(id1) || vpFilter.contains(id2)) {
                continue;
            }

            Set<String> sharedTerms = cell.getValue();

            if (sharedTerms.size() >= minSharedTerms) {
                Node node1 = graph.getNode(id1);
                Node node2 = graph.getNode(id2);
                String subLabel = convertListToString(Lists.newArrayList(sharedTerms));

                VPMEdgeDescriptor edge = buildEdgeDescriptor(node1, node2, subLabel, edgeRegistry);
                if (edge != null) {
                    logAnalysisInfo(id1, id2, "", "", String.format("Shared terms: %s", subLabel));
                    result.getEdgeDescriptors().add(edge);
                }
            }
        }
        return result;
    }

    /**
     * Build filter for variation points not sharing the same set of terms with all of it's
     * connected vps.
     *
     * If configured, also variation points sharing more than one term are included in the filter.
     *
     * @param sharedTermTable
     *            The table of variation points, there referenced other VPs and the terms they
     *            share.
     * @return The set of VP ids to not register any relationships for.
     */
    private Set<String> buildImpreciseVPFilter(Table<String, String, Set<String>> sharedTermTable) {
        Set<String> vpFilter = Sets.newLinkedHashSet();
        if (similarTermSetOnlyConfig.getCurrentValue()) {

            // CHECK VPs in both directions as shared term is a bi-derectional relationship
            // and some VPs might be contained in only one direction.

            // CHECK ROWS
            for (String referenceVP : sharedTermTable.rowKeySet()) {
                Map<String, Set<String>> row = sharedTermTable.row(referenceVP);
                Set<String> referenceTerms = null;
                for (Set<String> currentTerms : row.values()) {
                    if (referenceTerms == null) {
                        if (oneSharedTermOnlyConfig.getCurrentValue() && currentTerms.size() > 1) {
                            vpFilter.add(referenceVP);
                            break;
                        }
                        referenceTerms = currentTerms;
                    } else {
                        if (!referenceTerms.containsAll(currentTerms) || !currentTerms.containsAll(referenceTerms)) {
                            vpFilter.add(referenceVP);
                            break;
                        }
                    }
                }
            }

            // CHECK COLUMNS
            for (String referenceVP : sharedTermTable.columnKeySet()) {
                Map<String, Set<String>> column = sharedTermTable.column(referenceVP);
                Set<String> referenceTerms = null;
                for (Set<String> currentTerms : column.values()) {
                    if (referenceTerms == null) {
                        if (oneSharedTermOnlyConfig.getCurrentValue() && currentTerms.size() > 1) {
                            vpFilter.add(referenceVP);
                            break;
                        }
                        referenceTerms = currentTerms;
                    } else {
                        if (!referenceTerms.containsAll(currentTerms) || !currentTerms.containsAll(referenceTerms)) {
                            vpFilter.add(referenceVP);
                            break;
                        }
                    }
                }
            }
        }
        return vpFilter;
    }
}

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
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic.lucene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.splevo.vpm.analyzer.semantic.Config;

import com.google.common.base.Strings;

/**
 * This class handles the indexing. It creates one single main index. By providing two
 * {@link Analyzer}s Comments can be handled different than regular code. This class offers Methods
 * to add text to an index, clear the main index and get reading access to an index.
 *
 * DesignDecision: Singleton as required by Lucene infrastructure
 *
 * This class is a singleton because there should be only one index that holds all informations, at
 * any time. This class has the ability to create that index. To ensure there is only one Index, the
 * singleton pattern is used.
 */
public final class Indexer {

    private static final Version LUCENE_VERSION = Version.LUCENE_47;

    private static Logger logger = Logger.getLogger(Indexer.class);

    /** The field name the Variation Point ID is stored in the Lucene index. */
    public static final String INDEX_VARIATIONPOINT = "VP";

    /** The field name the content is stored in the Lucene index. */
    public static final String INDEX_CONTENT = "CONTENT";

    /** The field name the comments are stored in the Lucene index. */
    public static final String INDEX_COMMENT = "COMMENT";

    /** The {@link Directory} for the index. */
    private Directory directory;

    /** Singleton instance. */
    private static Indexer instance;

    /** Specifies how to store the code fragments in the index. */
    private static final FieldType TYPE_STORED_CONTENT = new FieldType();

    /** Specifies how to store the comment fragments in the index. */
    private static final FieldType TYPE_STORED_ID = new FieldType();

    /** Specifies whether to split on case-change or not. */
    private boolean splitCamelCase;

    /** Contains the stop-words to be filtered out. */
    private String[] stopWords;

    /** Option which stemming to use. */
    private Stemming stemming;

    /** Option which words to preserve during tokenization. */
    private Set<String> featureTermSet;

    /**
     * Define the Field-Type the text gets added to the index. To allow term extraction,
     * DOCS_AND_FREQS has to be stored in the index.
     */
    static {
        TYPE_STORED_CONTENT.setIndexed(true);
        TYPE_STORED_CONTENT.setTokenized(true);
        TYPE_STORED_CONTENT.setStored(true);
        TYPE_STORED_CONTENT.setStoreTermVectors(true);
        TYPE_STORED_CONTENT.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
    }

    /** Define the Field-Type the IDs get added to the index. */
    static {
        TYPE_STORED_ID.setIndexed(false);
        TYPE_STORED_ID.setTokenized(false);
        TYPE_STORED_ID.setStored(true);
        TYPE_STORED_ID.setStoreTermVectors(false);
        TYPE_STORED_ID.setIndexOptions(IndexOptions.DOCS_ONLY);
    }

    /**
     * Private constructor to prevent this class from being instantiated multiple times.
     */
    private Indexer() {
        this.splitCamelCase = true;
        this.stopWords = Config.DEFAULT_STOP_WORDS.split(" ");
        this.stemming = Stemming.valueOf(Config.DEFAULT_STEMMING);

        // Use RAMDirectory to use an in-memory index.
        directory = new RAMDirectory();
    }

    /**
     * Sets the stop-words that should be used by the {@link LuceneCodeAnalyzer}.
     *
     * @param stopWords
     *            The stop-words.
     */
    public void setStopWords(String[] stopWords) {
        this.stopWords = stopWords;
    }

    /**
     * Sets the option whether to split text on case-change or not.
     *
     * @param splitCamelCase
     *            True to split on case-change; False otherwise.
     */
    public void setSplitCamelCase(boolean splitCamelCase) {
        this.splitCamelCase = splitCamelCase;
    }

    /**
     * Sets the option to use stemming or not.
     *
     * @param stemming
     *            True if stemming should be used.
     */
    public void setStemming(Stemming stemming) {
        this.stemming = stemming;
    }

    /**
     * Gets the singleton instance.
     *
     * @return The singleton instance.
     */
    public static synchronized Indexer getInstance() {
        // Return singleton, create new if not existing.
        if (instance == null) {
            instance = new Indexer();
        }

        return instance;
    }

    /**
     * @return A {@link DirectoryReader} to search the main index.
     * @throws IOException
     *             Throws an {@link IOException} if there are problems opening the index.
     */
    public DirectoryReader getIndexReader() throws IOException {
        return DirectoryReader.open(directory);
    }

    /**
     * @return the featureTermSet
     */
    public Set<String> getFeatureTermSet() {
        return featureTermSet;
    }

    /**
     * @param featureTermSet the featureTermSet to set
     */
    public void setFeatureTermSet(Set<String> featureTermSet) {
        this.featureTermSet = featureTermSet;
    }

    /**
     * Adds content to the main index.
     *
     * @param variationPointId
     *            The ID of the {@link VariationPoint} to be linked with its content.
     * @param code
     *            The text content of the {@link VariationPoint}.
     * @param comments
     *            The text comments of the {@link VariationPoint}.
     * @throws IOException
     *             Thrown if the document couldn't be added to index.
     */
    public void addToIndex(String variationPointId, String code, String comments) throws IOException {
        if (variationPointId == null || variationPointId.length() == 0 || (code == null && comments == null)) {
            throw new IllegalArgumentException();
        }

        if (!Strings.isNullOrEmpty(code) || !Strings.isNullOrEmpty(comments)) {
            addDocument(variationPointId, code, comments);
        }
    }

    /**
     * Adds a {@link Document} to the index with the given VP ID and the text.
     *
     * @param variationPointId
     *            The ID to be stored.
     * @param content
     *            The text to be added to the index.
     * @param comments
     *            The text comments of the {@link VariationPoint}.
     * @throws IOException
     *             If the index cannot be opened or there are problems adding the document to the
     *             index.
     */
    private void addDocument(String variationPointId, String content, String comments) throws IOException {

        if (variationPointId == null) {
            logger.warn("Tried to add undefined variation point to index");
            return;
        }
        if (Strings.isNullOrEmpty(content) && Strings.isNullOrEmpty(comments)) {
            logger.warn("Tried to add document without semantic content");
            return;
        }

        // Create the document with two fields: the content and its ID.
        Document doc = new Document();
        doc.add(new Field(INDEX_VARIATIONPOINT, variationPointId, TYPE_STORED_ID));

        if (!Strings.isNullOrEmpty(content)) {
            doc.add(new Field(INDEX_CONTENT, content, TYPE_STORED_CONTENT));
        }
        if (Strings.isNullOrEmpty(comments)) {
            doc.add(new Field(INDEX_COMMENT, comments, TYPE_STORED_CONTENT));
        }

        IndexWriter indexWriter = createIndexWriter();
        indexWriter.addDocument(doc);
        indexWriter.close();
    }

    /**
     * Deletes all contents from the main index.
     *
     * @throws IOException
     *             Failed to clear index.
     */
    public void clearIndex() throws IOException {
        IndexWriter indexWriter = createIndexWriter();
        indexWriter.deleteAll();
        indexWriter.close();
        directory.close();
        directory = new RAMDirectory();
    }

    /**
     * Factory method to get a new writer for accessing the lucene index.
     *
     * @return The prepared index writer.
     * @throws IOException
     *             An error when trying to init the index access.
     */
    private IndexWriter createIndexWriter() throws IOException {
        PerFieldAnalyzerWrapper wrapper = createAnalzyerWrapper();
        IndexWriterConfig config = new IndexWriterConfig(LUCENE_VERSION, wrapper);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        return indexWriter;
    }

    /**
     * This method retrieves a wrapper that handles all index fields with a specific
     * {@link Analyzer}.
     *
     * @param stopWords
     *            The stop-word list to be used by the {@link LuceneCodeAnalyzer}.
     * @param splitCamelCase
     *            Specifies whether to split on case-change or not.
     * @param stemming
     *            Option to use stemming or not.
     * @return A {@link AnalyzerWrapper}.
     */
    private PerFieldAnalyzerWrapper createAnalzyerWrapper() {
        Map<String, Analyzer> analyzerPerField = new HashMap<String, Analyzer>();
        analyzerPerField.put(Indexer.INDEX_CONTENT, new LuceneCodeAnalyzer(stopWords, splitCamelCase, stemming, featureTermSet));
        analyzerPerField.put(Indexer.INDEX_COMMENT, new StandardAnalyzer(LUCENE_VERSION));

        PerFieldAnalyzerWrapper aWrapper = new PerFieldAnalyzerWrapper(new LuceneCodeAnalyzer(stopWords,
                splitCamelCase, stemming, featureTermSet), analyzerPerField);
        return aWrapper;
    }
}

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
package org.splevo.vpm.analyzer.semantic.lucene.finder;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.util.BytesRef;
import org.splevo.vpm.analyzer.semantic.lucene.Indexer;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * This Finder matches documents with a specified minimum percentage of equal terms.
 */
public class SharedTermFinder implements RelationshipFinder {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(SharedTermFinder.class);

    /** The reader containing the content to be matched. */
    private DirectoryReader reader;

    /** Indicates whether to include comments for analysis or not. */
    private boolean matchComments;

    /** The minimum number of shared terms to match. */
    private int minSharedTerms;

    /**
     * Initializations. Queries the content of the given {@link DirectoryReader} . Documents having
     * the specified minimum similarity or having at least one common term (depending on the
     * parameter) are a match.
     *
     * @param reader
     *            The reader to be used by the Finder.
     * @param matchComments
     *            Indicates whether to include comments for analysis or not.
     * @param minSharedTerms
     *            The minimum number of terms to share for a relationship.
     */
    public SharedTermFinder(DirectoryReader reader, boolean matchComments, int minSharedTerms) {
        this.reader = reader;
        this.matchComments = matchComments;
        this.minSharedTerms = minSharedTerms;
    }

    @Override
    public Table<String, String, Set<String>> findSimilarEntries() {

        Table<String, String, Set<String>> sharedTermTable = HashBasedTable.create();

        try {
            IndexSearcher indexSearcher = new IndexSearcher(reader);

            // Iterate over all documents (VariationPoints).
            for (int i = 0; i < reader.maxDoc(); i++) {
                Document referenceDoc = indexSearcher.doc(i);

                if (referenceDoc.getField(Indexer.INDEX_CONTENT) != null) {
                    Table<String, String, Set<String>> sharedTerms = buildQueryAndExecuteSearch(indexSearcher,
                            Indexer.INDEX_CONTENT, i, referenceDoc);
                    sharedTermTable.putAll(sharedTerms);
                }

                if (matchComments && referenceDoc.getField(Indexer.INDEX_COMMENT) != null) {
                    Table<String, String, Set<String>> sharedTerms = buildQueryAndExecuteSearch(indexSearcher,
                            Indexer.INDEX_COMMENT, i, referenceDoc);
                    sharedTermTable.putAll(sharedTerms);
                }
            }
        } catch (IOException e) {
            logger.error("Failure while searching Lucene index.", e);
        }

        return sharedTermTable;
    }

    /**
     * Builds the relevant arguments for a search and executes it. Add the search results to the
     * given result parameter.
     *
     * @param indexSearcher
     *            The {@link IndexSearcher} to execute the search on.
     * @param field
     *            The field to search on.
     * @param docID
     *            The current document's ID.
     * @param referenceDoc
     *            The current document.
     * @throws IOException
     *             Thrown if there were problems during search.
     */
    private Table<String, String, Set<String>> buildQueryAndExecuteSearch(IndexSearcher indexSearcher, String field,
            int docID, Document referenceDoc) throws IOException {
        Map<String, Integer> frequencies = getTermFrequencies(docID, field);
        Query query = buildQuery(field, frequencies);
        int maxDoc = reader.maxDoc();
        ScoreDoc[] hits = executeQuery(indexSearcher, maxDoc, query);
        Set<Term> referenceDocTerms = new HashSet<Term>();
        query.extractTerms(referenceDocTerms);
        return buildSharedTermTable(indexSearcher, hits, referenceDoc, referenceDocTerms, field);
    }

    /**
     * Build a matrix assigning to each variation point id combination their shared terms.
     *
     * If no terms are shared, no entry will be contained in the according table cell.
     *
     * Each variation point id pair is ordered before the table is filled. This ensures the required
     * bi-directional relationship.
     *
     * @param indexSearcher
     *            The index searcher to access the Lucene search index.
     * @param hits
     *            The number of hits for the document.
     * @param referenceDoc
     *            The document itself.
     * @param referenceDocTerms
     *            The terms to query for.
     * @param field
     *            The field to get the terms for.
     * @return The table of identified term-sharing variation points.
     * @throws IOException
     *             Any error while working with the search engine index.
     */
    private Table<String, String, Set<String>> buildSharedTermTable(IndexSearcher indexSearcher, ScoreDoc[] hits,
            Document referenceDoc, Set<Term> referenceDocTerms, String field) throws IOException {

        Table<String, String, Set<String>> sharedTermTable = HashBasedTable.create();
        for (int q = 0; q < hits.length; q++) {

            int indexDocId = hits[q].doc;

            String referenceDocId = referenceDoc.get(Indexer.INDEX_VARIATIONPOINT);
            Document foundDoc = indexSearcher.doc(indexDocId);
            String foundDocId = foundDoc.get(Indexer.INDEX_VARIATIONPOINT);

            if (referenceDocId.equals(foundDocId)) {
                continue;
            }

            Set<String> sharedTerms = determineSharedTerms(referenceDocTerms, foundDoc, indexDocId, field);

            // minShared terms is not check here because further
            // shared terms might be collected before this is evaluated.
            if (sharedTerms.size() > 0) {
                if (referenceDocId.compareTo(foundDocId) > 0) {
                    String idTmp = referenceDocId;
                    referenceDocId = foundDocId;
                    foundDocId = idTmp;
                }

                // initialize the shared term list for the pair if not
                // done yet
                Set<String> set = sharedTermTable.get(referenceDocId, foundDocId);
                if (set == null) {
                    set = new LinkedHashSet<String>();
                }
                set.addAll(sharedTerms);
                sharedTermTable.put(referenceDocId, foundDocId, sharedTerms);
            }
        }
        return sharedTermTable;
    }

    /**
     * Determine the terms shared by the related variation points by looking up all terms included
     * in the search query AND a found document.
     *
     * @param referenceDocTerms
     *            The terms of the reference doc and used in the search query.
     * @param foundDoc
     *            A specific document found by the query.
     * @param foundDocId
     *            The id of the document found to get it's index terms.
     * @param field
     *            The field to get the terms for.
     *
     * @return The {@link Set} of terms shared between the query and the document.
     * @throws IOException
     */
    private Set<String> determineSharedTerms(Set<Term> referenceDocTerms, Document foundDoc, int foundDocId,
            String field) throws IOException {
        Set<String> sharedTerms = new TreeSet<String>();
        Terms termVector = reader.getTermVector(foundDocId, field);
        TermsEnum termsEnum = null;
        TermsEnum iterator = termVector.iterator(termsEnum);
        BytesRef br = null;
        while ((br = iterator.next()) != null) {
            String term = br.utf8ToString();
            for (Term t : referenceDocTerms) {
                if (t.text().equals(term)) {
                    sharedTerms.add(term);
                }
            }
        }
        return sharedTerms;
    }

    /**
     * Executes a query.
     *
     * @param indexSearcher
     *            The {@link IndexSearcher} to be used.
     * @param maxDoc
     *            The max. number of results.
     * @param query
     *            The {@link Query} to be executed.
     * @return The result of the search.
     * @throws IOException
     *             If there were errors while executing the query.
     */
    private ScoreDoc[] executeQuery(IndexSearcher indexSearcher, int maxDoc, Query query) throws IOException {
        TopScoreDocCollector collector = TopScoreDocCollector.create(maxDoc, true);
        indexSearcher.search(query, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        return hits;
    }

    /**
     * Extracts the frequencies of all {@link Term}s in the specified {@link Document}. Uses the
     * member reader.
     *
     * @param docId
     *            The ID of the {@link Document} to extract the {@link Term}s from.
     * @param fieldName
     *            The name of the field to extract frequencies from.
     * @return A {@link Map} containing the terms as the key and the related frequencies as
     *         {@link Integer} value.
     */
    private Map<String, Integer> getTermFrequencies(int docId, String fieldName) {
        Map<String, Integer> frequencies = new HashMap<String, Integer>();

        try {
            Terms vector = reader.getTermVector(docId, fieldName);
            if (vector == null) {
                return frequencies;
            }
            TermsEnum termsEnum = null;
            termsEnum = vector.iterator(termsEnum);
            BytesRef text = null;
            while ((text = termsEnum.next()) != null) {
                String term = text.utf8ToString();
                int freq = (int) termsEnum.totalTermFreq();
                frequencies.put(term, freq);
            }
        } catch (IOException e) {
            logger.error("Failure while extracting Term Frequencies.");
        }
        return frequencies;
    }

    /**
     * This Method builds the {@link Query} the Finder uses to search similarities.
     *
     * @param fieldName
     *            The name of the field that should be searched.
     * @param termFrequencies
     *            A {@link Map} that contains all terms and their frequencies.
     * @return The {@link Query}.
     */
    private Query buildQuery(String fieldName, Map<String, Integer> termFrequencies) {
        BooleanQuery.setMaxClauseCount(Integer.MAX_VALUE);
        BooleanQuery finalQuery = new BooleanQuery();

        // Add a TermQuery for each term in the document.
        for (String key : termFrequencies.keySet()) {
            Term t = new Term(fieldName, key);
            TermQuery termQuery = new TermQuery(t);
            finalQuery.add(termQuery, Occur.SHOULD);
        }

        finalQuery.setMinimumNumberShouldMatch(minSharedTerms);

        return finalQuery;
    }
}

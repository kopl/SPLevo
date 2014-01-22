/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.diffing.match;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.MatchResource;
import org.eclipse.emf.compare.match.resource.StrategyResourceMatcher;
import org.eclipse.emf.ecore.resource.Resource;
import org.splevo.diffing.util.NormalizationUtil;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Hierarchical resource matcher to initialize the hierarchical name match strategy and the standard
 * RootIDMatchingStrategy as only matching strategies to be applied by a match engine.
 */
public class HierarchicalStrategyResourceMatcher extends StrategyResourceMatcher {

    /** Index to assign the left resources to their file name. */
    private ListMultimap<String, Resource> filenameResourcesIndexLeft = ArrayListMultimap.create();

    /** Index to assign the right resources to their file name. */
    private ListMultimap<String, Resource> filenameResourcesIndexRight = ArrayListMultimap.create();

    /** Patterns to replace with the defined target string in the URIs string representations. */
    private Map<Pattern, String> uriNormalizationPatterns = Maps.newHashMap();

    /** Patterns to replace with the defined target string in the URIs string representations. */
    private Map<Pattern, String> filenameNormalizationPatterns = Maps.newHashMap();

    /**
     * Constructor for default matching strategy without any renaming processing.
     */
    public HierarchicalStrategyResourceMatcher() {
    }

    /**
     * Constructor to specify pattern mappings to handle possible renaming.
     *
     * @param uriNormalizationPatterns
     *            A map with entries having a key representing a regular expression to match and a
     *            replacement string to set in case of a match.
     * @param fileNameNormalizationPatterns
     *            A map with entries having a key of a regular expression to match and a replacement
     *            string to set in case of a match.
     *
     */
    public HierarchicalStrategyResourceMatcher(Map<Pattern, String> uriNormalizationPatterns,
            Map<Pattern, String> fileNameNormalizationPatterns) {
        this.uriNormalizationPatterns = uriNormalizationPatterns;
        this.filenameNormalizationPatterns = fileNameNormalizationPatterns;
    }

    /**
     * Create the mappings between two lists of resources. If both lists contain only one resource,
     * they are always matches as assumed this was triggered explicitly.<br>
     * {@inheritDoc}
     */
    @Override
    public Iterable<MatchResource> createMappings(Iterator<? extends Resource> leftResources,
            Iterator<? extends Resource> rightResources, Iterator<? extends Resource> originResources) {

        final List<MatchResource> mappings = new ArrayList<MatchResource>();

        indexResources(leftResources, filenameResourcesIndexLeft, filenameNormalizationPatterns);
        indexResources(rightResources, filenameResourcesIndexRight, null);

        Set<String> allSegments = Sets.union(filenameResourcesIndexLeft.keySet(), filenameResourcesIndexRight.keySet());
        List<String> allSegmentsCopy = Lists.newArrayList(allSegments);

        for (String segment : allSegmentsCopy) {

            List<Resource> leftCandidates = Lists.newArrayList(filenameResourcesIndexLeft.get(segment));
            List<Resource> rightCandidates = Lists.newArrayList(filenameResourcesIndexRight.get(segment));

            if (leftCandidates.size() == 1 && rightCandidates.size() == 1) {
                Resource left = leftCandidates.get(0);
                Resource right = rightCandidates.get(0);
                mappings.add(createMatchResource(left, right, null));
                removeFromIndex(filenameResourcesIndexLeft, left);
                removeFromIndex(filenameResourcesIndexRight, right);

            } else if (leftCandidates.size() != 0 && rightCandidates.size() != 0) {
                matchBestMatches(leftCandidates, rightCandidates, mappings);
            }
        }

        Collection<Resource> remainingLeftResources = Sets.newLinkedHashSet(filenameResourcesIndexLeft.values());
        for (Resource left : remainingLeftResources) {
            mappings.add(createMatchResource(left, null, null));
        }
        Collection<Resource> remainingRightResources = Sets.newLinkedHashSet(filenameResourcesIndexRight.values());
        for (Resource right : remainingRightResources) {
            mappings.add(createMatchResource(null, right, null));
        }

        return mappings;
    }

    /**
     * Create matches for the left and right candidates. A match is only created if a pair is the
     * best match for both sides.
     *
     * Internally, indexes are build to identify the total number of matches and the best matches
     * for both candidate lists.
     *
     * @param leftCandidates
     *            The left candidates to search matches for.
     * @param rightCandidates
     *            The right candidates to search matches for.
     * @param mappings
     *            The list of mappings to fill.
     */
    private void matchBestMatches(List<Resource> leftCandidates, List<Resource> rightCandidates,
            List<MatchResource> mappings) {

        // index for a fast lookup of the highest match score for a resource.
        HashMap<Resource, Integer> bestMatchCountIndex = new HashMap<Resource, Integer>();
        HashMap<Resource, Resource> bestMatchIndexLeft = new HashMap<Resource, Resource>();
        HashMap<Resource, Resource> bestMatchIndexRight = new HashMap<Resource, Resource>();

        for (Resource leftRes : leftCandidates) {
            for (Resource rightRes : rightCandidates) {
                int matchCount = getMatchingSegments(leftRes, rightRes);

                if (!bestMatchCountIndex.containsKey(leftRes) || bestMatchCountIndex.get(leftRes) < matchCount) {
                    bestMatchCountIndex.put(leftRes, matchCount);
                    bestMatchIndexLeft.put(leftRes, rightRes);
                }

                if (!bestMatchCountIndex.containsKey(rightRes) || bestMatchCountIndex.get(rightRes) < matchCount) {
                    bestMatchCountIndex.put(rightRes, matchCount);
                    bestMatchIndexRight.put(rightRes, leftRes);
                }
            }
        }

        for (Resource leftRes : bestMatchIndexLeft.keySet()) {

            Resource rightRes = bestMatchIndexLeft.get(leftRes);

            // TODO: Check if a match should be prevented if it is only 1
            // 1 means only the filename is the same. The resources are expected
            // to
            // be located relative folders and the URI is an absolute uri.
            // on the other hand, the path to the root folder might be
            // different.
            // subfolderleft/resource.xmi vs. differentsubfolder/resource.xmi
            // vs
            // rootfolderlef/resource.xmi vs rootsfolderright/resource.xmi
            if (bestMatchCountIndex.get(leftRes) == bestMatchCountIndex.get(rightRes)) {
                mappings.add(createMatchResource(leftRes, rightRes, null));
                removeFromIndex(filenameResourcesIndexLeft, leftRes);
                removeFromIndex(filenameResourcesIndexRight, rightRes);
                break;
            }
        }
    }

    /**
     * Remove a resource entry from the index for all segments it has been registered for.
     *
     * @param index
     *            The index to clean.
     * @param resource
     *            The resource to remove
     */
    private void removeFromIndex(ListMultimap<String, Resource> index, Resource resource) {
        List<String> keys = Lists.newArrayList(index.keySet());
        for (String key : keys) {
            index.remove(key, resource);
        }
    }

    /**
     * Count the number of matching segments for two resources. The segment comparison starts from
     * the end of the resources' URIs because the filename is the most identifying one.
     *
     * @param leftResource
     *            The left resource to compare the uri.
     * @param rightResource
     *            The right resource to compare the uri.
     * @return The number of matching segments of the resources.
     */
    private int getMatchingSegments(Resource leftResource, Resource rightResource) {

        URI leftURI = leftResource.getURI();
        URI rightUri = rightResource.getURI();
        int count = 0;

        String[] segmentsLeft = processRenamingNormalizations(leftURI.segments());
        String[] segmentsRight = rightUri.segments();

        int refLength = segmentsLeft.length;
        int compLength = segmentsRight.length;

        for (int i = 0; i < refLength; i++) {

            if (i >= compLength) {
                break;
            }

            String refString = segmentsLeft[refLength - 1 - i];
            String compString = segmentsRight[compLength - 1 - i];
            if (refString.equals(compString)) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    /**
     * Resources are identified by URIs consisting of segments (directories and files).<br>
     * To apply a java package renaming normalization that also manifests in the source directories,
     * the normalization pattern provided as string containing "." characters must be mapped to the
     * array of URI segment strings.
     *
     * This is done by merging the URI segments with "." as glue character. As a result also the
     * directory segments of the URI representing the absolute path of the source directory are
     * joined with a dot. Later on, when the joined string is split again using "." as split
     * character, the complete string and also the absolute path part is split. As a result, when
     * the absolute path contained a "." character, a split will be performed for that.<br>
     * For example: An eclipse project named "my.first.project" will become a single segment, but
     * split into three segments when the absolute segment string is split again. if this becomes an
     * issue later on, the logic of this method must be adapted. However, any character potentially
     * lead to the same problem as different file systems also allow for different directory names.
     *
     * Note: The last segment representing the filename will be preserved even if it contains a dot
     * e.g. to separate the file extension.
     *
     * @param segmentsLeft
     *            The source array to process.
     * @return The resulting array after processing.
     */
    private String[] processRenamingNormalizations(String[] segmentsLeft) {
        String leftFilename = segmentsLeft[segmentsLeft.length - 1];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < segmentsLeft.length - 1; i++) {
            if (i > 0) {
                sb.append('.');
            }
            sb.append(segmentsLeft[i]);
        }
        String leftSegmentsAsString = NormalizationUtil.normalizeNamespace(sb.toString(), uriNormalizationPatterns);
        List<String> split = Lists.newArrayList(Splitter.on('.').split(leftSegmentsAsString));
        split.add(leftFilename);
        segmentsLeft = Iterables.toArray(split, String.class);
        return segmentsLeft;
    }

    /**
     * Index a set of resources according to their last segment.
     *
     * @param resources
     *            The resources to index.
     * @param index
     *            The index to put them in.
     * @param fileNameNormalizationPatterns
     *            The list of patterns to apply during resource indexing. Null or an empty list if
     *            none should be applied.
     */
    private void indexResources(Iterator<? extends Resource> resources, ListMultimap<String, Resource> index,
            Map<Pattern, String> fileNameNormalizationPatterns) {

        while (resources.hasNext()) {
            Resource res = resources.next();
            String filename = res.getURI().lastSegment();
            index.put(filename, res);

            // handle renaming
            if (fileNameNormalizationPatterns == null) {
                continue;
            }
            for (Pattern pattern : fileNameNormalizationPatterns.keySet()) {
                String replace = fileNameNormalizationPatterns.get(pattern);
                String newFilename = pattern.matcher(filename).replaceAll(replace);
                if (!filename.equals(newFilename)) {
                    index.put(newFilename, res);
                }
            }

        }

    }

}

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.MatchResource;
import org.eclipse.emf.compare.match.resource.StrategyResourceMatcher;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Hierarchical resource matcher to initialize the hierarchical name match
 * strategy and the standard RootIDMatchingStrategy as only matching strategies
 * to be applied by a match engine.
 */
public class HierarchicalStrategyResourceMatcher extends
		StrategyResourceMatcher {

	/** Index to assign the left resources to their file name. */
	private ListMultimap<String, Resource> segmentToLeftResources = ArrayListMultimap
			.create();

	/** Index to assign the right resources to their file name. */
	private ListMultimap<String, Resource> segmentToRightResources = ArrayListMultimap
			.create();

	@Override
	public Iterable<MatchResource> createMappings(
			Iterator<? extends Resource> leftResources,
			Iterator<? extends Resource> rightResources,
			Iterator<? extends Resource> originResources) {

		final List<MatchResource> mappings = new ArrayList<MatchResource>();

		indexResources(leftResources, segmentToLeftResources);
		indexResources(rightResources, segmentToRightResources);

		Set<String> allSegments = Sets.union(segmentToLeftResources.keySet(),
				segmentToRightResources.keySet());
		List<String> allSegmentsCopy = Lists.newArrayList(allSegments);

		for (String segment : allSegmentsCopy) {

			List<Resource> leftCandidates = segmentToLeftResources.get(segment);
			List<Resource> rightCandidates = segmentToRightResources
					.get(segment);

			if (leftCandidates.size() == 1 && rightCandidates.size() == 1) {
				Resource left = leftCandidates.get(0);
				Resource right = rightCandidates.get(0);
				mappings.add(createMatchResource(left, right, null));
				segmentToLeftResources.removeAll(segment);
				segmentToRightResources.removeAll(segment);

			} else if (leftCandidates.size() == 0) {
				for (Resource right : rightCandidates) {
					mappings.add(createMatchResource(null, right, null));
				}
				segmentToRightResources.removeAll(segment);

			} else if (rightCandidates.size() == 0) {
				for (Resource left : leftCandidates) {
					mappings.add(createMatchResource(left, null, null));
				}
				segmentToLeftResources.removeAll(segment);
			} else {
				matchBestMatches(leftCandidates, rightCandidates, mappings);
			}
		}

		for (Resource left : segmentToLeftResources.values()) {
			mappings.add(createMatchResource(left, null, null));
		}
		for (Resource right : segmentToRightResources.values()) {
			mappings.add(createMatchResource(null, right, null));
		}

		return mappings;
	}

	/**
	 * Create matches for the left and right candidates. A match is only created
	 * if a pair is the best match for both sides.
	 *
	 * Internally, an index is build to identify the matches and the best
	 * matches for both candidate lists.
	 *
	 * @param leftCandidates The left candidates to search matches for.
	 * @param rightCandidates The right candidates to search matches for.
	 * @param mappings The list of mappings to fill.
	 */
	private void matchBestMatches(List<Resource> leftCandidates,
			List<Resource> rightCandidates, List<MatchResource> mappings) {

		// index for a fast lookup of the highest match score for a resource.
		HashMap<Resource, Integer> bestMatchCountIndex = new HashMap<Resource, Integer>();
		HashMap<Resource, Resource> bestMatchIndexLeft = new HashMap<Resource, Resource>();
		HashMap<Resource, Resource> bestMatchIndexRight = new HashMap<Resource, Resource>();

		for (Resource leftRes : leftCandidates) {
			for (Resource rightRes : rightCandidates) {
				int matchCount = getMatchingSegments(leftRes, rightRes);

				if (!bestMatchCountIndex.containsKey(leftRes)
						|| bestMatchCountIndex.get(leftRes) < matchCount) {
					bestMatchCountIndex.put(leftRes, matchCount);
					bestMatchIndexLeft.put(leftRes, rightRes);
				}

				if (!bestMatchCountIndex.containsKey(rightRes)
						|| bestMatchCountIndex.get(rightRes) < matchCount) {
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
			if (bestMatchCountIndex.get(leftRes) == bestMatchCountIndex
					.get(rightRes)) {
				mappings.add(createMatchResource(leftRes, rightRes, null));
				String segment = leftRes.getURI().lastSegment();
				segmentToLeftResources.remove(segment, leftRes);
				segmentToRightResources.remove(segment, rightRes);
				break;
			}
		}
	}

	/**
	 * Count the number of matching segments for two resources. The segment
	 * comparison starts from the end of the resources' URIs because the
	 * filename is the most identifying one.
	 *
	 * @param leftResource
	 *            The left resource to compare the uri.
	 * @param rightResource
	 *            The right resource to compare the uri.
	 * @return The number of matching segments of the resources.
	 */
	private int getMatchingSegments(Resource leftResource,
			Resource rightResource) {

		URI leftURI = leftResource.getURI();
		URI rightUri = rightResource.getURI();
		int count = 0;

		String[] segmentsReference = leftURI.segments();
		String[] segmentsCompare = rightUri.segments();
		int refLength = segmentsReference.length;
		int compLength = segmentsCompare.length;

		for (int i = 0; i < refLength; i++) {

			if (i >= compLength) {
				break;
			}

			String refString = segmentsReference[refLength - 1 - i];
			String compString = segmentsCompare[compLength - 1 - i];
			if (refString.equals(compString)) {
				count++;
			} else {
				break;
			}
		}

		return count;
	}

	/**
	 * Index a set of resources according to their last segment.
	 *
	 * @param resources
	 *            The resources to index.
	 * @param index
	 *            The index to put them in.
	 */
	private void indexResources(Iterator<? extends Resource> resources,
			ListMultimap<String, Resource> index) {

		while (resources.hasNext()) {
			Resource res = resources.next();
			String filename = res.getURI().lastSegment();
			index.put(filename, res);
		}

	}

}

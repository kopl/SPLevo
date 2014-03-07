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

/**
 * This is a container class that holds all necessary information to execute a
 * search.
 */
public class RelationShipSearchConfiguration {

	/** Indicates whether to include comments for analysis or not. */
	private boolean matchComments;

	/** Determines whether to use OverallSimilarityFinder or not. */
	private boolean useSharedTermFinder;

	/** Defines the minimum number of shared terms between two variation points. */
	private int minSharedTerms;

	/**
	 * Sets default values. As a default no finder is used, comments are
	 * ignored.
	 */
	public RelationShipSearchConfiguration() {
		this.matchComments = false;
		this.useSharedTermFinder = false;
	}

	/**
	 * Configures the Overall Finder.
	 *
	 * @param useSharedTermFinder
	 *            Determines whether to use the {@link SharedTermFinder}
	 *            or not.
	 * @param minSharedTerms
	 *            The minimum similarity of two documents.
	 */
	public void configureSharedTermFinder(boolean useSharedTermFinder, int minSharedTerms) {
		this.useSharedTermFinder = useSharedTermFinder;
		this.minSharedTerms = minSharedTerms;
	}

	/**
	 * Indicates whether to use comments or not.
	 *
	 * @param use
	 *            True to use comments; False otherwise.
	 */
	public void useComments(boolean use) {
		this.matchComments = use;
	}

	/**
	 * @return the matchComments
	 */
	public boolean isMatchComments() {
		return matchComments;
	}

	/**
	 * @return the useOverallSimilarityFinder
	 */
	public boolean isUseSharedTermFinder() {
		return useSharedTermFinder;
	}

	/**
	 * @return the minimum similarity
	 */
	public int getMinSharedTerms() {
		return minSharedTerms;
	}
}

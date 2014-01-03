/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.diffing.postprocessor;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Match;

import com.google.common.collect.Lists;

/**
 * Utility to clean up the comparison model at the end of the comparison process.
 */
public class ComparisonModelCleanUp {

    /**
     * Check each match if it contains submatches or diffs.<br>
     * If not, it will be removed from the list.
     *
     * This clean up is done recursively to take the whole subtree into account.
     *
     * @param matches
     *            The list of match elements to clean up.
     */
    public static void cleanMatches(EList<Match> matches) {

        ArrayList<Match> toRemove = Lists.newArrayList();
        for (Match match : matches) {
            cleanMatches(match.getSubmatches());
            if (match.getSubmatches().size() == 0 && match.getDifferences().size() == 0) {
                toRemove.add(match);
            }
        }

        matches.removeAll(toRemove);
    }

}

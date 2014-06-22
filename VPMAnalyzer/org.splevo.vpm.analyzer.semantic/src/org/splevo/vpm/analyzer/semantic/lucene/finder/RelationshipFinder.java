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
 *    Benjamin Klatt - Refactoring and improvements
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic.lucene.finder;

import java.util.Set;

import com.google.common.collect.Table;

/**
 * To allow adding new evaluation methods easily, this class has been created.
 */
public interface RelationshipFinder {

    /**
     * Calculated the similarity between all nodes from the {@link DirectoryReader}'s index.
     *
     * @return A table linking each pair of VP ids to their set of shared terms.
     */
    public abstract Table<String, String, Set<String>> findSimilarEntries();
}

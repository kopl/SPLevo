/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CONTRIBUTR_FIRST_AND_LAST_NAME - WORK_DONE (e.g. "initial API and implementation and/or initial documentation")
 *******************************************************************************/
package org.splevo.ui.views.vpmgraph;

import org.splevo.vpm.analyzer.graph.VPMGraph;

/**
 * Interface for a provider of a {@link VPMGraph}.
 */
public interface VPMGraphProvider {

    /**
     * Gets the variation point graph to be analyzed.
     *
     * @return the variation point graph to be analyzed
     */
    public abstract VPMGraph getVpmGraph();

}
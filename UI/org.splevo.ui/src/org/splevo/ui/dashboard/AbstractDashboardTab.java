/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.dashboard;

import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;

/**
 * Super class for SPLevo dash board tabs providing common infrastructure.
 */
public abstract class AbstractDashboardTab {

    private SPLevoProjectEditor splevoProjectEditor;

    /**
     * Constructor to bind the tab with the enclosing editor.
     *
     * @param splevoProjectEditor
     *            The editor to bin with.
     */
    public AbstractDashboardTab(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

    /**
     * Access the bound editor.
     *
     * @return The editor the tab is placed on.
     */
    protected SPLevoProjectEditor getSplevoProjectEditor() {
        return splevoProjectEditor;
    }

    /**
     * Get the current project edited.
     *
     * @return The project.
     */
    protected SPLevoProject getSPLevoProject() {
        return splevoProjectEditor.getSplevoProject();
    }
}

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
package org.splevo.ui.util;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.splevo.project.SPLevoProject;

/**
 * Utility class to interact with the SPLevo workspace.
 */
public final class WorkspaceUtil {

    /** Disable constructor for utility class. */
    private WorkspaceUtil() {
    }

    // TODO check to use an absolute base path for the splevo project workspace.
    /**
     * Get the absolute URI of a variants source model assuming the workspace is located within the
     * eclipse workspace.
     *
     * @param splevoProject
     *            The splevo project defining the workspace.
     * @param variantName
     *            The variant to get the source model path for.
     * @return The absolute URI describing the workspace.
     */
    public static String getSourceModelPathWithinEclipse(SPLevoProject splevoProject, String variantName) {
        String basePath = splevoProject.getWorkspace() + "models/sourcemodels/";
        String relativePath = basePath + variantName;
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        String absolutePath = root.getLocation().toPortableString() + "/" + relativePath;
        return absolutePath;
    }

	/**
	 * Determine the absolute OS specific path of the workspace.
	 *
	 * @return The absolute path.
	 */
	public static String getAbsoluteWorkspacePath() {
	    IWorkspace workspace = ResourcesPlugin.getWorkspace();
	    String basePath = workspace.getRoot().getRawLocation().toOSString() + "/";
	    return basePath;
	}

}

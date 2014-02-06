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

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.splevo.project.SPLevoProject;

/**
 * Utility class to interact with the SPLevo workspace.
 */
public final class WorkspaceUtil {

    /** Disable constructor for utility class. */
    private WorkspaceUtil() {
    }

    // TODO Proof to return a String instead of a URI
    /**
     * Build the target uri for the model extraction.
     *
     * @param splevoProject
     *            The project to get the workspace for.
     * @param variantName
     *            The name of the variant to extract.
     * @return The prepared URI.
     */
    public static URI buildTargetURI(SPLevoProject splevoProject, String variantName) {
        String basePath = splevoProject.getWorkspace() + "models/sourcemodels/";
        String targetPath = basePath + variantName;
        URI targetURI = URI.createURI(targetPath);
        return targetURI;
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
    public static URI getSourceModelPathWithinEclipse(SPLevoProject splevoProject, String variantName) {
        URI targetURI = WorkspaceUtil.buildTargetURI(splevoProject, variantName);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        URI absoluteTargetUri = URI.createFileURI(root.getLocation().toPortableString() + targetURI.toString());
        return absoluteTargetUri;
    }

}

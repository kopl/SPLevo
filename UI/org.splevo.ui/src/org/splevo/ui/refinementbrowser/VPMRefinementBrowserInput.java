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
package org.splevo.ui.refinementbrowser;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.vpm.refinement.RefinementModel;

/**
 * Input object for the refinement browser.
 */
public class VPMRefinementBrowserInput implements IEditorInput {

    /** The refinement model to be presented. */
    private RefinementModel refinementModel;

    /** The splevo editor originally trigger the process. */
    private SPLevoProjectEditor splevoEditor = null;

    /**
     * Constructor requiring a reference to the refinements to present.
     * 
     * @param refinementModel
     *            The refinement model.
     * @param splevoEditor
     *            A reference to the SPLevo project editor as the browser context.
     */
    public VPMRefinementBrowserInput(RefinementModel refinementModel, SPLevoProjectEditor splevoEditor) {
        this.refinementModel = refinementModel;
        this.splevoEditor = splevoEditor;
    }

    /**
     * @return the refinement model
     */
    public RefinementModel getRefinementModel() {
        return refinementModel;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object getAdapter(Class adapter) {
        return null;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    @Override
    public String getName() {
        return "Recommended VPM Refinements";
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return "Presents the recommended VPM refinements";
    }

    /**
     * @return the splevoEditor
     */
    public SPLevoProjectEditor getSplevoEditor() {
        return splevoEditor;
    }

}

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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.vpm.refinement.Refinement;

/**
 * Input object for the refinement browser.
 */
public class VPMRefinementBrowserInput implements IEditorInput {

    /** The refinements to be presented. */
    private List<Refinement> refinements = new ArrayList<Refinement>();

    /** The splevo editor originally trigger the process. */
    private SPLevoProjectEditor splevoEditor = null;

    /**
     * Constructor requiring a reference to the refinements to present.
     * 
     * @param refinements
     *            The refinements map.
     * @param splevoEditor
     *            A reference to the SPLevo project editor as the browser context.
     */
    public VPMRefinementBrowserInput(List<Refinement> refinements, SPLevoProjectEditor splevoEditor) {
        this.refinements = refinements;
        this.splevoEditor = splevoEditor;
    }

    /**
     * @return the refinements
     */
    public List<Refinement> getRefinements() {
        return refinements;
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

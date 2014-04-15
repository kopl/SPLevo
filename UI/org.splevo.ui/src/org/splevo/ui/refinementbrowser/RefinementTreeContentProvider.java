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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.splevo.vpm.refinement.Refinement;

/**
 * The Content provider for the refinement tree.
 *
 * In the current version, the tree consists of a single level only. A hierarchy might be used later
 * on to present related refinements.
 */
public class RefinementTreeContentProvider implements ITreeContentProvider {

    /** The refinements to be performed. */
    private List<Refinement> refinements = new ArrayList<Refinement>();

    @Override
    public void dispose() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        this.refinements = (List<Refinement>) newInput;
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return refinements.toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        return null;
    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return false;
    }

}

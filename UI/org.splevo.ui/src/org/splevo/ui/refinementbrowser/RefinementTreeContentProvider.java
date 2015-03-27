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
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.refinementbrowser;

import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementModel;

/**
 * The Content provider for the refinement tree.
 *
 * In the current version, the tree consists of a single level only. A hierarchy might be used later
 * on to present related refinements.
 */
public class RefinementTreeContentProvider extends RefinementTreeContentProviderBase<RefinementModel> {

    @Override
    protected Object[] getElements() {
        return topLevelElement.getRefinements().toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof Refinement) {
            Refinement refinement = (Refinement) parentElement;
            return refinement.getSubRefinements().toArray();
        }
        return null;
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof Refinement) {
            Refinement refinement = (Refinement) element;
            return refinement.getParent();
        }
        return null;
    }

}

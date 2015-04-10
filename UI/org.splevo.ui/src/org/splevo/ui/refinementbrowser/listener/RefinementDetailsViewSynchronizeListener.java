/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.refinementbrowser.listener;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.splevo.ui.vpexplorer.explorer.ExplorerMediator;

/**
 * A listener for selections in the refinement details view. The selection will be propagated to the
 * VPExplorer mediator, which synchronizes the views.
 */
public class RefinementDetailsViewSynchronizeListener implements ISelectionChangedListener {

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        ExplorerMediator.getInstance().selectionChanged(event);
    }

}

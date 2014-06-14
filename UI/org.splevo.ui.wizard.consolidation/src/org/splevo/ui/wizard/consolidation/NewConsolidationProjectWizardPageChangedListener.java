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
package org.splevo.ui.wizard.consolidation;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;

/**
 * Listener to react on page transitions of the new consolidation project wizard.
 * 
 * @author Radoslav Yankov
 *
 */
public class NewConsolidationProjectWizardPageChangedListener implements IPageChangedListener {

    @Override
    public void pageChanged(PageChangedEvent event) {
        Object selectedPage = event.getSelectedPage();
        
        if (selectedPage instanceof ProjectsSelectionWizardPage) {
            ((ProjectsSelectionWizardPage) selectedPage).setSPLevoProjectConfiguration();
        } else if (selectedPage instanceof PackageScopeDefinitionWizardPage) {
            ((PackageScopeDefinitionWizardPage) selectedPage).setTreeViewerInput();
        }
    }
}

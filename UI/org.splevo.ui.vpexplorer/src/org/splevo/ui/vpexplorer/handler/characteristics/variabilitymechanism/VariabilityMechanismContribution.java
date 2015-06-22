/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thomas Czogalik
 *******************************************************************************/
package org.splevo.ui.vpexplorer.handler.characteristics.variabilitymechanism;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringRegistry;

/**
 * Implementation of the abstract ContributionItem class. 
 */
public class VariabilityMechanismContribution extends ContributionItem {

    //private List<VariabilityRefactoring> elements;

    private static List<VariabilityRefactoring> elements;
    private static Logger logger = Logger.getLogger(VariabilityMechanismContribution.class);
    private static final String DATA_KEY = "DATAKEY";
    private VariabilityRefactoring mechanism;
    
    static {
        elements = VariabilityRefactoringRegistry.getInstance().getElements();
    }
    
    /**
     * Creates a VariabilityMechanismContribution object with a null id. 
     */
    public VariabilityMechanismContribution() {
    }
    
    /**
     * Creates a VariabilityMechanismContribution object with the given id. 
     * @param id the id of the contribution item
     */
    public VariabilityMechanismContribution(String id) {
        super(id);
    }
 
    @Override
    public void fill(Menu menu, int index) {  
        for (int i = 0; i < elements.size(); i++) {
            MenuItem menuItem = new MenuItem(menu, SWT.CHECK, index);
            menuItem.setText(elements.get(i).getVariabilityMechanism().getName());
            menuItem.setData(DATA_KEY, elements.get(i));
                    
            menuItem.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    if (!(e.getSource() instanceof MenuItem)) {
                        return;
                    }
                    MenuItem item = (MenuItem) e.getSource();
                    if (!(item.getData(DATA_KEY) instanceof VariabilityRefactoring)) {
                        return;
                    }                    
                    mechanism = (VariabilityRefactoring) item.getData(DATA_KEY);
                    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                    ISelection selection = window.getActivePage().getSelection();
                    SetVariabilityMechanism setMechanism = new SetVariabilityMechanism(mechanism);
                    try {
                        setMechanism.execute(selection);
                    } catch (ExecutionException e1) {
                        logger.error("Failed to set mechanism: " + e1);
                    }
                }
            });
        }
    }
}

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
package org.splevo.ui.wizards.vpmanalysis;

import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerService;

import com.google.common.collect.Lists;

/**
 * Listener to open a dialog for selecting a vpm analysis to be performed.
 */
public class VPMAnalyzerSelectionDialogListener extends MouseAdapter {

    /** The wizard page to update. */
    private VPMAnalyzerConfigurationPage page = null;

    /** The analyzer service to work with. */
    private VPMAnalyzerService vpmAnalyzerService = new DefaultVPMAnalyzerService();

    /**
     * Constructor requiring a reference to the listed to be filled.
     *
     * @param page
     *            The page to open.
     */
    public VPMAnalyzerSelectionDialogListener(VPMAnalyzerConfigurationPage page) {
        this.page = page;
    }

    /**
     * Mouse handler to open the dialog.
     *
     * @param e
     *            The mouse event to process.
     */
    @Override
    public void mouseUp(MouseEvent e) {

        Shell shell = e.widget.getDisplay().getActiveShell();
        List<VPMAnalyzer> availableAnalyzer = Lists.newArrayList(vpmAnalyzerService.getAvailableAnalyzers());

        availableAnalyzer.removeAll(page.getAnalyzers());


        ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, new VPMAnalyzerLabelProvider());
        dialog.setTitle("VPM Analyzer Selection");
        dialog.setMessage("Select an analyzer by name (* = any string, ? = any char):");
        dialog.setElements(availableAnalyzer.toArray());
        dialog.setMultipleSelection(false);
        int result = dialog.open();
        if (result == Window.OK) {
            Object analyzerObject = dialog.getFirstResult();
            if (analyzerObject != null) {
                VPMAnalyzer analyzer = (VPMAnalyzer) analyzerObject;
                page.addAnalyzer(analyzer);
            }
        }
    }
}

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
package org.splevo.ui.sourceconnection.handler;

import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.UnifiedDiffEditor;
import org.splevo.ui.editors.UnifiedDiffEditor.UnifiedDiffEditorInput;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowser;
import org.splevo.ui.sourceconnection.DiffConnector;
import org.splevo.ui.vpexplorer.explorer.VPExplorer;
import org.splevo.vpm.variability.Variant;

/**
 * Handler for opening the unified diff of selected variants.
 */
public class OpenSourceLocationDiffHandler extends OpenSourceHandlerBase {

    private static final Logger LOGGER = Logger.getLogger(OpenSourceLocationDiffHandler.class);

    @Override
    protected void handle(Set<Variant> variants, IWorkbenchPart sendingPart) {
        SPLevoProject splevoProject = getSPLevoProjectFromWorkbenchPart(sendingPart);
        if (splevoProject != null) {
            handle(variants, splevoProject);            
        }
    }

    private void handle(Set<Variant> variants, SPLevoProject splevoProject) {
        DiffConnector diffConnector = new DiffConnector(splevoProject);
        String htmlDiff = diffConnector.generateDiffFromVPs(variants);

        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .openEditor(new UnifiedDiffEditorInput(htmlDiff), UnifiedDiffEditor.ID);
        } catch (PartInitException e) {
            LOGGER.error("Error initializing the unified diff editor.", e);
        }

    }
    
    private static SPLevoProject getSPLevoProjectFromWorkbenchPart(IWorkbenchPart workbenchPart) {
        if (workbenchPart instanceof VPExplorer) {
            return ((VPExplorer) workbenchPart).getVPExplorerMetaData().getSPLevoProject();
        }
        if (workbenchPart instanceof VPMRefinementBrowser) {
            return ((VPMRefinementBrowser) workbenchPart).getSPLevoProjectEditor().getSplevoProject();
        } else {
            LOGGER.warn("Got called from unknown source. Developers have to fix this.");
            return null;
        }
    }

}

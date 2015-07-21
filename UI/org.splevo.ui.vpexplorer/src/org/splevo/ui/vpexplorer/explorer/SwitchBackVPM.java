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
package org.splevo.ui.vpexplorer.explorer;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.splevo.project.VPMModelReference;
import org.splevo.ui.vpexplorer.util.VPMUIUtil;

import com.google.common.collect.Lists;

/**
 * Action for switching back the VPM version. It is realized as a drop down box from which the user
 * can choose the version.
 */
public class SwitchBackVPM extends Action {



    /**
     * Rollback action for a specific VPM path. This is used in the selection menu for the user.
     */
    private class VPMRollbackMenuAction extends Action {

        private final VPMModelReference vpmReference;

        public VPMRollbackMenuAction(VPMModelReference reference) {
            super(FilenameUtils.getBaseName(reference.getPath()));
            this.vpmReference = reference;
        }

        @Override
        public void runWithEvent(Event event) {
            Shell shell = event.widget.getDisplay().getShells()[0];
            boolean confirmed = MessageDialog.openQuestion(shell, "Switch Back VPM Version", String.format(
                    "You want to switch back to %s, which removes all later versions. Do you want to continue?",
                    getText()));
            if (confirmed) {
                VPMUIUtil.switchBackVPMVersion(vpexplorer.getSPLevoProject(), vpmReference);
            }
        }

    }

    private static final String ACTION_TEXT = "Switch Back VPM";
    private final VPExplorer vpexplorer;

    /**
     * Constructs a new switch back action for VPMs.
     * 
     * @param vpexplorer
     *            The VPExplorer to which this action belongs. It also provides the switch back
     *            helper used in this action.
     */
    public SwitchBackVPM(VPExplorer vpexplorer) {
        super(ACTION_TEXT, AS_DROP_DOWN_MENU);
        this.vpexplorer = vpexplorer;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UNDO);
    }

    @Override
    public IMenuCreator getMenuCreator() {
        IMenuCreator menuCreator = new IMenuCreator() {

            @Override
            public void dispose() {
                // TODO Auto-generated method stub
            }

            @Override
            public Menu getMenu(Control parent) {
                return createMenuManager().createContextMenu(parent);
            }

            @Override
            public Menu getMenu(Menu parent) {
                return null;
            }

            private MenuManager createMenuManager() {
                MenuManager manager = new MenuManager();

                manager.addMenuListener(new IMenuListener() {
                    @Override
                    public void menuAboutToShow(IMenuManager manager) {
                        if (vpexplorer.getSPLevoProject() != null
                                && vpexplorer.getSPLevoProject() != null
                                && vpexplorer.getSPLevoProject().getVpmModelReferences().size() > 0) {
                            for (VPMModelReference reference : Lists.reverse(vpexplorer.getSPLevoProject()
                                    .getVpmModelReferences())) {
                                manager.add(new VPMRollbackMenuAction(reference));
                            }
                        }
                    }
                });

                return manager;
            }
        };

        return menuCreator;
    }

}

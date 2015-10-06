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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.splevo.project.SPLevoProject;
import org.splevo.project.SPLevoProjectUtil;
import org.splevo.project.VPMModelReference;

import com.google.common.collect.Iterables;

/**
 * Handler for the "Load VPM" composite displayed in the VPExplorer. It constructs the composite,
 * updates the selectable values after changes and delegates the VPM loading request to a given
 * VPMLoader.
 */
public class LoadVPMCompositeHandler {

    /**
     * Base interface for classes that can be used to load a VPM.
     */
    public interface VPMLoader {
        /**
         * Loads the specified VPM.
         * 
         * @param project
         *            The corresponding SPLevo project.
         * @param vpmReference
         *            The reference to the VPM as specified in the project file.
         */
        public void loadVPM(SPLevoProject project, VPMModelReference vpmReference);
    }

    /**
     * Container for the information required for loading VPMs. It is used as selectable entry in
     * the combo box.
     */
    private static class VPMLoadingInformation {
        private final SPLevoProject project;
        private final VPMModelReference vpmReference;

        public VPMLoadingInformation(SPLevoProject project, VPMModelReference vpmReference) {
            super();
            this.project = project;
            this.vpmReference = vpmReference;
        }

        public SPLevoProject getProject() {
            return project;
        }

        public VPMModelReference getVPMReference() {
            return vpmReference;
        }

        @Override
        public String toString() {
            return String.format("%s: %s", project.getName(), FilenameUtils.getName(vpmReference.getPath()));
        }
    }

    /**
     * Provider class for the VPM loading information. The information is determined by looking for
     * open SPLevo projects and parsing the project file.
     */
    private static class VPMLoadingInformationProvider {
        public Object[] getElements() {
            List<VPMLoadingInformation> elements = new ArrayList<VPMLoadingInformation>();

            Iterable<IFile> projectFiles = SPLevoProjectUtil.findAllSPLevoProjectFilesInWorkspace(true);
            for (IFile projectFile : projectFiles) {
                try {
                    SPLevoProject project = SPLevoProjectUtil.loadSPLevoProjectModel(projectFile);
                    VPMModelReference vpmReference = Iterables.getLast(project.getVpmModelReferences());                        
                    elements.add(new VPMLoadingInformation(project, vpmReference));
                } catch (NoSuchElementException e) {
                    continue;
                } catch (IOException e) {
                    // we skip the project on a loading error
                    LOGGER.warn("Error loading the SPLevo project.", e);
                }
            }

            return elements.toArray();
        }
    }

    private static final Logger LOGGER = Logger.getLogger(LoadVPMCompositeHandler.class);

    private final IResourceChangeListener projecListener = createProjectOpenCloseListener();
    private final ILabelProvider labelProvider = createLabelProvider();
    private final IStructuredContentProvider contentProvider = createContentProvider();
    private final VPMLoader vpmLoader;
    private ComboViewer comboViewer;

    /**
     * Constructs the handler.
     * 
     * @param vpmLoader
     *            A load helper for VPMs.
     */
    public LoadVPMCompositeHandler(VPMLoader vpmLoader) {
        this.vpmLoader = vpmLoader;
    }

    /**
     * Disables the change monitoring and the update of the controls.
     */
    public void disable() {
        unregisterForProjectOpenCloseEvents();
    }

    /**
     * Creates the composite that shall be displayed. It contains a description on loading VPMs, a
     * combo box for VPM selection and an apply button.
     * 
     * @param parent
     *            The parent composite.
     * @return The composite representing the loading page.
     */
    public Composite createControl(Composite parent) {
        // register for content changing events
        registerForProjectOpenCloseEvents();

        // create the base composite
        Composite emptyViewPane = new Composite(parent, SWT.NONE);
        emptyViewPane.setLayoutData(new GridData(GridData.FILL_BOTH));
        emptyViewPane.setLayout(new GridLayout(2, false));

        // create the controls
        Label explanationLabel = new Label(emptyViewPane, SWT.WRAP);
        comboViewer = new ComboViewer(emptyViewPane, SWT.READ_ONLY | SWT.DROP_DOWN);
        // final Combo vpmSelectionCombo = new Combo(emptyViewPane, SWT.READ_ONLY | SWT.DROP_DOWN);
        final Button vpmLoadingButton = new Button(emptyViewPane, SWT.NONE);

        // initialize the explanation label
        explanationLabel.setText("There is no VPM loaded. "
                + "You can select a VPM based on the open consolidation projects located in your workspace.");
        explanationLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));

        // initialize the VPM selection combo box
        comboViewer.setContentProvider(contentProvider);
        comboViewer.setLabelProvider(labelProvider);
        comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                vpmLoadingButton.setEnabled(true);
            }
        });
        comboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        refreshComboViewerInput();

        // initialize the VPM loading button
        vpmLoadingButton.setText("Load");
        vpmLoadingButton.setEnabled(false);
        vpmLoadingButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (!(comboViewer.getSelection() instanceof IStructuredSelection)) {
                    LOGGER.warn("No structured selection returned from combo box.");
                    return;
                }

                IStructuredSelection cvSelection = (IStructuredSelection) comboViewer.getSelection();
                if (!(cvSelection.getFirstElement() instanceof VPMLoadingInformation)) {
                    LOGGER.warn("The selected value in the combo box is not correctly typed.");
                    vpmLoadingButton.setEnabled(false);
                    return;
                }

                VPMLoadingInformation vpmInformation = (VPMLoadingInformation) cvSelection.getFirstElement();
                loadVPM(vpmInformation.getProject(), vpmInformation.getVPMReference());
            }
        });

        // return the result
        return emptyViewPane;
    }

    private IResourceChangeListener createProjectOpenCloseListener() {
        return new IResourceChangeListener() {
            @Override
            public void resourceChanged(IResourceChangeEvent event) {
                if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
                    projectClosed((IProject) event.getResource());
                }  
                
                if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
                    projectClosed((IProject) event.getResource());
                    try {
                        
                        event.getDelta().accept(new IResourceDeltaVisitor() {
                            @Override
                            public boolean visit(IResourceDelta delta) throws CoreException {
                                if (delta.getResource() instanceof IWorkspaceRoot) {
                                    return true;
                                }

                                if ((delta.getFlags() & IResourceDelta.OPEN) != 0
                                        && delta.getResource() instanceof IProject) {
                                    projectOpened((IProject) delta.getResource());
                                }

                                return false;
                            }

                        });
                    } catch (CoreException e) {
                        LOGGER.warn(
                                "Error processing a resource change. The VPM loading composite might not be up to date anymore.",
                                e);
                    }
                }
            }
        };
    }

    private IStructuredContentProvider createContentProvider() {
        return new IStructuredContentProvider() {

            private VPMLoadingInformationProvider provider;

            @Override
            public void inputChanged(final Viewer viewer, Object oldInput, Object newInput) {
                if (newInput == null) {
                    return;
                }

                if (!(newInput instanceof VPMLoadingInformationProvider)) {
                    LOGGER.warn(String.format("The given input is of type %s instead of %s.", newInput.getClass()
                            .getSimpleName(), VPMLoadingInformationProvider.class.getSimpleName()));
                    return;
                }

                provider = (VPMLoadingInformationProvider) newInput;
                viewer.getControl().getDisplay().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        viewer.refresh();
                    }
                });
            }

            @Override
            public void dispose() {
            }

            @Override
            public Object[] getElements(Object inputElement) {
                if (provider != null) {
                    return provider.getElements();
                }
                return new Object[0];
            }
        };
    }

    private ILabelProvider createLabelProvider() {
        return new LabelProvider();
    }

    private void registerForProjectOpenCloseEvents() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(projecListener);
    }

    private void unregisterForProjectOpenCloseEvents() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(projecListener);
    }

    private void projectOpened(IProject resource) {
        refreshComboViewerInput();
    }

    private void projectClosed(IProject resource) {
        refreshComboViewerInput();
    }

    private void refreshComboViewerInput() {
        comboViewer.getControl().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                comboViewer.setInput(new VPMLoadingInformationProvider());
            }
        });
    }

    private void loadVPM(SPLevoProject project, VPMModelReference vpmReference) {
        vpmLoader.loadVPM(project, vpmReference);
    }

}

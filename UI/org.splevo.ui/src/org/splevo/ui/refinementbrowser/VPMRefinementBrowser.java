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

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.vpm.refinement.Refinement;

/***
 * An editor to view and accept, decline or modify the variation point model refinement
 * recommendations.
 */
public class VPMRefinementBrowser extends EditorPart {

    /** The public id to reference the editor. */
    public static final String ID = "org.splevo.ui.editor.VPMRefinementEditor"; //$NON-NLS-1$

    /** The editor input of the browser. */
    private VPMRefinementBrowserInput input;

    /** The checkbox tree viewer to select the refinements to apply. */
    private TreeViewer refinementListView;

    /** The form toolkit to manipulate the form. */
    private FormToolkit toolkit;

    /** The main form of the editor. */
    private Form form;

    private RefinementDetailsView detailsView;

    @Override
    public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
        if (!(editorInput instanceof VPMRefinementBrowserInput)) {
            throw new RuntimeException("Wrong input");
        }

        this.input = (VPMRefinementBrowserInput) editorInput;
        setSite(site);
        setInput(editorInput);
        setPartName("VPM Refinement Browser");
    }

    /**
     * This is a callback that will allow us to create the viewer and initialize it.
     *
     * @param parent
     *            The parent ui element to create this editor in.
     */
    public void createPartControl(Composite parent) {
        toolkit = new FormToolkit(parent.getDisplay());
        form = toolkit.createForm(parent);
        form.setText("SPLevo Refinement Browser");
        toolkit.decorateFormHeading(form);
        form.getMenuManager().add(new ApplyRefinementsAction(this, "Apply Refinements"));
        createFormContent(form.getBody());

        SashForm sashForm = new SashForm(form.getBody(), SWT.BORDER | SWT.FILL);
        sashForm.setSashWidth(1);
        sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
        toolkit.adapt(sashForm);
        toolkit.paintBordersFor(sashForm);

        createRefinementListView(sashForm);
        createRefinementDetails(sashForm);

        // The listener must be added after the two connected required widgets have been created
        refinementListView.addSelectionChangedListener(new RefinementSelectionListener(detailsView));
        refinementListView.addSelectionChangedListener(new RefinementInfoSelectionListener(detailsView));
        IActionBars actionBars = getEditorSite().getActionBars();
        refinementListView.addSelectionChangedListener(new RefinementActionBarListener(actionBars));
        getSite().setSelectionProvider(refinementListView);

        sashForm.setWeights(new int[] { 2, 8 });

        initContextMenu();
    }

    private void createRefinementDetails(SashForm sashForm) {
        detailsView = new RefinementDetailsView(sashForm, getSite());
    }

    private void createRefinementListView(SashForm sashForm) {
        refinementListView = new TreeViewer(sashForm, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        refinementListView.setContentProvider(new RefinementTreeContentProvider());
        refinementListView.setLabelProvider(new RefinementTreeLabelProvider());
        refinementListView.setAutoExpandLevel(2);
        refinementListView.setInput(input.getRefinements());
        refinementListView.addDoubleClickListener(new ExpandTreeListener());
    }

    /**
     * Create the form body providing the real content.
     *
     * @param parent
     *            The parent ui element to create the form in.
     */
    private void createFormContent(Composite parent) {
        form.getBody().setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    /**
     * Get the selected refinements.
     *
     * @return The list of refinements activated in the tree view.
     */
    public List<Refinement> getSelectedRefinements() {
        return input.getRefinements();
    }

    /**
     * Get the SPLevo project editor that was originally used to trigger the analysis process.
     *
     * @return Get the reference to the SPLevo dashboard editor.
     */
    public SPLevoProjectEditor getSPLevoProjectEditor() {
        return input.getSplevoEditor();
    }

    /** Passing the focus request to the form. */
    public void setFocus() {
        form.setFocus();
    }

    /**
     * Disposes the toolkit.
     */
    public void dispose() {
        toolkit.dispose();
        super.dispose();
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        // never be called
    }

    @Override
    public void doSaveAs() {
        // never be called
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    /**
     * initialize the context menu.
     */
    private void initContextMenu() {
        MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {

            @Override
            public void menuAboutToShow(IMenuManager manager) {
                Action action = new DeleteRefinementAction(refinementListView, detailsView);
                manager.add(action);
            }
        });
        menuMgr.addMenuListener(new CommandActionMenuListener("org.splevo.ui.commands.argouml.variantscan", SPLevoUIPlugin
                .getImageDescriptor("icons/kopl_circle_only.png")));
        Menu menu = menuMgr.createContextMenu(refinementListView.getTree());
        refinementListView.getTree().setMenu(menu);
    }
}

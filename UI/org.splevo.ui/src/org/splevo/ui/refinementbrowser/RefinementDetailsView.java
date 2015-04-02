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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.SWTResourceManager;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.commons.tooltip.TreeViewerToolTip;
import org.splevo.ui.refinementbrowser.action.RenameRefinementAction;
import org.splevo.ui.refinementbrowser.listener.CommandActionMenuListener;
import org.splevo.ui.refinementbrowser.listener.ExpandTreeListener;
import org.splevo.ui.refinementbrowser.listener.HighlightConnectedVPListener;
import org.splevo.ui.refinementbrowser.listener.RefinementInfoSelectionListener;
import org.splevo.ui.util.UIUtil;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.CustomizableDescriptionHaving;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * Details view to show information about a currently selected refinement.
 */
public class RefinementDetailsView extends Composite {

    private static final String COMMAND_ID_OPENSOURCELOCATION = "org.splevo.ui.commands.opensourcelocation";
    private static final String REFINEMENT_INFO_DEFAULT_TEXT = "Select refinement on the left to review details.";

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /** The internal tree viewer to present the refined variation points. */
    private TreeViewer refinementDetailsTreeViewer = null;

    /** The area to present info about a currently selected refinement in. */
    private StyledText refinementInfoArea = null;

    private RefinementGraph refinementGraph = null;

    /**
     * Constructor to create the view.
     * 
     * @param parent
     *            The parent ui element to present the view in.
     * @param site
     *            The site of the workbench the view is located at. E.g. used to register selection
     *            listeners.
     */
    public RefinementDetailsView(SashForm parent, IWorkbenchPartSite site) {
        super(parent, SWT.FILL);
        setLayout(new FillLayout(SWT.HORIZONTAL));

        SashForm sashForm = new SashForm(this, SWT.FILL);
        sashForm.setSashWidth(1);
        sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

        refinementDetailsTreeViewer = new TreeViewer(sashForm, SWT.MULTI | SWT.BORDER);
        refinementDetailsTreeViewer.setLabelProvider(new RefinementDetailsLabelProvider());
        refinementDetailsTreeViewer.setContentProvider(new RefinementDetailsTreeContentProvider());
        refinementDetailsTreeViewer.addDoubleClickListener(new ExpandTreeListener());
        refinementDetailsTreeViewer.addSelectionChangedListener(new RefinementInfoSelectionListener(this));
        refinementDetailsTreeViewer.addSelectionChangedListener(new HighlightConnectedVPListener());
        initContextMenu(refinementDetailsTreeViewer, site);
        initToolTips(refinementDetailsTreeViewer);

        SashForm detailsArea = new SashForm(sashForm, SWT.FILL);
        detailsArea.setSashWidth(1);
        detailsArea.setOrientation(SWT.VERTICAL);
        detailsArea.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

        refinementInfoArea = new StyledText(detailsArea, SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
        refinementInfoArea.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        refinementInfoArea.setText(REFINEMENT_INFO_DEFAULT_TEXT);
        refinementGraph = new RefinementGraph(detailsArea, SWT.BORDER);

        // FIXME IllegalArgumentException at runtime
        // The areas' relative height should be set but this leads to an
        // detailsArea.setWeights(new int[] { 6, 4 });

        sashForm.setWeights(new int[] { 5, 5 });
    }

    /**
     * Enables or disables the control.
     * 
     * @param enable
     *            <code>true</code> to enable; <code>false</code> to disable.
     */
    public void setEnabled(boolean enable) {
        refinementDetailsTreeViewer.getControl().setEnabled(enable);
    }

    /**
     * Create contents of the details page.
     * 
     * @param parent
     *            The parent ui element to create the view in.
     */
    public void createContents(Composite parent) {
        parent.setEnabled(true);

    }

    /**
     * Trigger the presentation of a specific refinement in the view.
     * 
     * @param refinement
     *            The refinement to show.
     */
    public void showRefinement(Refinement refinement) {
        setEnabled(true);
        refinementDetailsTreeViewer.setInput(refinement);
        refinementGraph.show(refinement);
    }

    /**
     * Display an info about a refinement.<br>
     * 
     * Allows for setting headline and sub headline which will have a specific styling.
     * 
     * @param headline
     *            A headline to be displayed
     * @param subHeadline
     *            A sub headline to be displayed
     * @param info
     *            The text to display.
     */
    public void displayRefinementInfo(String headline, String subHeadline, String info) {

        refinementInfoArea.setText(headline + LINE_SEPARATOR + LINE_SEPARATOR + subHeadline + LINE_SEPARATOR + info);

        if (!Strings.isNullOrEmpty(headline)) {

            StyleRange styleRange = new StyleRange();
            styleRange.start = 0;
            styleRange.length = headline.length();
            styleRange.fontStyle = SWT.BOLD;
            styleRange.underline = true;
            styleRange.underlineStyle = SWT.UNDERLINE_DOUBLE;
            refinementInfoArea.setStyleRange(styleRange);

            if (!Strings.isNullOrEmpty(subHeadline)) {
                styleRange = new StyleRange();
                styleRange.start = headline.length() + (2 * LINE_SEPARATOR.length());
                styleRange.length = subHeadline.length();
                styleRange.fontStyle = SWT.BOLD;
                styleRange.underline = true;
                styleRange.underlineStyle = SWT.UNDERLINE_SINGLE;
                refinementInfoArea.setStyleRange(styleRange);
            }
        }
    }

    /**
     * The content provider for the tree providing access to the variation points and their child
     * elements.
     */
    private static class RefinementDetailsTreeContentProvider extends RefinementTreeContentProviderBase<Refinement> {

        @Override
        protected Object[] getElements() {
            List<Object> children = Lists.newLinkedList();
            children.addAll(topLevelElement.getSubRefinements());
            children.addAll(topLevelElement.getVariationPoints());
            return children.toArray();
        }

        @Override
        public Object[] getChildren(Object parentElement) {

            if (parentElement instanceof Refinement) {
                Refinement parentRefinement = (Refinement) parentElement;
                List<Object> children = Lists.newLinkedList();
                children.addAll(parentRefinement.getSubRefinements());
                children.addAll(parentRefinement.getVariationPoints());
                return children.toArray();

            } else if (parentElement instanceof VariationPoint) {
                return ((VariationPoint) parentElement).getVariants().toArray();
            } else if (parentElement instanceof Variant) {
                return ((Variant) parentElement).getImplementingElements().toArray();
            } else {
                return new Object[] {};
            }
        }

        @Override
        public Object getParent(Object element) {
            return null;
        }

    }

    /**
     * Label Provider for a variation point element.
     */
    private static class RefinementDetailsLabelProvider extends LabelProvider {

        @Override
        public Image getImage(Object element) {
            Image image = UIUtil.getItemProviderImage(element);
            if (image != null) {
                return image;
            } else {
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
            }
        }

        /**
         * Get the text label for a supplied element. Adapted to interpret the information of the
         * specific type (VariationPoint, Variant, etc.)
         * 
         * {@inheritDoc}
         */
        @Override
        public String getText(Object element) {
            String label = UIUtil.getItemProviderText(element);
            if (!Strings.isNullOrEmpty(label)) {
                return label;
            } else {
                return "[UNKNOWN]";
            }
        }
    }

    /**
     * initialize the context menu.
     * 
     * DesignDecision Menu created programaticaly instead of extension point to prevent context menu
     * mess up by other plugins.
     * 
     * DesignDecision Reused command for common look and feel of context menu item for complete
     * application
     * 
     * @param viewer
     *            The viewer to register menu for.
     * @param site
     *            The workbench part to link the selection provider.
     */
    private void initContextMenu(final TreeViewer viewer, IWorkbenchPartSite site) {

        MenuManager menuManager = new MenuManager();
        menuManager.setRemoveAllWhenShown(true);
        menuManager.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(IMenuManager manager) {
                IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
                if (!selection.isEmpty() && selection.getFirstElement() instanceof Refinement) {
                    Action renameAction = new RenameRefinementAction(viewer);
                    manager.add(renameAction);
                }
            }
        });
        menuManager.addMenuListener(new CommandActionMenuListener(COMMAND_ID_OPENSOURCELOCATION, SPLevoUIPlugin
                .getImageDescriptor("icons/jcu_obj.gif")));
        menuManager.addMenuListener(new CommandActionMenuListener("org.splevo.ui.commands.argouml.variantscan",
                SPLevoUIPlugin.getImageDescriptor("icons/kopl_circle_only.png")));

        Menu menu = menuManager.createContextMenu(viewer.getTree());
        viewer.getTree().setMenu(menu);
        site.setSelectionProvider(viewer);
    }

    private void initToolTips(TreeViewer viewer) {
        new TreeViewerToolTip(viewer, CustomizableDescriptionHaving.class) {

            @Override
            protected boolean shouldCreateToolTip(Event event) {
                if (!super.shouldCreateToolTip(event)) {
                    return false;
                }
                
                CustomizableDescriptionHaving item = getCorrectlyTypedItem(event, CustomizableDescriptionHaving.class);
                if (Strings.isNullOrEmpty(item.getDescription())) {
                    return false;
                }
                
                return true;
            }
            
            @Override
            protected String getText(Event event) {
                CustomizableDescriptionHaving item = getCorrectlyTypedItem(event, CustomizableDescriptionHaving.class);
                return item.getDescription();
            }

            @Override
            protected String getToolTipHeading(Event event) {
                return "Description";
            }

        };
    }


}

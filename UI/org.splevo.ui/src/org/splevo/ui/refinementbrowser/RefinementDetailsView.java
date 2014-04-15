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

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.SWTResourceManager;
import org.splevo.ui.Activator;
import org.splevo.ui.util.UIUtil;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Strings;

/**
 * Details view to show information about a currently selected refinement.
 */
public class RefinementDetailsView extends Composite {

    private static final String COMMAND_ID_OPENSOURCELOCATION = "org.splevo.ui.commands.opensourcelocation";
    private static final String REFINEMENT_INFO_DEFAULT_TEXT = "Select refinement on the left to review details.";

    /** The internal tree viewer to present the refined variation points. */
    private TreeViewer refinementDetailsTreeViewer = null;

    /** The area to present info about a currently selected refinement in. */
    private StyledText refinementInfoArea = null;

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
        sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));

        refinementDetailsTreeViewer = new TreeViewer(sashForm, SWT.MULTI);
        refinementDetailsTreeViewer.setLabelProvider(new RefinementDetailsLabelProvider());
        refinementDetailsTreeViewer.setContentProvider(new RefinementDetailsTreeContentProvider());
        refinementDetailsTreeViewer.addDoubleClickListener(new ExpandTreeListener());
        initContextMenu(refinementDetailsTreeViewer, site);

        refinementInfoArea = new StyledText(sashForm, SWT.WRAP);
        refinementInfoArea.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        refinementInfoArea.setText(REFINEMENT_INFO_DEFAULT_TEXT);

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
        updateRefinementInfo(refinement);
    }

    private void updateRefinementInfo(Refinement refinement) {

        String headline = "Refinement Infos";
        String linebreak = "\r\n";
        String subHeadlineReason = "Source: ";

        StringBuilder text = new StringBuilder();
        text.append(headline);
        text.append(linebreak);
        text.append(linebreak);
        text.append(subHeadlineReason);
        text.append(linebreak);
        text.append(refinement.getSource());

        refinementInfoArea.setText(text.toString());

        StyleRange styleRange = new StyleRange();
        styleRange.start = 0;
        styleRange.length = headline.length();
        styleRange.fontStyle = SWT.BOLD;
        styleRange.underline = true;
        styleRange.underlineStyle = SWT.UNDERLINE_DOUBLE;
        refinementInfoArea.setStyleRange(styleRange);

        styleRange = new StyleRange();
        styleRange.start = headline.length() + (2 * linebreak.length());
        styleRange.length = subHeadlineReason.length();
        styleRange.fontStyle = SWT.BOLD;
        styleRange.underline = true;
        styleRange.underlineStyle = SWT.UNDERLINE_SINGLE;
        refinementInfoArea.setStyleRange(styleRange);
    }

    /**
     * The content provider for the tree providing access to the variation points and their child
     * elements.
     */
    private static class RefinementDetailsTreeContentProvider implements ITreeContentProvider {

        /** The refinement to display in the tree. */
        private Refinement refinement = null;

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            this.refinement = (Refinement) newInput;
        }

        @Override
        public Object[] getElements(Object inputElement) {
            return refinement.getVariationPoints().toArray();
        }

        @Override
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof Refinement) {
                return ((Refinement) parentElement).getVariationPoints().toArray();
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

        @Override
        public boolean hasChildren(Object element) {
            return getChildren(element).length > 0;
        }

        @Override
        public void dispose() {
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
     * DesignDecision Menu created programmatically instead of extension point to prevent context
     * menu mess up by other plugins.
     *
     * DesignDecision Reused command for common look and feel of context menu item for complete
     * application
     *
     * @param viewer
     *            The viewer to register menu for.
     * @param site
     *            The workbench part to link the selection provider.
     */
    private void initContextMenu(TreeViewer viewer, IWorkbenchPartSite site) {

        MenuManager menuManager = new MenuManager();
        menuManager.setRemoveAllWhenShown(true);
        menuManager.addMenuListener(new CommandActionMenuListener(COMMAND_ID_OPENSOURCELOCATION, Activator
                .getImageDescriptor("icons/jcu_obj.gif")));
        Menu menu = menuManager.createContextMenu(viewer.getTree());
        viewer.getTree().setMenu(menu);
        site.setSelectionProvider(viewer);

    }
}

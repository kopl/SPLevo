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
package org.splevo.ui.dashboard;

import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.header.Header;
import org.mihalis.opal.itemSelector.DLItem;
import org.mihalis.opal.itemSelector.DualListSelectionChangedListener;
import org.mihalis.opal.itemSelector.SPLevoDualList;
import org.splevo.project.ProjectFactory;
import org.splevo.project.SPLProfile;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringRegistry;
import org.splevo.ui.SPLevoUIPlugin;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.vpm.realization.VariabilityMechanism;

import com.google.common.collect.Lists;

/**
 * The product line configuration tab container. This is a pojo to create and manage the tab for
 * configuring the SPL Profile.
 */
public class SPLProfileTab extends AbstractDashboardTab {

    private static final String SPLPROFILE_CONFIG_ID_REFACTORING_DATA = "refactoring";

    /**
     * Create the tab to handle the SPL profile configuration.
     *
     * @param splevoProjectEditor
     *            The editor to access when configuration is modified.
     *
     * @param tabFolder
     *            The folder to add the tab to.
     *
     * @param tabIndex
     *            The index of the tab within the parent tab folder.
     */
    public SPLProfileTab(SPLevoProjectEditor splevoProjectEditor, TabFolder tabFolder, int tabIndex) {
        super(splevoProjectEditor);
        createTab(tabFolder, tabIndex);
    }

    /**
     * Create the tab.
     *
     * @param tabFolder
     *            The folder to add the tab to.
     * @param tabIndex
     *            The index of the tab within the parent tab folder.
     */
    private void createTab(TabFolder tabFolder, int tabIndex) {
        TabItem configurationTab = new TabItem(tabFolder, SWT.NONE, tabIndex);
        configurationTab.setText("SPL Profile");

        ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        configurationTab.setControl(scrolledComposite);

        Composite composite = new Composite(scrolledComposite, SWT.FILL);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(GridData.FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));

        createHeader(composite);
        createMechanismGroup(composite);

        scrolledComposite.setContent(composite);
        scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

    }

    private void createMechanismGroup(Composite composite) {
        Group mechanismGroup = new Group(composite, SWT.FILL);
        mechanismGroup.setText("Variability Mechanisms");
        mechanismGroup.setFont(getModifiedFont(mechanismGroup, SWT.BOLD));
        mechanismGroup.setLayout(new GridLayout(1, false));
        mechanismGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

        Label selectionInfo = new Label(mechanismGroup, SWT.NONE);
        selectionInfo.setText("Select the variability mechanisms to be used in the target product line.\r\n"
                + "Left: available ones, Right: selected ones. The order defines the priority");

        final SPLevoDualList dl = new SPLevoDualList(mechanismGroup, SWT.BORDER | SWT.BACKGROUND);
        dl.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
        final List<DLItem> refactoringList = Lists.newArrayList();
        List<Integer> selectedIds = Lists.newArrayList();
        for (VariabilityRefactoring refactoring : VariabilityRefactoringRegistry.getRefactorings()) {
            VariabilityMechanism mechanism = refactoring.getVariabilityMechanism();
            DLItem item = new DLItem(mechanism.getName());
            item.setData(SPLPROFILE_CONFIG_ID_REFACTORING_DATA, refactoring);
            refactoringList.add(item);
            if (getSPLProfile().getRecommendedRefactoringIds().contains(refactoring.getId())) {
                selectedIds.add(refactoringList.indexOf(item));
            }
        }

        dl.setItems(refactoringList);
        for (Integer index : selectedIds) {
            dl.select(index.intValue());
        }

        dl.addDualListSelectionChangedListener(new DualListSelectionChangedListener() {

            @Override
            public void itemsSelected(LinkedHashSet<DLItem> selectedItems) {

                boolean modified = false;

                for (DLItem item : selectedItems) {
                    Object data = item.getData(SPLPROFILE_CONFIG_ID_REFACTORING_DATA);
                    if (data instanceof VariabilityRefactoring) {
                        VariabilityRefactoring refactoring = (VariabilityRefactoring) data;
                        EList<String> recommendedRefactoringIds = getSPLProfile().getRecommendedRefactoringIds();
                        if (!recommendedRefactoringIds.contains(refactoring.getId())) {
                            recommendedRefactoringIds.add(refactoring.getId());
                            modified = true;
                        }
                    }
                }

                if (modified) {
                    getSplevoProjectEditor().markAsDirty();
                }

            }

            @Override
            public void itemsDeSelected(LinkedHashSet<DLItem> deSelectedItems) {

                boolean modified = false;

                for (DLItem item : deSelectedItems) {
                    Object data = item.getData(SPLPROFILE_CONFIG_ID_REFACTORING_DATA);
                    if (data instanceof VariabilityRefactoring) {
                        VariabilityRefactoring refactoring = (VariabilityRefactoring) data;
                        EList<String> recommendedRefactoringIds = getSPLProfile().getRecommendedRefactoringIds();
                        if (recommendedRefactoringIds.contains(refactoring.getId())) {
                            recommendedRefactoringIds.remove(refactoring.getId());
                            modified = true;
                        }
                    }
                }

                if (modified) {
                    getSplevoProjectEditor().markAsDirty();
                }

            }
        });

    }

    private void createHeader(Composite composite) {
        final Header header = new Header(composite, SWT.NONE);
        header.setTitle("SPL Profile");
        header.setImage(ResourceManager.getPluginImage(SPLevoUIPlugin.PLUGIN_ID, "icons/configure.png"));
        header.setDescription("Product-Line parameters as overarching company or project guidelines.");
        header.setLayoutData(new GridData(GridData.FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
    }

    /**
     * Get the current font of a modified according to the provided style parameter (e.g. SWT.BOLD)
     *
     * @param control
     *            The control to get the current font of.
     * @return The modified font according to the provided style parameter.
     */
    private Font getModifiedFont(Control control, int style) {
        FontData fontData = control.getFont().getFontData()[0];
        Font modifiedFont = new Font(Display.getCurrent(),
                new FontData(fontData.getName(), fontData.getHeight(), style));
        return modifiedFont;
    }

    private final SPLProfile getSPLProfile() {
        SPLProfile splProfile = getSPLevoProject().getSplProfile();
        if (splProfile == null) {
            splProfile = ProjectFactory.eINSTANCE.createSPLProfile();
            getSPLevoProject().setSplProfile(splProfile);
        }
        return splProfile;
    }

}

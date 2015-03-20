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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.splevo.diffing.Differ;
import org.splevo.diffing.DifferRegistry;
import org.splevo.fm.builder.FeatureModelBuilder;
import org.splevo.fm.builder.FeatureModelBuilderRegistry;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.editors.listener.DifferCheckBoxListener;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The dash board tab to configure the consolidation process.
 * 
 */
public class ConfigurationTab extends AbstractDashboardTab {

    /**
     * Create the tab to handle the SPL profile configuration.
     * 
     * @param splevoProjectEditor
     *            The editor to access when configuration is modified.
     * @param tabFolder
     *            The folder to add the tab to.
     * 
     * @param tabIndex
     *            The index of the tab within the parent tab folder.
     */
    public ConfigurationTab(SPLevoProjectEditor splevoProjectEditor, TabFolder tabFolder, int tabIndex) {
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
        configurationTab.setText("Configuration");

        ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);

        Composite composite = new Composite(scrolledComposite, SWT.FILL);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

        buildFeatureModelBuilderGroup(composite);
        buildDifferConfigurationGroup(composite);
        scrolledComposite.setContent(composite);
        scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        configurationTab.setControl(scrolledComposite);
    }

    private Group buildFeatureModelBuilderGroup(Composite composite) {
        Group group = new Group(composite, SWT.FILL);
        group.setText("FeatureModel Builders");
        group.setLayout(new GridLayout(2, false));
        group.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        Label configLabel = new Label(group, SWT.NONE);
        configLabel.setText("Feature Model Builder:");
        //configLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));

        final Combo fmBuilderCombo = new Combo(group, SWT.READ_ONLY | SWT.DROP_DOWN);
        //fmBuilderCombo.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));
        for (FeatureModelBuilder<?> fmBuilder : FeatureModelBuilderRegistry.getInstance().getElements()) {
            fmBuilderCombo.add(fmBuilder.getLabel());
        }

        FeatureModelBuilder<Object> firstFmBuilderInRegistry = Iterables.getFirst(FeatureModelBuilderRegistry
                .getInstance().getElements(), null);
        FeatureModelBuilder<Object> defaultFMBuilderInProject = FeatureModelBuilderRegistry.getInstance()
                .getElementById(getSPLevoProject().getFmBuilderId());
        final FeatureModelBuilder<Object> activeFmBuilder = defaultFMBuilderInProject == null ? firstFmBuilderInRegistry
                : defaultFMBuilderInProject;
        if (activeFmBuilder != null) {
            int index = Iterables.indexOf(Lists.newArrayList(fmBuilderCombo.getItems()), new Predicate<String>() {
                @Override
                public boolean apply(String itemLabel) {
                    return itemLabel.equals(activeFmBuilder.getLabel());
                }
            });
            fmBuilderCombo.select(index);
            getSPLevoProject().setFmBuilderId(activeFmBuilder.getId());
        }
        
        fmBuilderCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                String fmBuilderId = FeatureModelBuilderRegistry.getInstance().getIdByLabel(fmBuilderCombo.getText());
                if (fmBuilderId != null && !getSPLevoProject().getFmBuilderId().equals(fmBuilderId)) {
                    getSPLevoProject().setFmBuilderId(fmBuilderId);
                    getSplevoProjectEditor().markAsDirty();
                }
            }
        });

        return group;
    }

    /**
     * Build a ui group presenting check boxes to (de-)activate the extractors to executed or not.
     * 
     * @param composite
     *            The parent ui element to place on.
     * 
     * @return The newly created group.
     */
    private Group buildDifferConfigurationGroup(Composite composite) {

        Group groupDiffers = new Group(composite, SWT.FILL);
        groupDiffers.setText("Difference Analysis");
        groupDiffers.setLayout(new GridLayout(2, false));
        groupDiffers.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

        List<Button> differCheckBoxes = new LinkedList<Button>();

        List<Differ> availableDiffers = DifferRegistry.getInstance().getElements();

        int singleHeight = 20;
        int multipleHeight = 100;
        int yPositionCurrent = 5;

        if (availableDiffers.size() == 1) {
            getSPLevoProject().getDifferIds().add(availableDiffers.get(0).getId());
        }

        for (Differ differ : availableDiffers) {

            Button checkBox = new Button(groupDiffers, SWT.CHECK);
            GridData gridData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
            gridData.horizontalSpan = 2;
            checkBox.setLayoutData(gridData);
            checkBox.addSelectionListener(new DifferCheckBoxListener(checkBox, differ.getId(), getSplevoProjectEditor()));
            checkBox.setBounds(10, yPositionCurrent, 450, singleHeight);
            checkBox.setText(differ.getLabel());
            differCheckBoxes.add(checkBox);
            boolean selected = getSPLevoProject().getDifferIds().contains(differ.getId());
            checkBox.setSelection(selected);

            yPositionCurrent = yPositionCurrent + (singleHeight + 5);

            for (final String configKey : differ.getAvailableConfigurations().keySet()) {

                String defaultValue = differ.getAvailableConfigurations().get(configKey);
                String currentValue = getSPLevoProject().getDifferOptions().get(configKey);
                String label = getLabelFromKey(configKey);

                Label configLabel = new Label(groupDiffers, SWT.NONE);
                configLabel.setBounds(10, yPositionCurrent, 450, singleHeight);
                configLabel.setText(label + ":");
                configLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

                yPositionCurrent = yPositionCurrent + (singleHeight + 3);

                Text configInput = new Text(groupDiffers, SWT.BORDER | SWT.V_SCROLL);
                configInput.setBounds(10, yPositionCurrent, 450, multipleHeight);
                configInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
                if (currentValue != null) {
                    configInput.setText(currentValue);
                } else {
                    configInput.setText(defaultValue);
                }
                configInput.addModifyListener(new ModifyListener() {

                    @Override
                    public void modifyText(ModifyEvent event) {
                        Text text = (Text) event.widget;
                        getSPLevoProject().getDifferOptions().put(configKey, text.getText());
                        getSplevoProjectEditor().markAsDirty();
                    }
                });

                yPositionCurrent = yPositionCurrent + (multipleHeight + 5);

            }

        }

        groupDiffers.setBounds(10, 100, 490, yPositionCurrent + 5);

        return groupDiffers;
    }

    /**
     * Extract a label from a configuration key. The key will be split and concatenated again
     * separated by whitespaces.
     * 
     * @param configKey
     *            The key convert.
     * @return The resulting label.
     */
    private String getLabelFromKey(String configKey) {
        Splitter splitter = Splitter.on(CharMatcher.anyOf(".")).omitEmptyStrings().trimResults();
        Joiner joiner = Joiner.on(" ").skipNulls();
        return joiner.join(splitter.split(configKey));
    }

}

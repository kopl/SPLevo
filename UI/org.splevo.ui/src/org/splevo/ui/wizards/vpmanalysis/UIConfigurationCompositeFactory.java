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
 *    Daniel Kojic
 *    Christian Busch
 *******************************************************************************/
package org.splevo.ui.wizards.vpmanalysis;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.splevo.ui.util.UIUtil;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.config.AbstractVPMAnalyzerConfiguration;
import org.splevo.vpm.analyzer.config.BooleanConfiguration;
import org.splevo.vpm.analyzer.config.ChoiceConfiguration;
import org.splevo.vpm.analyzer.config.NumericConfiguration;
import org.splevo.vpm.analyzer.config.StringConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;

/**
 * This class has methods to create the configuration panel for the analysis configuration wizard.
 *
 */
public class UIConfigurationCompositeFactory {

    /**
     * The {@link VPMAnalyzer} to get the configs from.
     */
    private VPMAnalyzer analyzer;

    /**
     * @param analyzer
     *            The {@link VPMAnalyzer} to get the configs from.
     */
    public UIConfigurationCompositeFactory(VPMAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    /**
     * Takes a {@link Composite} and adds the configuration elements as children.
     *
     * @param parent
     *            The {@link Composite}.
     */
    public void createConfigComps(Composite parent) {
        VPMAnalyzerConfigurationSet configurations = analyzer.getConfigurations();
        Map<String, List<AbstractVPMAnalyzerConfiguration<?>>> configsByGroupName = configurations
                .getAllConfigurationsByGroupName();

        for (String groupName : configsByGroupName.keySet()) {
            createConfigurationGroup(parent, groupName, configsByGroupName.get(groupName));
        }
    }

    /**
     * Creates a named group that has configurations.
     *
     * @param parent
     *            The parent {@link Composite}.
     * @param groupName
     *            The name of the group.
     * @param configs
     *            The UI configuration elements to be added to the group.
     */
    private void createConfigurationGroup(Composite parent, String groupName,
            List<AbstractVPMAnalyzerConfiguration<?>> configs) {
        Group group = new Group(parent, SWT.NONE);
        group.setText(groupName);
        GridLayout groupGridLayout = new GridLayout(1, true);
        groupGridLayout.verticalSpacing = 0;
        group.setLayout(groupGridLayout);

        FormData formData = new FormData();
        if (parent.getChildren().length == 1) {
            formData.top = new FormAttachment(0);
        } else {
            Control lastGroup = parent.getChildren()[parent.getChildren().length - 2];
            formData.top = new FormAttachment(lastGroup, 10);
        }
        formData.left = new FormAttachment(0);
        formData.right = new FormAttachment(100);
        group.setLayoutData(formData);

        for (AbstractVPMAnalyzerConfiguration<?> config : configs) {
            Composite parentComp = new Composite(group, SWT.FILL);
            parentComp.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
            GridLayout parentCompLayout = new GridLayout(3, false);
            parentCompLayout.verticalSpacing = 0;
            parentComp.setLayout(parentCompLayout);

            if (config instanceof BooleanConfiguration) {
                createBooleanConfigField(parentComp, (BooleanConfiguration) config);
            } else if (config instanceof StringConfiguration) {
                createStringConfigField(parentComp, (StringConfiguration) config);
            } else if (config instanceof ChoiceConfiguration) {
                createChoiceConfigField(parentComp, (ChoiceConfiguration) config);
            } else if (config instanceof NumericConfiguration) {
                createNumericConfigField(parentComp, (NumericConfiguration) config);
            }
        }
    }

    /**
     * Creates the configuration elements for a numeric configuration. Adds them to a parent
     * element.
     *
     * This includes changing the background if the configured value is out of the valid range.
     *
     * @param parent
     *            The parent.
     * @param config
     *            The numeric configuration.
     */
    private void createNumericConfigField(Composite parent, final NumericConfiguration config) {
        final int decimalPlaces = 0;
        
        addLabel(parent, config.getLabel(), false);
        final Spinner spinner = new Spinner(parent, SWT.NONE);
        spinner.setIncrement(config.getStepSize());
        spinner.setDigits(decimalPlaces);
        spinner.setMinimum(config.getLowerBoundary());
        spinner.setMaximum(config.getUpperBoundary());
        spinner.setSelection(config.getDefaultValue());
        spinner.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent arg0) {
                int spinnerValue = spinner.getSelection();
                config.setCurrentValue(spinnerValue);
            }

        });
        
        GridData layoutData = new GridData(SWT.BEGINNING, SWT.CENTER, true, false);
        layoutData.widthHint = 50;
        spinner.setLayoutData(layoutData);

        UIUtil.addExplanation(parent, config.getExplanation());
    }

    /**
     * Creates the configuration elements for a choice configuration. Adds them to a parent element.
     *
     * @param parent
     *            The parent.
     * @param config
     *            The choice configuration.
     */
    private void createChoiceConfigField(Composite parent, final ChoiceConfiguration config) {
        addLabel(parent, config.getLabel(), false);
        final List<String> labels = config.getAvailableValues();
        final Combo combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
        combo.setItems(labels.toArray(new String[0]));
        combo.select(labels.indexOf(config.getDefaultValue()));
        combo.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                String selectedValue = labels.get(combo.getSelectionIndex());
                config.setCurrentValue(selectedValue);
            }
        });
        combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        UIUtil.addExplanation(parent, config.getExplanation());
    }

    /**
     * Creates the configuration elements for a string configuration. Adds them to a parent element.
     *
     * @param parent
     *            The parent.
     * @param config
     *            The string configuration.
     */
    private void createStringConfigField(Composite parent, final StringConfiguration config) {
        addLabel(parent, config.getLabel(), false);
        final StyledText text = new StyledText(parent, SWT.V_SCROLL | SWT.BORDER);
        text.setLeftMargin(2);
        text.setRightMargin(2);
        text.setTopMargin(2);
        text.setBottomMargin(2);
        text.setWordWrap(true);
        text.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent arg0) {
                config.setCurrentValue(text.getText());
            }
        });
        String defaultText = config.getDefaultValue();
        text.setText(defaultText);
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        layoutData.widthHint = SWT.FILL;
        if (config.isSingleLine()) {
            layoutData.heightHint = 16;
        } else {
            layoutData.heightHint = 80;
        }
        text.setLayoutData(layoutData);
        UIUtil.addExplanation(parent, config.getExplanation());
    }

    /**
     * Creates the configuration elements for a boolean configuration. Adds them to a parent
     * element.
     *
     * @param parent
     *            The parent.
     * @param config
     *            The boolean configuration.
     */
    private void createBooleanConfigField(Composite parent, final BooleanConfiguration config) {
        final Button checkButton = new Button(parent, SWT.CHECK);
        checkButton.setSelection(config.getDefaultValue());
        checkButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                config.setCurrentValue(checkButton.getSelection());
            }
        });
        addLabel(parent, config.getLabel(), true);
        UIUtil.addExplanation(parent, config.getExplanation());
    }

    /**
     * Adds a label with given text to a composite.
     *
     * @param parent
     *            The composite to add the label to.
     * @param text
     *            The text of the label.
     * @param grabHorizontalSpace Whether cell will be made wide enough to fit the remaining horizontal space.
     */
    private void addLabel(Composite parent, String text, boolean grabHorizontalSpace) {
        Label label = new Label(parent, SWT.WRAP);
        label.setText(text);
        label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, grabHorizontalSpace, false));
    }
}

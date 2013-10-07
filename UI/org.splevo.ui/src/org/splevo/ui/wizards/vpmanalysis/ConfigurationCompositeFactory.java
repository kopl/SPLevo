package org.splevo.ui.wizards.vpmanalysis;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.config.AbstractVPMAnalyzerConfiguration;
import org.splevo.vpm.analyzer.config.BooleanConfiguration;
import org.splevo.vpm.analyzer.config.ChoiceConfiguration;
import org.splevo.vpm.analyzer.config.NumericConfiguration;
import org.splevo.vpm.analyzer.config.StringConfiguration;
import org.splevo.vpm.analyzer.config.VPMAnalyzerConfigurationSet;

public class ConfigurationCompositeFactory {

	private VPMAnalyzer analyzer;

	public ConfigurationCompositeFactory(VPMAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

	public void createConfigComps(Composite parent) {
		VPMAnalyzerConfigurationSet configurations = analyzer.getConfigurations();
		Map<String, List<AbstractVPMAnalyzerConfiguration<?>>> configsByGroupName = configurations
				.getAllConfigurationsByGroupName();

		for (String groupName : configsByGroupName.keySet()) {
			createConfigurationGroup(parent, groupName,
					configsByGroupName.get(groupName));
		}
	}

	private void createConfigurationGroup(Composite parent, String groupName,
			List<AbstractVPMAnalyzerConfiguration<?>> configs) {
		Group group = new Group(parent, SWT.NONE);
		group.setText(groupName);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		rowLayout.justify = false;
		rowLayout.fill = false;
		rowLayout.wrap = false;
		group.setLayout(rowLayout);
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

		for (AbstractVPMAnalyzerConfiguration config : configs) {
			Composite parentComp = new Composite(group, SWT.NONE);
			RowLayout layout = new RowLayout(SWT.HORIZONTAL);
			layout.center = true;
			parentComp.setLayout(layout);
			if (config instanceof BooleanConfiguration) {
				createBooleanConfigField(parentComp,
						(BooleanConfiguration) config);
			} else if (config instanceof StringConfiguration) {
				createStringConfigField(parentComp,
						(StringConfiguration) config);
			} else if (config instanceof ChoiceConfiguration) {
				createChoiceConfigField(parentComp,
						(ChoiceConfiguration) config);
			} else if (config instanceof NumericConfiguration) {
				createNumericConfigField(parentComp,
						(NumericConfiguration) config);
			}
		}
	}

	private void createNumericConfigField(Composite parent,
			final NumericConfiguration config) {
		addLabel(parent, config.getLabel());
		final double spinnerTransformationFactor = Math.pow(10,
				config.getNumFractionalDigits());
		final Spinner spinner = new Spinner(parent, SWT.NONE);
		spinner.setIncrement((int) Math.round(config.getStepSize()
				* spinnerTransformationFactor));
		spinner.setDigits(config.getNumFractionalDigits());
		int value = (int) Math.round(config.getDefaultValue()
				* spinnerTransformationFactor);
		spinner.setSelection(value);
		spinner.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				double spinnerValue = (double) spinner.getSelection()
						/ spinnerTransformationFactor;
				if (spinnerValue >= config.getLowerBoundary()
						&& spinnerValue <= config.getUpperBoundary()) {
					config.setCurrentValue(spinnerValue);
					spinner.setBackground(spinner.getDisplay().getSystemColor(
							SWT.COLOR_LIST_BACKGROUND));
				} else {
					spinner.setBackground(new Color(spinner.getDisplay(), 255,
							0, 0));
				}
			}
		});

		addExplanation(parent, config.getExplanation());
	}

	private void createChoiceConfigField(Composite parent,
			final ChoiceConfiguration config) {
		addLabel(parent, config.getLabel());
		final List<String> labels = config.getAvailableValues();
		final Combo combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		combo.setItems(labels.toArray(new String[0]));
		combo.select(labels.indexOf(config.getDefaultValue()));
		combo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String selectedValue = labels.get(combo.getSelectionIndex());
				config.setCurrentValue(selectedValue);
			}
		});
		addExplanation(parent, config.getExplanation());
	}

	private void createStringConfigField(Composite parent,
			final StringConfiguration config) {
		addLabel(parent, config.getLabel());
		final StyledText text = new StyledText(parent, SWT.V_SCROLL);
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
		if (defaultText.length() > 30) {
			text.setLayoutData(new RowData(150, 80));
		} else {
			text.setLayoutData(new RowData(150, 16));
		}
		addExplanation(parent, config.getExplanation());
	}

	private void createBooleanConfigField(Composite parent,
			final BooleanConfiguration config) {
		final Button checkButton = new Button(parent, SWT.CHECK);
		checkButton.setSelection(config.getDefaultValue());
		checkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				config.setCurrentValue(checkButton.getSelection());
			}
		});
		addLabel(parent, config.getLabel());
		addExplanation(parent, config.getExplanation());
	}

	private void addLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.WRAP);
		label.setText(text);
	}

	private void addExplanation(Composite parent, String explanation) {
		Label explanationLabel = new Label(parent, SWT.NONE);
		explanationLabel.setImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_LCL_LINKTO_HELP));
		explanationLabel.setToolTipText(explanation);

		if (explanation == null || explanation.length() == 0) {
			explanationLabel.setVisible(false);
		}
	}
}

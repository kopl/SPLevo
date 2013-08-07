package org.splevo.ui.wizards.vpmanalysis;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.config.ChoiceConfigDefintion;
import org.splevo.vpm.analyzer.config.ConfigDefinition;
import org.splevo.vpm.analyzer.config.DoubleConfigDefinition;
import org.splevo.vpm.analyzer.config.IntegerConfigDefinition;

public class ConfigurationCompositeFactory {
	
	private VPMAnalyzer analyzer;

	public ConfigurationCompositeFactory(VPMAnalyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	public void createConfigComps(Composite parent) {
		Map<String, ConfigDefinition> availableConfigurations = analyzer.getAvailableConfigurations();
		Map<String, Object> configurations = analyzer.getConfigurations();
		Map<String, String> configurationLabels = analyzer.getConfigurationLabels();
		String[] keys = availableConfigurations.keySet().toArray(new String[0]);
		for (int i = 0; i < keys.length; i++) {
			ConfigDefinition configDef = availableConfigurations.get(keys[i]);
			String label = configurationLabels.get(keys[i]);
			if (configDef instanceof ChoiceConfigDefintion) {
				createComboBoxComposite(parent, keys[i], label, (ChoiceConfigDefintion)configDef, configurations);
			} else if (configDef instanceof IntegerConfigDefinition) {
				createIntegerFieldComposite(parent, keys[i], label, (IntegerConfigDefinition) configDef, configurations);	
			} else if (configDef instanceof DoubleConfigDefinition) {
				createDoubleFieldComposite(parent, keys[i], label, (DoubleConfigDefinition) configDef, configurations);	
			} else {
				createTextFieldComposite(parent, keys[i], label, configDef, configurations);								
			}
			if(i != (keys.length - 1)) {
				insertSeparationLine(parent);				
			}
		}
	}

	private void insertSeparationLine(Composite parent) {
		Label separatorLine = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		separatorLine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
	}

	private void createDoubleFieldComposite(Composite parent, final String key,
			String label, final DoubleConfigDefinition configDef,
			final Map<String, Object> configurations) {
		addLabel(parent, label);
		final Spinner spinner = new Spinner(parent, SWT.NONE);
		Double value = (Double) configurations.get(key);
		spinner.setIncrement(1);
		spinner.setDigits(2);
		spinner.setSelection((int) (value * 100.d));
		spinner.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				double spinnerValue = spinner.getSelection() / 100.d;
				if (configDef.isValidValue(spinnerValue)) {
					configurations.put(key, spinnerValue);
					spinner.setBackground(spinner.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
				} else {
					spinner.setBackground(new Color(spinner.getDisplay(), 255, 0, 0));
				}
			}
		});
	}

	private void createIntegerFieldComposite(Composite parent, final String key,
			String label, final IntegerConfigDefinition configDef,
			final Map<String, Object> configurations) {
		addLabel(parent, label);
		final Spinner spinner = new Spinner(parent, SWT.NONE);
		Integer value = (Integer) configurations.get(key);
		spinner.setIncrement(1);
		spinner.setSelection(value);
		spinner.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				if (configDef.isValidValue(spinner.getSelection())) {
					configurations.put(key, (Object) configDef.convertValue(spinner.getText()));
					spinner.setBackground(spinner.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
				} else {
					spinner.setBackground(new Color(spinner.getDisplay(), 255, 0, 0));
				}
			}
		});
	}

	private void createTextFieldComposite(Composite parent, final String key, final String label,
			final ConfigDefinition configDef,
			final Map<String, Object> configurations) {
		addLabel(parent, label);
		final StyledText text = new StyledText(parent, SWT.V_SCROLL);
		text.setLeftMargin(2);
		text.setRightMargin(2);
		text.setTopMargin(2);
		text.setBottomMargin(2);
		text.setWordWrap(true);
		text.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				if (configDef.isValidValue(text.getText())) {
					configurations.put(key, (Object) configDef.convertValue(text.getText()));
					text.setBackground(text.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
				} else {
					text.setBackground(new Color(text.getDisplay(), 255, 0, 0));
				}				
			}
		});
		String fieldValue = configurations.get(key).toString();
		text.setText(fieldValue);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.minimumWidth = 150;
		if (fieldValue.length() > 30) {
			gridData.heightHint = 80;
		} else {
			gridData.heightHint = 16;
		}
		text.setLayoutData(gridData);
	}

	@SuppressWarnings({ "rawtypes" })
	private void createComboBoxComposite(Composite parent, final String key, final String label,
			final ChoiceConfigDefintion configDef,
			final Map<String, Object> configurations) {		
		addLabel(parent, label);
		List<String> labels = new LinkedList<String>();
        for (final Object value : configDef.getAvailableValues().values()) {
            labels.add(value.toString());
        }
        
        final Combo combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
        combo.setItems(labels.toArray(new String[0]));
        combo.select(labels.indexOf(configurations.get(key).toString()));
        combo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Object value = configDef.getValueForIndex(combo.getSelectionIndex());
				configurations.put(key, value);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Ignore
			}
		});
	}
	
	private void addLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.WRAP);
		label.setText(text);
		Point size = label.computeSize(140, SWT.DEFAULT);
		label.setLayoutData(new GridData(size.x, size.y));
	}
}

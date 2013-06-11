/**
 * 
 */
package org.splevo.ui.wizards.vpmanalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.config.ChoiceConfigDefintion;
import org.splevo.vpm.analyzer.config.ConfigDefinition;

/**
 * Editing support to modify the configuration values within the config table of the analyzer
 * configuration dialog.
 */
public class VPMAnalyzerConfigurationEditingSupport extends EditingSupport {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VPMAnalyzerConfigurationEditingSupport.class);

    /** The table viewer this editing support is assigned to. */
    private final TableViewer viewer;

    /** The analyzer currently modified. */
    private VPMAnalyzer analyzer = null;

    /**
     * Constructor setting the reference to the enclosing viewer.
     * 
     * @param viewer
     *            The viewer to place the cell in.
     */
    public VPMAnalyzerConfigurationEditingSupport(TableViewer viewer) {
        super(viewer);
        this.viewer = viewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected CellEditor getCellEditor(Object element) {
        Entry<String, ConfigDefinition> e = (Entry<String, ConfigDefinition>) element;
        if (e.getValue() instanceof ChoiceConfigDefintion) {
            ChoiceConfigDefintion configDef = (ChoiceConfigDefintion) e.getValue();
            List<String> labels = new ArrayList<String>();
            for (Object label : configDef.getAvailableValues().values()) {
                labels.add(label.toString());
            }
            return new ComboBoxCellEditor(viewer.getTable(), labels.toArray(new String[] {}));

        } else {
            return new TextCellEditor(viewer.getTable());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(Object element) {
        @SuppressWarnings("unchecked")
        Entry<String, ConfigDefinition> e = (Entry<String, ConfigDefinition>) element;

        if (analyzer == null) {
            logger.error("No analyzer reference set");
            return null;
        }

        Object value = analyzer.getConfigurations().get(e.getKey());
        return getEditorValue(e.getValue(), value);
    }

    /**
     * Translate the configuration value into a representation for the editor.
     * 
     * @param configDefinition
     *            The definition of the configuration.
     * @param value
     *            The value translate.
     * @return The translated value.
     */
    @SuppressWarnings("rawtypes")
    private Object getEditorValue(ConfigDefinition configDefinition, Object value) {

        // handle choice definitions as special case
        if (configDefinition instanceof ChoiceConfigDefintion) {
            return getEditorValueChoiceConfiguration((ChoiceConfigDefintion) configDefinition, value);

        }

        if (value != null) {
            return value.toString();

        } else if (configDefinition.getDefaultValue() != null) {
            return configDefinition.getDefaultValue().toString();

        } else {
            return "";
        }
    }

    /**
     * Get the value to present in the editor for a value of a choice configuration parameter.
     * 
     * @param choiceDefinition
     *            The definition of the choice configuration parameter.
     * @param value
     *            The value to be translated.
     * @return The index of the value in the choice definition.
     */
    @SuppressWarnings("rawtypes")
    private Integer getEditorValueChoiceConfiguration(ChoiceConfigDefintion choiceDefinition, Object value) {

        if (value != null) {
            return choiceDefinition.getIndexOfValue(value);

        } else if (choiceDefinition.getDefaultValue() != null) {
            return choiceDefinition.getIndexOfValue(choiceDefinition.getDefaultValue());
        } else {

            return 0;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected void setValue(Object element, Object value) {
        Entry<String, ConfigDefinition> e = (Entry<String, ConfigDefinition>) element;

        ConfigDefinition configDef = e.getValue();
        
        // In case of a choice config switch the value from the identifying label to the real value
        if (configDef instanceof ChoiceConfigDefintion) {
            value = ((ChoiceConfigDefintion) e.getValue()).getValueForIndex((Integer) value);
        } else {
            value = configDef.convertValue(value.toString());
        }

        analyzer.getConfigurations().put(e.getKey(), value);
        viewer.update(element, null);
    }

    /**
     * @return the analyzer
     */
    public VPMAnalyzer getAnalyzer() {
        return analyzer;
    }

    /**
     * @param analyzer
     *            the analyzer to set
     */
    public void setAnalyzer(VPMAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

}

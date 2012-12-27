/**
 * 
 */
package org.splevo.ui.wizards;

import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.VPMAnalyzerConfigurationType;

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
    @Override
    protected CellEditor getCellEditor(Object element) {
        @SuppressWarnings("unchecked")
        Entry<String, VPMAnalyzerConfigurationType> e = (Entry<String, VPMAnalyzerConfigurationType>) element;
        if (e.getValue() == VPMAnalyzerConfigurationType.BOOLEAN) {
            return new CheckboxCellEditor(viewer.getTable());
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
        Entry<String, VPMAnalyzerConfigurationType> e = (Entry<String, VPMAnalyzerConfigurationType>) element;

        if (analyzer == null) {
            logger.error("No analyzer reference set");
            return null;
        }

        Object value = analyzer.getConfigurations().get(e.getKey());
        if (value != null) {
            return value.toString();
        }
        
        return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void setValue(Object element, Object value) {
        @SuppressWarnings("unchecked")
        Entry<String, VPMAnalyzerConfigurationType> e = (Entry<String, VPMAnalyzerConfigurationType>) element;
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

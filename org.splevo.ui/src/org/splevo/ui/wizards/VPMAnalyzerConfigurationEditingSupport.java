/**
 * 
 */
package org.splevo.ui.wizards;

import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.splevo.vpm.refinement.AnalyzerConfigurationType;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.VPMRefinementAnalyzer;

/**
 * Editing support to modify the configuration value of an analyzer.
 */
public class VPMAnalyzerConfigurationEditingSupport extends EditingSupport {

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(VPMAnalyzerConfigurationEditingSupport.class);

	private final TableViewer viewer;
	
	/** The analyzer currently modified. */
	private VPMRefinementAnalyzer analyzer = null;

	/**
	 * Constructor setting the reference to the enclosing viewer.
	 * @param viewer The viewer to place the cell in.
	 */
	public VPMAnalyzerConfigurationEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor getCellEditor(Object element) {
		@SuppressWarnings("unchecked")
		Entry<String, AnalyzerConfigurationType> e = (Entry<String, AnalyzerConfigurationType>) element;
		if(e.getValue() == AnalyzerConfigurationType.BOOLEAN){
			return new CheckboxCellEditor(viewer.getTable());
		} else if(e.getValue() == AnalyzerConfigurationType.GROUP_MERGE){
			String[] refinementType = new String[2];
			refinementType[0] = RefinementType.GROUPING.toString();
			refinementType[1] = RefinementType.MERGE.toString();
		    return new ComboBoxCellEditor(viewer.getTable(), refinementType);
		} else {
			return new TextCellEditor(viewer.getTable());
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
	 */
	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(Object element) {
		@SuppressWarnings("unchecked")
		Entry<String, AnalyzerConfigurationType> e = (Entry<String, AnalyzerConfigurationType>) element;

		if(analyzer == null){
			logger.error("No analyzer reference set");
			return null;
		}

		Object value = analyzer.getConfigurations().get(e.getKey());

		if(e.getValue() == AnalyzerConfigurationType.GROUP_MERGE){

			if( value == null || value.equals(RefinementType.GROUPING)){
				return 0;
			} else {
				return 1;
			}
				
		} else {
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object value) {
		@SuppressWarnings("unchecked")
		Entry<String, AnalyzerConfigurationType> e = (Entry<String, AnalyzerConfigurationType>) element;
		if(e.getValue() == AnalyzerConfigurationType.GROUP_MERGE){
			if (((Integer) value) == 0) {
				analyzer.getConfigurations().put(e.getKey(), RefinementType.GROUPING);
			} else {
			    analyzer.getConfigurations().put(e.getKey(), RefinementType.MERGE);
			}
		} else {
			analyzer.getConfigurations().put(e.getKey(), value);
		}
		
		viewer.update(element, null);
	}

	/**
	 * @return the analyzer
	 */
	public VPMRefinementAnalyzer getAnalyzer() {
		return analyzer;
	}

	/**
	 * @param analyzer the analyzer to set
	 */
	public void setAnalyzer(VPMRefinementAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

}

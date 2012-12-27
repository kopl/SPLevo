package org.splevo.ui.workflow;

import org.splevo.ui.editors.SPLevoProjectEditor;

import de.uka.ipd.sdq.workflow.AbstractJobConfiguration;

public class BasicSPLevoWorkflowConfiguration extends AbstractJobConfiguration {
	
	/** The internal reference to the splevo project editor to work with. */
	private SPLevoProjectEditor splevoProjectEditor = null;
	
	
	
	/**
	 * This configuration is always valid.
	 * {@inheritDoc}
	 */
	@Override
	public String getErrorMessage() {
		if(getSplevoProjectEditor() == null){
			return "No SPLevo Project editor configured";
		}
		return null;
	}

	/**
	 * No defaults to be set yet.
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaults() {
		
	}

	/**
	 * @return the splevoProjectEditor
	 */
	public SPLevoProjectEditor getSplevoProjectEditor() {
		return splevoProjectEditor;
	}

	/**
	 * @param splevoProjectEditor the splevoProjectEditor to set
	 */
	public void setSplevoProjectEditor(SPLevoProjectEditor splevoProjectEditor) {
		this.splevoProjectEditor = splevoProjectEditor;
	}

}

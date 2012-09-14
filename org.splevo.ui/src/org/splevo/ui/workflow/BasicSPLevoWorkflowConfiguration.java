package org.splevo.ui.workflow;

import org.splevo.project.SPLevoProject;
import org.splevo.ui.editors.SPLevoProjectEditor;

import de.uka.ipd.sdq.workflow.AbstractJobConfiguration;

public class BasicSPLevoWorkflowConfiguration extends AbstractJobConfiguration {

	/** The splevo project to process. */
	private SPLevoProject splevoProject = null;
	
	/** The internal reference to the splevo project editor to work with. */
	private SPLevoProjectEditor splevoProjectEditor = null;
	
	
	
	/**
	 * This configuration is always valid.
	 * {@inheritDoc}
	 */
	@Override
	public String getErrorMessage() {
		if(getSplevoProject() == null){
			return "No SPLevo Project configured";
		} 
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
	 * @return the splevoProject
	 */
	public SPLevoProject getSplevoProject() {
		return splevoProject;
	}

	/**
	 * @param splevoProject the splevoProject to set
	 */
	public void setSplevoProject(SPLevoProject splevoProject) {
		this.splevoProject = splevoProject;
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

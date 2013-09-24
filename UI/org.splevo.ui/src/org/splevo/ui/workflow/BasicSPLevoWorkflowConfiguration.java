package org.splevo.ui.workflow;

import org.splevo.ui.editors.SPLevoProjectEditor;

import de.uka.ipd.sdq.workflow.AbstractJobConfiguration;

/**
 * A basic workflow configuration shared by different workflows providing access to the splevo
 * project instance.
 * 
 * @author Benjamin Klatt
 * 
 */
public class BasicSPLevoWorkflowConfiguration extends AbstractJobConfiguration {

    public static final String ERROR_MSG_EDITOR_MISSING = "No SPLevo Project editor configured";
    
	/** The internal reference to the splevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    /**
     * Check the configuration and return a message in case of an error.
     *  
     * {@inheritDoc}
     */
    @Override
    public String getErrorMessage() {
        if (getSplevoProjectEditor() == null) {
            return ERROR_MSG_EDITOR_MISSING;
        }
        return null;
    }

    /**
     * No defaults to be set yet. {@inheritDoc}
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
     * @param splevoProjectEditor
     *            the splevoProjectEditor to set
     */
    public void setSplevoProjectEditor(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

}

package org.splevo.ui.workflow;

import org.splevo.project.SPLevoProject;

import de.uka.ipd.sdq.workflow.configuration.AbstractJobConfiguration;

public class BasicSPLevoProjectWorkflowConfiguration extends AbstractJobConfiguration {

    public static final String ERROR_MSG_PROJECT_MISSING = "No SPLevo Project configured";

    /** The internal reference to the splevo project editor to work with. */
    private SPLevoProject splevoProject = null;

    /**
     * Check the configuration and return a message in case of an error.
     *
     * {@inheritDoc}
     */
    @Override
    public String getErrorMessage() {
        if (getSplevoProject() == null) {
            return ERROR_MSG_PROJECT_MISSING;
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
     * @return the splevoProject
     */
    public SPLevoProject getSplevoProject() {
        return splevoProject;
    }

    /**
     * @param splevoProject
     *            the splevoProject to set
     */
    public void setSplevoProject(SPLevoProject splevoProject) {
        this.splevoProject = splevoProject;
    }

}

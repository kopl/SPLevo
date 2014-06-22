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
 *******************************************************************************/
package org.splevo.ui.wizards.project;

import java.net.URI;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.splevo.ui.nature.SPLevoNature;

/**
 * Wizard to create a new SPLevo consolidation project.
 */
public class NewConsolidationProjectWizardBasic extends Wizard implements INewWizard, IExecutableExtension {

    private static final String WIZARD_NAME = "New Basic Consolidation Project Wizard";

    private static Logger logger = Logger.getLogger(NewConsolidationProjectWizardBasic.class);

    private WizardNewProjectCreationPage projectCreationPage;
    private IConfigurationElement configurationElement;

    /**
     * Constructor preparing the wizard infrastructure.
     */
    public NewConsolidationProjectWizardBasic() {
        setWindowTitle(WIZARD_NAME);
    }

    @Override
    public void addPages() {
        super.addPages();

        projectCreationPage = new WizardNewProjectCreationPage("Basic Consolidation Project Wizard");
        projectCreationPage.setTitle("Basic Consolidation Project");
        projectCreationPage
                .setDescription("Create a new project for consolidating project copies into a variable product line.");

        addPage(projectCreationPage);
    }

    @Override
    public boolean performFinish() {
        String name = projectCreationPage.getProjectName();
        URI nonDefaultLocation = null;
        if (!projectCreationPage.useDefaults()) {
            nonDefaultLocation = projectCreationPage.getLocationURI();
        }

        try {
            IProject project = createBaseProject(name, nonDefaultLocation);
            addNature(project);
        } catch (CoreException e) {
            logger.error("Failed to create consolidation project", e);
        }

        BasicNewProjectResourceWizard.updatePerspective(configurationElement);
        return true;
    }

    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
            throws CoreException {
        this.configurationElement = config;
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    /**
     * Create the project in the workspace.
     *
     * @param nonDefaultLocation
     *            The location to create the project at. If null, a project with the give name is
     *            created inside the workspace.
     * @param projectName
     *            The name of the project
     * @throws CoreException
     *             Failed to create the new project.
     */
    private IProject createBaseProject(String projectName, URI nonDefaultLocation) {

        IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

        if (!newProject.exists()) {
            URI projectLocation = nonDefaultLocation;
            IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
            if (nonDefaultLocation != null
                    && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(nonDefaultLocation)) {
                projectLocation = null;
            }

            desc.setLocationURI(projectLocation);

            try {
                newProject.create(desc, null);
            } catch (CoreException e) {
                e.printStackTrace();
            }
            if (!newProject.isOpen()) {
                try {
                    newProject.open(null);
                } catch (CoreException e) {
                    e.printStackTrace();
                }
            }
        }

        return newProject;
    }

    /**
     * Add the custom SPLevo nature to the project.
     *
     * @param project
     *            The project to add the nature to.
     * @throws CoreException
     *             identifies a problem during nature setting.
     */
    private void addNature(IProject project) throws CoreException {
        if (!project.hasNature(SPLevoNature.NATURE_ID)) {
            IProjectDescription description = project.getDescription();
            String[] prevNatures = description.getNatureIds();
            String[] newNatures = new String[prevNatures.length + 1];
            System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
            newNatures[prevNatures.length] = SPLevoNature.NATURE_ID;
            description.setNatureIds(newNatures);

            IProgressMonitor monitor = null;
            project.setDescription(description, monitor);
        }
    }

}

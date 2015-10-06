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
package org.splevo.ui.nature;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.splevo.project.ProjectFactory;
import org.splevo.project.SPLevoProject;
import org.splevo.project.utils.SPLevoProjectUtil;
import org.splevo.ui.editors.SPLevoProjectEditor;

/**
 * The SPLevo project nature.
 */
public class SPLevoNature implements IProjectNature {

    /** The project. */
    private IProject project;

    /**
     * Configure the project. This creates and initializes a SPLevo project file and opens the
     * associated editor.
     *
     * @throws CoreException
     *             identifies the configuration could not be performed successfully.
     */
    @Override
    public void configure() throws CoreException {
        IProjectDescription desc = project.getDescription();
        ICommand[] commands = desc.getBuildSpec();

        for (int i = 0; i < commands.length; ++i) {
            if (commands[i].getBuilderName().equals(SPLevoBuilder.BUILDER_ID)) {
                return;
            }
        }

        ICommand[] newCommands = new ICommand[commands.length + 1];
        System.arraycopy(commands, 0, newCommands, 0, commands.length);
        ICommand command = desc.newCommand();
        command.setBuilderName(SPLevoBuilder.BUILDER_ID);
        newCommands[newCommands.length - 1] = command;
        desc.setBuildSpec(newCommands);
        project.setDescription(desc, null);

        // init the default directory structure of a SPLevo project.
        String splevoProjectFileName = project.getName() + "." + SPLevoProjectUtil.SPLEVO_FILE_EXTENSION;
        File filePath = new File(project.getFullPath().toString() + File.separator + splevoProjectFileName);
        String workspaceRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
        File absolutePath = new File(workspaceRoot + filePath.toString());
        if (!absolutePath.exists()) {
            SPLevoProject projectConfiguration = ProjectFactory.eINSTANCE.createSPLevoProject();
            projectConfiguration.setWorkspace(project.getName() + "/");
            projectConfiguration.setName(project.getName());
            try {
                SPLevoProjectUtil.save(projectConfiguration, filePath);
                project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
                IFile file = project.getFile(splevoProjectFileName);
                IFileEditorInput inputFile = new FileEditorInput(file);
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                page.openEditor(inputFile, SPLevoProjectEditor.ID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void deconfigure() throws CoreException {
        IProjectDescription description = getProject().getDescription();
        ICommand[] commands = description.getBuildSpec();
        for (int i = 0; i < commands.length; ++i) {
            if (commands[i].getBuilderName().equals(SPLevoBuilder.BUILDER_ID)) {
                ICommand[] newCommands = new ICommand[commands.length - 1];
                System.arraycopy(commands, 0, newCommands, 0, i);
                System.arraycopy(commands, i + 1, newCommands, i, commands.length - i - 1);
                description.setBuildSpec(newCommands);
                project.setDescription(description, null);
                return;
            }
        }
    }

    @Override
    public IProject getProject() {
        return project;
    }

    @Override
    public void setProject(IProject project) {
        this.project = project;
    }
    
    /**
     * @return The ID of this nature.
     */
    public static String getNatureId() {
        return SPLevoProjectUtil.NATURE_ID;
    }

}

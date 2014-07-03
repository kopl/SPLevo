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
package org.splevo.ui.refinementbrowser.listener;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.actions.CommandAction;

/**
 * Menu listener to provide an action wrapping a command to be executed.
 */
@SuppressWarnings("restriction")
public class CommandActionMenuListener implements IMenuListener {

    private String commandId = null;
    private ImageDescriptor imageDescriptor = null;

    /**
     * Constructor for a command action.
     *
     * @param commandId
     *            The command to execute.
     */
    public CommandActionMenuListener(String commandId) {
        this(commandId, null);
    }

    /**
     * Constructor for a command action with an icon.
     *
     * @param commandId
     *            The command to execute.
     * @param imageDescriptor
     *            The image to be displayed in the menu.
     */
    public CommandActionMenuListener(String commandId, ImageDescriptor imageDescriptor) {
        this.commandId = commandId;
        this.imageDescriptor = imageDescriptor;
    }

    @Override
    public void menuAboutToShow(IMenuManager manager) {
        IWorkbench wb = PlatformUI.getWorkbench();
        Action action = new CommandAction(wb, commandId);
        if (imageDescriptor != null) {
            action.setImageDescriptor(imageDescriptor);
        }
        manager.add(action);
    }

}

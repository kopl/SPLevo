/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt, Radoslav Yankov
 *******************************************************************************/
package org.splevo.ui.wizard.consolidation.provider;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for projects 
 */
@SuppressWarnings("restriction")
public class ProjectLabelProvider extends ColumnLabelProvider {
    
    @Override
    public String getText(Object element) {
        IProject project = (IProject) element;
        return project.getName();
    }

    @Override
    public Image getImage(Object element) {
        JavaUILabelProvider provider = new JavaUILabelProvider();
        return provider.getImage(element);
    }
}

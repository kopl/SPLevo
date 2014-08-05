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

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for packages.
 */
public class PackageLabelProvider extends ColumnLabelProvider {
    
    @Override
    public String getText(Object element) {
        if (element instanceof IPackageFragment) {
            return ((IPackageFragment) element).getElementName();
        }
        return null;
    }

    @Override
    public Image getImage(Object element) {
        return JavaUI.getSharedImages().getImage(ISharedImages.IMG_OBJS_PACKAGE);
    }
}

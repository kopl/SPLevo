/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.wizards.vpmanalysis;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.splevo.ui.util.UIUtil;

import com.google.common.base.Strings;

/**
 * A label provider for the nodes presented in the refinement tree.
 */
public class CompleteRefinementTreeLabelProvider extends LabelProvider {

    private static final String UNKNOWN_LABEL = "[UNKNOWN]";

    @Override
    public String getText(final Object element) {
        String label = UIUtil.getItemProviderText(element);
        if (!Strings.isNullOrEmpty(label)) {
            return label;
        } else {
            return UNKNOWN_LABEL;
        }
    }

    @Override
    public Image getImage(final Object element) {
        Image image = UIUtil.getItemProviderImage(element);
        if (image != null) {
            return image;
        } else {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
        }
    }
}

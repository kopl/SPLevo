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
package org.splevo.ui.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Utility class for UI creation and handling.
 */
public final class UIUtil {

    /** Disable constructor for static utility handling. */
    private UIUtil() {
    }

    /**
     * Adds a Tool-Tip explanation to a given parent composite.
     *
     * @param parent
     *            The parent.
     * @param explanation
     *            The textual explanation.
     */
    public static void addExplanation(Composite parent, String explanation) {
        Label explanationLabel = new Label(parent, SWT.NONE);
        explanationLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        explanationLabel.setImage(PlatformUI.getWorkbench().getSharedImages()
                .getImage(ISharedImages.IMG_LCL_LINKTO_HELP));
        explanationLabel.setToolTipText(explanation);

        if (explanation == null || explanation.length() == 0) {
            explanationLabel.setVisible(false);
        }
    }

}
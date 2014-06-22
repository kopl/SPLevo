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
package org.splevo.ui.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * A factory for the SPLevo default perspective.
 */
public class SPLevoPerspectiveFactory implements IPerspectiveFactory {

    /** The internal id of the SPLevo perspective. */
    public static final String SPLEVO_PERSPECTIVE_ID = "org.splevo.ui.perspective";

    @Override
    public void createInitialLayout(IPageLayout layout) {
    }

}

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
package org.splevo.ui.vpexplorer.handler.characteristics.extendibility;

import org.splevo.vpm.variability.Extendibility;

/**
 * Handler to set the {@link Extendibility} of a Variation Point to
 * {@link Extendibility#CLOSED}
 */
public class SetClosedExtendibilityHandler extends AbstractSetExtendibilityHandler {

    @Override
    protected Extendibility getTargetExtendibility() {
        return Extendibility.CLOSED;
    }

}

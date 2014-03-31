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

import org.splevo.vpm.variability.Extensible;

/**
 * Handler to set the {@link Extensible} of a Variation Point to
 * {@link Extensible#NO}
 */
public class SetExtensibleNOHandler extends AbstractSetExtensibilityHandler {

    @Override
    protected Extensible getTargetExtensibility() {
        return Extensible.NO;
    }

}

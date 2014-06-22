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
package org.splevo.ui.vpexplorer.handler.characteristics.bindingtime;

import org.splevo.vpm.variability.BindingTime;

/**
 * Handler to set the {@link BindingTime} of a Variation Point to
 * {@link BindingTime#COMPILE_TIME}
 */
public class SetLoadTimeHandler extends AbstractSetBindingTimeHandler {

    @Override
    protected BindingTime getTargetBindingTime() {
        return BindingTime.COMPILE_TIME;
    }

}

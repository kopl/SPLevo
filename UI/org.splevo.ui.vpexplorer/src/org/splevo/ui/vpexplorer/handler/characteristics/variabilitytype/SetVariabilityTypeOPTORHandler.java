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
package org.splevo.ui.vpexplorer.handler.characteristics.variabilitytype;

import org.splevo.vpm.variability.VariabilityType;

/**
 * Handler to set a variation points variability type to OPTOR.
 */
public class SetVariabilityTypeOPTORHandler extends AbstractSetVariabilityTypeHandler {

    @Override
    protected VariabilityType getTargetVariabilityType() {
        return VariabilityType.OPTOR;
    }

}

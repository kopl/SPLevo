/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thomas Czogalik
 *******************************************************************************/
package org.splevo.ui.vpexplorer.handler.characteristics.variabilitymechanism;

import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.jamopp.refactoring.java.ifelse.optxor.IfStaticConfigClassOPTXOR;

/**
 * Handler to set a variation points variability mechanism to IfStatic OPTXOR.
 */
public class SetIfStaticMechanismHandler extends AbstractSetVariabilityMechanism {

    @Override
    protected VariabilityRefactoring getTargetVariabilityMechanism() {
        return (new IfStaticConfigClassOPTXOR());
    }


}

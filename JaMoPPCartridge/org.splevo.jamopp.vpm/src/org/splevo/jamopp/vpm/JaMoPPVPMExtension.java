/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.vpm;

import org.splevo.jamopp.vpm.software.softwarePackage;
import org.splevo.vpm.VPMExtension;

/**
 * Life cycle management for the variation point model extension.
 */
public class JaMoPPVPMExtension implements VPMExtension {

    @Override
    public void init() {
        softwarePackage.eINSTANCE.eClass();
    }

    @Override
    public String getName() {
        return "JaMoPPVPMExtension";
    }

}

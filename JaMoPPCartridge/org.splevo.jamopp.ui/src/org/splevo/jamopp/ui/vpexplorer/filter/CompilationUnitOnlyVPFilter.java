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
package org.splevo.jamopp.ui.vpexplorer.filter;

import org.emftext.language.java.containers.CompilationUnit;

/**
 * Viewer content filter for variation points with variants containing Compilation Units only.
 */
public class CompilationUnitOnlyVPFilter extends AbstractJaMoPPVPFilter {

    @Override
    protected Class<?> getExpectedLocationClass() {
        return CompilationUnit.class;
    }

    @Override
    protected Class<?> getImplementingElementClass() {
        return CompilationUnit.class;
    }

}

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

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.statements.Statement;

/**
 * Viewer content filter for variation points with variants implemented by statements (excluding
 * classifiers) only.
 */
public class StatementOnlyVPFilter extends AbstractJaMoPPVPFilter {

    @Override
    protected Class<?> getExpectedLocationClass() {
        return null;
    }

    @Override
    protected Class<?> getImplementingElementClass() {
        return Statement.class;
    }

    @Override
    protected Class<?> getExcludeImplementingElementClass() {
        return ConcreteClassifier.class;
    }

}

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
package org.splevo.jamopp.vpm.analyzer.programdependency.references;

/**
 * The specific type of the reference.
 */
public enum ReferenceType {
    SuperType,
    Calls,
    Reads,
    Writes,
    Checks,
    Creates,
    Declares,
    Typed,
    Import,
    Modifies,
    SELF,
    DEFAULT;
}

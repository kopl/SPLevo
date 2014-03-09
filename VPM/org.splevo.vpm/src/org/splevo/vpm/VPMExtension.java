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
package org.splevo.vpm;

/**
 * An extension of the vpm model providing capabilities to manage its life cycle.
 */
public interface VPMExtension {

    /** Initialize the variation point model extension. */
    void init();

    /**
     * Get the name of the extension.
     * @return The name of the extension which should be unique.
     */
    String getName();
}

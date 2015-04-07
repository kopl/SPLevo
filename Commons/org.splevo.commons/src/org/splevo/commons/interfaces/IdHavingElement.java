/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.commons.interfaces;

/**
 * Interface for elements that have an identifier.
 * 
 * @param <T>
 *            The type of the identifier.
 */
public interface IdHavingElement<T> {

    /**
     * @return The associated identifier.
     */
    T getId();

}

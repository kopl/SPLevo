/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.refactoring;

/**
 * Customizer for the ToDo tags specified in the Eclipse settings.
 */
public interface TodoTagCustomizer {

    /**
     * Adjusts the ToDo tags according to the needs of the specific technology catridge.
     */
    void adjustTodoTags();
    
}

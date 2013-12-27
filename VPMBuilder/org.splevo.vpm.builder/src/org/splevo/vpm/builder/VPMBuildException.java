/*******************************************************************************
 * Copyright (c) 2013
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.vpm.builder;

/**
 * Exception identifying an error during the VPM build process.
 */
public class VPMBuildException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Create a new exception without any settings.
     */
    public VPMBuildException() {
    }

    /**
     * Create a new exception with an info message.
     * @param message Some information about the exception.
     */
    public VPMBuildException(String message) {
        super(message);
    }

    /**
     * Create a new exception with an info message and wrapped exception.
     * @param throwable Another throwable to wrap by this exception.
     */
    public VPMBuildException(Throwable throwable) {
        super(throwable);
    }

    /**
     * Create a new exception with an info message and wrapped exception.
     * @param message Some information about the exception.
     * @param throwable Another throwable to wrap by this exception.
     */
    public VPMBuildException(String message, Throwable throwable) {
        super(message, throwable);
    }

}

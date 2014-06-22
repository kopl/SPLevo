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
package org.splevo.vpm.analyzer;

/**
 * Exception indicating a failed varation point model analysis.
 */
public class VPMAnalyzerException extends Exception {

    /** Id identifying the serialization version of the class. */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public VPMAnalyzerException() {
        super();
    }

    /**
     * Constructor providing a message.
     *
     * @param message
     *            The informative message about the exception.
     */
    public VPMAnalyzerException(String message) {
        super(message);
    }

    /**
     * Constructor wrapping another throwable.
     *
     * @param throwable
     *            A detailed exception to wrap.
     */
    public VPMAnalyzerException(Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructor providing a message and wrapping another throwable.
     *
     * @param message
     *            The informative message about the exception.
     * @param throwable
     *            A detailed exception to wrap.
     */
    public VPMAnalyzerException(String message, Throwable throwable) {
        super(message, throwable);
    }

}

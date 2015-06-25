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
package org.splevo.vpm;

import org.splevo.vpm.variability.VariationPoint;

/**
 * Validator for the VPM. This one is used during the validation of the EMF model.
 */
public interface VPMValidator {

    /**
     * Result of a validation. Basically, a DTO.
     */
    public class VPMValidationResult {
        private final boolean valid;
        private final String message;

        /**
         * Constructs the result object for a successful validation.
         */
        public VPMValidationResult() {
            this(true, "");
        }

        /**
         * Constructs a result object.
         * @param valid Flag that indicates a successful validation.
         * @param message Message to describe the result/cause of the validation.
         */
        public VPMValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        /**
         * @return True if the validation succeeded.
         */
        public boolean isValid() {
            return valid;
        }

        /**
         * @return The message description the validation result.
         */
        public String getMessage() {
            return message;
        }

    }

    /**
     * Validates the given variation point.
     * @param vp The variation point to be validated.
     * @return The result of the validation.
     */
    public VPMValidationResult validate(VariationPoint vp);

}

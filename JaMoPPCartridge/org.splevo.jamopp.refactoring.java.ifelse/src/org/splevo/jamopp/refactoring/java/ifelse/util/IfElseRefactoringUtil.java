/*******************************************************************************
 * Copyright (c) 2015
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Max Scheerer
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.util;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.statements.Condition;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Optional;

/**
 * Base interface for utility classes used in the IfElse refactorings.
 */
public interface IfElseRefactoringUtil {

    /**
     * Creates the condition element for the if statement.
     * 
     * @param vp
     *            The variation point that is refactored.
     * @param variantId
     *            The ID of the variant that shall be encapsulated.
     * @return The generated {@link Condition}.
     */
    public Condition createVariabilityCondition(VariationPoint vp, String variantId);

    /**
     * Creates a configuration class if required.
     * 
     * @param variationPoint
     *            The variation point that is refactored.
     * @param refactoringOptions
     *            The refactoring options.
     * @return An optional encapsulating the created resource. If no resource has been created, the
     *         value is absent.
     */
    public Optional<Resource> createConfigurationClass(VariationPoint variationPoint,
            Map<String, Object> refactoringOptions);

    /**
     * Creates the import for the configuration class if required.
     * 
     * @param vpLocation
     *            The location of the variation point.
     */
    void createConfigurationClassImport(Commentable vpLocation);
}

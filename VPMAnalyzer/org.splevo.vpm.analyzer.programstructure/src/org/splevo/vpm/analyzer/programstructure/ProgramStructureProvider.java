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
package org.splevo.vpm.analyzer.programstructure;

import java.util.List;

import org.splevo.vpm.software.SoftwareElement;

/**
 * An extension for the {@link ProgramStructureVPMAnalyzer} to provide access to the program structure
 * itself.
 *
 */
public interface ProgramStructureProvider {

    /**
     * Get all relevant sub elements of a software element to be analyzed by the {@link ProgramStructureVPMAnalyzer}.
     * An element is relevant if it can be referenced by an element of another variation point.
     *
     * @param softwareElement
     *            The software element to get the sub elements for.
     * @return The list of relevant sub elements.
     */
    List<SoftwareElement> getRelevantSubElements(SoftwareElement softwareElement);

    /**
     * Get all software elements referring to a specific software element.
     *
     * @param referredSoftwareElement
     *            The software element to get referring elements for.
     * @return The list of referring elements.
     */
    List<SoftwareElement> getReferringSoftwareElements(SoftwareElement referredSoftwareElement);
}

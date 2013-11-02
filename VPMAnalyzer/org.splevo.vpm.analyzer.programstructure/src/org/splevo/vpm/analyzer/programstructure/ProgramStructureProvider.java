package org.splevo.vpm.analyzer.programstructure;

import java.util.List;

import org.splevo.vpm.software.SoftwareElement;

/**
 * An extension for the program structure analyzer to provide access to the program structure
 * itself.
 * 
 */
public interface ProgramStructureProvider {

    /**
     * Get all relevant sub elements of a software element to analyze in context of the program
     * structure analyzer.
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

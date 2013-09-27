package org.splevo.modisco.java.diffing.util;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * This class is responsible to check a java model element if it is located in a package that should
 * be ignored.
 * 
 * Internally, it makes use of a visitor based on a EMF generated switch for the java model to
 * improve the performance of the EObject analysis.
 * 
 */
public class PackageIgnoreChecker {

    /** The internal switch to check for ignoring an element. */
    private IgnoreSwitch ignoreSwitch = null;

    /**
     * Constructor requires to set the list of packages to be ignored.
     * 
     * @param ignorePackages
     *            The list of packages to be ignored. Regular expressions are excepted.
     */
    public PackageIgnoreChecker(List<String> ignorePackages) {
        this.ignoreSwitch = new IgnoreSwitch(ignorePackages);
    }

    /**
     * Check if an element is in a package that should be ignored.
     * 
     * @param element
     *            The element to check.
     * @return TRUE, if it should be ignored; FALSE
     */
    public Boolean isInIgnorePackage(EObject element) {
        return ignoreSwitch.doSwitch(element);
    }

}

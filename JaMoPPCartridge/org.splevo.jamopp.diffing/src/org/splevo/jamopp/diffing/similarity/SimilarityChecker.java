package org.splevo.jamopp.diffing.similarity;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

/**
 * Checker for the similarity of two elements specific for the java application model.
 * 
 * TODO: Check caching for this similarity checker. Would require to pass this to the similarity switch as well!
 * 
 */
public class SimilarityChecker {

    /** The logger for this class. */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(SimilarityChecker.class);

    /**
     * Check two objects if they are similar.
     * 
     * @param element1
     *            The first element to check.
     * @param element2
     *            The second element to check.
     * @return TRUE, if they are similar; FALSE if not, NULL if it can't be decided.
     */
    public Boolean isSimilar(final EObject element1, final EObject element2) {

        // check that either both or none of them is null
        if (element1 == element2) {
            return Boolean.TRUE;
        }

        if (element1 != null && element2 == null) {
            return Boolean.FALSE;
        } else if (element1 == null && element2 != null) {
            return Boolean.FALSE;
        }

        // check the elements to have similar classes
        if (!element1.getClass().equals(element2.getClass())) {
            return Boolean.FALSE;
        }

        // check type specific similarity
        SimilaritySwitch similaritySwitch = new SimilaritySwitch(element2);
        Boolean similar = similaritySwitch.doSwitch(element1);
        return similar;
    }

}

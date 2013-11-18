package org.splevo.modisco.java.diffing.match;

import org.eclipse.emf.ecore.EObject;
import org.splevo.modisco.java.diffing.match.HierarchicalMatchEngine.EqualityStrategy;
import org.splevo.modisco.util.SimilarityChecker;

/**
 * MoDisco java model specific equality strategy based on the similarity checker strategy.
 */
public class MoDiscoJavaEqualityStrategy implements EqualityStrategy {

    /** The similarity checker to use internally for equality checks. */
    private SimilarityChecker similarityChecker = null;
    
    /**
     * Constructor to set the required dependencies.
     * @param similarityChecker The similarity checker to proof equality.
     */
    public MoDiscoJavaEqualityStrategy(SimilarityChecker similarityChecker) {
        this.similarityChecker = similarityChecker;
    }
    
    @Override
    public boolean areEqual(EObject left, EObject right) {
        Boolean similar = similarityChecker.isSimilar(left, right);
        if (similar == Boolean.TRUE) {
            return true;
        }
        return false;
    }

}
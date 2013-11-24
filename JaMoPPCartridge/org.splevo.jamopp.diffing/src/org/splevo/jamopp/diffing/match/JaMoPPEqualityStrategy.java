package org.splevo.jamopp.diffing.match;

import org.eclipse.emf.ecore.EObject;
import org.splevo.diffing.match.HierarchicalMatchEngine.EqualityStrategy;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

/**
 * JaMoPP java model specific equality strategy based on the similarity checker strategy.
 */
public class JaMoPPEqualityStrategy implements EqualityStrategy {

    /** The similarity checker to use internally for equality checks. */
    private SimilarityChecker similarityChecker = null;
    
    /**
     * Constructor to set the required dependencies.
     * @param similarityChecker The similarity checker to proof equality.
     */
    public JaMoPPEqualityStrategy(SimilarityChecker similarityChecker) {
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
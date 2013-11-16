package org.splevo.modisco.java.diffing;

import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.ecore.EObject;
import org.splevo.modisco.util.SimilarityChecker;

import com.google.common.cache.LoadingCache;

/**
 * EqualityHelper specific for the MoDisco Java software element.
 */
public class MoDiscoJavaEqualityHelper extends EqualityHelper {

    /** A similarity checker for internal similarity comparisons. */
    private SimilarityChecker similarityChecker = null;

    /**
     * Constructor to initialize the required cache.
     * 
     * @param uriCache
     *            The cache to use during the equality checks.
     * @param similarityChecker
     *            The similarity checker to be used.
     */
    public MoDiscoJavaEqualityHelper(LoadingCache<EObject, org.eclipse.emf.common.util.URI> uriCache,
            SimilarityChecker similarityChecker) {
        super(uriCache);
        this.similarityChecker = similarityChecker;
    }

    @Override
    protected boolean matchingEObjects(EObject object1, EObject object2) {

        Boolean similar = similarityChecker.isSimilar(object1, object2);
        if (similar != null) {
            return similar.booleanValue();
        } else {
            return false;
        }
    }
}

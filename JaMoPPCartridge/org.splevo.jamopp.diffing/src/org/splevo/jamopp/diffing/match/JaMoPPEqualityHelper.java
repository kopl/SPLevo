package org.splevo.jamopp.diffing.match;

import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

import com.google.common.cache.LoadingCache;

/**
 * EqualityHelper specific for the MoDisco Java software element.
 */
public class JaMoPPEqualityHelper extends EqualityHelper {

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
    public JaMoPPEqualityHelper(LoadingCache<EObject, org.eclipse.emf.common.util.URI> uriCache,
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
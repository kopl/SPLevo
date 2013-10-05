package org.splevo.modisco.java.diffing.match;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.match.engine.internal.GenericMatchEngineToCheckerBridge;
import org.eclipse.emf.compare.match.engine.internal.StatisticBasedSimilarityChecker;
import org.eclipse.emf.compare.match.statistic.MetamodelFilter;
import org.eclipse.emf.ecore.EObject;
import org.splevo.modisco.util.SimilarityChecker;

/**
 * The Class JavaModelSimilarityChecker.
 * A java model specific similarity checker which is used by the match engine
 * to find matching elements.
 */
@SuppressWarnings("restriction")
public class JavaModelSimilarityChecker extends StatisticBasedSimilarityChecker {

    /** The checker to use for the java model specific element similarity. */
    private SimilarityChecker checker = new SimilarityChecker();

    /**
     * Create a new checker.
     * 
     * @param mmFilter
     *            a metamodel filter the checker can use to know whether a feature alwaas has the
     *            same value or not in the models.
     * @param bridge
     *            utility class to keep API compatibility.
     */
    public JavaModelSimilarityChecker(MetamodelFilter mmFilter, GenericMatchEngineToCheckerBridge bridge) {
        super(mmFilter, bridge);
    }

    @Override
    public double absoluteMetric(EObject obj1, EObject obj2) throws FactoryException {

        Boolean result = checker.isSimilar(obj1, obj2);
        if (result != null) {
            if (result.booleanValue()) {
                return 1;
            } else {
                return 0;
            }
        }

        return super.absoluteMetric(obj1, obj2);
    }
}

package org.splevo.diffing.java.modisco.match;

import java.util.Map;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.AbstractSimilarityChecker;
import org.eclipse.emf.compare.match.engine.GenericMatchEngine;
import org.eclipse.emf.compare.match.engine.internal.GenericMatchEngineToCheckerBridge;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.modisco.util.SimilarityChecker;

/**
 * A KDM specific match engine taking into account that multi-step references need to be considered
 * when comparing elements.
 * 
 * For example, an import statement references a type access which references a type. If the import
 * is changed, the type is changed but not the type access. As a result the generic match engine
 * returns those import statements as similar.
 * 
 * The match engine checks similarity according to the element type. It does not take into account
 * any scope limitations. It is necessary to match even elements outside the scope to not
 * successfully match the target elements of references.
 * 
 * {@inheritDoc}
 * 
 * @see http://www.eclipse.org/forums/index.php?t=msg&goto=511859&
 * 
 */
public class JavaModelMatchEngine extends GenericMatchEngine {

    /** The checker to use to prove element similarity. */
    private SimilarityChecker similarityChecker = new SimilarityChecker();

    /**
     * A custom similarity check for kdm / modisco elements.
     * 
     * {@inheritDoc}
     */
    @Override
    protected boolean isSimilar(final EObject obj1, final EObject obj2) throws FactoryException {

        // if the types of the elements is different return false straight away
        if (!obj1.getClass().equals(obj2.getClass())) {
            return false;
        }

        // check the similarity for java model specific elements.
        Boolean similar = similarityChecker.isSimilar(obj1, obj2);
        if (similar != null) {
            return similar.booleanValue();
        }

        return super.isSimilar(obj1, obj2);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.compare.match.engine.IMatchEngine#modelMatch(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject, java.util.Map)
     */
    public MatchModel modelMatch(EObject leftRoot, EObject rightRoot, Map<String, Object> optionMap)
            throws InterruptedException {
        
        // TODO Improve Algorithm to check search list. use model specifics instead of simple search window
       optionMap.put(MatchOptions.OPTION_SEARCH_WINDOW, 300);

        JavaApplication leftJavaApplication = (JavaApplication) leftRoot;
        JavaApplication rightJavaApplication = (JavaApplication) rightRoot;

        Model leftJavaModel = leftJavaApplication.getJavaModel();
        Model rightJavaModel = rightJavaApplication.getJavaModel();
        MatchModel result = super.modelMatch(leftJavaModel, rightJavaModel, optionMap);

        // add the composition models
        result.getLeftRoots().add(leftJavaApplication);
        result.getRightRoots().add(rightJavaApplication);

        // add the inventory models
        result.getLeftRoots().add(leftJavaApplication.getDeploymentModel());
        result.getRightRoots().add(rightJavaApplication.getDeploymentModel());

        return result;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    protected AbstractSimilarityChecker prepareChecker() {
        AbstractSimilarityChecker checker = null;
        GenericMatchEngineToCheckerBridge bridge = new GenericMatchEngineToCheckerBridge() {

            @Override
            public double nameSimilarity(EObject obj1, EObject obj2) {
                return JavaModelMatchEngine.this.nameSimilarity(obj1, obj2);
            }

            @Override
            public double contentSimilarity(EObject obj1, EObject obj2) throws FactoryException {
                return JavaModelMatchEngine.this.contentSimilarity(obj1, obj2);
            }
        };
        checker = new JavaModelSimilarityChecker(filter, bridge);
        return checker;
    }

}

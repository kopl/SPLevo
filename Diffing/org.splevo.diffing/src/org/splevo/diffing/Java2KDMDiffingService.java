package org.splevo.diffing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.DefaultMatchScopeProvider;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.emfcompare.diff.JavaModelDiffEngine;
import org.splevo.diffing.emfcompare.match.JavaModelMatchEngine;
import org.splevo.diffing.kdm.JavaModelElementPrinter;
import org.splevo.diffing.postprocessor.DiffModelPostProcessor;

/**
 * Diffing Service that directly builds up the diffing model instead of using a post processor based
 * on the diffing identified by emf compare.
 * 
 * @author Benjamin Klatt
 * 
 */
public class Java2KDMDiffingService {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(Java2KDMDiffingService.class);

    /** Regular expressions defining packages to be ignored. */
    private List<String> ignorePackages = new ArrayList<String>();

    /**
     * Perform the diffing process for two modisco JavaApplicationModels.
     * 
     * @param leadingModel
     *            The leading java model
     * @param integrationModel
     *            The java model to be integrated and compared to the original one.
     * @return The difference model.
     * @throws InterruptedException
     *             Identifying that the match engine has been interrupted.
     */
    public DiffModel doDiff(JavaApplication integrationModel, JavaApplication leadingModel) throws InterruptedException {

        Model integrationJavaModel = integrationModel.getJavaModel();
        Model leadingJavaModel = leadingModel.getJavaModel();

        // configure the match engine
        final Map<String, Object> matchOptions = new EMFCompareMap<String, Object>();
        DefaultMatchScopeProvider matchScopeProvider = new DefaultMatchScopeProvider(integrationJavaModel,
                leadingJavaModel);
        matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER, matchScopeProvider);
        matchOptions.put(MatchOptions.OPTION_DISTINCT_METAMODELS, true); 
        
        logger.debug("Diffing: MATCHING PHASE");
        JavaModelMatchEngine matchEngine = new JavaModelMatchEngine();
        MatchModel matchModel = matchEngine.modelMatch(integrationModel, leadingModel, matchOptions);

        if (logger.isDebugEnabled()) {
            logger.debug("Diffing: UNMATCHED ELEMENTS");
            EList<UnmatchElement> unmatchedElements = matchModel.getUnmatchedElements();
            JavaModelElementPrinter elementPrinter = new JavaModelElementPrinter();
            for (UnmatchElement unmatchedElement : unmatchedElements) {
                StringBuilder debugMessage = new StringBuilder();
                debugMessage.append(unmatchedElement.getSide() + "\t");
                debugMessage.append(unmatchedElement.getElement().getClass().getSimpleName() + "\t");
                debugMessage.append(elementPrinter.printElement(unmatchedElement.getElement()));
                logger.debug(debugMessage.toString());
            }
        }

        logger.debug("Diffing: MAIN DIFFING PHASE");
        JavaModelDiffEngine javaModelDiffEngine = new JavaModelDiffEngine();
        javaModelDiffEngine.getIgnorePackages().addAll(this.ignorePackages);
        DiffModel diffModel = javaModelDiffEngine.doDiff(matchModel, false);

        logger.debug("Diffing: POST PROCESSING PHASE");

        DiffModelPostProcessor postProcessor = new DiffModelPostProcessor(javaModelDiffEngine.getMatchManager());
        postProcessor.process(diffModel);

        return diffModel;

    }

    /**
     * Get the regular expressions defining packages to be ignored.
     * 
     * @return the ignorePackages
     */
    public List<String> getIgnorePackages() {
        return ignorePackages;
    }

}

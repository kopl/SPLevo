package org.splevo.modisco.java.diffing;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.diffing.DiffingException;
import org.splevo.diffing.DiffingNotSupportedException;
import org.splevo.diffing.JavaDiffer;
import org.splevo.modisco.java.diffing.diff.JavaModelDiffEngine;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffPackage;
import org.splevo.modisco.java.diffing.match.JavaModelMatchEngine;
import org.splevo.modisco.java.diffing.match.JavaModelMatchScopeProvider;
import org.splevo.modisco.java.diffing.postprocessor.DiffModelPostProcessor;
import org.splevo.modisco.util.KDMUtil;

import com.google.common.io.PatternFilenameFilter;

/**
 * Differ for MoDiscos Java2KDM software models.
 * 
 * @author Benjamin Klatt
 * 
 */
public class Java2KDMDiffer extends JavaDiffer {

    private static final String LABEL = "MoDisco-based Java Differ";
    private static final String ID = "org.splevo.technology.java.modisco.differ";
    private Logger logger = Logger.getLogger(Java2KDMDiffer.class);

    /**
     * Diffing identifies the MoDisco Java2KDM source model in the directory and performs the
     * modisco specific diffing. {@inheritDoc}
     * 
     * @return null if no supported source models available.
     * @throws DiffingNotSupportedException Thrown if no Java2Kdm models provided to compare.
     */
    @Override
    public DiffModel doDiff(URI leadingModelDirectory, URI integrationModelDirectory, Map<String, Object> diffingOptions)
            throws DiffingException, DiffingNotSupportedException {

        @SuppressWarnings("unchecked")
        List<String> ignorePackages = (List<String>) diffingOptions.get(OPTION_JAVA_IGNORE_PACKAGES);

        final Map<String, Object> matchOptions = buildMatchOptions(ignorePackages);

        this.logger.info("Load source models");
        JavaApplication leadingModel = null;
        JavaApplication integrationModel = null;
        try {
            File leadingModelFile = findRootModelFileInDirectory(leadingModelDirectory);
            File integrationModelFile = findRootModelFileInDirectory(integrationModelDirectory);
            if (leadingModelFile == null || integrationModelFile == null) {
                throw new DiffingNotSupportedException("No Java2KDM models provided to compare.");
            }
            leadingModel = KDMUtil.loadKDMModel(leadingModelFile);
            integrationModel = KDMUtil.loadKDMModel(integrationModelFile);
        } catch (final IOException e) {
            throw new DiffingException("Failed to load source models", e);
        }

        logger.debug("Diffing: MATCHING PHASE");
        JavaModelMatchEngine matchEngine = new JavaModelMatchEngine();
        MatchModel matchModel;
        try {
            matchModel = matchEngine.modelMatch(integrationModel, leadingModel, matchOptions);
        } catch (InterruptedException e) {
            throw new DiffingException("Failed to build match model.", e);
        }

        logger.debug("Diffing: MAIN DIFFING PHASE");
        JavaModelDiffEngine javaModelDiffEngine = new JavaModelDiffEngine(ignorePackages);
        DiffModel diffModel = javaModelDiffEngine.doDiff(matchModel, false);

        logger.debug("Diffing: POST PROCESSING PHASE");

        DiffModelPostProcessor postProcessor = new DiffModelPostProcessor(javaModelDiffEngine.getMatchManager());
        postProcessor.process(diffModel);

        return diffModel;

    }

    /**
     * Find the MoDisco root model file within a directory.
     * 
     * @param basePath
     *            The path of the directory to search in.
     * @return The detected File.
     */
    private File findRootModelFileInDirectory(final URI basePath) {
        File leadingModelDirectory = new File(basePath);
        String[] rootFiles = leadingModelDirectory.list(new PatternFilenameFilter(".*java2kdm\\.xmi"));
        if (rootFiles.length < 1) {
            return null;
        }
        File moDiscoRootFile = new File(leadingModelDirectory.getAbsolutePath() + File.separator + rootFiles[0]);
        return moDiscoRootFile;
    }

    /**
     * Build old match options for.
     * 
     * @param ignorePackages
     *            The configured packages to ignore.
     * @return The prepared match options.
     */
    private Map<String, Object> buildMatchOptions(List<String> ignorePackages) {
        final Map<String, Object> options = new EMFCompareMap<String, Object>();
        JavaModelMatchScopeProvider scopeProvider = new JavaModelMatchScopeProvider(ignorePackages);
        options.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER, scopeProvider);
        options.put(MatchOptions.OPTION_DISTINCT_METAMODELS, true);
        options.put(MatchOptions.OPTION_IGNORE_XMI_ID, true);
        options.put(MatchOptions.OPTION_IGNORE_ID, true);
        return options;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public void init() {
        Java2KDMDiffPackage.eINSTANCE.eClass();
    }

}

package org.splevo.diffing;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Comparison;

/**
 * Default service to run the an extractor.
 */
public class DefaultDiffingService implements DiffingService {

    private Logger logger = Logger.getLogger(DefaultDiffingService.class);

    /** Regular expressions defining packages to be ignored. */
    private static final String MSG_DIFFER_NOT_AVAILABLE = "No differs available.";

    @Override
    public Comparison diffSoftwareModels(URI leadingModelDirectory, URI integrationModelDirectory,
            Map<String, Object> diffingOptions) throws DiffingException {

        List<Differ> differs = getDiffers();
        if (differs.size() == 0) {
            throw new DiffingException(String.format(MSG_DIFFER_NOT_AVAILABLE));
        }

        Comparison diffModel = CompareFactory.eINSTANCE.createComparison();
        diffModel.setThreeWay(false);

        for (Differ differ : differs) {
            Comparison partComparisonModel;
            try {
                partComparisonModel = differ.doDiff(leadingModelDirectory, integrationModelDirectory, diffingOptions);
                // diffModel.getDifferences().addAll(partComparisonModel.getDifferences());
                diffModel.getMatches().addAll(partComparisonModel.getMatches());
                diffModel.getMatchedResources().addAll(partComparisonModel.getMatchedResources());
            } catch (DiffingNotSupportedException e) {
                logger.info("The differ does not support the provided input");
            }
        }

        return diffModel;
    }

    /**
     * Load the software model extractor implementations registered for the according extension
     * point.
     * 
     * {@inheritDoc}
     */
    @Override
    public List<Differ> getDiffers() {
        return Activator.getDiffers();
    }
}

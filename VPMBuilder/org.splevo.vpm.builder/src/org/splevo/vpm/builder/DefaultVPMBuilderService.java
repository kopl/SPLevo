package org.splevo.vpm.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * Default service to run the an extractor.
 */
public class DefaultVPMBuilderService implements VPMBuilderService {

    private Logger logger = Logger.getLogger(DefaultVPMBuilderService.class);

    /** Regular expressions defining packages to be ignored. */
    private static final String MSG_DIFFER_NOT_AVAILABLE = "No builder available.";
    private static final String DIFFER_EXTENSION_POINT_ID = "org.splevo.vpm.builder";
    private static final String EXTENSION_POINT_ATTR_DIFFER_CLASS = "builder.class";

    @Override
    public VariationPointModel buildVPM(DiffModel diffModel, String variantIDLeading, String variantIDIntegration,
            Map<String, Object> builderOptions) throws VPMBuildException {

        List<VPMBuilder> builders = getVPMBuilders();
        if (builders.size() == 0) {
            throw new VPMBuildException(String.format(MSG_DIFFER_NOT_AVAILABLE));
        }

        VariationPointModel vpm = variabilityFactory.eINSTANCE.createVariationPointModel();

        for (VPMBuilder vpmBuilder : builders) {
            VariationPointModel builderVPM = vpmBuilder.buildVPM(diffModel, variantIDLeading, variantIDIntegration);
            mergeIntoVPM(vpm, builderVPM);
        }

        return vpm;
    }

    /**
     * Merge a variation point model into another one. The second vpm will be merged into the first
     * one.
     * 
     * @param enrichVPM
     *            The VPM to enrich.
     * @param mergeVPM
     *            The VPM to merge into the other one.
     */
    private void mergeIntoVPM(VariationPointModel enrichVPM, VariationPointModel mergeVPM) {
        if (mergeVPM != null) {
            enrichVPM.getSoftwareElements().addAll(mergeVPM.getSoftwareElements());
            enrichVPM.getVariationPointGroups().addAll(mergeVPM.getVariationPointGroups());
        }
    }

    /**
     * Load the software model extractor implementations registered for the according extension
     * point.
     * 
     * {@inheritDoc}
     */
    @Override
    public List<VPMBuilder> getVPMBuilders() {
        List<VPMBuilder> builders = new LinkedList<VPMBuilder>();

        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            logger.warn("No extension point registry available.");
            return null;
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(DIFFER_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            logger.warn("No extension point found for the ID " + DIFFER_EXTENSION_POINT_ID);
            return null;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_DIFFER_CLASS);
                    if ((o != null) && (o instanceof VPMBuilder)) {
                        VPMBuilder differ = (VPMBuilder) o;
                        builders.add(differ);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load VPM builder extension", e);
                }
            }
        }

        if (differIdsNotUnique(builders)) {
            logger.warn("Two or more VPM builder with the same id loaded.");
        }

        return builders;
    }

    /**
     * Check if there are two or more builders with the same id.
     * 
     * @param builders
     *            The builders to check.
     * @return True if the same id is used more than once. False otherwise.
     */
    private boolean differIdsNotUnique(List<VPMBuilder> builders) {
        List<String> ids = new LinkedList<String>();
        for (VPMBuilder builder : builders) {
            if (ids.contains(builder.getId())) {
                return true;
            }
            ids.add(builder.getId());
        }
        return false;

    }
}

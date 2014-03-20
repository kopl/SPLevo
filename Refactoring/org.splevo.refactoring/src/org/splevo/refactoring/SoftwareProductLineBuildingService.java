package org.splevo.refactoring;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.splevo.vrm.VariabilityRealizationConfiguration;
import org.splevo.vrm.VariabilityRealizationModel;

import com.google.common.collect.Lists;

/**
 * This service is capable of building the SPL from a {@link VariabilityRealizationModel}.
 */
public abstract class SoftwareProductLineBuildingService {

    private static final String REFACTORING_EXTENSION_POINT_ID = "org.splevo.refactoring.refactoringservice";
    private static final String EXTENSION_POINT_ATTR_REFACTORING_CLASS = "refactoringservice.class";

	/**
     * Builds the Software Product Line according to a {@link VariabilityRealizationModel} and {@link VariabilityRealizationConfiguration}s.
     *
     * @param vrm The {@link VariabilityRealizationModel}.
     * @param variabilityRealizationConfigurations A {@link List} of {@link VariabilityRealizationConfiguration}s.
     */
    public abstract void buildSoftwareProductLine(VariabilityRealizationModel vrm,
    		List<VariabilityRealizationConfiguration> variabilityRealizationConfigurations);

    /**
     * Gets all {@link RefactoringService} that are registered to this extension point.
     *
     * @return A {@link List} of {@link RefactoringService}s.
     */
    protected List<VariabilityRefactoring> getRefactoringServices() {
        List<VariabilityRefactoring> refactorings = Lists.newLinkedList();
        Logger logger = getLogger();

        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            logger.warn("No extension point registry available.");
            return null;
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(REFACTORING_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            logger.warn("No extension point found for the ID " + REFACTORING_EXTENSION_POINT_ID);
            return null;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_REFACTORING_CLASS);
                    if ((o != null) && (o instanceof VariabilityRefactoring)) {
                        VariabilityRefactoring refactoring = (VariabilityRefactoring) o;
                        refactorings.add(refactoring);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load refactoring service extension", e);
                }
            }
        }

        if (refactoringIdsNotUnique(refactorings)) {
            logger.warn("Two or more refactoring services with the same id loaded.");
        }

        return refactorings;
    }

	/**
	 * Gets the logger that is responsible for this class.
	 *
	 * @return The {@link Logger}.
	 */
	protected abstract Logger getLogger();

	/**
     * Check if there are two or more builders with the same id.
     *
     * @param refactorings
     *            The builders to check.
     * @return True if the same id is used more than once. False otherwise.
     */
    private boolean refactoringIdsNotUnique(List<VariabilityRefactoring> refactorings) {
        List<String> ids = new LinkedList<String>();
        for (VariabilityRefactoring refactoring : refactorings) {
            if (ids.contains(refactoring.getId())) {
                return true;
            }
            ids.add(refactoring.getId());
        }
        return false;

    }
}

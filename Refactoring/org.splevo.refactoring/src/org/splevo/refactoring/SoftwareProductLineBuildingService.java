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
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vrm.VariabilityRealizationConfiguration;

/**
 * This service is capable of building the SPL from a {@link VariationPointModel}.
 */
public abstract class SoftwareProductLineBuildingService {
	
    private static final String REFACTORING_EXTENSION_POINT_ID = "org.splevo.refactoring.refactoringservice";
    private static final String EXTENSION_POINT_ATTR_REFACTORING_CLASS = "refactoringservice.class";

	/**
     * Builds the Software Product Line according to a {@link VariationPointModel} and {@link VariabilityRealizationConfiguration}s.
     * 
     * @param vpm The {@link VariationPointModel}.
     * @param variabilityRealizationConfigurations A {@link List} of {@link VariabilityRealizationConfiguration}s. 
     */
    public abstract void buildSoftwareProductLine(VariationPointModel vpm, 
    		List<VariabilityRealizationConfiguration> variabilityRealizationConfigurations);
    
    /**
     * Gets all {@link RefactoringService} that are registered to this extension point.
     * 
     * @return A {@link List} of {@link RefactoringService}s.
     */
    protected List<RefactoringService> getRefactoringServices() {
        List<RefactoringService> refactoringServices = new LinkedList<RefactoringService>();
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
                    if ((o != null) && (o instanceof RefactoringService)) {
                    	RefactoringService differ = (RefactoringService) o;
                        refactoringServices.add(differ);
                    }
                } catch (CoreException e) {
                    logger.error("Failed to load refactoring service extension", e);
                }
            }
        }

        if (refactoringServiceIdsNotUnique(refactoringServices)) {
            logger.warn("Two or more refactoring services with the same id loaded.");
        }

        return refactoringServices;
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
     * @param refServices
     *            The builders to check.
     * @return True if the same id is used more than once. False otherwise.
     */
    private boolean refactoringServiceIdsNotUnique(List<RefactoringService> refServices) {
        List<String> ids = new LinkedList<String>();
        for (RefactoringService refService : refServices) {
            if (ids.contains(refService.getId())) {
                return true;
            }
            ids.add(refService.getId());
        }
        return false;

    }
}

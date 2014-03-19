package org.splevo.refactoring;

import java.util.List;

import org.apache.log4j.Logger;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vrm.VariabilityRealizationConfiguration;

/**
 * The default service to refactor the input models according to a {@link VariationPointModel}.
 */
public class DefaultSoftwareProductLineBuildingService extends SoftwareProductLineBuildingService {

    private Logger logger = Logger.getLogger(DefaultSoftwareProductLineBuildingService.class);

	@Override
	public void buildSoftwareProductLine(
			VariationPointModel variationPointModel,
			List<VariabilityRealizationConfiguration> variabilityRealizationConfigurations) {
		// get all registered refactoring services
		List<RefactoringService> refactoringServices = getRefactoringServices();
		
		// execute refactoring
		// TODO: respect configs
		for (RefactoringService refactoringService : refactoringServices) {
			refactoringService.refactor(variationPointModel);
		}
	}

	@Override
	protected Logger getLogger() {
		return this.logger;
	}
}

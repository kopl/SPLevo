package org.splevo.refactoring;

import java.util.List;

import org.apache.log4j.Logger;
import org.splevo.vrm.VariabilityRealizationConfiguration;
import org.splevo.vrm.VariabilityRealizationModel;

/**
 * The default service to refactor the input models according to a
 * {@link VariabilityRealizationModel}.
 */
public class DefaultSoftwareProductLineBuildingService extends SoftwareProductLineBuildingService {

    private Logger logger = Logger.getLogger(DefaultSoftwareProductLineBuildingService.class);

    @Override
    public void buildSoftwareProductLine(VariabilityRealizationModel vrm,
            List<VariabilityRealizationConfiguration> variabilityRealizationConfigurations) {
        // get all registered refactoring services
        List<VariabilityRefactoring> refactorings = getRefactoringServices();

        // execute refactoring
        // TODO: respect configs
        for (VariabilityRefactoring refactoringService : refactorings) {
            //refactoringService.refactor(vrm);
        }
    }

    @Override
    protected Logger getLogger() {
        return this.logger;
    }
}
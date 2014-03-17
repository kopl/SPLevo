package org.splevo.refactoring;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * The default service to refactor the input models according to a {@link VariationPointModel}.
 */
public class DefaultRefactoringService implements RefactoringService {

    private Logger logger = Logger.getLogger(DefaultRefactoringService.class);

    @Override
    public void buildSoftwareProductLine(VariationPointModel vpm) {
        if (vpm == null) {
            throw new IllegalArgumentException();
        }

        // FIXME: resource null. some bug in copyvpmjob.
        Resource eResource = ((JaMoPPSoftwareElement) vpm.getSoftwareElements().get(0)).getJamoppElement().eResource();

        throw new UnsupportedOperationException("Refactoring not yet implemented");
    }
}

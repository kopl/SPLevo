package org.splevo.refactoring;

import org.apache.log4j.Logger;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * The default service to refactor the input models according to a {@link VariationPointModel}.
 */
public class DefaultRefactoringService implements RefactoringService {

    private Logger logger = Logger.getLogger(DefaultRefactoringService.class);

    @Override
    public void buildSoftwareProductLine(VariationPointModel vpm, String outputPath) {
        throw new UnsupportedOperationException("Refactoring not yet implemented");
        // TODO: copy project contents
        // TODO: copy model: Copier copier = copyModel(vpm, outputPath);
        // TODO: do refactoring
    }

//    private Copier copyModel(EObject inputModel, String to) {
//        // delete old file if existing
//        File outputFile = new File(to);
//        if (outputFile.exists()) {
//            if (!outputFile.delete()) {
//                logger.error("File could not be deleted: " + outputFile.getPath());
//            }
//        }
//
//        // get a copier
//        Copier copier = new Copier(false, true);
//        EObject copiedModel = copier.copy(inputModel);
//        copier.copyReferences();
//
//        // create new ResourceSet
//        ResourceSet outputResourceSet = new ResourceSetImpl();
//        URI uri = URI.createFileURI(outputFile.getAbsolutePath());
//        Resource outputResource = outputResourceSet.createResource(uri);
//        outputResourceSet.getResources().add(outputResource);
//        outputResource.getContents().add(copiedModel);
//
//        return copier;
//    }
}

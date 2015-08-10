package org.splevo.jamopp.refactoring.tests;

import org.splevo.jamopp.refactoring.tests.util.RefactoringTestUtilsBase;
import org.splevo.vpm.variability.VariationPointModel;

/**
 * Utility class for JaMoPP refactoring tests. Special methods for constructing required models
 * exist.
 */
public class JaMoPPRefactoringsTestUtils extends RefactoringTestUtilsBase {

    private static final String PATH_TESTCODE_FOLDER = "testcode/";

    /**
     * Collection of test code paths relevant for the tests.
     */
    private static enum TestcodePaths {
        VARIANTS_WITH_COMMENTS("variantsWithComments");

        private String folderName;

        private TestcodePaths(String folderName) {
            this.folderName = folderName;
        }

        public String getName() {
            return folderName;
        }
    }

    @Override
    protected String getBasePath() {
        return PATH_TESTCODE_FOLDER;
    }

    /**
     * Creates a VPM for simple source code copies including trashy comments.
     * 
     * @param layoutInformation
     *            Flag to indicate if the layout information shall be loaded (True) or not (False).
     * @return The VPM for the specified source code.
     * @throws Exception In case of IO error.
     */
    public VariationPointModel getVariantsWithComments(boolean layoutInformation) throws Exception {
        return getVPM(TestcodePaths.VARIANTS_WITH_COMMENTS, layoutInformation);
    }

    private VariationPointModel getVPM(TestcodePaths testcode, boolean layoutInformation) throws Exception {
        return initializeVariationPointModel(testcode.getName(), layoutInformation);
    }

}

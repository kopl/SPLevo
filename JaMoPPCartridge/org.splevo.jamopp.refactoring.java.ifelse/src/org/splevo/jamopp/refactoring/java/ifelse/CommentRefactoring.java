package org.splevo.jamopp.refactoring.java.ifelse;

import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.VariationPoint;

public class CommentRefactoring implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "Comment Refactoring";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.CommentRefactoring";
    private static final String COMMENT_TEXT = "/*FIXME: Variability could not be handled*/";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint vp) {
        Commentable vpLocation = ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
        RefactoringUtil.addCommentBefore(vpLocation, COMMENT_TEXT);
    }


    @Override
    public boolean canBeAppliedTo(VariationPoint variationPoint) {
        return true;
    }

    @Override
    public String getId() {
        return REFACTORING_ID;
    }
}

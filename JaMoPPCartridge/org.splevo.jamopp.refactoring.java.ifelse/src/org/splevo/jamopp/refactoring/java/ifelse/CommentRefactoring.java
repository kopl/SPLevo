package org.splevo.jamopp.refactoring.java.ifelse;

import java.util.Map;

import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.realization.RealizationFactory;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * This refctoring annotates a variation point's location with a fixme comment with the following
 * content: "Variability could not be handled". This refactoring can be used to annotate variation
 * points that cannot be provessed.
 */
public class CommentRefactoring implements VariabilityRefactoring {

    private static final String REFACTORING_NAME = "Comment Refactoring";
    private static final String REFACTORING_ID = "org.splevo.jamopp.refactoring.java.ifelse.CommentRefactoring";
    private static final String COMMENT_TEXT = "FIXME: Variability could not be handled\n";

    @Override
    public VariabilityMechanism getVariabilityMechanism() {
        VariabilityMechanism variabilityMechanism = RealizationFactory.eINSTANCE.createVariabilityMechanism();
        variabilityMechanism.setName(REFACTORING_NAME);
        variabilityMechanism.setRefactoringID(REFACTORING_ID);
        return variabilityMechanism;
    }

    @Override
    public void refactor(VariationPoint variationPoint, Map<String, String> refactoringOptions) {
        Commentable vpLocation = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        StringBuilder sb = new StringBuilder();
        sb.append(COMMENT_TEXT + "\n");
        for (Variant variant : variationPoint.getVariants()) {
            sb.append("Variant: " + variant.getId() + "\n");
            for (SoftwareElement se : variant.getImplementingElements()) {
                String label = JaMoPPElementUtil.getLabel(((JaMoPPSoftwareElement) se).getJamoppElement());
                sb.append(label + "\n");
            }
        }
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

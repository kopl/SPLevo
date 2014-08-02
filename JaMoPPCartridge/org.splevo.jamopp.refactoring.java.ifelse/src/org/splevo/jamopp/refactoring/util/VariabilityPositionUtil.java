package org.splevo.jamopp.refactoring.util;

import java.util.List;

import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * Handles the calculation of the variablity position for variation points.
 */
public class VariabilityPositionUtil {

    /**
     * Calculates the position where the variable statements of a given variation point have to be
     * placed in the leading variant.
     * 
     * @param variationPoint
     *            The variation point.
     * @return The position.
     */
    public static int getVariabilityPosition(VariationPoint variationPoint) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();

        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                Statement firstLeadingImplElement = (Statement) ((JaMoPPSoftwareElement) variant
                        .getImplementingElements().get(0)).getJamoppElement();
                return JaMoPPElementUtil.getPositionInContainer(firstLeadingImplElement);
            }
        }

        Statement firstImplElement = (Statement) ((JaMoPPSoftwareElement) variationPoint.getVariants().get(0)
                .getImplementingElements().get(0)).getJamoppElement();
        StatementListContainer integrationContainer = (StatementListContainer) firstImplElement.eContainer();
        int posInIntegration = JaMoPPElementUtil.getPositionInContainer(firstImplElement);

        List<Statement> predecessors = integrationContainer.getStatements().subList(0, posInIntegration);

        intersect(vpLocation, predecessors);

        if (predecessors.size() == 0) {
            return 0;
        }

        int pos = getEndPositionOfFirstGroupOccurence(vpLocation, predecessors);

        return pos + 1;
    }

    private static void intersect(StatementListContainer vpLocation, List<Statement> predecessors) {
        List<Statement> toBeDeleted = Lists.newLinkedList();
        for (Statement predecessor : predecessors) {
            boolean found = false;
            for (Statement vpLocationStatement : vpLocation.getStatements()) {
                if (isVariabilityCondition(vpLocationStatement)) {
                    Block block = (Block) ((Condition) vpLocationStatement).getStatement();
                    for (Statement ifBlockStatement : block.getStatements()) {
                        if (RefactoringUtil.areEqual(predecessor, ifBlockStatement)) {
                            found = true;
                            break;
                        }
                    }
                }
                if (RefactoringUtil.areEqual(predecessor, vpLocationStatement)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                toBeDeleted.add(predecessor);
            }
        }
        predecessors.removeAll(toBeDeleted);
    }

    private static int getEndPositionOfFirstGroupOccurence(StatementListContainer targetContainer,
            List<Statement> predecessors) {
        int predecessorPos = 0;

        for (int i = 0; i < targetContainer.getStatements().size(); i++) {
            Statement baseStatement = targetContainer.getStatements().get(i);
            Statement predecessor = predecessors.get(predecessorPos);

            if (RefactoringUtil.areEqual(baseStatement, predecessor)) {
                predecessorPos++;
            } else if (isVariabilityCondition(baseStatement)) {
                Block ifBlock = (Block) ((Condition) baseStatement).getStatement();
                predecessorPos += countVariableStatements(ifBlock,
                        predecessors.subList(predecessorPos, predecessors.size()));
            }

            if (predecessorPos == predecessors.size()) {
                if (predecessor instanceof LocalVariableStatement) {
                    int posNextVarCond = posNextVariabilityCondition(targetContainer, i);
                    return posNextVarCond != -1 ? posNextVarCond : i;
                }
                return i;
            }
        }

        return -1;
    }

    private static int posNextVariabilityCondition(StatementListContainer targetContainer, int startIndex) {
        for (int i = startIndex; i < targetContainer.getStatements().size(); i++) {
            Statement currentStatement = targetContainer.getStatements().get(i);
            if (isVariabilityCondition(currentStatement)) {
                return i;
            }
        }
        return -1;
    }

    private static int countVariableStatements(StatementListContainer container, List<Statement> precedingElements) {
        int result = 0;
        for (Statement statement : container.getStatements()) {
            if (result == precedingElements.size()) {
                break;
            }
            if (RefactoringUtil.areEqual(statement, precedingElements.get(result))) {
                result++;
            }
        }
        return result;
    }

    private static boolean isVariabilityCondition(Commentable statement) {
        if (!(statement instanceof Condition)) {
            return false;
        }
        Condition condition = (Condition) statement;

        if (!(condition.getCondition() instanceof IdentifierReference)) {
            return false;
        }

        ReferenceableElement conditionIdentifierRefTarget = ((IdentifierReference) condition.getCondition())
                .getTarget();

        return conditionIdentifierRefTarget.getName().equals(SPLConfigurationUtil.CONFIGURATION_READER_CLASS_NAME);
    }
}

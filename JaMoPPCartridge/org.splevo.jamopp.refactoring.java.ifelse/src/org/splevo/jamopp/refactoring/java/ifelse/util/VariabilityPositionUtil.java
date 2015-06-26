/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and initial documentation
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.util;

import java.util.LinkedList;
import java.util.List;

import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

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

        predecessors = intersect(vpLocation, predecessors);

        if (predecessors.size() == 0) {
            return 0;
        }

        int pos = searchInVPLocation(variationPoint, predecessors);

        return pos + 1;
    }

    private static List<Statement> intersect(StatementListContainer vpLocation, List<Statement> predecessors) {
        List<Statement> result = new LinkedList<Statement>(predecessors);
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
                result.remove(predecessor);
            }
        }
        return result;
    }

    private static int searchInVPLocation(VariationPoint variationPoint, List<Statement> predecessors) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();

        int predecessorPos = 0;

        for (int i = 0; i < vpLocation.getStatements().size(); i++) {
            Statement baseStatement = vpLocation.getStatements().get(i);
            Statement predecessor = predecessors.get(predecessorPos);

            if (RefactoringUtil.areEqual(baseStatement, predecessor)) {
                predecessorPos++;
            } else if (isVariabilityCondition(baseStatement)) {
                Block ifBlock = (Block) ((Condition) baseStatement).getStatement();
                predecessorPos += countEqualElementsInContainer(ifBlock,
                        predecessors.subList(predecessorPos, predecessors.size()));
            }

            if (predecessorPos == predecessors.size()) {
                if (predecessor instanceof LocalVariableStatement) {
                    String variableName = ((LocalVariableStatement) predecessor).getVariable().getName();
                    int numVariants = getNumVariantsFor(variationPoint, variableName);
                    i += numVariants;
                }
                return i;
            }
        }

        return -1;
    }

    private static int getNumVariantsFor(VariationPoint variationPoint, String variableName) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();

        VariationPointModel vpm = variationPoint.getGroup().getModel();

        int numVariants = 0;
        for (VariationPointGroup vpg : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpg.getVariationPoints()) {
                Commentable currentVPL = ((JaMoPPSoftwareElement) vp.getLocation()).getJamoppElement();
                if (currentVPL != vpLocation) {
                    continue;
                }
                for (Variant v : vp.getVariants()) {
                    for (SoftwareElement se : v.getImplementingElements()) {
                        Commentable element = ((JaMoPPSoftwareElement) se).getJamoppElement();
                        if (element instanceof LocalVariableStatement
                                && ((LocalVariableStatement) element).getVariable().getName().equals(variableName)) {
                            numVariants++;
                            break;
                        }
                    }
                }
            }
        }
        return numVariants;
    }

    private static int countEqualElementsInContainer(StatementListContainer container, List<Statement> elements) {
        int result = 0;
        for (Statement statement : container.getStatements()) {
            if (result == elements.size()) {
                break;
            }
            if (RefactoringUtil.areEqual(statement, elements.get(result))) {
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

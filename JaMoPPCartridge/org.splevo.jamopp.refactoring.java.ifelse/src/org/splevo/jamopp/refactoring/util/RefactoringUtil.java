package org.splevo.jamopp.refactoring.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.Parametrizable;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.Void;
import org.emftext.language.java.variables.LocalVariable;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * Provides utility methods for the refactorings.
 */
public final class RefactoringUtil {

    private RefactoringUtil() {
    }

    /**
     * For each variant of a variation point, this method takes all common and variable statements
     * and extracts them into separate, variant-specific methods, which will be added to the parent
     * class. Clears the variation point's location afterwards. Finally, clears all variants and
     * adds new variants that represent the extracted methods.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     */
    public static void extractVariantsIntoMethods(VariationPoint variationPoint) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();
        Class parentClass = findContainingClassRec(vpLocation);

        HashMap<Variant, List<Statement>> variantStatements = buildVariantStatementMap(variationPoint);

        vpLocation.getStatements().clear();
        variationPoint.getVariants().clear();
        TypeReference returnType = getReturnType(vpLocation);

        for (Variant variant : variantStatements.keySet()) {
            List<Statement> statements = variantStatements.get(variant);

            ClassMethod method = MembersFactory.eINSTANCE.createClassMethod();
            method.setName("extracted" + variant.getVariantId());
            method.getStatements().addAll(statements);
            method.setTypeReference(returnType);

            vpLocation.getClassClass().getMembers().add(method);

            MethodCall methodCall = ReferencesFactory.eINSTANCE.createMethodCall();
            methodCall.setTarget(method);

            Statement statement = null;
            if (returnType == null || returnType.getTarget() instanceof Void) {
                ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
                expressionStatement.setExpression(methodCall);
                statement = expressionStatement;
            } else {
                Return returnStatement = StatementsFactory.eINSTANCE.createReturn();
                returnStatement.setReturnValue(methodCall);
                statement = returnStatement;
            }

            JaMoPPSoftwareElement newJaMoPPElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
            newJaMoPPElement.setJamoppElement(statement);
            vpLocation.getStatements().add(statement);

            Variant newVariant = RefactoringUtil.cloneVariant(variant, variationPoint);
            newVariant.getImplementingElements().add(newJaMoPPElement);
            variationPoint.getVariants().add(newVariant);

            parentClass.getMembers().add(method);
        }
    }

    private static HashMap<Variant, List<Statement>> buildVariantStatementMap(VariationPoint variationPoint) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();

        HashMap<Variant, List<Statement>> variantStatements = new HashMap<Variant, List<Statement>>();

        Set<VariationPoint> relatedVPs = getVPsWithSameLocation(variationPoint);

        for (VariationPoint relatedVP : relatedVPs) {
            int posVariability = RefactoringUtil.getVariabilityPosition(relatedVP);
            for (Variant variant : relatedVP.getVariants()) {
                List<Statement> statements;
                if (!variantStatements.containsKey(variant)) {
                    statements = new LinkedList<Statement>();
                    Collection<Statement> copies = EcoreUtil.copyAll(vpLocation.getStatements());
                    statements.addAll(copies);
                    variantStatements.put(variant, statements);
                } else {
                    statements = variantStatements.get(variant);
                }

                List<Statement> variableStatements = getImplementingStatements(variant);
                statements.addAll(posVariability, variableStatements);
            }
        }
        return variantStatements;
    }

    private static TypeReference getReturnType(StatementListContainer vpLocation) {
        ClassMethod method = findContainingMethodRec(vpLocation);
        if (method != null) {
            return method.getTypeReference();
        }
        return null;
    }

    private static ClassMethod findContainingMethodRec(StatementListContainer vpLocation) {
        EObject container = vpLocation;
        while (container != null) {
            if (container instanceof ClassMethod) {
                return ((ClassMethod) container);
            }
            container = container.eContainer();
        }
        return null;
    }

    private static Class findContainingClassRec(StatementListContainer vpLocation) {
        EObject container = vpLocation;
        while (container != null) {
            if (container instanceof Class) {
                return ((Class) container);
            }
            container = container.eContainer();
        }
        return null;
    }

    private static List<Statement> getImplementingStatements(Variant variant) {
        LinkedList<Statement> statements = new LinkedList<Statement>();
        for (SoftwareElement se : variant.getImplementingElements()) {
            Statement statement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
            statements.add(statement);
        }
        return statements;
    }

    private static Set<VariationPoint> getVPsWithSameLocation(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        StatementListContainer vpLocation = (StatementListContainer) jamoppElement;
        Set<VariationPoint> relatedVPs = new HashSet<VariationPoint>();
        VariationPointModel model = variationPoint.getGroup().getModel();
        for (VariationPointGroup group : model.getVariationPointGroups()) {
            for (VariationPoint vp : group.getVariationPoints()) {
                Commentable currentVPElement = ((JaMoPPSoftwareElement) variationPoint.getLocation())
                        .getJamoppElement();
                if (currentVPElement == vpLocation) {
                    relatedVPs.add(vp);
                }
            }
        }
        return relatedVPs;
    }

    /**
     * Checks whether a variation point's variants contain variables with equal names but different
     * types.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if such variables are contained; <code>false</code> otherwise.
     */
    public static boolean containsVarsSameNameDiffType(VariationPoint variationPoint) {
        HashMap<String, Type> namedVariables = new HashMap<String, Type>();
        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Commentable currentElement = ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (currentElement instanceof LocalVariableStatement) {
                    LocalVariable variable = ((LocalVariableStatement) currentElement).getVariable();
                    Type variableType = variable.getTypeReference().getTarget();
                    Type oldValue = namedVariables.put(variable.getName(), variableType);
                    if (oldValue != null && !oldValue.getClass().equals(variableType.getClass())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Deletes the statements in a variation point's location that are variable (occur in a
     * variant).
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     */
    public static void deleteVariableElements(VariationPoint variationPoint) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();
        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                for (SoftwareElement se : variant.getImplementingElements()) {
                    Statement variantStatement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
                    vpLocation.getStatements().remove(variantStatement);
                }
            }
        }
    }

    /**
     * Calculates the position of the variable elements in the variation point's location.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return The position. -1 if none found.
     */
    public static int getVariabilityPosition(VariationPoint variationPoint) {
        for (Variant variant : variationPoint.getVariants()) {
            Statement firstImplementingElement = (Statement) ((JaMoPPSoftwareElement) variant.getImplementingElements()
                    .get(0)).getJamoppElement();
            int position = JaMoPPElementUtil.getPositionInContainer(firstImplementingElement);
            if (position == -1) {
                continue;
            }
            return position;
        }

        return -1;
    }

    /**
     * Extracts the initial value expression from a local variable into a independent statement and
     * sets the variable's initial value to <code>null</code>.
     * 
     * @param variable
     *            The {@link LocalVariable}.
     * @return The extracted assignment as {@link ExpressionStatement}.
     */
    public static ExpressionStatement extractAssignment(LocalVariable variable) {
        ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
        expressionStatement.setExpression(variable.getInitialValue());
        variable.setInitialValue(null);

        return expressionStatement;
    }

    private static Variant cloneVariant(Variant variant, VariationPoint parentVariationPoint) {
        Variant newVariant = variabilityFactory.eINSTANCE.createVariant();
        newVariant.setVariationPoint(parentVariationPoint);
        newVariant.setVariantId(variant.getVariantId());
        newVariant.setLeading(variant.getLeading());
        newVariant.setChildFeature(variant.getChildFeature());
        return newVariant;
    }

    /**
     * Checks whether a class has a constructor with equal parameters.
     * 
     * @param c
     *            The {@link Class}.
     * @param constructor
     *            The {@link Constructor} to compare with.
     * @return <code>true</code> if such a constructor is found; <code>false</code> otherwise.
     */
    public static boolean hasConstructorWithSameParameters(Class c, Constructor constructor) {
        for (Constructor vpConstructor : c.getConstructors()) {
            if (hasEqualParameters(constructor, vpConstructor)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasEqualParameters(Parametrizable p1, Parametrizable p2) {
        if (p2.getParameters().size() != p1.getParameters().size()) {
            return false;
        }
        for (Parameter param : p2.getParameters()) {
            if (!p1.getParameters().contains(param)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a condition with an empty if-block. Matches the SPL configuration with the given
     * variant id within the condition.
     * 
     * @param variantId
     *            The variant id as {@link String}.
     * @return The generated {@link Condition}.
     */
    public static Condition generateVariantIDMatchingConditionWithEmptyIfBlock(String variantId) {
        Condition currentCondition = StatementsFactory.eINSTANCE.createCondition();
        currentCondition.setCondition(SPLConfigurationUtil.generateConfigMatchingExpression(variantId));
        Block currentBlock = StatementsFactory.eINSTANCE.createBlock();
        currentCondition.setStatement(currentBlock);
        return currentCondition;
    }

    /**
     * Fills the if-block of a given condition with the statements that are contained in the given
     * variant (as implementing elements).
     * 
     * @param variant
     *            The {@link Variant}.
     * @param currentCondition
     *            The {@link Condition} to be filled.
     * @param localVariableStatements
     *            A {@link Map} that stores the local variables to their names that were found.
     */
    public static void fillIfBlockWithVariantElements(Variant variant, Condition currentCondition,
            Map<String, LocalVariableStatement> localVariableStatements) {
        for (SoftwareElement se : variant.getImplementingElements()) {
            Statement variantStatement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
            if (variantStatement instanceof LocalVariableStatement) {
                LocalVariableStatement localVariableStatement = (LocalVariableStatement) variantStatement;
                LocalVariable variable = localVariableStatement.getVariable();
                if (!localVariableStatements.containsKey(variable.getName())) {
                    localVariableStatements.put(variable.getName(), localVariableStatement);
                }
                ExpressionStatement extractedAssignment = RefactoringUtil.extractAssignment(variable);
                variable.setInitialValue(null);
                variantStatement = extractedAssignment;
            }
            ((Block) currentCondition.getStatement()).getStatements().add(variantStatement);
        }
    }

    /**
     * Checks whether all implementing elements of a variation point's variants are of a certain
     * type.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @param c
     *            The type's {@link java.lang.Class}.
     * @param <T>
     *            The type.
     * @return <code>true</code> if all elements implement the given type; <code>false</code>
     *         otherwise.
     */
    public static <T> boolean allImplementingElementsOfType(VariationPoint variationPoint, java.lang.Class<T> c) {
        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Commentable commentable = ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (!(c.isInstance(commentable))) {
                    return false;
                }
            }
        }
        return true;
    }
}

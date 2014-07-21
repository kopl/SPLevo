package org.splevo.jamopp.refactoring.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.language.java.arrays.ArrayDimension;
import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiation;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;
import org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped;
import org.emftext.language.java.arrays.ArraysFactory;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.literals.BooleanLiteral;
import org.emftext.language.java.literals.CharacterLiteral;
import org.emftext.language.java.literals.DecimalDoubleLiteral;
import org.emftext.language.java.literals.DecimalFloatLiteral;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.literals.DecimalLongLiteral;
import org.emftext.language.java.literals.Literal;
import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.Variable;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Maps;

/**
 * Provides utility methods for the refactorings.
 */
public final class RefactoringUtil {

    private static JaMoPPDiffer jaMoPPDiffer = new JaMoPPDiffer();

    private RefactoringUtil() {
    }

    /**
     * Deletes the statements in a variation point's location that are variable (occur in a
     * variant).
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     */
    public static void deleteVariableStatements(VariationPoint variationPoint) {
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
     * Deletes the members in a variation point's location that are variable (occur in a variant).
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     */
    public static void deleteVariableMembersFromLeading(VariationPoint variationPoint) {
        MemberContainer vpLocation = (MemberContainer) ((JaMoPPSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();
        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                for (SoftwareElement se : variant.getImplementingElements()) {
                    Member variantMember = (Member) ((JaMoPPSoftwareElement) se).getJamoppElement();
                    vpLocation.getMembers().remove(variantMember);
                }
            }
        }
    }

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

        // if the variation point has a leading variant, return its position in
        // its parent container
        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                Statement firstLeadingImplElement = (Statement) ((JaMoPPSoftwareElement) variant
                        .getImplementingElements().get(0)).getJamoppElement();
                return JaMoPPElementUtil.getPositionInContainer(firstLeadingImplElement);
            }
        }

        // get the first implementing element of the first variant and get its
        // position in its parent container
        Statement firstImplElement = (Statement) ((JaMoPPSoftwareElement) variationPoint.getVariants().get(0)
                .getImplementingElements().get(0)).getJamoppElement();
        StatementListContainer integrationContainer = (StatementListContainer) firstImplElement.eContainer();
        int posFirstElementInIntegrationContainer = JaMoPPElementUtil.getPositionInContainer(firstImplElement);

        List<Statement> precedingElements = integrationContainer.getStatements().subList(0,
                posFirstElementInIntegrationContainer);

        if (precedingElements.size() == 0) {
            return 0;
        }

        int pos = getEndPositionOfFirstGroupOccurence(vpLocation, precedingElements, integrationContainer);

        if (pos == -1) {
            // no occurences found - must be at beginning
            return 0;
        }

        return pos + 1;
    }

    private static int getEndPositionOfFirstGroupOccurence(StatementListContainer vpLocation,
            List<Statement> precedingElements, StatementListContainer integrationContainer) {
        int currentIndexPreElements = 0;

        HashMap<String, Expression> initialValuesToVarName = new HashMap<String, Expression>();
        for (int i = 0; i < vpLocation.getStatements().size(); i++) {
            Statement vpLocationStatement = vpLocation.getStatements().get(i);
            Statement precedingElement = precedingElements.get(currentIndexPreElements);

            boolean statementsEqual = areEqual(vpLocationStatement, precedingElement);

            if (statementsEqual) {
                currentIndexPreElements++;
            } else if (areLocalVariableStatementsWithSameName(vpLocationStatement, precedingElement)) {
                LocalVariable variable = ((LocalVariableStatement) vpLocationStatement).getVariable();
                initialValuesToVarName.put(variable.getName(), variable.getInitialValue());
                continue;
            } else if (vpLocationStatement instanceof Condition
                    && ((Condition) vpLocationStatement).getStatement() instanceof Block) {
                Block block = (Block) ((Condition) vpLocationStatement).getStatement();
                boolean hadVariableElement = false;
                for (Statement blockStatement : block.getStatements()) {
                    if (currentIndexPreElements == precedingElements.size()) {
                        return i;
                    }
                    if (areEqual(blockStatement, precedingElements.get(currentIndexPreElements))
                            || isMatchingAssingment(initialValuesToVarName, blockStatement)) {
                        currentIndexPreElements++;
                        hadVariableElement = true;
                    }
                }
                if (!hadVariableElement) {
                    currentIndexPreElements = 0;
                    continue;
                }
            } else if (!containsEqualStatement(integrationContainer, vpLocationStatement)) {
                continue;
            } else {
                currentIndexPreElements = 0;
                continue;
            }

            if (currentIndexPreElements == precedingElements.size()) {
                return i;
            }
        }

        return -1;
    }

    private static boolean isMatchingAssingment(HashMap<String, Expression> initialValuesToVarName, Statement currentEl) {
        if (currentEl instanceof ExpressionStatement
                && ((ExpressionStatement) currentEl).getExpression() instanceof AssignmentExpression
                && ((AssignmentExpression) ((ExpressionStatement) currentEl).getExpression()).getChild() instanceof IdentifierReference
                && ((IdentifierReference) ((AssignmentExpression) ((ExpressionStatement) currentEl).getExpression())
                        .getChild()).getTarget() instanceof LocalVariableStatement) {
            LocalVariableStatement localVarStat = (LocalVariableStatement) ((IdentifierReference) ((AssignmentExpression) ((ExpressionStatement) currentEl)
                    .getExpression()).getChild()).getTarget();
            Expression initialValue = initialValuesToVarName.get(localVarStat.getVariable().getName());
            if (initialValue != null) {
                if (EcoreUtil.equals(initialValue, localVarStat.getVariable().getInitialValue())) {
                    initialValuesToVarName.remove(localVarStat.getVariable().getName());
                    return true;
                }
                if (initialValue instanceof ArrayInstantiation) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean areLocalVariableStatementsWithSameName(Statement vpLocationStatement,
            Statement precedingElement) {
        return vpLocationStatement instanceof LocalVariableStatement
                && precedingElement instanceof LocalVariableStatement
                && ((LocalVariableStatement) vpLocationStatement).getVariable().getName()
                        .equals(((LocalVariableStatement) precedingElement).getVariable().getName());
    }

    private static boolean containsEqualStatement(StatementListContainer container, Statement statement) {
        for (Statement vpLocationStatement : container.getStatements()) {
            boolean statementsEqual = areEqual(statement, vpLocationStatement);
            if (statementsEqual) {
                return true;
            }

            if (vpLocationStatement instanceof Condition
                    && ((Condition) vpLocationStatement).getStatement() instanceof Block) {
                Block block = (Block) ((Condition) vpLocationStatement).getStatement();
                if (containsEqualStatement(block, statement)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean areEqual(Statement s1, Statement s2) {
        Map<String, String> settings = Maps.newHashMap();
        Comparison diff = jaMoPPDiffer.doDiff(s1, s2, settings);
        return diff.getDifferences().size() == 0;
    }

    /**
     * Extracts the initial value expression from a given element into a independent statement and
     * sets the element's initial value to <code>null</code>.
     * 
     * @param variable
     *            The {@link LocalVariable}.
     * @return The extracted assignment as {@link ExpressionStatement}.
     */
    public static ExpressionStatement extractAssignment(LocalVariable variable) {
        if (variable.getInitialValue() == null) {
            return null;
        }
        IdentifierReference varRef = ReferencesFactory.eINSTANCE.createIdentifierReference();
        varRef.setTarget(EcoreUtil.copy(variable));

        AssignmentExpression assignmentExpression = ExpressionsFactory.eINSTANCE.createAssignmentExpression();

        if (variable.getInitialValue() instanceof ArrayInstantiationByValues) {
            EList<ArrayInitializationValue> initialValues = ((ArrayInstantiationByValues) variable.getInitialValue())
                    .getArrayInitializer().getInitialValues();
            ArrayInstantiationByValuesTyped arrayInstantiationByValuesTyped = ArraysFactory.eINSTANCE
                    .createArrayInstantiationByValuesTyped();
            ArrayInitializer arrayInitializer = ArraysFactory.eINSTANCE.createArrayInitializer();
            arrayInitializer.getInitialValues().addAll(initialValues);
            arrayInstantiationByValuesTyped.setArrayInitializer(arrayInitializer);
            arrayInstantiationByValuesTyped.setTypeReference(EcoreUtil.copy(variable.getTypeReference()));
            ArrayDimension arrayDimension = ArraysFactory.eINSTANCE.createArrayDimension();
            arrayInstantiationByValuesTyped.getArrayDimensionsBefore().add(arrayDimension);
            assignmentExpression.setValue(arrayInstantiationByValuesTyped);
        } else {
            assignmentExpression.setValue(variable.getInitialValue());
        }

        assignmentExpression.setAssignmentOperator(OperatorsFactory.eINSTANCE.createAssignment());
        assignmentExpression.setChild(varRef);

        ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
        expressionStatement.setExpression(assignmentExpression);

        Literal defaultValue = getDefaultValueForVariable(variable);
        variable.setInitialValue(defaultValue);

        return expressionStatement;
    }

    private static boolean replaceLocalVariableStatementWithExpressionStatement(LocalVariableStatement statement) {
        ExpressionStatement extractedAssignment = extractAssignment(statement.getVariable());
        StatementListContainer parentContainer = (StatementListContainer) statement.eContainer();

        if (parentContainer == null) {
            return false;
        }

        int pos = parentContainer.getStatements().indexOf(statement);

        parentContainer.getStatements().remove(pos);

        if (extractedAssignment != null) {
            parentContainer.getStatements().add(pos, extractedAssignment);
        }

        return true;
    }

    /**
     * Generates a condition with an empty if-block. Matches the SPL configuration attribute with
     * the given name (from the group ID) with the given variant id within the condition.
     * 
     * @param variantId
     *            The variant id as {@link String}.
     * @param groupID
     *            The group id as {@link String}.
     * @return The generated {@link Condition}.
     */
    public static Condition generateConditionVariantIDWithEmptyIfBlock(String variantId, String groupID) {
        Condition currentCondition = StatementsFactory.eINSTANCE.createCondition();
        currentCondition.setCondition(SPLConfigurationUtil.generateConfigMatchingExpression(variantId, groupID));

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
            Statement statement = (Statement) ((JaMoPPSoftwareElement) se).getJamoppElement();
            Statement stmtCpy = EcoreUtil.copy(statement);

            searchAndHandleLocalVariables(stmtCpy, localVariableStatements);
            ensureInitialValueAndNonFinalVariableUsage(stmtCpy);

            if (stmtCpy instanceof LocalVariableStatement) {
                LocalVariableStatement localVarStat = (LocalVariableStatement) stmtCpy;
                LocalVariable variable = localVarStat.getVariable();
                removeFinalIfApplicable(variable);
                stmtCpy = extractAssignment(variable);
                variable.setInitialValue(getDefaultValueForVariable(variable));
                if (!localVariableStatements.containsKey(variable.getName())) {
                    localVariableStatements.put(variable.getName(), localVarStat);
                }
            }

            if (stmtCpy != null) {
                ((Block) currentCondition.getStatement()).getStatements().add(stmtCpy);
            }
        }
    }

    private static LocalVariable getVariableIfIsChildOgAssignmentStatement(Statement stmtCpy) {
        if (stmtCpy instanceof ExpressionStatement
                && ((ExpressionStatement) stmtCpy).getExpression() instanceof AssignmentExpression
                && ((AssignmentExpression) ((ExpressionStatement) stmtCpy).getExpression()).getChild() instanceof IdentifierReference
                && ((IdentifierReference) ((AssignmentExpression) ((ExpressionStatement) stmtCpy).getExpression())
                        .getChild()).getTarget() instanceof LocalVariable) {
            LocalVariable variable = (LocalVariable) ((IdentifierReference) ((AssignmentExpression) ((ExpressionStatement) stmtCpy)
                    .getExpression()).getChild()).getTarget();
            return variable;
        }
        return null;
    }

    private static void ensureInitialValueAndNonFinalVariableUsage(Statement stmtCpy) {
        LocalVariable variable = getVariableIfIsChildOgAssignmentStatement(stmtCpy);
        if (variable == null) {
            return;
        }

        if (variable.getInitialValue() == null) {
            variable.setInitialValue(getDefaultValueForVariable(variable));
        }
        removeFinalIfApplicable(variable);

        TreeIterator<Object> contents = EcoreUtil.getAllContents(stmtCpy.eContents());
        while (contents.hasNext()) {
            Object next = contents.next();
            if (!(next instanceof Statement)) {
                continue;
            }
            LocalVariable referencedVariable = getVariableIfIsChildOgAssignmentStatement((Statement) next);
            if (referencedVariable == null) {
                continue;
            }
            if (referencedVariable.getInitialValue() == null) {
                referencedVariable.setInitialValue(getDefaultValueForVariable(variable));
            }
            removeFinalIfApplicable(referencedVariable);
        }
    }

    private static void searchAndHandleLocalVariables(Statement statement,
            Map<String, LocalVariableStatement> localVariableStatements) {
        TreeIterator<Object> allContents = EcoreUtil.getAllContents(statement.eContents(), true);
        while (allContents.hasNext()) {
            Object next = allContents.next();
            if (next instanceof LocalVariableStatement) {
                handleLocalVariable(localVariableStatements, (LocalVariableStatement) next);
            }
        }
    }

    private static void handleLocalVariable(Map<String, LocalVariableStatement> localVariableStatements,
            LocalVariableStatement localVariableStatement) {
        LocalVariableStatement localVarStat = (LocalVariableStatement) localVariableStatement;
        LocalVariable variable = localVarStat.getVariable();

        replaceLocalVariableStatementWithExpressionStatement(localVarStat);

        removeFinalIfApplicable(variable);

        variable.setInitialValue(getDefaultValueForVariable(variable));

        String varName = variable.getName();

        if (!localVariableStatements.containsKey(varName)) {
            localVariableStatements.put(varName, localVarStat);
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

    /**
     * Checks whether a classifier import is already contained in a compilation unit.
     * 
     * @param cu
     *            The {@link CompilationUnit}.
     * @param i
     *            The {@link ClassifierImport}.
     * @return <code>true</code> if contained; <code>false</code> otherwise.
     */
    public static boolean containsImport(CompilationUnit cu, Import i) {
        for (Import currentI : cu.getImports()) {
            if (EcoreUtil.equals(currentI, i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a comment with given contents in front of an element.
     * 
     * @param vpLocation
     *            The {@link Commentable} element.
     * @param comment
     *            The comment content as {@link String}.
     */
    public static void addCommentBefore(Commentable vpLocation, String comment) {
        LayoutInformation layoutInformation = getFirstLayoutInformation(vpLocation);
        String finalComment = layoutInformation.getHiddenTokenText();
        if (finalComment.length() == 0) {
            finalComment = "/* " + comment + " */\n";
        } else {
            finalComment = "\n/* " + comment + " */\n" + finalComment;
        }
        layoutInformation.setHiddenTokenText(finalComment);
    }

    /**
     * Removes the final modifier of an element (if existing) and adds a comment with a fix-me tag
     * to the element: "\/* Removed final modifier to introduce variability. *\/"
     * 
     * @param modifieable
     *            The {@link Modifiable} element.
     */
    public static void removeFinalIfApplicable(AnnotableAndModifiable modifieable) {
        for (Modifier modifier : modifieable.getModifiers()) {
            if (modifier instanceof Final) {
                modifieable.removeModifier(Final.class);
                break;
            }
        }
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
    public static boolean hasConstructorWithEqualParameters(Class c, Constructor constructor) {
        for (Constructor vpConstructor : c.getConstructors()) {
            if (EcoreUtil.equals(constructor.getParameters(), vpConstructor.getParameters())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a class has a method with the same name and equal parameters.
     * 
     * @param memberContainer
     *            The {@link Class}.
     * @param method
     *            The {@link Method} to compare with.
     * @return <code>true</code> if such a method is found; <code>false</code> otherwise.
     */
    public static boolean hasMethodWithEqualNameAndParameters(MemberContainer memberContainer, Method method) {
        for (Method vpMethod : memberContainer.getMethods()) {
            if (method.getName().equals(vpMethod.getName())
                    && EcoreUtil.equals(method.getParameters(), vpMethod.getParameters())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a variation point's variants contain variables with equal names but different
     * types.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if such variables are contained; <code>false</code> otherwise.
     */
    public static boolean hasConflictingLocalVariables(VariationPoint variationPoint) {
        HashMap<String, Type> namedVariables = new HashMap<String, Type>();
        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Commentable currentElement = ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (currentElement instanceof LocalVariableStatement) {
                    LocalVariable variable = ((LocalVariableStatement) currentElement).getVariable();
                    Type variableType = variable.getTypeReference().getTarget();
                    Type oldValue = namedVariables.put(variable.getName(), variableType);
                    if (oldValue != null && variableType != null
                            && !oldValue.getClass().equals(variableType.getClass())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks whether a variation point has fields with equal names but different types.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if the vp has variables with equal name but different types;
     *         <code>false</code> otherwise.
     */
    public static boolean hasConflictingFields(VariationPoint variationPoint) {
        HashMap<String, Type> namedVariables = new HashMap<String, Type>();
        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Commentable currentElement = ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (currentElement instanceof Field) {
                    Field field = (Field) currentElement;
                    Type fieldType = field.getTypeReference().getTarget();
                    Type oldValue = namedVariables.put(field.getName(), fieldType);
                    if (oldValue != null && !oldValue.getClass().equals(fieldType.getClass())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks whether a variation point has methods with equal names but different return types.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if conflicting elements are found; <code>false</code> otherwise.
     */
    public static boolean hasConflictingMethods(VariationPoint variationPoint) {
        List<Method> methods = new LinkedList<Method>();

        for (Variant variant : variationPoint.getVariants()) {
            methods.addAll(getImplementingElements(variant, Method.class));
        }

        TypeReference retType = null;
        for (Method method : methods) {
            if (retType == null) {
                retType = method.getTypeReference();
                continue;
            }
            if (!EcoreUtil.equals(method.getTypeReference(), retType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a variation point's variants have constructor calls.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if {@link ExplicitConstructorCall}s are contained;
     *         <code>false</code> otherwise.
     */
    public static boolean hasConstructorCalls(VariationPoint variationPoint) {
        for (Variant variant : variationPoint.getVariants()) {
            List<Statement> implementingElements = getImplementingElements(variant, Statement.class);
            for (Statement statement : implementingElements) {
                if (!(statement instanceof ExpressionStatement)) {
                    continue;
                }
                Expression expression = ((ExpressionStatement) statement).getExpression();
                if (expression instanceof ExplicitConstructorCall) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks whether a variation point's variants have an element of a certain type.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @param c
     *            The type's {@link java.lang.Class}.
     * @param <T>
     *            The type.
     * @return <code>true</code> a element was found; <code>false</code> otherwise.
     */
    public static <T> boolean hasImplementingElementsOfType(VariationPoint variationPoint, java.lang.Class<T> c) {
        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Commentable commentable = ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (c.isAssignableFrom(commentable.getClass())) {
                    return true;
                }
                TreeIterator<EObject> it = commentable.eAllContents();
                while (it.hasNext()) {
                    EObject eObject = it.next();
                    if (c.isAssignableFrom(eObject.getClass())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static Literal getDefaultValueForVariable(Variable variable) {
        if (variable.getTypeReference() == null) {
            return LiteralsFactory.eINSTANCE.createNullLiteral();
        }

        Type type = variable.getTypeReference().getTarget();
        if (type instanceof org.emftext.language.java.types.Boolean) {
            BooleanLiteral literal = LiteralsFactory.eINSTANCE.createBooleanLiteral();
            literal.setValue(false);
            return literal;
        } else if (type instanceof org.emftext.language.java.types.Char) {
            CharacterLiteral literal = LiteralsFactory.eINSTANCE.createCharacterLiteral();
            literal.setValue(Character.MIN_VALUE);
            return literal;
        } else if (type instanceof org.emftext.language.java.types.Double) {
            DecimalDoubleLiteral literal = LiteralsFactory.eINSTANCE.createDecimalDoubleLiteral();
            literal.setDecimalValue(0);
            return literal;
        } else if (type instanceof org.emftext.language.java.types.Float) {
            DecimalFloatLiteral literal = LiteralsFactory.eINSTANCE.createDecimalFloatLiteral();
            literal.setDecimalValue(0);
            return literal;
        } else if (type instanceof org.emftext.language.java.types.Int) {
            DecimalIntegerLiteral literal = LiteralsFactory.eINSTANCE.createDecimalIntegerLiteral();
            literal.setDecimalValue(BigInteger.valueOf(0));
            return literal;
        } else if (type instanceof org.emftext.language.java.types.Long) {
            DecimalLongLiteral literal = LiteralsFactory.eINSTANCE.createDecimalLongLiteral();
            literal.setDecimalValue(BigInteger.valueOf(0));
            return literal;
        } else if (type instanceof org.emftext.language.java.types.Short) {
            DecimalIntegerLiteral literal = LiteralsFactory.eINSTANCE.createDecimalIntegerLiteral();
            literal.setDecimalValue(BigInteger.valueOf(0));
            return literal;
        } else {
            return LiteralsFactory.eINSTANCE.createNullLiteral();
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Commentable> List<T> getImplementingElements(Variant variant, java.lang.Class<T> type) {
        LinkedList<T> elements = new LinkedList<T>();
        for (SoftwareElement se : variant.getImplementingElements()) {
            T element = (T) ((JaMoPPSoftwareElement) se).getJamoppElement();
            elements.add(element);
        }
        return elements;
    }

    private static LayoutInformation getFirstLayoutInformation(final Commentable commentable) {
        List<LayoutInformation> layoutInfos = getRecursiveLayoutInfos(commentable);
        if (layoutInfos.isEmpty()) {
            return null;
        } else {
            return layoutInfos.get(0);
        }
    }

    private static List<LayoutInformation> getRecursiveLayoutInfos(final Commentable commentable) {

        List<LayoutInformation> layoutInfos = new ArrayList<LayoutInformation>();

        Collection<EObject> objects = new ArrayList<EObject>();
        objects.add(commentable);
        TreeIterator<Object> allContents = EcoreUtil.getAllContents(objects);
        while (allContents.hasNext()) {
            EObject next = (EObject) allContents.next();
            if (next instanceof Commentable) {
                Commentable childCommentable = (Commentable) next;
                layoutInfos.addAll(childCommentable.getLayoutInformations());
            }
        }

        layoutInfos.addAll(commentable.getLayoutInformations());

        Collections.sort(layoutInfos, new Comparator<LayoutInformation>() {

            @Override
            public int compare(LayoutInformation o1, LayoutInformation o2) {
                return o1.getStartOffset() - o2.getStartOffset();

            }
        });

        return layoutInfos;
    }

    /**
     * Checks whether a given container has local variable declarations with a given name.
     * 
     * @param container
     *            The container.
     * @param name
     *            The {@link String} name.
     * @return <code>true</code> if such a variable was found; <code>false</code> otherwise.
     */
    public static boolean hasVariableWithSameName(StatementListContainer container, String name) {
        for (Statement statement : container.getStatements()) {
            if (!(statement instanceof LocalVariableStatement)) {
                continue;
            }

            boolean hasEqualName = ((LocalVariableStatement) statement).getVariable().getName().equals(name);

            if (hasEqualName) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a given container has a class, interface or enumeration with with a given
     * name.
     * 
     * @param container
     *            The {@link MemberContainer}.
     * @param name
     *            The {@link String} name.
     * @return <code>true</code> if such a entity was found; <code>false</code> otherwise.
     */
    public static boolean containsClassInterfaceOrEnumWithName(MemberContainer container, String name) {
        for (Member member : container.getMembers()) {
            if (member instanceof Class || member instanceof Interface || member instanceof Enumeration) {
                if (member.getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}

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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayInitializer;
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
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypesFactory;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.Variable;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Provides utility methods for the refactorings.
 */
public final class RefactoringUtil {

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
    public static void deleteVariableMembers(VariationPoint variationPoint) {
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
     * Calculates the position of the variable elements in the variation point's location for a
     * variant.
     * 
     * @param vpLocation
     *            The variation point's location.
     * @param variant
     *            The {@link Variant}.
     * @return The position. -1 if it could not be calculated.
     */
    public static int getVariabilityPosition(StatementListContainer vpLocation, Variant variant) {
        Statement firstImplElement = (Statement) ((JaMoPPSoftwareElement) variant.getImplementingElements().get(0))
                .getJamoppElement();

        Statement prevElement = null;

        int positionCurrElement = JaMoPPElementUtil.getPositionInContainer(firstImplElement);
        if (variant.getLeading() || positionCurrElement == 0 || positionCurrElement == -1) {
            return positionCurrElement;
        }

        int positionPrevElement = positionCurrElement - 1;

        do {
            prevElement = ((StatementListContainer) firstImplElement.eContainer()).getStatements().get(
                    positionPrevElement);

            for (int i = 0; i < vpLocation.getStatements().size(); i++) {
                Statement statement = vpLocation.getStatements().get(i);
                // current element equal?
                if (EcoreUtil.equals(statement, prevElement)) {
                    return i + 1;
                }

                // search element in a variability-if
                if (statement instanceof Condition && ((Condition) statement).getStatement() instanceof Block
                        && ((Block) ((Condition) statement).getStatement()).getStatements().size() > 0) {
                    Block block = (Block) ((Condition) statement).getStatement();
                    Statement lastStatementPreviousIfBlock = block.getStatements()
                            .get(block.getStatements().size() - 1);
                    if (EcoreUtil.equals(lastStatementPreviousIfBlock, prevElement)) {
                        return i + 1;
                    }
                }
            }
        } while (--positionPrevElement >= 0);
        return 0;
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
        removeFinalAndAddCommentIfApplicable(variable);

        IdentifierReference varRef = ReferencesFactory.eINSTANCE.createIdentifierReference();
        varRef.setTarget(variable);

        NamespaceClassifierReference nsClassifierRef = TypesFactory.eINSTANCE.createNamespaceClassifierReference();
        nsClassifierRef.setTarget(variable.getContainingConcreteClassifier());
        NamespaceClassifierReference nsClassifierRef1 = TypesFactory.eINSTANCE.createNamespaceClassifierReference();
        nsClassifierRef1.setTarget(variable.getContainingConcreteClassifier());

        AssignmentExpression assignmentExpression = ExpressionsFactory.eINSTANCE.createAssignmentExpression();

        if (variable.getInitialValue() instanceof ArrayInstantiationByValues) {
            EList<ArrayInitializationValue> initialValues = ((ArrayInstantiationByValues) variable.getInitialValue())
                    .getArrayInitializer().getInitialValues();
            ArrayInstantiationByValuesTyped arrayInstantiationByValuesTyped = ArraysFactory.eINSTANCE
                    .createArrayInstantiationByValuesTyped();
            ArrayInitializer arrayInitializer = ArraysFactory.eINSTANCE.createArrayInitializer();
            arrayInitializer.getInitialValues().addAll(initialValues);
            arrayInstantiationByValuesTyped.setArrayInitializer(arrayInitializer);
            arrayInstantiationByValuesTyped.setTypeReference(nsClassifierRef);
            assignmentExpression.setValue(arrayInstantiationByValuesTyped);
            variable.setTypeReference(nsClassifierRef1);
        } else {
            assignmentExpression.setValue(variable.getInitialValue());
        }

        assignmentExpression.setAssignmentOperator(OperatorsFactory.eINSTANCE.createAssignment());
        assignmentExpression.setChild(varRef);

        ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
        expressionStatement.setExpression(assignmentExpression);

        Literal defaultValue = getDefaultValueForType(variable);
        variable.setInitialValue(defaultValue);

        return expressionStatement;
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
            Statement statementCpy = EcoreUtil.copy((Statement) ((JaMoPPSoftwareElement) se).getJamoppElement());
            if (statementCpy instanceof LocalVariableStatement) {
                LocalVariableStatement localVariableStatement = (LocalVariableStatement) statementCpy;
                LocalVariable variable = localVariableStatement.getVariable();

                if (!localVariableStatements.containsKey(variable.getName())) {
                    localVariableStatements.put(variable.getName(), localVariableStatement);
                }

                ExpressionStatement extractedAssignment = RefactoringUtil.extractAssignment(variable);
                statementCpy = extractedAssignment;
            }
            // in case the variable is common among all variants and it has no initialization and it
            // is assigned in variants, assign default value.
            if (statementCpy instanceof ExpressionStatement
                    && ((ExpressionStatement) statementCpy).getExpression() instanceof AssignmentExpression
                    && ((AssignmentExpression) ((ExpressionStatement) statementCpy)
                            .getExpression()).getChild() instanceof IdentifierReference
                    && ((IdentifierReference) ((AssignmentExpression) ((ExpressionStatement) statementCpy)
                            .getExpression()).getChild()).getTarget() instanceof LocalVariable) {
                LocalVariable variable = (LocalVariable) ((IdentifierReference) ((AssignmentExpression) 
                        ((ExpressionStatement) statementCpy).getExpression()).getChild()).getTarget();
                if (variable.getInitialValue() == null) {
                    variable.setInitialValue(getDefaultValueForType(variable));
                }
                removeFinalAndAddCommentIfApplicable(variable);
            }

            ((Block) currentCondition.getStatement()).getStatements().add(statementCpy);
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
    public static void removeFinalAndAddCommentIfApplicable(AnnotableAndModifiable modifieable) {
        for (Modifier modifier : modifieable.getModifiers()) {
            if (modifier instanceof Final) {
                modifieable.removeModifier(Final.class);
                RefactoringUtil
                        .addCommentBefore(modifieable, "FIXME: Removed final modifier to introduce variability.");
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
     * @param c
     *            The {@link Class}.
     * @param method
     *            The {@link Method} to compare with.
     * @return <code>true</code> if such a method is found; <code>false</code> otherwise.
     */
    public static boolean hasMethodWithEqualParameters(Class c, Method method) {
        for (Method vpMethod : c.getMethods()) {
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
     * Checks whether a variation point's location and variants have conflicting classes (same name
     * but different extends/implements).
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if interfering members were found; <code>false</code> otherwise.
     */
    public static boolean hasConflictingClasses(VariationPoint variationPoint) {
        TypeReference extendsRefs = null;
        List<TypeReference> implementsRefs = null;
        boolean isFirst = true;

        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Member member = (Member) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (!(member instanceof Class)) {
                    continue;
                }
                Class c = (Class) member;
                if (isFirst) {
                    isFirst = false;
                    extendsRefs = c.getExtends();
                    implementsRefs = c.getImplements();
                } else {
                    if (!EcoreUtil.equals(extendsRefs, c.getExtends())) {
                        return true;
                    }
                    if (!EcoreUtil.equals(implementsRefs, c.getImplements())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks whether a variation point's location and variants have conflicting interfaces (same
     * name but different extends).
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if interfering members were found; <code>false</code> otherwise.
     */
    public static boolean hasConflictingInterfaces(VariationPoint variationPoint) {
        List<TypeReference> extendsRef = null;
        boolean isFirst = true;

        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Member member = (Member) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (!(member instanceof Interface)) {
                    continue;
                }
                Interface c = (Interface) member;
                if (isFirst) {
                    isFirst = false;
                    extendsRef = c.getExtends();
                } else {
                    if (!EcoreUtil.equals(extendsRef, c.getExtends())) {
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
     * Checks whether a variation point has enums with equal names but different extends.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if the vp has enums with equal name but different extends;
     *         <code>false</code> otherwise.
     */
    public static boolean hasConflictingEnums(VariationPoint variationPoint) {
        EList<TypeReference> implementsList = null;
        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Enumeration enumeration = (Enumeration) ((JaMoPPSoftwareElement) se).getJamoppElement();
                if (implementsList == null) {
                    implementsList = enumeration.getImplements();
                    continue;
                }
                if (!EcoreUtil.equals(implementsList, enumeration.getImplements())) {
                    return true;
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

    private static Literal getDefaultValueForType(Variable variable) {
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
}

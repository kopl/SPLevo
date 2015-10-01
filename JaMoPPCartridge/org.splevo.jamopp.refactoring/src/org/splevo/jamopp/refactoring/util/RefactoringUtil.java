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
package org.splevo.jamopp.refactoring.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.language.java.arrays.ArrayDimension;
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
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.variables.LocalVariable;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;
import org.splevo.jamopp.refactoring.JaMoPPTodoTagCustomizer;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.JaMoPPJavaSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.jamopp.vpm.software.impl.CommentableSoftwareElementImpl;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Maps;

/**
 * Provides utility methods for the refactorings.
 */
public final class RefactoringUtil {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final Logger LOGGER = Logger.getLogger(RefactoringUtil.class);
    private static SimilarityChecker similarityChecker;

    static {
        LinkedHashMap<Pattern, String> config = Maps.newLinkedHashMap();
        similarityChecker = new SimilarityChecker(config, config, config);
    }

    private RefactoringUtil() {
    }

    /**
     * Adds a return statement to return the default value at the end of the method if the method
     * has no trailing return.
     * 
     * @param method
     *            The {@link ClassMethod}.
     */
    public static void addReturnStatement(ClassMethod method) {
        if (method.getStatements().get(method.getStatements().size() - 1) instanceof Return) {
            return;
        }

        Return returnStatement = StatementsFactory.eINSTANCE.createReturn();
        Type methodReturnType = method.getTypeReference().getTarget();
        Literal returnValue = RefactoringUtil.getDefaultValueForType(methodReturnType);
        returnStatement.setReturnValue(returnValue);
        method.getStatements().add(returnStatement);
    }

    /**
     * Checks whether a given variation point has a leading variant.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if it contains a leading variant; <code>false</code> otherwise.
     */
    public static boolean hasLeadingVariant(VariationPoint variationPoint) {
        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                return true;
            }
        }
        return false;
    }

    /**
     * If the vp has no leading variant, this method checks whether the variants have members that
     * with a name that already exists in the vp location.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if the vp has conflicting members; <code>false</code> otherwise.
     */
    public static boolean hasMembersWithConflictingNames(VariationPoint variationPoint) {
        MemberContainer jamoppElement = (MemberContainer) ((JaMoPPJavaSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();

        if (RefactoringUtil.hasLeadingVariant(variationPoint)) {
            return false;
        }

        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Member member = (Member) ((JaMoPPJavaSoftwareElement) se).getJamoppElement();
                if (RefactoringUtil.containsClassInterfaceOrEnumWithName(jamoppElement, member.getName())) {
                    return true;
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
    public static void deleteVariableStatements(VariationPoint variationPoint) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPJavaSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();
        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                for (SoftwareElement se : variant.getImplementingElements()) {
                    Statement variantStatement = (Statement) ((JaMoPPJavaSoftwareElement) se).getJamoppElement();
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
        MemberContainer vpLocation = (MemberContainer) ((JaMoPPJavaSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();
        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                for (SoftwareElement se : variant.getImplementingElements()) {
                    Member variantMember = (Member) ((JaMoPPJavaSoftwareElement) se).getJamoppElement();
                    vpLocation.getMembers().remove(variantMember);
                }
            }
        }
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

        Type variableType = variable.getTypeReference().getTarget();
        Literal defaultValue = getDefaultValueForType(variableType);
        variable.setInitialValue(defaultValue);

        return expressionStatement;
    }

    /**
     * Checks whether a {@link LocalVariableStatement}'s {@link LocalVariable} is referenced by a
     * following element in its parent container.
     * 
     * @param localVariableStatement
     *            The {@link LocalVariableStatement} containing the {@link LocalVariable}.
     * @param offset
     *            Search starts at the position of the statement in the parent container + offset.
     * @return <code>true</code> if it is referenced; otherwise <code>false</code>.
     */
    public static boolean isReferencedByPostdecessor(LocalVariableStatement localVariableStatement, int offset) {
        LocalVariable variable = ((LocalVariableStatement) localVariableStatement).getVariable();

        StatementListContainer container = (StatementListContainer) localVariableStatement.eContainer();
        EList<Statement> containerStatements = container.getStatements();

        int index = containerStatements.indexOf(localVariableStatement);

        int fromIndex = index + offset;
        int toIndex = containerStatements.size();

        if (fromIndex >= toIndex) {
            return false;
        }

        List<Statement> postdecessors = containerStatements.subList(fromIndex, toIndex);

        for (Statement postdecessor : postdecessors) {
            if (hasReferenceTo(postdecessor, variable)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasReferenceTo(Statement toBeChecked, LocalVariable variable) {
        TreeIterator<Object> allContents = EcoreUtil.getAllContents(toBeChecked, true);
        while (allContents.hasNext()) {
            EObject next = (EObject) allContents.next();
            if (next instanceof IdentifierReference && areEqual(((IdentifierReference) next).getTarget(), variable)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Assigns the default value to all referenced {@link LocalVariable}s if they don't have one and
     * removes final modifiers.
     * 
     * @param eObject
     *            The {@link EObject} to be checked for referenced {@link LocalVariable}s.
     */
    public static void initializeAndRemoveFinalForReferencedVariables(EObject eObject) {
        TreeIterator<Object> contents = EcoreUtil.getAllContents(eObject, true);

        while (contents.hasNext()) {
            EObject child = (EObject) contents.next();
            for (EObject crossReference : child.eCrossReferences()) {
                if (crossReference instanceof AnnotableAndModifiable) {
                    removeFinalIfApplicable((AnnotableAndModifiable) crossReference);
                }
                if (crossReference instanceof LocalVariable) {
                    LocalVariable variable = (LocalVariable) crossReference;
                    if (variable.getInitialValue() == null) {
                        Type variableType = variable.getTypeReference().getTarget();
                        variable.setInitialValue(getDefaultValueForType(variableType));
                    }
                }
            }
            initializeAndRemoveFinalForReferencedVariables(child);
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
                Commentable commentable = ((JaMoPPJavaSoftwareElement) se).getJamoppElement();
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
            if (areEqual(currentI, i)) {
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
        if (layoutInformation == null) {
            return;
        }

        final String oldComment = layoutInformation.getHiddenTokenText();
        String newComment = String.format("/* %s */", comment);
        if (oldComment.length() != 0) {
            newComment = buildIntegratedCommentString(oldComment, newComment);
        } else {
            newComment = NEWLINE + newComment;
        }
        layoutInformation.setHiddenTokenText(newComment);
    }
    
    /**
     * Removes the final modifier of an element (if existing) and adds a comment with a fix-me tag
     * to the element: "\/* Removed final modifier to introduce variability. *\/"
     * 
     * @param modifieable
     *            The {@link Modifiable} element.
     * @return <code>true</code> if a final modifier was removed; <code>false</code> otherwise.
     */
    public static boolean removeFinalIfApplicable(AnnotableAndModifiable modifieable) {
        for (Modifier modifier : modifieable.getModifiers()) {
            if (modifier instanceof Final) {
                modifieable.removeModifier(Final.class);
                return true;
            }
        }
        return false;
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
            EList<Parameter> currentConstructorParams = vpConstructor.getParameters();
            EList<Parameter> givenParams = constructor.getParameters();

            boolean haveEqualSetOfParameters = haveEqualSetOfParameters(currentConstructorParams, givenParams);
            if (haveEqualSetOfParameters) {
                return true;
            }
        }
        return false;
    }

    private static boolean haveEqualSetOfParameters(EList<Parameter> params1, EList<Parameter> params2) {
        if (params1.size() != params2.size()) {
            return false;
        }

        for (int i = 0; i < params1.size(); i++) {
            Type target1 = params1.get(i).getTypeReference().getTarget();
            Type target2 = params2.get(i).getTypeReference().getTarget();
            if (!areEqual(target1, target2)) {
                return false;
            }
        }

        return true;
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
                    && haveEqualSetOfParameters(method.getParameters(), vpMethod.getParameters())) {
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
                Commentable currentElement = ((JaMoPPJavaSoftwareElement) se).getJamoppElement();

                if (currentElement instanceof LocalVariableStatement) {
                    LocalVariableStatement localVarStatement = (LocalVariableStatement) currentElement;
                    int offset = variant.getImplementingElements().size()
                            - variant.getImplementingElements().indexOf(se);
                    if (!isReferencedByPostdecessor(localVarStatement, offset)) {
                        continue;
                    }

                    LocalVariable variable = localVarStatement.getVariable();
                    Type variableType = variable.getTypeReference().getTarget();

                    Type oldValue = namedVariables.put(variable.getName(), variableType);

                    if (oldValue != null && variableType != null && !areEqual(oldValue, variableType)) {
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
                Commentable currentElement = ((JaMoPPJavaSoftwareElement) se).getJamoppElement();
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
     * Checks whether two statements are equal. Uses the {@link SimilarityChecker} for comparison.
     * 
     * @param c1
     *            The first {@link Commentable}.
     * @param c2
     *            The second {@link Commentable}.
     * @return <code>true</code> if the statements are equal; <code>false</code> otherwise.
     */
    public static Boolean areEqual(Commentable c1, Commentable c2) {
        return similarityChecker.isSimilar(c1, c2, false);
    }

    /**
     * Gets the default literal for a given type.
     * 
     * @param type
     *            The {@link Type}.
     * @return The {@link Literal}.
     */
    public static Literal getDefaultValueForType(Type type) {
        if (type instanceof org.emftext.language.java.types.Void) {
            return null;
        } else if (type instanceof org.emftext.language.java.types.Boolean) {
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
            T element = (T) ((JaMoPPJavaSoftwareElement) se).getJamoppElement();
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

    /**
     * Resolves the implementing elements of all {@link Variant}s of all {@link VariationPoint}s
     * with the same location.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     */
    public static void resolveVPsWithSameLocation(VariationPoint variationPoint) {
        VariationPointModel vpm = variationPoint.getGroup().getModel();
        Commentable vpLocation = ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        for (VariationPointGroup vpg : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpg.getVariationPoints()) {
                Commentable currentVPLocation = ((JaMoPPJavaSoftwareElement) vp.getLocation()).getJamoppElement();
                if (vpLocation != currentVPLocation && !JaMoPPElementUtil.isParentOf(vpLocation, currentVPLocation)) {
                    continue;
                }

                for (Variant v : vp.getVariants()) {
                    if (!v.getLeading()) {
                        continue;
                    }

                    for (SoftwareElement se : v.getImplementingElements()) {
                        EcoreUtil.resolveAll(se);
                    }
                }
            }
        }
    }
    
    /**
     * Removes the comment for a CommentableSoftwareElement from source code.
     * 
     * @param commentableElement
     *            The element for which the comment shall be removed.
     * @return True if the comment has been removed successfully.
     */
    public static boolean removeCommentableSoftwareElementReference(CommentableSoftwareElement commentableElement) {
        final String commentText = CommentableSoftwareElementImpl.buildReferencingCommentText(commentableElement
                .getId());
        final Commentable commentable = commentableElement.getJamoppElement();
        return removeTransitiveCommentString(commentable, commentText);
    }
           
    /**
     * Adds a comment to the given element (or one of its sub elements) that can be used by a
     * CommentableSoftwareElement to refer to the given element.
     * 
     * @param element
     *            The element to add the comment to.
     * @return The ID included in the comment.
     */
    public static String addCommentableSoftwareElementReference(Commentable element) {
        final String elementID = EcoreUtil.generateUUID();
        final String commentText = CommentableSoftwareElementImpl.buildReferencingCommentText(elementID);
        RefactoringUtil.addCommentBefore(element, commentText);
        return elementID;
    }
    
    /**
     * Creates a CommentableSoftwareElement by a given element and an ID to be used during the resolution.
     * @param referencedElement The element to be referenced.
     * @param id The ID to be used as reference.
     * @return The CommentableSoftwareElement.
     */
    public static CommentableSoftwareElement createCommentableSoftwareElement(Commentable referencedElement, String id) {
        CommentableSoftwareElement commentable = softwareFactory.eINSTANCE.createCommentableSoftwareElement();
        commentable.setCompilationUnit(referencedElement.getContainingCompilationUnit());
        commentable.setId(id);
        commentable.setType(referencedElement.getClass());
        return commentable;
    }
    
    /**
	 * Removes the refactoring comment for the given variation point from the source code.
	 * @param variationPoint
	 * 			The variation point for which the comment shall be removed.
	 * @return True if the comment has been removed successfully.
	 */
	public static boolean deleteRefactoringCommentFor(VariationPoint variationPoint) {
		Commentable commentable = (Commentable) ((JaMoPPJavaSoftwareElement) variationPoint.getLocation()).getJamoppElement();
		final String commentText = JaMoPPTodoTagCustomizer.getTodoTaskTag() + " " + variationPoint.getId();
		
        return removeTransitiveCommentString(commentable, commentText);
	}
	
    private static boolean removeTransitiveCommentString(Commentable commentable, String commentToRemove) {
        String stringToRemove = commentToRemove;
        if (!Pattern.compile("/\\*\\s*" + commentToRemove + "\\s*\\*/").matcher(commentToRemove).matches()) {
            stringToRemove = String.format("/* %s */", commentToRemove);
        }
        TreeIterator<EObject> iterator = commentable.eAllContents();
        for (EObject nextItem = commentable; iterator.hasNext(); nextItem = iterator.next()) {
            if (nextItem instanceof Commentable) {
                if (removeDirectCommentString((Commentable) nextItem, stringToRemove)) {
                    return true;
                }
            } else {
                iterator.prune();
            }
        }
        return false;
    }
    
    private static boolean removeDirectCommentString(Commentable commentable, String commentToRemove) {
        for (LayoutInformation comment : commentable.getLayoutInformations()) {
            if (comment.getHiddenTokenText().contains(commentToRemove)) {
                String currentCommentText = comment.getHiddenTokenText();
                comment.setHiddenTokenText(cleanCommentStringFromComment(currentCommentText, commentToRemove));
                return true;
            }
        }
        return false;
    }
	
    private static String cleanCommentStringFromComment(String currentCommentText, String stringToRemove) {
        Pattern pattern = Pattern.compile("(\\r?\\n)?([^\\S\n]*?)" + Pattern.quote(stringToRemove) + "[^\\S\n]*?(\\r?\\n)?");
        Matcher matcher = pattern.matcher(currentCommentText);
        if (!matcher.find()) {
            LOGGER.warn(String.format("Could not remove comment \"%s\" from \"%s\".", stringToRemove, currentCommentText));
            return currentCommentText;
        }
        
        if (matcher.group(1) != null && matcher.group(3) != null) {
            return matcher.replaceAll(NEWLINE);
        }
        if (matcher.group(1) != null && matcher.group(3) == null) {
            return matcher.replaceAll(matcher.group(1) + matcher.group(2));
        }
        return matcher.replaceAll("");
    }

    /**
     * Builds a new comment string from an old one and a part to be inserted. The new part will be
     * added in front of the old comment. The algorithm determines the indenting of the old comment
     * and uses it for the new comment as well.
     * 
     * @param oldComment The old comment.
     * @param partToInsert The part to be inserted into the old comment.
     * @return The new comment string.
     */
    private static String buildIntegratedCommentString(String oldComment, String partToInsert) {
        /**
         * 1. Determine the start of the real content (no whitespace character).
         * 2. Determine the whitespaces before the start of the real content in the same line.
         * 3. Use the determined whitespaces as padding/indenting.
         * 4. Add the new part at the starting position of the real content
         *    and append a newline and the padding.
         */
        int oldContentStartIndex = firstIndexOfNotWhitespace(oldComment);
        if (oldContentStartIndex == -1) {
            oldContentStartIndex = oldComment.length();
        }
        String whitespaceString = oldComment.substring(0, oldContentStartIndex);
        int lastLineBreakIndex = whitespaceString.lastIndexOf('\n');
        if (lastLineBreakIndex == -1) {
            lastLineBreakIndex = 0;
        } else {
            lastLineBreakIndex++;
        }
        String paddingString = whitespaceString.substring(lastLineBreakIndex);

        return oldComment.substring(0, oldContentStartIndex) + partToInsert + NEWLINE + paddingString
                + oldComment.substring(oldContentStartIndex);
    }
    
    private static int firstIndexOfNotWhitespace(String str) {
        for (int i = 0; i < str.length(); ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

}

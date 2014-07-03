package org.splevo.jamopp.refactoring.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.NullLiteral;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.references.IdentifierReference;
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
        Class parentClass = findContainingUnitByType(vpLocation, Class.class);

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

            MethodCall methodCall = ReferencesFactory.eINSTANCE.createMethodCall();
            methodCall.setTarget(method);

            Statement variantInvokingStatement = null;
            if (returnType == null || returnType.getTarget() instanceof Void) {
                ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
                expressionStatement.setExpression(methodCall);
                variantInvokingStatement = expressionStatement;
            } else {
                Return returnStatement = StatementsFactory.eINSTANCE.createReturn();
                returnStatement.setReturnValue(methodCall);
                variantInvokingStatement = returnStatement;
            }

            JaMoPPSoftwareElement newJaMoPPElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
            newJaMoPPElement.setJamoppElement(variantInvokingStatement);

            Variant newVariant = RefactoringUtil.cloneVariant(variant, variationPoint);
            newVariant.getImplementingElements().add(newJaMoPPElement);
            variationPoint.getVariants().add(newVariant);

            parentClass.getMembers().add(method);
        }
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
        // try to get pos in container
        Commentable firstElement = ((JaMoPPSoftwareElement) variant.getImplementingElements().get(0))
                .getJamoppElement();
        EObject eObject = firstElement;
        while (eObject.eContainer() != null && !(eObject.eContainer() instanceof StatementListContainer)) {
            eObject = eObject.eContainer();
        }
        int pos = JaMoPPElementUtil.getPositionInContainer((Statement) eObject);

        if (pos == 0) {
            return pos;
        } else if (eObject.eContainer() == null) {
            return -1;
        }

        // get pos by determinign previous element in the vp location
        StatementListContainer container = (StatementListContainer) eObject.eContainer();
        Statement prevStatement = container.getStatements().get(pos - 1);
        for (Statement statement : vpLocation.getStatements()) {
            if (!(statement instanceof Condition) || !(((Condition) statement).getStatement() instanceof Block)) {
                continue;
            }
            Block block = (Block) ((Condition) statement).getStatement();
            Statement lastBlockStatement = block.getStatements().get(block.getStatements().size() - 1);
            if (prevStatement instanceof LocalVariableStatement
                    && lastBlockStatement instanceof ExpressionStatement
                    && ((ExpressionStatement) lastBlockStatement).getExpression() instanceof AssignmentExpression
                    && EcoreUtil.equals(((LocalVariableStatement) prevStatement).getVariable().getInitialValue(),
                            ((AssignmentExpression) ((ExpressionStatement) lastBlockStatement).getExpression())
                                    .getValue())) {
                return vpLocation.getStatements().indexOf(statement) + 1;
            }
            if (EcoreUtil.equals(lastBlockStatement, prevStatement)) {
                return vpLocation.getStatements().indexOf(statement) + 1;
            }
        }

        // if pos too large (because other vp not yet processed), use size
        if (pos > vpLocation.getStatements().size()) {
            return vpLocation.getStatements().size();
        }

        return pos;
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
        IdentifierReference varRef = ReferencesFactory.eINSTANCE.createIdentifierReference();
        varRef.setTarget(variable);

        AssignmentExpression assignmentExpression = ExpressionsFactory.eINSTANCE.createAssignmentExpression();
        assignmentExpression.setValue(variable.getInitialValue());
        assignmentExpression.setAssignmentOperator(OperatorsFactory.eINSTANCE.createAssignment());
        assignmentExpression.setChild(varRef);

        ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
        expressionStatement.setExpression(assignmentExpression);

        NullLiteral nullLiteral = LiteralsFactory.eINSTANCE.createNullLiteral();
        variable.setInitialValue(nullLiteral);

        return expressionStatement;
    }

    /**
     * Generates a condition with an empty if-block. Matches the SPL configuration with the given
     * variant id within the condition.
     * 
     * @param variantId
     *            The variant id as {@link String}.
     * @return The generated {@link Condition}.
     */
    public static Condition generateConditionVariantIDWithEmptyIfBlock(String variantId) {
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
                if (c.isInstance(commentable)) {
                    return true;
                }
            }
        }
        return false;
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
            finalComment = comment + "\n";
        } else {
            finalComment += "\n" + comment + "\n";
        }
        layoutInformation.setHiddenTokenText(finalComment);
    }

    /**
     * Removes the final modifier of an element (if existing) and adds a comment to the element:
     * "\/* FIXME: Removed final modifier to introduce variability. *\/"
     * 
     * @param modifieable
     *            The {@link Modifiable} element.
     */
    public static void removeFinalAndAddCommentIfApplicable(AnnotableAndModifiable modifieable) {
        for (Modifier modifier : modifieable.getModifiers()) {
            if (modifier instanceof Final) {
                modifieable.removeModifier(Final.class);
                RefactoringUtil.addCommentBefore(modifieable,
                        "/* FIXME: Removed final modifier to introduce variability. */");
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
    public static boolean hasConflictingConstructor(Class c, Constructor constructor) {
        for (Constructor vpConstructor : c.getConstructors()) {
            if (EcoreUtil.equals(constructor.getParameters(), vpConstructor.getParameters())) {
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

    private static HashMap<Variant, List<Statement>> buildVariantStatementMap(VariationPoint variationPoint) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();

        HashMap<Variant, List<Statement>> variantStatements = new HashMap<Variant, List<Statement>>();

        Set<VariationPoint> vpsWithSameLocation = getVPsWithSameLocation(variationPoint);

        for (VariationPoint relatedVP : vpsWithSameLocation) {
            Collection<Statement> nonVariableStatements = EcoreUtil.copyAll(getNonVariableStatements(variationPoint));
            for (Variant variant : relatedVP.getVariants()) {
                int posVariability = RefactoringUtil.getVariabilityPosition(vpLocation, variant);
                List<Statement> statements;
                if (!variantStatements.containsKey(variant)) {
                    statements = new LinkedList<Statement>();
                    Collection<Statement> copies = EcoreUtil.copyAll(nonVariableStatements);
                    statements.addAll(copies);
                    variantStatements.put(variant, statements);
                } else {
                    statements = variantStatements.get(variant);
                }

                Collection<Statement> variableStatements = EcoreUtil.copyAll(getImplementingElements(variant,
                        Statement.class));
                statements.addAll(posVariability, variableStatements);
            }
        }
        return variantStatements;
    }

    private static List<Statement> getNonVariableStatements(VariationPoint variationPoint) {
        StatementListContainer vpLocation = (StatementListContainer) ((JaMoPPSoftwareElement) variationPoint
                .getLocation()).getJamoppElement();
        Variant leading = null;

        for (Variant variant : variationPoint.getVariants()) {
            if (variant.getLeading()) {
                leading = variant;
            }
        }

        if (leading == null) {
            return vpLocation.getStatements();
        }

        List<Statement> fixedStatements = new LinkedList<Statement>(vpLocation.getStatements());
        fixedStatements.removeAll(getImplementingElements(leading, Statement.class));

        return fixedStatements;
    }

    private static TypeReference getReturnType(StatementListContainer vpLocation) {
        ClassMethod method = findContainingUnitByType(vpLocation, ClassMethod.class);
        if (method != null) {
            return method.getTypeReference();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T> T findContainingUnitByType(EObject element, java.lang.Class<T> type) {
        EObject container = element;
        while (container != null) {
            if (type.isAssignableFrom(container.getClass())) {
                return ((T) container);
            }
            container = container.eContainer();
        }
        return null;
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

    private static Variant cloneVariant(Variant variant, VariationPoint parentVariationPoint) {
        Variant newVariant = variabilityFactory.eINSTANCE.createVariant();
        newVariant.setVariationPoint(parentVariationPoint);
        newVariant.setVariantId(variant.getVariantId());
        newVariant.setLeading(variant.getLeading());
        newVariant.setChildFeature(variant.getChildFeature());
        return newVariant;
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

package org.splevo.jamopp.refactoring.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.instantiations.InstantiationsFactory;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Throw;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypesFactory;
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
 * Provides utility methods for if-else refactorings.
 */
public final class RefactoringUtil {

    private RefactoringUtil() {
    }

    /**
     * Merges the imports of all variants into the {@link VariationPoint} point's location. The
     * location must be a {@link CompilationUnit}.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     */
    public static void mergeImports(VariationPoint variationPoint) {
        CompilationUnit compilationUnit = (CompilationUnit) ((JaMoPPSoftwareElement) variationPoint.getLocation())
                .getJamoppElement();
        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement se : variant.getImplementingElements()) {
                Commentable currentJamoppElement = ((JaMoPPSoftwareElement) se).getJamoppElement();
                EList<Import> leadingImports = compilationUnit.getImports();
                if (currentJamoppElement instanceof Import && !(leadingImports.contains(currentJamoppElement))) {
                    leadingImports.add((Import) currentJamoppElement);
                }
            }
        }
    }

    /**
     * For each variant of a variation point, this method takes all common and variable statements
     * and extracts them into separate methods. The variation point's location will contain one
     * method call per variant. The methods will be added to the parent class.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     */
    public static void extractVariableStatementsIntoMethods(VariationPoint variationPoint) {
        Commentable jamoppElement = ((JaMoPPSoftwareElement) variationPoint.getLocation()).getJamoppElement();
        StatementListContainer vpLocation = (StatementListContainer) jamoppElement;

        int posVariability = RefactoringUtil.getVariabilityPosition(variationPoint);
        RefactoringUtil.deleteVariableElements(variationPoint);

        Set<VariationPoint> relatedVPs = getVPsWithSameLocation(variationPoint);
        HashMap<Variant, List<Statement>> variantStatements = new HashMap<Variant, List<Statement>>();

        for (VariationPoint relatedVP : relatedVPs) {
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

                List<Statement> variableStatements = extractVariableStatements(variant);
                statements.addAll(posVariability, variableStatements);
            }
        }

        vpLocation.getStatements().clear();
        variationPoint.getVariants().clear();
        for (Variant variant : variantStatements.keySet()) {
            List<Statement> statements = variantStatements.get(variant);

            ClassMethod method = MembersFactory.eINSTANCE.createClassMethod();
            method.setName("extracted" + variant.getVariantId());
            TypeReference returnType = getReturnType(vpLocation);
            method.getStatements().addAll(statements);
            method.setTypeReference(returnType);

            vpLocation.getClassClass().getMembers().add(method);

            MethodCall methodCall = ReferencesFactory.eINSTANCE.createMethodCall();
            methodCall.setTarget(method);
            ExpressionStatement exprStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
            exprStatement.setExpression(methodCall);
            JaMoPPSoftwareElement newJaMoPPElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
            newJaMoPPElement.setJamoppElement(exprStatement);
            vpLocation.getStatements().add(exprStatement);

            Variant newVariant = RefactoringUtil.cloneVariant(variant, variationPoint);
            newVariant.getImplementingElements().add(newJaMoPPElement);
            variationPoint.getVariants().add(newVariant);
        }
    }

    private static TypeReference getReturnType(StatementListContainer vpLocation) {
        EObject container = vpLocation.eContainer();
        while (container != null) {
            if (container instanceof ClassMethod) {
                return ((ClassMethod) container).getTypeReference();
            }
            container = container.eContainer();
        }
        return TypesFactory.eINSTANCE.createVoid();
    }

    private static List<Statement> extractVariableStatements(Variant variant) {
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
     * Checks whether a variation point contains variables with equal names but different types.
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
     * Deletes the statements in a variation point's location that are variable.
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
     * Calculates the position of the variant's elements in the variation point's location.
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
     * Extracts the initial value expression from a local variable and sets the variable's initial
     * value to <code>null</code>.
     * 
     * @param variable
     *            The {@link LocalVariable}.
     * @return The extracted {@link ExpressionStatement}.
     */
    public static ExpressionStatement extractAssignment(LocalVariable variable) {
        ExpressionStatement expressionStatement = StatementsFactory.eINSTANCE.createExpressionStatement();
        expressionStatement.setExpression(variable.getInitialValue());
        variable.setInitialValue(null);

        return expressionStatement;
    }

    /**
     * Creates a primitive copy of a given variant and sets the parent variation point of the copy
     * as specified.
     * 
     * @param variant
     *            The {@link Variant}.
     * @param parentVariationPoint
     *            The {@link VariationPoint}.
     * @return The copy.
     */
    public static Variant cloneVariant(Variant variant, VariationPoint parentVariationPoint) {
        Variant newVariant = variabilityFactory.eINSTANCE.createVariant();
        newVariant.setVariationPoint(parentVariationPoint);
        newVariant.setVariantId(variant.getVariantId());
        newVariant.setLeading(variant.getLeading());
        newVariant.setChildFeature(variant.getChildFeature());
        return newVariant;
    }

    /**
     * Checks whether all Implementing elements of all variants of a variation point are imports.
     * 
     * @param variationPoint
     *            The {@link VariationPoint}.
     * @return <code>true</code> if all elements are of type {@link Import}; <code>false</code>
     *         otherwise.
     */
    public static boolean allImplementingElementsAreImports(VariationPoint variationPoint) {
        for (Variant variant : variationPoint.getVariants()) {
            for (SoftwareElement softwareElement : variant.getImplementingElements()) {
                if (((JaMoPPSoftwareElement) softwareElement).getJamoppElement() instanceof Import) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a block with a throw statement. It throws a RuntimeException with a message saying
     * that the SPL is configured wrong.
     * 
     * @return The generated {@link Block}.
     */
    public static Statement getBlockThrowingARuntimeException() {
        Class createdClass = ClassifiersFactory.eINSTANCE.createClass();
        createdClass.setName("RuntimeException");
        ClassifierReference createdClassifierReference = TypesFactory.eINSTANCE.createClassifierReference();
        createdClassifierReference.setTarget(createdClass);
        NewConstructorCall createdNewConstructorCall = InstantiationsFactory.eINSTANCE.createNewConstructorCall();
        createdNewConstructorCall.setTypeReference(createdClassifierReference);
        StringReference argument = ReferencesFactory.eINSTANCE.createStringReference();
        argument.setValue("Invalid SPL configuration.");
        createdNewConstructorCall.getArguments().add(argument);
        Throw createdThrow = StatementsFactory.eINSTANCE.createThrow();
        createdThrow.setThrowable(createdNewConstructorCall);
        Block block = StatementsFactory.eINSTANCE.createBlock();
        block.getStatements().add(createdThrow);
        return block;
    }

}

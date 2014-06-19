/*******************************************************************************
 * Copyright (c) 2014
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial version
 *******************************************************************************/
package org.splevo.jamopp.refactoring.util;

import java.util.List;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.NestedExpression;
import org.emftext.language.java.expressions.UnaryExpression;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.instantiations.InstantiationsFactory;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Throw;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

/**
 * This class provides utility methods when it comes to the configuration of the SPL which is to be
 * built.
 */
public final class SPLConfigurationUtil {

    private static final String CONFIG_FIELD_NAME = "Config";
    private static final String CONFIGURATION_READER_CLASS_NAME = "SPLConfig";

    /**
     * Force static access with a private constructor.
     */
    private SPLConfigurationUtil() {
    }

    /**
     * Generates the import element that is needed to use the configuration reader.
     * 
     * @return The {@link Import}.
     */
    public static Import getSPLConfigClassImport() {
        ClassifierImport generatedImport = ImportsFactory.eINSTANCE.createClassifierImport();
        Class classifier = generateConfigReaderClassifier();
        generatedImport.setClassifier(classifier);
        return generatedImport;
    }

    private static Class generateConfigReaderClassifier() {
        CompilationUnit cu = ContainersFactory.eINSTANCE.createCompilationUnit();
        cu.setName("org.splevo.spl.configuration" + CONFIGURATION_READER_CLASS_NAME + ".java");
        cu.getNamespaces().add("org");
        cu.getNamespaces().add("splevo");
        cu.getNamespaces().add("spl");
        cu.getNamespaces().add("configuration");
        Class classifier = ClassifiersFactory.eINSTANCE.createClass();
        classifier.setName(CONFIGURATION_READER_CLASS_NAME);
        cu.getClassifiers().add(classifier);
        return classifier;
    }

    /**
     * Creates an expression matching the configuration field with the variant id:
     * <code>ConfigClass.Config.equals(id);</code>
     * 
     * @param variantID
     *            The variant ID to compare to.
     * @return The generated {@link Expression}.
     */
    public static IdentifierReference generateConfigMatchingExpression(String variantID) {
        Class createClass = ClassifiersFactory.eINSTANCE.createClass();
        createClass.addModifier(ModifiersFactory.eINSTANCE.createPublic());
        createClass.addModifier(ModifiersFactory.eINSTANCE.createStatic());
        createClass.setName(CONFIGURATION_READER_CLASS_NAME);

        Field field = MembersFactory.eINSTANCE.createField();
        field.setName(CONFIG_FIELD_NAME);
        Class stringClass = ClassifiersFactory.eINSTANCE.createClass();
        stringClass.setName("String");
        ClassifierReference stringRef = TypesFactory.eINSTANCE.createClassifierReference();
        stringRef.setTarget(stringClass);
        field.setTypeReference(stringRef);
        field.addModifier(ModifiersFactory.eINSTANCE.createPublic());
        field.addModifier(ModifiersFactory.eINSTANCE.createStatic());
        field.addModifier(ModifiersFactory.eINSTANCE.createFinal());

        createClass.getMembers().add(field);

        IdentifierReference classReference = ReferencesFactory.eINSTANCE.createIdentifierReference();
        classReference.setTarget(createClass);
        IdentifierReference fieldReference = ReferencesFactory.eINSTANCE.createIdentifierReference();
        fieldReference.setTarget(field);
        classReference.setNext(fieldReference);
        MethodCall equalsMethodRef = ReferencesFactory.eINSTANCE.createMethodCall();
        ClassMethod equalsMethod = MembersFactory.eINSTANCE.createClassMethod();
        equalsMethod.setName("equals");
        StringReference variantIdStringRef = ReferencesFactory.eINSTANCE.createStringReference();
        variantIdStringRef.setValue(variantID);
        equalsMethodRef.getArguments().add(variantIdStringRef);
        equalsMethodRef.setTarget(equalsMethod);
        fieldReference.setNext(equalsMethodRef);

        return classReference;
    }

    /**
     * Creates the following expression:
     * <code>!(ConfigClass.Config.equals(id1) || ConfigClass.Config.equals(id2) || ...)</code>
     * 
     * @param variants
     *            The {@link List} containing the {@link Variant}s.
     * @return The generated {@link Expression}.
     */
    private static Expression generateConfigAndVariantIDMatchingExpression(List<Variant> variants) {
        ConditionalOrExpression conditionalOrExpression = ExpressionsFactory.eINSTANCE.createConditionalOrExpression();
        UnaryExpression unaryExpression = ExpressionsFactory.eINSTANCE.createUnaryExpression();
        unaryExpression.getOperators().add(OperatorsFactory.eINSTANCE.createNegate());
        NestedExpression nestedExpression = ExpressionsFactory.eINSTANCE.createNestedExpression();
        nestedExpression.setExpression(conditionalOrExpression);
        unaryExpression.setChild(nestedExpression);

        for (Variant currentVariant : variants) {
            conditionalOrExpression.getChildren().add(generateConfigMatchingExpression(currentVariant.getVariantId()));
        }

        return unaryExpression;
    }

    /**
     * Creates a block with a throw statement. It throws a RuntimeException with a message saying
     * that the SPL is configured wrong.
     * 
     * @return The generated {@link Block}.
     */
    public static Block generateBlockThrowingARuntimeException() {
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

    /**
     * Creates an if that checks whether at least one variant has been selected in the
     * configuration: <code>
     * if(!(ConfigClass.Config.equals(id1) || ConfigClass.Config.equals(id2) || ...){
     *  throw new RuntimeException();
     * }
     * </code>
     * 
     * @param vp
     *            The {@link VariationPoint} that has the variants.
     * @return The generated {@link Condition}.
     */
    public static Condition generateORVerificationCondition(VariationPoint vp) {
        Condition createCondition = StatementsFactory.eINSTANCE.createCondition();
        createCondition.setStatement(generateBlockThrowingARuntimeException());
        createCondition.setCondition(generateConfigAndVariantIDMatchingExpression(vp.getVariants()));
        return createCondition;
    }
}

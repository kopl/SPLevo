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

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.instantiations.InstantiationsFactory;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Throw;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;

/**
 * This class provides utility methods when it comes to the configuration of the SPL which is to be
 * built.
 */
public final class SPLConfigurationUtil {

    private static final String CONFIGURATION_READER_CLASS_NAME = "SPLConfig";

    /**
     * Force static access with a private constructor.
     */
    private SPLConfigurationUtil() {
    }

    /**
     * Generates the import element that is needed to use the configuration reader.
     * 
     * @return The {@link ClassifierImport}.
     */
    public static ClassifierImport getSPLConfigClassImport() {
        ClassifierImport generatedImport = ImportsFactory.eINSTANCE.createClassifierImport();
        Class classifier = generateConfigReaderClassifier();
        generatedImport.getNamespaces().add("org");
        generatedImport.getNamespaces().add("splevo");
        generatedImport.getNamespaces().add("config");
        generatedImport.setClassifier(classifier);
        return generatedImport;
    }

    private static Class generateConfigReaderClassifier() {
        Class classifier = ClassifiersFactory.eINSTANCE.createClass();
        classifier.setName(CONFIGURATION_READER_CLASS_NAME);
        return classifier;
    }

    /**
     * Creates an expression matching the configuration field with the variant id:
     * <code>ConfigClass.[groupID].equals([variantID]);</code>
     * 
     * @param variantID
     *            The variant ID.
     * @param groupID
     *            The group ID.
     * @return The generated {@link Expression}.
     */
    public static IdentifierReference generateConfigMatchingExpression(String variantID, String groupID) {
        Class createClass = ClassifiersFactory.eINSTANCE.createClass();
        createClass.addModifier(ModifiersFactory.eINSTANCE.createPublic());
        createClass.addModifier(ModifiersFactory.eINSTANCE.createStatic());
        createClass.setName(CONFIGURATION_READER_CLASS_NAME);

        Field field = MembersFactory.eINSTANCE.createField();
        field.setName(groupID);
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
}

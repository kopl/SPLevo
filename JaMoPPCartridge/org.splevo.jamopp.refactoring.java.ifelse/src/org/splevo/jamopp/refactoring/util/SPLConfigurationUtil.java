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

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.generics.QualifiedTypeArgument;
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
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypesFactory;

/**
 * This class provides utility methods when it comes to the configuration of the SPL which is to be
 * built.
 */
public final class SPLConfigurationUtil {

    public static final String CONFIGURATION_READER_CLASS_NAME = "SPLConfig";

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

    private static ClassifierImport getArrayListImport() {
        ClassifierImport generatedImport = ImportsFactory.eINSTANCE.createClassifierImport();
        Class classifier = ClassifiersFactory.eINSTANCE.createClass();
        classifier.setName("ArrayList");
        generatedImport.getNamespaces().add("java");
        generatedImport.getNamespaces().add("util");
        generatedImport.setClassifier(classifier);
        return generatedImport;
    }

    private static Class generateConfigReaderClassifier() {
        Class classifier = ClassifiersFactory.eINSTANCE.createClass();
        classifier.setName(CONFIGURATION_READER_CLASS_NAME);
        classifier.makePublic();
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
        equalsMethod.setName("contains");
        StringReference variantIdStringRef = ReferencesFactory.eINSTANCE.createStringReference();
        variantIdStringRef.setValue(variantID);
        equalsMethodRef.getArguments().add(variantIdStringRef);
        equalsMethodRef.setTarget(equalsMethod);
        fieldReference.setNext(equalsMethodRef);

        return classReference;
    }

    private static CompilationUnit getConfigurationCompilationUnit() {
        CompilationUnit cu = ContainersFactory.eINSTANCE.createCompilationUnit();
        cu.setName(CONFIGURATION_READER_CLASS_NAME + ".java");
        cu.getNamespaces().clear();
        cu.getNamespaces().add("org");
        cu.getNamespaces().add("splevo");
        cu.getNamespaces().add("config");
        
        ClassifierImport arrayListImport = getArrayListImport();
        cu.getImports().add(arrayListImport);

        ConcreteClassifier c = generateConfigReaderClassifier();
        cu.getClassifiers().add(c);

        return cu;
    }

    /**
     * Adds a configuration (a field) with a given name and value to a given configuration class.
     * 
     * @param splConfigurationClass
     *            The configuration {@link ConcreteClassifier}.
     * @param configurationName
     *            The configuration {@link String} name.
     * @param configurationValue
     *            The configuration {@link String} value.
     */
    public static void addConfiguration(ConcreteClassifier splConfigurationClass, String configurationName,
            String configurationValue) {
        StringReference configurationValueRef = ReferencesFactory.eINSTANCE.createStringReference();
        configurationValueRef.setValue(configurationValue);

        Field field = MembersFactory.eINSTANCE.createField();
        field.setName(configurationName);
        field.setInitialValue(configurationValueRef);

        NewConstructorCall newConstructorCall = InstantiationsFactory.eINSTANCE.createNewConstructorCall();
        newConstructorCall.setTypeReference(getStringArrayList());

        field.setInitialValue(newConstructorCall);
        field.setTypeReference(getStringArrayList());

        field.makePublic();
        field.addModifier(ModifiersFactory.eINSTANCE.createStatic());
        field.addModifier(ModifiersFactory.eINSTANCE.createFinal());

        splConfigurationClass.getMembers().add(field);
    }

    private static NamespaceClassifierReference getStringArrayList() {
        Class stringClass = ClassifiersFactory.eINSTANCE.createClass();
        stringClass.setName("String");
        ClassifierReference stringCR = TypesFactory.eINSTANCE.createClassifierReference();
        stringCR.setTarget(stringClass);

        Class listClass = ClassifiersFactory.eINSTANCE.createClass();
        listClass.setName("ArrayList");
        ClassifierReference listCR = TypesFactory.eINSTANCE.createClassifierReference();
        listCR.setTarget(listClass);
        QualifiedTypeArgument qualifiedTypeArgument = GenericsFactory.eINSTANCE.createQualifiedTypeArgument();
        qualifiedTypeArgument.setTypeReference(stringCR);
        listCR.getTypeArguments().add(qualifiedTypeArgument);

        NamespaceClassifierReference fieldTypeNCR = TypesFactory.eINSTANCE.createNamespaceClassifierReference();
        fieldTypeNCR.getClassifierReferences().add(listCR);
        return fieldTypeNCR;
    }

    /**
     * Checks whether a given configuration class has a configuration of a given name.
     * 
     * @param splConfigurationClass
     *            The configuration {@link ConcreteClassifier}.
     * @param name
     *            The {@link String} name.
     * @return <code>true</code> if a parameter was found; <code>false</code> otherwise.
     */
    public static boolean hasConfigurationWithName(ConcreteClassifier splConfigurationClass, String name) {
        EList<Field> fields = splConfigurationClass.getFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches a given {@link ResourceSet} for a configuration class and returns it.
     * 
     * @param resourceSet
     *            The {@link Resource}.
     * @return The configuration {@link ConcreteClassifier}. <code>null</code> if nothing found.
     */
    public static ConcreteClassifier getConfigurationClass(ResourceSet resourceSet) {
        for (Resource resource : resourceSet.getResources()) {
            boolean sizeIsOne = resource.getContents().size() == 1;
            if (!sizeIsOne) {
                continue;
            }

            boolean contentIsACompilationUnit = resource.getContents().get(0) instanceof CompilationUnit;
            if (!contentIsACompilationUnit) {
                continue;
            }

            CompilationUnit compilationUnit = (CompilationUnit) resource.getContents().get(0);
            boolean hasCompilationUnitHasCorrectName = compilationUnit.getName().equals(
                    CONFIGURATION_READER_CLASS_NAME + ".java");
            if (!hasCompilationUnitHasCorrectName) {
                continue;
            }

            boolean hasOneClassifier = compilationUnit.getClassifiers().size() == 1;
            if (!hasOneClassifier) {
                continue;
            }

            ConcreteClassifier concreteClassifier = compilationUnit.getClassifiers().get(0);
            boolean classifierHasCorrectName = concreteClassifier.getName().equals(CONFIGURATION_READER_CLASS_NAME);
            if (!classifierHasCorrectName) {
                continue;
            }

            return concreteClassifier;
        }

        return null;
    }

    /**
     * Checks whether or not a given {@link ResourceSet} already has a configuration class.
     * 
     * @param resourceSet
     *            The {@link ResourceSet}.
     * @return <code>true</code> if it contains such a class; <code>false</code> otherwise.
     */
    public static boolean hasConfigurationClass(ResourceSet resourceSet) {
        ConcreteClassifier configurationClass = getConfigurationClass(resourceSet);
        return configurationClass != null;
    }

    /**
     * Generates the SPL configuration class in a given {@link ResourceSet}.
     * 
     * @param resourceSet
     *            The {@link ResourceSet}.
     * @param sourcePath
     *            The source path of the project to create the class in.
     * @return The {@link Resource} containing the class.
     */
    public static Resource generateConfigurationClassIn(ResourceSet resourceSet, String sourcePath) {
        CompilationUnit configurationCompilationUnit = getConfigurationCompilationUnit();
        String path = sourcePath;
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        path += "org" + File.separator;
        path += "splevo" + File.separator;
        path += "config" + File.separator;
        path += CONFIGURATION_READER_CLASS_NAME + ".java";
        URI uri = URI.createFileURI(path);
        Resource resource = resourceSet.createResource(uri);
        resource.getContents().add(configurationCompilationUnit);

        return resource;
    }
}

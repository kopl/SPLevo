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
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;
import org.splevo.vpm.variability.VariationPoint;

/**
 * This class provides utility methods when it comes to the configuration of the SPL which is to be
 * built.
 */
/**
 * @author daniel
 *
 */
public final class SPLConfigurationUtil {

    private static Logger logger = Logger.getLogger(SPLConfigurationUtil.class);

    public static final String CONFIGURATION_READER_CLASS_NAME = "SPLConfig";

    /**
     * Force static access with a private constructor.
     */
    private SPLConfigurationUtil() {
    }

    /**
     * Creates or searches a configuration file in a given resource set depending on whether it is
     * already existing or not. Adds a new configuration with the first variant as default value.
     *
     * @param sourcePath
     *            The {@link String} source path to store or search the configuration file in.
     * @param resourceSet
     *            The {@link ResourceSet} to store or search the configuration file in.
     * @param variationPoint
     *            The {@link VariationPoint} to create the configuration for.
     * @return The resource containing the configuration file; <code>null</code> if none created.
     */
    public static Resource addConfigurationIfMissing(String sourcePath, ResourceSet resourceSet,
            VariationPoint variationPoint) {
        Resource configFileResource = SPLConfigurationUtil.generateConfigurationClassIfMissing(resourceSet, sourcePath);

        SPLConfigurationUtil.addConfigurationToConfigurationClass(resourceSet, sourcePath, variationPoint);

        return configFileResource;
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
        equalsMethod.setName("equals");
        StringReference variantIdStringRef = ReferencesFactory.eINSTANCE.createStringReference();
        variantIdStringRef.setValue(variantID);
        equalsMethodRef.getArguments().add(variantIdStringRef);
        equalsMethodRef.setTarget(equalsMethod);
        fieldReference.setNext(equalsMethodRef);

        return classReference;
    }

    private static CompilationUnit getConfigurationCompilationUnit() {
        CompilationUnit cu = ContainersFactory.eINSTANCE.createCompilationUnit();
        cu.setName("org.splevo.config." + CONFIGURATION_READER_CLASS_NAME + ".java");
        cu.getNamespaces().clear();
        cu.getNamespaces().add("org");
        cu.getNamespaces().add("splevo");
        cu.getNamespaces().add("config");

        ConcreteClassifier c = generateConfigReaderClassifier();
        cu.getClassifiers().add(c);

        return cu;
    }

    /**
     * Adds a configuration in a configuration class. The configuration is a field named after the
     * ID of the {@link VariationPoint} and the first variant's ID as default value.
     *
     * @param rs
     *            The {@link ResourceSet} which contains the configuration class.
     * @param sourcePath
     *            The path of the configuration file.
     * @param vp
     *            The {@link VariationPoint}.
     */
    public static void addConfigurationToConfigurationClass(ResourceSet rs, String sourcePath, VariationPoint vp) {
        URI uri = generateConfigFileUri(sourcePath);
        Resource configResource = rs.getResource(uri, false);
        CompilationUnit cu = (CompilationUnit) configResource.getContents().get(0);
        ConcreteClassifier configClass = cu.getClassifiers().get(0);

        String groupID = vp.getGroup().getId();

        if (configurationClassHasConfigurationWithName(configClass, groupID)) {
            return;
        }

        StringReference configurationValueRef = ReferencesFactory.eINSTANCE.createStringReference();
        String firstVariantID = vp.getVariants().get(0).getId();
        configurationValueRef.setValue(firstVariantID);

        Field field = MembersFactory.eINSTANCE.createField();
        field.setName(groupID);
        field.setInitialValue(configurationValueRef);

        Class stringClass = ClassifiersFactory.eINSTANCE.createClass();
        stringClass.setName("String");
        ClassifierReference classifierReference = TypesFactory.eINSTANCE.createClassifierReference();
        classifierReference.setTarget(stringClass);

        field.setTypeReference(classifierReference);
        field.makePublic();
        field.addModifier(ModifiersFactory.eINSTANCE.createStatic());
        field.addModifier(ModifiersFactory.eINSTANCE.createFinal());

        configClass.getMembers().add(field);
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
    public static boolean configurationClassHasConfigurationWithName(ConcreteClassifier splConfigurationClass,
            String name) {
        EList<Field> fields = splConfigurationClass.getFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                return true;
            }
        }
        return false;
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
    public static Resource generateConfigurationClassIfMissing(ResourceSet resourceSet, String sourcePath) {
        URI uri = generateConfigFileUri(sourcePath);
        Resource configResource = resourceSet.getResource(uri, false);
        if (configResource != null) {
            return null;
        }

        CompilationUnit configurationCompilationUnit = getConfigurationCompilationUnit();
        Resource resource = resourceSet.createResource(uri);
        resource.getContents().add(configurationCompilationUnit);
        try {
            resource.save(null);
        } catch (IOException e) {
            logger.error("Could not save configuraiton resource.", e);
        }

        return resource;
    }

    private static URI generateConfigFileUri(String sourcePath) {
        String path = sourcePath;
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        path += "org" + File.separator;
        path += "splevo" + File.separator;
        path += "config" + File.separator;
        path += CONFIGURATION_READER_CLASS_NAME + ".java";
        URI uri = URI.createFileURI(path);
        return uri;
    }

    /**
     * Adds the {@link Import} to access the configuration class to a given {@link CompilationUnit}.
     *
     * @param compilationUnit
     *            The {@link CompilationUnit}.
     */
    public static void addConfigurationClassImportIfMissing(CompilationUnit compilationUnit) {
        ClassifierImport splConfigClassImport = getSPLConfigClassImport();

        if (!RefactoringUtil.containsImport(compilationUnit, splConfigClassImport)) {
            compilationUnit.getImports().add(splConfigClassImport);
        }
    }
}

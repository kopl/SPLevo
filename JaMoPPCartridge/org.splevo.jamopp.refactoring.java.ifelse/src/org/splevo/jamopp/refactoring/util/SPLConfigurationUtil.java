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

import java.util.Set;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferencesFactory;
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
     * Creates an expression reflecting the configuration of the specified variant as boolean. This
     * can be used in an if-condition.
     * 
     * @param variationPointID
     *            The propertie's {@link String} name.
     * @param variantID
     *            The {@link String} to compare to.
     * @return The generated {@link Expression}.
     */
    public static IdentifierReference generateSingleVariantMatchingExpression(String variationPointID, String variantID) {
        Field field = MembersFactory.eINSTANCE.createField();
        field.setName(variantID);
        field.setTypeReference(TypesFactory.eINSTANCE.createBoolean());
        field.addModifier(ModifiersFactory.eINSTANCE.createPublic());
        field.addModifier(ModifiersFactory.eINSTANCE.createStatic());
        IdentifierReference identifierReference = ReferencesFactory.eINSTANCE.createIdentifierReference();
        identifierReference.setTarget(field);

        IdentifierReference configReaderReference = ReferencesFactory.eINSTANCE.createIdentifierReference();
        configReaderReference.setTarget(generateConfigReaderClassifier());
        configReaderReference.setNext(identifierReference);

        return configReaderReference;
    }

    /**
     * Creates the following expression: id1 || id2 || ... This can be used in an if-condition.
     * 
     * @param variationPointID
     *            The propertie's {@link String} name.
     * @param variantIDs
     *            The variant {@link String} IDs in a {@link Set}.
     * @return The generated {@link Expression}.
     */
    public static Expression generateVariantMatchingExpression(String variationPointID, Set<String> variantIDs) {
        ConditionalOrExpression conditionalOrExpression = ExpressionsFactory.eINSTANCE.createConditionalOrExpression();

        for (String variantID : variantIDs) {
            conditionalOrExpression.getChildren().add(
                    generateSingleVariantMatchingExpression(variationPointID, variantID));
        }

        return conditionalOrExpression;
    }
}

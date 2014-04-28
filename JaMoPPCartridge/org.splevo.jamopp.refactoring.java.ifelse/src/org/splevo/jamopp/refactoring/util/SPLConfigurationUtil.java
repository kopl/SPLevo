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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
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
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;

/**
 * This class provides utility methods when it comes to the configuration of the SPL which is to be
 * built.
 */
public final class SPLConfigurationUtil {

    private static final String CONFIGURATION_READER_CLASS_NAME = "SPLConfigReader";
    private static final String CONFIGURATION_READER_READ_CONFIG_METHOD_NAME = "getConfigs";

    /** The logger used by this class. */
    private static Logger logger = Logger.getLogger(SPLConfigurationUtil.class);

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
    public static Import getRequiringImport() {
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
     * Creates an expression that matches a property of a given name with a given string.
     * 
     * @param variationPointID
     *            The propertie's {@link String} name.
     * @param variantID
     *            The {@link String} to compare to.
     * @return The generated {@link Expression}.
     */
    public static IdentifierReference generateSingleVariantMatchingExpression(String variationPointID, String variantID) {
        IdentifierReference configReaderReference = ReferencesFactory.eINSTANCE.createIdentifierReference();
        configReaderReference.setTarget(generateConfigReaderClassifier());

        MethodCall readConfigMethodCall = getReadConfigMethodCall(
				variationPointID, configReaderReference);

        MethodCall containsMethodCall = ReferencesFactory.eINSTANCE.createMethodCall();
        ClassMethod containsMethod = MembersFactory.eINSTANCE.createClassMethod();
        containsMethod.setName("contains");
        containsMethodCall.setTarget(containsMethod);
        StringReference matchWithRef = ReferencesFactory.eINSTANCE.createStringReference();
        matchWithRef.setValue(variantID);
        containsMethodCall.getArguments().add(matchWithRef);
        readConfigMethodCall.setNext(containsMethodCall);

        return configReaderReference;
    }

    public static Expression generateVariantMatchingExpression(String variationPointID, Set<String> variantIDs) {
    	ConditionalOrExpression conditionalOrExpression = ExpressionsFactory.eINSTANCE.createConditionalOrExpression();
    	
		for (String variantID : variantIDs) {
			conditionalOrExpression.getChildren().add(generateSingleVariantMatchingExpression(variationPointID, variantID));
		}
    	
		return conditionalOrExpression;
    }

	private static MethodCall getReadConfigMethodCall(String variationPointID,
			IdentifierReference configReaderReference) {
		MethodCall readConfigMethodCall = ReferencesFactory.eINSTANCE.createMethodCall();
        ClassMethod readConfigMethod = MembersFactory.eINSTANCE.createClassMethod();
        readConfigMethod.setName(CONFIGURATION_READER_READ_CONFIG_METHOD_NAME);
        readConfigMethodCall.setTarget(readConfigMethod);
        StringReference propertyNameRef = ReferencesFactory.eINSTANCE.createStringReference();
        propertyNameRef.setValue(variationPointID);
        readConfigMethodCall.getArguments().add(propertyNameRef);
        configReaderReference.setNext(readConfigMethodCall);
		return readConfigMethodCall;
	}

    /**
     * Creates a Java SPL properties file with the given key value configs at a specified path.
     * 
     * @param path
     *            The file path.
     * @param configs
     *            The properties.
     * @throws IOException
     *             Thrown if properties file cannot be saved.
     */
    public void createSPLPropertiesFile(String path, Map<String, String> configs) throws IOException {
        Properties prop = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream(path);
            for (String key : configs.keySet()) {
                prop.setProperty(key, configs.get(key));
            }
            prop.store(output, null);

        } catch (IOException e) {
            logger.error("Cannot save properties file at location: " + path, e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    logger.error("Cannot close output stream.", e);
                }
            }
        }
    }
}

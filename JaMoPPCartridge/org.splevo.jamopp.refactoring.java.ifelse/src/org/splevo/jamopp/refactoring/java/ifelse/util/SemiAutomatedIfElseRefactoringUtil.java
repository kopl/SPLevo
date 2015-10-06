/*******************************************************************************
 * Copyright (c) 2015
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Max Scheerer
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.refactoring.java.ifelse.util;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.StatementsFactory;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Optional;

/**
 * A refactoring util tailored for the usage in semi-automated refactorings.
 */
public class SemiAutomatedIfElseRefactoringUtil implements IfElseRefactoringUtil {

    private final String validatorName;
    private final Map<String, String> variantToLicenseMap;

    /**
     * Constructs the refactoring util.
     * 
     * @param validatorName
     *            The name of the license validator.
     * @param variantToLicenseMap
     *            The mapping between a variant and a license constant.
     */
    public SemiAutomatedIfElseRefactoringUtil(String validatorName, Map<String, String> variantToLicenseMap) {
        this.validatorName = validatorName;
        this.variantToLicenseMap = variantToLicenseMap;
    }

    @Override
    public Condition createVariabilityCondition(VariationPoint notUsedParameter, String variantID) {
        Condition condition = StatementsFactory.eINSTANCE.createCondition();

        condition.setCondition(generateLicenseValidationExpression(variantToLicenseMap.get(variantID),
                this.validatorName));

        Block ifBlock = StatementsFactory.eINSTANCE.createBlock();
        condition.setStatement(ifBlock);

        return condition;
    }

    /**
     * Creates an expression which checks if a given license of a variation point is valid:
     * <code>className.getInstance().hasUserModuleLicense([licenseConstant]);</code>
     * 
     * @param licenseConstant
     *            The name of a license.
     * @param className
     *            The name of the license validator.
     * @return The generated {@link Expression}.
     */
    private IdentifierReference generateLicenseValidationExpression(String licenseConstant, String className) {
        Class createClass = ClassifiersFactory.eINSTANCE.createClass();
        createClass.addModifier(ModifiersFactory.eINSTANCE.createPublic());
        createClass.addModifier(ModifiersFactory.eINSTANCE.createStatic());
        createClass.setName(className);

        IdentifierReference classReference = ReferencesFactory.eINSTANCE.createIdentifierReference();
        classReference.setTarget(createClass);
        MethodCall getInstanceMethodRef = ReferencesFactory.eINSTANCE.createMethodCall();
        ClassMethod getInstanceMethod = MembersFactory.eINSTANCE.createClassMethod();
        getInstanceMethod.addModifier(ModifiersFactory.eINSTANCE.createStatic());
        getInstanceMethod.setName("getInstance");
        getInstanceMethodRef.setTarget(getInstanceMethod);

        MethodCall hasUserModuleLicenseMethodRef = ReferencesFactory.eINSTANCE.createMethodCall();
        ClassMethod hasUserModuleLicenseMethod = MembersFactory.eINSTANCE.createClassMethod();
        hasUserModuleLicenseMethod.setName("hasUserModuleLicense");
        hasUserModuleLicenseMethodRef.setTarget(hasUserModuleLicenseMethod);
        StringReference licenseConstantStringRef = ReferencesFactory.eINSTANCE.createStringReference();
        licenseConstantStringRef.setValue(licenseConstant);
        hasUserModuleLicenseMethodRef.getArguments().add(licenseConstantStringRef);

        getInstanceMethodRef.setNext(hasUserModuleLicenseMethodRef);

        classReference.setNext(getInstanceMethodRef);

        return classReference;
    }

    @Override
    public Optional<Resource> createConfigurationClass(VariationPoint variationPoint,
            Map<String, Object> refactoringOptions) {
        // do nothing
        return Optional.absent();
    }

    @Override
    public void createConfigurationClassImport(Commentable vpLocation) {
        // do nothing
    }

}

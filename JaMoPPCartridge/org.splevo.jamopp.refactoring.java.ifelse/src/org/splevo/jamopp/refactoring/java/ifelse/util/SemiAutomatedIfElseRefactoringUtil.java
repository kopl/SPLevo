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
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.ImportsFactory;
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
import org.splevo.jamopp.refactoring.util.RefactoringUtil;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.base.Optional;

/**
 * A refactoring util tailored for the usage in semi-automated refactorings.
 */
public class SemiAutomatedIfElseRefactoringUtil implements IfElseRefactoringUtil {

    private final ConcreteClassifier validator;
    private final Map<String, String> variantToLicenseMap;

    /**
     * Constructs the refactoring util.
     * 
     * @param validator
     *            The license validator.
     * @param variantToLicenseMap
     *            The mapping between a variant and a license constant.
     */
    public SemiAutomatedIfElseRefactoringUtil(ConcreteClassifier validator, Map<String, String> variantToLicenseMap) {
        this.validator = validator;
        this.variantToLicenseMap = variantToLicenseMap;
    }

    @Override
    public Condition createVariabilityCondition(VariationPoint notUsedParameter, String variantID) {
        Condition condition = StatementsFactory.eINSTANCE.createCondition();

        condition.setCondition(generateLicenseValidationExpression(variantToLicenseMap.get(variantID),
                this.validator));

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
     * @param licenseValidator
     *            The license validator.
     * @return The generated {@link Expression}.
     */
    private IdentifierReference generateLicenseValidationExpression(String licenseConstant, ConcreteClassifier licenseValidator) {
        IdentifierReference classReference = ReferencesFactory.eINSTANCE.createIdentifierReference();
        classReference.setTarget(licenseValidator);
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
        CompilationUnit vpCompilationUnit = vpLocation.getContainingCompilationUnit();
        ConcreteClassifier importTarget = validator;
        
        ClassifierImport validatorImport = ImportsFactory.eINSTANCE.createClassifierImport();
        validatorImport.getNamespaces().addAll(importTarget.getContainingContainerName());
        validatorImport.setClassifier(importTarget);

        if (!RefactoringUtil.containsImport(vpCompilationUnit, validatorImport)) {
            vpCompilationUnit.getImports().add(validatorImport);
        }
    }

}

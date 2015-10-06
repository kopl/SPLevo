package org.splevo.jamopp.refactoring.java.ifelse.util;

import java.util.Map;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
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

public class SemiAutomatedIfElseRefactoringUtil implements IfElseRefactoringUtil {

	private String validatorName = "";
	private Map<String, String> variantToLicenseMap = null;
	
	public SemiAutomatedIfElseRefactoringUtil(String validatorName, Map<String, String> variantToLicenseMap) {
		this.validatorName = validatorName;
		this.variantToLicenseMap = variantToLicenseMap;
	}
	
	/**
     * Generates a condition for license-if-block. Matches the SPL configuration attribute with
     * the given name (from the group ID) with the given variant id within the condition.
     * 
     * @param variantId
     *            The variant id as {@link String}.
     * @param groupName
     *            is not used in this context.
     * @return The generated {@link Condition}.
     */
	@Override
	public Condition createVariabilityCondition(String variantID, String groupName) {
		Condition condition = StatementsFactory.eINSTANCE.createCondition();
		
        condition.setCondition(generateLicenseValidationExpression(variantToLicenseMap.get(variantID), this.validatorName));
		
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
}

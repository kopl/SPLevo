package org.splevo.diffing.emfcompare.diff;

import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Model;

/**
 * An attribute checker specific to the java model to 
 * define if a specific attribute should be ignored or not.
 *
 */
public class JavaModelAttributesCheck extends AttributesCheck {

	/**
	 * Constructor to set the match manager to be used.
	 * @param manager The match manager to use.
	 */
	public JavaModelAttributesCheck(IMatchManager manager) {
		super(manager);
	}

	/**
	 * Model specific check whether an attribute should be ignored or not.
	 * 
	 * @param attribute The EAttribute to check.
	 * @return The decision whether to ignore the attribute or not.
	 */
	@Override
	protected boolean shouldBeIgnored(EAttribute attribute) {

		// ignore CompilationUnit.originalFilePath
		if("originalFilePath".equals(attribute.getName())){
			if(CompilationUnit.class.getCanonicalName().equals(attribute.getEContainingClass().getInstanceTypeName())){
				return true;
			}
		}

		// ignore all attributes of the Model itself
		if(Model.class.getCanonicalName().equals(attribute.getEContainingClass().getInstanceTypeName())){
			return true;
		}
		
		return super.shouldBeIgnored(attribute);
	}

	
}

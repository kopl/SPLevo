package org.splevo.diffing.kdm;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;

/**
 * A printer that generates a type specific information string
 * for a supplied model element.
 * 
 * It uses an internal EMF switch based type decision for an enhanced performance.
 * 
 */
public class JavaModelElementPrinter {
	
	/** The internal printer to be used */
	ElementPrinter internalPrinter = new ElementPrinter();
	
	/**
	 * Print the supplied element.
	 * @param eObject The element to be printed
	 */
	public String printElement(EObject eObject){
		return internalPrinter.doSwitch(eObject);
	}
	
	/**
	 * An emf switch based printer specific to the java application model.
	 *
	 */
	class ElementPrinter extends JavaSwitch<String>{

		@Override
		public String caseSingleVariableAccess(SingleVariableAccess object) {
			return "SingleVariableAccessImpl "+object.getVariable().getName();
		}
		
		@Override
		public String caseJavadoc(Javadoc object) {
			return "JavadocImpl";
		}
		
		@Override
		public String caseClassInstanceCreation(ClassInstanceCreation object) {
			return object.getType().getType().getName();
		}
		
		@Override
		public String caseTypeAccess(TypeAccess object) {
			return object.getType().getName();
		}
		
		@Override
		public String casePackage(Package object) {
			return object.getName();
		}
		
		@Override
		public String caseTextElement(TextElement object) {
			if(object.getText() != null){
				return object.getText().substring(0,20);
			} else if(object.getComments() != null){
				return object.getComments().toString().substring(0,20);
			} else {
				return "TextElement without text: "+object.toString();
			}
		}
		
		@Override
		public String caseMethodInvocation(MethodInvocation object) {
			return object.getMethod().getName();
		}
		
		@Override
		public String defaultCase(EObject object) {
			return object.getClass().getName();
		}
	}

}

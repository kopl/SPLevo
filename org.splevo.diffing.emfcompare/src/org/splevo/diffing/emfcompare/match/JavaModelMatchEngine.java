package org.splevo.diffing.emfcompare.match;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.match.engine.GenericMatchEngine;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.util.JavaModelUtil;

/**
 * A KDM specific match engine taking into account
 * that multi-step references need to be considered when comparing elements.
 * 
 * For example, an import statement references a type access which references a type.
 * If the import is changed, the type is changed but not the type access. 
 * As a result the generic match engine returns those import statements as similar. 
 * 
 * {@inheritDoc}
 * 
 * @see http://www.eclipse.org/forums/index.php?t=msg&goto=511859&
 *  
 */
public class JavaModelMatchEngine extends GenericMatchEngine {
	
	/**
	 * A custom similarity check for kdm / modisco elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isSimilar(final EObject obj1, final EObject obj2)
			throws FactoryException {

		// if the types of the elements is different return false straight away
		if(!obj1.getClass().equals(obj2.getClass())){
			return false;
		}
		
		// check the similarity for java model specific elements.
		SimilaritySwitch similaritySwitch = new SimilaritySwitch(obj2);
		Boolean similar = similaritySwitch.doSwitch(obj1);
		if(similar != null){
			return similar.booleanValue();
		}
		
		return super.isSimilar(obj1, obj2);
	}
	
	/**
	 * Internal switch class to prove element similarity.
	 */
	private class SimilaritySwitch extends JavaSwitch<Boolean>{
		
	    private Logger logger = Logger.getLogger(SimilaritySwitch.class);

		/** The object to compare the switched element with. */
		private EObject compareElement = null;

		/** 
		 * Constructor requiring the element to compare with.
		 * 
		 * @param compareElement The element to check the similarity with.
		 */
		public SimilaritySwitch(EObject compareElement) {
			this.compareElement = compareElement;
		}
		
		@Override
		public Boolean caseImportDeclaration(ImportDeclaration object) {

			NamedElement importedElement1 = object.getImportedElement(); 
			NamedElement importedElement2 = ((ImportDeclaration) compareElement).getImportedElement();
			
			if(importedElement1 instanceof AbstractTypeDeclaration 
					&& importedElement2 instanceof AbstractTypeDeclaration){
				
				AbstractTypeDeclaration atd1 = (AbstractTypeDeclaration) importedElement1;
				AbstractTypeDeclaration atd2 = (AbstractTypeDeclaration) importedElement2;
				
				if(atd1.getName().equals(atd2.getName()) &&
						atd1.getPackage().getName().equals(atd2.getPackage().getName())){
					return Boolean.TRUE;
				} else {
					return Boolean.FALSE;
				}
			}
			
			return null;
		}
		
		/**
		 * Check the similarity of an abstract type declaration.
		 * 
		 * They should only match if they are in the same package and have the same name
		 * This might be further enhanced in the future by comparing
		 * their contained fields, methods, etc.
		 * 
		 * @param The type declaration to check.
		 * @return true/false whether the element match or not.
		 */
		@Override
		public Boolean caseAbstractTypeDeclaration(
				AbstractTypeDeclaration object) {
			AbstractTypeDeclaration type1 = (AbstractTypeDeclaration) object; 
			AbstractTypeDeclaration type2 = (AbstractTypeDeclaration) compareElement;

			if(type1.getName() != null && !type1.getName().equals(type2.getName())){
				return Boolean.FALSE;
			} else {
				StringBuilder packageBuilder1 = new StringBuilder();
				JavaModelUtil.buildPackagePath(type1.getPackage(), packageBuilder1);
				StringBuilder packageBuilder2 = new StringBuilder();
				JavaModelUtil.buildPackagePath(type2.getPackage(), packageBuilder2);
				if(packageBuilder1.toString().equals(packageBuilder2.toString())){
					return Boolean.TRUE;
				} else {
					return Boolean.FALSE;
				}
			}
		}
		
		/**
		 * Check if two method invocations are similar.
		 * This checks 
		 * <ul>
		 * 	<li>the method name</li>
		 *  <li>the method declaring type</li>
		 *  <li>the method parameters</li>
		 *  <li>the method return type</li>
		 * </ul>
		 */
		@Override
		public Boolean caseMethodInvocation(MethodInvocation methodInvocation1) {

			MethodInvocation methodInvocation2 = (MethodInvocation) compareElement;

			// check the methods names
			AbstractMethodDeclaration method1 = methodInvocation1.getMethod();
			AbstractMethodDeclaration method2 = methodInvocation2.getMethod();
			if(!method1.getName().equals(method2.getName())){
				return Boolean.FALSE;
			}
			
			// check the methods declaring types
			AbstractTypeDeclaration type1 = method1.getAbstractTypeDeclaration();
			AbstractTypeDeclaration type2 = method2.getAbstractTypeDeclaration();
			String fqnType1 = JavaModelUtil.buildFullQualifiedName(type1);
			String fqnType2 = JavaModelUtil.buildFullQualifiedName(type2);
			if(!fqnType1.equals(fqnType2)){
				logger.debug("methodInvocations not similar because of unmatched declaring type "+method1.getName());
				return Boolean.FALSE;
			}
			
			// TODO check if the proactive parameter check is really necessary or of this is done by emf compare by default
			
			// check parameter count
			if(method1.getParameters().size() != method2.getParameters().size()){
				logger.debug("methodInvocations not similar because of different parameter counts ");
				return Boolean.FALSE;
			}
			
			// check parameter types
			// TODO check not only the type names but also the type packages
			for(int i = 0; i < method1.getParameters().size(); i++){
				SingleVariableDeclaration varDecl1 = method1.getParameters().get(i);
				SingleVariableDeclaration varDecl2 = method1.getParameters().get(i);
				
				String var1TypeName = varDecl1.getType().getType().getName();
				String var2TypeName = varDecl2.getType().getType().getName();
				if(!var1TypeName.equals(var2TypeName)){
					logger.debug("methodInvocations not similar because of different parameter types ");
					return Boolean.FALSE;
				}
			}
			
			return null;
		}

		/**
		 * Return null in the default case to indicate that it 
		 * has to be further processed by the standard similarity check.
		 */
		@Override
		public Boolean defaultCase(EObject object) {
			return null;
		}
		
	}

}

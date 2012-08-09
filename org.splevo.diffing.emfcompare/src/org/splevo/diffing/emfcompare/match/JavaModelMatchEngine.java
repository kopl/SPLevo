package org.splevo.diffing.emfcompare.match;

import java.util.List;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.match.engine.GenericMatchEngine;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.splevo.diffing.emfcompare.util.JavaModelUtil;

/**
 * A KDM specific match engine taking into account
 * that multi-step references need to be considered when comparing elements.
 * 
 * For example, an import statement references a type access which references a type.
 * If the import is changed, the type is changed but not the type access. 
 * As a result the generic match engine returns those import statements as similar. 
 *  
 * @see http://www.eclipse.org/forums/index.php?t=msg&goto=511859&
 *  
 * @author benjamin
 *
 */
public class JavaModelMatchEngine extends GenericMatchEngine {
	
	/**
	 * A custom similarity check for kdm / modisco elements.
	 * 
	 * 
	 */
	@Override
	protected boolean isSimilar(final EObject obj1, final EObject obj2)
			throws FactoryException {

		// if the types of the elements is different return false straight away
		if(!obj1.getClass().equals(obj2.getClass())){
			return false;
		}
		
		// Handle Import Declarations
		// They should only match if they are about the same declaration type
		if(obj1 instanceof ImportDeclaration){
			NamedElement importedElement1 = ((ImportDeclaration) obj1).getImportedElement(); 
			NamedElement importedElement2 = ((ImportDeclaration) obj2).getImportedElement();
			
			if(importedElement1 instanceof AbstractTypeDeclaration 
					&& importedElement2 instanceof AbstractTypeDeclaration){
				
				AbstractTypeDeclaration atd1 = (AbstractTypeDeclaration) importedElement1;
				AbstractTypeDeclaration atd2 = (AbstractTypeDeclaration) importedElement2;
				
				if(atd1.getName().equals(atd2.getName()) &&
						atd1.getPackage().getName().equals(atd2.getPackage().getName())){
					return true;
				} else {
					return false;
				}
					
			}
		}
		
		
		// Handle Class declaration
		// They should only match if they are in the same package and have the same name
		// This might be further enhanced in the future by comparing 
		// their contained fields, methods, etc.
		if(obj1 instanceof AbstractTypeDeclaration){
			AbstractTypeDeclaration type1 = (AbstractTypeDeclaration) obj1; 
			AbstractTypeDeclaration type2 = (AbstractTypeDeclaration) obj2;
						
			if(type1.getName() != null && !type1.getName().equals(type2.getName())){
				return false;
			} else {
				StringBuilder packageBuilder1 = new StringBuilder();
				JavaModelUtil.buildPackagePath(type1.getPackage(), packageBuilder1);
				StringBuilder packageBuilder2 = new StringBuilder();
				JavaModelUtil.buildPackagePath(type2.getPackage(), packageBuilder2);
				return packageBuilder1.toString().equals(packageBuilder2.toString());
			}
		}
		
		
		return super.isSimilar(obj1, obj2);
	}
	
	
	
	/**
	 * Find the most similar object of a provided list of objects
	 */
	@Override
	protected EObject findMostSimilar(EObject eObj, List<EObject> list)
			throws FactoryException {
		return super.findMostSimilar(eObj, list);
	}	

}

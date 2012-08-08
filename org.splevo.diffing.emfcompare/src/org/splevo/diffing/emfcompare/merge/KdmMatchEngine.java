package org.splevo.diffing.emfcompare.merge;

import java.util.List;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.match.engine.GenericMatchEngine;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.omg.kdm.kdm.util.KdmSwitch;

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
public class KdmMatchEngine extends GenericMatchEngine {
	
	/**
	 * A custom similarity check for kdm / modisco elements.
	 * 
	 * 
	 */
	@Override
	protected boolean isSimilar(EObject obj1, EObject obj2)
			throws FactoryException {
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

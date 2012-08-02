package org.splevo.diffing;

import org.eclipse.emf.compare.match.metamodel.MatchPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer;

//TODO: Original CrossReferencer has been externalized. Documentation should be added?
/**
 * The cross referencer is an emf infrastructure acting as a map and storing references
 * which are not explicit available in models under study.
 * In this case, the cross referencer stores match references between model elements 
 * (see reference check below: boolean crossReference)
 *
 */
class SPLevoCrossReferencer extends CrossReferencer {
	private static final long serialVersionUID = 2615156054928729681L;
	{
		crossReference();
	}

	SPLevoCrossReferencer(EObject arg0) {
		super(arg0);
	}

	@Override
	protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
		// FIXME shouldn't we test for eObject instanceof Match2Elements?
		// Cross reference this if it is either one of the left, right, or ancestor elements
		boolean crossReference = eReference == MatchPackage.eINSTANCE.getMatch2Elements_LeftElement()
				|| eReference == MatchPackage.eINSTANCE.getMatch2Elements_RightElement()
				|| eReference == MatchPackage.eINSTANCE.getMatch3Elements_OriginElement();

		// Or if it is an unmatched element
		crossReference = crossReference || eReference == MatchPackage.eINSTANCE.getUnmatchElement_Element();

		if (crossReference) {
			super.crossReference(eObject, eReference, crossReferencedEObject);
		}

		return crossReference;
	}
}
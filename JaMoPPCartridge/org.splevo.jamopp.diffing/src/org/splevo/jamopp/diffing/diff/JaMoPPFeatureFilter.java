package org.splevo.jamopp.diffing.diff;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.commons.layout.LayoutPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.imports.ImportsPackage;
import org.splevo.jamopp.diffing.scope.PackageIgnoreChecker;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

/**
 * A filter for model element features specific for the JaMoPP java model.
 */
public class JaMoPPFeatureFilter extends FeatureFilter {

	/** The logger for this class. */
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(JaMoPPFeatureFilter.class);

	/** The package ignore visitor instance to be used. */
	private PackageIgnoreChecker packageIgnoreChecker = null;

	/** The similarity checker. */
	@SuppressWarnings("unused")
	private SimilarityChecker similarityChecker = new SimilarityChecker();

	/**
	 * Constructor requires to set the list of packages to be ignored.
	 *
	 * @param packageIgnoreChecker
	 *            Checker to decide if an element is in a package to ignore.
	 */
	public JaMoPPFeatureFilter(PackageIgnoreChecker packageIgnoreChecker) {
		this.packageIgnoreChecker = packageIgnoreChecker;
	}

	/**
	 * TODO: Implement JaMoPP specific attribute filtering.<br>
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isIgnoredAttribute(EAttribute attribute) {

		switch (attribute.getFeatureID()) {
		case CommonsPackage.COMMENTABLE__LAYOUT_INFORMATIONS:
		case LayoutPackage.LAYOUT_INFORMATION__HIDDEN_TOKEN_TEXT:
		case LayoutPackage.LAYOUT_INFORMATION__VISIBLE_TOKEN_TEXT:
		case LayoutPackage.LAYOUT_INFORMATION__SYNTAX_ELEMENT_ID:

			return true;
			// case JavaPackage.COMPILATION_UNIT__ORIGINAL_FILE_PATH:
			// case JavaPackage.MODEL__NAME:
			// case JavaPackage.ABSTRACT_TYPE_QUALIFIED_EXPRESSION__QUALIFIER:
			// case JavaPackage.MEMBER_REF__QUALIFIER:
			// return true;

		default:
			return super.isIgnoredAttribute(attribute);
		}
	}

	/**
	 * Check if a reference should be ignored or not.<br>
	 * TODO: Implement JaMoPP specific reference filtering. <br>
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isIgnoredReference(Match match, EReference reference) {

		EObject referencingElementLeft = match.getLeft();
		EObject referencingElementRight = match.getRight();

		// The classifier reference of import elements is ignored
		// because changed imports are already handled by not matching import
		// declarations.
		if (reference.getEContainingClass().getClassifierID() == ImportsPackage.CLASSIFIER_IMPORT
				&& reference.getFeatureID() == ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER) {
			return true;
		}

		switch (reference.getFeatureID()) {
		case CommonsPackage.COMMENTABLE__LAYOUT_INFORMATIONS:
		case ContainersPackage.PACKAGE__COMPILATION_UNITS:
			return true;
			// case JavaPackage.MODEL__NAME:
			// case JavaPackage.ABSTRACT_TYPE_QUALIFIED_EXPRESSION__QUALIFIER:
			// case JavaPackage.MEMBER_REF__QUALIFIER:
			// return true;

		default:
			break;
		}

		// filter references of elements located in ignore packages
		if (isOneInIgnorePackage(referencingElementLeft,
				referencingElementRight)) {
			return true;
		}

		return super.isIgnoredReference(match, reference);
	}

	@Override
	public boolean checkForOrderingChanges(EStructuralFeature feature) {

//		if("statements".equals(feature.getName())){
//			return true;
//		}
		return false;
	}

	/**
	 * Check if one of the elements is in an ignore package.
	 *
	 * If the left element is not null it is checked. Otherwise the right one is
	 * checked if it is not null. If both are null, null is returned.
	 *
	 * @param left
	 *            The left element to check.
	 * @param right
	 *            The right element to check.
	 * @return True/False if one is in an ignore package. Null if both inputs
	 *         are null.
	 */
	private boolean isOneInIgnorePackage(EObject left, EObject right) {
		if (left != null
				&& packageIgnoreChecker.isInIgnorePackage(left) == Boolean.TRUE) {
			return true;
		} else {
			return (right != null && packageIgnoreChecker
					.isInIgnorePackage(right) == Boolean.TRUE);
		}
	}

}

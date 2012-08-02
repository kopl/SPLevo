package org.splevo.diffing;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.engine.IDiffEngine;
import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSetSnapshot;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.DiffResourceSet;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChange;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.ReferenceChange;
import org.eclipse.emf.compare.diff.metamodel.ReferenceChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ReferenceChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.ResourceDependencyChange;
import org.eclipse.emf.compare.diff.metamodel.UpdateReference;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.DefaultMatchScopeProvider;
import org.eclipse.emf.compare.match.engine.IMatchEngine;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.metamodel.MatchResourceSet;
import org.eclipse.emf.compare.match.metamodel.Side;
import org.eclipse.emf.compare.match.metamodel.UnmatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer;
import org.splevo.diffing.emfcompare.diff.KdmDiffEngine;
import org.splevo.diffing.emfcompare.diff.transform.DiffTreeTransformer;
import org.splevo.diffing.emfcompare.merge.KdmMatchEngine;

/**
 * A service to analyze the difference between to abstract syntax trees.
 * 
 * @author Benjamin Klatt
 * 
 */
public class EMFCompareDiffingService implements DiffingService {

	/** The KDM specific match engine */
	private final IMatchEngine matchEngine = new KdmMatchEngine();

	/** The KDM specific diff engine */
	private final IDiffEngine diffEngine = new KdmDiffEngine();

	/* (non-Javadoc)
	 * @see org.splevo.diffing.DiffingService#getDiff(java.util.List, java.util.List)
	 */
	@Override
	public ComparisonResourceSetSnapshot getDiff(List<File> leftASTModels,
			List<File> rightASTModels) throws IOException, InterruptedException {

		ResourceSet leftResourceSet = loadModelResourceSet(leftASTModels);
		ResourceSet rightResourceSet = loadModelResourceSet(rightASTModels);

		matchEngine.reset();
		diffEngine.reset();

		// define the match resource set
		final MatchResourceSet matchResourceSet;
		final Map<String, Object> options = new EMFCompareMap<String, Object>();
		DefaultMatchScopeProvider matchScopeProvider = new DefaultMatchScopeProvider(
				leftResourceSet, rightResourceSet);
		options.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER,
				matchScopeProvider);
		matchResourceSet = MatchService.doResourceSetMatch(
				leftResourceSet, rightResourceSet,
				options);

		// define the match scope provider
		Map<String, Object> matchOptions = new EMFCompareMap<String, Object>();
		matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER,
				matchScopeProvider);

		DiffResourceSet diffSet = DiffFactory.eINSTANCE.createDiffResourceSet();
		CrossReferencer crossReferencer = new SPLevoCrossReferencer(diffSet);

		for (final MatchModel match : matchResourceSet.getMatchModels()) {
			final DiffModel diffmodel = diffEngine.doDiffResourceSet(match,
					false, crossReferencer);

			// load the diff model extensions for the match model
			// and let each extension perform the post processing
			final Collection<AbstractDiffExtension> extensions = DiffService
					.getCorrespondingDiffExtensions(match);
			for (final AbstractDiffExtension ext : extensions) {
				if (ext != null) {
					ext.visit(diffmodel);
				}
			}

			diffEngine.reset();
			diffSet.getDiffModels().add(diffmodel);
		}
		for (final UnmatchModel unmatch : matchResourceSet.getUnmatchedModels()) {
			ResourceDependencyChange dependencyChange;
			if (unmatch.getSide() == Side.LEFT) {
				dependencyChange = DiffFactory.eINSTANCE
						.createResourceDependencyChangeLeftTarget();
			} else {
				dependencyChange = DiffFactory.eINSTANCE
						.createResourceDependencyChangeRightTarget();
			}
			if (unmatch.isRemote()) {
				dependencyChange.setRemote(true);
			}
			dependencyChange.getRoots().addAll(unmatch.getRoots());
			diffSet.getResourceDiffs().add(dependencyChange);
		}

		fillRequiredDifferences(diffSet);

		ComparisonResourceSetSnapshot snapshot = buildResourceSetComparisionSnapshot(
				matchResourceSet, diffSet);

		DiffTreeTransformer transformer = new DiffTreeTransformer();
		transformer.convertModel(snapshot);

		return snapshot;

	}

	/**
	 * Post-processing on the DiffModel or DiffResourceSet to fill each
	 * {@link ModelElementChange} with possible required others differences.
	 * 
	 * @param model
	 *            The DiffModel or DiffResourceSet.
	 */
	private static void fillRequiredDifferences(EObject model) {
		CrossReferencer crossReferencer = new CrossReferencer(model) {
			/** Generic Serial ID. */
			private static final long serialVersionUID = 1L;

			{
				crossReference();
			}
		};

		final Iterator<EObject> diffs = model.eAllContents();
		while (diffs.hasNext()) {
			final EObject obj = diffs.next();
			if ((obj instanceof ModelElementChange || obj instanceof ReferenceChange)
					&& !(obj instanceof AbstractDiffExtension)) {
				fillRequiredDifferences(crossReferencer, (DiffElement) obj);
			}
		}
	}

	/**
	 * Fill the specified difference with a possible required other difference.
	 * 
	 * @param crossReferencer
	 *            Cross referencer on the DiffModel or DiffResourceSet.
	 * @param diff
	 *            The difference to fill.
	 */
	private static void fillRequiredDifferences(
			CrossReferencer crossReferencer, final DiffElement diff) {
		final Set<EObject> refEObjects = getReferencedEObjects(diff);
		final Iterator<EObject> referencedEObjects = refEObjects.iterator();
		while (referencedEObjects.hasNext()) {
			final EObject referencedEObject = referencedEObjects.next();
			final Collection<Setting> settings = crossReferencer
					.get(referencedEObject);
			if (settings != null) {
				for (Setting setting : settings) {
					final EObject crossElt = setting.getEObject();
					linkDifferences(diff, crossElt);
				}
			}
		}

	}

	/**
	 * Get the all the referenced model objects from the specified difference.
	 * 
	 * @param diff
	 *            The difference.
	 * @return The list of the model objects.
	 */
	private static Set<EObject> getReferencedEObjects(final DiffElement diff) {
		EObject elt = null;
		final Set<EObject> referencedEObjects = new LinkedHashSet<EObject>();
		if (diff instanceof ModelElementChangeLeftTarget) {
			final ModelElementChangeLeftTarget mec = (ModelElementChangeLeftTarget) diff;
			elt = mec.getLeftElement();
			// referencedEObjects.addAll(DiffReferenceUtil.getReferencedEObjects(elt,
			// true));
			referencedEObjects.remove(elt);
		} else if (diff instanceof ModelElementChangeRightTarget) {
			final ModelElementChangeRightTarget mec = (ModelElementChangeRightTarget) diff;
			elt = mec.getRightElement();
			// referencedEObjects.addAll(DiffReferenceUtil.getReferencedEObjects(elt,
			// true));
			referencedEObjects.remove(elt);
		} else if (diff instanceof ReferenceChangeLeftTarget) {
			final ReferenceChangeLeftTarget rc = (ReferenceChangeLeftTarget) diff;
			final EObject leftTarget = rc.getLeftTarget();
			if (leftTarget != null) {
				referencedEObjects.add(leftTarget);
			}
		} else if (diff instanceof ReferenceChangeRightTarget) {
			final ReferenceChangeRightTarget rc = (ReferenceChangeRightTarget) diff;
			final EObject rightTarget = rc.getRightTarget();
			if (rightTarget != null) {
				referencedEObjects.add(rightTarget);
			}
		} else if (diff instanceof UpdateReference) {
			final UpdateReference ur = (UpdateReference) diff;
			final EObject leftTarget = ur.getLeftTarget();
			final EObject rightTarget = ur.getRightTarget();
			if (leftTarget != null && leftTarget != ur.getLeftElement()) {
				referencedEObjects.add(leftTarget);
			}
			if (rightTarget != null && rightTarget != ur.getRightElement()) {
				referencedEObjects.add(rightTarget);
			}
		}
		return referencedEObjects;
	}

	/**
	 * Checks if the given difference {@link dest} is a good candidate to be
	 * added as required difference the specified difference {@link origin}. A
	 * good candidate is a {@link ModelElementChange} which is not an
	 * {@link AbstractDiffExtension}.
	 * 
	 * @param origin
	 *            The difference to fill.
	 * @param dest
	 *            The difference candidate.
	 */
	private static void linkDifferences(final DiffElement origin,
			final EObject dest) {
		if (dest instanceof ModelElementChange
				&& !(dest instanceof AbstractDiffExtension)) {
			final ModelElementChange mec = (ModelElementChange) dest;
			// origin.getRequires().add(mec);
			/*
			 * In the case of UpdateReference differences, we've set the left
			 * and/or right target to an unmatched value. We need to null out
			 * that value now in order not to merge it and use the result of the
			 * ModelElementChange merging.
			 */
			if (origin instanceof UpdateReference) {
				final UpdateReference updateDiff = (UpdateReference) origin;
				final EObject changedElement;
				if (mec instanceof ModelElementChangeLeftTarget) {
					changedElement = ((ModelElementChangeLeftTarget) mec)
							.getLeftElement();
				} else if (mec instanceof ModelElementChangeRightTarget) {
					changedElement = ((ModelElementChangeRightTarget) mec)
							.getRightElement();
				} else {
					changedElement = null;
				}
				if (updateDiff.getLeftTarget() == changedElement) {
					updateDiff.setLeftTarget(null);
				} else if (updateDiff.getRightTarget() == changedElement) {
					updateDiff.setRightTarget(null);
				}
			}
		}
	}

	/**
	 * Create the comparsion resource snapshot combining the match and diff
	 * references as done by the default emf compare.
	 * 
	 * @param matchModel
	 *            matched model generated by EMF Compare
	 * @param diffModel
	 *            diff model generated by EMF Compare
	 * @return The complete comparsion snapshot.
	 */
	private ComparisonResourceSetSnapshot buildResourceSetComparisionSnapshot(
			MatchResourceSet matchResourceSet, DiffResourceSet diffSet) {
		final ComparisonResourceSetSnapshot snapshot = DiffFactory.eINSTANCE
				.createComparisonResourceSetSnapshot();
		snapshot.setDate(Calendar.getInstance().getTime());
		snapshot.setMatchResourceSet(matchResourceSet);
		snapshot.setDiffResourceSet(diffSet);
		return snapshot;
	}

	/**
	 * Loads an Ecore model from the supplied file
	 * 
	 * @param modelFiles
	 *            List of models to load
	 * @return model resource set instance
	 * @throws IOException
	 *             possible load exception
	 */
	private ResourceSet loadModelResourceSet(List<File> modelFiles)
			throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		for (File modelFile : modelFiles) {
			if (!modelFile.canRead()) {
				throw new IllegalArgumentException("cannot read model file "
						+ modelFile.getAbsolutePath());
			}
			URI fileUri = URI.createFileURI(modelFile.getPath());
			Resource resource = resourceSet.createResource(fileUri);
			resource.load(Collections.emptyMap());
		}

		return resourceSet;
	}
}

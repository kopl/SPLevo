package org.splevo.diffing.postprocessor;

import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.util.DiffSwitch;

/**
 * A post processor to analyze a diff model to identify specific changes.
 * 
 * @author Benjamin Klatt
 *
 */
public class DiffModelPostProcessor extends DiffSwitch<DiffElement>{
	
	/** The diff model to work with in the switch. */
	private DiffModel enhancedDiffModel = null;
	
	/** Constructor initializing an empty enhanced diff model */
	public DiffModelPostProcessor() {
		enhancedDiffModel = DiffFactory.eINSTANCE.createDiffModel();
	}

	/**
	 * Process the difference model.
	 * 
	 * @param diffModel The original Diff model.
	 * @return The enhanced DiffModel.
	 */
	public DiffModel process(DiffModel diffModel) {
		
		// set the references to the original models
		enhancedDiffModel.getLeftRoots().addAll(diffModel.getLeftRoots());
		enhancedDiffModel.getRightRoots().addAll(diffModel.getRightRoots());
		enhancedDiffModel.getAncestorRoots().addAll(diffModel.getAncestorRoots());
		
		for (DiffElement diffElement : diffModel.getDifferences()) {
			doSwitch(diffElement);
		}
		
		return diffModel;
	}
	
	@Override
	public DiffElement caseDiffElement(DiffElement object) {
		
		this.enhancedDiffModel.getDifferences().add(object);
		
		return super.caseDiffElement(object);
	}

}

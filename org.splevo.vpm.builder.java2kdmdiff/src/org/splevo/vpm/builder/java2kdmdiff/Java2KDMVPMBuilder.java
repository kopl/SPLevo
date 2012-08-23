package org.splevo.vpm.builder.java2kdmdiff;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.gmt.modisco.java.Model;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

public class Java2KDMVPMBuilder {
	
    private Logger logger = Logger.getLogger(Java2KDMVPMBuilder.class);
	
	
	/**
	 * Build a new VariationPointModel based on a DiffModel.
	 * 
	 * The provided diff model should be the result of a JavaModel diffing.
	 * This will be checked by the builder and null will be returned if 
	 * this is not valid.
	 * 
	 * @param diffModel The diff model to interpret.
	 * @return The resulting VariationPointModel or null if the DiffModel is not a about a modisco JavaModel.
	 * 
	 */
	public VariationPointModel buildVPM(DiffModel diffModel) {
		
		if(!checkDiffModelIsValid(diffModel)){
			return null;
		}
		
		VariationPointModel vpm = initVPM(diffModel);
		
		// visit the difference tree
		Java2KDMDiffVisitor java2KDMDiffVisitor = new Java2KDMDiffVisitor();
		for(DiffElement diffElement : diffModel.getDifferences()){
			VariationPoint vp = java2KDMDiffVisitor.doSwitch(diffElement);
			if(vp != null){
				vpm.getVariationPoints().add(vp);
			} else {
				logger.info("null VariationPoint created");
			}
		}
		
		return vpm;
		
		
	}

	/**
	 * Init the instance of the variation point model.
	 * 
	 * @param diffModel The diff model to get the diffed models from.
	 * @return The initialized variation point model.
	 */
	private VariationPointModel initVPM(DiffModel diffModel) {
		
		// assume the diff models have only one root
		Model leadingModel = (Model) diffModel.getRightRoots().get(0);
		Model integrationModel = (Model) diffModel.getLeftRoots().get(0);
		
		VariationPointModel vpm = variabilityFactory.eINSTANCE.createVariationPointModel();
		vpm.setLeadingModel(leadingModel);
		vpm.setIntegrationModel(integrationModel);
		
		return vpm;
	}

	/**
	 * Check that the DiffModel is a valid input.
	 * This means, that the diffed models must be modisco discovered java models.
	 * 
	 * @param diffModel The diff model to check
	 * @return true/false depending whether the diff model is valid.
	 */
	private boolean checkDiffModelIsValid(DiffModel diffModel) {
		
		// check that one left and one right root is available
		if(diffModel.getLeftRoots().size() != 1){
			//("There is more than one left root model");
			return false; 
		}
		if(diffModel.getRightRoots().size() != 1){
			// throw new IllegalArgumentException("There is more than one right root model");
			return false;
		}

		if(!(diffModel.getLeftRoots().get(0) instanceof Model)){
			return false;
			//throw new IllegalArgumentException("The left root model is not a modisco JavaModel");
		}
		if(!(diffModel.getRightRoots().get(0) instanceof Model)){
			return false;
			//throw new IllegalArgumentException("The left root model is not a modisco JavaModel");
		}
		
		return true;
	}

}

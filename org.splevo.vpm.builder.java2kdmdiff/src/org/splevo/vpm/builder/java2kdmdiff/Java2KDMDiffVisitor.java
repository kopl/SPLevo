package org.splevo.vpm.builder.java2kdmdiff;

import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange;
import org.splevo.diffing.emfcompare.java2kdmdiff.util.Java2KDMDiffSwitch;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * A visitor for diff elements to fill up a variation point mode.
 */
class Java2KDMDiffVisitor  extends Java2KDMDiffSwitch<VariationPoint>{

	/**
	 * Handle import inserts.
	 * VP references the compilation unit.
	 * The leading variant references the inserted import declaration.
	 * 
	 * @param importInsert The import insert diff element.
	 * @return The prepared variation point.
	 */
	@Override
	public VariationPoint caseImportInsert(ImportInsert importInsert) {

		ImportDeclaration importDeclaration = importInsert.getImportLeft();
		CompilationUnit parent = importDeclaration.getOriginalCompilationUnit(); 
		
		// create the variation point
		VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
		variationPoint.setSoftwareEntity((ASTNode) parent);

		// create the variants
		Variant integrationVariant = null;
		integrationVariant = variabilityFactory.eINSTANCE.createVariant();
		integrationVariant.getSoftwareEntities().add(importDeclaration);
		integrationVariant.setLeading(Boolean.FALSE);
		variationPoint.getVariants().add(integrationVariant);

		// return the result
		return variationPoint;

	}
	
	/**
	 * Handle import deletes.
	 * VP references the compilation unit.
	 * The leading variant references the deleted import declaration.
	 * 
	 * @param importDelete The import delete diff element.
	 * @return The prepared variation point.
	 */
	@Override
	public VariationPoint caseImportDelete(ImportDelete importDelete) {

		ImportDeclaration importDeclaration = importDelete.getImportRight();
		CompilationUnit parent = importDeclaration.getOriginalCompilationUnit(); 
		
		// create the variation point
		VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
		variationPoint.setSoftwareEntity((ASTNode) parent);

		// create the variants
		Variant leadingVariant = null;
		leadingVariant = variabilityFactory.eINSTANCE.createVariant();
		leadingVariant.getSoftwareEntities().add(importDeclaration);
		leadingVariant.setLeading(Boolean.TRUE);
		variationPoint.getVariants().add(leadingVariant);

		// return the result
		return variationPoint;
	}
	
	@Override
	public VariationPoint caseStatementChange(StatementChange statementChange) {

		Statement statement = statementChange.getStatementRight();
		ASTNode parent = (ASTNode) statement.eContainer();
		
		// create the variation point
		VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
		variationPoint.setSoftwareEntity(parent);

		// create the leading variant
		if(!DifferenceKind.ADDITION.equals(statementChange.getKind())){
			Variant leadingVariant = variabilityFactory.eINSTANCE.createVariant();
			leadingVariant.getSoftwareEntities().add(statement);
			leadingVariant.setLeading(Boolean.TRUE);
			variationPoint.getVariants().add(leadingVariant);
		}
		
		// create the variant to integrate
		if(!DifferenceKind.DELETION.equals(statementChange.getKind())){
			Variant integrationVariant = variabilityFactory.eINSTANCE.createVariant();
			integrationVariant.getSoftwareEntities().add(statementChange.getStatementLeft());
			integrationVariant.setLeading(Boolean.FALSE);
			variationPoint.getVariants().add(integrationVariant);
		}

		// return the result
		return variationPoint;
	}
}
package org.splevo.vpm.builder.java2kdmdiff;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.Statement;
import org.splevo.diffing.emfcompare.java2kdmdiff.ClassInsert;
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
class Java2KDMDiffVisitor extends Java2KDMDiffSwitch<VariationPoint> {

    /** The logger used by this class. */
    private Logger logger = Logger.getLogger(Java2KDMDiffVisitor.class);

    /**
     * Handle import inserts. VP references the compilation unit. The leading variant references the
     * inserted import declaration.
     * 
     * @param importInsert
     *            The import insert diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseImportInsert(ImportInsert importInsert) {

        ImportDeclaration importDeclaration = importInsert.getImportLeft();
        CompilationUnit parent = importDeclaration.getOriginalCompilationUnit();

        // create the variation point
        VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
        variationPoint.setEnclosingSoftwareEntity((ASTNode) parent);

        // create the variants
        Variant integrationVariant = null;
        integrationVariant = variabilityFactory.eINSTANCE.createVariant();
        integrationVariant.getSoftwareEntities().add(importDeclaration);
        integrationVariant.setLeading(Boolean.FALSE);
        integrationVariant.setVariantId("Import " + importDeclaration.getImportedElement().getName());
        variationPoint.getVariants().add(integrationVariant);

        // return the result
        return variationPoint;

    }
    
    /**
     * Handle class inserts. VP references the package. The leading variant references the
     * inserted import declaration.
     * 
     * @param classInsert
     *            The class insert diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseClassInsert(ClassInsert classInsert) {

        ClassDeclaration classDeclaration = classInsert.getClassLeft();
        Package parent = classDeclaration.getPackage();

        // create the variation point
        VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
        variationPoint.setEnclosingSoftwareEntity((ASTNode) parent);

        // create the variants
        Variant integrationVariant = null;
        integrationVariant = variabilityFactory.eINSTANCE.createVariant();
        integrationVariant.getSoftwareEntities().add(classDeclaration);
        integrationVariant.setLeading(Boolean.FALSE);
        integrationVariant.setVariantId("Class " + classDeclaration.getName());
        variationPoint.getVariants().add(integrationVariant);

        // return the result
        return variationPoint;
    }

    /**
     * Handle import deletes. VP references the compilation unit. The leading variant references the
     * deleted import declaration.
     * 
     * @param importDelete
     *            The import delete diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseImportDelete(ImportDelete importDelete) {

        ImportDeclaration importDeclaration = importDelete.getImportRight();
        //CompilationUnit parent = importDeclaration.getOriginalCompilationUnit();
        CompilationUnit parent = importDelete.getLeftContainer();

        // create the variation point
        VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
        variationPoint.setEnclosingSoftwareEntity((ASTNode) parent);

        // create the variants
        Variant leadingVariant = null;
        leadingVariant = variabilityFactory.eINSTANCE.createVariant();
        leadingVariant.getSoftwareEntities().add(importDeclaration);
        leadingVariant.setLeading(Boolean.TRUE);
        leadingVariant.setVariantId("Import " + importDeclaration.getImportedElement().getName());
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
        variationPoint.setEnclosingSoftwareEntity(parent);

        // create the leading variant
        if (!DifferenceKind.DELETION.equals(statementChange.getKind())) {
            Variant leadingVariant = variabilityFactory.eINSTANCE.createVariant();
            leadingVariant.getSoftwareEntities().add(statementChange.getStatementLeft());
            leadingVariant.setLeading(Boolean.TRUE);
            leadingVariant.setVariantId("Statement Right");
            variationPoint.getVariants().add(leadingVariant);
        }

        // create the variant to integrate
        if (!DifferenceKind.ADDITION.equals(statementChange.getKind())) {
            Variant integrationVariant = variabilityFactory.eINSTANCE.createVariant();
            integrationVariant.getSoftwareEntities().add(statement);
            integrationVariant.setLeading(Boolean.FALSE);
            integrationVariant.setVariantId("Statement Left");
            variationPoint.getVariants().add(integrationVariant);
        }

        // return the result
        return variationPoint;
    }

    @Override
    public VariationPoint defaultCase(EObject object) {
        logger.warn("Unhandled DiffType: " + object.getClass());
        return null;
    }
}
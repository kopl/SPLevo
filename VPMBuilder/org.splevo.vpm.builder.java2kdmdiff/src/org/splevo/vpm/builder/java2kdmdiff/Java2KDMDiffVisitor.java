package org.splevo.vpm.builder.java2kdmdiff;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.Statement;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.util.Java2KDMDiffSwitch;
import org.splevo.modisco.java.vpm.software.MoDiscoJavaSoftwareElement;
import org.splevo.modisco.java.vpm.software.softwareFactory;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

/**
 * A visitor for diff elements to fill up a variation point mode.
 */
class Java2KDMDiffVisitor extends Java2KDMDiffSwitch<VariationPoint> {

    /** The logger used by this class. */
    private Logger logger = Logger.getLogger(Java2KDMDiffVisitor.class);

    /** The id to set for leading variants. */
    private String variantIDLeading = null;

    /** The id to set for integration variants. */
    private String variantIDIntegration = null;

    private VariationPointModel vpm = null;

    /**
     * Constructor to set the variant ids.
     * 
     * @param variantIDLeading
     *            The id for the leading variants.
     * @param variantIDIntegration
     *            The id for the integration variants.
     * @param vpm
     *            The variation point model to add new software element nodes to.
     */
    public Java2KDMDiffVisitor(String variantIDLeading, String variantIDIntegration, VariationPointModel vpm) {
        this.variantIDIntegration = variantIDIntegration;
        this.variantIDLeading = variantIDLeading;
        this.vpm = vpm;
    }

    /**
     * Handle import inserts. VP references the compilation unit. The leading variant references the
     * inserted import declaration.
     * 
     * @param packageInsert
     *            The import insert diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint casePackageInsert(PackageInsert packageInsert) {

        Package packageLeft = packageInsert.getPackageLeft();
        Package parent = packageLeft.getPackage();

        return createVariationPointInsert(packageLeft, parent);
    }

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

        return createVariationPointInsert(importDeclaration, parent);
    }

    /**
     * Handle class inserts. VP references the package. The leading variant references the inserted
     * import declaration.
     * 
     * @param classInsert
     *            The class insert diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseClassInsert(ClassInsert classInsert) {

        ClassDeclaration classDeclaration = classInsert.getClassLeft();
        Package parent = classDeclaration.getPackage();

        return createVariationPointInsert(classDeclaration, parent);
    }

    /**
     * Handle field inserts. VP references the enclosing class. The integration variant references
     * the inserted import declaration.
     * 
     * @param fieldInsert
     *            The field insert diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseFieldInsert(FieldInsert fieldInsert) {

        FieldDeclaration fieldLeft = fieldInsert.getFieldLeft();
        AbstractTypeDeclaration parent = fieldLeft.getAbstractTypeDeclaration();

        return createVariationPointInsert(fieldLeft, parent);
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
        CompilationUnit parent = importDelete.getLeftContainer();

        return createVariationPointDelete(importDeclaration, parent);
    }

    /**
     * Handle package deletes.<br>
     * VP references the parent package.<br>
     * The leading variant references the deleted package declaration.
     * 
     * @param packageDelete
     *            The package delete diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint casePackageDelete(PackageDelete packageDelete) {

        Package packageDeclaration = packageDelete.getPackageRight();
        Package parent = packageDelete.getLeftContainer();

        return createVariationPointDelete(packageDeclaration, parent);
    }

    /**
     * Handle class deletes.<br>
     * VP references the parent package.<br>
     * The leading variant references the deleted class declaration.
     * 
     * @param classDelete
     *            The class delete diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseClassDelete(ClassDelete classDelete) {

        ClassDeclaration classDeclaration = classDelete.getClassRight();
        Package parent = classDelete.getLeftContainer();

        return createVariationPointDelete(classDeclaration, parent);
    }

    /**
     * Handle field deletes.<br>
     * VP references the containing type which can be either an AbstractTypeDeclaration or an
     * AnonymousTypeDeclaration according to field declaration element's container definition.<br>
     * The leading variant references the deleted field declaration.
     * 
     * @param fieldDelete
     *            The field delete diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseFieldDelete(FieldDelete fieldDelete) {

        FieldDeclaration fieldDeclaration = fieldDelete.getFieldRight();
        ASTNode parent = fieldDelete.getLeftContainer();

        return createVariationPointDelete(fieldDeclaration, parent);
    }

    /**
     * Handle method deletes.<br>
     * VP references the containing type.<br>
     * The leading variant references the deleted method declaration.
     * 
     * @param methodDelete
     *            The method delete diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseMethodDelete(MethodDelete methodDelete) {

        AbstractMethodDeclaration methodDeclaration = methodDelete.getMethodRight();
        ASTNode parent = methodDelete.getLeftContainer();

        return createVariationPointDelete(methodDeclaration, parent);
    }

    /**
     * Create a variation point for a new method that was inserted.
     * 
     * It is assumed that the method insert references a "left" method which is contained in an AST
     * node.
     * 
     * @param methodInsert
     *            The method insert change,
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseMethodInsert(MethodInsert methodInsert) {

        AbstractMethodDeclaration methodDeclaration = methodInsert.getMethodLeft();
        AbstractTypeDeclaration parent = methodDeclaration.getAbstractTypeDeclaration();

        return createVariationPointInsert(methodDeclaration, parent);
    }

    /**
     * Handle an inserted statement change by creating a variation point describing this change.
     * 
     * @param statementInsert
     *            The diff element to handle.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseStatementInsert(StatementInsert statementInsert) {

        Statement statement = statementInsert.getStatementLeft();
        ASTNode parent = (ASTNode) statement.eContainer();

        return createVariationPointInsert(statement, parent);
    }

    /**
     * Handle a deleted statement change by creating a variation point describing this change.
     * 
     * @param statementDelete
     *            The diff element to handle.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseStatementDelete(StatementDelete statementDelete) {

        Statement statement = statementDelete.getStatementRight();
        ASTNode parent = statementDelete.getLeftContainer();

        return createVariationPointDelete(statement, parent);
    }

    /**
     * Handle a field declaration change by creating a variation point describing this change.
     * 
     * @param fieldDeclChange
     *            The diff element to handle.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseFieldDeclarationChange(FieldDeclarationChange fieldDeclChange) {

        FieldDeclaration fieldDeclLeft = fieldDeclChange.getFieldLeft();
        FieldDeclaration fieldDeclRight = fieldDeclChange.getFieldRight();
        AbstractTypeDeclaration parent = fieldDeclLeft.getAbstractTypeDeclaration();

        return createVariationPointChange(fieldDeclLeft, fieldDeclRight, parent);
    }

    /**
     * Handle a statement change by creating a variation point describing this change.
     * 
     * @param statementChange
     *            The diff element to handle.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseStatementChange(StatementChange statementChange) {

        Statement statementLeft = statementChange.getStatementLeft();
        Statement statementRight = statementChange.getStatementRight();
        ASTNode parent = (ASTNode) statementLeft.eContainer();

        return createVariationPointChange(statementLeft, statementRight, parent);
    }

    /**
     * Handle an enumeration declaration change by creating a variation point describing this
     * change.
     * 
     * @param enumDeclChange
     *            The diff element to handle.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseEnumDeclarationChange(EnumDeclarationChange enumDeclChange) {

        EnumDeclaration enumDeclLeft = enumDeclChange.getEnumLeft();
        EnumDeclaration enumDeclRight = enumDeclChange.getEnumRight();
        AbstractTypeDeclaration parent = enumDeclLeft.getAbstractTypeDeclaration();

        return createVariationPointChange(enumDeclLeft, enumDeclRight, parent);
    }

    @Override
    public VariationPoint defaultCase(EObject object) {
        logger.error("Unhandled DiffType: " + object.getClass());
        return null;
    }

    /**
     * Create a new variation point for an inserted element.
     * 
     * @param newElement
     *            The new ast element.
     * @param parentNode
     *            The parent node the change was made in.
     * @return The prepared variation point.
     */
    private VariationPoint createVariationPointInsert(ASTNode newElement, ASTNode parentNode) {

        // create the variation point
        VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();

        MoDiscoJavaSoftwareElement enclosingSoftwareElement = createSoftwareElement(parentNode);
        variationPoint.setEnclosingSoftwareEntity(enclosingSoftwareElement);

        // create the variants
        Variant integrationVariant = createVariant(newElement, false, variantIDIntegration);
        variationPoint.getVariants().add(integrationVariant);

        return variationPoint;
    }

    /**
     * Create a new variation point for a deleted element.
     * 
     * @param element
     *            The deleted ast element.
     * @param enclosingASTNode
     *            The parent node the change was made in.
     * @return The prepared variation point.
     */
    private VariationPoint createVariationPointDelete(ASTNode element, ASTNode enclosingASTNode) {

        MoDiscoJavaSoftwareElement enclosingSoftwareElement = createSoftwareElement(enclosingASTNode);

        // create the variation point
        VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
        variationPoint.setEnclosingSoftwareEntity(enclosingSoftwareElement);

        // create the variants
        Variant leadingVariant = createVariant(element, true, variantIDLeading);
        variationPoint.getVariants().add(leadingVariant);

        return variationPoint;
    }

    /**
     * Factory method for a variation point describing a program change.
     * 
     * @param astNodeLeft
     *            The ast node of the left variant.
     * @param astNodeRight
     *            The ast node of the right variant.
     * @param parent
     *            The parent ast node containing the change.
     * @return The initialized variation point.
     */
    private VariationPoint createVariationPointChange(ASTNode astNodeLeft, ASTNode astNodeRight, ASTNode parent) {

        MoDiscoJavaSoftwareElement enclosingSoftwareElement = createSoftwareElement(parent);

        // create the variation point
        VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
        variationPoint.setEnclosingSoftwareEntity(enclosingSoftwareElement);

        // create the integration variant
        Variant integrationVariant = createVariant(astNodeLeft, false, variantIDIntegration);
        variationPoint.getVariants().add(integrationVariant);

        // create the leading variant
        Variant leadingVariant = createVariant(astNodeRight, true, variantIDLeading);
        variationPoint.getVariants().add(leadingVariant);
        return variationPoint;
    }

    /**
     * Factory method to create an initialized variant element.
     * 
     * @param astNode
     *            The element to be linked with the variant.
     * @param leading
     *            The flag whether this variant is a leading one.
     * @param variantID
     *            The id of the product variant this one belongs to.
     * @return The prepared variant object.
     */
    private Variant createVariant(ASTNode astNode, Boolean leading, String variantID) {

        MoDiscoJavaSoftwareElement softwareElement = createSoftwareElement(astNode);

        Variant integrationVariant = null;
        integrationVariant = variabilityFactory.eINSTANCE.createVariant();
        integrationVariant.getSoftwareEntities().add(softwareElement);
        integrationVariant.setLeading(leading);
        integrationVariant.setVariantId(variantID);
        return integrationVariant;
    }

    /**
     * Create a software element wrapping an ASTNode.
     * 
     * @param astNode
     *            The ASTNode to link.
     * @return The prepared software element.
     */
    private MoDiscoJavaSoftwareElement createSoftwareElement(ASTNode astNode) {
        MoDiscoJavaSoftwareElement element = softwareFactory.eINSTANCE.createMoDiscoJavaSoftwareElement();
        vpm.getSoftwareElements().add(element);
        element.setAstNode(astNode);
        return element;
    }
}
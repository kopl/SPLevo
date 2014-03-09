package org.splevo.modisco.java.vpm.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.modisco.java.diffing.java2kdmdiff.ClassChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImportChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.MethodChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.PackageChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;
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

    /** The leading model to set in software elements. */
    private JavaApplication leadingModel = null;

    /** The integration model to set in software elements. */
    private JavaApplication integrationModel = null;

    /** The id to set for leading variants. */
    private String variantIDLeading = null;

    /** The id to set for integration variants. */
    private String variantIDIntegration = null;

    /** The variation point model to fill. */
    private VariationPointModel vpm = null;

    /** The comparison model to read information from. */
    private Comparison comparison = null;

    /** Registry to store already created software elements. */
    private Map<ASTNode, MoDiscoJavaSoftwareElement> elementRegistry = new LinkedHashMap<ASTNode, MoDiscoJavaSoftwareElement>();

    /**
     * Constructor to set the variant ids.
     *
     * @param leadingModel
     *            the comprehensive model referencing leading ASTNodes and their source locations.
     * @param integrationModel
     *            the comprehensive model referencing integration ASTNodes and their source
     *            locations.
     * @param variantIDLeading
     *            The id for the leading variants.
     * @param variantIDIntegration
     *            The id for the integration variants.
     * @param vpm
     *            The variation point model to add new software element nodes to.
     * @param comparison
     *            The comparison model to use for matches etc.
     */
    public Java2KDMDiffVisitor(JavaApplication leadingModel, JavaApplication integrationModel, String variantIDLeading,
            String variantIDIntegration, VariationPointModel vpm, Comparison comparison) {
        this.leadingModel = leadingModel;
        this.integrationModel = integrationModel;
        this.variantIDIntegration = variantIDIntegration;
        this.variantIDLeading = variantIDLeading;
        this.vpm = vpm;
        this.comparison = comparison;
    }

    /**
     * Handle import inserts. VP references the compilation unit. The leading variant references the
     * inserted import declaration.
     *
     * @param change
     *            The package change to process.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint casePackageChange(PackageChange change) {
        Package changedElement = change.getChangedPackage();
        return buildKindSpecificVariationPoint(change, changedElement);
    }

    /**
     * Handle import changes. VP references the compilation unit. The leading variant references the
     * inserted import declaration.
     *
     * @param change
     *            The import change diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseImportChange(ImportChange change) {
        ImportDeclaration changedElement = change.getChangedImport();
        return buildKindSpecificVariationPoint(change, changedElement);
    }

    /**
     * Handle class changes.
     *
     * @param change
     *            The class change diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseClassChange(ClassChange change) {
        ClassDeclaration changedElement = change.getChangedClass();
        return buildKindSpecificVariationPoint(change, changedElement);
    }

    /**
     * Handle field inserts. VP references the enclosing class. The integration variant references
     * the inserted import declaration.
     *
     * @param change
     *            The field change diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseFieldChange(FieldChange change) {
        FieldDeclaration changedElement = change.getChangedField();
        return buildKindSpecificVariationPoint(change, changedElement);
    }

    /**
     * Handle method changes.<br>
     * VP references the changed method.<br>
     *
     * @param change
     *            The method change diff element.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseMethodChange(MethodChange change) {
        AbstractMethodDeclaration changedElement = change.getChangedMethod();
        return buildKindSpecificVariationPoint(change, changedElement);
    }

    /**
     * Handle a statement change by creating a variation point describing this change.
     *
     * @param change
     *            The diff element to handle.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseStatementChange(StatementChange change) {
        Statement changedElement = change.getChangedStatement();
        return buildKindSpecificVariationPoint(change, changedElement);
    }

    /**
     * Handle an enumeration declaration change by creating a variation point describing this
     * change.
     *
     * @param change
     *            The diff element to handle.
     * @return The prepared variation point.
     */
    @Override
    public VariationPoint caseEnumChange(EnumChange change) {
        EnumDeclaration changedElement = change.getChangedEnum();
        return buildKindSpecificVariationPoint(change, changedElement);
    }

    @Override
    public VariationPoint defaultCase(EObject object) {
        logger.error("Unhandled DiffType: " + object.getClass());
        return null;
    }

    /**
     * Build a variation point according to the change kind of the provided Change.
     *
     * @param diff
     *            The Diff element to build the VP for.
     * @param changedASTNode
     *            The changed software AST node.
     * @return The variation point for the provided Diff.
     */
    private VariationPoint buildKindSpecificVariationPoint(Diff diff, ASTNode changedASTNode) {
        ASTNode parent = getParentNode(changedASTNode);

        if(parent == null) {
        	logger.warn("No parent node identified (changedASTNode: " + changedASTNode + ", diff: " + diff + ")");
        	return null;
        }

        switch (diff.getKind()) {
        case ADD:
            return createVariationPointInsert(changedASTNode, parent);

        case DELETE:
            return createVariationPointDelete(changedASTNode, parent);

        case CHANGE:
            return createVariationPointChange(changedASTNode, parent);

        default:
            logger.error("Unhandled change: " + diff);
            return null;
        }
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

        MoDiscoJavaSoftwareElement enclosingSoftwareElement = createSoftwareElement(parentNode, true);
        variationPoint.setLocation(enclosingSoftwareElement);

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

        MoDiscoJavaSoftwareElement enclosingSoftwareElement = createSoftwareElement(enclosingASTNode, true);

        // create the variation point
        VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
        variationPoint.setLocation(enclosingSoftwareElement);

        // create the variants
        Variant leadingVariant = createVariant(element, true, variantIDLeading);
        variationPoint.getVariants().add(leadingVariant);

        return variationPoint;
    }

    /**
     * Create a variation point for a CHANGE with an existing left and right side.
     *
     * @param changedElement
     *            The changed model element.
     * @param parent
     *            The parent ASTNode of the change.
     * @return The new VariationPoint.
     */
    private VariationPoint createVariationPointChange(EObject changedElement, ASTNode parent) {
        Match match = comparison.getMatch(changedElement);
        ASTNode astNodeLeft = (ASTNode) match.getLeft();
        ASTNode astNodeRight = (ASTNode) match.getRight();
        return createVariationPointChange(astNodeLeft, astNodeRight, parent);
    }

    /**
     * Factory method for a variation point describing a program change.
     *
     * @param astNodeLeft
     *            The AST node of the left variant.
     * @param astNodeRight
     *            The AST node of the right variant.
     * @param parent
     *            The parent AST node containing the change.
     * @return The initialized variation point.
     */
    private VariationPoint createVariationPointChange(ASTNode astNodeLeft, ASTNode astNodeRight, ASTNode parent) {

        MoDiscoJavaSoftwareElement enclosingSoftwareElement = createSoftwareElement(parent, true);

        // create the variation point
        VariationPoint variationPoint = variabilityFactory.eINSTANCE.createVariationPoint();
        variationPoint.setLocation(enclosingSoftwareElement);

        // create the integration variant
        Variant integrationVariant = createVariant(astNodeLeft, false, variantIDIntegration);
        variationPoint.getVariants().add(integrationVariant);

        // create the leading variant
        Variant leadingVariant = createVariant(astNodeRight, true, variantIDLeading);
        variationPoint.getVariants().add(leadingVariant);
        return variationPoint;
    }

    /**
     * Get the parent node for an AST node.
     * Using the comparison model, the match for the parent
     * element will be searched and if available the right side will be used,
     * the left side as fall back.
     *
     * @param astNode
     *            The {@link Diff} to get the parent for.
     * @return The most reasonable parent AST node.
     */
    private ASTNode getParentNode(ASTNode astNode) {

        Match parentMatch = comparison.getMatch(astNode.eContainer());

        EObject parent = null;
        if (parentMatch.getRight() != null) {
            parent = parentMatch.getRight();
        } else {
            parent = parentMatch.getLeft();
        }

        if(parent instanceof Block && parent.eContainer() instanceof AbstractMethodDeclaration){
        	return (ASTNode) parent.eContainer();
        }

        if(parent instanceof ASTNode) {
        	return (ASTNode) parent;
        } else {
        	logger.warn("Diff with a non-ASTNode parent detected. Diff child: " + astNode);
        	return null;
        }
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

        MoDiscoJavaSoftwareElement softwareElement = createSoftwareElement(astNode, leading);

        Variant integrationVariant = null;
        integrationVariant = variabilityFactory.eINSTANCE.createVariant();
        integrationVariant.getImplementingElements().add(softwareElement);
        integrationVariant.setLeading(leading);
        integrationVariant.setVariantId(variantID);
        return integrationVariant;
    }

    /**
     * Create a software element wrapping an ASTNode.
     *
     * Uses a registry map to prevent creating multiple SoftwareElements for the same ASTNode.
     *
     * @param astNode
     *            The ASTNode to link.
     * @param leading
     *            flag whether to set the leading or integration model.
     * @return The prepared software element.
     */
    private MoDiscoJavaSoftwareElement createSoftwareElement(ASTNode astNode, boolean leading) {

        if (elementRegistry.containsKey(astNode)) {
            return elementRegistry.get(astNode);
        }

        MoDiscoJavaSoftwareElement element = softwareFactory.eINSTANCE.createMoDiscoJavaSoftwareElement();
        if (leading) {
            element.setJavaApplicationModel(leadingModel);
        } else {
            element.setJavaApplicationModel(integrationModel);
        }
        element.setAstNode(astNode);
        vpm.getSoftwareElements().add(element);
        elementRegistry.put(astNode, element);

        return element;
    }
}
package org.splevo.modisco.java.diffing.postprocessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.engine.IMatchManager.MatchSide;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.util.DiffSwitch;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.DoStatement;
import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ForStatement;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.SwitchStatement;
import org.eclipse.gmt.modisco.java.TryStatement;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.WhileStatement;
import org.splevo.modisco.java.diffing.java2kdmdiff.EnumDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.FieldDeclarationChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.ImplementsInterfaceInsert;
import org.splevo.modisco.java.diffing.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementChange;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementDelete;
import org.splevo.modisco.java.diffing.java2kdmdiff.StatementInsert;
import org.splevo.modisco.java.diffing.util.DiffElementFactory;

/**
 * A post processor to analyze a diff model to identify specific changes.
 * 
 * @author Benjamin Klatt
 * 
 */
public class DiffModelPostProcessor extends DiffSwitch<Boolean> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(DiffModelPostProcessor.class);

    /** The match manager. */
    private IMatchManager matchManager = null;

    /**
     * Constructor initializing an empty enhanced diff model.
     * 
     * @param matchManager
     *            the match manager
     */
    public DiffModelPostProcessor(IMatchManager matchManager) {
        this.matchManager = matchManager;
    }

    /**
     * Process the difference model.
     * 
     * @param diffModel
     *            The original Diff model.
     */
    public void process(DiffModel diffModel) {

        List<EObject> filteredElements = new ArrayList<EObject>();

        TreeIterator<EObject> diffElementIterator = diffModel.eAllContents();
        while (diffElementIterator.hasNext()) {
            EObject diffElement = diffElementIterator.next();

            Boolean replaced = doSwitch(diffElement);

            // if the diff element has been processed
            // skip the rest of the subtree by invoking
            // prune on the tree iterator
            if (replaced == Boolean.TRUE) {
                diffElementIterator.prune();
                filteredElements.add(diffElement);
            }
        }

        // filter outdated elements afterwards
        // TODO: This filter should be implement more efficiently
        EList<DiffElement> subDiffElements = diffModel.getOwnedElements();
        filterEmptySubDiffGroups(subDiffElements, filteredElements);
    }

    /**
     * Filter recursively sub diff elements.
     * 
     * @param subDiffElements
     *            The sub diff elements to check.
     * @param filteredElements
     *            The list of elements to filter.
     */
    private void filterEmptySubDiffGroups(EList<DiffElement> subDiffElements, List<EObject> filteredElements) {
        List<EObject> postFilterElements = new ArrayList<EObject>();
        for (DiffElement subDiffElement : subDiffElements) {
            if (filteredElements.contains(subDiffElement)) {
                postFilterElements.add(subDiffElement);
            } else {
                filterEmptySubDiffGroups(subDiffElement.getSubDiffElements(), filteredElements);
            }
        }
        subDiffElements.removeAll(postFilterElements);
    }

    /**
     * Process the diff group elements.
     * 
     * If it is about a field declaration, convert it to a FieldDeclarationChange.
     * 
     * If it is about a statement element which is not a block convert it to a
     * StatementChangeElement.
     * 
     * @param diffGroup
     *            The diff group under study.
     * @return Flag if the diff group has been converted or not.
     */
    @Override
    public Boolean caseDiffGroup(DiffGroup diffGroup) {

        // no specific handling if switching the root.
        if (diffGroup.getRightParent() == null) {
            return Boolean.FALSE;
        }

        // handle changed enum declarations
        if (diffGroup.getRightParent() instanceof EnumDeclaration) {

            // Get left and right parent enumerations
            // The rightParent of the diff group might link to the left model in
            // case of only added
            // sub elements.
            // In this case switch the orientations
            EnumDeclaration enumDeclarationRight = null;
            EnumDeclaration enumDeclarationLeft = null;
            EObject leftMatch = matchManager.getMatchedEObject(diffGroup.getRightParent(), MatchSide.LEFT);
            if (leftMatch != null) {
                enumDeclarationRight = (EnumDeclaration) diffGroup.getRightParent();
                enumDeclarationLeft = (EnumDeclaration) leftMatch;
            } else {
                enumDeclarationRight = (EnumDeclaration) matchManager.getMatchedEObject(diffGroup.getRightParent(),
                        MatchSide.RIGHT);
                enumDeclarationLeft = (EnumDeclaration) diffGroup.getRightParent();
            }

            EnumDeclarationChange enumChange = Java2KDMDiffFactory.eINSTANCE.createEnumDeclarationChange();
            enumChange.setEnumRight(enumDeclarationRight);
            enumChange.setEnumLeft(enumDeclarationLeft);
            enumChange.getSubDiffElements().addAll(diffGroup.getSubDiffElements());

            return addToParentGroup(diffGroup, enumChange);
        }

        // handle changed field declarations
        if (diffGroup.getRightParent() instanceof FieldDeclaration) {
            FieldDeclaration fieldDeclarationRight = (FieldDeclaration) diffGroup.getRightParent();
            FieldDeclaration fieldDeclarationLeft = (FieldDeclaration) matchManager
                    .getMatchedEObject(fieldDeclarationRight);

            FieldDeclarationChange fieldChange = Java2KDMDiffFactory.eINSTANCE.createFieldDeclarationChange();
            fieldChange.setFieldRight(fieldDeclarationRight);
            fieldChange.setFieldLeft(fieldDeclarationLeft);
            fieldChange.getSubDiffElements().addAll(diffGroup.getSubDiffElements());

            return addToParentGroup(diffGroup, fieldChange);
        }

        // handle statement statements
        if (diffGroup.getRightParent() instanceof Statement) {
            if (isNotEnclosingStatement((Statement) diffGroup.getRightParent())) {

                EObject leftMatch = matchManager.getMatchedEObject(diffGroup.getRightParent(), MatchSide.LEFT);
                if (leftMatch == null) {
                    logger.error("left match is null for " + diffGroup.getRightParent());
                }

                StatementChange statementChange = Java2KDMDiffFactory.eINSTANCE.createStatementChange();
                Statement statementRight = (Statement) diffGroup.getRightParent();
                statementChange.setStatementRight(statementRight);
                Statement statementLeft = (Statement) leftMatch;
                statementChange.setStatementLeft(statementLeft);
                statementChange.getSubDiffElements().addAll(diffGroup.getSubDiffElements());

                return addToParentGroup(diffGroup, statementChange);
            }
        }

        return Boolean.FALSE;
    }

    /**
     * The inverted isEnclosingStatement(Statement statement).
     * 
     * @param statement
     *            The statement to check.
     * @return false/true if it's an enclosing statement or not.
     */
    private boolean isNotEnclosingStatement(Statement statement) {
        return !isEnclosingStatement(statement);
    }

    /**
     * Check if a statement is of a type that encapsulates multiple other statements.
     * 
     * @param statement
     *            The statement to check.
     * @return true/false if it's an enclosing statement or not.
     */
    private boolean isEnclosingStatement(Statement statement) {

        return (statement instanceof Block) || (statement instanceof IfStatement)
                || (statement instanceof ForStatement) || (statement instanceof TryStatement)
                || (statement instanceof CatchClause) || (statement instanceof WhileStatement)
                || (statement instanceof SwitchStatement) || (statement instanceof DoStatement)
                || (statement instanceof EnhancedForStatement);
    }

    /**
     * Add the statement change to the parent container.
     * 
     * @param diffElement
     *            The diffing element to get the diffing group to add the element to.
     * @param newDiffElement
     *            The new diffing element to add to the parent group.
     * @return True or false if the add was successful.
     */
    private Boolean addToParentGroup(DiffElement diffElement, DiffElement newDiffElement) {
        if (diffElement.eContainer() instanceof DiffGroup) {
            DiffGroup parentGroup = (DiffGroup) diffElement.eContainer();
            parentGroup.getSubDiffElements().add(newDiffElement);

            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Handle complete model element changes.
     * 
     * LeftTarget means additions to the new model.
     * 
     * Supported types of model element changes
     * <ul>
     * <li>a reference to a changed line comment</li>
     * </ul>
     * 
     * @param diffElement
     *            the model change to analyze
     * @return True/False whether the element has been replaced or not.
     */
    @Override
    public Boolean caseModelElementChangeLeftTarget(ModelElementChangeLeftTarget diffElement) {

        if (diffElement.getKind() == DifferenceKind.ADDITION) {

            if (handleImplementsInterfaceInsert(diffElement)) {
                return Boolean.TRUE;
            }

            if (handleForStatementExpression(diffElement)) {
                return Boolean.TRUE;
            }

        }

        return super.caseModelElementChangeLeftTarget(diffElement);
    }

    /**
     * Handle complete model element changes.
     * 
     * LeftTarget means additions to the new model.
     * 
     * Supported types of model element changes
     * <ul>
     * <li>a reference to a changed line comment</li>
     * </ul>
     * 
     * @param diffElement
     *            the model change to analyze
     * @return True/False whether the element has been replaced or not.
     */
    @Override
    public Boolean caseModelElementChangeRightTarget(ModelElementChangeRightTarget diffElement) {

        if (diffElement.getKind() == DifferenceKind.DELETION) {

            if (handleImplementsInterfaceDelete(diffElement)) {
                return Boolean.TRUE;
            }

            if (handleForStatementExpression(diffElement)) {
                return Boolean.TRUE;
            }
        }

        return super.caseModelElementChangeRightTarget(diffElement);
    }

    /**
     * Check if the deletion diff is about the expression of a for statement 
     * and create an according diff element if this is the case.
     * for(int i = 0; i < a; i++);
     * 
     * TODO check for general VariableDeclarationHandling
     * This method might not be used from a for loop perspective but
     * to handle all blocks that enclose a VariableDeclarationHandling within an expression
     * 
     * @param diffElement
     *            The element to proof.
     * @return True if the change is a implements interface deletion. False otherwise.
     */
    private boolean handleForStatementExpression(ModelElementChangeLeftTarget diffElement) {

        EObject rightParent = diffElement.getRightParent();
        if (!(rightParent instanceof VariableDeclarationFragment)) {
            return false;
        }

        EObject expression = rightParent.eContainer();
        if (!(expression instanceof Expression)) {
            return false;
        }

        EObject statement = expression.eContainer();
        if (!(statement instanceof ForStatement)) {
            return false;
        }

        StatementInsert insert = DiffElementFactory.createStatementInsert((ForStatement) statement);
        addToParentDiffGroup(diffElement, insert);

        return true;
    }

    /**
     * Check if the deletion diff is about the expression of a for statement 
     * and create an according diff element if this is the case.
     * for(int i = 0; i < a; i++);
     * 
     * TODO check for general VariableDeclarationHandling
     * This method might not be used from a for loop perspective but
     * to handle all blocks that enclose a VariableDeclarationHandling within an expression
     * 
     * @param diffElement
     *            The element to proof.
     * @return True if the change is a implements interface deletion. False otherwise.
     */
    private boolean handleForStatementExpression(ModelElementChangeRightTarget diffElement) {

        EObject leftParent = diffElement.getLeftParent();
        if (!(leftParent instanceof VariableDeclarationFragment)) {
            return false;
        }

        EObject expression = leftParent.eContainer();
        if (!(expression instanceof Expression)) {
            return false;
        }

        EObject statement = expression.eContainer();
        if (!(statement instanceof ForStatement)) {
            return false;
        }

        StatementDelete delete = DiffElementFactory.createStatementDelete((ForStatement) statement, matchManager);
        addToParentDiffGroup(diffElement, delete);

        return true;
    }

    /**
     * Proof if the change is about an "implements interface" deletion and create an according diff
     * element if this is the case.
     * 
     * @param diffElement
     *            The raw change element to proof.
     * @return True if the change is a implements interface deletion. False otherwise.
     */
    private boolean handleImplementsInterfaceDelete(ModelElementChangeRightTarget diffElement) {

        if (!(diffElement.getRightElement() instanceof TypeAccess)) {
            return false;
        }

        TypeAccess rightElement = (TypeAccess) diffElement.getRightElement();
        Type type = rightElement.getType();
        EObject eContainer = rightElement.eContainer();

        if (!(eContainer instanceof ClassDeclaration) || !(type instanceof InterfaceDeclaration)) {
            return false;
        }

        ImplementsInterfaceDelete delete = Java2KDMDiffFactory.eINSTANCE.createImplementsInterfaceDelete();
        delete.setImplementedInterface((InterfaceDeclaration) type);
        delete.setChangedClass((ClassDeclaration) eContainer);

        addToParentDiffGroup(diffElement, delete);
        return true;

    }

    /**
     * Proof if the change is about an "implements interface" deletion and create an according diff
     * element if this is the case.
     * 
     * @param diffElement
     *            The raw change element to proof.
     * @return True if the change is a implements interface deletion. False otherwise.
     */
    private boolean handleImplementsInterfaceInsert(ModelElementChangeLeftTarget diffElement) {

        if (!(diffElement.getLeftElement() instanceof TypeAccess)) {
            return false;
        }

        TypeAccess leftElement = (TypeAccess) diffElement.getLeftElement();
        Type type = leftElement.getType();
        EObject eContainer = leftElement.eContainer();

        if (!(eContainer instanceof ClassDeclaration) || !(type instanceof InterfaceDeclaration)) {
            return false;
        }

        ImplementsInterfaceInsert insert = Java2KDMDiffFactory.eINSTANCE.createImplementsInterfaceInsert();
        insert.setImplementedInterface((InterfaceDeclaration) type);
        insert.setChangedClass((ClassDeclaration) eContainer);

        addToParentDiffGroup(diffElement, insert);
        return true;
    }

    /**
     * Add a new diff element to the parent diff group.
     * 
     * @param originalElement
     *            The original diff element to get the parent from
     * @param newElement
     *            The new diff element to add.
     */
    private void addToParentDiffGroup(DiffElement originalElement, DiffElement newElement) {
        if (originalElement.eContainer() instanceof DiffGroup) {
            DiffGroup parentGroup = (DiffGroup) originalElement.eContainer();
            parentGroup.getSubDiffElements().add(newElement);
        }
    }

    /**
     * Always keep all standard diff elements.
     * 
     * @param object
     *            The diff element object.
     * @return False to identify that it has not been replaced
     */
    @Override
    public Boolean caseDiffElement(DiffElement object) {
        return Boolean.FALSE;
    }

}

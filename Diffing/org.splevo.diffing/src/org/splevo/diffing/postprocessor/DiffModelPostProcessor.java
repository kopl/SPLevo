package org.splevo.diffing.postprocessor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.util.DiffSwitch;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.splevo.diffing.emfcompare.java2kdmdiff.FieldDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImplementsInterfaceInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange;

/**
 * A post processor to analyze a diff model to identify specific changes.
 * 
 * @author Benjamin Klatt
 * 
 */
public class DiffModelPostProcessor extends DiffSwitch<Boolean> {

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
     * If it is about a statement element which is not a block convert it to a
     * StatementChangeElement.
     * 
     * @param diffGroup
     *            the diff group
     * @return the boolean
     */
    @Override
    public Boolean caseDiffGroup(DiffGroup diffGroup) {

        // no specific handling if switching the root.
        if (diffGroup.getRightParent() == null) {
            return Boolean.FALSE;
        }

        if (diffGroup.getRightParent() instanceof Statement) {
            if (!(diffGroup.getRightParent() instanceof Block)) {

                StatementChange statementChange = Java2KDMDiffFactory.eINSTANCE.createStatementChange();
                statementChange.setStatementRight((Statement) diffGroup.getRightParent());
                statementChange
                        .setStatementLeft((Statement) matchManager.getMatchedEObject(diffGroup.getRightParent()));
                statementChange.getSubDiffElements().addAll(diffGroup.getSubDiffElements());

                // add the statement change to the parent container
                if (diffGroup.eContainer() instanceof DiffGroup) {
                    DiffGroup parentGroup = (DiffGroup) diffGroup.eContainer();
                    parentGroup.getSubDiffElements().add(statementChange);
                }

                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
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
     * @param object
     *            the model change to analyze
     * @return True/False whether the element has been replaced or not.
     */
    @Override
    public Boolean caseModelElementChangeLeftTarget(ModelElementChangeLeftTarget object) {

        if (object.getKind() == DifferenceKind.ADDITION) {
            if ((object.getLeftElement() instanceof TypeAccess)
                    && object.getLeftElement().eContainer() instanceof ClassDeclaration
                    && ((TypeAccess) object.getLeftElement()).getType() instanceof InterfaceDeclaration) {

                ImplementsInterfaceInsert implementsInterfaceInsert = Java2KDMDiffFactory.eINSTANCE
                        .createImplementsInterfaceInsert();
                implementsInterfaceInsert.setImplementedInterface((InterfaceDeclaration) ((TypeAccess) object
                        .getLeftElement()).getType());
                implementsInterfaceInsert.setChangedClass((ClassDeclaration) object.getLeftElement().eContainer());

                // add the statement change to the parent container
                if (object.eContainer() instanceof DiffGroup) {
                    DiffGroup parentGroup = (DiffGroup) object.eContainer();
                    parentGroup.getSubDiffElements().add(implementsInterfaceInsert);
                }

                return Boolean.TRUE;
            }
        }

        return super.caseModelElementChangeLeftTarget(object);
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
     * @param object
     *            the model change to analyze
     * @return True/False whether the element has been replaced or not.
     */
    @Override
    public Boolean caseModelElementChangeRightTarget(ModelElementChangeRightTarget object) {

        if (object.getKind() == DifferenceKind.DELETION) {
            if ((object.getRightElement() instanceof FieldDeclaration)) {

                FieldDelete fieldDelete = Java2KDMDiffFactory.eINSTANCE.createFieldDelete();
                fieldDelete.setFieldRight((FieldDeclaration) object.getRightElement());

                // add the statement change to the parent container
                if (object.eContainer() instanceof DiffGroup) {
                    DiffGroup parentGroup = (DiffGroup) object.eContainer();
                    parentGroup.getSubDiffElements().add(fieldDelete);
                }

                return Boolean.TRUE;
            } else if ((object.getRightElement() instanceof TypeAccess)
                    && object.getRightElement().eContainer() instanceof ClassDeclaration
                    && ((TypeAccess) object.getRightElement()).getType() instanceof InterfaceDeclaration) {

                ImplementsInterfaceDelete implementsInterfaceDelete = Java2KDMDiffFactory.eINSTANCE
                        .createImplementsInterfaceDelete();
                implementsInterfaceDelete.setImplementedInterface((InterfaceDeclaration) ((TypeAccess) object
                        .getRightElement()).getType());
                implementsInterfaceDelete.setChangedClass((ClassDeclaration) object.getRightElement().eContainer());

                // add the statement change to the parent container
                if (object.eContainer() instanceof DiffGroup) {
                    DiffGroup parentGroup = (DiffGroup) object.eContainer();
                    parentGroup.getSubDiffElements().add(implementsInterfaceDelete);
                }

                return Boolean.TRUE;
            }
        }

        return super.caseModelElementChangeRightTarget(object);
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

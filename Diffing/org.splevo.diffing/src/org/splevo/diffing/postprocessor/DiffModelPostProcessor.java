package org.splevo.diffing.postprocessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.util.DiffSwitch;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.Statement;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffFactory;
import org.splevo.diffing.emfcompare.java2kdmdiff.StatementChange;

/**
 * A post processor to analyze a diff model to identify specific changes.
 * 
 * @author Benjamin Klatt
 * 
 */
public class DiffModelPostProcessor extends DiffSwitch<Boolean> {

    /** The logger for the class. */
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
                logger.debug("filtered :" + subDiffElement);
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

    /* (non-Javadoc)
     * @see org.eclipse.emf.compare.diff.metamodel.util.DiffSwitch#caseDiffElement(org.eclipse.emf.compare.diff.metamodel.DiffElement)
     */
    @Override
    public Boolean caseDiffElement(DiffElement object) {
        return Boolean.FALSE;
    }

}

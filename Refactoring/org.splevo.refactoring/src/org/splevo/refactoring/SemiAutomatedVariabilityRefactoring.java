package org.splevo.refactoring;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.collect.Lists;

/**
 * A semi-automated variability refactoring. Semi-automated means that not the entire refactoring is
 * carried out by our routines. Instead, humans have to do some steps that are hard to automate. By
 * calling the refactor() method, comments will be generated that fill up a list of semi-automated
 * refactorings. In a second step, the user has to chose one of these refactorings and execute it.
 * The execution calls the startManualRefactoring() method.
 */
public abstract class SemiAutomatedVariabilityRefactoring implements VariabilityRefactoring {

    /**
     * Prepares the variation point for the outstanding semi-automated refactoring.
     * 
     * @see org.splevo.refactoring.VariabilityRefactoring#refactor(org.splevo.vpm.variability.VariationPoint,
     *      java.util.Map)
     */
    @Override
    public List<Resource> refactor(VariationPoint variationPoint, Map<String, Object> refactoringConfigurations) {
        EObject elementToComment = variationPoint.getLocation().getWrappedElement();
        addCommentToElement(elementToComment, variationPoint.getId());
        return Lists.newArrayList(elementToComment.eResource());
    }

    /**
     * Adds a ToDo comment to the given element. The comment contains the technology-specific ToDo
     * tag and the variation point ID.
     * 
     * @param element
     *            The element that receives the comment.
     * @param variationPointID
     *            The variation point ID to be used in the comment.
     */
    protected abstract void addCommentToElement(EObject element, String variationPointID);

    /**
     * Starts the semi-automated refactoring process. TODO we might be able to provide some more
     * base functionality for this.
     * 
     * @param variationPoint
     *            The variation point to be refactored.
     * @param refactoringConfigurations
     *            The refactoring configurations to be considered during the refactoring.
     * @return The list of changed resources that shall be peristed.
     * @throws VariabilityRefactoringFailedException
     *             If the refactoring did not finish successfully.
     */
    public List<Resource> startManualRefactoring(VariationPoint variationPoint,
            Map<String, Object> refactoringConfigurations) throws VariabilityRefactoringFailedException {
        List<Resource> changedResources = startLanguageSpecificManualRefactoring(variationPoint,
                refactoringConfigurations);
        variationPoint.setRefactored(true);
        return changedResources;
    }

    /**
     * Starts the language specific part of the refactoring.
     * 
     * @param variationPoint
     *            The variation point to be refactored.
     * @param refactoringConfigurations
     *            The refactoring configuration to be used.
     * @return The list of changed resources that shall be peristed.
     * @throws VariabilityRefactoringFailedException
     *             If the refactoring did not finish successfully.
     */
    protected abstract List<Resource> startLanguageSpecificManualRefactoring(VariationPoint variationPoint,
            Map<String, Object> refactoringConfigurations) throws VariabilityRefactoringFailedException;

}

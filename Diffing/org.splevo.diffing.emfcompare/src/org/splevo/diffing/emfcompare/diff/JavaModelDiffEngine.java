package org.splevo.diffing.emfcompare.diff;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.engine.check.AttributesCheck;
import org.eclipse.emf.compare.diff.engine.check.ReferencesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ParameterizedType;

/**
 * A diff engine specific to the modisco java application model.
 * 
 * @author Benjamin Klatt
 * 
 */
public class JavaModelDiffEngine extends GenericDiffEngine {

    /** The packages to be ignored. */
    private List<String> ignorePackages = new ArrayList<String>();

    /**
     * Get the java model specific attributes checker. The JavaModelAttributesCheck class is
     * customized to the specific semantics of attributes contained in the modisco java model.
     * 
     * @return the java model attribute checker.
     */
    @Override
    protected AttributesCheck getAttributesChecker() {
        return new JavaModelAttributesCheck(getMatchManager(), ignorePackages);
    }

    /**
     * Get the java model specific reference checker. The JavaModelReferenceCheck class is
     * customized to the specific semantics of references contained in the modisco java model.
     * 
     * @return THe java model specific reference checker.
     */
    @Override
    protected ReferencesCheck getReferencesChecker() {
        return new JavaModelReferenceCheck(getMatchManager(), ignorePackages);
    }

    /**
     * Process the unmatched elements.
     * 
     * Customized unmatched elements processing including: - ignore elements from filtered packages
     * 
     * @param diffGroup
     *            the diff group
     * @param unmatched
     *            the unmatched elements
     */
    @Override
    protected void processUnmatchedElements(DiffGroup diffGroup, List<UnmatchElement> unmatched) {

        // filter due to package restrictions
        unmatched = filterIgnoredPackages(unmatched);

        // filter unmatched elements to ignore
        unmatched = filterIgnoreElements(unmatched);

        // initialize the processors and filters
        UnmatchedElementProcessor ueProcessor = new UnmatchedElementProcessor();
        UnmatchedElementFilter ueFilter = new UnmatchedElementFilter();

        // analyze unmatched elements to create specific diff types.
        List<UnmatchElement> filteredUnmatched = new ArrayList<UnmatchElement>();
        for (final UnmatchElement unmatchElement : unmatched) {

            DiffElement diffElement = ueProcessor.process(unmatchElement);

            // check if a specific diff element has been created.
            // otherwise add it to the list that still needs to be processed
            if (diffElement != null) {

                // check the type of differences and for additions, which are made
                // in the left model, try to find a matching parent in the right one.
                EObject container = unmatchElement.getElement().eContainer();
                if (diffElement.getKind() == DifferenceKind.ADDITION) {
                    addInContainerPackage(diffGroup, diffElement, getMatchManager().getMatchedEObject(container));
                } else {
                    addInContainerPackage(diffGroup, diffElement, container);
                }

            } else {
                Boolean filter = ueFilter.filter(unmatchElement);
                if (filter == Boolean.FALSE) {
                    filteredUnmatched.add(unmatchElement);
                }
            }
        }

        // Process the elements that were not handled by type specific diff's yet.
        super.processUnmatchedElements(diffGroup, filteredUnmatched);
    }

    /**
     * Filter unmatched elements which are not relevant for the source model diffing.
     * 
     * @param unmatched
     *            The list of elements to filter.
     * @return The filtered list.
     */
    private List<UnmatchElement> filterIgnoreElements(List<UnmatchElement> unmatched) {

        List<UnmatchElement> unmatchedFiltered = new ArrayList<UnmatchElement>(unmatched.size());

        for (UnmatchElement unmatchElement : unmatched) {

            // filter parameterized types
            if (unmatchElement.getElement() instanceof ParameterizedType) {
                continue;
            }
            
            // filter compilation units
            if (unmatchElement.getElement() instanceof CompilationUnit) {
                continue;
            }
        
            unmatchedFiltered.add(unmatchElement);
        }
        return unmatchedFiltered;
    }

    /**
     * Build a new list of unmatched elements containing only those not filtered due to the given
     * package restrictions.
     * 
     * @param unmatched
     *            The original list of unmatched elements.
     * @return The list of unmatched elements that should not be filtered.
     */
    private List<UnmatchElement> filterIgnoredPackages(List<UnmatchElement> unmatched) {

        List<UnmatchElement> unmatchedFiltered = new ArrayList<UnmatchElement>(unmatched.size());

        // filter type declarations located in a package to be ignored
        PackageIgnoreVisitor packageIgnoreVisitor = new PackageIgnoreVisitor(ignorePackages);
        for (UnmatchElement unmatchElement : unmatched) {
            Boolean ignore = packageIgnoreVisitor.doSwitch(unmatchElement.getElement());
            if (ignore == Boolean.FALSE) {
                unmatchedFiltered.add(unmatchElement);
            }
        }
        return unmatchedFiltered;
    }

    /**
     * Get the list of packages to ignored any differences in.
     * 
     * @return The list of regular expressions describing the packages to ignore.
     */
    public List<String> getIgnorePackages() {
        return ignorePackages;
    }

    /**
     * Provide public access to the match manager used by the diff engine.
     * 
     * @return The MatchManager used by the diff engine.
     */
    public IMatchManager getMatchManager() {
        return super.getMatchManager();
    }
}

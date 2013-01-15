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
import org.splevo.diffing.emfcompare.util.PackageIgnoreChecker;

/**
 * A diff engine specific to the modisco java application model.
 * 
 * @author Benjamin Klatt
 * 
 */
public class JavaModelDiffEngine extends GenericDiffEngine {

    /** The unmatched element processor to build more semantical diff elements. */
    private UnmatchedElementProcessor ueProcessor = null;
    
    /** Identifier for elements which are not relevant. */
    private UnmatchedElementFilter ueFilter = null;
    
    /** The package ignore checker to be used. */
    private PackageIgnoreChecker packageIgnoreChecker = null;

    /**
     * Constructor requiring to set the relevant references.
     * 
     * @param ignorePackages The packages to ignore.
     */
    public JavaModelDiffEngine(List<String> ignorePackages) {
        super();
        this.packageIgnoreChecker = new PackageIgnoreChecker(ignorePackages);
        ueFilter = new UnmatchedElementFilter(packageIgnoreChecker);
    }

    /**
     * Get the java model specific attributes checker. The JavaModelAttributesCheck class is
     * customized to the specific semantics of attributes contained in the modisco java model.
     * 
     * @return the java model attribute checker.
     */
    @Override
    protected AttributesCheck getAttributesChecker() {
        return new JavaModelAttributesCheck(getMatchManager(), packageIgnoreChecker);
    }

    /**
     * Get the java model specific reference checker. The JavaModelReferenceCheck class is
     * customized to the specific semantics of references contained in the modisco java model.
     * 
     * @return THe java model specific reference checker.
     */
    @Override
    protected ReferencesCheck getReferencesChecker() {
        return new JavaModelReferenceCheck(getMatchManager(), packageIgnoreChecker);
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
        
        // init the unmatched element processor
        ueProcessor = new UnmatchedElementProcessor(getMatchManager());

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
        for (UnmatchElement unmatchElement : unmatched) {
            Boolean ignore = packageIgnoreChecker.isInIgnorePackage(unmatchElement.getElement());
            if (ignore == Boolean.FALSE) {
                unmatchedFiltered.add(unmatchElement);
            }
        }
        return unmatchedFiltered;
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

package org.splevo.diffing.emfcompare.diff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.diff.engine.IMatchManager;
import org.eclipse.emf.compare.diff.engine.check.ReferencesCheck;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.compare.match.metamodel.Match3Elements;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * A java model specific reference check to interpret model references which can be ignored.
 */
public class JavaModelReferenceCheck extends ReferencesCheck {

    /** The packages to be ignored. */
    private List<String> ignorePackages = new ArrayList<String>();

    /**
     * Constructor requiring a match manager to access required match objects.
     * 
     * @param manager
     *            The match manager to use.
     * @param ignorePackages
     *            The list of patterns to filter our packages that should be ignored.
     */
    public JavaModelReferenceCheck(IMatchManager manager, List<String> ignorePackages) {
        super(manager);
        this.ignorePackages = ignorePackages;
    }

    /**
     * Checks if there's been references updates in the model.<br/>
     * <p>
     * A reference is considered updated if its value(s) has been changed (either removal or
     * addition of an element if the reference is multi-valued or update of a single-valued
     * reference) between the left and the right model.
     * </p>
     * 
     * Note: The method has been overriden to extend the signiture of shouldBeIgnored to also hand
     * over the origin element of the mapping
     * 
     * @param root
     *            {@link DiffGroup root} of the {@link DiffElement} to create.
     * @param mapping
     *            Contains informations about the left and right model elements we have to compare.
     * @throws FactoryException
     *             Thrown if we cannot fetch the references' values.
     */
    public void checkReferencesUpdates(DiffGroup root, Match2Elements mapping) throws FactoryException {
        final EClass eClass = mapping.getLeftElement().eClass();
        final List<EReference> eclassReferences = eClass.getEAllReferences();

        final Iterator<EReference> it = eclassReferences.iterator();
        while (it.hasNext()) {
            final EReference next = it.next();
            if (!shouldBeIgnored(next, mapping.getLeftElement())) {
                checkReferenceUpdates(root, mapping, next);
            } else if (next.isContainment() && next.isOrdered()) {
                checkContainmentReferenceOrderChange(root, mapping, next);
            }
        }
    }

    /**
     * Checks if there's been references updates in the model.<br/>
     * <p>
     * A reference is considered updated if its value(s) has been changed (either removal or
     * addition of an element if the reference is multi-valued or update of a single-valued
     * reference) between the left and the ancestor model, the right and the ancestor or between the
     * left and the right model.
     * </p>
     * 
     * Note: The method has been overriden to extend the signiture of shouldBeIgnored to also hand
     * over the origin element of the mapping
     * 
     * @param root
     *            {@link DiffGroup root} of the {@link DiffElement} to create.
     * @param mapping
     *            Contains informations about the left, right and origin model elements we have to
     *            compare.
     * @throws FactoryException
     *             Thrown if we cannot fetch the references' values.
     */
    public void checkReferencesUpdates(DiffGroup root, Match3Elements mapping) throws FactoryException {
        // Ignores matchElements when they don't have origin (no updates on these)
        if (mapping.getOriginElement() == null) {
            return;
        }
        final EClass eClass = mapping.getOriginElement().eClass();
        final List<EReference> eclassReferences = eClass.getEAllReferences();

        final Iterator<EReference> it = eclassReferences.iterator();
        while (it.hasNext()) {
            final EReference next = it.next();
            if (!shouldBeIgnored(next, mapping.getOriginElement())) {
                checkReferenceUpdates(root, mapping, next);
            } else if (next.isContainment() && next.isOrdered()) {
                checkContainmentReferenceOrderChange(root, mapping, next);
            }
        }
    }

    /**
     * Check if a reference should be ignored. This method is an overloaded version of the
     * shouldBeIgnored(EReference) to also interprete the referencing element.
     * 
     * @param reference
     *            The reference to check.
     * @param referencingElement
     *            The referencing element to include in the check.
     * @return true/false whether to ignore the reference or not.
     */
    protected boolean shouldBeIgnored(EReference reference, EObject referencingElement) {

        // ignore CompilationUnit.originalFilePath
        if ("usagesInTypeAccess".equals(reference.getName())) {
            PackageIgnoreVisitor packageIgnoreVisitor = new PackageIgnoreVisitor(ignorePackages);
            Boolean ignore = packageIgnoreVisitor.doSwitch(referencingElement);
            if (Boolean.TRUE.equals(ignore)) {
                return true;
            }
        }

        return super.shouldBeIgnored(reference);
    }

}

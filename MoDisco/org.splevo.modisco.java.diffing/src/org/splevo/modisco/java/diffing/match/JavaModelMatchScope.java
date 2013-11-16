package org.splevo.modisco.java.diffing.match;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;
import org.splevo.modisco.java.diffing.util.PackageIgnoreChecker;

import com.google.common.base.Predicate;

/**
 * A match scope defining which elements should be included in the java model differencing.
 */
public class JavaModelMatchScope extends DefaultComparisonScope {

    /** Switch to detect elements not relevant. */
    private IsInScopeSwitch isInScopeSwitch = new IsInScopeSwitch();

    /** Checker to decide if an element is in scope. */
    private PackageIgnoreChecker packageIgnoreChecker;

    /**
     * Constructor requiring to specify the packages to ignore.
     * 
     * @param left
     *            The left notifier of this scope.
     * @param right
     *            The right notifier of this scope.
     * @param packageIgnoreChecker
     *            The checker to identify elements that are in packages to be ignored.
     */
    public JavaModelMatchScope(Notifier left, Notifier right, PackageIgnoreChecker packageIgnoreChecker) {
        super(left, right, null);
        this.packageIgnoreChecker = packageIgnoreChecker;
        EObjectInScopePredicate predicate = new EObjectInScopePredicate();
        setEObjectContentFilter(predicate);
        setResourceContentFilter(predicate);
        setResourceSetContentFilter(new ResourceInScopePredicate());
    }

    /**
     * A predicate to decide if a resource is in the scope.<br>
     * For MoDisco models we must include the resources instances of {@link JavaModel} as these are
     * the AST models we must compare as well as instances of {@link JavaApplication} as their
     * references are required in the downstream process.
     */
    private class ResourceInScopePredicate implements Predicate<Resource> {

        @Override
        public boolean apply(Resource input) {
            for (EObject rootElement : input.getContents()) {
                if (rootElement instanceof Model) {
                    return true;
                }
                if (rootElement instanceof JavaApplication) {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     * A predicate to decide if an eObject is in scope or not.
     */
    private class EObjectInScopePredicate implements Predicate<EObject> {

        @Override
        public boolean apply(EObject input) {
            Boolean isInScope = isInScopeSwitch.doSwitch(input);
            if (isInScope == Boolean.FALSE) {
                return false;
            }

            Boolean isInIgnorePackage = packageIgnoreChecker.isInIgnorePackage(input);
            if (isInIgnorePackage == Boolean.TRUE) {
                return false;
            }

            // The java application model is an over-arching glue model only
            // and can be ignored completely during match and diffing.
            if (input instanceof JavaApplication) {
                return false;
            }

            return true;
        }

    }
}

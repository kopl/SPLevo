package org.splevo.jamopp.diffing.scope;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.commons.layout.LayoutInformation;
import org.emftext.language.java.containers.CompilationUnit;

import com.google.common.base.Predicate;

/**
 * A match scope defining which elements should be included in the java model differencing.
 */
public class JavaModelMatchScope extends DefaultComparisonScope {

    /** Checker to decide if an element is in scope. */
    private PackageIgnoreChecker packageIgnoreChecker;

    /** The name of the package info files to ignore. */
    private static final String PACKAGE_INFO_FILENAME = "package-info.java";

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
                if (rootElement instanceof CompilationUnit) {
                    return true;
                }
                if(rootElement instanceof org.emftext.language.java.containers.Package){
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
        	
        	if(input instanceof LayoutInformation) {
        		return false;
        	}
        	
        	if(input instanceof CompilationUnit) {
        		if(PACKAGE_INFO_FILENAME.equals(((CompilationUnit) input).getName())) {
        			return false;
        		}
        	}

            Boolean isInIgnorePackage = packageIgnoreChecker.isInIgnorePackage(input);
            if (isInIgnorePackage == Boolean.TRUE) {
                return false;
            }

            return true;
        }

    }
}

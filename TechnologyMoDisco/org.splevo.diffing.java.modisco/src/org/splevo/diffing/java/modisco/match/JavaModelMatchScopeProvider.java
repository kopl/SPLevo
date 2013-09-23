package org.splevo.diffing.java.modisco.match;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.match.engine.IMatchScope;
import org.eclipse.emf.compare.match.engine.IMatchScopeProvider;
import org.eclipse.emf.compare.match.filter.IResourceFilter;

/**
 * Match scope provider specific to the java model diffing.
 * 
 * @author Benjamin Klatt
 *
 */
public class JavaModelMatchScopeProvider implements IMatchScopeProvider {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(JavaModelMatchScope.class);

    /** Regular expressions defining packages to be ignored. */
    private List<String> ignorePackages = new ArrayList<String>();
    
    /** The match scope. */
    private JavaModelMatchScope matchScope = null;
    
    /**
     * Constructor requiring to specify the packages to ignore.
     * 
     * @param ignorePackages The regex patterns of packages to ignore.
     */
    public JavaModelMatchScopeProvider(List<String> ignorePackages) {
        this.ignorePackages.addAll(ignorePackages);
        this.matchScope = new JavaModelMatchScope(ignorePackages);
    }

    @Override
    public IMatchScope getLeftScope() {
        return matchScope;
    }

    @Override
    public IMatchScope getRightScope() {
        return matchScope;
    }

    @Override
    public IMatchScope getAncestorScope() {
        return matchScope;
    }

    @Override
    public void applyResourceFilter(IResourceFilter filter) {
        logger.debug("isInScope(Resource resource) called");
    }
}

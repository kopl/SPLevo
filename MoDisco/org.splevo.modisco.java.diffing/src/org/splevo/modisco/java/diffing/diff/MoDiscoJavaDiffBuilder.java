package org.splevo.modisco.java.diffing.diff;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.DifferenceSource;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.Statement;

/**
 * Diff builder / DiffProcessor specific for the MoDisco Java model.
 * 
 * Main purpose of this builder is to filter invalid move changes and produce custom diffs for
 * directly identifyable .
 */
@SuppressWarnings("restriction")
public class MoDiscoJavaDiffBuilder extends DiffBuilder {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(MoDiscoJavaDiffBuilder.class);

    /** The factory to create custom changes. */
    private CustomChangeFactory customChangeFactory = new CustomChangeFactory();

    /** A cache to prevent duplicate changes for a single element. */
    private Map<EObject, Diff> changeCache = new LinkedHashMap<EObject, Diff>();

    /**
     * Resource attachment changes are ignored to prevent errors because of unmatched resources.<br>
     * {@inheritDoc}
     */
    @Override
    public void resourceAttachmentChange(Match match, String uri, DifferenceKind kind, DifferenceSource source) {
    }

    /**
     * Reference changes. New or deleted model elements are identified by changed containment
     * references.
     * 
     * {@inheritDoc}
     */
    @Override
    public void referenceChange(Match match, EReference reference, EObject value, DifferenceKind kind,
            DifferenceSource source) {

        // handle containment changes as typical ADD / DELETE cases for
        // custom elements.
        if (reference.isContainment()) {
            Diff change = customChangeFactory.doSwitch(value);
            if (change != null) {
                fillStandardFields(change, match, kind, source);
                return;
            }
        }

        // catch all reasonable explicit elements

        // TODO: Enable not only for expressions
        if (value instanceof EnumConstantDeclaration || value instanceof Expression || value instanceof ClassDeclaration || value instanceof InterfaceDeclaration
                || value instanceof AbstractMethodDeclaration) {
            Match nextParent = nextResonableMatch(match);
            if (nextParent != null) {
                EObject parentObject = null;
                if (nextParent.getLeft() != null) {
                    parentObject = nextParent.getLeft();
                } else if (nextParent.getRight() != null) {
                    parentObject = nextParent.getRight();
                }

                if (!changeCache.containsKey(parentObject)) {
                    Diff change = customChangeFactory.doSwitch(nextParent.getLeft());
                    fillStandardFields(change, nextParent, DifferenceKind.CHANGE, source);
                    changeCache.put(parentObject, change);
                }
                return;
            }
        }

        logger.info("Unhandled Reference Change (" + kind + ") :" + reference.getName() + ", left: " + match.getLeft()
                + ", right: " + match.getRight() + ", value: " + value);
    }

    @Override
    public void attributeChange(Match match, EAttribute attribute, Object value, DifferenceKind kind,
            DifferenceSource source) {

        logger.info("Attribute Change (" + kind + ") :" + attribute.getName() + ", left: " + match.getLeft()
                + ", right: " + match.getRight() + ", value: " + value);
        super.attributeChange(match, attribute, value, kind, source);
    }

    /**
     * Check a match if it is reasonable as parent for a change or get the next reasonable of its
     * parent matches.
     * 
     * @param match
     *            The match to check.
     * @return The next reasonable match or null if none exists.
     */
    private Match nextResonableMatch(Match match) {

        if (match == null) {
            return null;
        }

        EObject parentObject = null;
        if (match.getLeft() != null) {
            parentObject = match.getLeft();
        } else {
            parentObject = match.getRight();
        }

        if (parentObject instanceof ClassDeclaration) {
            return match;
        } else if (parentObject instanceof FieldDeclaration) {
            return match;
        } else if (parentObject instanceof AbstractMethodDeclaration) {
            return match;
        } else if (parentObject instanceof Package) {
            return match;
        } else if (parentObject instanceof EnumDeclaration) {
            return match;
        } else if (parentObject instanceof ImportDeclaration) {
            return match;
        } else if (parentObject instanceof Statement) {
            return match;
        }

        if (match.eContainer() instanceof Match) {
            return nextResonableMatch((Match) match.eContainer());
        } else {
            return null;
        }

    }

    /**
     * Convenience method to fill the standard fields.
     * 
     * @param diff
     *            The diff to change.
     * @param match
     *            The match element to set.
     * @param kind
     *            The kind to set.
     * @param source
     *            The source to set.
     */
    private void fillStandardFields(Diff diff, Match match, DifferenceKind kind, DifferenceSource source) {
        diff.setKind(kind);
        diff.setMatch(match);
        diff.setSource(source);
    }
}

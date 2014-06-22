package org.splevo.refactoring;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * This singleton class handles the registration of available refactoring extensions.<br>
 * Use registerRefactoring() to do so.
 */
public final class VariabilityRefactoringRegistry {

    private static Logger logger = Logger.getLogger(VariabilityRefactoringRegistry.class);

    private static List<VariabilityRefactoring> refactorings = Lists.newArrayList();

    /**
     * Register a new refactoring.
     *
     * Note: If a refactoring instance has already been registered, nothing is done.<br>
     * Instance and id checking can be done.
     *
     * @param refactoring
     *            The provider itself.
     */
    public static void registerRefactoring(VariabilityRefactoring refactoring) {
        if (isValid(refactoring) && isNotRegistered(refactoring)) {
            refactorings.add(refactoring);
        }
    }

    /**
     * Get the list of registered content providers.
     *
     * The list will be ordered according to the refactorings order id.
     *
     * @return The current list.
     */
    public static List<VariabilityRefactoring> getRefactorings() {
        Collections.sort(refactorings, new Comparator<VariabilityRefactoring>() {
            @Override
            public int compare(VariabilityRefactoring r1, VariabilityRefactoring r2) {
                String name1 = r1.getVariabilityMechanism().getName();
                String name2 = r2.getVariabilityMechanism().getName();
                return Strings.nullToEmpty(name1).compareTo(name2);
            }
        });
        return refactorings;
    }

    /**
     * Get a refactoring for a specific id.
     *
     * @param id
     *            The id to get the refactoring for.
     * @return The refactoring or null if non exists for the provided id.
     */
    public static VariabilityRefactoring getRefactoringById(String id) {
        for (VariabilityRefactoring refactoring : refactorings) {
            if (refactoring.getId().equals(id)) {
                return refactoring;
            }
        }
        return null;
    }

    /**
     * Ensure the refactoring is not null and all required attributes set.
     *
     * @param refactoring
     *            The refactoring to test.
     * @return True/false if it is valid or not.
     */
    private static boolean isValid(VariabilityRefactoring refactoring) {
        if (refactoring == null) {
            logger.warn("Tried to register null refactoring");
            return false;
        }
        if (refactoring.getId() == null) {
            logger.warn("Tried to register refactoring without id " + refactoring);
            return false;
        }
        if (refactoring.getVariabilityMechanism() == null) {
            logger.warn("Tried to register refactoring without a variability mechanism " + refactoring);
            return false;
        }

        return true;
    }

    /**
     * Check if a refactoring is already registered.
     *
     * @param refactoring
     *            The refactoring to check.
     * @return True if is not registered, false otherwise.
     */
    private static boolean isNotRegistered(VariabilityRefactoring refactoring) {
        for (VariabilityRefactoring existingRefactoring : refactorings) {
            if (existingRefactoring.getId().equals(refactoring.getId())) {
                return false;
            }
        }
        return true;
    }
}

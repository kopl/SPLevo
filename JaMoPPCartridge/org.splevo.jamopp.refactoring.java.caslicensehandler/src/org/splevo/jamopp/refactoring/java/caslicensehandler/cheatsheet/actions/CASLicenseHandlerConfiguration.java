package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Field;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Configuration class for the cheat sheet. Stores values which are needed in another contexts.
 */
public final class CASLicenseHandlerConfiguration {

    private ConcreteClassifier licenseConstantsClassifier;
    private ConcreteClassifier licenseValidatorClassifier;
    private Map<String, String> variantToLicenseMap = new HashMap<String, String>();
    private VariationPoint variationPoint = null;
    private Map<String, Object> refactoringConfigurations = Maps.newHashMap();
    private final Object refactoringFinishedMonitor = new Object();
    private boolean refactoringFinished = true;
    private SPLevoProject consolidationProject = null;

    private static CASLicenseHandlerConfiguration eINSTANCE = null;

    private CASLicenseHandlerConfiguration() {

    }

    /**
     * Returns the currently used instance.
     * 
     * @return the currently used instance.
     */
    public static CASLicenseHandlerConfiguration getInstance() {
        if (eINSTANCE == null || eINSTANCE.isRefactoringFinished()) {
            eINSTANCE = new CASLicenseHandlerConfiguration();
        }
        return eINSTANCE;
    }

    /**
     * @return The consolidation project.
     */
    public SPLevoProject getConsolidationProject() {
        return this.consolidationProject;
    }

    /**
     * Sets the consolidation project.
     * 
     * @param project
     *            The consolidation project.
     */
    public void setConsolidationProject(SPLevoProject project) {
        this.consolidationProject = project;
    }

    /**
     * Maps the src-directory to the leading path.
     * 
     * @param configuration
     *            sets the current refactoring configurations.
     */
    public void setRefactoringConfigurations(Map<String, Object> configuration) {
        this.refactoringConfigurations = configuration;
    }

    /**
     * Returns the stored refactoring configuration map.
     * 
     * @return returns the stored refactoring configuration map.
     */
    public Map<String, Object> getRefactoringConfigurations() {
        return this.refactoringConfigurations;
    }

    /**
     * Sets the variation point.
     * 
     * @param vp
     *            represents the variation point.
     */
    public void setVariationPoint(VariationPoint vp) {
        this.variationPoint = vp;
    }

    /**
     * Returns the variation point.
     * 
     * @return returns a variation point.
     */
    public VariationPoint getVariationPoint() {
        return this.variationPoint;
    }

    /**
     * Returns a variant by the variants id.
     * 
     * @param variantID
     *            the id which identify the variant.
     * @return returns the corresponding variant by the id.
     */
    public Variant getVariantBy(String variantID) {
        for (Variant variant : this.getVariationPoint().getVariants()) {
            if (variant.getId().equals(variantID)) {
                return variant;
            }
        }
        return null;
    }

    /**
     * Returns the current variant-to-license map.
     * 
     * @return the current variant-to-license map.
     */
    public Map<String, String> getVariantToLicenseMap() {
        return this.variantToLicenseMap;
    }

    /**
     * Add a given variant-license-pair. The pair is only added if the given license constant is not
     * part of any other mapping stored in the map.
     * 
     * @param variantID
     *            represents the key of the map.
     * @param license
     *            represents the given value of the map.
     * @return True if the mapping has been added. False if the mapping has not been added.
     */
    public boolean addVariantLicensePair(String variantID, String license) {
        if (this.isLicenseAlreadyAssigned(license.toUpperCase(), variantID)) {
            return false;
        }

        this.variantToLicenseMap.put(variantID, license.toUpperCase());
        return true;
    }

    private boolean isLicenseAlreadyAssigned(final String licenseToCheck, final String... variantIdsToIgnore) {
        final Iterable<String> idsToIgnore = Lists.newArrayList(variantIdsToIgnore);
        return Iterables.any(variantToLicenseMap.entrySet(), new Predicate<Entry<String, String>>() {
            @Override
            public boolean apply(Entry<String, String> input) {
                if (Iterables.contains(idsToIgnore, input.getKey())) {
                    return false;
                }
                return input.getValue().equals(licenseToCheck);
            }
        });
    }

    /**
     * Sets the LicenseValidator class.
     * 
     * @param concreteClassifier
     *            Represents the license validator.
     */
    public void setLicenseValidatorClassifier(ConcreteClassifier concreteClassifier) {
        this.licenseValidatorClassifier = concreteClassifier;
    }

    /**
     * Gets the LicenseValidator class.
     * 
     * @return the class.
     */
    public ConcreteClassifier getLicenseValidatorClassifier() {
        return this.licenseValidatorClassifier;
    }

    /**
     * Returns the LicenseConstants class.
     * 
     * @return the type of the LicenseConstants class.
     */
    public ConcreteClassifier getLicenseConstantClassifier() {
        return this.licenseConstantsClassifier;
    }

    /**
     * Sets the LicenseConstant class.
     * 
     * @param classifier
     *            represents the new type of LicenseConstant class
     */
    public void setLicenseConstantClassifier(ConcreteClassifier classifier) {
        this.licenseConstantsClassifier = classifier;
    }

    /**
     * Gets all License stored in the LicenseConstant-class.
     * 
     * @return all licenses.
     */
    public String[] getAllLicenses() {
        if (this.licenseConstantsClassifier == null) {
            return null;
        }
        return this.getAllLicensesBy(this.licenseConstantsClassifier);

    }

    private String[] getAllLicensesBy(ConcreteClassifier classifier) {
        return Iterables.toArray(Iterables.transform(classifier.getFields(), new Function<Field, String>() {
            @Override
            public String apply(Field input) {
                return input.getName();
            }
        }), String.class);
    }

    /**
     * Gets the variation-point-model.
     * 
     * @return the variation-point-model of the current variation point.
     */
    public VariationPointModel getVariationPointModel() {
        return (VariationPointModel) this.getVariationPoint().eContainer().eContainer();
    }

    /**
     * Indicates that a refactoring is started.
     */
    public void refactoringStarted() {
        synchronized (this.refactoringFinishedMonitor) {
            this.refactoringFinished = false;
            this.refactoringFinishedMonitor.notifyAll();
        }
    }

    /**
     * Indicates that a refactoring is finished.
     */
    public void refactoringFinished() {
        synchronized (this.refactoringFinishedMonitor) {
            this.refactoringFinished = true;
            this.refactoringFinishedMonitor.notifyAll();
        }
    }

    /**
     * Stops the thread until the refactoring-thread is finished.
     * 
     * @throws InterruptedException
     *             thrown if the thread is interrupted.
     */
    public void waitForRefactoringToBeFinished() throws InterruptedException {
        synchronized (this.refactoringFinishedMonitor) {
            while (!this.refactoringFinished) {
                this.refactoringFinishedMonitor.wait();
            }
        }
    }

    /**
     * Checks if a refactoring is finished.
     * 
     * @return true if the refactoring is finished, otherwise false.
     */
    public boolean isRefactoringFinished() {
        synchronized (this.refactoringFinishedMonitor) {
            return this.refactoringFinished;
        }
    }
}

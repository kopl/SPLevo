package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.IType;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Field;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

/**
 * Configuration class for the cheat sheet. Stores values which are needed in another contexts.
 */
public final class CASLicenseHandlerConfiguration {

	private IType licenseConstantsType;
	private IType licenseValidatorType;
	private Map<String, String> variantToLicenseMap = new  HashMap<String, String>();
	private VariationPoint variationPoint = null;
	private Map<String, Object> refactoringConfigurations = Maps.newHashMap();
	private final Object refactoringFinishedMonitor = new Object();
	private boolean refactoringFinished = true;
	private SPLevoProject leadingProjects = null;
	
	private static CASLicenseHandlerConfiguration eINSTANCE = null;
	
	private CASLicenseHandlerConfiguration() {
		
	}
	
	/**
	 * Returns the currently used instance.
	 * @return the currently used instance.
	 */
	public static CASLicenseHandlerConfiguration getInstance() {
		if (eINSTANCE == null || eINSTANCE.isRefactoringFinished()) {
			eINSTANCE = new CASLicenseHandlerConfiguration();
		}
		return eINSTANCE;
	}
	
	/**
	 * Returns the leading SPLevoProject.
	 * @return the leading SPLevoProject.
	 */
	public SPLevoProject getLeadingProject() {
		return this.leadingProjects;
	}
	
	/**
	 * Sets the leading SPLevoProject.
	 * @param project
	 * 			represents the new project which will be set.
	 */
	public void setLeadingProject(SPLevoProject project) {
		this.leadingProjects = project;
	}
	
	/**
	 * Maps the src-directory to the leading path.
	 * @param configuration
	 * 				sets the current refactoring configurations.
	 */
	public void setRefactoringConfigurations(Map<String, Object> configuration) {
		this.refactoringConfigurations = configuration;
	}
	
	/**
	 * Returns the stored refactoring configuration map.
	 * @return
	 * 			returns the stored refactoring configuration map.
	 */
	public Map<String, Object> getRefactoringConfigurations() {
		return this.refactoringConfigurations;
	}
	
	/**
	 * Sets the variation point.
	 * @param vp
	 * 				represents the variation point.
	 */
	public void setVariationPoint(VariationPoint vp) {
		this.variationPoint = vp;
	}
	
	/**
	 * Returns the variation point.
	 * @return 
	 * 			returns a variation point.
	 */
	public VariationPoint getVariationPoint() {
		return this.variationPoint;
	}
	
	/**
	 * Returns a variant by the variants id.
	 * @param variantID
	 * 			the id which identify the variant.
	 * @return
	 * 			returns the corresponding variant by the id.
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
	 * @return the current variant-to-license map.
	 */
	public Map<String, String> getVariantToLicenseMap() {
		return this.variantToLicenseMap;
	}
	
	/**
	 * Add a given variant-license-pair.
	 * @param variantID
	 * 			represents the key of the map.
	 * @param license
	 * 			represents the given value of the map.
	 * @return a boolean value which identifies if a given variant-license-pair
	 * 		   is already contained in the map.	
	 */
	public boolean addVariantLicensePair(String variantID, String license) {
		if (this.isLicenseAlreadyAssigned(license.toUpperCase())) {
			return false;
		}
		
		this.variantToLicenseMap.put(variantID, license.toUpperCase());
		return true;
	}
	
	private boolean isLicenseAlreadyAssigned(String licenseToCheck) {
		for (String currentLicense : this.variantToLicenseMap.values()) {
			if (currentLicense.equals(licenseToCheck)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the LicenseValidator-class.
	 * @param type
	 * 			represents the type of the class.
	 */
	public void setLicenseValidatorType(IType type) {
		this.licenseValidatorType = type;
	}
	
	/**
	 * Gets the type of the LicenseValidator-class.
	 * @return the type of the class.
	 */
	public IType getLicenseValidatorType() {
		return this.licenseValidatorType;
	}
	
	/**
	 * Returns the type of the LicenseConstants-class.
	 * @return the type of the LicenseConstants-class.
	 */
	public IType getLicenseConstantType() {
		return this.licenseConstantsType;
	}
	
	/**
	 * Sets the type of the LicenseConstant-class.
	 * @param newLicenseConstantClass
	 * 			represents the new type of LicenseCOnstant-class
	 */
	public void setLicenseConstantType(IType newLicenseConstantClass) {
		this.licenseConstantsType = newLicenseConstantClass;
	}
	
	/**
	 * Gets all License stored in the LicenseConstant-class.
	 * @return all licenses.
	 */
	public String[] getAllLicenses() {
		
		if (null == this.licenseConstantsType) {
			return null;
		}
		
        Optional<ConcreteClassifier> classifier = JaMoPPRoutines.getConcreteClassifierOf(this.licenseConstantsType);
        if (classifier.isPresent()) {
            return this.getAllLicensesBy(classifier.get());            
        } else {
            // TODO logging
            return null;
        }
        		
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
	 * @throws InterruptedException
	 * 			thrown if the thread is interrupted.
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
	 * @return true if the refactoring is finished, otherwise false.
	 */
	public boolean isRefactoringFinished() {
       synchronized (this.refactoringFinishedMonitor) {
           return this.refactoringFinished;
        }
	}
}

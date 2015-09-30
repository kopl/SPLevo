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
public class CASLicenseHandlerConfiguration {

	private IType licenseConstantsType;
	private IType licenseValidatorType;
	private Map<String, String> variantToLicenseMap = new  HashMap<String, String>();
	private VariationPoint variationPoint = null;
	private Map<String, Object> refactoringConfigurations = Maps.newHashMap();
	private static final Object refactoringFinishedMonitor = new Object();
	private static boolean refactoringFinished = true;
	private SPLevoProject leadingProjects = null;
	
	private static CASLicenseHandlerConfiguration INSTANCE = null;
	
	private CASLicenseHandlerConfiguration() {
		
	}
	
	/**
	 * Returns the currently used instance.
	 * @return the currently used instance.
	 */
	public static CASLicenseHandlerConfiguration getInstance() {
		if (isRefactoringFinished()) {
			INSTANCE = new CASLicenseHandlerConfiguration();
		}
		return INSTANCE;
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
	 * @param srcDirectory
	 * 				represents the source directory
	 * @param leadingSrcPath
	 * 				represents the leading source path
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
	 * Set the variant id.
	 * @param newVariationPointID
	 * 				represents the variant id.
	 */
	public void setVariationPoint(VariationPoint vp) {
		this.variationPoint = vp;
	}
	
	/**
	 * Returns the corresponding variation point to the id.
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
	 * 			returns the variant by the id.
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

	public void setLicenseValidatorType(IType type) {
		this.licenseValidatorType = type;
	}
	
	public IType getLicenseValidatorType() {
		return this.licenseValidatorType;
	}
	
	public IType getLicenseConstantType() {
		return this.licenseConstantsType;
	}
	
	public void setLicenseConstantType(IType newLicenseConstantClass) {
		this.licenseConstantsType = newLicenseConstantClass;
	}
	
	public String[] getAllLicenses() {
		
		if(null == this.licenseConstantsType) {
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
	
	public VariationPointModel getVariationPointModel() {
	    return (VariationPointModel)this.getVariationPoint().eContainer().eContainer();
	}
	
	public static void refactoringStarted() {
       synchronized(refactoringFinishedMonitor) {
           refactoringFinished = false;
           refactoringFinishedMonitor.notifyAll();
        }
	}
	
	public static void refactoringFinished() {
       synchronized(refactoringFinishedMonitor) {
           refactoringFinished = true;
           refactoringFinishedMonitor.notifyAll();
        }   
	}
	
	public static void waitForRefactoringToBeFinished() throws InterruptedException {
	    synchronized(refactoringFinishedMonitor) {
	        while (!refactoringFinished) {
	            refactoringFinishedMonitor.wait();
	        }
	    }
	}
	
	public static boolean isRefactoringFinished() {
       synchronized(refactoringFinishedMonitor) {
           return refactoringFinished;
        }
	}
}

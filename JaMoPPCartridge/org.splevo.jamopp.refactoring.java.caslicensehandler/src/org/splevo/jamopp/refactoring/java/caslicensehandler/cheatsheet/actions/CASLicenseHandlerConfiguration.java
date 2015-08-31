package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.splevo.ui.vpexplorer.explorer.ExplorerMediator;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

import com.google.common.collect.Maps;

/**
 * Configuration class for the cheat sheet. Stores values which are needed in another contexts.
 */
public class CASLicenseHandlerConfiguration {

	private static File licenseConstants = null;
	private static String licenseValidatorName = "";
	private static Map<String, String> variantToLicenseMap = new  HashMap<String, String>();
	private static String variationPointID = "";
	private static Map<String, Object> refactoringConfigurations = Maps.newHashMap();
	
	/**
	 * Maps the src-directory to the leading path.
	 * @param srcDirectory
	 * 				represents the source directory
	 * @param leadingSrcPath
	 * 				represents the leading source path
	 */
	public static void setRefactoringConfigurations(String srcDirectory, Object leadingSrcPath) {
		refactoringConfigurations.put(srcDirectory, leadingSrcPath);
	}
	
	/**
	 * Returns the stored refactoring configuration map.
	 * @return
	 * 			returns the stored refactoring configuration map.
	 */
	public static Map<String, Object> getRefactoringConfigurations() {
		return refactoringConfigurations;
	}
	
	/**
	 * Set the variant id.
	 * @param newVariationPointID
	 * 				represents the variant id.
	 */
	public static void setVariationPointID(String newVariationPointID) {
		variationPointID = newVariationPointID;
	}
	
	/**
	 * Returns the corresponding variation point to the id.
	 * @return 
	 * 			returns a variation point.
	 */
	public static VariationPoint getVariationPoint() {
		for (VariationPointGroup vpGroup : ExplorerMediator.getInstance().getCurrentVPM().getVariationPointGroups()) {
			for (VariationPoint variationPoint : vpGroup.getVariationPoints()) {
				if (variationPointID.equals(variationPoint.getId())) {
					return variationPoint;
				}
			}
		}
		
		return null;
	}
	
	public static Variant getVariantBy(String variantID) {
		for (Variant variant : getVariationPoint().getVariants()) {
			if (variant.getId().equals(variantID)) {
				return variant;
			}
		}
		return null;
	}
	
	public static Map<String, String> getVariantToLicenseMap() {
		return variantToLicenseMap;
	}
	
	public static void addVariantLicensePair(String variantID, String license) {
		variantToLicenseMap.put(variantID, license);
	}
	
	public static void setLicenseValidatorName(String newLicenseValidatorName) {
		licenseValidatorName = newLicenseValidatorName;
	}
	
	public static String getLicenseValidatorName() {
		return licenseValidatorName;
	}
	
	public static File getLicenseConstant() {
		return licenseConstants;
	}
	
	public static void setLicenseConstant(File newLicenseConstantClass) {
		licenseConstants = newLicenseConstantClass;
	}
	
	public static String[] getAllLicenses() {
		
		if(null == licenseConstants) {
			return null;
		}
		
        Resource resource = JaMoPPRoutines.getResourceOf(licenseConstants);
        		
		return getAllLicensesBy((CompilationUnit) resource.getContents().get(0));
	}

	private static String[] getAllLicensesBy(CompilationUnit compilationUnit) {
		ArrayList<String> licenses = new ArrayList<String>();
		
		for (ConcreteClassifier concreteClassifier : compilationUnit.getClassifiers()) {
			for (Field field : concreteClassifier.getFields()) {
					licenses.add(field.getName());
			}
		}
		
		return licenses.toArray(new String[licenses.size()]);
	}
}

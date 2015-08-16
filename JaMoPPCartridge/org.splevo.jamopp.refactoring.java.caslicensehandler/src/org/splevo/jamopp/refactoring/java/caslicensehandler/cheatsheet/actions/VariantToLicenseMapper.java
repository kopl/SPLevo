package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.HashMap;
import java.util.Map;

import org.splevo.vpm.variability.Variant;

public class VariantToLicenseMapper {
	private static Map<Variant, String> variantToLicenseMap = new  HashMap<Variant, String>();
	
	public static String getLicenseBy(Variant variant) {
		return variantToLicenseMap.get(variant);
	}
	
	public static void addVariantLicensePair(Variant variant, String license) {
		variantToLicenseMap.put(variant, license);
	}
}

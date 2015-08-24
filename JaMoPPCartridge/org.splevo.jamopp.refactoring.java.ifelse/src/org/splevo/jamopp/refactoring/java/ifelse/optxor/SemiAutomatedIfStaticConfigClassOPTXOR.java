package org.splevo.jamopp.refactoring.java.ifelse.optxor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.splevo.jamopp.refactoring.java.ifelse.util.SemiAutomatedIfElseRefactoring;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.VariationPoint;

public class SemiAutomatedIfStaticConfigClassOPTXOR extends IfStaticConfigClassOPTXOR {

	public SemiAutomatedIfStaticConfigClassOPTXOR(String validatorName, Map<String, String> variantToLicenseMap) {
    	super(new SemiAutomatedIfElseRefactoring(validatorName, variantToLicenseMap));
    }
	
	@Override
    protected List<Resource> refactorFullyAutomated(VariationPoint variationPoint, Map<String, Object> refactoringOptions) {
		return removeAllStaticClassImportsFrom(super.refactorFullyAutomated(variationPoint, refactoringOptions));
	}

	private List<Resource> removeAllStaticClassImportsFrom(List<Resource> changedResources) {
		List<Resource> resourcesToRemove = new ArrayList<Resource>();
		for (Resource resource : changedResources) {
			CompilationUnit compilationUnit = (CompilationUnit) resource.getContents().get(0);
			
			List<Import> importsToRemove = new ArrayList<Import>();
			for (Import classImport : compilationUnit.getImports()) {
				if (checkNamespace(classImport.getNamespaces())) {
					importsToRemove.add(classImport);
				}
			}
			compilationUnit.getImports().removeAll(importsToRemove);
			
			if (compilationUnit.getClassifiers().get(0).getName().equals("SPLConfig")) {
				resourcesToRemove.add(resource);
			}
		}
		changedResources.removeAll(resourcesToRemove);
		
        return changedResources;
	}

	private boolean checkNamespace(EList<String> namespaces) {
		String[] ifStaticConfigNamespace = new String[] {"org", "splevo", "config"};
		
		for (int i = 0; i < namespaces.size(); i++) {
			if (!ifStaticConfigNamespace[i].equals(namespaces.get(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	 @Override
	 public boolean canBeAppliedTo(VariationPoint variationPoint) {
		 boolean correctBindingTime = (variationPoint.getBindingTime() == BindingTime.RUN_TIME);
		 boolean correctVariabilityType = variationPoint.getVariabilityType() == VariabilityType.OPTXOR;
		 boolean correctExtensibility = variationPoint.getExtensibility() == Extensible.NO;
		 boolean correctCharacteristics = correctBindingTime && correctVariabilityType && correctExtensibility;
		 
		 if (!correctCharacteristics) {
			 return false;
		 }

		 for (VariabilityRefactoring refactoring : this.availableRefactorings) {
			 if (refactoring.canBeAppliedTo(variationPoint)) {
				 return true;
			 }
		 }

	        return false;
	    }
}

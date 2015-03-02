package org.splevo.refactoring;

import org.apache.log4j.Logger;
import org.splevo.commons.registry.IdBasedRegistryBase;

import com.google.common.base.Strings;

public enum VariabilityRefactoringRegistry {
    INSTANCE;
    
    private final InnerVariabilityRefactoringRegistry innerRegistry = new InnerVariabilityRefactoringRegistry();
    
    public static InnerVariabilityRefactoringRegistry getInstance() {
        return INSTANCE.innerRegistry;
    }
    
    /**
     * This singleton class handles the registration of available refactoring extensions.<br>
     * Use registerRefactoring() to do so.
     */
    public class InnerVariabilityRefactoringRegistry extends IdBasedRegistryBase<VariabilityRefactoring, String> {
        
        private final Logger logger = Logger.getLogger(InnerVariabilityRefactoringRegistry.class);

        @Override
        protected int compareElements(VariabilityRefactoring element1, VariabilityRefactoring element2) {
            String name1 = element1.getVariabilityMechanism().getName();
            String name2 = element2.getVariabilityMechanism().getName();
            return Strings.nullToEmpty(name1).compareTo(name2);
        }
        
        @Override
        protected boolean isValid(VariabilityRefactoring element) {
            if (!super.isValid(element)) {
                return false;
            }
            if (element.getVariabilityMechanism() == null) {
                logger.warn("Tried to register refactoring without a variability mechanism " + element);
                return false;
            }

            return true;
        }

    }
    
}
package org.splevo.refactoring;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.resource.JaMoPPUtil;
import org.emftext.language.refactoring.refactoring_specification.RefactoringSpecificationPackage;
import org.emftext.language.refactoring.rolemapping.RoleMapping;
import org.emftext.language.refactoring.rolemapping.RolemappingPackage;
import org.emftext.language.refactoring.rolemapping.resource.rolemapping.mopp.RolemappingResourceFactory;
import org.emftext.language.refactoring.roles.RolesPackage;
import org.emftext.language.refactoring.roles.resource.rolestext.mopp.RolestextResourceFactory;
import org.emftext.language.refactoring.specification.resource.mopp.RefspecResourceFactory;
import org.emftext.refactoring.registry.rolemapping.IRoleMappingRegistry;

/**
 * This singleton class handles the registration of all available refactorings. Use
 * registerRefactorings to do so.
 */
public final class RefactoringRegistry {

    private static Logger logger = Logger.getLogger(RefactoringRegistry.class);

     /**
     * Initializes JaMoPP, required packages and factories.
     */
    public static final void initialize() {
        JaMoPPUtil.initialize();
        EPackage.Registry.INSTANCE.put(RolesPackage.eNS_URI, RolesPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(RolemappingPackage.eNS_URI, RolemappingPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(RefactoringSpecificationPackage.eNS_URI,
                RefactoringSpecificationPackage.eINSTANCE);
        Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        extensionToFactoryMap.put("rolestext", new RolestextResourceFactory());
        extensionToFactoryMap.put("rolemapping", new RolemappingResourceFactory());
        extensionToFactoryMap.put("refspec", new RefspecResourceFactory());
    }
    
    /**
     * Gets the {@link RoleMapping} that is registered for a given concrete refactoring name.
     * 
     * @param refactoringName
     *            The {@link String} name.
     * @return The {@link RoleMapping}.
     */
    public static RoleMapping getRoleMapping(String refactoringName) {
        Map<String, RoleMapping> registeredRoleMappings = IRoleMappingRegistry.INSTANCE
                .getRoleMappingsForUri(JavaPackage.eNS_URI);
        if (registeredRoleMappings == null) {
            logger.error("No concrete refactorings registered for java.");
        }

        RoleMapping roleMapping = registeredRoleMappings.get(refactoringName);
        if (roleMapping == null) {
            logger.error("Concrete refactoring '" + refactoringName + "' not registered.");
        }

        return roleMapping;
    }
}

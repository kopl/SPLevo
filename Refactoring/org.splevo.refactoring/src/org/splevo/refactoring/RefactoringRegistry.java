package org.splevo.refactoring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.resource.JaMoPPUtil;
import org.emftext.language.refactoring.refactoring_specification.RefactoringSpecification;
import org.emftext.language.refactoring.refactoring_specification.RefactoringSpecificationPackage;
import org.emftext.language.refactoring.rolemapping.RoleMapping;
import org.emftext.language.refactoring.rolemapping.RoleMappingModel;
import org.emftext.language.refactoring.rolemapping.RolemappingPackage;
import org.emftext.language.refactoring.rolemapping.resource.rolemapping.mopp.RolemappingResourceFactory;
import org.emftext.language.refactoring.roles.RoleModel;
import org.emftext.language.refactoring.roles.RolesPackage;
import org.emftext.language.refactoring.roles.resource.rolestext.mopp.RolestextResourceFactory;
import org.emftext.language.refactoring.specification.resource.mopp.RefspecResourceFactory;
import org.emftext.refactoring.registry.refactoringspecification.IRefactoringSpecificationRegistry;
import org.emftext.refactoring.registry.refactoringspecification.exceptions.RefSpecAlreadyRegisteredException;
import org.emftext.refactoring.registry.rolemapping.IRefactoringPostProcessor;
import org.emftext.refactoring.registry.rolemapping.IRoleMappingRegistry;
import org.emftext.refactoring.registry.rolemapping.exceptions.RoleMappingAlreadyRegistered;
import org.emftext.refactoring.registry.rolemodel.IRoleModelRegistry;
import org.emftext.refactoring.registry.rolemodel.exceptions.RoleModelAlreadyRegisteredException;

/**
 * This singleton class handles the registration of all available refactorings. Use
 * registerRefactorings to do so.
 */
public final class RefactoringRegistry {

    private static Logger logger = Logger.getLogger(RefactoringRegistry.class);

    // refactoring folders
    private static final String REFACTORING_FOLDER = File.separator + "refactoring" + File.separator;
    private static final String ROLE_MODEL_FOLDER = REFACTORING_FOLDER + "rolemodels" + File.separator;
    private static final String REF_SPEC_FOLDER = REFACTORING_FOLDER + "refspecs" + File.separator;
    private static final String ROLE_MAPPING_FOLDER = REFACTORING_FOLDER + "rolemappings" + File.separator;

    // filenames of all refactorings (without file extension)
    private static Set<String> roleModels;
    private static Set<String> refSpecs;
    private static Set<String> roleMappings;

    // post processors for role mappings (name without file extension)
    private static Map<String, Set<IRefactoringPostProcessor>> mappingPostProcessors;

    static {
        initMembers();

        // add role models here
        // add ref specs here
        // add role mappings here
        // add post processors here
    }

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
     * Registers all refactorings. That includes Role Models, Ref Specs, Role Mappings and related
     * PostProcessors.
     */
    public static final void registerRefactorings() {
        for (String rmName : roleModels) {
            try {
                registerRoleModel(rmName);
            } catch (RoleModelAlreadyRegisteredException e) {
                logger.error("Role Model already registered", e);
            } catch (FileNotFoundException e) {
                logger.error("Could not find role model file: " + rmName);
            }
        }
        for (String rsName : refSpecs) {
            try {
                registerRefSpec(rsName);
            } catch (RefSpecAlreadyRegisteredException e) {
                logger.error("Ref Spec already registered", e);
            } catch (FileNotFoundException e) {
                logger.error("Could not find ref spec file: " + rsName);
            }
        }
        for (String rmName : roleMappings) {
            try {
                registerRoleMappingWithPostProcessors(rmName);
            } catch (RoleMappingAlreadyRegistered e) {
                logger.error("Role Mapping already registered", e);
            } catch (FileNotFoundException e) {
                logger.error("Could not find role mapping file: " + rmName);
            }
        }
    }

    /**
     * Unregisters all refactorings. That includes Role Models, Ref Specs and Role Mappings.
     */
    public static final void unregisterRefactorings() {
        for (String rmName : roleModels) {
            unregisterModelByType(rmName, RoleModel.class);
        }
        for (String rsName : refSpecs) {
            unregisterModelByType(rsName, RefactoringSpecification.class);
        }
        for (String rmName : roleMappings) {
            unregisterModelByType(rmName, RoleMapping.class);
        }
    }

    /**
     * Gets the {@link RoleMapping} that is registered for a given concrete refactoring name.
     * 
     * @param refactoringName
     *            The {@link String} name.
     * @return The {@link RoleMapping}.
     */
    public RoleMapping getRoleMapping(String refactoringName) {
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

    /**
     * Registers the role model of the given name.
     * 
     * @param name
     *            The name.
     * @throws RoleModelAlreadyRegisteredException
     *             If the model is already registered.
     * @throws FileNotFoundException
     *             thrown if no file was found at the given path.
     */
    private static void registerRoleModel(String name) throws RoleModelAlreadyRegisteredException,
            FileNotFoundException {
        RoleModel roleModel = getModelByType(ROLE_MODEL_FOLDER + name + ".rolestext", RoleModel.class);
        IRoleModelRegistry.INSTANCE.registerRoleModel(roleModel);

        logger.info("Role Model registered: " + name);
    }

    /**
     * Registers the ref specs of the given name.
     * 
     * @param name
     *            The name.
     * @throws RefSpecAlreadyRegisteredException
     *             If the model is already registered.
     * @throws FileNotFoundException
     *             thrown if no file was found at the given path.
     */
    private static void registerRefSpec(String name) throws RefSpecAlreadyRegisteredException, FileNotFoundException {
        RefactoringSpecification refSpec = getModelByType(REF_SPEC_FOLDER + name + ".refspec",
                RefactoringSpecification.class);
        IRefactoringSpecificationRegistry.INSTANCE.registerRefSpec(refSpec);

        logger.info("Ref Spec registered: " + name);
    }

    /**
     * Registers the role mappings of a given name and related post processors.
     * 
     * @param name
     *            The name.
     * @throws RoleMappingAlreadyRegistered
     *             If the model is already registered.
     * @throws FileNotFoundException
     *             thrown if no file was found at the given path.
     */
    private static void registerRoleMappingWithPostProcessors(String name) throws RoleMappingAlreadyRegistered,
            FileNotFoundException {
        RoleMappingModel roleMappingModel = getModelByType(ROLE_MAPPING_FOLDER + name + ".rolemapping",
                RoleMappingModel.class);
        RoleMapping roleMapping = roleMappingModel.getMappings().get(0);
        IRoleMappingRegistry.INSTANCE.registerRoleMapping(roleMapping);

        for (IRefactoringPostProcessor pp : mappingPostProcessors.get(name)) {
            IRoleMappingRegistry.INSTANCE.registerPostProcessor(roleMapping, pp);
        }

        logger.info("Role Mapping registered: " + name);
    }

    /**
     * Unregisters a given {@link RoleModel}s, {@link RefactoringSpecification}s or
     * {@link RoleMapping}s.
     * 
     * @param model
     *            The model.
     */
    private static <T extends EObject> void unregisterModelByType(String name, Class<T> type) {
        try {
            if (type.isAssignableFrom(RoleModel.class)) {
                RoleModel model = getModelByType(ROLE_MODEL_FOLDER + name + ".rolestext", RoleModel.class);
                IRoleModelRegistry.INSTANCE.unregisterRoleModel(model);
            }
            if (type.isAssignableFrom(RefactoringSpecification.class)) {
                RefactoringSpecification model = getModelByType(ROLE_MODEL_FOLDER + name + ".rolestext",
                        RefactoringSpecification.class);
                IRefactoringSpecificationRegistry.INSTANCE.unregisterRefSpec(model);
            }
            if (type.isAssignableFrom(RoleMapping.class)) {
                RoleMapping model = getModelByType(ROLE_MODEL_FOLDER + name + ".rolestext", RoleMapping.class);
                IRoleMappingRegistry.INSTANCE.unregisterRoleMapping(model);
            }

            logger.info("Model unregistered: " + name + " (" + type.toString() + ")");
        } catch (FileNotFoundException e) {
            logger.error("Couldn't find model file: " + name);
        }
    }

    /**
     * Gets a model of a specific type from a specified path.
     * 
     * @param path
     *            The path.
     * @param type
     *            The model type.
     * @param <T>
     *            The model type.
     * @return The loaded model.
     * @throws FileNotFoundException
     *             thrown if no file was found at the given path.
     */
    private static <T> T getModelByType(String path, Class<T> type) throws FileNotFoundException {
        if (path == null || type == null) {
            throw new IllegalArgumentException();
        }

        // check whether files exists; throw exception otherwise
        File modelFile = new File(path);
        if (!modelFile.exists()) {
            throw new FileNotFoundException();
        }

        // get resource; throw exception if not found
        ResourceSet rs = new ResourceSetImpl();
        URI uri = URI.createFileURI(modelFile.getAbsolutePath());
        Resource resource = rs.getResource(uri, true);
        if (resource == null) {
            throw new NullPointerException("Resource couldn't be loaded");
        }

        EObject model = resource.getContents().get(0);
        if (model == null) {
            throw new NullPointerException("Resource is empty");
        }
        if (!type.isAssignableFrom(model.getClass())) {
            throw new NullPointerException("Model must be an instance of " + type.getName());
        }

        return type.cast(model);
    }

    /**
     * Initializes the map and set members that hold the refactoring information.
     */
    private static void initMembers() {
        RefactoringRegistry.roleModels = new HashSet<String>();
        RefactoringRegistry.refSpecs = new HashSet<String>();
        RefactoringRegistry.roleMappings = new HashSet<String>();
        RefactoringRegistry.mappingPostProcessors = new HashMap<String, Set<IRefactoringPostProcessor>>();
    }
}

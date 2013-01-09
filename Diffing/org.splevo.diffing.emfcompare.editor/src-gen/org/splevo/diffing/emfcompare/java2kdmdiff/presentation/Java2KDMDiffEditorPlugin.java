/**
 */
package org.splevo.diffing.emfcompare.java2kdmdiff.presentation;

import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.ui.EclipseUIPlugin;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.compare.diff.provider.DiffEditPlugin;

import org.eclipse.emf.compare.match.metamodel.provider.MatchEditPlugin;

import org.eclipse.emf.ecore.provider.EcoreEditPlugin;

import org.eclipse.gmt.modisco.java.emf.provider.JavaEditPlugin;

/**
 * This is the central singleton for the Java2KDMDiff editor plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class Java2KDMDiffEditorPlugin extends EMFPlugin {
	/**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final Java2KDMDiffEditorPlugin INSTANCE = new Java2KDMDiffEditorPlugin();
	
	/**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static Implementation plugin;

	/**
     * Create the instance.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Java2KDMDiffEditorPlugin() {
        super
            (new ResourceLocator [] {
                DiffEditPlugin.INSTANCE,
                EcoreEditPlugin.INSTANCE,
                JavaEditPlugin.INSTANCE,
                MatchEditPlugin.INSTANCE,
            });
    }

	/**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
	@Override
	public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }
	
	/**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
	public static Implementation getPlugin() {
        return plugin;
    }
	
	/**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static class Implementation extends EclipseUIPlugin {
		/**
         * Creates an instance.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		public Implementation() {
            super();
    
            // Remember the static instance.
            //
            plugin = this;
        }
	}

}

/**
 */
package org.splevo.vpm.variability.provider;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.featuremodel.provider.FeatureModelEditPlugin;
import org.eclipse.gmt.modisco.java.emf.provider.JavaEditPlugin;
import org.eclipse.gmt.modisco.omg.kdm.core.provider.KdmEditPlugin;
import org.eclipse.modisco.java.composition.javaapplication.provider.JavaApplicationEditPlugin;
import org.eclipse.modisco.kdm.source.extension.provider.KdmSourceExtensionEditPlugin;

/**
 * This is the central singleton for the vpm edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class vpmEditPlugin extends EMFPlugin {
	/**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final vpmEditPlugin INSTANCE = new vpmEditPlugin();

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
	public vpmEditPlugin() {
        super
          (new ResourceLocator [] {
             EcoreEditPlugin.INSTANCE,
             FeatureModelEditPlugin.INSTANCE,
             JavaEditPlugin.INSTANCE,
             JavaApplicationEditPlugin.INSTANCE,
             KdmEditPlugin.INSTANCE,
             KdmSourceExtensionEditPlugin.INSTANCE,
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
	public static class Implementation extends EclipsePlugin {
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

/**
 *
 */
package org.splevo.jamopp.vpm;

import org.splevo.jamopp.vpm.software.softwarePackage;
import org.splevo.vpm.VPMExtension;

/**
 * Life cycle management for the variation point model extension.
 */
public class JaMoPPVPMExtension implements VPMExtension {

    @Override
    public void init() {
        softwarePackage.eINSTANCE.eClass();
    }

    @Override
    public String getName() {
        return "JaMoPPVPMExtension";
    }

}

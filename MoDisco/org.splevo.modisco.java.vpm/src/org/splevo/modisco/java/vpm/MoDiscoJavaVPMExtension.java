/**
 * 
 */
package org.splevo.modisco.java.vpm;

import org.splevo.modisco.java.vpm.software.softwarePackage;
import org.splevo.vpm.VPMExtension;

/**
 * Life cycle management for the variation point model extension.
 */
public class MoDiscoJavaVPMExtension implements VPMExtension {

    @Override
    public void init() {
        softwarePackage.eINSTANCE.eClass();
    }

    @Override
    public String getName() {
        return "MoDiscoJavaVPMExtension";
    }

}

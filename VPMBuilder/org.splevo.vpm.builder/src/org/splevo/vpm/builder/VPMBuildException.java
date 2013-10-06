/**
 * 
 */
package org.splevo.vpm.builder;

/**
 * Exception identifying an error during the VPM build process.
 */
public class VPMBuildException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public VPMBuildException() {
    }

    /**
     * {@inheritDoc}
     */
    public VPMBuildException(String arg0) {
        super(arg0);
    }

    /**
     * {@inheritDoc}
     */
    public VPMBuildException(Throwable arg0) {
        super(arg0);
    }

    /**
     * {@inheritDoc}
     */
    public VPMBuildException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}

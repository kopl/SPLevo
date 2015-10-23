package org.splevo.ui.vpexplorer.explorer;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;

/**
 * Deals with the events that are generated as controls gain and lose focus in the context of key bindings. 
 */
public class KeyFocusListener implements FocusListener {

    private IContextActivation activation;
    private String contextID;
    
    /**
     * Creates a new KeyFocusListener.
     * @param contextId the id of the context
     */
    public KeyFocusListener(String contextId) {
        this.contextID = contextId;
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        activation = ((IContextService) PlatformUI.getWorkbench().getService(IContextService.class))
                .activateContext(contextID);
    }

    @Override
    public void focusLost(FocusEvent e) {
        ((IContextService) PlatformUI.getWorkbench().getService(IContextService.class))
                .deactivateContext(activation);              
    }          

}

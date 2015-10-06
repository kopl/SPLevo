/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.commons.vpm;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringRegistry;
import org.splevo.vpm.realization.VariabilityMechanism;
import org.splevo.vpm.variability.VariationPoint;

/**
 * Utility class for setting attributes of VPM objects.
 */
public class VPMAttributeSetter {
    
    /**
     * Action object for setting and reverting values of a given EObject.
     * @param <T> The type of the EObject to be processed.
     */
    public interface SetAndRevertAction<T extends EObject> {
        
        /**
         * Sets values of the given object.
         * @param eObject The object to be processed.
         */
        public void set(T eObject);
        
        /**
         * Reverts values of the given object.
         * @param eObject The object to be processed.
         */
        public void revert(T eObject);
    }
    
    /**
     * Applies the given action to the given variation point if this is possible.
     * @param action The action to be applied.
     * @param vp The variation point to be processed.
     * @return True if the action has been applied successfully, False otherwise.
     */
    public static boolean applyIfPossible(SetAndRevertAction<VariationPoint> action, VariationPoint vp) {
        action.set(vp);
        VariabilityMechanism mechanism = vp.getVariabilityMechanism();
        if (mechanism == null) { 
            return true;
        }
        VariabilityRefactoring ref = VariabilityRefactoringRegistry.getInstance().getElementById(mechanism.getRefactoringID());
        if (ref == null) {
            return true;
        }
        Diagnostic diagnostic = ref.canBeAppliedTo(vp);
        if (diagnostic.getSeverity() != Diagnostic.OK) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("The following errors occured during validation:\n\n");
            errorMessage.append(diagnostic.getMessage() + "\n");   
            errorMessage.append("\nYour changes have been discarded.");
            
            MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), "Validation Errors", null, 
                    errorMessage.toString(), 0, getDialogLabels(diagnostic), 0);
            
            if (dialog.open() == 1) {  
                MessageDialog.openInformation(Display.getDefault().getActiveShell(), 
                        "Validation Errors", getDiagnosticChildrenMessage(diagnostic));
            }
            
            action.revert(vp);
            return false;
        }
        return true;
    }
    
    private static String[] getDialogLabels(Diagnostic diagnostic) {
        if (diagnostic.getChildren().size() > 0) {
            return new String[] {"OK", "More Information"};
        }
        return new String[] {"OK"};
    }
        
    private static String getDiagnosticChildrenMessage(Diagnostic diagnostic) {
        StringBuilder message = new StringBuilder();
        message.append("The following errors occured during validation:\n\n");                
        for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
            message.append(childDiagnostic.getMessage() + "\n");
        }
        return message.toString();
    }   
}

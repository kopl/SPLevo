/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.ui.listeners;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.splevo.project.SPLProfile;
import org.splevo.project.SPLevoProject;
import org.splevo.refactoring.RecommenderResult;
import org.splevo.refactoring.VariabilityRefactoring;
import org.splevo.refactoring.VariabilityRefactoringRegistry;
import org.splevo.refactoring.VariabilityRefactoringService;
import org.splevo.ui.editors.SPLevoProjectEditor;
import org.splevo.ui.jobs.JobUtil;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * Mouse adapter to check the latest vpm and trigger the refactoring.
 */
public class StartRefactoringListener extends MouseAdapter {

    private static Logger logger = Logger.getLogger(StartRefactoringListener.class);

    /** The internal reference to the splevo project editor to work with. */
    private SPLevoProjectEditor splevoProjectEditor = null;

    /**
     * Constructor requiring the reference to a splevoProject.
     *
     * @param splevoProjectEditor
     *            The references to the splevo project editor.
     */
    public StartRefactoringListener(SPLevoProjectEditor splevoProjectEditor) {
        this.splevoProjectEditor = splevoProjectEditor;
    }

    @Override
    public void mouseUp(MouseEvent e) {

        if (!checkVPMExists()) {
            return;
        }

        VariationPointModel vpm = loadVPM();
        if (vpm == null) {
            MessageDialog.openError(getShell(), "Failed to load VPM", "Unable to laod the latest VPM");
            return;
        }

        boolean vpmReady = checkAllMechanismsSelected(vpm);
        if (vpmReady) {
            startRefactoring();
        } else {
            boolean executeRecommender = askForRecommenderExecution();
            if (executeRecommender) {
                executeRecommender(vpm);
            }
            return;
        }
    }

    private boolean askForRecommenderExecution() {
        boolean executeRecommender = MessageDialog
                .openConfirm(getShell(), "Execute Recommender",
                        "Not all variation points have a variability mechanism defined, yet. Do you want to start the auto recommender?");
        return executeRecommender;
    }

    private Shell getShell() {
        Shell shell = Display.getCurrent().getActiveShell();
        return shell;
    }

    private void startRefactoring() {
        MessageDialog.openInformation(getShell(), "Refactoring comming soon",
                "The refactoring service will be available in a future release.");
    }

    private void executeRecommender(VariationPointModel vpm) {
        VariabilityRefactoringService service = new VariabilityRefactoringService();
        SPLProfile splProfile = splevoProjectEditor.getSplevoProject().getSplProfile();
        List<String> refactoringIds = splProfile.getRecommendedRefactoringIds();
        List<VariabilityRefactoring> refactorings = getRefactorings(refactoringIds);

        RecommenderResult result = service.recommendMechanisms(vpm, refactorings);

        if (result.getUnassignedVariationPoints().isEmpty()) {
            MessageDialog.openInformation(getShell(), "Recommender Succeeded",
                    "All variation points are now successfully assigned with a variability mechanism");
        } else {
            MessageDialog.openWarning(getShell(), "Recommender Failed",
                    "Not all variation points could be assigned with a variability mechanism");
        }
    }

    private List<VariabilityRefactoring> getRefactorings(List<String> refactoringIds) {
        List<VariabilityRefactoring> refactorings = Lists.newArrayList();
        for (String refactoringId : refactoringIds) {
            VariabilityRefactoring refactoring = VariabilityRefactoringRegistry.getRefactoringById(refactoringId);
            if (refactoring != null) {
                refactorings.add(refactoring);
            } else {
                logger.warn("Refactoring specified in splProfile not registered (" + refactoringId + ")");
            }
        }
        return refactorings;
    }

    private boolean checkAllMechanismsSelected(VariationPointModel vpm) {
        for (VariationPointGroup group : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : group.getVariationPoints()) {
                if (vp.getVariabilityMechanism() == null
                        || Strings.isNullOrEmpty(vp.getVariabilityMechanism().getRefactoringID())) {
                    return false;
                }
            }
        }
        return true;
    }

    private VariationPointModel loadVPM() {

        SPLevoProject splevoProject = splevoProjectEditor.getSplevoProject();

        int index = splevoProject.getVpmModelPaths().size() - 1;
        String vpmPath = splevoProject.getVpmModelPaths().get(index);

        ResourceSet rs = JobUtil.initResourceSet(splevoProject);
        try {
            return VPMUtil.loadVariationPointModel(new File(vpmPath), rs);
        } catch (IOException e) {
            logger.error("Failed to load VPM", e);
        }

        return null;
    }

    private boolean checkVPMExists() {
        boolean vpmExistCheck = true;
        if (splevoProjectEditor.getSplevoProject().getVpmModelPaths().size() == 0) {
            MessageDialog.openError(getShell(), "Variation Point Model Missing", "No VPM available.");
            vpmExistCheck = false;
        }
        return vpmExistCheck;
    }
}

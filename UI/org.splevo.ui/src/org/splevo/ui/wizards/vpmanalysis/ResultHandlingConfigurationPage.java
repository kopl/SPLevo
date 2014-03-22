/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.ui.wizards.vpmanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;
import org.splevo.ui.util.UIUtil;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;
import org.splevo.vpm.analyzer.DefaultVPMAnalyzerService;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.refinement.BasicDetectionRule;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.RefinementType;

/**
 * Wizard page to configure how the analysis result should be presented.
 */
public class ResultHandlingConfigurationPage extends WizardPage {

    /**
     * The chosen presentation for the results.
     */
    private ResultPresentation resultPresentation;

    /**
     * Stores the ID's {@link String} labels.
     */
    private Map<Integer, Set<String>> labelsToGroupID;

    /**
     * Stores the ID's {@link RefinementType}s.
     */
    private Map<Integer, RefinementType> refinementTypeToGroupID;

    /**
     * The detection rule label.
     */
    private Label detectionRuleLabel;

    /**
     * This component manages the groups.
     */
    private Group groupListGroup;

    /**
     * The {@link Group} that has the components to manage groups.
     */
    private Group groupDetailGroup;

    /**
     * The table that stores all groups.
     */
    private ListViewer listViewerAnalysis;

    /**
     * The button that removes groups.
     */
    private Button removeRuleButton;

    /**
     * This {@link Composite} manages group details.
     */
    private Composite detailComp;

    private List<VPMAnalyzer> analyzers;

    private List<DetectionRule> detectionRules;

    /**
     * Create the wizard and initialize members.
     *
     * @param defaultConfiguration
     *            The default configuration to initialize the page.
     */
    public ResultHandlingConfigurationPage(VPMAnalysisWorkflowConfiguration defaultConfiguration) {
        super("ResultHandlingConfiguration");

        setTitle("Analysis Result Handling");
        setDescription("Configure how you would like to handle the analysis result.");

        this.analyzers = new LinkedList<VPMAnalyzer>();

        this.labelsToGroupID = new HashMap<Integer, Set<String>>();
        this.refinementTypeToGroupID = new HashMap<Integer, RefinementType>();

        this.resultPresentation = defaultConfiguration.getPresentation();
        this.detectionRules = defaultConfiguration.getDetectionRules();
    }

    /**
     * If no refinement {@link DetectionRule}s are defined yet, initialize the set of default rules.
     *
     * If there are already rules specified, do nothing.
     */
    private void fillInitialRuleMaps() {

        if (detectionRules.size() > 0) {
            return;
        }

        List<String> edgeLabels = new ArrayList<String>();
        DefaultVPMAnalyzerService service = new DefaultVPMAnalyzerService();
        for (VPMAnalyzer analyzer : service.getAvailableAnalyzers()) {
            edgeLabels.add(analyzer.getRelationshipLabel());
        }
        detectionRules.clear();
        detectionRules.add(new BasicDetectionRule(edgeLabels, RefinementType.GROUPING));

        // Update the rule presentation
        labelsToGroupID.clear();
        refinementTypeToGroupID.clear();
        for (DetectionRule rule : detectionRules) {
            int id = findNextFreeID();

            // Add only edge labels that do have a corresponding analyzer.
            this.labelsToGroupID.put(id, new HashSet<String>());
            for (VPMAnalyzer analyzer : analyzers) {
                if (rule.getEdgeLabels().contains(analyzer.getRelationshipLabel())) {
                    labelsToGroupID.get(id).add(analyzer.getRelationshipLabel());
                }
            }

            // Add the refinement type.
            this.refinementTypeToGroupID.put(id, rule.getRefinementType());
        }

        listViewerAnalysis.refresh();
    }

    /**
     * Removes all edge labels that do not have a corresponding analyzer.
     */
    private void removeInvalidEdgeLabelsFromRules() {
        for (Integer id : labelsToGroupID.keySet()) {
            Set<String> labels = labelsToGroupID.get(id);
            Set<String> toBeDeleted = new HashSet<String>();
            for (String label : labels) {
                boolean labelHasAnalyer = false;
                for (VPMAnalyzer analyzer : this.analyzers) {
                    if (analyzer.getRelationshipLabel().equals(label)) {
                        labelHasAnalyer = true;
                        break;
                    }
                }
                if (!labelHasAnalyer) {
                    toBeDeleted.add(label);
                }
            }
            labels.removeAll(toBeDeleted);
        }
    }

    /**
     * Create contents of the wizard.
     *
     * @param parent
     *            The parent ui element to place this one into.
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new FormLayout());
        setControl(container);

        Group resultPresentationGrp = generateResultPresentationGroup(container);

        generateGroupComponents(container, resultPresentationGrp);

        boolean isRefinementBrowser = this.resultPresentation == ResultPresentation.REFINEMENT_BROWSER;
        enableRulesDetection(isRefinementBrowser);
    }

    /**
     * Generates the components that handle (create and add) the groups.
     *
     * @param parent
     *            The parent composite.
     * @param presentationGroup
     *            Places the components below this component.
     */
    private void generateGroupComponents(Composite parent, Group presentationGroup) {
        detectionRuleLabel = new Label(parent, SWT.NONE);
        FormData labelFD = new FormData();
        labelFD.top = new FormAttachment(presentationGroup, 20);
        labelFD.left = new FormAttachment(0);
        labelFD.right = new FormAttachment(100);
        labelFD.height = detectionRuleLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
        detectionRuleLabel.setLayoutData(labelFD);
        detectionRuleLabel.setText("Detection Rules");

        groupListGroup = new Group(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(2, true);
        groupListGroup.setLayout(gridLayout);
        groupListGroup.setText("Rules");
        FormData groupFD = new FormData();
        groupFD.top = new FormAttachment(detectionRuleLabel, 10);
        groupFD.bottom = new FormAttachment(100);
        groupFD.left = new FormAttachment(0);
        groupFD.right = new FormAttachment(30);
        groupListGroup.setLayoutData(groupFD);
        GridData gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        listViewerAnalysis = new ListViewer(groupListGroup, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        listViewerAnalysis.setContentProvider(ArrayContentProvider.getInstance());
        listViewerAnalysis.setInput(labelsToGroupID.keySet());
        listViewerAnalysis.setLabelProvider(new RefinementTypeLabelProvider(labelsToGroupID, refinementTypeToGroupID));
        listViewerAnalysis.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        listViewerAnalysis.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                rebuildDetailComp();
            }
        });
        Button addRuleButton = new Button(groupListGroup, SWT.PUSH);
        addRuleButton.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD));
        addRuleButton.setLayoutData(gridData);
        addRuleButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                Set<String> labels = new LinkedHashSet<String>();
                for (VPMAnalyzer analyzer : analyzers) {
                    labels.add(analyzer.getRelationshipLabel());
                }
                int id = findNextFreeID();
                labelsToGroupID.put(id, labels);
                refinementTypeToGroupID.put(id, RefinementType.GROUPING);
                listViewerAnalysis.refresh();
                update();
            }
        });
        removeRuleButton = new Button(groupListGroup, SWT.PUSH);
        removeRuleButton.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/cross.png"));
        removeRuleButton.setEnabled(false);
        removeRuleButton.setLayoutData(gridData);
        removeRuleButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                Integer selectedID = getSelectedID();
                labelsToGroupID.remove(selectedID);
                refinementTypeToGroupID.remove(selectedID);
                listViewerAnalysis.refresh();
                removeRuleDetailComponents();
                detailComp.pack();
                showHowToConfigureRulesInfo();
                update();
            }
        });

        groupDetailGroup = new Group(parent, SWT.NONE);
        groupDetailGroup.setText("Details");
        FormData groupDetailFD = new FormData();
        groupDetailFD.top = new FormAttachment(detectionRuleLabel, 10);
        groupDetailFD.bottom = new FormAttachment(100);
        groupDetailFD.left = new FormAttachment(groupListGroup, 5);
        groupDetailFD.right = new FormAttachment(100);
        groupDetailGroup.setLayoutData(groupDetailFD);
        groupDetailGroup.setLayout(new RowLayout());

        detailComp = new Composite(groupDetailGroup, SWT.NONE);
        GridLayout detailCompLayout = new GridLayout(3, true);
        detailComp.setLayout(detailCompLayout);
    }

    /**
     * Generates a group that allows the user to choose between several result presentation options.
     *
     * @param parent
     *            The parent container.
     * @return The Group.
     */
    private Group generateResultPresentationGroup(Composite parent) {
        Label analysisLabel = new Label(parent, SWT.NONE);
        analysisLabel.setText("Analysis Presentation");
        Group resultPresentationGrp = new Group(parent, SWT.NONE);
        resultPresentationGrp.setText("Choose the view for the analysis results");
        resultPresentationGrp.setLayout(new RowLayout(SWT.HORIZONTAL));
        FormData resultPresentationFD = new FormData();
        resultPresentationFD.top = new FormAttachment(analysisLabel, 10);
        resultPresentationFD.left = new FormAttachment(0);
        resultPresentationFD.right = new FormAttachment(100);
        resultPresentationGrp.setLayoutData(resultPresentationFD);
        Button vpmGraphBtn = new Button(resultPresentationGrp, SWT.RADIO);
        vpmGraphBtn.setText("VPM Graph");
        vpmGraphBtn.setToolTipText("Show VPM Graph only. Refinement detection will be skipped.");
        boolean isVPMGraphActivated = resultPresentation == ResultPresentation.RELATIONSHIP_GRAPH_ONLY;
        vpmGraphBtn.setSelection(isVPMGraphActivated);
        vpmGraphBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                resultPresentation = ResultPresentation.RELATIONSHIP_GRAPH_ONLY;
                enableRulesDetection(false);
                update();
            }
        });
        Button refBrowserBtn = new Button(resultPresentationGrp, SWT.RADIO);
        refBrowserBtn.setText("Refinement Browser");
        refBrowserBtn.setToolTipText("Show analysis results in the refinement browser.");
        refBrowserBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                resultPresentation = ResultPresentation.REFINEMENT_BROWSER;
                enableRulesDetection(true);
                update();
            }
        });
        resultPresentationFD.height = resultPresentationGrp.computeSize(SWT.DEFAULT, SWT.DEFAULT).y - 15;
        refBrowserBtn.setSelection(!isVPMGraphActivated);
        return resultPresentationGrp;
    }

    /**
     * Disposes all children of the detailComp composite.
     */
    protected void removeRuleDetailComponents() {
        for (Control control : detailComp.getChildren()) {
            control.dispose();
        }
    }

    private void showHowToConfigureRulesInfo() {
        Label infoLabel = new Label(detailComp, SWT.NONE);
        infoLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        infoLabel.setText("Select rule on the left to check on configure details.");
    }

    /**
     * Builds the components that display the group's details.
     *
     * @param id
     *            The group's id.
     */
    private void buildDetailComponents(final Integer id) {
        Label organizeLabel = new Label(detailComp, SWT.NONE);
        organizeLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        organizeLabel.setText("How to aggregate related variation points?");
        UIUtil.addExplanation(detailComp, "Use group per default. If a merge is possible, this be detected automatically.");

        Button mergeBtn = new Button(detailComp, SWT.RADIO);
        mergeBtn.setText("Merge");
        mergeBtn.setToolTipText("Recommend merging for related variation points.");
        mergeBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                refinementTypeToGroupID.put(id, RefinementType.MERGE);
                listViewerAnalysis.refresh();
            }
        });
        Button groupBtn = new Button(detailComp, SWT.RADIO);
        groupBtn.setText("Group");
        groupBtn.setToolTipText("Recommend grouping for related variation points.");
        groupBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                refinementTypeToGroupID.put(id, RefinementType.GROUPING);
                listViewerAnalysis.refresh();
            }
        });
        if (refinementTypeToGroupID.get(id).equals(RefinementType.MERGE)) {
            mergeBtn.setSelection(true);
        } else {
            groupBtn.setSelection(true);
        }
        Label labelsLabel = new Label(detailComp, SWT.NONE);
        labelsLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        labelsLabel.setText("Select the relationship combination to detect:");
        UIUtil.addExplanation(detailComp, "The analyzer specific relationships are considered.");

        createAnalyzerSelection(id);
    }

    /**
     * Adds selectable check-boxes with labels for each available analyzer to the details view.
     *
     * @param id
     *            The id of the group which is currently selected.
     */
    private void createAnalyzerSelection(final Integer id) {
        Set<String> alreadyExistingLabels = new HashSet<String>();
        for (final VPMAnalyzer analyzer : this.analyzers) {
            if (!alreadyExistingLabels.add(analyzer.getRelationshipLabel())) {
                continue;
            }

            Button checkBtn = new Button(detailComp, SWT.CHECK);
            checkBtn.setText(analyzer.getRelationshipLabel());
            if (labelsToGroupID.get(id).contains(analyzer.getRelationshipLabel())) {
                checkBtn.setSelection(true);
            }
            checkBtn.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    super.widgetSelected(e);
                    if (((Button) (e.widget)).getSelection()) {
                        labelsToGroupID.get(id).add(analyzer.getRelationshipLabel());
                    } else {
                        labelsToGroupID.get(id).remove(analyzer.getRelationshipLabel());
                    }
                    listViewerAnalysis.refresh();
                }
            });
        }
    }

    /**
     * Get the result presentation configured on the wizard page.
     *
     * @return The result presentation option.
     */
    public ResultPresentation getResultPresentation() {
        return this.resultPresentation;
    }

    /**
     * Get the detection rules configured by the user.
     *
     * @return The list of prepared detection rules.
     */
    public List<DetectionRule> getDetectionRules() {
        LinkedList<DetectionRule> result = new LinkedList<DetectionRule>();
        for (Integer id : labelsToGroupID.keySet()) {
            BasicDetectionRule basicDetectionRule = new BasicDetectionRule(new LinkedList<String>(
                    labelsToGroupID.get(id)), refinementTypeToGroupID.get(id));
            result.add(basicDetectionRule);
        }
        return result;
    }

    /**
     * Enables or disables the components that are responsible for the group handling.
     *
     * @param enabled
     *            Determines whether to enable or disable the components.
     */
    private void enableRulesDetection(boolean enabled) {
        recursiveSetEnabled(groupListGroup, enabled);
        recursiveSetEnabled(groupDetailGroup, enabled);
        detectionRuleLabel.setEnabled(enabled);
    }

    /**
     * Recursively enables or disables the given Control and all it's children.
     *
     * @param ctrl
     *            The control to be en/disabled.
     * @param enabled
     *            Determines whether to enable / disable the control.
     */
    private void recursiveSetEnabled(Control ctrl, boolean enabled) {
        if (ctrl instanceof Composite) {
            Composite comp = (Composite) ctrl;
            for (Control c : comp.getChildren()) {
                recursiveSetEnabled(c, enabled);
            }
        } else {
            if (ctrl != removeRuleButton || !enabled) {
                ctrl.setEnabled(enabled);
            }
        }
    }

    /**
     * Checks the keys from the refinementTypeToGroupID and returns the lowest number that is not
     * part of the set.
     *
     * @return The number.
     */
    private int findNextFreeID() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (!refinementTypeToGroupID.keySet().contains(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the ID that is currently selected in the listViewerAnalysis.
     *
     * @return The ID.
     */
    private Integer getSelectedID() {

        Integer id = null;

        if (listViewerAnalysis.getSelection() instanceof StructuredSelection) {
            Object selection = ((StructuredSelection) listViewerAnalysis.getSelection()).getFirstElement();
            if (selection != null) {
                id = (Integer) selection;
            }
        }
        return id;

    }

    /**
     * Rebuilds the detail view.
     */
    private void rebuildDetailComp() {
        removeRuleDetailComponents();
        detailComp.pack();
        Integer selectedID = getSelectedID();
        if (selectedID == null) {
            removeRuleButton.setEnabled(false);
            showHowToConfigureRulesInfo();
        } else {
            removeRuleButton.setEnabled(true);
            buildDetailComponents(selectedID);
        }
        detailComp.pack();
    }

    /**
     * Set the selected analyzers and adapt the page presentation.
     *
     * @param analyzers
     *            The analyzers that were selected in the configuration page.
     */
    public void setSelectedAnalyzers(List<VPMAnalyzer> analyzers) {
        if (analyzers == null) {
            throw new IllegalArgumentException();
        }

        this.analyzers = analyzers;

        fillInitialRuleMaps();
        removeInvalidEdgeLabelsFromRules();

        rebuildDetailComp();
        enableRulesDetection(resultPresentation == ResultPresentation.REFINEMENT_BROWSER);
        listViewerAnalysis.refresh();
        update();
    }

    /**
     * Updates everything according to the current state. Has to be called manually after state
     * change.
     */
    private void update() {
        getContainer().updateButtons();
    }

    @Override
    public boolean isPageComplete() {
        return resultPresentation == ResultPresentation.RELATIONSHIP_GRAPH_ONLY
                || (resultPresentation == ResultPresentation.REFINEMENT_BROWSER && !labelsToGroupID.isEmpty());
    }

    @Override
    public boolean canFlipToNextPage() {
        return resultPresentation == ResultPresentation.REFINEMENT_BROWSER && !labelsToGroupID.isEmpty();
    }
}

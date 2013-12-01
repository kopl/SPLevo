package org.splevo.ui.wizards.vpmanalysis;

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
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration;
import org.splevo.ui.workflow.VPMAnalysisWorkflowConfiguration.ResultPresentation;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.refinement.BasicDetectionRule;
import org.splevo.vpm.analyzer.refinement.DetectionRule;
import org.splevo.vpm.refinement.RefinementType;

/**
 * Wizard page to configure how the analysis result should be presented.
 * 
 * @author Benjamin Klatt
 * 
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
	private Button rmvBtn;

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
	public ResultHandlingConfigurationPage(
			VPMAnalysisWorkflowConfiguration defaultConfiguration) {
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
	 * Fills the member group maps according to the {@link DetectionRule}s given
	 * in the configuration.
	 */
	private void fillInitialRuleMaps() {
		labelsToGroupID.clear();
		refinementTypeToGroupID.clear();

		for (DetectionRule rule : detectionRules) {
			int id = findNextFreeID();

			// Add only edge labes that do have a corresponding analyzer.
			this.labelsToGroupID.put(id, new HashSet<String>());
			for (VPMAnalyzer analyzer : analyzers) {
				if (rule.getEdgeLabels().contains(
						analyzer.getRelationshipLabel())) {
					labelsToGroupID.get(id)
							.add(analyzer.getRelationshipLabel());
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
	private void generateGroupComponents(Composite parent,
			Group presentationGroup) {
		detectionRuleLabel = new Label(parent, SWT.NONE);
		FormData labelFD = new FormData();
		labelFD.top = new FormAttachment(presentationGroup, 20);
		labelFD.left = new FormAttachment(0);
		labelFD.right = new FormAttachment(100);
		labelFD.height = detectionRuleLabel.computeSize(SWT.DEFAULT,
				SWT.DEFAULT).y;
		detectionRuleLabel.setLayoutData(labelFD);
		detectionRuleLabel.setText("Detection Rules");

		groupListGroup = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, true);
		groupListGroup.setLayout(gridLayout);
		groupListGroup.setText("Groups");
		FormData groupFD = new FormData();
		groupFD.top = new FormAttachment(detectionRuleLabel, 10);
		groupFD.bottom = new FormAttachment(100);
		groupFD.left = new FormAttachment(0);
		groupFD.right = new FormAttachment(30);
		groupListGroup.setLayoutData(groupFD);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		listViewerAnalysis = new ListViewer(groupListGroup, SWT.BORDER
				| SWT.V_SCROLL | SWT.H_SCROLL);
		listViewerAnalysis.setContentProvider(ArrayContentProvider
				.getInstance());
		listViewerAnalysis.setInput(labelsToGroupID.keySet());
		listViewerAnalysis.setLabelProvider(new RefinementTypeLabelProvider(
				labelsToGroupID, refinementTypeToGroupID));
		listViewerAnalysis.getList().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		listViewerAnalysis
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						rebuildDetailComp();
					}
				});
		Button addBtn = new Button(groupListGroup, SWT.PUSH);
		addBtn.setImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_ADD));
		addBtn.setLayoutData(gridData);
		addBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				Set<String> labels = new LinkedHashSet<String>();
				for (VPMAnalyzer analyzer : analyzers) {
					labels.add(analyzer.getRelationshipLabel());
				}
				int id = findNextFreeID();
				labelsToGroupID.put(id, labels);
				refinementTypeToGroupID.put(id, RefinementType.MERGE);
				listViewerAnalysis.refresh();
			}
		});
		rmvBtn = new Button(groupListGroup, SWT.PUSH);
		rmvBtn.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/cross.png"));
		rmvBtn.setEnabled(false);
		rmvBtn.setLayoutData(gridData);
		rmvBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				Integer selectedID = getSelectedID();
				labelsToGroupID.remove(selectedID);
				refinementTypeToGroupID.remove(selectedID);
				listViewerAnalysis.refresh();
				removeDetailComponents();
				detailComp.pack();
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
		detailComp = new Composite(groupDetailGroup, SWT.NONE);
		groupDetailGroup.setLayout(new RowLayout());
		detailComp.setLayout(gridLayout);
	}

	/**
	 * Generates a group that allows the user to choose between several result
	 * presentation options.
	 * 
	 * @param parent
	 *            The parent container.
	 * @return The Group.
	 */
	private Group generateResultPresentationGroup(Composite parent) {
		Label analysisLabel = new Label(parent, SWT.NONE);
		analysisLabel.setText("Analysis Presentation");
		Group resultPresentationGrp = new Group(parent, SWT.NONE);
		resultPresentationGrp
				.setText("Choose the view for the analysis results");
		resultPresentationGrp.setLayout(new RowLayout(SWT.HORIZONTAL));
		FormData resultPresentationFD = new FormData();
		resultPresentationFD.top = new FormAttachment(analysisLabel, 10);
		resultPresentationFD.left = new FormAttachment(0);
		resultPresentationFD.right = new FormAttachment(100);
		resultPresentationGrp.setLayoutData(resultPresentationFD);
		Button vpmGraphBtn = new Button(resultPresentationGrp, SWT.RADIO);
		vpmGraphBtn.setText("VPM Graph");
		vpmGraphBtn
				.setToolTipText("Show VPM Graph only. Refinement detection will be skipped.");
		boolean isVPMGraphActivated = resultPresentation == ResultPresentation.RELATIONSHIP_GRAPH_ONLY;
		vpmGraphBtn.setSelection(isVPMGraphActivated);
		vpmGraphBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				resultPresentation = ResultPresentation.RELATIONSHIP_GRAPH_ONLY;
				enableRulesDetection(false);
			}
		});
		Button refBrowserBtn = new Button(resultPresentationGrp, SWT.RADIO);
		refBrowserBtn.setText("Refinement Browser");
		refBrowserBtn
				.setToolTipText("Show analysis results in the refinement browser.");
		refBrowserBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				resultPresentation = ResultPresentation.REFINEMENT_BROWSER;
				enableRulesDetection(true);
			}
		});
		resultPresentationFD.height = resultPresentationGrp.computeSize(
				SWT.DEFAULT, SWT.DEFAULT).y - 15;
		refBrowserBtn.setSelection(!isVPMGraphActivated);
		return resultPresentationGrp;
	}

	/**
	 * Disposes all children of the detailComp composite.
	 */
	protected void removeDetailComponents() {
		for (Control control : detailComp.getChildren()) {
			control.dispose();
		}
	}

	/**
	 * Builds the components that display the group's details.
	 * 
	 * @param id
	 *            The group's id.
	 */
	private void buildDetailComponents(final Integer id) {
		Label organizeLabel = new Label(detailComp, SWT.NONE);
		organizeLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1));
		organizeLabel.setText("How should the labels be organized?");
		Button mergeBtn = new Button(detailComp, SWT.RADIO);
		mergeBtn.setText("Merge");
		mergeBtn.setToolTipText("Merges labels with the specified label.");
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
		groupBtn.setToolTipText("Creates groups of labels with the same name.");
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
		labelsLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1));
		labelsLabel
				.setText("Choose the labels the rule should be applied for:");
		createAnalyzerSelection(id);
	}

	/**
	 * Adds selectable check-boxes with labels for each available analyzer to
	 * the details view.
	 * 
	 * @param id
	 *            The id of the group which is currently selected.
	 */
	private void createAnalyzerSelection(final Integer id) {
		for (final VPMAnalyzer analyzer : this.analyzers) {
			Button checkBtn = new Button(detailComp, SWT.CHECK);
			checkBtn.setText(analyzer.getRelationshipLabel());
			if (labelsToGroupID.get(id).contains(
					analyzer.getRelationshipLabel())) {
				checkBtn.setSelection(true);
			}
			checkBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					super.widgetSelected(e);
					if (((Button) (e.widget)).getSelection()) {
						labelsToGroupID.get(id).add(
								analyzer.getRelationshipLabel());
					} else {
						labelsToGroupID.get(id).remove(
								analyzer.getRelationshipLabel());
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
			BasicDetectionRule basicDetectionRule = new BasicDetectionRule(
					new LinkedList<String>(labelsToGroupID.get(id)),
					refinementTypeToGroupID.get(id));
			result.add(basicDetectionRule);
		}
		return result;
	}

	/**
	 * Enables or disables the components that are responsible for the group
	 * handling.
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
			if (ctrl != rmvBtn || !enabled) {
				ctrl.setEnabled(enabled);
			}
		}
	}

	/**
	 * Checks the keys from the refinementTypeToGroupID and returns the lowest
	 * number that is not part of the set.
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
			Object selection = ((StructuredSelection) listViewerAnalysis
					.getSelection()).getFirstElement();
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
		removeDetailComponents();
		detailComp.pack();
		Integer selectedID = getSelectedID();
		if (selectedID == null) {
			rmvBtn.setEnabled(false);
		} else {
			rmvBtn.setEnabled(true);
			buildDetailComponents(selectedID);
		}
		detailComp.pack();
	}

	/**
	 * Prepares the GUI for the initial call.
	 * 
	 * @param analyzers The analyzers that were selected in the configuration page.
	 * @param clearRules Indicates whether to restore initial detection rules or not.
	 */
	public void prepareForInitialCall(List<VPMAnalyzer> analyzers,
			boolean clearRules) {
		if (analyzers == null) {
			throw new IllegalArgumentException();
		}

		this.analyzers = analyzers;

		if (clearRules) {
			fillInitialRuleMaps();
		} else {
			removeInvalidEdgeLabelsFromRules();
		}

		rebuildDetailComp();
		listViewerAnalysis.refresh();
	}
}

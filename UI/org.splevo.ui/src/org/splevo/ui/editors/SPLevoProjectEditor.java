package org.splevo.ui.editors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.splevo.extraction.DefaultExtractionService;
import org.splevo.extraction.ExtractionService;
import org.splevo.extraction.SoftwareModelExtractor;
import org.splevo.project.SPLevoProject;
import org.splevo.project.SPLevoProjectUtil;
import org.splevo.ui.editors.listener.ExtractorCheckBoxListener;
import org.splevo.ui.editors.listener.GotoTabMouseListener;
import org.splevo.ui.editors.listener.MarkDirtyListener;
import org.splevo.ui.editors.listener.ProjectDropListener;
import org.splevo.ui.listeners.DiffSourceModelListener;
import org.splevo.ui.listeners.ExtractProjectListener;
import org.splevo.ui.listeners.GenerateFeatureModelListener;
import org.splevo.ui.listeners.InitVPMListener;
import org.splevo.ui.listeners.VPMAnalysisListener;

/**
 * The SPLevo dash board to control the consolidation process as well as editing
 * the SPLevo project configuration.
 * 
 * @author Benjamin Klatt
 * 
 */
public class SPLevoProjectEditor extends EditorPart {

	/** The logger for this class. */
	private Logger logger = Logger.getLogger(SPLevoProjectEditor.class);

	/** The id of the editor. */
	public static final String ID = "org.splevo.ui.editors.SPLevoProjectEditor"; //$NON-NLS-1$

	/** The internal index of the process control tab. */
	private static final int TABINDEX_PROCESS_CONTROL = 0;

	/** The internal index of the project info tab. */
	private static final int TABINDEX_PROJECT_INFOS = 1;

	/** The internal index of the project selection tab. */
	private static final int TABINDEX_PROJECT_SELECTION = 2;

	/** The internal index of the source model tab. */
	private static final int TABINDEX_SOURCE_MODELS = 3;

	/** The internal index of the diffing model tab. */
	private static final int TABINDEX_DIFFING_MODEL = 4;

	/** The splevo project manipulated in the editor instance. */
	private SPLevoProject splevoProject = null;

	/** The tab folder element of the editor. */
	private TabFolder tabFolder;

	/** The project selection tab. */
	private TabItem tbtmProjectSelection = null;

	/**
	 * The table viewer for the leading source models the variant should be
	 * integrated to.
	 */
	private TableViewer viewerLeadingProjects;

	/**
	 * The table column for the leading source models the variant should be
	 * integrated to.
	 */
	private TableColumn tblclmnLeadingProjects;

	/**
	 * The table viewer for the integration source models to be integrated to
	 * the leading one.
	 */
	private TableViewer viewerIntegrationProjects;

	/** The text input for the project name. */
	private Text projectNameInput;

	/** The text input for the project description. */
	private Text infoInput;

	/** The text input for the project's workspace path. */
	private Text workspaceInput;

	/** Flag identifying the dirty state of the editor. */
	private boolean dirtyFlag = false;

	/** The available transfer types for the drag and drop support. */
	private Transfer[] transferTypes = new Transfer[] {
			FileTransfer.getInstance(), LocalSelectionTransfer.getTransfer() };

	/** The supported drag and drop operations. */
	private int dragNDropOperations = DND.DROP_MOVE;

	/** The text input for the name of the leading variant. */
	private Text inputVariantNameLeading;

	/** The text input for the name of the variant to be integrated. */
	private Text inputVariantNameIntegration;

	/** The text input for the path to the model of the leading variant. */
	private Text sourceModelLeadingInput;

	/**
	 * The text input for the path to the model of the variant to be integrated.
	 */
	private Text sourceModelIntegrationInput;

	/** The text input for the path to the diffing model. */
	private Text diffingModelInput;

	/** The text input field for the packages to filter. */
	private Text diffingPackageFilterInput;

	/** Button Project Selection. */
	private Button btnSelectSourceProjects;

	/** Button Extract source models . */
	private Button btnExtractSourceModels;

	/** Button Diffing. */
	private Button btnDiffing;

	/** Button Init VPM. */
	private Button btnInitVpm;

	/** Button Analyze VPM. */
	private Button btnRefineVPM;

	/** Button Generate feature model. */
	private Button btnGenerateFeatureModel;

	/** Button Open Diff. */
	private Button btnOpenDiff;

	/** Button Open VPM. */
	private Button btnOpenVPM;

	/**
	 * Default constructor setting the icon in the editor title.
	 */
	public SPLevoProjectEditor() {
		setTitleImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/splevo.gif"));
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridLayout glParent = new GridLayout(1, false);
		glParent.verticalSpacing = 0;
		glParent.marginWidth = 0;
		glParent.marginHeight = 0;
		glParent.horizontalSpacing = 0;
		parent.setLayout(glParent);

		// init the data tables
		tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gdTabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1);
		gdTabFolder.widthHint = 837;
		gdTabFolder.heightHint = 353;
		tabFolder.setLayoutData(gdTabFolder);

		// create tabs
		createProcessControlTab(TABINDEX_PROCESS_CONTROL);
		createProjectInfoTab(TABINDEX_PROJECT_INFOS);
		createProjectSelectionTab(TABINDEX_PROJECT_SELECTION);
		createSourceModelTab(TABINDEX_SOURCE_MODELS);
		createDiffingModelTab(TABINDEX_DIFFING_MODEL);

		initDataBindings();

		enableButtonsIfInformationAvailable();

	}

	/**
	 * Create the tab to handle the source models.
	 * @param tabIndex The index of the tab within the parent tab folder.
	 */
	private void createSourceModelTab(int tabIndex) {

		TabItem tabItemSourceModel = new TabItem(tabFolder, SWT.NONE, tabIndex);
		tabItemSourceModel.setText("Extraction");

		Composite sourceModelTabComposite = new Composite(tabFolder, SWT.NONE);
		tabItemSourceModel.setControl(sourceModelTabComposite);
		sourceModelTabComposite.setLayout(null);

		Label lblTheSoftwareModels = new Label(sourceModelTabComposite, SWT.NONE);
		lblTheSoftwareModels.setBounds(10, 10, 490, 20);
		lblTheSoftwareModels.setText("Software model extraction configuration and infos.");

		Group groupExtractors = buildExtractorSelectionGroup(sourceModelTabComposite);

		int verticalBase = groupExtractors.getBounds().y + groupExtractors.getBounds().height;
		buildSourceModelPathGroup(sourceModelTabComposite, verticalBase);
	}

	/**
	 * Build a ui group containing information of the currently used source model paths.
	 * 
	 * @param parent The container UI element.
	 * @param verticalBase The vertical base offset to place the group.
	 */
	private void buildSourceModelPathGroup(Composite parent, int verticalBase) {
		Group grpExtractedSourceModels = new Group(parent, SWT.NONE);
		grpExtractedSourceModels.setText("Extracted Source Models");
		grpExtractedSourceModels.setBounds(10, verticalBase + 10, 490, 160);

		Label lblLeadingProjectModel = new Label(grpExtractedSourceModels, SWT.NONE);
		lblLeadingProjectModel.setBounds(10, 31, 191, 20);
		lblLeadingProjectModel.setText("Leading Source Model:");

		sourceModelLeadingInput = new Text(grpExtractedSourceModels, SWT.BORDER);
		sourceModelLeadingInput.setBounds(10, 51, 464, 26);

		Label lblsourceModelIntegration = new Label(grpExtractedSourceModels, SWT.NONE);
		lblsourceModelIntegration.setBounds(10, 91, 191, 20);
		lblsourceModelIntegration.setText("Integration Source Model:");

		sourceModelIntegrationInput = new Text(grpExtractedSourceModels, SWT.BORDER);
		sourceModelIntegrationInput.setBounds(10, 111, 464, 26);
	}

	/**
	 * Build a ui group presenting check boxes to (de-)activate the extractors
	 * to executed or not.
	 * 
	 * @param sourceModelTabComposite
	 *            The parent ui element to place on.
	 * 
	 * @return The newly created group.
	 */
	private Group buildExtractorSelectionGroup(Composite sourceModelTabComposite) {

		Group groupExtractors = new Group(sourceModelTabComposite, SWT.NONE);
		groupExtractors.setText("Extractors");

		List<Button> extractorCheckBoxes = new LinkedList<Button>();

		ExtractionService extractionService = new DefaultExtractionService();
		Map<String, SoftwareModelExtractor> availableExtractors = extractionService
				.getSoftwareModelExtractors();

		int yBase = 25;
		int index = 0;
		int offset = 25;

		for (String key : availableExtractors.keySet()) {

			SoftwareModelExtractor extractor = availableExtractors.get(key);

			Button btnExtracor = new Button(groupExtractors, SWT.CHECK);
			btnExtracor.addSelectionListener(new ExtractorCheckBoxListener(
					btnExtracor, extractor.getId(), this));
			int yPositionCurrent = yBase + (index * offset);
			btnExtracor.setBounds(10, yPositionCurrent, 111, 20);
			btnExtracor.setText(extractor.getLabel());
			extractorCheckBoxes.add(btnExtracor);

			boolean selected = splevoProject.getExtractorIds().contains(
					extractor.getId());
			btnExtracor.setSelection(selected);

			index++;
		}

		groupExtractors.setBounds(10, 49, 490, yBase + (index * offset) + 5);

		return groupExtractors;
	}

	/**
	 * Create the tab to handle the diff model.
	 * @param tabIndex The index of the tab within the parent tab folder.
	 */
	private void createDiffingModelTab(int tabIndex) {

		TabItem tbtmDiffingModel = new TabItem(tabFolder, SWT.NONE, tabIndex);
		tbtmDiffingModel.setText("Diffing Model");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmDiffingModel.setControl(composite);

		Label lblDiffingModelDescription = new Label(composite, SWT.NONE);
		lblDiffingModelDescription
				.setText("The diffing model extracted from the source models.");
		lblDiffingModelDescription.setBounds(10, 10, 490, 20);

		Label lblDiffingModel = new Label(composite, SWT.NONE);
		lblDiffingModel.setText("Diffing Model:");
		lblDiffingModel.setBounds(10, 44, 191, 20);

		diffingModelInput = new Text(composite, SWT.BORDER);
		diffingModelInput.setBounds(10, 67, 490, 26);

		diffingPackageFilterInput = new Text(composite, SWT.BORDER | SWT.WRAP);
		diffingPackageFilterInput.setBounds(10, 141, 490, 158);

		Label lblPackageFilterRules = new Label(composite, SWT.NONE);
		lblPackageFilterRules.setBounds(10, 115, 266, 20);
		lblPackageFilterRules.setText("Package Filter Rules (one per line):");
	}

	/**
	 * Create the tab to select the source projects.
	 * @param tabIndex The index of the tab within the parent tab folder.
	 */
	private void createProjectSelectionTab(int tabIndex) {

		// SOURCE PROJECT SELECTION TAB

		tbtmProjectSelection = new TabItem(tabFolder, SWT.NONE, tabIndex);
		tbtmProjectSelection.setText("Source Projects");
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmProjectSelection.setControl(composite);
		composite.setLayout(null);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel
				.setToolTipText("Select the source entity models of the leading projects");
		lblNewLabel.setBounds(10, 10, 740, 20);
		lblNewLabel.setText("Define the projects to be consolidated");

		Label labelVariantNameLeading = new Label(composite, SWT.NONE);
		labelVariantNameLeading.setBounds(10, 37, 106, 20);
		labelVariantNameLeading.setText("Variant Name:");

		Label labelVariantNameIntegration = new Label(composite, SWT.NONE);
		labelVariantNameIntegration.setText("Variant Name:");
		labelVariantNameIntegration.setBounds(430, 36, 106, 20);

		inputVariantNameLeading = new Text(composite, SWT.BORDER);
		inputVariantNameLeading.setBounds(122, 36, 238, 26);

		inputVariantNameIntegration = new Text(composite, SWT.BORDER);
		inputVariantNameIntegration.setBounds(542, 36, 238, 26);

		// / LEADING PROJECT LIST

		viewerLeadingProjects = new TableViewer(composite, SWT.BORDER
				| SWT.FULL_SELECTION);
		viewerLeadingProjects.setContentProvider(ArrayContentProvider
				.getInstance());
		viewerLeadingProjects.setInput(getSplevoProject().getLeadingProjects());
		ProjectDropListener dropListenerLeadingProjects = new ProjectDropListener(
				this, viewerLeadingProjects,
				splevoProject.getLeadingProjects(), inputVariantNameLeading);
		viewerLeadingProjects.addDropSupport(dragNDropOperations,
				transferTypes, dropListenerLeadingProjects);

		Table tableLeadingProjects = viewerLeadingProjects.getTable();
		tableLeadingProjects.setHeaderVisible(true);
		tableLeadingProjects.setLinesVisible(true);
		tableLeadingProjects.setBounds(10, 63, 350, 209);

		tblclmnLeadingProjects = new TableColumn(tableLeadingProjects, SWT.NONE);
		tblclmnLeadingProjects.setWidth(tblclmnLeadingProjects.getParent()
				.getBounds().width);
		tblclmnLeadingProjects.setText("Leading Projects");

		// / INTEGRATION PROJECT LIST

		viewerIntegrationProjects = new TableViewer(composite, SWT.BORDER
				| SWT.FULL_SELECTION);
		viewerIntegrationProjects.setContentProvider(ArrayContentProvider
				.getInstance());
		viewerIntegrationProjects.setInput(getSplevoProject()
				.getIntegrationProjects());
		ProjectDropListener dropListenerIntegrationProjects = new ProjectDropListener(
				this, viewerIntegrationProjects,
				splevoProject.getIntegrationProjects(),
				inputVariantNameIntegration);
		viewerIntegrationProjects.addDropSupport(dragNDropOperations,
				transferTypes, dropListenerIntegrationProjects);

		Table tableIntegrationProjects = viewerIntegrationProjects.getTable();
		tableIntegrationProjects.setHeaderVisible(true);
		tableIntegrationProjects.setLinesVisible(true);
		tableIntegrationProjects.setBounds(430, 63, 350, 209);

		TableColumn tblclmnIntegrationProjects = new TableColumn(
				tableIntegrationProjects, SWT.NONE);
		tblclmnIntegrationProjects.setWidth(tblclmnIntegrationProjects
				.getParent().getBounds().width);
		tblclmnIntegrationProjects.setText("Integration Projects");

		Button btnClear = new Button(composite, SWT.NONE);
		btnClear.setGrayed(true);
		btnClear.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/cross.png"));
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSplevoProject().getLeadingProjects().clear();
				viewerLeadingProjects.refresh();
				markAsDirty();
			}
		});
		btnClear.setBounds(366, 63, 30, 30);

		Button btnClearList = new Button(composite, SWT.NONE);
		btnClearList
				.setToolTipText("Clear the list of source projects to integrate.");
		btnClearList.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/cross.png"));
		btnClearList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSplevoProject().getIntegrationProjects().clear();
				viewerIntegrationProjects.refresh();
				markAsDirty();
			}
		});
		btnClearList.setBounds(786, 63, 30, 30);

		composite.setTabList(new Control[] { tableLeadingProjects,
				tableIntegrationProjects });
	}

	/**
	 * Create the tab for project information.
	 * @param tabIndex The index of the tab within the parent tab folder.
	 */
	private void createProjectInfoTab(int tabIndex) {

		TabItem tbtmProjectInfos = new TabItem(tabFolder, SWT.NONE, tabIndex);
		tbtmProjectInfos.setText("Project Infos");

		Composite projectInfoContainer = new Composite(tabFolder, SWT.NONE);
		tbtmProjectInfos.setControl(projectInfoContainer);

		projectNameInput = new Text(projectInfoContainer, SWT.BORDER);
		projectNameInput.setBounds(113, 30, 307, 26);

		Label lblProjectName = new Label(projectInfoContainer, SWT.NONE);
		lblProjectName.setBounds(10, 33, 97, 20);
		lblProjectName.setText("Project Name");

		Label lblInfo = new Label(projectInfoContainer, SWT.NONE);
		lblInfo.setBounds(10, 62, 70, 20);
		lblInfo.setText("Info");

		infoInput = new Text(projectInfoContainer, SWT.BORDER | SWT.WRAP);
		infoInput.setBounds(113, 62, 307, 112);

		Label lblWorkspace = new Label(projectInfoContainer, SWT.NONE);
		lblWorkspace.setBounds(10, 180, 83, 20);
		lblWorkspace.setText("Workspace");

		workspaceInput = new Text(projectInfoContainer, SWT.BORDER);
		workspaceInput.setBounds(113, 180, 307, 26);
	}

	/**
	 * Create the tab for the process control.
	 * 
	 * @param tabIndex The index of the tab within the parent tab folder.
	 */
	private void createProcessControlTab(int tabIndex) {

		TabItem tbtmProcessControl = new TabItem(tabFolder, SWT.NONE, tabIndex);
		tbtmProcessControl.setText("Process Control");

		Composite processControlContainer = new Composite(tabFolder, SWT.NONE);
		tbtmProcessControl.setControl(processControlContainer);

		Label lblSplevoDashboard = new Label(processControlContainer, SWT.NONE);
		lblSplevoDashboard.setAlignment(SWT.CENTER);
		lblSplevoDashboard.setFont(SWTResourceManager.getFont("Arial", 14, SWT.BOLD));
		lblSplevoDashboard.setBounds(10, 10, 746, 30);
		lblSplevoDashboard.setText("SPLevo Dashboard");

		Label activityStart = new Label(processControlContainer, SWT.NONE);
		activityStart.setAlignment(SWT.CENTER);
		activityStart.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/bullet_green.png"));
		activityStart.setBounds(20, 66, 30, 30);

		Label activityFlow0 = new Label(processControlContainer, SWT.NONE);
		activityFlow0.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/arrow_right.png"));
		activityFlow0.setAlignment(SWT.CENTER);
		activityFlow0.setBounds(44, 66, 30, 30);

		btnSelectSourceProjects = new Button(processControlContainer, SWT.WRAP);
		btnSelectSourceProjects.addMouseListener(new GotoTabMouseListener(
				tabFolder, TABINDEX_PROJECT_SELECTION));
		btnSelectSourceProjects.setBounds(75, 59, 78, 45);
		btnSelectSourceProjects.setText("Project Selection");

		Label activityFlow1 = new Label(processControlContainer, SWT.NONE);
		activityFlow1.setAlignment(SWT.CENTER);
		activityFlow1.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
		activityFlow1.setBounds(159, 66, 30, 30);

		btnExtractSourceModels = new Button(processControlContainer, SWT.WRAP);
		btnExtractSourceModels.addMouseListener(new ExtractProjectListener(this));
		btnExtractSourceModels.setBounds(195, 59, 78, 45);
		btnExtractSourceModels.setText("Model Extraction");

		Label activityFlow3 = new Label(processControlContainer, SWT.NONE);
		activityFlow3.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/arrow_right.png"));
		activityFlow3.setAlignment(SWT.CENTER);
		activityFlow3.setBounds(279, 66, 30, 30);

		btnDiffing = new Button(processControlContainer, SWT.WRAP);
		btnDiffing.addMouseListener(new DiffSourceModelListener(this));
		btnDiffing.setBounds(315, 59, 72, 45);
		btnDiffing.setText("Diffing");

		Label activityFlow4 = new Label(processControlContainer, SWT.NONE);
		activityFlow4.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/arrow_right.png"));
		activityFlow4.setAlignment(SWT.CENTER);
		activityFlow4.setBounds(393, 66, 30, 30);

		btnInitVpm = new Button(processControlContainer, SWT.WRAP);
		btnInitVpm.addMouseListener(new InitVPMListener(this));
		btnInitVpm.setBounds(429, 59, 72, 45);
		btnInitVpm.setText("Init VPM");

		Label activityFlow5 = new Label(processControlContainer, SWT.NONE);
		activityFlow5.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/arrow_right.png"));
		activityFlow5.setAlignment(SWT.CENTER);
		activityFlow5.setBounds(507, 66, 30, 30);

		btnRefineVPM = new Button(processControlContainer, SWT.WRAP);
		btnRefineVPM.addMouseListener(new VPMAnalysisListener(this));
		btnRefineVPM.setText("Analyze VPM");
		btnRefineVPM.setBounds(539, 59, 72, 45);

		Label label = new Label(processControlContainer, SWT.NONE);
		label.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/arrow_right.png"));
		label.setAlignment(SWT.CENTER);
		label.setBounds(617, 66, 30, 30);

		btnGenerateFeatureModel = new Button(processControlContainer, SWT.WRAP);
		btnGenerateFeatureModel
				.addMouseListener(new GenerateFeatureModelListener(this));
		btnGenerateFeatureModel.setText("Generate Feature Model");
		btnGenerateFeatureModel.setBounds(648, 59, 118, 45);

		btnOpenDiff = new Button(processControlContainer, SWT.NONE);
		btnOpenDiff.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/page_white_go.png"));
		btnOpenDiff.setBounds(341, 110, 26, 30);
		btnOpenDiff.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseUp(MouseEvent e) {
				if (splevoProject.getDiffingModelPath() != null
						&& splevoProject.getDiffingModelPath().length() > 0) {
					String basePath = getAbsoluteWorkspacePath();
					File fileToOpen = new File(basePath + File.separator
							+ splevoProject.getDiffingModelPath());
					IFileStore fileStore = EFS.getLocalFileSystem().getStore(
							fileToOpen.toURI());
					IWorkbenchPage page = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();

					try {
						IDE.openEditorOnFileStore(page, fileStore);
					} catch (PartInitException pie) {
						logger.error("failed to open diff file.");
					}
				}
			}
		});

		btnOpenVPM = new Button(processControlContainer, SWT.NONE);
		btnOpenVPM.setImage(ResourceManager.getPluginImage("org.splevo.ui",
				"icons/page_white_go.png"));
		btnOpenVPM.setBounds(451, 110, 26, 30);
		btnOpenVPM.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseUp(MouseEvent e) {
				if (splevoProject.getVpmModelPaths().size() > 0) {
					String basePath = getAbsoluteWorkspacePath();
					File fileToOpen = new File(basePath
							+ File.separator
							+ splevoProject.getVpmModelPaths()
									.get(splevoProject.getVpmModelPaths()
											.size() - 1));
					IFileStore fileStore = EFS.getLocalFileSystem().getStore(
							fileToOpen.toURI());
					IWorkbenchPage page = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();

					try {
						IDE.openEditorOnFileStore(page, fileStore);
					} catch (PartInitException pie) {
						logger.error("failed to open diff file.");
					}
				}
			}
		});
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	/**
	 * Get the editor input resource. This methods overrides the super types
	 * method to return a more specific IFileEditorInput.
	 * 
	 * @return The editor input converted to an IFileEditorInput or null if not
	 *         possible.
	 */
	@Override
	public IFileEditorInput getEditorInput() {
		if (super.getEditorInput() instanceof IFileEditorInput) {
			return (IFileEditorInput) super.getEditorInput();
		}
		return null;
	}

	/**
	 * Save the project configuration to the currently edited file.
	 * 
	 * @param monitor
	 *            The progress monitor to report to.
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {

		// check workspace setting
		if (getSplevoProject().getWorkspace() == null
				|| getSplevoProject().getWorkspace().equals("")) {
			Shell shell = getEditorSite().getShell();
			MessageDialog
					.openError(shell, "Workspace Missing",
							"You need to specify a workspace directory for the project.");
			return;
		}

		String filePath = null;
		try {
			filePath = getCurrentFilePath();
			ModelUtils.save(splevoProject, filePath);
		} catch (Exception e) {
			Shell shell = getEditorSite().getShell();
			MessageDialog.openError(shell, "Save error",
					"Unable to save the project file to " + filePath);
			e.printStackTrace();
		}

		dirtyFlag = false;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	/**
	 * Get the absolute path of the current editor input file.
	 * 
	 * @return The file path derived from the editor input.
	 */
	private String getCurrentFilePath() {
		FileEditorInput fileInput = (FileEditorInput) getEditorInput();
		String filePath = fileInput.getFile().getFullPath().toOSString();
		return filePath;
	}

	@Override
	public void doSaveAs() {
		// do save as is not supported
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {

		setSite(site);
		setInput(input);

		if (input instanceof IFileEditorInput) {
			IFileEditorInput fileInput = (IFileEditorInput) input;
			if (fileInput.getName().endsWith(
					SPLevoProjectUtil.SPLEVO_FILE_EXTENSION)) {
				ResourceSet rs = new ResourceSetImpl();
				File projectFile = new File(fileInput.getFile().getFullPath()
						.toOSString());
				EObject model;
				try {
					model = ModelUtils.load(projectFile, rs);
				} catch (IOException e) {
					throw new PartInitException(
							"Unable to load SPLevo project file in editor", e);
				}
				if (model instanceof SPLevoProject) {
					this.splevoProject = (SPLevoProject) model;
				}
			}
		}
	}

	/**
	 * @return the splevoProject
	 */
	public SPLevoProject getSplevoProject() {
		return splevoProject;
	}

	@Override
	public boolean isDirty() {
		return dirtyFlag;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * Mark the editor as dirty.
	 */
	public void markAsDirty() {
		dirtyFlag = true;
		firePropertyChange(IEditorPart.PROP_DIRTY);
		enableButtonsIfInformationAvailable();
	}

	/**
	 * Update the ui and present the submitted message in an information dialog.
	 * If the provided message is null, no dialog will be opened.
	 * 
	 * @param message
	 *            The optional message to be presented.
	 */
	public void updateUi(String message) {
		updateUI();
		if (message != null) {
			Shell shell = getEditorSite().getShell();
			MessageDialog.openInformation(shell, "SPLevo Info", message);
		}
	}

	/**
	 * Update the user interface.
	 */
	public void updateUI() {

		if (splevoProject.getSourceModelPathLeading() != null) {
			sourceModelLeadingInput.setText(splevoProject
					.getSourceModelPathLeading());
		} else {
			logger.warn("Leading source model path is empty.");
		}

		if (splevoProject.getSourceModelPathIntegration() != null) {
			sourceModelIntegrationInput.setText(splevoProject
					.getSourceModelPathIntegration());
		} else {
			logger.warn("Integration source model path is empty.");
		}

		if (splevoProject.getDiffingModelPath() != null) {
			diffingModelInput.setText(splevoProject.getDiffingModelPath());
		}

		markAsDirty();

		enableButtonsIfInformationAvailable();

	}

	/**
	 * Enable Buttons if the information for the action is available.
	 */
	public void enableButtonsIfInformationAvailable() {
		disableAllButtonsExceptProjectSelection();

		if (projectsSelected()) {
			btnExtractSourceModels.setEnabled(true);
		} else {
			return;
		}

		if (sourceModelsExtracted()) {
			btnDiffing.setEnabled(true);
		} else {
			return;
		}

		if (diffModelAvailable()) {
			btnInitVpm.setEnabled(true);
			btnOpenDiff.setEnabled(true);
		} else {
			return;
		}

		if (vpmAvailable()) {
			btnOpenVPM.setEnabled(true);
			btnRefineVPM.setEnabled(true);
			btnGenerateFeatureModel.setEnabled(true);
		} else {
			return;
		}
	}

	/**
	 * Check if at least one variation point model is set and can be accessed.
	 * 
	 * @return True if an accessible vpm exists.
	 */
	private boolean vpmAvailable() {
		String basePath = getAbsoluteWorkspacePath();
		return splevoProject.getVpmModelPaths().size() > 0
				&& new File(basePath + splevoProject.getVpmModelPaths().get(0))
						.canRead();
	}

	/**
	 * Check if a diff model is set in the project file and if it can be read.
	 * 
	 * @return true if the diff model is available.
	 */
	private boolean diffModelAvailable() {
		String basePath = getAbsoluteWorkspacePath();
		return splevoProject.getDiffingModelPath() != null
				&& new File(basePath + splevoProject.getDiffingModelPath())
						.canRead();
	}

	/**
	 * Determine the absolute OS specific path of the workspace.
	 * 
	 * @return The absolute path.
	 */
	private String getAbsoluteWorkspacePath() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		String basePath = workspace.getRoot().getRawLocation().toOSString();
		return basePath;
	}

	/**
	 * Check if source models have been extracted previously based on the
	 * project file content.
	 * 
	 * @return True if both models are available.
	 */
	private boolean sourceModelsExtracted() {
		String basePath = getAbsoluteWorkspacePath();

		return splevoProject.getSourceModelPathIntegration() != null
				&& new File(basePath
						+ splevoProject.getSourceModelPathIntegration())
						.canRead()
				&& splevoProject.getSourceModelPathLeading() != null
				&& new File(basePath
						+ splevoProject.getSourceModelPathLeading()).canRead();
	}

	/**
	 * Checks if both input models have more than one project.
	 * 
	 * @return true, if both input models have more than one project, else false
	 */
	private boolean projectsSelected() {
		return splevoProject.getLeadingProjects().size() > 0
				&& splevoProject.getIntegrationProjects().size() > 0;
	}

	/**
	 * Disable all buttons except the Project Selection button.
	 */
	private void disableAllButtonsExceptProjectSelection() {
		List<Button> buttons = new ArrayList<Button>();

		buttons.add(btnExtractSourceModels);
		buttons.add(btnDiffing);
		buttons.add(btnOpenDiff);
		buttons.add(btnGenerateFeatureModel);
		buttons.add(btnInitVpm);
		buttons.add(btnRefineVPM);
		buttons.add(btnOpenVPM);

		for (Button button : buttons) {
			button.setEnabled(false);
		}
	}

	/**
	 * initializing the data bindings for the UI.
	 * 
	 * @return The prepared context to be bound to the ui.
	 */
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();

		MarkDirtyListener markDirtyListener = new MarkDirtyListener(this);
		//
		IObservableValue observeTextProjectNameInputObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(projectNameInput);
		IObservableValue nameGetSplevoProjectObserveValue = PojoProperties
				.value("name").observe(getSplevoProject());
		nameGetSplevoProjectObserveValue.addChangeListener(markDirtyListener);
		bindingContext.bindValue(observeTextProjectNameInputObserveWidget,
				nameGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextInfoInputObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(infoInput);
		IObservableValue descriptionGetSplevoProjectObserveValue = PojoProperties
				.value("description").observe(getSplevoProject());
		descriptionGetSplevoProjectObserveValue
				.addChangeListener(markDirtyListener);
		bindingContext.bindValue(observeTextInfoInputObserveWidget,
				descriptionGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextWorkspaceInputObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(workspaceInput);
		IObservableValue workspaceGetSplevoProjectObserveValue = PojoProperties
				.value("workspace").observe(getSplevoProject());
		workspaceGetSplevoProjectObserveValue
				.addChangeListener(markDirtyListener);
		bindingContext.bindValue(observeTextWorkspaceInputObserveWidget,
				workspaceGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextInputVariantNameLeadingObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(inputVariantNameLeading);
		IObservableValue variantNameLeadingGetSplevoProjectObserveValue = PojoProperties
				.value("variantNameLeading").observe(getSplevoProject());
		variantNameLeadingGetSplevoProjectObserveValue
				.addChangeListener(markDirtyListener);
		bindingContext.bindValue(
				observeTextInputVariantNameLeadingObserveWidget,
				variantNameLeadingGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextInputVariantNameIntegrationObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(inputVariantNameIntegration);
		IObservableValue variantNameIntegrationGetSplevoProjectObserveValue = PojoProperties
				.value("variantNameIntegration").observe(getSplevoProject());
		variantNameIntegrationGetSplevoProjectObserveValue
				.addChangeListener(markDirtyListener);
		bindingContext.bindValue(
				observeTextInputVariantNameIntegrationObserveWidget,
				variantNameIntegrationGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextSourceModelLeadingObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(sourceModelLeadingInput);
		IObservableValue sourceModelPathLeadingGetSplevoProjectObserveValue = PojoProperties
				.value("sourceModelPathLeading").observe(splevoProject);
		sourceModelPathLeadingGetSplevoProjectObserveValue
				.addChangeListener(markDirtyListener);
		bindingContext.bindValue(observeTextSourceModelLeadingObserveWidget,
				sourceModelPathLeadingGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextSourceModelIntegrationObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(sourceModelIntegrationInput);
		IObservableValue sourceModelPathIntegrationGetSplevoProjectObserveValue = PojoProperties
				.value("sourceModelPathIntegration").observe(splevoProject);
		sourceModelPathIntegrationGetSplevoProjectObserveValue
				.addChangeListener(markDirtyListener);
		bindingContext.bindValue(
				observeTextSourceModelIntegrationObserveWidget,
				sourceModelPathIntegrationGetSplevoProjectObserveValue, null,
				null);
		//
		IObservableValue observeTextDiffingModelInputObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(diffingModelInput);
		IObservableValue diffingModelPathSplevoProjectObserveValue = PojoProperties
				.value("diffingModelPath").observe(splevoProject);
		diffingModelPathSplevoProjectObserveValue
				.addChangeListener(markDirtyListener);
		bindingContext.bindValue(observeTextDiffingModelInputObserveWidget,
				diffingModelPathSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextDiffingPackageFilterInputObserveWidget = WidgetProperties
				.text(SWT.Modify).observe(diffingPackageFilterInput);
		IObservableValue diffingFilterRulesSplevoProjectObserveValue = PojoProperties
				.value("diffingFilterRules").observe(splevoProject);
		diffingFilterRulesSplevoProjectObserveValue
				.addChangeListener(markDirtyListener);
		bindingContext.bindValue(
				observeTextDiffingPackageFilterInputObserveWidget,
				diffingFilterRulesSplevoProjectObserveValue, null, null);
		//
		return bindingContext;
	}
}

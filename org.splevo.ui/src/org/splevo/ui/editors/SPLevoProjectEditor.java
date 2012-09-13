package org.splevo.ui.editors;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.compare.util.ModelUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.splevo.project.SPLevoProject;
import org.splevo.project.SPLevoProjectUtil;
import org.splevo.ui.listeners.ExtractProjectListener;
import org.splevo.ui.listeners.GotoTabMouseListener;
import org.splevo.ui.listeners.MarkDirtyListener;
import org.splevo.ui.listeners.ProjectDropListener;

public class SPLevoProjectEditor extends EditorPart {

	public static final String ID = "org.splevo.ui.editors.SPLevoProjectEditor"; //$NON-NLS-1$

	private static final int TABINDEX_PROJECT_INFOS = 0;
	private static final int TABINDEX_PROCESS_CONTROL = 1;
	private static final int TABINDEX_PROJECT_SELECTION = 2;
	private static final int TABINDEX_DIFFING_MODEL = 3;

	
	/** The splevo project manipulated in the editor instance. */
	private SPLevoProject splevoProject = null;

	private TabFolder tabFolder = null;
	private TabItem tbtmProjectSelection = null;
	/** The table viewer for the leading source models the variant should be integrated to */
	private TableViewer viewerLeadingProjects;
	private TableColumn tblclmnLeadingProjects;
	private TableViewer viewerIntegrationProjects;
	private Text projectNameInput;
	private Text infoInput;
	private Text workspaceInput;
	
	/** Flag identifying the dirty state of the editor. */
	private boolean dirtyFlag = false;
	
	private Transfer[] transferTypes = new Transfer[] { FileTransfer.getInstance() };
	private Text inputVariantNameLeading;
	private Text inputVariantNameIntegration;
	private Text messageTextBox;
	private Text sourceModelLeading;
	private Text sourceModelIntegration;

	public SPLevoProjectEditor() {
		setTitleImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/splevo.gif"));
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.verticalSpacing = 0;
		gl_parent.marginWidth = 0;
		gl_parent.marginHeight = 0;
		gl_parent.horizontalSpacing = 0;
		parent.setLayout(gl_parent);		
		
		// init the data tables
		tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_tabFolder = new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1);
		gd_tabFolder.widthHint = 837;
		gd_tabFolder.heightHint = 353;
		tabFolder.setLayoutData(gd_tabFolder);
		
		// create tabs
		createProjectInfoTab();
		createProcessControlTab();
		createProjectSelectionTab();
		createSourceModelTab();

		// create message area
		Composite messageArea = new Composite(parent, SWT.NONE);
		messageArea.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_messageArea = new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 3);
		gd_messageArea.heightHint = 83;
		gd_messageArea.widthHint = 838;
		messageArea.setLayoutData(gd_messageArea);
		
		messageTextBox = new Text(messageArea, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		messageTextBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		messageTextBox.setEditable(true);
		messageTextBox.setBounds(0, 0, 835, 83);
		
		
		initDataBindings();

	}

	/**
	 * Create the tab to handle the diff model.
	 */
	private void createSourceModelTab() {

		TabItem tabItemSourceModel = new TabItem(tabFolder, SWT.NONE, TABINDEX_DIFFING_MODEL);
		tabItemSourceModel.setText("Source Models");
		
		Composite sourceModelTabComposite = new Composite(tabFolder, SWT.NONE);
		tabItemSourceModel.setControl(sourceModelTabComposite);
		sourceModelTabComposite.setLayout(null);
		
		Label lblTheSoftwareModels = new Label(sourceModelTabComposite, SWT.NONE);
		lblTheSoftwareModels.setBounds(10, 10, 490, 20);
		lblTheSoftwareModels.setText("The software models extracted from the source projects.");
		
		Label lblLeadingProjectModel = new Label(sourceModelTabComposite, SWT.NONE);
		lblLeadingProjectModel.setBounds(10, 44, 191, 20);
		lblLeadingProjectModel.setText("Leading Source Model:");
		
		sourceModelLeading = new Text(sourceModelTabComposite, SWT.BORDER);
		sourceModelLeading.setBounds(10, 67, 490, 26);
		
		Label lblsourceModelIntegration = new Label(sourceModelTabComposite, SWT.NONE);
		lblsourceModelIntegration.setText("Integration Source Model:");
		lblsourceModelIntegration.setBounds(10, 124, 191, 20);
		
		sourceModelIntegration = new Text(sourceModelTabComposite, SWT.BORDER);
		sourceModelIntegration.setBounds(10, 147, 490, 26);
	}

	/**
	 * Create the tab to select the source projects.
	 */
	private void createProjectSelectionTab() {
		
		// SOURCE PROJECT SELECTION TAB
		
		tbtmProjectSelection = new TabItem(tabFolder, SWT.NONE,TABINDEX_PROJECT_SELECTION);
		tbtmProjectSelection.setText("Source Projects");
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmProjectSelection.setControl(composite);
		composite.setLayout(null);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setToolTipText("Select the source entity models of the leading projects");
		lblNewLabel.setBounds(10, 10, 740, 20);
		lblNewLabel.setText("Define the projects to be consolidated");
		
		/// LEADING PROJECT LIST

		viewerLeadingProjects = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		viewerLeadingProjects.setContentProvider(ArrayContentProvider.getInstance());
		viewerLeadingProjects.setInput(getSplevoProject().getLeadingProjects());
		ProjectDropListener dropListenerLeadingProjects = new ProjectDropListener(viewerLeadingProjects, splevoProject.getLeadingProjects());
		viewerLeadingProjects.addDropSupport(DND.DROP_COPY | DND.DROP_MOVE, transferTypes, dropListenerLeadingProjects);
		
		Table tableLeadingProjects = viewerLeadingProjects.getTable();
		tableLeadingProjects.setHeaderVisible(true);
		tableLeadingProjects.setLinesVisible(true);
		tableLeadingProjects.setBounds(10, 63, 350, 209);
		
		tblclmnLeadingProjects = new TableColumn(tableLeadingProjects, SWT.NONE);
		tblclmnLeadingProjects.setWidth(tblclmnLeadingProjects.getParent().getBounds().width);
		tblclmnLeadingProjects.setText("Leading Projects");
		
		
		/// INTEGRATION PROJECT LIST
		
		viewerIntegrationProjects = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		viewerIntegrationProjects.setContentProvider(ArrayContentProvider.getInstance());
		viewerIntegrationProjects.setInput(getSplevoProject().getIntegrationProjects());
		ProjectDropListener dropListenerIntegrationProjects = new ProjectDropListener(viewerIntegrationProjects, splevoProject.getIntegrationProjects());
		viewerIntegrationProjects.addDropSupport(DND.DROP_COPY | DND.DROP_MOVE, transferTypes, dropListenerIntegrationProjects);
		
		Table tableIntegrationProjects = viewerIntegrationProjects.getTable();
		tableIntegrationProjects.setHeaderVisible(true);
		tableIntegrationProjects.setLinesVisible(true);
		tableIntegrationProjects.setBounds(430, 63, 350, 209);
		
		TableColumn tblclmnIntegrationProjects = new TableColumn(tableIntegrationProjects, SWT.NONE);
		tblclmnIntegrationProjects.setWidth(tblclmnIntegrationProjects.getParent().getBounds().width);
		tblclmnIntegrationProjects.setText("Integration Projects");
		
		Button btnClear = new Button(composite, SWT.NONE);
		btnClear.setGrayed(true);
		btnClear.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/cross.png"));
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
		btnClearList.setToolTipText("Clear the list of source projects to integrate.");
		btnClearList.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/cross.png"));
		btnClearList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSplevoProject().getIntegrationProjects().clear();
				viewerIntegrationProjects.refresh();
				markAsDirty();
			}
		});
		btnClearList.setBounds(786, 63, 30, 30);
		
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
		
		composite.setTabList(new Control[]{tableLeadingProjects,tableIntegrationProjects});
	}

	/**
	 * Create the tab for project information
	 */
	private void createProjectInfoTab() {
		
		TabItem tbtmProjectInfos = new TabItem(tabFolder, SWT.NONE,TABINDEX_PROJECT_INFOS);
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
		
		infoInput = new Text(projectInfoContainer, SWT.BORDER);
		infoInput.setBounds(113, 62, 307, 112);
		
		Label lblWorkspace = new Label(projectInfoContainer, SWT.NONE);
		lblWorkspace.setBounds(10, 180, 83, 20);
		lblWorkspace.setText("Workspace");
		
		workspaceInput = new Text(projectInfoContainer, SWT.BORDER);
		workspaceInput.setBounds(113, 180, 307, 26);
	}

	private void createProcessControlTab() {
		
		TabItem tbtmProcessControl = new TabItem(tabFolder, SWT.NONE);
		tbtmProcessControl.setText("Process Control");
		
		Composite processControlContainer = new Composite(tabFolder, SWT.NONE);
		tbtmProcessControl.setControl(processControlContainer);
		
		Label activityStart = new Label(processControlContainer, SWT.NONE);
		activityStart.setAlignment(SWT.CENTER);
		activityStart.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/bullet_green.png"));
		activityStart.setBounds(10, 105, 30, 30);
		
		Label activityFlow0 = new Label(processControlContainer, SWT.NONE);
		activityFlow0.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
		activityFlow0.setAlignment(SWT.CENTER);
		activityFlow0.setBounds(34, 105, 30, 30);
		
		Button btnSelectSourceProjects = new Button(processControlContainer, SWT.WRAP);
		btnSelectSourceProjects.addMouseListener(new GotoTabMouseListener(tabFolder,TABINDEX_PROJECT_SELECTION));
		btnSelectSourceProjects.setBounds(65, 98, 78, 45);
		btnSelectSourceProjects.setText("Project Selection");
		
		Label activityFlow1 = new Label(processControlContainer, SWT.NONE);
		activityFlow1.setAlignment(SWT.CENTER);
		activityFlow1.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
		activityFlow1.setBounds(149, 105, 30, 30);
		
		Button btnExtractSourceModels = new Button(processControlContainer, SWT.WRAP);
		btnExtractSourceModels.addMouseListener(new ExtractProjectListener(this));
		btnExtractSourceModels.setBounds(185, 98, 78, 45);
		btnExtractSourceModels.setText("Model Extraction");
		
		Label activityFlow3 = new Label(processControlContainer, SWT.NONE);
		activityFlow3.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
		activityFlow3.setAlignment(SWT.CENTER);
		activityFlow3.setBounds(269, 105, 30, 30);
		
		Button btnDiffing = new Button(processControlContainer, SWT.WRAP);
		btnDiffing.setBounds(305, 98, 72, 45);
		btnDiffing.setText("Diffing");
		
		Label activityFlow4 = new Label(processControlContainer, SWT.NONE);
		activityFlow4.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
		activityFlow4.setAlignment(SWT.CENTER);
		activityFlow4.setBounds(383, 105, 30, 30);
		
		Button btnInitVpm = new Button(processControlContainer, SWT.WRAP);
		btnInitVpm.setBounds(419, 98, 72, 45);
		btnInitVpm.setText("Init VPM");
		
		Label activityFlow5 = new Label(processControlContainer, SWT.NONE);
		activityFlow5.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/arrow_right.png"));
		activityFlow5.setAlignment(SWT.CENTER);
		activityFlow5.setBounds(497, 105, 30, 30);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	/**
	 * Get the editor input resource.
	 * This methods overrides the super types method to return a more specific IFileEditorInput.
	 * @return The editor input converted to an IFileEditorInput or null if not possible.
	 */
	@Override
	public IFileEditorInput getEditorInput() {
		if(super.getEditorInput() instanceof IFileEditorInput){
			return (IFileEditorInput) super.getEditorInput();
		}
		return null;
	}
	
	/**
	 * Save the project configuration to the currently edited file.
	 * @param monitor The progress monitor to report to.
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		
		// check workspace setting
		if(getSplevoProject().getWorkspace() == null || getSplevoProject().getWorkspace().equals("")){
			Shell shell = getEditorSite().getShell();
			MessageDialog.openError(shell, "Workspace Missing", "You need to specify a workspace directory for the project.");
			return;
		}

		String filePath = null;
		try {
			filePath = getCurrentFilePath();
			ModelUtils.save(splevoProject, filePath);
		} catch (Exception e) {
			Shell shell = getEditorSite().getShell();
			MessageDialog.openError(shell, "Save error", "Unable to save the project file to "+filePath);
			e.printStackTrace();
		}
		
		dirtyFlag = false;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	/**
	 * Get the absolute path of the current editor input file.
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
		
		if(input instanceof IFileEditorInput){
			IFileEditorInput fileInput = (IFileEditorInput) input;
			if(fileInput.getName().endsWith(SPLevoProjectUtil.SPLEVO_FILE_EXTENSION)){
				ResourceSet rs = new ResourceSetImpl();
				File projectFile = new File(fileInput.getFile().getFullPath().toOSString());
				EObject model;
				try {
					model = ModelUtils.load(projectFile,  rs);
				} catch (IOException e) {
					throw new PartInitException("Unable to load SPLevo project file in editor",e);
				}
				if(model instanceof SPLevoProject){
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
	public void markAsDirty(){
		dirtyFlag = true;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}
	protected DataBindingContext initDataBindings() {
		MarkDirtyListener markDirtyListerener = new MarkDirtyListener(this);
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextProjectNameInputObserveWidget = WidgetProperties.text(SWT.Modify).observe(projectNameInput);
		IObservableValue nameGetSplevoProjectObserveValue = PojoProperties.value("name").observe(getSplevoProject());
		nameGetSplevoProjectObserveValue.addChangeListener(markDirtyListerener);
		bindingContext.bindValue(observeTextProjectNameInputObserveWidget, nameGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextInfoInputObserveWidget = WidgetProperties.text(SWT.Modify).observe(infoInput);
		IObservableValue descriptionGetSplevoProjectObserveValue = PojoProperties.value("description").observe(getSplevoProject());
		descriptionGetSplevoProjectObserveValue.addChangeListener(markDirtyListerener);
		bindingContext.bindValue(observeTextInfoInputObserveWidget, descriptionGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextWorkspaceInputObserveWidget = WidgetProperties.text(SWT.Modify).observe(workspaceInput);
		IObservableValue workspaceGetSplevoProjectObserveValue = PojoProperties.value("workspace").observe(getSplevoProject());
		workspaceGetSplevoProjectObserveValue.addChangeListener(markDirtyListerener);
		bindingContext.bindValue(observeTextWorkspaceInputObserveWidget, workspaceGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextInputVariantNameLeadingObserveWidget = WidgetProperties.text(SWT.Modify).observe(inputVariantNameLeading);
		IObservableValue variantNameLeadingGetSplevoProjectObserveValue = PojoProperties.value("variantNameLeading").observe(getSplevoProject());
		variantNameLeadingGetSplevoProjectObserveValue.addChangeListener(markDirtyListerener);
		bindingContext.bindValue(observeTextInputVariantNameLeadingObserveWidget, variantNameLeadingGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextInputVariantNameIntegrationObserveWidget = WidgetProperties.text(SWT.Modify).observe(inputVariantNameIntegration);
		IObservableValue variantNameIntegrationGetSplevoProjectObserveValue = PojoProperties.value("variantNameIntegration").observe(getSplevoProject());
		variantNameIntegrationGetSplevoProjectObserveValue.addChangeListener(markDirtyListerener);
		bindingContext.bindValue(observeTextInputVariantNameIntegrationObserveWidget, variantNameIntegrationGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextSourceModelLeadingObserveWidget = WidgetProperties.text(SWT.Modify).observe(sourceModelLeading);
		IObservableValue sourceModelPathLeadingGetSplevoProjectObserveValue = PojoProperties.value("sourceModelPathLeading").observe(splevoProject);
		sourceModelPathLeadingGetSplevoProjectObserveValue.addChangeListener(markDirtyListerener);
		bindingContext.bindValue(observeTextSourceModelLeadingObserveWidget, sourceModelPathLeadingGetSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextSourceModelIntegrationObserveWidget = WidgetProperties.text(SWT.Modify).observe(sourceModelIntegration);
		IObservableValue sourceModelPathIntegrationGetSplevoProjectObserveValue = PojoProperties.value("sourceModelPathIntegration").observe(splevoProject);
		sourceModelPathIntegrationGetSplevoProjectObserveValue.addChangeListener(markDirtyListerener);
		bindingContext.bindValue(observeTextSourceModelIntegrationObserveWidget, sourceModelPathIntegrationGetSplevoProjectObserveValue, null, null);
		//
		return bindingContext;
	}

	/**
	 * Update the ui and present the submitted message in an information dialog.
	 * @param message The optional message to be presented.
	 */
	public void updateUi(String message) {
		updateUI();
		Shell shell = getEditorSite().getShell();
		MessageDialog.openInformation(shell, "SPLevo Info", message);
	}
		
	public void updateUI(){
		sourceModelLeading.setText(splevoProject.getSourceModelPathLeading());
		sourceModelIntegration.setText(splevoProject.getSourceModelPathIntegration());
		markAsDirty();
	}
}

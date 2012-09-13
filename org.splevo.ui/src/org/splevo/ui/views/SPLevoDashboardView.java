package org.splevo.ui.views;


import java.io.File;
import java.io.IOException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.splevo.project.ProjectFactory;
import org.splevo.project.SPLevoProject;
import org.splevo.project.SPLevoProjectUtil;
import org.splevo.ui.listeners.ProjectDropListener;


/**
 * The SPLevo Dashboard View provides control over a SPLevo product copy analysis run.
 * <p>
 */

public class SPLevoDashboardView extends ViewPart {
	private DataBindingContext m_bindingContext;

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.splevo.ui.views.SPLevoDashboardView";
	
	/** The splevo project presented in the dashboard view. */
	private SPLevoProject splevoProject = ProjectFactory.eINSTANCE.createSPLevoProject();
	
	/** The file, the splevo project definition is stored in. */
	private File splevoProjectFile = null;

	/** The table viewer for the leading source models the variant should be integrated to */
	private TableViewer viewerLeadingProjects;
	private TableColumn tblclmnLeadingProjects;
	private TableViewer viewerIntegrationProjects;
	private Text projectNameInput;
	private Text infoInput;
	private Text workspaceInput;

	private Transfer[] transferTypes = new Transfer[] { FileTransfer.getInstance() };
	private Text projectFileInput;

	/**
	 * The constructor.
	 */
	public SPLevoDashboardView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		
		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		
		TabItem tbtmProjectInfos = new TabItem(tabFolder, SWT.NONE);
		tbtmProjectInfos.setText("Project Infos");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmProjectInfos.setControl(composite_1);
		
		projectNameInput = new Text(composite_1, SWT.BORDER);
		projectNameInput.setBounds(113, 30, 307, 26);
		
		Label lblProjectName = new Label(composite_1, SWT.NONE);
		lblProjectName.setBounds(10, 33, 97, 20);
		lblProjectName.setText("Project Name");
		
		Label lblInfo = new Label(composite_1, SWT.NONE);
		lblInfo.setBounds(10, 62, 70, 20);
		lblInfo.setText("Info");
		
		infoInput = new Text(composite_1, SWT.BORDER);
		infoInput.setBounds(113, 62, 307, 112);
		
		Label lblWorkspace = new Label(composite_1, SWT.NONE);
		lblWorkspace.setBounds(10, 180, 83, 20);
		lblWorkspace.setText("Workspace");
		
		workspaceInput = new Text(composite_1, SWT.BORDER);
		workspaceInput.setBounds(113, 180, 307, 26);
		
		Button btnSave = new Button(composite_1, SWT.NONE);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				// check workspace setting
				if(getSplevoProject().getWorkspace() == null || getSplevoProject().getWorkspace().equals("")){
					Shell shell = getViewSite().getShell();
					MessageDialog.openError(shell, "Workspace Missing", "You need to specify a workspace directory for the project.");
					return;
				}
				
				// check project filename setting
				if(getSplevoProject().getName() == null || getSplevoProject().getName().equals("")){
					Shell shell = getViewSite().getShell();
					MessageDialog.openError(shell, "Project Name Missing", "You need to specify a name for the project.");
					return;
				}
				
				// set default project file in workspace if none defined.
				if(splevoProjectFile == null){
					splevoProjectFile = new File(getSplevoProject().getWorkspace() + File.separator + getSplevoProject().getName() + "."+SPLevoProjectUtil.SPLEVO_FILE_EXTENSION);
				}
				
				try {
					SPLevoProjectUtil.save(getSplevoProject(), splevoProjectFile);
					Shell shell = getViewSite().getShell();
					MessageDialog.openInformation(shell, "Project saved", "Project successfully saved");
			    } catch (IOException exception) {					
			    	Shell shell = getViewSite().getShell();
			    	MessageDialog.openError(shell, "Project File not accessible", "Unable to write to project file: "+splevoProjectFile.getAbsolutePath());
			    	exception.printStackTrace();
			    }	
			}
		});
		btnSave.setBounds(330, 212, 90, 30);
		btnSave.setText("save");
		
		Label lblProjectFile = new Label(composite_1, SWT.NONE);
		lblProjectFile.setBounds(10, 322, 83, 20);
		lblProjectFile.setText("Project File");
		
		projectFileInput = new Text(composite_1, SWT.BORDER);
		projectFileInput.setBounds(113, 322, 307, 26);

		Button btnLoadProjectFile = new Button(composite_1, SWT.NONE);
		btnLoadProjectFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				try {
					splevoProject = SPLevoProjectUtil.loadSPLevoProjectModel(splevoProjectFile);
				} catch (IOException e1) {
					Shell shell = getViewSite().getShell();
					MessageDialog.openError(shell, "Project File not loaded", "No SPLevo project information could be loaded from the specified destination.");
					e1.printStackTrace();
				}
			}
		});
		btnLoadProjectFile.setBounds(426, 318, 90, 30);
		btnLoadProjectFile.setText("load file");
		
		Label label = new Label(composite_1, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 295, 506, 2);
		
		TabItem tbtmProjectSelection = new TabItem(tabFolder, SWT.NONE);
		tbtmProjectSelection.setText("Source Models");
		
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
		tableLeadingProjects.setBounds(10, 36, 350, 209);
		
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
		tableIntegrationProjects.setBounds(400, 36, 350, 209);
		
		TableColumn tblclmnIntegrationProjects = new TableColumn(tableIntegrationProjects, SWT.NONE);
		tblclmnIntegrationProjects.setWidth(tblclmnIntegrationProjects.getParent().getBounds().width);
		tblclmnIntegrationProjects.setText("Integration Projects");
		
		Button btnClear = new Button(composite, SWT.NONE);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSplevoProject().getLeadingProjects().clear();
				viewerLeadingProjects.refresh();
			}
		});
		btnClear.setBounds(270, 251, 90, 30);
		btnClear.setText("clear list");
		
		Button btnClearList = new Button(composite, SWT.NONE);
		btnClearList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSplevoProject().getIntegrationProjects().clear();
				viewerIntegrationProjects.refresh();
			}
		});
		btnClearList.setBounds(660, 251, 90, 30);
		btnClearList.setText("clear list");
		
		composite.setTabList(new Control[]{tableLeadingProjects,tableIntegrationProjects});
		
				// Create the help context id for the viewer's control
				PlatformUI.getWorkbench().getHelpSystem().setHelp(viewerLeadingProjects.getControl(), "org.splevo.ui.viewer");
		
		TabItem tabItemDiffingModel = new TabItem(tabFolder, SWT.NONE);
		tabItemDiffingModel.setText("Diffing Model");
		m_bindingContext = initDataBindings();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewerLeadingProjects.getControl().setFocus();
	}

	/**
	 * @return the splevoProject
	 */
	public SPLevoProject getSplevoProject() {
		return splevoProject;
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextProjectFileInputObserveWidget = WidgetProperties.text(SWT.Modify).observe(projectFileInput);
		IObservableValue absolutePathSplevoProjectFileObserveValue = PojoProperties.value("absolutePath").observe(splevoProjectFile);
		bindingContext.bindValue(observeTextProjectFileInputObserveWidget, absolutePathSplevoProjectFileObserveValue, null, null);
		//
		IObservableValue observeTextWorkspaceInputObserveWidget = WidgetProperties.text(SWT.Modify).observe(workspaceInput);
		IObservableValue workspaceSplevoProjectObserveValue = PojoProperties.value("workspace").observe(splevoProject);
		bindingContext.bindValue(observeTextWorkspaceInputObserveWidget, workspaceSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextInfoInputObserveWidget = WidgetProperties.text(SWT.Modify).observe(infoInput);
		IObservableValue descriptionSplevoProjectObserveValue = PojoProperties.value("description").observe(splevoProject);
		bindingContext.bindValue(observeTextInfoInputObserveWidget, descriptionSplevoProjectObserveValue, null, null);
		//
		IObservableValue observeTextProjectNameInputObserveWidget = WidgetProperties.text(SWT.Modify).observe(projectNameInput);
		IObservableValue nameSplevoProjectObserveValue = PojoProperties.value("name").observe(splevoProject);
		bindingContext.bindValue(observeTextProjectNameInputObserveWidget, nameSplevoProjectObserveValue, null, null);
		//
		return bindingContext;
	}
}
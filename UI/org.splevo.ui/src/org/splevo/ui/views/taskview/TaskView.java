package org.splevo.ui.views.taskview;


import java.awt.LayoutManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.LayoutStyle;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.layout.LayoutUtil;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.jface.text.*;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.apache.commons.io.*;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class TaskView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.splevo.ui.views.taskview.TaskView";

	private TableViewer viewer;
	private Action getAllTasksAction;

	/*
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	
	public class TasksTableLabelProvider extends LabelProvider implements ITableLabelProvider
	{

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Task task = (Task) element;
			switch(columnIndex)
			{
				case 0:
					return task.getDescription();
				case 1:
					return task.getResource();
				case 2:
					return task.getPath();
				case 3:
					return new Integer(task.getLocation()).toString();
				default:
					return "not specified yet";
			}
		}
		
	}
	
	/**
	 * The constructor.
	 */
	public TaskView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {

		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		createViewer(parent);

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(viewer.getControl(), "PrototypeRefactorings.viewer");
		makeActions();
		hookContextMenu();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				TaskView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(getAllTasksAction);
		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(getAllTasksAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(getAllTasksAction);
	}

	private void makeActions() {
		getAllTasksAction = new Action() {
			public void run() {
				IWorkbenchPart workbenchPart = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage()
						.getActivePart();
				if (workbenchPart.getSite().getPage().getActiveEditor() == null) {
					showMessage("Editor is empty!");
					return;
				}

				IFile file = (IFile) workbenchPart.getSite().getPage()
						.getActiveEditor().getEditorInput()
						.getAdapter(IFile.class);
				if (file == null) {
					showMessage("file is empty");
				}

				try {
					String EditorText = IOUtils.toString(file.getContents(),
							file.getCharset());
					viewer.setInput(new TaskCreator().getTasks());
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showMessage("Error occured");
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showMessage("Error occured");
				}
			}
		};
		getAllTasksAction.setText("Get all Tasks");
		getAllTasksAction.setToolTipText("Get all SPLevo tasks and collect them in the SPLevoTaskView");
		getAllTasksAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}
	
	private void showMessage(String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),
				"SPLevoTaskView", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new TasksTableLabelProvider());
		// make the selection available to other views
		getSite().setSelectionProvider(viewer);
		// set the sorter for the table

		// define layout for the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	public TableViewer getViewer() {
		return viewer;
	}

	// create the columns for the table
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "Description", "Resource", "Path", "Location" };
		int[] bounds = { 300, 100, 50, 200 };

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col = createTableViewerColumn(titles[3], bounds[3], 3);
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
}
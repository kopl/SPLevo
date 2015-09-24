package org.splevo.ui.views.taskview;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.splevo.project.SPLevoProject;
import org.splevo.project.SPLevoProjectUtil;
import org.splevo.ui.commons.project.SPLevoProjectWorkspaceListener;
import org.splevo.ui.commons.project.SPLevoProjectWorkspaceObserver;
import org.splevo.ui.commons.util.ComboBoxSelectionComposite;
import org.splevo.ui.commons.util.CompositeSwitcher;
import org.splevo.ui.commons.util.SingleLevelElementProvider;
import org.splevo.ui.commons.util.WorkspaceUtil;
import org.splevo.ui.jobs.SPLevoBlackBoard;
import org.splevo.ui.listeners.WorkflowListenerUtil;
import org.splevo.ui.workflow.BuildSemiAutomatedRefactoringWorkflowDelegate;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view shows data obtained
 * from the model. The sample creates a dummy model on the fly, but a real implementation would
 * connect to the model available either in this or another plug-in (e.g. the workspace). The view
 * is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be presented in the view. Each
 * view can present the same model objects using different labels and icons, if needed.
 * Alternatively, a single label provider can be shared between views in order to ensure that
 * objects of the same type are presented in the same way everywhere.
 * <p>
 */

public class TaskView extends ViewPart {

    /**
     * The ID of the view as specified by the extension.
     */
    public static final String ID = "org.splevo.ui.views.taskview.TaskView";

    private static final Logger LOGGER = Logger.getLogger(TaskView.class);
    private Table table = null;
    private TableViewer viewer = null;
    private Action startRefactoringAction = null;

    private CompositeSwitcher compositeSwitcherComposite;
    private Optional<SPLevoProject> selectedSPLevoProject;
    
    private class TaskViewComboBoxSelectionComposite extends ComboBoxSelectionComposite {
        
        private final SPLevoProjectWorkspaceObserver projectObserver;
        private final SPLevoProjectWorkspaceListener projectListener;
        
        public TaskViewComboBoxSelectionComposite(Composite parent) {
            super(parent, "Please select a consolidation project below. Afterwards, this list shows the outstanding refactorings for this project.",
                    "Select");
 
            projectObserver = new SPLevoProjectWorkspaceObserver();
            projectListener = new SPLevoProjectWorkspaceListener() {
                @Override
                public void availableProjectFilesChanged(SPLevoProjectWorkspaceObserver observer) {
                    reset();
                }
            };
            projectObserver.registerSubscriber(projectListener);
            projectObserver.startObserver();
            reset();
        }

        @Override
        protected void handleSelectionAfterAccept(IStructuredSelection cvSelection) {
            try {
                if (cvSelection.getFirstElement() instanceof IFile) {
                    SPLevoProject project = SPLevoProjectUtil.loadSPLevoProjectModel((IFile) cvSelection
                            .getFirstElement());
                    selectedSPLevoProject = Optional.fromNullable(project);
                    if (project != null) {
                        setSPLevoProject(project);
                        return;
                    }
                }
            } catch (IOException e) {
                LOGGER.warn("Failure during loading of SPLevoProject.", e);
            }

            selectedSPLevoProject = Optional.absent();
        }

        @Override
        protected ILabelProvider getLabelProvider() {
            return new LabelProvider() {

                /*
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
                 */
                @Override
                public String getText(Object element) {
                    try {
                        if (element != null && element instanceof IFile) {
                            IFile file = (IFile) element;
                            SPLevoProject project = SPLevoProjectUtil.loadSPLevoProjectModel(file);
                            return project.getName();
                        }
                    } catch (IOException e) {
                        LOGGER.warn("Could not load SPLevoProjectFile.", e);
                    }
                    return "Invalid element";
                }
            };
        }

        @Override
        protected SingleLevelElementProvider getComboViewerInput() {
            return new SingleLevelElementProvider() {
                @Override
                public Object[] getElements() {
                    return Iterables.toArray(projectObserver.getCurrentState(), IFile.class);
                }
            };
        }

        /* (non-Javadoc)
         * @see org.eclipse.swt.widgets.Widget#dispose()
         */
        @Override
        public void dispose() {
            projectObserver.stopObserver();
            projectObserver.unregisterSubscriber(projectListener);
            super.dispose();
        }
        
    }
    
    /**
     * Provides the table for the taskviewer
     */
    public class TasksTableLabelProvider extends LabelProvider implements ITableLabelProvider {

        @Override
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        @Override
        public String getColumnText(Object element, int columnIndex) {
            Task task = (Task) element;
            switch (columnIndex) {
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
     * This is a callback that will allow us to create the viewer and initialize it.
     * 
     * @param parent
     *            to create PartControl
     */
    public void createPartControl(Composite parent) {
        // setup UI elements
        compositeSwitcherComposite = new CompositeSwitcher(parent);
        new TaskViewComboBoxSelectionComposite(compositeSwitcherComposite);
        createViewer(compositeSwitcherComposite);
        compositeSwitcherComposite.switchToElement(0);

        // Create the help context id for the viewer's control
        PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "PrototypeRefactorings.viewer");
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
        manager.add(startRefactoringAction);
        manager.add(new Separator());
    }

    private void fillContextMenu(IMenuManager manager) {
        manager.add(startRefactoringAction);
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    private void fillLocalToolBar(IToolBarManager manager) {
        manager.add(startRefactoringAction);
    }

    private void makeActions() {
        startRefactoringAction = new Action() {
            public void run() {
                TableItem[] tableItem = table.getSelection();
                if (null != tableItem) {
                	String[] splittedByBlank = tableItem[0].getText(0).split(" ");
					String variationPointID = splittedByBlank[0];
                    if (variationPointID != "" || variationPointID != null) {
                        SPLevoBlackBoard spLevoBlackBoard = new SPLevoBlackBoard();
                        BuildSemiAutomatedRefactoringWorkflowDelegate buildSPLWorkflowConfiguration = new BuildSemiAutomatedRefactoringWorkflowDelegate(
                                spLevoBlackBoard, variationPointID, getSPLevoProject());
                        WorkflowListenerUtil.runWorkflowAndRunUITask(buildSPLWorkflowConfiguration,
                                "Refactor VP semiautomatically", null, true);

                        table.remove(table.getSelectionIndex());
                    }
                }
            }
        };
        startRefactoringAction.setText("Start Refactoring");
        startRefactoringAction.setToolTipText("Start Refactoring");
        startRefactoringAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
                .getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
    }
    
    private void fillTableWithTasks(boolean forceProjectSelection) {
        if (forceProjectSelection || !selectedSPLevoProject.isPresent()) {
            compositeSwitcherComposite.switchToElement(0);
            return;
        }
        
        compositeSwitcherComposite.switchToElement(1);
        SPLevoProject project = selectedSPLevoProject.get();
        Iterable<IProject> relevantProjects = Iterables.concat(WorkspaceUtil.transformProjectNamesToProjects(project.getLeadingProjects()),
                WorkspaceUtil.transformProjectNamesToProjects(project.getIntegrationProjects()));
        
        try {
            viewer.setInput(new TaskCreator().getTasks(relevantProjects));
        } catch (CoreException e) {
            e.printStackTrace();
            showMessage("Error occured");
        }
    }

    private void showMessage(String message) {
        MessageDialog.openInformation(viewer.getControl().getShell(), "SPLevoTaskView", message);
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    private void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        createColumns(parent, viewer);
        table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new TasksTableLabelProvider());

        getSite().setSelectionProvider(viewer);

        GridData gridData = new GridData();
        gridData.verticalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = SWT.FILL;
        viewer.getControl().setLayoutData(gridData);
    }

    /**
     * Gets the viewer.
     * 
     * @return the viewer
     */
    public TableViewer getViewer() {
        return viewer;
    }

    private void createColumns(final Composite parent, final TableViewer viewer) {
        String[] titles = { "Description", "Resource", "Path", "Location" };
        int[] bounds = { 300, 100, 50, 200 };

        createTableViewerColumn(titles[0], bounds[0], 0);
        createTableViewerColumn(titles[1], bounds[1], 1);
        createTableViewerColumn(titles[2], bounds[2], 2);
        createTableViewerColumn(titles[3], bounds[3], 3);
    }

    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
    }

    private SPLevoProject getSPLevoProject() {
        return this.selectedSPLevoProject.orNull();
    }

    public void setSPLevoProject(SPLevoProject splevoProject) {
        this.selectedSPLevoProject = Optional.fromNullable(splevoProject);
        fillTableWithTasks(false);
    }
}
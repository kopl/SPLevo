package org.splevo.utilities.metrics.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewer;
import org.splevo.utilities.metrics.calculator.MetricCalculationException;
import org.splevo.utilities.metrics.calculator.MetricItem;
import org.splevo.utilities.metrics.calculator.MetricsResultSet;
import org.splevo.utilities.metrics.commonloc.CommonLOCMetricCalculator;
import org.eclipse.swt.custom.SashForm;

public class MetricsView extends ViewPart implements ISelectionListener {

    public static final String ID = "org.splevo.utilities.MetricsView"; //$NON-NLS-1$
    private Text metricResultOutput;
    private List<Object> selectedItems = new ArrayList<Object>();
    private Table table;
    
    private TableViewer commonDetailTableViewer;
    private TableViewer commonTotalTableViewer;
    private TabItem tbtmRawOutput;
    private Composite topContainer;

    public MetricsView() {
    }

    /**
     * Create contents of the view part.
     * 
     * @param parent
     *            the parent
     */
    @Override
    public void createPartControl(Composite parent) {
        topContainer = new Composite(parent, SWT.FILL);
        topContainer.setLayout(new FillLayout(SWT.HORIZONTAL));

        TabFolder tabFolder = new TabFolder(topContainer, SWT.BOTTOM);

        createCommonTableTab(tabFolder);

        createRawOutputTab(tabFolder);

        createActions();
        initializeToolBar();
        initializeMenu();

        ISelectionService selectionService = getSite().getWorkbenchWindow().getSelectionService();
        selectionService.addSelectionListener(this);
    }

    /**
     * @param tabFolder
     */
    private void createRawOutputTab(TabFolder tabFolder) {
        tbtmRawOutput = new TabItem(tabFolder, SWT.NONE);
        tbtmRawOutput.setText("Raw Output");
        metricResultOutput = new Text(tabFolder, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL
                | SWT.MULTI);
        tbtmRawOutput.setControl(metricResultOutput);
    }

    /**
     * @param tabFolder
     */
    private void createCommonTableTab(TabFolder tabFolder) {

        TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
        tabItem.setText("Overview Table");
        
        // initialize the table container
        Composite overviewTabContainer = new Composite(tabFolder, SWT.NONE);
        tabItem.setControl(overviewTabContainer);
        FillLayout overviewTabContainerLayout = new FillLayout(SWT.VERTICAL);
        overviewTabContainerLayout.spacing = 4;
        overviewTabContainer.setLayout(overviewTabContainerLayout);
        
        SashForm sashForm = new SashForm(overviewTabContainer, SWT.SMOOTH | SWT.VERTICAL);
        sashForm.setSashWidth(2);
        
        // Create the table for the detail metric results
        commonDetailTableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
        commonDetailTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        Table detailTable = commonDetailTableViewer.getTable();
        detailTable.setLinesVisible(true);
        detailTable.setHeaderVisible(true);
        
        // Create the table for the total Results        
        commonTotalTableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
        commonTotalTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        Table  totalTable = commonTotalTableViewer.getTable();
        totalTable.setLinesVisible(true);
        totalTable.setHeaderVisible(true);
        
                // create the metric name column
                TableViewerColumn metricNameViewerColumn = new TableViewerColumn(commonTotalTableViewer, SWT.FILL);
                TableColumn metricNameColumn = metricNameViewerColumn.getColumn();
                metricNameColumn.setText("Total Metric");
                metricNameColumn.setResizable(true);
                metricNameColumn.setMoveable(true);
                metricNameColumn.setWidth(200);
                metricNameViewerColumn.setLabelProvider(new MetricNameLabelProvider());
                
                        // create the metric name column
                        TableViewerColumn metricValueViewerColumn = new TableViewerColumn(commonTotalTableViewer, SWT.FILL);
                        TableColumn metricValueColumn = metricValueViewerColumn.getColumn();
                        metricValueColumn.setText("Value");
                        metricValueColumn.setResizable(true);
                        metricValueColumn.setMoveable(true);
                        metricValueColumn.setWidth(200);
                        metricValueViewerColumn.setLabelProvider(new MetricValueLabelProvider());
        sashForm.setWeights(new int[] {2, 1});

    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions
    }

    /**
     * Initialize the toolbar.
     */
    private void initializeToolBar() {
        IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
    }

    /**
     * Initialize the menu.
     */
    private void initializeMenu() {
        IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
    }

    @Override
    public void setFocus() {
        // Set the focus
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            selectedItems = structuredSelection.toList();
            updateMetricsView();
        }

    }

    /**
     * Update the metrics view by calculating the metrics for the selected items and trigger the
     * result presentation.
     */
    private void updateMetricsView() {
        if (selectedItems == null || selectedItems.size() < 1) {
            metricResultOutput.setText("Please select item(s) to calculate metrics.");
            tbtmRawOutput.getParent().setSelection(1);
            return;
        }
        try {
            MetricsResultSet resultSet = calculateMetrics(selectedItems);
            updateCommonTable(resultSet);
            updateRawOutput(resultSet);
        } catch (MetricCalculationException e) {
        }
        
    }

    /**
     * Update the textual output tab.
     * @param resultSet The results to present.
     */
    private void updateRawOutput(MetricsResultSet resultSet) {
        metricResultOutput.setText("");

        StringBuilder metricsOutput = new StringBuilder();

        for (MetricItem metricItem : resultSet.getMetrics()) {

            metricsOutput.append(metricItem.getItemName());

            Map<String, Object> metrics = metricItem.getMetrics();

            for (String key : metrics.keySet()) {
                metricsOutput.append("\t");
                metricsOutput.append(key);
                metricsOutput.append("\t");
                metricsOutput.append(metrics.get(key).toString());
            }
            metricsOutput.append("\n");
        }
        metricsOutput.append("\n");

        Map<String, Object> totalMetrics = resultSet.getTotalMetrics();
        if (totalMetrics.size() > 0) {

            metricsOutput.append("==============================\n");
            metricsOutput.append("Total Metrics\n");

            for (String key : totalMetrics.keySet()) {
                metricsOutput.append(key);
                metricsOutput.append(":\t");
                metricsOutput.append(totalMetrics.get(key).toString());
                metricsOutput.append("\n");
            }
        }

        metricResultOutput.setText(metricsOutput.toString());
    }

    /**
     * Calculate the metrics.
     * 
     * @param selectedItems
     *            the selected items
     * @return The prepared result set.
     * @throws MetricCalculationException
     *             Failed to calculate metrics.
     */
    private MetricsResultSet calculateMetrics(List<Object> selectedItems) throws MetricCalculationException {
        CommonLOCMetricCalculator calculator = new CommonLOCMetricCalculator();
        MetricsResultSet resultSet = calculator.calculateMetrics(selectedItems);
        return resultSet;
    }

    /**
     * Update the common table for default presentation of result sets.
     * 
     * @param resultSet
     *            The result set to present.
     */
    private void updateCommonTable(MetricsResultSet resultSet) {

        // remove the current input
        // commonTableViewer.setInput(null);

        // remove the current tables
        TableColumn[] columns = commonDetailTableViewer.getTable().getColumns();
        for (TableColumn tc : columns) {
            tc.dispose();
        }

        // create the item name column
        TableViewerColumn itemNameViewerColumn = new TableViewerColumn(commonDetailTableViewer, SWT.FILL);
        TableColumn itemNameColumn = itemNameViewerColumn.getColumn();
        itemNameColumn.setText("Item");
        itemNameColumn.setResizable(true);
        itemNameColumn.setMoveable(true);
        itemNameColumn.setWidth(200);
        itemNameViewerColumn.setLabelProvider(new ItemLabelProvider());

        // create the metric columns
        for (String metricKey : resultSet.getAvailableMetrics()) {
            TableViewerColumn viewerColumn = new TableViewerColumn(commonDetailTableViewer, SWT.FILL);
            TableColumn column = viewerColumn.getColumn();
            column.setText(metricKey);
            column.setResizable(true);
            column.setMoveable(true);
            column.setWidth(200);
            viewerColumn.setLabelProvider(new MetricLabelProvider(metricKey));
        }
        commonDetailTableViewer.setInput(resultSet.getMetrics());
        commonDetailTableViewer.refresh();
        
        commonTotalTableViewer.setInput(resultSet.getTotalMetrics().entrySet());
        commonTotalTableViewer.refresh();
    }
}

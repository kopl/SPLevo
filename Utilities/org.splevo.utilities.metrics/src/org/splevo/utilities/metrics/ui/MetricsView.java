package org.splevo.utilities.metrics.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.splevo.utilities.metrics.calculator.MetricCalculationException;
import org.splevo.utilities.metrics.calculator.MetricResultItem;
import org.splevo.utilities.metrics.calculator.MetricsCalculator;
import org.splevo.utilities.metrics.calculator.MetricsResultSet;

/**
 * The view to present metric results for the elements currently selected in the workbench.
 * 
 * @author Benjamin Klatt
 * 
 */
public class MetricsView extends ViewPart implements ISelectionListener {

    /** The id of the view. */
    public static final String ID = "org.splevo.utilities.MetricsView"; //$NON-NLS-1$

    /** The id of the metrics calculator extension point. */
    private static final String METRICS_CALCULATOR_EXTENSION_POINT_ID = "org.splevo.utilities.metrics.calculator";

    /** The id of the extension point calculator class. */
    private static final String EXTENSION_POINT_ATTR_CALCULATOR_CLASS = "calculator.class";;

    /** The list of currently selected items. */
    private List<Object> selectedItems = new ArrayList<Object>();

    /** The folder for the tabs. */
    private TabFolder tabFolder = null;

    /** The tab for the raw output. */
    private TabItem tabRawOutput;

    /** The text field for the result output. */
    private Text metricResultOutput;

    /**
     * Default constructor.
     */
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
        Composite topContainer = new Composite(parent, SWT.FILL);
        topContainer.setLayout(new FillLayout(SWT.HORIZONTAL));

        tabFolder = new TabFolder(topContainer, SWT.BOTTOM);

        createRawOutputTab(tabFolder);

        createActions();
        initializeToolBar();
        initializeMenu();

        ISelectionService selectionService = getSite().getWorkbenchWindow().getSelectionService();
        selectionService.addSelectionListener(this);
    }

    /**
     * Create the tab for the raw text output.
     * 
     * @param tabFolder
     *            The tab folder to place the raw input tab in.
     */
    private void createRawOutputTab(TabFolder tabFolder) {
        tabRawOutput = new TabItem(tabFolder, SWT.NONE);
        tabRawOutput.setText("Raw Output");
        metricResultOutput = new Text(tabFolder, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL
                | SWT.MULTI);
        tabRawOutput.setControl(metricResultOutput);
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
    @SuppressWarnings("unused")
    private void initializeToolBar() {
        IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
    }

    /**
     * Initialize the menu.
     */
    @SuppressWarnings("unused")
    private void initializeMenu() {
        IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
    }

    @Override
    public void setFocus() {
        // Set the focus
    }

    /**
     * React on selection changes in the ui. {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
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

        // remove all tab items except of the raw output
        for (TabItem tabItem : tabFolder.getItems()) {
            if (tabItem != tabRawOutput) {
                tabItem.dispose();
            }
        }

        // check that at least one item has been selected.
        if (selectedItems == null || selectedItems.size() < 1) {
            metricResultOutput.setText("Please select item(s) to calculate metrics.");
            tabRawOutput.getParent().setSelection(1);
            return;
        }
        try {
            List<MetricsResultSet> resultSets = calculateMetrics(selectedItems);
            for (MetricsResultSet metricsResultSet : resultSets) {
                createResultTableTab(tabFolder, metricsResultSet);
            }
            updateRawOutput(resultSets);

        } catch (MetricCalculationException e) {
            System.err.println(e.getMessage());
        }

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
    private List<MetricsResultSet> calculateMetrics(List<Object> selectedItems) throws MetricCalculationException {

        List<MetricsResultSet> results = new ArrayList<MetricsResultSet>();

        List<MetricsCalculator> calculators = getAvailableCalculators();
        
        for (MetricsCalculator metricsCalculator : calculators) {
            MetricsResultSet resultSet = metricsCalculator.calculateMetrics(selectedItems);
            if (resultSet.getMetricResultItems().size() > 0 || resultSet.getTotalMetrics().size() > 0) {
                results.add(resultSet);
            }
        }

//        // File metrics
//        CommonLOCMetricCalculator fileCalculator = new CommonLOCMetricCalculator();
//        MetricsResultSet fileResultSet = fileCalculator.calculateMetrics(selectedItems);
//        results.add(fileResultSet);
//
//        // QVTo metrics
//        QVToMetricCalculator qvtoCalculator = new QVToMetricCalculator();
//        MetricsResultSet qvtoResultSet = qvtoCalculator.calculateMetrics(selectedItems);
//        if (qvtoResultSet.getMetrics().size() > 0 || qvtoResultSet.getTotalMetrics().size() > 0) {
//            results.add(qvtoResultSet);
//        }

        return results;
    }

    /**
     * Get the list of available metric calculators registered 
     * at the according extension point.
     * @return The list of registered calculators.
     */
    private List<MetricsCalculator> getAvailableCalculators() {
        List<MetricsCalculator> calculators = new ArrayList<MetricsCalculator>();

        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            System.err.println("No extension point registry available.");
            return calculators;
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(METRICS_CALCULATOR_EXTENSION_POINT_ID);

        if (extensionPoint == null) {
            System.err.println("No extension point found for the ID " + METRICS_CALCULATOR_EXTENSION_POINT_ID);
            return null;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            IConfigurationElement[] configurations = extension.getConfigurationElements();
            for (IConfigurationElement element : configurations) {
                try {
                    Object o = element.createExecutableExtension(EXTENSION_POINT_ATTR_CALCULATOR_CLASS);
                    if ((o != null) && (o instanceof MetricsCalculator)) {
                        MetricsCalculator analyzer = (MetricsCalculator) o;
                        calculators.add(analyzer);
                    }
                } catch (CoreException e) {
                    System.err.println("Failed to load metrics calculator extension ");
                    e.printStackTrace();
                }
            }
        }

        return calculators;
    }

    /**
     * Update the textual output tab.
     * 
     * @param resultSets
     *            The list of result sets to present.
     */
    private void updateRawOutput(List<MetricsResultSet> resultSets) {
        metricResultOutput.setText("");

        StringBuilder metricsOutput = new StringBuilder();

        for (MetricsResultSet metricsResultSet : resultSets) {

            metricsOutput.append("==============================\n");
            metricsOutput.append("===    METRIC SET: ");
            metricsOutput.append(metricsResultSet.getId().toUpperCase());
            metricsOutput.append("    ===\n");
            metricsOutput.append("==============================\n");

            for (MetricResultItem metricItem : metricsResultSet.getMetricResultItems()) {

                metricsOutput.append(metricItem.getItemName());

                for (String key : metricItem.keySet()) {
                    metricsOutput.append("\t");
                    metricsOutput.append(key);
                    metricsOutput.append("\t");
                    metricsOutput.append(metricItem.get(key).toString());
                }
                metricsOutput.append("\n");
            }
            metricsOutput.append("\n");

            Map<String, Object> totalMetrics = metricsResultSet.getTotalMetrics();
            if (totalMetrics.size() > 0) {

                metricsOutput.append("------------------------------\n");
                metricsOutput.append("Total Metrics\n");

                for (String key : totalMetrics.keySet()) {
                    metricsOutput.append(key);
                    metricsOutput.append(":\t");
                    metricsOutput.append(totalMetrics.get(key).toString());
                    metricsOutput.append("\n");
                }
            }
        }

        metricResultOutput.setText(metricsOutput.toString());
    }

    /**
     * Create a result set tab.
     * 
     * @param tabFolder
     *            The tab folder to add the tab to.
     * @param resultSet
     *            The result set to present on the tab.
     */
    private void createResultTableTab(TabFolder tabFolder, MetricsResultSet resultSet) {

        TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
        tabItem.setText(resultSet.getId());

        // initialize the table container
        Composite overviewTabContainer = new Composite(tabFolder, SWT.NONE);
        tabItem.setControl(overviewTabContainer);
        FillLayout overviewTabContainerLayout = new FillLayout(SWT.VERTICAL);
        overviewTabContainerLayout.spacing = 4;
        overviewTabContainer.setLayout(overviewTabContainerLayout);

        SashForm sashForm = new SashForm(overviewTabContainer, SWT.SMOOTH | SWT.VERTICAL);
        sashForm.setSashWidth(2);

        // Create the table for the detail metric results
        TableViewer detailTableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
        detailTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        Table detailTable = detailTableViewer.getTable();
        detailTable.setLinesVisible(true);
        detailTable.setHeaderVisible(true);

        // Create the table for the total Results
        TableViewer totalTableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
        totalTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        Table totalTable = totalTableViewer.getTable();
        totalTable.setLinesVisible(true);
        totalTable.setHeaderVisible(true);

        // create the metric name column
        TableViewerColumn metricNameViewerColumn = new TableViewerColumn(totalTableViewer, SWT.FILL);
        TableColumn metricNameColumn = metricNameViewerColumn.getColumn();
        metricNameColumn.setText("Total Metric");
        metricNameColumn.setResizable(true);
        metricNameColumn.setMoveable(true);
        metricNameColumn.setWidth(200);
        metricNameViewerColumn.setLabelProvider(new MetricNameLabelProvider());

        // create the metric name column
        TableViewerColumn metricValueViewerColumn = new TableViewerColumn(totalTableViewer, SWT.FILL);
        TableColumn metricValueColumn = metricValueViewerColumn.getColumn();
        metricValueColumn.setText("Value");
        metricValueColumn.setResizable(true);
        metricValueColumn.setMoveable(true);
        metricValueColumn.setWidth(200);
        metricValueViewerColumn.setLabelProvider(new MetricValueLabelProvider());
        sashForm.setWeights(new int[] { 2, 1 });

        updateCommonTable(detailTableViewer, totalTableViewer, resultSet);

    }

    /**
     * Update the common table for default presentation of result sets.
     * 
     * @param detailTableViewer
     *            The table viewer for the detailed metric results.
     * @param totalTableViewer
     *            The table viewer for the aggregated results.
     * @param resultSet
     *            The result set to present.
     */
    private void updateCommonTable(TableViewer detailTableViewer, TableViewer totalTableViewer,
            MetricsResultSet resultSet) {

        // remove the current input
        // commonTableViewer.setInput(null);

        // remove the current tables
        TableColumn[] columns = detailTableViewer.getTable().getColumns();
        for (TableColumn tc : columns) {
            tc.dispose();
        }

        // create the item name column
        TableViewerColumn itemNameViewerColumn = new TableViewerColumn(detailTableViewer, SWT.FILL);
        TableColumn itemNameColumn = itemNameViewerColumn.getColumn();
        itemNameColumn.setText("Item");
        itemNameColumn.setResizable(true);
        itemNameColumn.setMoveable(true);
        itemNameColumn.setWidth(200);
        itemNameViewerColumn.setLabelProvider(new ItemLabelProvider());

        // create the metric columns
        for (String metricKey : resultSet.getAvailableMetrics()) {
            TableViewerColumn viewerColumn = new TableViewerColumn(detailTableViewer, SWT.FILL);
            TableColumn column = viewerColumn.getColumn();
            column.setText(metricKey);
            column.setResizable(true);
            column.setMoveable(true);
            column.setWidth(200);
            viewerColumn.setLabelProvider(new MetricLabelProvider(metricKey));
        }
        detailTableViewer.setInput(resultSet.getMetricResultItems());
        detailTableViewer.refresh();

        totalTableViewer.setInput(resultSet.getTotalMetrics().entrySet());
        totalTableViewer.refresh();
    }
    
    @Override
    public void dispose() {
        ISelectionService selectionService = getSite().getWorkbenchWindow().getSelectionService();
        selectionService.removeSelectionListener(this);
        super.dispose();
    }
}

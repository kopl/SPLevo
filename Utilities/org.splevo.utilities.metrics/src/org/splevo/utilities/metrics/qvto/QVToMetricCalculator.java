package org.splevo.utilities.metrics.qvto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.splevo.utilities.metrics.calculator.MetricCalculationException;
import org.splevo.utilities.metrics.calculator.MetricResultItem;
import org.splevo.utilities.metrics.calculator.MetricsCalculator;
import org.splevo.utilities.metrics.calculator.MetricsResultSet;
import org.splevo.utilities.metrics.calculator.impl.MetricResultItemImpl;
import org.splevo.utilities.metrics.calculator.impl.MetricsResultSetImpl;

/**
 * A metric calculator specific for QVTo scripts.
 * 
 * @author Benjamin Klatt
 * 
 */
public class QVToMetricCalculator implements MetricsCalculator {

    /** The file extension of qvto scripts. */
    public static final String QVTO_FILE_EXTENSION = ".qvto";

    /** Metric key for the number of helpers. */
    public static final String METRIC_HELPERS = "Helpers";

    /** Metric key for the number of mappings. */
    public static final String METRIC_MAPPINGS = "Mappings";

    /** Metric key for the number of queries. */
    public static final String METRIC_QUERIES = "Queries";

    /**
     * Return if an item can be analyzed by the calculator.
     * 
     * @param item
     *            The item to check.
     * @return True/false if it can be analyzed or not.
     */
    public boolean isSupported(Object item) {
        if (item instanceof IFile) {
            if (((IFile) item).getName().endsWith(QVTO_FILE_EXTENSION)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculate the metric for this item.
     * 
     * @param fileItem
     *            The file item to analyze.
     * @return The metrics for this item. Or null if no metric could be calculated.
     * @throws MetricCalculationException
     *             identifies a failed metric calculation.
     */
    public MetricResultItem calculateSingleMetric(IFile fileItem) throws MetricCalculationException {

        MetricResultItemImpl metricItem = new MetricResultItemImpl(fileItem.getName(), fileItem.getLocationURI());

        int counterHelpers = 0;
        int counterQueries = 0;
        int counterMappings = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(fileItem.getContents()));

            while (reader.ready()) {
                String line = reader.readLine().trim();
                if (line.startsWith("helper")) {
                    counterHelpers++;
                } else if (line.startsWith("mapping")) {
                    counterMappings++;
                } else if (line.startsWith("query")) {
                    counterQueries++;
                }
            }
        } catch (Exception e) {
            throw new MetricCalculationException("Failed to load qvto script to analyze", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // no action required.
                    e = null;
                }
            }
        }

        metricItem.put(METRIC_MAPPINGS, counterMappings);
        metricItem.put(METRIC_QUERIES, counterQueries);
        metricItem.put(METRIC_HELPERS, counterHelpers);

        return metricItem;
    }

    @Override
    public MetricsResultSet calculateMetrics(List<Object> items) throws MetricCalculationException {

        MetricsResultSetImpl resultSet = new MetricsResultSetImpl("QVTo Metrics");

        // calculate the individual metrics
        for (Object item : items) {
            if (item instanceof IFile) {
                IFile fileItem = (IFile) item;
                if (fileItem.getName().endsWith(QVTO_FILE_EXTENSION)) {
                    MetricResultItem metricItem = calculateSingleMetric(fileItem);
                    if (metricItem != null) {
                        resultSet.addMetricResultItem(metricItem);
                    }
                }
            }
        }

        // calculate the total metrics
        int counterTotalMappings = 0;
        int counterTotalQueries = 0;
        int counterTotalHelpers = 0;
        for (MetricResultItem metricItem : resultSet.getMetricResultItems()) {
            counterTotalMappings += Integer.valueOf(metricItem.get(METRIC_MAPPINGS).toString());
            counterTotalQueries += Integer.valueOf(metricItem.get(METRIC_QUERIES).toString());
            counterTotalHelpers += Integer.valueOf(metricItem.get(METRIC_HELPERS).toString());
        }
        resultSet.getTotalMetrics().put(METRIC_MAPPINGS, counterTotalMappings);
        resultSet.getTotalMetrics().put(METRIC_QUERIES, counterTotalQueries);
        resultSet.getTotalMetrics().put(METRIC_HELPERS, counterTotalHelpers);

        // set the available metrics
        resultSet.addAvailableMetric(METRIC_MAPPINGS);
        resultSet.addAvailableMetric(METRIC_QUERIES);
        resultSet.addAvailableMetric(METRIC_HELPERS);

        return resultSet;
    }

    @Override
    public String getId() {
        return "QVT-O Metric Calculator";
    }

}

package org.splevo.utilities.metrics.commonloc;

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
 * A lines of code calculator for generic text files.
 * 
 * @author Benjamin Klatt
 * 
 */
public class CommonLOCMetricCalculator implements MetricsCalculator {

    /** Metric key for the non empty lines count. */
    public static final String METRIC_LINES_NON_EMPTY = "Lines Non-Empty";

    /** Metric key for the total lines count. */
    public static final String METRIC_LINES_TOTAL = "Lines Total";

    @Override
    public MetricsResultSet calculateMetrics(List<Object> items) throws MetricCalculationException {

        MetricsResultSetImpl resultSet = new MetricsResultSetImpl("File Metrics");

        // calculate the individual metrics
        for (Object item : items) {
            if (item instanceof IFile) {
                MetricResultItem metricItem = calculateSingleMetric((IFile) item);
                resultSet.getMetricResultItems().add(metricItem);
            }
        }

        // calculate the total metrics
        int counterTotal = 0;
        int counterTotalNonEmpty = 0;
        for (MetricResultItem metricItem : resultSet.getMetricResultItems()) {
            counterTotal += Integer.valueOf(metricItem.get(METRIC_LINES_TOTAL).toString());
            counterTotalNonEmpty += Integer.valueOf(metricItem.get(METRIC_LINES_NON_EMPTY).toString());
        }
        resultSet.getTotalMetrics().put(METRIC_LINES_TOTAL, counterTotal);
        resultSet.getTotalMetrics().put(METRIC_LINES_NON_EMPTY, counterTotalNonEmpty);

        // set the available metrics
        resultSet.addAvailableMetric(METRIC_LINES_TOTAL);
        resultSet.addAvailableMetric(METRIC_LINES_NON_EMPTY);

        return resultSet;
    }

    @Override
    public String getId() {
        return "Common LoC Metric Calculator";
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

        int counterTotal = 0;
        int counterNonEmpty = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(fileItem.getContents()));

            while (reader.ready()) {
                String line = reader.readLine().trim();
                counterTotal++;
                if (line.length() > 0) {
                    counterNonEmpty++;
                }
            }
        } catch (Exception e) {
            throw new MetricCalculationException("Failed to load file to analyze", e);
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

        metricItem.put(METRIC_LINES_TOTAL, counterTotal);
        metricItem.put(METRIC_LINES_NON_EMPTY, counterNonEmpty);

        return metricItem;
    }

}

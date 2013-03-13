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
import org.splevo.utilities.metrics.calculator.impl.MetricItemImpl;
import org.splevo.utilities.metrics.calculator.impl.MetricsResultSetImpl;

/**
 * A lines of code calculator for generic text files.
 * @author Benjamin Klatt
 *
 */
public class CommonLOCMetricCalculator implements MetricsCalculator {

    /** Metric key for the non empty lines count. */
    public static final String METRIC_LINES_NON_EMPTY = "Lines Non-Empty";

    /** Metric key for the total lines count. */
    public static final String METRIC_LINES_TOTAL = "Lines Total";

    @Override
    public boolean isSupported(Object item) {
        if (item instanceof IFile) {
            return true;
        }
        return false;
    }

    @Override
    public MetricResultItem calculateSingleMetric(Object item) throws MetricCalculationException {
        if (item instanceof IFile) {
            IFile fileItem = (IFile) item;

            MetricItemImpl metricItem = new MetricItemImpl(fileItem.getName());

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

            metricItem.setMetric(METRIC_LINES_TOTAL, counterTotal);
            metricItem.setMetric(METRIC_LINES_NON_EMPTY, counterNonEmpty);

            return metricItem;
        }
        return null;
    }

    @Override
    public MetricsResultSet calculateMetrics(List<Object> items) throws MetricCalculationException {

        MetricsResultSet resultSet = new MetricsResultSetImpl();
        resultSet.setId("File Metrics");

        // calculate the individual metrics
        for (Object item : items) {
            if (isSupported(item)) {
                MetricResultItem metricItem = calculateSingleMetric(item);
                resultSet.getMetrics().add(metricItem);
            }
        }

        // calculate the total metrics
        int counterTotal = 0;
        int counterTotalNonEmpty = 0;
        for (MetricResultItem metricItem : resultSet.getMetrics()) {
            counterTotal += Integer.valueOf(metricItem.getMetrics().get(METRIC_LINES_TOTAL).toString());
            counterTotalNonEmpty += Integer.valueOf(metricItem.getMetrics().get(METRIC_LINES_NON_EMPTY).toString());
        }
        resultSet.getTotalMetrics().put(METRIC_LINES_TOTAL, counterTotal);
        resultSet.getTotalMetrics().put(METRIC_LINES_NON_EMPTY, counterTotalNonEmpty);

        // set the available metrics
        resultSet.getAvailableMetrics().add(METRIC_LINES_TOTAL);
        resultSet.getAvailableMetrics().add(METRIC_LINES_NON_EMPTY);

        return resultSet;
    }

}

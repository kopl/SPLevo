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
import org.splevo.utilities.metrics.calculator.impl.MetricItemImpl;
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

    @Override
    public boolean isSupported(Object item) {
        if (item instanceof IFile) {
            if (((IFile) item).getName().endsWith(QVTO_FILE_EXTENSION)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MetricResultItem calculateSingleMetric(Object item) throws MetricCalculationException {
        if (item instanceof IFile) {
            IFile fileItem = (IFile) item;
            if (!fileItem.getName().endsWith(QVTO_FILE_EXTENSION)) {
                return null;
            }

            MetricItemImpl metricItem = new MetricItemImpl(fileItem.getName());

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

            metricItem.setMetric(METRIC_MAPPINGS, counterMappings);
            metricItem.setMetric(METRIC_QUERIES, counterQueries);
            metricItem.setMetric(METRIC_HELPERS, counterHelpers);

            return metricItem;
        }
        return null;
    }

    @Override
    public MetricsResultSet calculateMetrics(List<Object> items) throws MetricCalculationException {

        MetricsResultSet resultSet = new MetricsResultSetImpl();
        resultSet.setId("QVTo Metrics");

        // calculate the individual metrics
        for (Object item : items) {
            if (isSupported(item)) {
                MetricResultItem metricItem = calculateSingleMetric(item);
                if (metricItem != null) {
                    resultSet.getMetrics().add(metricItem);
                }
            }
        }

        // calculate the total metrics
        int counterTotalMappings = 0;
        int counterTotalQueries = 0;
        int counterTotalHelpers = 0;
        for (MetricResultItem metricItem : resultSet.getMetrics()) {
            counterTotalMappings += Integer.valueOf(metricItem.getMetrics().get(METRIC_MAPPINGS).toString());
            counterTotalQueries += Integer.valueOf(metricItem.getMetrics().get(METRIC_QUERIES).toString());
            counterTotalHelpers += Integer.valueOf(metricItem.getMetrics().get(METRIC_HELPERS).toString());
        }
        resultSet.getTotalMetrics().put(METRIC_MAPPINGS, counterTotalMappings);
        resultSet.getTotalMetrics().put(METRIC_QUERIES, counterTotalQueries);
        resultSet.getTotalMetrics().put(METRIC_HELPERS, counterTotalHelpers);

        // set the available metrics
        resultSet.getAvailableMetrics().add(METRIC_MAPPINGS);
        resultSet.getAvailableMetrics().add(METRIC_QUERIES);
        resultSet.getAvailableMetrics().add(METRIC_HELPERS);

        return resultSet;
    }

}

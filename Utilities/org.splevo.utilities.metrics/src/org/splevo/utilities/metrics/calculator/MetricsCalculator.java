package org.splevo.utilities.metrics.calculator;

import java.util.List;

/**
 * A calculator able to calculate metrics for specific types of elements.
 * 
 * @author Benjamin Klatt
 *
 */
public interface MetricsCalculator {

    /**
     * Return if an item can be analyzed by the calculator.
     * 
     * @param item
     *            The item to check.
     * @return True/false if it can be analyzed or not.
     */
    public boolean isSupported(Object item);

    /**
     * Calculate the metric for this item.
     * 
     * @param item
     *            The item to analyze.
     * @return The metrics for this item.
     * @throws MetricCalculationException identifies a failes metric calculation.
     */
    public MetricItem calculateSingleMetric(Object item) throws MetricCalculationException;

    /**
     * Calculate a full metrics result set for a list of items to analyze.
     * 
     * @param items
     *            The items to analyze.
     * @return The calculated metrics result set.
     * @throws MetricCalculationException
     *             identifies that the metric can not be calculated successfully.
     */
    public MetricsResultSet calculateMetrics(List<Object> items) throws MetricCalculationException;
}

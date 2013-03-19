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
     * Calculate a full metrics result set for a list of items to analyze.
     * 
     * @param items
     *            The items to analyze.
     * @return The calculated metrics result set.
     * @throws MetricCalculationException
     *             identifies that the metric can not be calculated successfully.
     */
    public MetricsResultSet calculateMetrics(List<Object> items) throws MetricCalculationException;
    
    /**
     * Get the identifier of the calculator.
     * @return The id string.
     */
    public String getId();
}

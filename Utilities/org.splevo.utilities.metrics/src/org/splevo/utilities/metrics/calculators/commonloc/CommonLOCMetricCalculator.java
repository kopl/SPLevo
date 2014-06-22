package org.splevo.utilities.metrics.calculators.commonloc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.PackageFragmentRoot;
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
@SuppressWarnings("restriction")
public class CommonLOCMetricCalculator implements MetricsCalculator {

	/** Metric key for the non empty lines count. */
	public static final String METRIC_LINES_NON_EMPTY = "Lines Non-Empty";

	/** Metric key for the total lines count. */
	public static final String METRIC_LINES_TOTAL = "Lines Total";
	/** Result set for LoC Metrics . */
	private MetricsResultSetImpl resultSet = new MetricsResultSetImpl(
			"File Metrics");
	/** Resource list needed for adding resources on the fly. */
	private ArrayList<Object> resourceList = new ArrayList<Object>();

	@Override
	public MetricsResultSet calculateMetrics(List<Object> items)
			throws MetricCalculationException {

		resourceList.addAll(items);

		// calculate the individual metrics
		// iterate over the resource list - if there is a folder the convent of
		// that folder
		// (members) are added dynamically to that list.
		for (int i = 0; i < resourceList.size(); i++) {
			Object item = resourceList.get(i);

			try {
				handleFileElements(item);
				handleJavaElements(item);
			} catch (CoreException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

		// calculate the total metrics
		int counterTotal = 0;
		int counterTotalNonEmpty = 0;
		for (MetricResultItem metricItem : resultSet.getMetricResultItems()) {
			counterTotal += Integer.valueOf(metricItem.get(METRIC_LINES_TOTAL)
					.toString());
			counterTotalNonEmpty += Integer.valueOf(metricItem.get(
					METRIC_LINES_NON_EMPTY).toString());
		}
		resultSet.getTotalMetrics().put(METRIC_LINES_TOTAL, counterTotal);
		resultSet.getTotalMetrics().put(METRIC_LINES_NON_EMPTY,
				counterTotalNonEmpty);

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
	 * @return The metrics for this item. Or null if no metric could be
	 *         calculated.
	 * @throws MetricCalculationException
	 *             identifies a failed metric calculation.
	 */
	public MetricResultItem calculateSingleMetric(IFile fileItem)
			throws MetricCalculationException {

		MetricResultItemImpl metricItem = new MetricResultItemImpl(
				fileItem.getName(), fileItem.getLocationURI());

		int counterTotal = 0;
		int counterNonEmpty = 0;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					fileItem.getContents()));

			while (reader.ready()) {
				String line = reader.readLine().trim();
				counterTotal++;
				if (line.length() > 0) {
					counterNonEmpty++;
				}
			}
		} catch (Exception e) {
			throw new MetricCalculationException(
					"Failed to load file to analyze", e);
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

	/**
	 * Handles if selection happened in project explorer. Works with
	 * ICompilationUnits and PackageFragments
	 * 
	 * @param item
	 *            to analyze
	 * @throws MetricCalculationException
	 *             thrown by calculateSingleMetric
	 * @throws JavaModelException
	 *             to get underlying resource
	 */
	private void handleJavaElements(Object item)
			throws MetricCalculationException, JavaModelException {
		if (item instanceof ICompilationUnit) {
			IResource res = ((ICompilationUnit) item).getUnderlyingResource();
			if (res.getType() == IResource.FILE) {
				MetricResultItem metricItem = calculateSingleMetric((IFile) res);
				resultSet.getMetricResultItems().add(metricItem);
			}
		}
		if (item instanceof IPackageFragment) {
			for (IJavaElement element : ((IPackageFragment) item).getChildren()) {
				if (element instanceof ICompilationUnit) {
					resourceList.add(element);
				}
			}
		}
		if (item instanceof PackageFragmentRoot) {
			for (IJavaElement element : ((PackageFragmentRoot) item)
					.getChildren()) {
				if (element instanceof ICompilationUnit
						|| element instanceof IPackageFragment) {
					resourceList.add(element);
				}
			}
		}
	}

	/**
	 * Handles if selection happened in Navigator View. Works with Files
	 * objects.
	 * 
	 * @param item
	 *            to analyze
	 * @throws MetricCalculationException
	 *             thrown by calculateSingleMetric
	 * @throws CoreException
	 *             to get underlying resource
	 */
	private void handleFileElements(Object item)
			throws MetricCalculationException, CoreException {
		if (item instanceof IFolder) {
			// add members of the folder the resourceList
			for (IResource resource : ((IFolder) item).members()) {
				resourceList.add(resource);
			}
		}
		if (item instanceof IProject) {
			// add members of the folder the resourceList
			for (IResource resource : ((IProject) item).members()) {
				resourceList.add(resource);
			}
		}
		if (item instanceof IFile) {
			// calculate the metrics if the item is a File.
			MetricResultItem metricItem = calculateSingleMetric((IFile) item);
			resultSet.getMetricResultItems().add(metricItem);
		}
	}

}

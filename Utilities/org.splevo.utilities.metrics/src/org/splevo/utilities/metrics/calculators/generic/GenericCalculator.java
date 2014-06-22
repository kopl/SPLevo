package org.splevo.utilities.metrics.calculators.generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.eclipse.jface.preference.IPreferenceStore;
import org.splevo.utilities.metrics.Activator;
import org.splevo.utilities.metrics.calculator.MetricCalculationException;
import org.splevo.utilities.metrics.calculator.MetricsCalculator;
import org.splevo.utilities.metrics.calculator.MetricsResultSet;
import org.splevo.utilities.metrics.calculator.impl.MetricResultItemImpl;
import org.splevo.utilities.metrics.calculator.impl.MetricsResultSetImpl;
import org.splevo.utilities.metrics.util.Constants;

/**
 * Generic Metrics Calculator that can be enabled through the Preferences Menu.
 * String can be given to this Calculator and it checks for the number of lines
 * that start with the given prefix.
 * 
 * @author Bodo Vossen
 * 
 */
@SuppressWarnings("restriction")
public class GenericCalculator implements MetricsCalculator {

	/** Array for saving the metrics. */
	private String[] metrics;

	/** Preferences Store for Preferences Page. */
	private IPreferenceStore prefStore = Activator.getDefault()
			.getPreferenceStore();
	/** Resultset for generic Calculator. */
	private MetricsResultSetImpl resultSet = new MetricsResultSetImpl(
			"Generic Metrics");

	/** Hashmap needed for calculation. */
	private HashMap<String, Integer> counter = new HashMap<String, Integer>();

	/** resource list needed for adding resources on the fly. */
	private ArrayList<Object> resourceList = new ArrayList<Object>();

	@Override
	public MetricsResultSet calculateMetrics(List<Object> items)
			throws MetricCalculationException {

		String input = prefStore.getString(Constants.METRICS);
		if (!input.trim().equals("")) {
			resourceList.addAll(items);
			for (int i = 0; i < resourceList.size(); i++) {
				Object item = resourceList.get(i);
				try {
					handleFileElements(item, input);
					handleJavaElements(item, input);
				} catch (JavaModelException e) {
					e.printStackTrace();
				}
			}

			for (String key : counter.keySet()) {
				resultSet.getTotalMetrics().put(key, counter.get(key));
				resultSet.addAvailableMetric(key);
			}
			return resultSet;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param item
	 *            file to analyze
	 * @param input
	 *            the actual metric
	 * @throws JavaModelException
	 *             thrown by getUnderlyingResources
	 * @throws MetricCalculationException
	 *             thrown by handleSingleFile()
	 */
	private void handleJavaElements(Object item, String input)
			throws JavaModelException, MetricCalculationException {
		if (item instanceof ICompilationUnit) {
			IResource res = ((ICompilationUnit) item).getUnderlyingResource();
			if (res.getType() == IResource.FILE) {
				handleSingleFile((IFile) res, input);
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
	 * Handles selection from Navigator view. Works with file elements.
	 * 
	 * @param item
	 *            file to analyze
	 * @param input
	 *            the actual metric
	 * @throws MetricCalculationException
	 *             thrown by calculateSingleMetric
	 */
	private void handleFileElements(Object item, String input)
			throws MetricCalculationException {
		if (item instanceof IFolder) {
			try {
				// add members of the folder the resourceList
				for (IResource resource : ((IFolder) item).members()) {
					resourceList.add(resource);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		if (item instanceof IProject) {
			try {
				// add members of the folder the resourceList
				for (IResource resource : ((IProject) item).members()) {
					resourceList.add(resource);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		if (item instanceof IFile) {
			// calculate the metrics if the item is a File.
			IFile fileItem = (IFile) item;
			handleSingleFile(fileItem, input);

		}
	}

	/**
	 * Handles on single file.
	 * 
	 * @param fileItem
	 *            file to analyze
	 * @param input
	 *            the input specified in pref page.
	 * @throws MetricCalculationException
	 *             thrown by calcSingleMetric
	 */
	private void handleSingleFile(IFile fileItem, String input)
			throws MetricCalculationException {
		MetricResultItemImpl metricItem = new MetricResultItemImpl(
				fileItem.getName(), fileItem.getLocationURI());
		metrics = input.split(",");

		for (int j = 0; j < metrics.length; j++) {
			int result = calculateSingleMetric(fileItem, metrics[j]);
			if (counter.containsKey(metrics[j])) {
				counter.put(metrics[j], counter.get(metrics[j]) + result);
			} else {
				counter.put(metrics[j], result);
			}
			metricItem.put(metrics[j], result);
		}
		resultSet.getMetricResultItems().add(metricItem);
	}

	/**
	 * Calculates a metric that was given as a Parameter. Checks if the lines
	 * starts with the given prefix and return the MetricResult.
	 * 
	 * @param fileItem
	 *            the file to check
	 * @param metric
	 *            the given prefix
	 * @return MetricResultItem
	 * @throws MetricCalculationException
	 *             identifies a failed metric calculation.
	 */
	private int calculateSingleMetric(IFile fileItem, String metric)
			throws MetricCalculationException {

		int counterTotal = 0;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					fileItem.getContents()));

			while (reader.ready()) {
				String line = reader.readLine().trim().toLowerCase();
				if (line.startsWith(metric.toLowerCase())) {
					counterTotal++;
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
		return counterTotal;
	}

	@Override
	public String getId() {
		return "Generic Metrics Calculator";
	}

}

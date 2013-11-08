package org.splevo.utilities.metrics.calculators.preprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.splevo.utilities.metrics.calculator.MetricCalculationException;
import org.splevo.utilities.metrics.calculator.MetricResultItem;
import org.splevo.utilities.metrics.calculator.MetricsCalculator;
import org.splevo.utilities.metrics.calculator.MetricsResultSet;
import org.splevo.utilities.metrics.calculator.impl.MetricResultItemImpl;
import org.splevo.utilities.metrics.calculator.impl.MetricsResultSetImpl;

/**
 * Class for calculating metrics for the preprocessor. Currently .java and C/C++
 * files are supported.
 * 
 * @author Bodo Vossen
 * 
 */
public class PreprocessorMetricCalculator implements MetricsCalculator {

	/** Metric key for the number of #if. */
	public static final String METRIC_IF = "if";

	/** Metric key for the number of #else. */
	public static final String METRIC_ELSE = "else";

	/** Metric key for the number of #ifdef. */
	public static final String METRIC_IFDEF = "ifdef";

	/** Metric key for the number of #ifndef. */
	public static final String METRIC_IFNDEF = "ifndef";

	/** Metric key for the number of #elif. */
	public static final String METRIC_ELIF = "elif";

	/** Metric key for the number of #endif. */
	public static final String METRIC_ENDIF = "endif";

	/** Metric key for the number of #define. */
	public static final String METRIC_DEFINE = "define";

	/** Metric key for the number of #undefine. */
	public static final String METRIC_UNDEFINE = "undefine";

	/** Metric key for the number of #include. */
	public static final String METRIC_INCLUDE = "include";

	/** List with all the typical C/C++ file endings. */
	private static final ArrayList<String> POSSIBLE_C_ENDINGS = new ArrayList<String>(
			Arrays.asList(".c", ".C", ".cpp", ".CPP", ".c++"));

	/** Java ending. */
	private static final String JAVA_ENDING = ".java";

	/** Result set for Preprocessor metrics. */
	private MetricsResultSetImpl resultSet = new MetricsResultSetImpl(
			"Preprocessor Metrics");

	@Override
	public MetricsResultSet calculateMetrics(List<Object> items)
			throws MetricCalculationException {

		for (Object item : items) {
			if (item instanceof IFile) {
				handleFile((IFile) item);
			}
			if (item instanceof ICompilationUnit) {
				try {
					handleCompUnit(item);
				} catch (JavaModelException e) {
					e.printStackTrace();
				}
			}
		}

		// calculate total metrics
		// #if, #ifdef, #ifndef, #elif und #endif, #define and #include
		// #else and #undefine for java files
		int counterIf = 0;
		int counterIfDef = 0;
		int counterIfnDef = 0;
		int counterElif = 0;
		int counterEndIf = 0;
		int counterDefine = 0;
		int counterInclude = 0;

		int counterUnDefine = 0;
		int counterElse = 0;

		for (MetricResultItem metricItem : resultSet.getMetricResultItems()) {
			counterIf += Integer.valueOf(metricItem.get(METRIC_IF).toString());
			counterElse += Integer.valueOf(metricItem.get(METRIC_ELSE)
					.toString());
			counterIfDef += Integer.valueOf(metricItem.get(METRIC_IFDEF)
					.toString());
			counterIfnDef += Integer.valueOf(metricItem.get(METRIC_IFNDEF)
					.toString());
			counterElif += Integer.valueOf(metricItem.get(METRIC_ELIF)
					.toString());
			counterEndIf += Integer.valueOf(metricItem.get(METRIC_ENDIF)
					.toString());
			counterDefine += Integer.valueOf(metricItem.get(METRIC_DEFINE)
					.toString());
			counterUnDefine += Integer.valueOf(metricItem.get(METRIC_UNDEFINE)
					.toString());
			counterInclude += Integer.valueOf(metricItem.get(METRIC_INCLUDE)
					.toString());

		}

		resultSet.getTotalMetrics().put(METRIC_IF, counterIf);
		resultSet.getTotalMetrics().put(METRIC_ELSE, counterElse);
		resultSet.getTotalMetrics().put(METRIC_IFDEF, counterIfDef);
		resultSet.getTotalMetrics().put(METRIC_IFNDEF, counterIfnDef);
		resultSet.getTotalMetrics().put(METRIC_ELIF, counterElif);
		resultSet.getTotalMetrics().put(METRIC_ENDIF, counterEndIf);
		resultSet.getTotalMetrics().put(METRIC_DEFINE, counterDefine);
		resultSet.getTotalMetrics().put(METRIC_UNDEFINE, counterUnDefine);
		resultSet.getTotalMetrics().put(METRIC_INCLUDE, counterInclude);

		resultSet.addAvailableMetric(METRIC_IF);
		resultSet.addAvailableMetric(METRIC_ELSE);
		resultSet.addAvailableMetric(METRIC_IFDEF);
		resultSet.addAvailableMetric(METRIC_IFNDEF);
		resultSet.addAvailableMetric(METRIC_ELIF);
		resultSet.addAvailableMetric(METRIC_ENDIF);
		resultSet.addAvailableMetric(METRIC_DEFINE);
		resultSet.addAvailableMetric(METRIC_UNDEFINE);
		resultSet.addAvailableMetric(METRIC_INCLUDE);

		return resultSet;
	}

	/**
	 * Handles selection from Project Explorer. Works with Compilation Units.
	 * @param item file to analyze
	 * @throws JavaModelException thrown by getUnderlyingResource
	 * @throws MetricCalculationException thrown by handleFile
	 */
	private void handleCompUnit(Object item) throws JavaModelException,
			MetricCalculationException {
		if (item instanceof ICompilationUnit) {
			IResource res = ((ICompilationUnit) item).getUnderlyingResource();
			if (res.getType() == IResource.FILE) {
				handleFile((IFile) res);
			}
		}
	}

	/**
	 * Handles selection from Navigator View. Works with File objects.
	 * 
	 * @param item
	 *            file to analyze
	 * @throws MetricCalculationException
	 *             thrown by handleJavaFile and handleCFile
	 */
	private void handleFile(IFile item) throws MetricCalculationException {
		IFile fileItem = (IFile) item;
		MetricResultItem metricItem = null;
		if (fileItem.getName().endsWith(JAVA_ENDING)) {
			metricItem = handleJavaFile(fileItem);
		} else if (isCFile(fileItem.getName())) {
			metricItem = handleCFile(fileItem);
		}
		if (metricItem != null) {
			resultSet.addMetricResultItem(metricItem);
		}
	}

	/**
	 * Method for calculating the preprocessor metric for a given C/C++ File.
	 * Currently supports #if, #ifdef, #ifndef, #elif, #endif, #define and
	 * #include. All other values are set zero.
	 * 
	 * @param fileItem
	 *            the given file to analyze
	 * @return a metric item with the calculated metrics
	 * @throws MetricCalculationException
	 *             identifies a failed metric calculation
	 */
	private MetricResultItem handleCFile(IFile fileItem)
			throws MetricCalculationException {
		MetricResultItemImpl metricItem = new MetricResultItemImpl(
				fileItem.getName(), fileItem.getLocationURI());

		int counterIf = 0;
		int counterElse = 0;
		int counterIfDef = 0;
		int counterIfnDef = 0;
		int counterElif = 0;
		int counterEndIf = 0;
		int counterDefine = 0;
		int counterUnDefine = 0;
		int counterInclude = 0;

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(
					fileItem.getContents()));

			while (reader.ready()) {
				String line = reader.readLine().trim();
				if (line.startsWith("#if")) {
					counterIf++;
				} else if (line.startsWith("#ifdef")) {
					counterIfDef++;
				} else if (line.startsWith("#ifndef")) {
					counterIfnDef++;
				} else if (line.startsWith("#elif")) {
					counterElif++;
				} else if (line.startsWith("#endif")) {
					counterEndIf++;
				} else if (line.startsWith("#define")) {
					counterDefine++;
				} else if (line.startsWith("#include")) {
					counterInclude++;
				}
			}
		} catch (Exception e) {
			throw new MetricCalculationException(
					"Failed to load C/C++ file to analyze", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		metricItem.put(METRIC_IF, counterIf);
		metricItem.put(METRIC_ELSE, counterElse);
		metricItem.put(METRIC_IFDEF, counterIfDef);
		metricItem.put(METRIC_IFNDEF, counterIfnDef);
		metricItem.put(METRIC_ELIF, counterElif);
		metricItem.put(METRIC_ENDIF, counterEndIf);
		metricItem.put(METRIC_DEFINE, counterDefine);
		metricItem.put(METRIC_UNDEFINE, counterUnDefine);
		metricItem.put(METRIC_INCLUDE, counterInclude);

		return metricItem;
	}

	/**
	 * Method for calculating the preprocessor metric for a given .java File.
	 * Java Preprocessor supports only the following statements: if, elif, else,
	 * endif, define, undefine. All other values are set zero.
	 * 
	 * @param fileItem
	 *            given .java files
	 * @return the MetricResultItem
	 * @throws MetricCalculationException
	 *             identifies a failed metric calculation
	 */
	private MetricResultItem handleJavaFile(IFile fileItem)
			throws MetricCalculationException {
		MetricResultItemImpl metricItem = new MetricResultItemImpl(
				fileItem.getName(), fileItem.getLocationURI());

		int counterIf = 0;
		int counterElse = 0;
		int counterIfDef = 0;
		int counterIfnDef = 0;
		int counterElif = 0;
		int counterEndIf = 0;
		int counterDefine = 0;
		int counterUnDefine = 0;
		int counterInclude = 0;

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(
					fileItem.getContents()));

			while (reader.ready()) {
				String line = reader.readLine().trim();
				if (line.startsWith("//#if")) {
					counterIf++;
				} else if (line.startsWith("//#else")) {
					counterElse++;
				} else if (line.startsWith("//#elif")) {
					counterElif++;
				} else if (line.startsWith("//#endif")) {
					counterEndIf++;
				} else if (line.startsWith("//#define")) {
					counterDefine++;
				} else if (line.startsWith("//#undefine")) {
					counterUnDefine++;
				}
			}
		} catch (Exception e) {
			throw new MetricCalculationException(
					"Failed to load .java file to analyze", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		metricItem.put(METRIC_IF, counterIf);
		metricItem.put(METRIC_ELSE, counterElse);
		metricItem.put(METRIC_IFDEF, counterIfDef);
		metricItem.put(METRIC_IFNDEF, counterIfnDef);
		metricItem.put(METRIC_ELIF, counterElif);
		metricItem.put(METRIC_ENDIF, counterEndIf);
		metricItem.put(METRIC_DEFINE, counterDefine);
		metricItem.put(METRIC_UNDEFINE, counterUnDefine);
		metricItem.put(METRIC_INCLUDE, counterInclude);

		return metricItem;
	}

	/**
	 * Method for determining if the given file is a C/C++ File. If file suffix
	 * is one of ".c", ".C", ".cpp", ".CPP", ".c++" the method return true.
	 * False otherwise.
	 * 
	 * @param fileName
	 *            given filename
	 * @return true if suffix is one of the above.
	 */
	private boolean isCFile(String fileName) {
		boolean result = false;
		for (String entry : POSSIBLE_C_ENDINGS) {
			if (fileName.endsWith(entry)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public String getId() {
		return "Preprocessor Metric Calculator";
	}

}

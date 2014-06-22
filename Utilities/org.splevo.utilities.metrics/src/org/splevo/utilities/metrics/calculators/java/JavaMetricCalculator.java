package org.splevo.utilities.metrics.calculators.java;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.core.PackageFragmentRoot;
import org.splevo.utilities.metrics.calculator.MetricCalculationException;
import org.splevo.utilities.metrics.calculator.MetricResultItem;
import org.splevo.utilities.metrics.calculator.MetricsCalculator;
import org.splevo.utilities.metrics.calculator.MetricsResultSet;
import org.splevo.utilities.metrics.calculator.impl.MetricResultItemImpl;
import org.splevo.utilities.metrics.calculator.impl.MetricsResultSetImpl;

/**
 * Class for calculating metrics specific for Java Code. Examples include number
 * of methods, classes and packages for a given project.
 * 
 * @author Bodo Vossen
 * 
 */
@SuppressWarnings("restriction")
public class JavaMetricCalculator implements MetricsCalculator {

	/** Metric key for the number of methods. */
	public static final String NUMBER_OF_METHODS = "Number of Methods";

	/** Metric key for the number of classes. */
	public static final String NUMBER_OF_CLASSES = "Number of Classes";

	/** Metric key for the number of packages. */
	public static final String NUMBER_OF_PACKAGES = "Number of Packages";

	/** Metric key for the number of inner classes. */
	public static final String NUMBER_OF_INNER_CLASSES = "Number of inner Classes";

	/** Result-Set for the Java Metrics. */
	private MetricsResultSetImpl resultSet = new MetricsResultSetImpl(
			"Java Metrics");

	/**
	 * List for iterating over resources, needed for recursive directory
	 * support.
	 */
	private ArrayList<Object> resourceList = new ArrayList<Object>();

	@Override
	public MetricsResultSet calculateMetrics(List<Object> items)
			throws MetricCalculationException {

		resourceList.addAll(items);

		// recursive directory support, add items while iterating.
		// if folder then add members, if file then calculate metric.
		for (int i = 0; i < resourceList.size(); i++) {

			Object item = resourceList.get(i);
			try {
				handleFileElements(item);
				handleJavaElements(item);
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int counterMethods = 0;
		int counterClasses = 0;
		int counterInnerClasses = 0;
		int counterPackages = 0;

		for (MetricResultItem metricItem : resultSet.getMetricResultItems()) {
			if (metricItem.get(NUMBER_OF_METHODS) != null) {
				counterMethods += Integer.valueOf(metricItem.get(
						NUMBER_OF_METHODS).toString());
			}
			if (metricItem.get(NUMBER_OF_CLASSES) != null) {
				counterClasses += Integer.valueOf(metricItem.get(
						NUMBER_OF_CLASSES).toString());
			}
			if (metricItem.get(NUMBER_OF_INNER_CLASSES) != null) {
				counterInnerClasses += Integer.valueOf(metricItem.get(
						NUMBER_OF_INNER_CLASSES).toString());
			}
			if (metricItem.get(NUMBER_OF_PACKAGES) != null) {
				counterPackages += Integer.valueOf(metricItem.get(
						NUMBER_OF_PACKAGES).toString());
			}
		}

		resultSet.getTotalMetrics().put(NUMBER_OF_METHODS, counterMethods);
		resultSet.getTotalMetrics().put(NUMBER_OF_CLASSES, counterClasses);
		resultSet.getTotalMetrics().put(NUMBER_OF_INNER_CLASSES,
				counterInnerClasses);
		resultSet.getTotalMetrics().put(NUMBER_OF_PACKAGES, counterPackages);
		resultSet.addAvailableMetric(NUMBER_OF_METHODS);
		resultSet.addAvailableMetric(NUMBER_OF_CLASSES);
		resultSet.addAvailableMetric(NUMBER_OF_INNER_CLASSES);
		resultSet.addAvailableMetric(NUMBER_OF_PACKAGES);

		// Delete all items before next call.
		resourceList.clear();

		return resultSet;
	}

	/**
	 * Handles if selection happened in project explorer. Works with
	 * ICompilationUnits and PackageFragments
	 * 
	 * @param item
	 *            to analyze
	 * @throws JavaModelException
	 *             to get underlying resource
	 */
	private void handleJavaElements(Object item) throws JavaModelException {
		if (item instanceof ICompilationUnit) {
			IResource res = ((ICompilationUnit) item).getUnderlyingResource();
			if (res.getType() == IResource.FILE) {
				MetricResultItem metricItem = handleFile((IFile) res);
				resultSet.getMetricResultItems().add(metricItem);
			}
		}
		if (item instanceof IPackageFragment) {
			for (IJavaElement element : ((IPackageFragment) item).getChildren()) {
				if (element instanceof ICompilationUnit && !element.toString().contains("package-info")) {
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
	 */
	private void handleFileElements(Object item) {
		if (item instanceof IProject) {
			handleProject(item);
		} else if (item instanceof IFolder) {
			handleFolder(item);
		} else if (item instanceof IFile) {
			MetricResultItem metricItem = handleFile((IFile) item);
			resultSet.getMetricResultItems().add(metricItem);
		}
	}

	/**
	 * Handles how an IFile object is treated. If it is an .java file (and not
	 * package-info) the method calculateNumberOfMethods is called.
	 * 
	 * @param item
	 *            is the file
	 * @return metricItem metricItem with the results
	 */
	private MetricResultItem handleFile(IFile item) {

		MetricResultItemImpl metricItem = new MetricResultItemImpl(
				item.getName(), item.getLocationURI());

		// only for java files and not package-info
		if (((IFile) item).getFileExtension().equals("java")
				&& !item.toString().contains("package-info")) {

			int numberOfMethods = calculateNumberOfMethods((IFile) item);
			int numberOfClasses = calculateNumberOfClasses((IFile) item);
			int numberOfInnerClasses = calculateNumberOfInnerClasses((IFile) item);

			metricItem.put(NUMBER_OF_METHODS, numberOfMethods);
			metricItem.put(NUMBER_OF_CLASSES, numberOfClasses);
			metricItem.put(NUMBER_OF_INNER_CLASSES, numberOfInnerClasses);
		}
		return metricItem;
	}

	/**
	 * Handles how an IFolder object is treated. Add the members of the folder
	 * to the resourceList. For files only .java files are added, and no
	 * package-info.
	 * 
	 * @param item
	 *            is the folder
	 */
	private void handleFolder(Object item) {
		try {
			// add members of the folder the resourceList
			for (IResource resource : ((IFolder) item).members()) {
				// add folders
				if (resource instanceof IFolder) {
					resourceList.add(resource);
					// exclude the package.info file and only add java
					// files.
				} else if (resource instanceof IFile
						&& resource.getFileExtension().equals("java")
						&& !resource.toString().contains("package-info")) {
					resourceList.add(resource);
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles how an IProject object is treated. If it is an JavaProject an the
	 * method calculateNumberOfPackages is called. Furthermore the members of
	 * the Project are added, but only folder, .java files and no package-info
	 * 
	 * @param item
	 *            is the Project
	 */
	private void handleProject(Object item) {
		try {
			if (((IProject) item).hasNature(JavaCore.NATURE_ID)) {
				//calculate number of packages
				MetricResultItem metricItem = calculateNumberOfPackages(JavaCore
						.create((IProject) item));
				resultSet.getMetricResultItems().add(metricItem);

				//add members
				for (IResource resource : ((IProject) item).members()) {
					// add folders
					if (resource instanceof IFolder) {
						resourceList.add(resource);
						// exclude the package.info file and only add
						// java files.
					} else if (resource instanceof IFile
							&& resource.getFileExtension().equals("java")
							&& !resource.toString().contains("package-info")) {
						resourceList.add(resource);
					}
				}

			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calculates the number of Packages of an IJavaProject.
	 * 
	 * @param item
	 *            is the IJavaProject file
	 * @return a MetricResultItem with the Metric NUMBER_OF_PACKAGES
	 */
	private MetricResultItem calculateNumberOfPackages(IJavaProject item) {

		int numberOfPackages = 0;
		MetricResultItemImpl metricItem = null;

		// work around to get the URI needed for creating a
		// MetricResultItemImpl.
		URI uri = null;
		try {
			uri = new URI(item.getPath().toString());
			metricItem = new MetricResultItemImpl(item.getElementName(), uri);
			IPackageFragment[] packages = item.getPackageFragments();

			// loop necessary to get only the packages from the source folder.
			// K.SOURCE does that.

			for (IPackageFragment mypackage : packages) {
				if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
					numberOfPackages += 1;
				}
			}
			metricItem.put(NUMBER_OF_PACKAGES, numberOfPackages);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return metricItem;
	}

	/**
	 * Calculates the number of Classes for a given IIFile.
	 * 
	 * @param fileItem
	 *            item to analyze
	 * @return a MetricResultItem with the computed values
	 */
	private int calculateNumberOfClasses(IFile fileItem) {

		ICompilationUnit unit = JavaCore.createCompilationUnitFrom(fileItem);
		int counter = 0;
		try {
			IType[] types = unit.getAllTypes();
			for (IType type : types) {
				if (type.isClass()) {
					counter++;
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}

		return counter;
	}

	/**
	 * Method for calculating the Number of methods of an IFile. From the IFile
	 * a ICompilationUnit is created to work on the Eclipse Java Model.
	 * 
	 * @param fileItem
	 *            of Type IFile
	 * @return the resulting metricItem
	 */
	private int calculateNumberOfMethods(IFile fileItem) {
		int numberOfMethods = 0;

		ICompilationUnit unit = JavaCore.createCompilationUnitFrom(fileItem);

		if (unit instanceof ICompilationUnit) {
			try {
				IType[] types = unit.getAllTypes();

				for (int i = 0; i < types.length; i++) {
					IType type = types[i];
					numberOfMethods = type.getMethods().length;
				}

			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}

		return numberOfMethods;
	}

	@Override
	public String getId() {
		return "Java Metrics Calculator";
	}

	/**
	 * Creates AST DOM for ICompilationUnit.
	 * 
	 * @param unit
	 *            given ICompilationUnit
	 * @return this
	 */
	private static CompilationUnit parse(ICompilationUnit unit) {
		@SuppressWarnings("deprecation")
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}

	/**
	 * Calculates Number of InnerClasses.
	 * 
	 * @param fileItem
	 *            to analyze
	 * @return metricItem
	 */
	private int calculateNumberOfInnerClasses(IFile fileItem) {
		ICompilationUnit unit = JavaCore.createCompilationUnitFrom(fileItem);

		// Now create the AST for the ICompilationUnits
		CompilationUnit parse = parse(unit);
		InnerClassesVisitor visitor = new InnerClassesVisitor();
		parse.accept(visitor);

		int counter = visitor.getCounterInnerClasses();
		return counter;
	}

}

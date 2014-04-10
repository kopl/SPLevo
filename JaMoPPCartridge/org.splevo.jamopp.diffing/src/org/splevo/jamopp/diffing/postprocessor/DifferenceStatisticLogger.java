/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.diffing.postprocessor;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.MatchResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.resource.java.mopp.JavaPrinter2;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.emftext.language.java.statements.Statement;
import org.splevo.jamopp.diffing.jamoppdiff.CompilationUnitChange;
import org.splevo.jamopp.diffing.jamoppdiff.StatementChange;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Logger for statistics about the identified differences.
 *
 * The logger produces a set of csv files containing the details about the loggings identified. Call
 * log(Comparison,String) as central method do produce the logs.
 */
public class DifferenceStatisticLogger {

    /** The class logger for software issues to use. */
    private static Logger logger = Logger.getLogger(DifferenceStatisticLogger.class);

    /**
     * Trigger all logs for analyzing the case study.
     *
     * @param comparison
     *            The comparison to get the result infos for.
     * @param logDir
     *            The directory to log the statistics to. It should end with a slash "/".
     */
    public static void log(Comparison comparison, String logDir) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");
        if (!logDir.endsWith(File.separator)) {
            logDir = logDir + File.separator;
        }
        String runLogDir = logDir + dateFormat.format(new Date());
        DifferenceStatisticLogger.logDiffingStatistics(comparison, runLogDir + File.separator + "log-diffings-statistics.csv");
        DifferenceStatisticLogger.logDiffings(comparison, runLogDir + File.separator + "log-diffings.csv");
        DifferenceStatisticLogger.logResourceMatchings(comparison, runLogDir + File.separator + "log-resource-matches.csv");
    }

    /**
     * Write a log file about matched and unmatched resources.
     *
     * @param comparison
     *            model
     * @param filePath
     *            the filename
     */
    private static void logDiffings(Comparison comparison, String filePath) {
        BufferedWriter writer = null;
        try {
            File logFile = new File(filePath);
            FileUtils.forceMkdir(logFile.getParentFile());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("ChangeKind ; ChangeType ; Containing Resource; Changed Element; Container Element \n");
            for (Diff diff : comparison.getDifferences()) {
                String containingResource = getContainingResource(diff);
                String changeKind = diff.getKind().getLiteral();

                if (diff instanceof CompilationUnitChange) {
                    CompilationUnitChange change = (CompilationUnitChange) diff;
                    writer.write(changeKind + ";CompilationUnitChange;" + containingResource + ";"
                            + change.getChangedCompilationUnit().getName() + ";\n");
                } else if (diff instanceof StatementChange) {
                    StatementChange change = (StatementChange) diff;

                    Statement statement = change.getChangedStatement();
                    Method containingMethod = getContainingMethod(statement);
                    String containingElement = null;
                    if (containingMethod != null) {
                        containingElement = containingMethod.getName() + "()";
                    } else if (statement.eContainer() instanceof Constructor) {
                        Constructor constructor = (Constructor) statement.eContainer();
                        containingElement = constructor.getName() + "()";
                    } else {
                        containingElement = "" + statement.eContainer();
                    }
                    writer.write(changeKind + ";StatementChange;" + containingResource + ";" + statement + ";"
                            + containingElement + "\n");
                } else {
                    writer.write(changeKind + ";" + diff.getClass().getSimpleName() + ";" + containingResource + "\n");
                }
            }
        } catch (IOException e) {
            logger.error("Failed to write to statistic logger.", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.error("Failed to close statistic log writer.", e);
                }
            }
        }
    }

    /**
     * Get an identifier of the resource containing the original source element.
     *
     * @param diff
     *            The difference.
     * @return The identifier of the resource.
     */
    private static String getContainingResource(Diff diff) {
        EObject orig = null;
        if (diff.getMatch().getRight() != null) {
            orig = diff.getMatch().getRight();
        } else {
            orig = diff.getMatch().getLeft();
        }
        String containingResource = null;
        if (orig != null) {
            containingResource = getRelativeSourcePath(orig.eResource());
        } else {
            containingResource = "unknown";
        }
        return containingResource;
    }

    /**
     * Get the string representation of a JaMoPP element.
     *
     * @param element
     *            The element to get the string representation for.
     * @return The formatted code snippet.
     */
    @SuppressWarnings("unused")
    private static String getStringRepresentation(Commentable element) {
        String printString = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JavaPrinter2 printer = new JavaPrinter2(stream, new JavaResource());
        try {
            printer.print(element);
        } catch (IOException e) {
            System.err.println("Failed to print element");
        }
        printString = stream.toString();
        return printString;
    }

    /**
     * Get the method a statement is located in. Returns null if there is no method container in the
     * statement's parent tree.
     *
     * @param statement
     *            The statement to get the enclosing method for.
     * @return The method or null if none found.
     */
    private static Method getContainingMethod(Statement statement) {
        EObject container = statement.eContainer();
        Method containingMethod = null;
        while (container != null) {
            if (container instanceof Method) {
                containingMethod = (Method) container;
                break;
            } else {
                container = container.eContainer();
            }
        }
        return containingMethod;
    }

    /**
     * Write a log file about matched and unmatched resources.
     *
     * @param comparison
     *            model
     * @param logFilePath
     *            the filename
     */
    @SuppressWarnings("rawtypes")
    private static void logDiffingStatistics(Comparison comparison, String logFilePath) {

        Table<Class, DifferenceKind, Integer> statistics = HashBasedTable.create();

        for (Diff diff : comparison.getDifferences()) {
            Integer current = statistics.get(diff.getClass(), diff.getKind());
            if (current == null) {
                current = 0;
            }
            current++;
            statistics.put(diff.getClass(), diff.getKind(), current);
        }

        BufferedWriter writer = null;
        try {
            File logFile = new File(logFilePath);
            FileUtils.forceMkdir(logFile.getParentFile());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("Type ; Kind ; Count \n");
            for (Class rowKey : statistics.rowKeySet()) {
                Map<DifferenceKind, Integer> row = statistics.row(rowKey);
                for (DifferenceKind kind : row.keySet()) {
                    writer.write(rowKey.getSimpleName() + ";" + kind.getLiteral() + ";" + row.get(kind) + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Write a log file about matched and unmatched resources.
     *
     * @param comparison
     *            model
     * @param logFilePath
     *            the filename
     */
    private static void logResourceMatchings(Comparison comparison, String logFilePath) {
        BufferedWriter writer = null;
        try {
            File logFile = new File(logFilePath);
            FileUtils.forceMkdir(logFile.getParentFile());

            writer = new BufferedWriter(new FileWriter(logFile));
            for (MatchResource matchResource : comparison.getMatchedResources()) {
                String left = getRelativeSourcePath(matchResource.getLeft());
                String right = getRelativeSourcePath(matchResource.getRight());
                if ("pathmap:/javaclass/java.lang.Object.java".equals(left)) {
                    continue;
                }
                writer.write(left + ";" + right + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Cut of absolute source directory path.
     *
     * @param resource
     *            the resource to get the relative path for
     * @return the string
     */
    private static String getRelativeSourcePath(Resource resource) {

        if (resource == null || resource.getURI() == null) {
            return "";
        }

        String absolutePath = resource.getURI().toString();

        if (absolutePath.indexOf("src") > -1) {
            absolutePath = absolutePath.substring(absolutePath.indexOf("src"));
        }
        return absolutePath;
    }
}

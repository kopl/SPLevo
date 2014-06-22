/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * Static registry for available VPM analyzers.
 */
public class VPMAnalyzerRegistry {

    private static List<VPMAnalyzer> analyzers = Lists.newArrayList();

    /**
     * Register a new analyzer.
     *
     * Note: If an analyzer instance has already been registered, nothing is done.<br>
     * Instance and name checking is done.
     *
     * @param analyzer
     *            The analyzer to register.
     */
    public static void registerVPMAnalyzer(VPMAnalyzer analyzer) {
        if (isValid(analyzer) && isNotRegistered(analyzer)) {
            analyzers.add(analyzer);
        }
    }

    /**
     * Get the list of registered vpm analyzer.
     *
     * The list will be ordered according to the analyzer's name.
     *
     * @return The current list.
     */
    public static List<VPMAnalyzer> getVPMAnalyzers() {
        Collections.sort(analyzers, new Comparator<VPMAnalyzer>() {
            @Override
            public int compare(VPMAnalyzer d1, VPMAnalyzer d2) {
                return Strings.nullToEmpty(d1.getName()).compareTo(d2.getName());
            }
        });
        return analyzers;
    }

    /**
     * Ensure the differ is not null and all required attributes set.
     *
     * @param differ
     *            The differ to test.
     * @return True/false if it is valid or not.
     */
    private static boolean isValid(VPMAnalyzer differ) {
        if (differ == null || differ.getName() == null) {
            return false;
        }
        return true;
    }

    private static boolean isNotRegistered(VPMAnalyzer differ) {
        for (VPMAnalyzer existingAnalyzer : analyzers) {
            if (existingAnalyzer.getName().equals(differ.getName())) {
                return false;
            }
        }
        return true;
    }

}

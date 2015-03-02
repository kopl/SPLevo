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

import org.splevo.commons.registry.RegistryBase;

import com.google.common.base.Strings;

/**
 * Registry for available VPM analyzers.
 */
public enum VPMAnalyzerRegistry {
    INSTANCE;
    
    private final InnerVPMAnalyzerRegistry innerRegistry = new InnerVPMAnalyzerRegistry();
    
    /**
     * @return The singleton instance.
     */
    public static InnerVPMAnalyzerRegistry getInstance() {
        return INSTANCE.innerRegistry;
    }
    
    /**
     * Implementation of registry for available VPM analyzers.
     */
    public class InnerVPMAnalyzerRegistry extends RegistryBase<VPMAnalyzer> {

        @Override
        protected boolean areElementsConsideredTheSame(VPMAnalyzer element1, VPMAnalyzer element2) {
            return element1.getName().equals(element2.getName());
        }

        @Override
        protected int compareElements(VPMAnalyzer element1, VPMAnalyzer element2) {
            return Strings.nullToEmpty(element1.getName()).compareTo(element2.getName());
        }

    }
    
}
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
package org.splevo.vpm.analyzer.mergedecider;

import org.splevo.commons.registry.RegistryBase;

import com.google.common.base.Strings;

/**
 * Registry for available merge deciders.
 */
public enum MergeDeciderRegistry {
    INSTANCE;
    
    private final InnerMergeDeciderRegistry innerRegistry = new InnerMergeDeciderRegistry();
    
    /**
     * @return The singleton instance.
     */
    public static InnerMergeDeciderRegistry getInstance() {
        return INSTANCE.innerRegistry;
    }
    
    /**
     * Implementation of the MergeDecider registry.
     */
    public class InnerMergeDeciderRegistry extends RegistryBase<MergeDecider> {
        
        /**
         * Clear all registered decider.<br>
         * This should be called if you need to ensure there are no decider registered previously.
         */
        public void clearRegistry() {
            elements.clear();
        }

        @Override
        protected boolean areElementsConsideredTheSame(MergeDecider element1, MergeDecider element2) {
            return element1.getClass().equals(element2.getClass());
        }

        @Override
        protected int compareElements(MergeDecider element1, MergeDecider element2) {
            String name1 = element1.getClass().getSimpleName();
            String name2 = element2.getClass().getSimpleName();
            return Strings.nullToEmpty(name1).compareTo(name2);
        }

    }
    
}



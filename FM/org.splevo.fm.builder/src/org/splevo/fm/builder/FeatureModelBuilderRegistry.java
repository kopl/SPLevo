/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.fm.builder;

import org.splevo.commons.registry.IdBasedRegistryBase;

/**
 * A registry for FeatureModelBuilder instances implemented as singleton.
 */
public enum FeatureModelBuilderRegistry {

    INSTANCE;

    private final InnerFeatureModelBuilderRegistry instance = new InnerFeatureModelBuilderRegistry();

    /**
     * @return The singleton instance of the registry.
     */
    public static InnerFeatureModelBuilderRegistry getInstance() {
        return INSTANCE.instance;
    }

    /**
     * The implementation class of the FeatureModelBuilder registry.
     */
    public class InnerFeatureModelBuilderRegistry extends IdBasedRegistryBase<FeatureModelBuilder<Object>, String> {

        /**
         * Returns the id of the first feature model builder with the given label.
         * 
         * @param label
         *            The label of the feature model builder.
         * @return The id of the matching feature model builder or null if no such builder is
         *         registered.
         */
        public String getIdByLabel(String label) {
            for (FeatureModelBuilder<?> fmBuilder : getElements()) {
                if (fmBuilder.getLabel().equals(label)) {
                    return fmBuilder.getId();
                }
            }
            return null;
        }

    }

}

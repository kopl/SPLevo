/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Daniel Kojic - initial API and implementation and/or initial documentation
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.vpm.analyzer.semantic.extensionpoint;

import org.splevo.commons.registry.RegistryBase;

public enum SemanticContentProviderRegistry {
    INSTANCE;
    
    private final InnerSemanticContentProviderRegistry innerRegistry = new InnerSemanticContentProviderRegistry();
    
    public static InnerSemanticContentProviderRegistry getInstance() {
        return INSTANCE.innerRegistry;
    }
    
    /**
     * A registry for available content providers.
     */
    public class InnerSemanticContentProviderRegistry extends RegistryBase<SemanticContentProvider> {

    }
    
}

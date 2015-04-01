/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.commons.wizard;

import org.splevo.ui.commons.wizard.ChangeSingleAttributeWizardPage.ValueConverter;

/**
 * Dummy converter that does not convert values but just returns the inputs because no conversion is
 * required. This class is necessary because there are several generic interfaces that require
 * converts between types.
 */
public class StringValueConverter implements ValueConverter<String> {

    @Override
    public String convertFromString(String str) {
        return str;
    }

    @Override
    public String convertFromType(String t) {
        return t;
    }

    @Override
    public boolean isValid(String t) {
        return true;
    }

}

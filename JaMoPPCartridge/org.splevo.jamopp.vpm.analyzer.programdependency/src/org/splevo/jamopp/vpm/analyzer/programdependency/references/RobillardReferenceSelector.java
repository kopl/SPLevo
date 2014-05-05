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
package org.splevo.jamopp.vpm.analyzer.programdependency.references;

import java.util.List;

import org.apache.log4j.Logger;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.util.JaMoPPElementUtil;

/**
 * A reference selector implementing the references defined by:<br>
 * Robillard, M. R., & Murphy, G. C. (2002).<br>
 * "Concern graphs: finding and describing concerns using structural program dependencies."<br>
 * In Proceedings of the 24th International Conference on Software Engineering. ICSE 2002 (pp.
 * 406–416).<br>
 */
public class RobillardReferenceSelector implements ReferenceSelector {

    private static Logger logger = Logger.getLogger(RobillardReferenceSelector.class);

    @Override
    public List<Commentable> getReferencedElements(Commentable commentable) {
        return new DefaultReferenceSelector().getReferencedElements(commentable);
    }

    /**
     * Ignore all references not represented in the logic defined by robillard et al.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean ignoreReference(Commentable source1, Commentable source2, Commentable target) {

        if (source1 == source2) {
            return true;
        }
        if (JaMoPPElementUtil.isParentOf(source1, target)) {
            return false;
        }
        if (JaMoPPElementUtil.isParentOf(source2, target)) {
            return false;
        }

        logger.info("Robillard Reference Selector invoked");

        return false;
    }

}

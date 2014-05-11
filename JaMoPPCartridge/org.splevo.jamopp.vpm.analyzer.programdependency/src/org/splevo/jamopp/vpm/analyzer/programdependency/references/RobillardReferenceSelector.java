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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.util.JaMoPPElementUtil;
import org.splevo.vpm.analyzer.VPMAnalyzerUtil;

/**
 * A reference selector implementing the references defined by:<br>
 * Robillard, M. R., & Murphy, G. C. (2002).<br>
 * "Concern graphs: finding and describing concerns using structural program dependencies."<br>
 * In Proceedings of the 24th International Conference on Software Engineering. ICSE 2002 (pp.
 * 406–416).<br>
 *
 * It considers the following reference types, while interpreting the "body of a method", also as
 * single statements implementing a variant of a variation point.
 * <table>
 *
 * <tr>
 * <td>(calls,m1,m2)</td>
 * <td>The body of method m1 contains a call that can bind (statically or dynamically) to m2.</td>
 * </tr>
 * <tr>
 * <td>(reads,m,f)</td>
 * <td>The body of method m contains an instruction that reads (uses) a value from field f.</td>
 * </tr>
 * <tr>
 * <td>(writes,m,f)</td>
 * <td>The body of method contains an instruction that writes (defines) a value to field f.</td>
 * </tr>
 * <tr>
 * <td>(checks,m,c)</td>
 * <td>The body of method m checks the class of an object, or casts an object, to c.</td>
 * </tr>
 * <tr>
 * <td>(creates,m,c)</td>
 * <td>The body of method m creates an object of class c.</td>
 * </tr>
 * <tr>
 * <td>(declares,c,{m|f})</td>
 * <td>Class c declares method m or declares field f.</td>
 * </tr>
 * <tr>
 * <td>(superclass,c1,c2)</td>
 * <td>Class c1 is the superclass of c2.</td>
 * </tr>
 * </table>
 */
public class RobillardReferenceSelector implements ReferenceSelector {

    private static Logger logger = Logger.getLogger(RobillardReferenceSelector.class);

    private RobillardReferenceSelectorSwitch selectorSwitch = new RobillardReferenceSelectorSwitch();

    @Override
    public List<Commentable> getReferencedElements(Commentable commentable) {
        if (VPMAnalyzerUtil.isNullOrProxy(commentable)) {
            return new ArrayList<Commentable>();
        }

        List<Commentable> referencedElements = selectorSwitch.doSwitch(commentable);

        for (Commentable element : referencedElements) {
            if (VPMAnalyzerUtil.isNullOrProxy(element)) {
                referencedElements.remove(element);
            }
        }

        return referencedElements;
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
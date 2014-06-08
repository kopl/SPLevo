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
package org.splevo.diffing;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DiffingModelUtilTest {

    private TemporaryFolder folder = null;

    /**
     * Prepare the test. Initializes a log4j logging environment.
     */
    @BeforeClass
    public static void setUpBefore() {
        // set up a basic logging configuration for the test environment
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Prepare the test.
     *
     * @throws IOException
     *             Failed to create required resources.
     */
    @Before
    public void setUp() throws IOException {
        folder = new TemporaryFolder();
        folder.create();
    }

    /** Clean up afterwards. */
    @After
    public void tearDown() {
        //folder.delete();
    }

    @Test
    public void testLoadModel() throws IOException {

        String modelPath = "testmodel/singlediffmodel.diff";
        File modelFile = new File(modelPath).getAbsoluteFile();
        ResourceSetImpl rs = new ResourceSetImpl();
        DiffingModelUtil.loadModel(modelFile, rs);

        Resource resource = rs.getResources().get(0);
        EObject rootObject = resource.getContents().get(0);
        assertThat(rootObject, is(instanceOf(Comparison.class)));

    }

    @Test
    public void testSave() throws IOException {


        File modelFilePath = new File(folder.getRoot().getAbsolutePath() + File.separator + "testmodelsave.diff");

        Comparison model = CompareFactory.eINSTANCE.createComparison();
        Diff diff = CompareFactory.eINSTANCE.createDiff();
        model.getDifferences().add(diff);

        DiffingModelUtil.save(model, modelFilePath);

        assertThat(modelFilePath.exists(), is(true));
    }

}

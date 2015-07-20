package org.splevo.jamopp.refactoring.tests;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.splevo.jamopp.refactoring.JaMoPPResourceProcessor;
import org.splevo.jamopp.vpm.software.CommentableSoftwareElement;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Test for the JaMoPPResourceProcessor that do not use mocking.
 */
public class JaMoPPResourceProcessorTest {

    private static final JaMoPPRefactoringsTestUtils TEST_UTILS = new JaMoPPRefactoringsTestUtils();
    private JaMoPPResourceProcessor subject;

    /**
     * Initializes logging functionality.
     */
    @BeforeClass
    public static void init() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("%m%n")));
    }

    /**
     * Sets up the test subject.
     */
    @Before
    public void setUp() {
        subject = new JaMoPPResourceProcessor();
    }

    /**
     * Tests the correct processing of resources before the refactoring. Basically, the resources
     * have to be reloaded in a way such that comments are available for the refactoring.
     * 
     * @throws Exception
     *             In case of failed VPM loading.
     */
    @Test
    public void testProcessResourcesBeforeRefactoring() throws Exception {
        // load VPM
        VariationPointModel vpm = TEST_UTILS.getVariantsWithComments(false);
        EcoreUtil.resolveAll(vpm);

        // ensure that no layout information exists
        for (SoftwareElement se : vpm.getSoftwareElements()) {
            Commentable c = (Commentable) se.getWrappedElement();
            assertNotNull(c.getLayoutInformations());
            assertEquals(0, c.getLayoutInformations().size());
        }

        // determine resources
        Set<Resource> resources = Sets.newHashSet(Iterables.transform(vpm.getSoftwareElements(),
                new Function<SoftwareElement, Resource>() {
                    @Override
                    public Resource apply(SoftwareElement input) {
                        return input.getWrappedElement().eResource();
                    }
                }));

        // execute processing
        for (Resource r : resources) {
            subject.processBeforeRefactoring(r);
        }
        EcoreUtil.resolveAll(vpm);

        // ensure that layout information exists
        for (SoftwareElement se : vpm.getSoftwareElements()) {
            Commentable c = (Commentable) se.getWrappedElement();
            assertNotNull(c.getLayoutInformations());
            assertNotEquals(0, c.getLayoutInformations().size());
        }

    }

    /**
     * Tests the correct processing of the VPM before the refactoring. All direct references to
     * software elements have to be replaced by indirect references via comments. Nevertheless, the
     * resolving of the references JaMoPP elements has to reveal the same objects.
     * 
     * @throws Exception
     *             In case of failed VPM loading.
     */
    @Test
    public void testProcessVPMAfterRefactoring() throws Exception {
        // load VPM
        VariationPointModel vpm = TEST_UTILS.getVariantsWithComments(true);

        // build expected
        Map<Object, Iterable<EObject>> expected = Maps.newHashMap();
        for (VariationPointGroup vpg : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpg.getVariationPoints()) {
                expected.put(vp, Lists.newArrayList((EObject) vp.getLocation().getWrappedElement()));
                for (Variant v : vp.getVariants()) {
                    expected.put(v,
                            Iterables.transform(v.getImplementingElements(), new Function<SoftwareElement, EObject>() {
                                @Override
                                public EObject apply(SoftwareElement input) {
                                    return input.getWrappedElement();
                                }
                            }));
                }
            }
        }

        // process VPM
        subject.processVPMAfterRefactorings(vpm);

        // assert results
        for (SoftwareElement se : vpm.getSoftwareElements()) {
            if (se.getWrappedElement() instanceof CompilationUnit) {
                assertThat(se, instanceOf(JaMoPPSoftwareElement.class));
            } else {
                assertThat(se, instanceOf(CommentableSoftwareElement.class));
            }
        }

        for (VariationPointGroup vpg : vpm.getVariationPointGroups()) {
            for (VariationPoint vp : vpg.getVariationPoints()) {
                Iterable<EObject> actualVPLocation = Lists.newArrayList((EObject) vp.getLocation().getWrappedElement());
                EObject[] expectedVPLocation = Iterables.toArray(expected.get(vp), EObject.class);
                assertThat(actualVPLocation, hasItems(expectedVPLocation));
                for (Variant v : vp.getVariants()) {
                    Iterable<EObject> actual = Iterables.transform(v.getImplementingElements(),
                            new Function<SoftwareElement, EObject>() {
                                @Override
                                public EObject apply(SoftwareElement input) {
                                    return input.getWrappedElement();
                                }
                            });
                    assertThat(Iterables.size(actual), equalTo(Iterables.size(expected.get(v))));
                    assertThat(actual, hasItems(Iterables.toArray(expected.get(v), EObject.class)));
                }
            }
        }

    }

}

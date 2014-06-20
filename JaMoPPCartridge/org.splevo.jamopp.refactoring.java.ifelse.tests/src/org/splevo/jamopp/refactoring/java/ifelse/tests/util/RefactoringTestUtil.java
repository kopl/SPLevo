package org.splevo.jamopp.refactoring.java.ifelse.tests.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.commons.Commentable;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.jamopp.vpm.software.JaMoPPSoftwareElement;
import org.splevo.jamopp.vpm.software.softwareFactory;
import org.splevo.vpm.variability.BindingTime;
import org.splevo.vpm.variability.Extensible;
import org.splevo.vpm.variability.VariabilityType;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;
import org.splevo.vpm.variability.variabilityFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Utility class for JaMoPP refactoring tests.
 */
public final class RefactoringTestUtil {

    private static final String PATH_TESTCODE_FOLDER = "testcode/";

    private RefactoringTestUtil() {
    }

    /**
     * Generated a variation point according to the Statement_Default test case. The location is a
     * method. In the integration variant, there is one additional statement.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getStatemententDefaultCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Statement_Default");

        assert (vpm.getVariationPointGroups().size() == 1);
        assert (vpm.getVariationPointGroups().get(0).getVariationPoints().size() == 1);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generated a variation point according to the Constructor_ExistingOneParam test case. The
     * location is a class. There are two variants, each having a constructor. The leading variant
     * has an int parameter, the integration a short parameter.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getConstructorExistingOneParamCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Constructor_ExistingOneParam");
        assert (vpm.getVariationPointGroups().size() == 1);
        assert (vpm.getVariationPointGroups().get(0).getVariationPoints().size() == 1);
        assert (vpm.getVariationPointGroups().get(0).getVariationPoints().get(0).getVariants().size() == 2);

        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);

        return variationPoint;
    }

    /**
     * Generated a variation point according to the Constructor_AddTwoParam test case. The
     * location is a class. There are two variants, each having a constructor. The leading variant
     * has an int parameter, the integration an int and a short parameter.
     * 
     * @param variabilityType
     *            The {@link VariabilityType} the variation point will have.
     * @return The generated {@link VariationPoint}.
     * @throws Exception
     *             In case of an unexpected error.
     */
    public static VariationPoint getConstructorAddTwoParamCase(VariabilityType variabilityType) throws Exception {
        VariationPointModel vpm = initializeVariationPointModel("Constructor_AddTwoParam");
        assert (vpm.getVariationPointGroups().size() == 1);
        assert (vpm.getVariationPointGroups().get(0).getVariationPoints().size() == 1);
        assert (vpm.getVariationPointGroups().get(0).getVariationPoints().get(0).getVariants().size() == 2);
        
        VariationPoint variationPoint = vpm.getVariationPointGroups().get(0).getVariationPoints().get(0);
        setUpVariationPoint(variationPoint, variabilityType);
        
        return variationPoint;
    }

    /**
     * Generated a variation point mock with a given variability type, extensibility, binding time,
     * location and two variants, each with one element.
     * 
     * @param varType
     *            The {@link VariabilityType}.
     * @param extensible
     *            The {@link Extensible}.
     * @param bt
     *            The {@link BindingTime}.
     * @param location
     *            The vp location.
     * @param implEl1
     *            The implementing element of the first variant.
     * @param implEl2
     *            The implementing element of the second variant.
     * @return The {@link VariationPoint}.
     */
    public static VariationPoint generateSimpleVPMock(VariabilityType varType, Extensible extensible, BindingTime bt,
            Commentable location, Commentable implEl1, Commentable implEl2) {
        VariationPoint vpMock = mock(VariationPoint.class);

        JaMoPPSoftwareElement locationElement = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        locationElement.setJamoppElement(location);

        JaMoPPSoftwareElement implElement1 = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        implElement1.setJamoppElement(implEl1);
        JaMoPPSoftwareElement implElement2 = softwareFactory.eINSTANCE.createJaMoPPSoftwareElement();
        implElement2.setJamoppElement(implEl2);

        Variant variant1 = variabilityFactory.eINSTANCE.createVariant();
        variant1.getImplementingElements().add(implElement1);
        Variant variant2 = variabilityFactory.eINSTANCE.createVariant();
        variant2.getImplementingElements().add(implElement2);
        EList<Variant> variants = new BasicEList<Variant>();
        variants.add(variant1);
        variants.add(variant2);

        when(vpMock.getVariabilityType()).thenReturn(varType);
        when(vpMock.getExtensibility()).thenReturn(extensible);
        when(vpMock.getBindingTime()).thenReturn(bt);
        when(vpMock.getLocation()).thenReturn(locationElement);
        when(vpMock.getVariants()).thenReturn(variants);
        return vpMock;
    }

    private static VariationPointModel initializeVariationPointModel(String folderName) throws Exception {
        String leadingPath = PATH_TESTCODE_FOLDER + folderName + "/leading/";
        String integrationPath = PATH_TESTCODE_FOLDER + folderName + "/integration/";

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        List<String> urisA = Lists.newArrayList(new File(leadingPath).getAbsolutePath());
        List<String> urisB = Lists.newArrayList(new File(integrationPath).getAbsolutePath());
        NullProgressMonitor monitor = new NullProgressMonitor();

        ResourceSet setA = extractor.extractSoftwareModel(urisA, monitor, null);
        ResourceSet setB = extractor.extractSoftwareModel(urisB, monitor, null);

        String ignorePackages = buildIgnorePackages();

        Map<String, String> diffOptions = Maps.newLinkedHashMap();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
        VariationPointModel vpm = builder.buildVPM(comparison, "leading", "integration");
        return vpm;
    }

    private static String buildIgnorePackages() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.*");
        sb.append(System.getProperty("line.separator"));
        String ignorePackages = sb.toString();
        return ignorePackages;
    }

    // private static void performRefinement(VariationPointModel vpm, RefinementType refinementType,
    // VariationPoint... variationPoints) {
    // Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
    // refinement.setType(refinementType);
    //
    // for (VariationPoint variationPoint : variationPoints) {
    // refinement.getVariationPoints().add(variationPoint);
    // }
    //
    // VPMRefinementService refinementService = new VPMRefinementService();
    // refinementService.applyRefinements(Lists.newArrayList(refinement), vpm);
    // }

    private static void setUpVariationPoint(VariationPoint variationPoint, VariabilityType variabilityType) {
        variationPoint.setBindingTime(BindingTime.COMPILE_TIME);
        variationPoint.setExtensibility(Extensible.NO);
        variationPoint.setVariabilityType(variabilityType);
    }
}

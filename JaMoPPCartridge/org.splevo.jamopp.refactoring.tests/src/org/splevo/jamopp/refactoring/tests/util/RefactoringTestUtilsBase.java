package org.splevo.jamopp.refactoring.tests.util;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.splevo.jamopp.diffing.JaMoPPDiffer;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;
import org.splevo.jamopp.vpm.builder.JaMoPPVPMBuilder;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.refinement.RefinementFactory;
import org.splevo.vpm.refinement.RefinementType;
import org.splevo.vpm.refinement.VPMRefinementService;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

/**
 * Base class for refactoring utilities such as loading a VPM or refining it.
 */
public abstract class RefactoringTestUtilsBase {

    /**
     * Determines the project relative path of the test code base folder. This folder contains
     * further folders that consist of an leading and integration folder and the corresponding
     * source code.
     * 
     * @return The project relative path of the test code base folder.
     */
    protected abstract String getBasePath();

    private String getPathFromFolderName(String folderName, boolean leading) {
        return getBasePath() + folderName + (leading ? "/leading/" : "/integration/");
    }

    /**
     * Initializes a variation point model for the given folder name. The folder name is relative to
     * the base path determined from the getBasePath method. The sources and VPM will be stored in a
     * temporary folder and deleted after the tests.
     * 
     * @param folderName The folder containing the test code relative to the base path.
     * @param layoutInformation Flag for layout information loading.
     * @return The VPM.
     * @throws Exception In case of failed IO handling.
     */
    protected VariationPointModel initializeVariationPointModel(String folderName, boolean layoutInformation)
            throws Exception {
        String leadingPath = getPathFromFolderName(folderName, true);
        String integrationPath = getPathFromFolderName(folderName, false);

        final File tmpDir = Files.createTempDir();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                FileUtils.deleteQuietly(tmpDir);
            }
        }));

        File tmpLeading = new File(tmpDir, "leading");
        File tmpIntegration = new File(tmpDir, "integration");
        FileUtils.copyDirectory(new File(leadingPath), tmpLeading);
        FileUtils.copyDirectory(new File(integrationPath), tmpIntegration);

        JaMoPPSoftwareModelExtractor extractor = new JaMoPPSoftwareModelExtractor();
        List<String> urisA = Lists.newArrayList(tmpLeading.getAbsolutePath());
        List<String> urisB = Lists.newArrayList(tmpIntegration.getAbsolutePath());
        NullProgressMonitor monitor = new NullProgressMonitor();

        ResourceSet setA = extractor.extractSoftwareModel(urisA, monitor, null, layoutInformation);
        ResourceSet setB = extractor.extractSoftwareModel(urisB, monitor, null, layoutInformation);

        String ignorePackages = buildIgnorePackages();

        Map<String, String> diffOptions = Maps.newLinkedHashMap();
        diffOptions.put(JaMoPPDiffer.OPTION_JAVA_IGNORE_PACKAGES, ignorePackages);

        JaMoPPDiffer differ = new JaMoPPDiffer();
        Comparison comparison = differ.doDiff(setA, setB, diffOptions);

        JaMoPPVPMBuilder builder = new JaMoPPVPMBuilder();
        VariationPointModel vpm = builder.buildVPM(comparison, "leading", "integration");

        File tmpVpm = new File(tmpDir, "tmp.vpm");
        VPMUtil.save(vpm, URI.createFileURI(tmpVpm.getAbsolutePath()));

        ResourceSet rs = new ResourceSetImpl();
        extractor.prepareResourceSet(rs,
                Lists.newArrayList(tmpLeading.getAbsolutePath(), tmpIntegration.getAbsolutePath()), layoutInformation);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("vpm", new XMIResourceFactoryImpl());
        Resource vpmResource = rs.createResource(URI.createFileURI(tmpVpm.getAbsolutePath()));
        vpmResource.load(Collections.EMPTY_MAP);
        return (VariationPointModel) vpmResource.getContents().get(0);
    }

    private static String buildIgnorePackages() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.*");
        sb.append(System.getProperty("line.separator"));
        String ignorePackages = sb.toString();
        return ignorePackages;
    }

    /**
     * Performs the given refinement for the given variation points.
     * @param vpm The VPM to which the variation points belong to.
     * @param refinementType The type of the refinement to be performed.
     * @param variationPoints The variation points that are subject to the refinement.
     */
    protected static void performRefinement(VariationPointModel vpm, RefinementType refinementType,
            VariationPoint... variationPoints) {
        Refinement refinement = RefinementFactory.eINSTANCE.createRefinement();
        refinement.setType(refinementType);

        for (VariationPoint variationPoint : variationPoints) {
            refinement.getVariationPoints().add(variationPoint);
        }

        VPMRefinementService refinementService = new VPMRefinementService();
        refinementService.applyRefinements(Lists.newArrayList(refinement), vpm);
    }

}

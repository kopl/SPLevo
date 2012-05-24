package org.splevo.diffing.emfcompare.tests;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.engine.IDiffEngine;
import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSnapshot;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.GenericMatchScopeProvider;
import org.eclipse.emf.compare.match.engine.IMatchEngine;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.splevo.diffing.emfcompare.diff.KdmDiffEngine;
import org.splevo.diffing.emfcompare.merge.KdmMatchEngine;

public class KDMDiffTest {
	private static final File NATIVE_MODEL_FILE = new File(
			"../CaseStudyCalculatorProject/models/implementation/native/_java2kdm.xmi");
	private static final File JSCIENCE_MODEL_FILE = new File(
			"../CaseStudyCalculatorProject/models/implementation/jscience/_java2kdm.xmi");
	private EObject jscienceModel;
	private EObject nativeModel;
	private final IMatchEngine matchEngine = new KdmMatchEngine();
	private final IDiffEngine diffEngine = new KdmDiffEngine();

	@Before
	public void setUp() throws Exception {
		jscienceModel = loadModel(JSCIENCE_MODEL_FILE);
		nativeModel = loadModel(NATIVE_MODEL_FILE);

		matchEngine.reset();
		diffEngine.reset();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDiff() throws Exception {
		GenericMatchScopeProvider matchScopeProvider = new GenericMatchScopeProvider(
				nativeModel, jscienceModel);
		Map<String, Object> matchOptions = new EMFCompareMap<String, Object>();
		matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER,
				matchScopeProvider);

		MatchModel matchModel = matchEngine.contentMatch(nativeModel,
				jscienceModel, matchOptions);
		DiffModel diffModel = diffEngine.doDiff(matchModel);

		saveDiff(new File("testDiff.emfdiff"), matchModel, diffModel);
	}

	private EObject loadModel(File modelFile) throws IOException {
		if (!modelFile.canRead()) {
			throw new IllegalArgumentException("cannot read model file "
					+ modelFile.getAbsolutePath());
		}
		ResourceSet resSet = new ResourceSetImpl();
		URI fileUri = URI.createFileURI(modelFile.getPath());
		Resource resource = resSet.createResource(fileUri);
		resource.load(Collections.emptyMap());

		if (resource.getContents().size() > 0) {
			EObject model = resource.getContents().get(0);

			return model;
		}
		return null;
	}

	private void saveDiff(File targetFile, MatchModel matchModel,
			DiffModel diffModel) throws IOException {
		final ComparisonResourceSnapshot snapshot = DiffFactory.eINSTANCE
				.createComparisonResourceSnapshot();
		snapshot.setDate(Calendar.getInstance().getTime());
		snapshot.setMatch(matchModel);
		snapshot.setDiff(diffModel);

		Resource newModelRes = (new ResourceSetImpl()).createResource(URI
				.createFileURI(targetFile.getPath()));
		newModelRes.getContents().add(snapshot);
		final Map<String, String> options = new EMFCompareMap<String, String>();
		options.put(XMLResource.OPTION_ENCODING,
				System.getProperty("file.encoding"));
		newModelRes.save(options);
	}
}

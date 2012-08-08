package org.splevo.diffing.emfcompare.diff.transform;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSetSnapshot;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.DiffResourceSet;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;

public class DiffTreeTransformer {
	DiffNodeTransformer diffSwitch = new DiffNodeTransformer();

	public void convertModel(ComparisonResourceSetSnapshot resourceSetDiff) throws IOException {
		DiffResourceSet transformedResSet = transform(resourceSetDiff.getDiffResourceSet());
		ComparisonResourceSetSnapshot snapshot = DiffFactory.eINSTANCE.createComparisonResourceSetSnapshot();
		snapshot.setDate(Calendar.getInstance().getTime());
		snapshot.setMatchResourceSet(resourceSetDiff.getMatchResourceSet());
		snapshot.setDiffResourceSet(transformedResSet);

		File targetFile = new File("transformedDiff.emfdiff");
		ResourceSetImpl resSet = new ResourceSetImpl();
		//resSet.getPackageRegistry().put(KDM2JavaDiffPackage.eINSTANCE.getNsURI(), KDM2JavaDiffPackage.eINSTANCE);
		Resource newModelRes = resSet.createResource(URI
				.createFileURI(targetFile.getPath()));
		newModelRes.getContents().add(snapshot);
		final Map<String, Object> saveOptions = new EMFCompareMap<String, Object>();
		saveOptions.put(XMLResource.OPTION_ENCODING,
				System.getProperty("file.encoding"));
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
		newModelRes.save(saveOptions);
	}

	DiffResourceSet transform(DiffResourceSet diffSet) {
		//DiffResourceSet transformedSet = EcoreUtil.copy(diffSet);
		//transformedSet.getDiffModels().clear();
		DiffResourceSet transformedSet = DiffFactory.eINSTANCE.createDiffResourceSet();
		
		for (DiffModel dModel : diffSet.getDiffModels()) {
			//DiffModel transformedModel = EcoreUtil.copy(dModel);
			//transformedModel.getOwnedElements().clear();
			DiffModel transformedModel = DiffFactory.eINSTANCE.createDiffModel();
			transformedModel.getOwnedElements().addAll(transformElements(dModel.getOwnedElements()));
			transformedSet.getDiffModels().add(transformedModel);
		}

		return transformedSet;
	}

	public EList<DiffElement> transformElements(EList<DiffElement> sourceElements) {
		 EList<DiffElement> transformedElems = new BasicEList<DiffElement>();
		for (DiffElement sourceElem : sourceElements) {
			DiffElement targetElem = diffSwitch.doSwitch(sourceElem);

			//if null is returned, we discard the element
			if (targetElem != null) {
				targetElem.getSubDiffElements().addAll(transformElements(sourceElem.getSubDiffElements()));
				transformedElems.add(targetElem);
			}
		}

		return transformedElems;
	}
}

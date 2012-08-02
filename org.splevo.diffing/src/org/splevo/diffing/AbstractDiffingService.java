package org.splevo.diffing;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * Abstract diffing service providing infrastructure methods.
 * 
 * @author Benjamin Klatt
 *
 */
public abstract class AbstractDiffingService implements DiffingService {


	/**
	 * Loads an Ecore model from the supplied file
	 * 
	 * @param modelFiles
	 *            List of models to load
	 * @return model resource set instance
	 * @throws IOException
	 *             possible load exception
	 */
	protected ResourceSet loadModelResourceSet(List<File> modelFiles) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		for (File modelFile : modelFiles) {
			if (!modelFile.canRead()) {
				throw new IllegalArgumentException("cannot read model file "
						+ modelFile.getAbsolutePath());
			}
			URI fileUri = URI.createFileURI(modelFile.getPath());
			Resource resource = resourceSet.createResource(fileUri);
			resource.load(Collections.emptyMap());
		}
	
		return resourceSet;
	}

}
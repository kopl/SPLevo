package de.devboost.featuremapper.splevo.builder.test;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import de.devboost.featuremapper.splevo.builder.FeatureMapperModelsBuilder;

/**
 * FeatureMapper models Builder Mock for tests 
 * @author cwende
 *
 */
public class FeatureMapperModelsBuilderMock extends FeatureMapperModelsBuilder {

	@Override
	protected URI getFixedURI(URI uri) {
		// in the test we have no eclipse workspace, so we do not fix the uri.	
		return uri;
	}
}

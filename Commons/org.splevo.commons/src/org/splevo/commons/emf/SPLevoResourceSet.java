package org.splevo.commons.emf;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Strings;

/**
 * ResourceSet for the SPLevo tooling. The resource set automatically converts URIs when
 * creating/loading resources. The exact behavior depends on the default settings in
 * FileResourceHandling.
 */
public class SPLevoResourceSet extends ResourceSetImpl {

    
    
    @Override
    public EObject getEObject(URI uri, boolean loadOnDemand) {
        return super.getEObject(convertURI(uri), loadOnDemand);
    }

    @Override
    public Resource getResource(URI uri, boolean loadOnDemand) {
        return super.getResource(convertURI(uri), loadOnDemand);
    }

    @Override
    public Resource createResource(URI uri, String contentType) {
        return super.createResource(convertURI(uri), contentType);
    }

    private URI convertURI(URI uri) {
        if (FileResourceHandling.usePlatformResource(this) && uri.isPlatform()) {
            return uri;
        }

        File f = FileResourceHandling.getPhysicalFilePath(uri, this);
        if (f == null) {
            return uri;
        }

        if (FileResourceHandling.usePlatformResource(this)) {
            IPath path = new Path(f.getAbsolutePath());
            IFile resourceFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
            return createURI(URI.createPlatformResourceURI(resourceFile.getFullPath().toString(), true), uri.fragment());
        } else if (uri.isFile()) {
            return createURI(URI.createFileURI(f.getAbsolutePath()), uri.fragment());
        }
        return uri;
    }
        
    private static URI createURI(URI uri, String fragment) {
        if (Strings.isNullOrEmpty(fragment)) {
            return uri;
        }
        return URI.createURI(uri.toString() + "#" + fragment, false, URI.FRAGMENT_FIRST_SEPARATOR);
    }

}

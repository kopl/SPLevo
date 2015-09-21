package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.eclipse.ui.dialogs.SearchPattern;

public class LeadingProjectFilteredSelectionDialog extends FilteredItemsSelectionDialog{
	private Iterable<IProject> leadingProjects;
	private Map<IFile, IProject> fileToProjectMapper = new HashMap<IFile, IProject>();
	private final static String JAVA_EXTENSION = "java";
		
	public LeadingProjectFilteredSelectionDialog(Shell shell, boolean multi, Iterable<IProject> leadingProjects) {
		super(shell, multi);
		this.leadingProjects = leadingProjects;
		initMap(Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getProjects()));
	}

	private void initMap(List<IProject> leadingProject) {
		for (IProject project : leadingProject) {
			List<IFile> files = getAllFilesIn(project);
			for (IFile file : files) {
				this.fileToProjectMapper.put(file, project);
			}
		}
	}

	@Override
	protected Control createExtendedContentArea(Composite parent) {
		return null;
	}

	@Override
	protected IDialogSettings getDialogSettings() {
		//Siehe opentypedialog....
		return new DialogSettings("Settings");
	}

	@Override
	protected IStatus validateItem(Object item) {
		return Status.OK_STATUS;
	}
	
	@Override
	protected ItemsFilter createFilter() {
		return new ItemsFilter(new SearchPattern()) {

			@Override
			public boolean matchItem(Object item) {
				IFile file = (IFile) item;
				
				for (IProject project : leadingProjects) {
					if (!(fileToProjectMapper.get(file).getName().equals(project.getName()))) {
						continue;
					}
					
					if (matches(file.getName())) {
						return true;
					}
				}
				
				return false;
			}

			@Override
			public boolean isConsistentItem(Object item) {
				return true;
			}
			
		};
	}

	@Override
	protected Comparator getItemsComparator() {
		return new Comparator() {
	         public int compare(Object arg0, Object arg1) {
	            return ((IFile) arg0).getName().compareTo(((IFile) arg1).getName());
	         }
	      };
	}

	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider,
			ItemsFilter itemsFilter, IProgressMonitor progressMonitor)
			throws CoreException {
		Collection<IFile> files = this.fileToProjectMapper.keySet();
		progressMonitor.beginTask("Searching workspace...", files.size());
		for (IFile file : files) {
			contentProvider.add(file, itemsFilter);
			progressMonitor.worked(1);
		}
	}

	private List<IFile> getAllFilesIn(IContainer container) {
		IResource[] members = null;
		List<IFile> files = new ArrayList<IFile>();
		
		try {
			members = container.members();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		for (IResource member : members) {
			if (member instanceof IContainer) {
				files.addAll(getAllFilesIn((IContainer) member));
			}
			
			if (member instanceof IFile) {
				if (member.getFileExtension().equals(JAVA_EXTENSION)) {
					files.add((IFile) member);
				}
			}
		}
		
		return files;
	}

	@Override
	public String getElementName(Object item) {
		return ((IFile) item).getName();
	}

}

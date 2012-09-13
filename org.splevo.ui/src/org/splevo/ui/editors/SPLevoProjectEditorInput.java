package org.splevo.ui.editors;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.part.FileEditorInput;

public class SPLevoProjectEditorInput extends FileEditorInput {

	public SPLevoProjectEditorInput(IFile file) {
		super(file);
	}
	
    @Override
    public String getName() {
        return "SPLevoProject: "+getFile().getName();
    }

    @Override
    public String getToolTipText() {
        return "Manages a SPLevo Project";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getFile().getFullPath().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        SPLevoProjectEditorInput other = (SPLevoProjectEditorInput) obj;
        if (!getFile().getFullPath().equals(other.getFile().getFullPath())){
            return false;
        }
        return true;
    }
	

}

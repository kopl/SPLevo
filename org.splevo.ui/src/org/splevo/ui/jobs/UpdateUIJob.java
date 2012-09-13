package org.splevo.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.splevo.ui.editors.SPLevoProjectEditor;

import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

public class UpdateUIJob implements IJob {
	
	/** The internal reference to the splevo project editor to work with. */
	private SPLevoProjectEditor splevoProjectEditor = null;
	
	public UpdateUIJob(SPLevoProjectEditor splevoProjectEditor) {
		this.splevoProjectEditor = splevoProjectEditor;
	}
	
	@Override
	public void rollback(IProgressMonitor monitor)
			throws RollbackFailedException {
		// no rollback necessary
	}
	
	@Override
	public String getName() {
		return "Update UI Job";
	}
	
	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException,
			UserCanceledException {
		monitor.beginTask("UpdateUI", 100);
		splevoProjectEditor.updateUi("Models extracted");
		monitor.done();
	}

}

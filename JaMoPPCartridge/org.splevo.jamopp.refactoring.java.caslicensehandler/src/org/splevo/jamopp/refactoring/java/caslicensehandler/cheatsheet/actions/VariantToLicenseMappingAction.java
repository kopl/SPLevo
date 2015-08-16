package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.splevo.ui.vpexplorer.explorer.ExplorerMediator;
import org.splevo.vpm.variability.VariationPoint;

public class VariantToLicenseMappingAction extends Action implements
		ICheatSheetAction {

	@Override
	public void run(String[] params, ICheatSheetManager manager) {
		//For testing there will be a random variationpoint of the variation point model chosen
		VariationPoint vp = ExplorerMediator.getInstance().getCurrentVPM().getVariationPointGroups().get(0).getVariationPoints().get(1);
		MappingDialog dialog = new MappingDialog(JavaPlugin.getActiveWorkbenchShell(), vp);
		dialog.open();
	}

}

package org.splevo.ui.wizards.vpmanalysis;

import java.io.File;

import org.eclipse.jdt.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.splevo.ui.refinementbrowser.RefinementTreeContentProvider;
import org.splevo.ui.refinementbrowser.RefinementTreeLabelProvider;
import org.splevo.ui.refinementbrowser.VPMRefinementBrowserInput;

/**
 * Wizard page to modify the results of the vpm analysis.
 */
public class VPMRefinementPage extends WizardPage {

    private VPMRefinementBrowserInput refinement;
    private CheckboxTreeViewer treeViewer;
    
	/**
	 * Create the wizard page to let the user modify the found VPM.
	 */
	public VPMRefinementPage() {
		super("VPMRefinementWizardPage");
		setTitle("VPM Refinement");
		setDescription("Inspect and modify the VPM.");
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Insert actual content.
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new FillLayout());
		SashForm outerSash = new SashForm(container, SWT.VERTICAL);
		treeViewer = new CheckboxTreeViewer(outerSash);
		treeViewer.setContentProvider(new RefinementTreeContentProvider());
		treeViewer.setLabelProvider(new RefinementTreeLabelProvider());
		treeViewer.setInput(refinement);
		SashForm editorSash = new SashForm(outerSash, SWT.HORIZONTAL);
		IVerticalRuler leadingRuler = new VerticalRuler(20);
		IVerticalRuler followingRuler = new VerticalRuler(20);
		SourceViewer leadingSourceViewer = new SourceViewer(editorSash, leadingRuler, SWT.V_SCROLL);
		SourceViewer followingSourceViewer = new SourceViewer(editorSash, followingRuler, SWT.V_SCROLL);
//		leadingSourceViewer.setEditable(false);
//		followingSourceViewer.setEditable(false);
		IColorManager colorManager = null;
		IPreferenceStore preferenceStore = new PreferenceStore();
//		SourceViewerConfiguration configuration = new JavaSourceViewerConfiguration(colorManager, preferenceStore, null, null);
//		leadingSourceViewer.configure(configuration);
		IDocument document = new Document();
		IDocumentProvider dp = new FileDocumentProvider();
		
		File file = new File("C:/Users/Morpheus/workspace/runtime-New_configuration/Calculator-JScience/src/org/splevo/examples/calculator/CalculatorGCD.java");
		System.out.println("exists: " + file.exists());
		System.out.println("readable: " + file.canRead());
		System.out.println("is file: " + file.isFile());
		System.out.println("is directory: " + file.isDirectory());
		try {
			dp.connect(file);
			document.set("Dolorem ipsum sit amet...");
			document = dp.getDocument(file);
			leadingSourceViewer.setDocument(document);
			System.out.println("Done.");
//			dp.disconnect(file);
		} catch (Exception e) {
			System.out.println("!!!!!!!!!!!!!!!!!! NO !!!!!!!!!!!!!!!! NO !!!!!!!!!!!!!!!!! NO !!!!!!!!!!!!!!!!!! NO !!!!!!!!!!");
			e.printStackTrace();
		}
	}

	/**
	 * Setter for the refinements.
	 * 
	 * @param refinement refinements to be suggested in the VPMRefinementPage
	 */
    public void setRefinementBrowserInput(VPMRefinementBrowserInput refinement) {
        this.refinement = refinement;
        treeViewer.refresh();
    }

}

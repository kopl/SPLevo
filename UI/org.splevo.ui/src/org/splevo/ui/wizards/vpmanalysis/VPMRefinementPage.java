/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christian Busch - initial API and implementation and/or initial documentation
 *    Benjamin Klatt - Improvement for result hand over and presentation
 *******************************************************************************/
package org.splevo.ui.wizards.vpmanalysis;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.ui.text.IColorManager;
import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.splevo.vpm.refinement.Refinement;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;

import com.google.common.io.CharStreams;

/**
 * Wizard page to modify the results of the vpm analysis.
 */
@SuppressWarnings("restriction")
public class VPMRefinementPage extends WizardPage {

    private static Logger logger = Logger.getLogger(VPMRefinementPage.class);

    private Set<Refinement> selectedRefinements;
    private CheckboxTreeViewer treeViewer;
    private SourceViewer leadingSourceViewer;
    private SourceViewer integrationSourceViewer;
    private CompositeRuler leadingRuler;
    private CompositeRuler integrationRuler;

    /**
     * Create the wizard page to let the user modify the found VPM.
     */
    public VPMRefinementPage() {
        super("VPMRefinementWizardPage");
        this.setTitle("VPM Refinement");
        this.setDescription("Inspect and modify the VPM.");
    }

    @Override
    public void createControl(final Composite parent) {

        final Composite container = new Composite(parent, SWT.NULL);
        this.setControl(container);
        container.setLayout(new FillLayout());
        final SashForm outerSash = new SashForm(container, SWT.VERTICAL);
        this.treeViewer = new CheckboxTreeViewer(outerSash);
        this.treeViewer.setContentProvider(new CompleteRefinementTreeContentProvider());
        this.treeViewer.setLabelProvider(new CompleteRefinementTreeLabelProvider());

        final SashForm sourceViewersSash = new SashForm(outerSash, SWT.HORIZONTAL);

        leadingRuler = new CompositeRuler();
        integrationRuler = new CompositeRuler();
        leadingSourceViewer = new SourceViewer(sourceViewersSash, leadingRuler, SWT.V_SCROLL);
        integrationSourceViewer = new SourceViewer(sourceViewersSash, integrationRuler, SWT.V_SCROLL);

        this.treeViewer.addSelectionChangedListener(new VPMSelectionChangedListener());
        this.treeViewer.setAutoExpandLevel(1);
        this.treeViewer.setCheckStateProvider(new CheckStateProvider());
        this.treeViewer.addCheckStateListener(new CheckStateListener());
    }

    /**
     * Displays the Document in the SourceViewer. To achieve this, the SourceViewer will also be set
     * up according to the Document.
     *
     * @param sourceViewer
     *            The Source viewer to display the Document in.
     * @param document
     *            The Document to display.
     */
    private void displayDocument(final SourceViewer sourceViewer, Document document) {
        // TODO: This needs to be modified to also handle other file types correctly.
        // Left warnings intentionally so it's obvious that this is a hack.
        // FIXME: Don't use any members of JavaPlugin even though this could result in code
        // duplication.
        final IColorManager colorManager = JavaPlugin.getDefault().getJavaTextTools().getColorManager();
        final IPreferenceStore preferenceStore = JavaPlugin.getDefault().getCombinedPreferenceStore();

        sourceViewer.unconfigure();
        // TODO: This needs to be replaced for non-java files.
        sourceViewer.configure(new JavaSourceViewerConfiguration(colorManager, preferenceStore, null, null));
        sourceViewer.setEditable(false);
        sourceViewer.setDocument(document);
    }

    /**
     * Given a path will return a Document with the contents of the file at this path and the
     * DocumentPartitioner set up accordingly.
     *
     * @param path
     *            Path of the File to generate a Document from.
     * @return a Document containing the content of the file at the path.
     */
    private Document createDocumentFromPath(IPath path) {
    	
    	if (path == null) {
    		logger.error("Could not create document as path is null");
    		return new Document();
    	}
    	
        Document document;
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
        
        if (file == null) {
        	logger.error("Could not open file at path " + path.toString());
        	return new Document();
        }
        
        String content = null;
        InputStreamReader inputStreamReader = null;
        
        try {
            inputStreamReader = new InputStreamReader(file.getContents(), file.getCharset());
            content = CharStreams.toString(inputStreamReader);
        } catch (UnsupportedEncodingException e) {
            logger.error("Cannot create InputStream reader because of unsupported encoding.", e);
        } catch (IOException e1) {
        	logger.error("Cannot read file content.", e1);
        } catch (CoreException e2) {
            logger.error("Cannot read from file.", e2);
        } finally {
        	if (inputStreamReader != null) {
        		try {
					inputStreamReader.close();
				} catch (IOException e) {
					logger.error("Could not close InputStreamReader", e);
				}
        	}
        }

        document = new Document(content);
        // TODO: This needs to be modified to also handle other file types correctly.
        // FIXME: Don't use any members of JavaPlugin even though this could result in code
        // duplication.
        JavaPlugin.getDefault().getJavaTextTools().setupJavaDocumentPartitioner(document);
        return document;
    }

    /**
     * Setter for the refinements.
     *
     * @param refinements
     *            refinements to be suggested in the VPMRefinementPage
     */
    public void setRefinements(final List<Refinement> refinements) {
        // TODO: Copy refinements to output structure
        selectedRefinements = new HashSet<Refinement>(refinements);
        this.treeViewer.setInput(refinements);
        this.treeViewer.refresh();
        setMessage("Variation Point Refinement Recommendations", DialogPage.NONE);
    }

    /**
     * Display the source of the the selected VariationPoints variants in the SourceViewers.
     *
     * @param selectedVariationPoint
     *            The VariationPoint of which its Variants sources should be displayed.
     */
    private void displaySources(VariationPoint selectedVariationPoint) {
        VariationPoint vp = (VariationPoint) selectedVariationPoint;
        Variant leadingVariant = vp.getVariants().get(0);
        String leadingLocation = leadingVariant.getImplementingElements().get(0).getSourceLocation().getFilePath();
        IPath leadingPath = new Path(leadingLocation);
        Document leadingDocument = createDocumentFromPath(leadingPath);
        displayDocument(leadingSourceViewer, leadingDocument);

        addRulerDecorators(leadingRuler);

        Document integrationDocument;
        if (vp.getVariants().size() > 1) {
            Variant integrationVariant = vp.getVariants().get(1);
            String integationLocation = integrationVariant.getImplementingElements().get(0).getSourceLocation()
                    .getFilePath();
            IPath integrationPath = new Path(integationLocation);
            integrationDocument = createDocumentFromPath(integrationPath);

            addRulerDecorators(integrationRuler);
        } else {
            removeAllRulerDecorators(integrationRuler);
            integrationDocument = new Document();
        }
        displayDocument(integrationSourceViewer, integrationDocument);
    }

    /**
     * Reset the refinement page to ensure no out-dated data resists.
     */
    public void reset() {

        removeAllRulerDecorators(leadingRuler);
        removeAllRulerDecorators(integrationRuler);

        setRefinements(new ArrayList<Refinement>());
    }

    /**
     * Adds all necessary decorators to the ruler if they are not already set.
     *
     * @param ruler
     *            The ruler to add the decorators to.
     */
    private void addRulerDecorators(CompositeRuler ruler) {
        if (!ruler.getDecoratorIterator().hasNext()) {
            ruler.addDecorator(0, new LineNumberRulerColumn());
        }
    }

    /**
     * Removes all decorators of the ruler.
     *
     * @param ruler
     *            The ruler to remove the decorators from.
     */
    private void removeAllRulerDecorators(CompositeRuler ruler) {
        @SuppressWarnings("rawtypes")
        Iterator iter = ruler.getDecoratorIterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        ruler.relayout(); // We need this line to force our changes to be visually updated.
    }

    /**
     * SelectionChangedListener that updates the the SourceViewers if the selection in the
     * TreeViewer changes.
     *
     * @author Christian Busch
     *
     */
    private class VPMSelectionChangedListener implements ISelectionChangedListener {

        @Override
        public void selectionChanged(SelectionChangedEvent event) {

            Object selectedElement = ((StructuredSelection) event.getSelection()).getFirstElement();

            if (selectedElement instanceof VariationPoint) {
                displaySources((VariationPoint) selectedElement);
            } else if (selectedElement instanceof Variant) {
                displaySources(((Variant) selectedElement).getVariationPoint());
            }
        }
    }

    /**
     * This provider is responsible to compute which nodes are to be display as selected.
     */
    private class CheckStateProvider implements ICheckStateProvider {

        @Override
        public boolean isChecked(Object element) {
            if (element instanceof Refinement) {
                return selectedRefinements.contains(element);
            } else {
                logger.info("Unhandled element checked");
                // TODO Check if element is in tree and in output data structure
            }

            return false;
        }

        @Override
        public boolean isGrayed(Object element) {
            // TODO check if there exists one child element not selected
            return false;
        }

    }

    /**
     * Listener to react if the checkbox of a refinement tree node is changed.
     */
    private class CheckStateListener implements ICheckStateListener {

        @Override
        public void checkStateChanged(CheckStateChangedEvent event) {
            logger.info("Tree Checkbox state changed: " + "Source=" + event.getSource() + " | State=" + event.getChecked());
        }

    }

}

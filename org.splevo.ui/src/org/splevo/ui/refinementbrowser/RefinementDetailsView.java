package org.splevo.ui.refinementbrowser;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.ResourceManager;
import org.splevo.vpm.refinement.Refinement;

import swing2swt.layout.BorderLayout;

public class RefinementDetailsView extends Composite {
	

	public RefinementDetailsView(Composite parent, int style) {
		this(parent, style, null);
	}
	public RefinementDetailsView(Composite parent, int style, Refinement refinement) {
		super(parent, style);
	}

	private Table table;
	private Text txtRefinementType;

	/**
	 * Create contents of the details page.
	 * @param parent
	 */
	public void createContents(Composite parent) {
		parent.setEnabled(true);
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		parent.setLayout(new BorderLayout(0, 0));
		
		Section sectionDetails = toolkit.createSection(parent, Section.CLIENT_INDENT | Section.TWISTIE | Section.TITLE_BAR);
		sectionDetails.setLayoutData(BorderLayout.NORTH);
		toolkit.paintBordersFor(sectionDetails);
		sectionDetails.setText("Refinement Details");
		
		Composite infoArea = toolkit.createComposite(sectionDetails, SWT.NONE);
		toolkit.paintBordersFor(infoArea);
		sectionDetails.setClient(infoArea);
		
		Label lblRefinementType = toolkit.createLabel(infoArea, "Refinement Type:", SWT.NONE);
		lblRefinementType.setBounds(10, 10, 122, 20);
		
		txtRefinementType = toolkit.createText(infoArea, "", SWT.NONE);
		txtRefinementType.setEnabled(false);
		txtRefinementType.setEditable(false);
		txtRefinementType.setBounds(138, 7, 100, 26);
		
		ToolBar toolBar = new ToolBar(sectionDetails, SWT.FLAT | SWT.RIGHT);
		toolkit.adapt(toolBar);
		toolkit.paintBordersFor(toolBar);
		sectionDetails.setTextClient(toolBar);
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setHotImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/bin.png"));
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		tltmNewItem.setToolTipText("Discard Refinement");
		tltmNewItem.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/bin_closed.png"));
		//		
		Section sectionVariationPoints = toolkit.createSection(parent,
				Section.TWISTIE | Section.TITLE_BAR);
		sectionVariationPoints.setText("Variation Points");
		sectionVariationPoints.setExpanded(true);
		//
		Composite composite = toolkit.createComposite(sectionVariationPoints, SWT.NONE);
		toolkit.paintBordersFor(composite);
		sectionVariationPoints.setClient(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		toolkit.paintBordersFor(table);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnVariationPoints = tableViewerColumn.getColumn();
		tblclmnVariationPoints.setWidth(150);
		tblclmnVariationPoints.setText("Variation Points");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnLocation = tableViewerColumn_1.getColumn();
		tblclmnLocation.setWidth(400);
		tblclmnLocation.setText("Location");
	}
}

package org.splevo.ui.wizards.vpmanalysis;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.ResourceManager;
import org.splevo.vpm.analyzer.VPMAnalyzer;
import org.splevo.vpm.analyzer.config.ConfigDefinition;

/**
 * Wizard page to select and configure the vpm analyzers to be executed.
 * 
 * @author Benjamin Klatt
 */
public class VPMAnalyzerConfigurationPage extends WizardPage {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(VPMAnalyzerConfigurationPage.class);

    /** The configured analyzer instances. */
    private List<VPMAnalyzer> analyzers = new LinkedList<VPMAnalyzer>();

    /** The editing support to manipulate a configuration table cell. */
    private VPMAnalyzerConfigurationEditingSupport configEditingSupport = null;

	/** References the currently selected analyzer. */
	private VPMAnalyzer selectedAnalyzer;

	/** The {@link TableViewer} for the configurations table. */
	private TableViewer tv;

	/** The composite containing the used analyzers. */
	private Composite analyzerComposite;

    /**
     * Create the wizard page to let the user select the analyzes to be performed.
     */
    public VPMAnalyzerConfigurationPage() {
        super("wizardPage");
        setTitle("Analyzer Configuration");
        setDescription("Select and configure the variation point model analyzer to be executed.");
    }

    /**
     * Create contents of the wizard.
     * 
     * @param parent
     *            The parent ui element this control should be placed in.
     */
    public void createControl(Composite parent) {
    	Composite container = new Composite(parent, SWT.NULL);
    	setControl(container);
    	container.setLayout(new FormLayout());
    	
    	Group grpAnalyzers = new Group(container, SWT.SHADOW_IN);
		grpAnalyzers.setText("Analyzers");
		grpAnalyzers.setLayout(new FillLayout());
		FormData fd_grpAnalyzers = new FormData();
		fd_grpAnalyzers.top = new FormAttachment(0);
		fd_grpAnalyzers.left = new FormAttachment(0, 5);
		fd_grpAnalyzers.right = new FormAttachment(100, -5);
		fd_grpAnalyzers.bottom = new FormAttachment(30);
		grpAnalyzers.setLayoutData(fd_grpAnalyzers);
		
		ScrolledComposite scrolledComposite1 = new ScrolledComposite(grpAnalyzers, SWT.SHADOW_IN | SWT.H_SCROLL | SWT.V_SCROLL);
		analyzerComposite = new Composite(scrolledComposite1, SWT.NONE);
		analyzerComposite.setLayout(new GridLayout(3, false));
		scrolledComposite1.setContent(analyzerComposite);
		
		Group grpConfigurations = new Group(container, SWT.SHADOW_IN);
		grpConfigurations.setText("Configurations");
		grpConfigurations.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_grpConfigurations = new FormData();
		fd_grpConfigurations.top = new FormAttachment(grpAnalyzers, 6);
		fd_grpConfigurations.left = new FormAttachment(0, 5);
		fd_grpConfigurations.right = new FormAttachment(100, -5);
		fd_grpConfigurations.bottom = new FormAttachment(100);
		grpConfigurations.setLayoutData(fd_grpConfigurations);
			
		Composite tableComp = new Composite(grpConfigurations, SWT.NONE);
		tv = new TableViewer(tableComp, SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		
        TableViewerColumn tableViewerColumnSetting = new TableViewerColumn(tv, SWT.NONE);
        TableColumn setCol = tableViewerColumnSetting.getColumn();
        setCol.setResizable(true);
        setCol.setText("Setting");
        setCol.setWidth(100);
        tableViewerColumnSetting.setLabelProvider(new CellLabelProvider() {
            @SuppressWarnings("unchecked")
            @Override
            public void update(ViewerCell cell) {
            	if (selectedAnalyzer == null) {
                	return;
                }
            	Entry<String, ConfigDefinition> e = (Entry<String, ConfigDefinition>) cell.getElement();
                String label = selectedAnalyzer.getConfigurationLabels().get(e.getKey());
                if (label == null) {
                    logger.warn("Label not specified for setting " + e.getKey() + " in Analyzer "
                            + selectedAnalyzer);
                    cell.setText(e.getKey());
                } else {
                    cell.setText(label);
                }
            }
        });

        TableViewerColumn tableViewerColumnValue = new TableViewerColumn(tv, SWT.FILL);
        TableColumn valCol = tableViewerColumnValue.getColumn();
        valCol.setText("Value");
        valCol.setResizable(true);
        tableViewerColumnValue.setLabelProvider(new CellLabelProvider() {
            @SuppressWarnings("unchecked")
            @Override
            public void update(ViewerCell cell) {
                if (selectedAnalyzer == null) {
                	return;
                }
                Entry<String, ConfigDefinition> e = (Entry<String, ConfigDefinition>) cell.getElement();
                Map<String, Object> configurations = selectedAnalyzer.getConfigurations();
                if (configurations.containsKey(e.getKey()) && configurations.get(e.getKey()) != null) {
                    cell.setText(configurations.get(e.getKey()).toString());
                } else if (e.getValue().getDefaultValue() != null) {
                    cell.setText(e.getValue().getDefaultValue().toString());
                } else {
                    cell.setText("");
                }
            }
        });
        
        // Set up config table
        Table table = tv.getTable();
		table.setEnabled(false);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

        configEditingSupport = new VPMAnalyzerConfigurationEditingSupport(tv);
        tableViewerColumnValue.setEditingSupport(configEditingSupport);
        
        // Resize the columns to fit the contents
        setCol.pack();
        valCol.pack();
        // Use the packed widths as the minimum widths
        int setWidth = setCol.getWidth();
        int valWidth = valCol.getWidth();
        // Set value column to fill 100% and settings column to fit 0%, but with their packed widths as minimums
        TableColumnLayout tableLayout = new TableColumnLayout();
        tableLayout.setColumnData(setCol, new ColumnWeightData(0, setWidth));
        tableLayout.setColumnData(valCol, new ColumnWeightData(100, valWidth));
        tableComp.setLayout(tableLayout);
        
        // Update view
        updateAnalyzerPanel();
        updateConfigTable();
    }

    @Override
    public boolean isPageComplete() {
        return (analyzers.size() > 0);
    }

    @Override
    public boolean canFlipToNextPage() {
        return isPageComplete();
    }

    /**
     * Add an analyzer to the list of configured analyzers.
     * 
     * @param analyzer
     *            The analyzer instance to be added.
     */
    public void addAnalyzer(VPMAnalyzer analyzer) {
    	changeSelectedAnalyzer(analyzer, false);
        this.analyzers.add(analyzer);
        update();
    }    

	/**
     * Remove an analyzer from the list of configured analyzers.
     * 
     * @param analyzer
     *            The analyzer to be removed.
     */
    public void removeAnalyzer(VPMAnalyzer analyzer) {
    	if(this.selectedAnalyzer == analyzer) {
			changeSelectedAnalyzer(null, false);
		}
        this.analyzers.remove(analyzer);
        update();
    }

    /**
     * Get the set of configured analyzer instances.
     * 
     * @return The list of analyzers.
     */
    public List<VPMAnalyzer> getAnalyzers() {
        return analyzers;
    }
    
	private void changeSelectedAnalyzer(VPMAnalyzer analyzer, boolean updateUI) {
		this.selectedAnalyzer = analyzer;
		if (updateUI) {
			update();
		}		
	}

	private void update() {
    	updateAnalyzerPanel();
    	updateConfigTable();
    	getWizard().getContainer().updateButtons();
    }
    
    private void updateConfigTable(){
    	Table table = tv.getTable();
    	
    	if (this.selectedAnalyzer == null) {
    		table.setEnabled(false);
		} else {
			table.setEnabled(true);
			tv.setContentProvider(ArrayContentProvider.getInstance());
			tv.setInput(this.selectedAnalyzer.getAvailableConfigurations().entrySet());
			configEditingSupport.setAnalyzer(this.selectedAnalyzer);
		}
    }
	
	private void updateAnalyzerPanel() {
    	for (Control control : analyzerComposite.getChildren()) {
    		control.dispose();
        }
    	analyzerComposite.pack();
    	for (final VPMAnalyzer analyzer : analyzers) {
    		final Composite analyzerComponent  = new Composite(this.analyzerComposite, SWT.BORDER);
    		RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
    		rowLayout.center = true;
    		analyzerComponent.setLayout(rowLayout);
    		final Label label = new Label(analyzerComponent, SWT.NONE);
    		label.setText(analyzer.getName());
    		
    		if (analyzer.equals(this.selectedAnalyzer)) {
    			analyzerComponent.setBackground(new Color(analyzerComponent.getDisplay(), 220, 220, 220));
        		label.setBackground(new Color(analyzerComponent.getDisplay(), 220, 220, 220));
    		}
    		
    		MouseListener changeListener = new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent arg0) {
					changeSelectedAnalyzer(analyzer, true);
				}
				
				@Override
				public void mouseDown(MouseEvent arg0) {
					// Ignore: Not used					
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent arg0) {
					// Ignore: Not used
				}
			};
    		analyzerComponent.addMouseListener(changeListener);
    		label.addMouseListener(changeListener);
    		
    		Button rmvBtn = new Button(analyzerComponent, SWT.PUSH);
    		rmvBtn.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/cross.png"));
    		rmvBtn.addMouseListener(new MouseListener() {
    			
    			@Override
    			public void mouseUp(MouseEvent arg0) {
    				removeAnalyzer(analyzer);
    			}

    			@Override
    			public void mouseDoubleClick(MouseEvent arg0) {
    				// Ignore: Not used
    			}

    			@Override
    			public void mouseDown(MouseEvent arg0) {
    				// Ignore: Not used
    			}
    		});
    	}
    	
    	if (analyzers.size() == 0) {
    		Label labelNoElements = new Label(analyzerComposite, SWT.NONE);
    		labelNoElements.setText("No analyzer added, yet...");
    		labelNoElements.setForeground(new Color(analyzerComposite.getDisplay(), 255, 0, 0));
    	}
    	
    	Button addBtn = new Button(analyzerComposite, SWT.PUSH);
    	addBtn.setImage(ResourceManager.getPluginImage("org.splevo.ui", "icons/plus.png"));
    	addBtn.addMouseListener(new VPMAnalyzerSelectionDialogListener(this));

    	analyzerComposite.setSize(analyzerComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}

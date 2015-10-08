package org.splevo.jamopp.refactoring.java.caslicensehandler.cheatsheet.actions;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.splevo.vpm.variability.Variant;


/**
 * The user can assign a variant to a given license with the mapping dialog.
 */
public class MappingDialog extends TitleAreaDialog {

    private List<Variant> variants;
    private CASLicenseHandlerConfiguration config;
    private ComboBoxViewerCellEditor cellEditor;

    /**
     * The constructor.
     * 
     * @param parentShell
     *            represents the parent shell.
     */
    public MappingDialog(Shell parentShell) {
        super(parentShell);
        this.config = CASLicenseHandlerConfiguration.getInstance();
        this.variants = config.getVariationPoint().getVariants();
    }
    
    @Override
    public void create() {
        super.create();
        setTitle("Mapping Dialog");
        setMessage("Assign a variant to a given license");
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        
        GridLayout gridLayout = new GridLayout(1, false);
        container.setLayout(gridLayout);
        
        TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        
        Table table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setLayout(tableLayout);
        
        TableViewer tableViewer = new TableViewer(table);
        tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        TableViewerColumn labelColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        labelColumn.getColumn().setText("Variant");
        
        TableViewerColumn valueColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        valueColumn.getColumn().setText("License Constant");

        EditingSupport exampleEditingSupport = new TableEditingSupport(valueColumn.getViewer());
        valueColumn.setEditingSupport(exampleEditingSupport);

        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new VariantTableLabelProvider());
        tableViewer.setInput(variants);
        
        Button newLicense = new Button(container, SWT.PUSH);
        newLicense.setText("New License Constant");
        newLicense.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseUp(MouseEvent e) {
                AddLicenseDialog dialog = new AddLicenseDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell());
                if (dialog.open() == Window.OK) {
                    cellEditor.setInput(config.getAllLicenses());
                }
            }
            
            @Override
            public void mouseDown(MouseEvent e) { }
            
            @Override
            public void mouseDoubleClick(MouseEvent e) { }
        });

        return container;
    }

    /**
     * Provides table with labels for each column.
     */
    private final class VariantTableLabelProvider extends LabelProvider implements ITableLabelProvider {

        @Override
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        @Override
        public String getColumnText(Object element, int columnIndex) {
            if (!(element instanceof Variant)) {
                return "";
            }
            Variant data = (Variant) element;
            switch (columnIndex) {
            case 0:
                return data.getId();
            case 1:
                return config.getVariantToLicenseMap().get(data.getId());
            default:
                return "";
            }
        }
    }

    /**
     * Supports the table with cell editing.
     */
    private final class TableEditingSupport extends EditingSupport {       

        @SuppressWarnings("deprecation")
        private TableEditingSupport(ColumnViewer viewer) {
            super(viewer);
            cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(), SWT.NONE);
            cellEditor.setLabelProvider(new LabelProvider());
            cellEditor.setContenProvider(new ArrayContentProvider());    
            cellEditor.setInput(config.getAllLicenses());       
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            return cellEditor;
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @Override
        protected Object getValue(Object element) {
            if (!(element instanceof Variant)) {
                return null;
            }            
            return config.getVariantToLicenseMap().get(((Variant) element).getId());
        }

        @Override
        protected void setValue(Object element, Object value) {
            if (element instanceof Variant && value instanceof String) {
                Variant data = (Variant) element;
                String license = (String) value;                
                if (!config.addVariantLicensePair(data.getId(), license)) {
                    MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
                            "Information", "Every variant needs a different License Constant.");                   
                }
                getViewer().update(element, null);
            }
        }
    }    
    
    @Override 
    protected Point getInitialSize() { 
        return new Point(450, 300); 
    }
}

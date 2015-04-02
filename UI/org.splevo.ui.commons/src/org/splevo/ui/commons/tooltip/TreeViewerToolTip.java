/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.ui.commons.tooltip;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TreeItem;

import com.google.common.base.Strings;

/**
 * Base class for tree viewer tool tips that automatically wraps long tool tips and provides the
 * possibility to add an header text to the tooltip. The decision to display a tooltip is done by
 * comparing the hovered item's type with a given set of relevant types. Please note that background
 * images are not supported.
 */
public abstract class TreeViewerToolTip extends DefaultToolTip {

    private final TreeViewer viewer;
    private final Class<?>[] relevantItemTypes;

    /**
     * Constructs a new tree viewer tooltip.
     * 
     * @param viewer
     *            The tree viewer to be monitored.
     * @param relevantItemTypes
     *            A set of types to which a hovered item has to conform in order to display the
     *            tooltip.
     */
    public TreeViewerToolTip(TreeViewer viewer, Class<?>... relevantItemTypes) {
        super(viewer.getTree());
        this.viewer = viewer;
        this.relevantItemTypes = relevantItemTypes;
        this.setShift(new Point(12, 0));
        this.setRespectDisplayBounds(true);
        this.setRespectMonitorBounds(true);
    }

    @Override
    protected boolean shouldCreateToolTip(Event event) {
        if (getItem(event, relevantItemTypes) == null) {
            return false;
        }
        return super.shouldCreateToolTip(event);
    }

    @Override
    protected Composite createToolTipContentArea(Event event, Composite parent) {
        String text = getText(event);
        String heading = getToolTipHeading(event);

        final Composite container = setDefaultLayout(new Composite(parent, SWT.NULL), event);
        container.setLayout(new GridLayout());

        StyledText label = setDefaultLayout(new StyledText(container, SWT.WRAP), event);

        String labelText = text;
        StyleRange headingStyling = new StyleRange();
        if (!Strings.isNullOrEmpty(heading)) {
            labelText = String.format("%s\n%s", heading, labelText);
            headingStyling.start = 0;
            headingStyling.length = heading.length();
            headingStyling.fontStyle = SWT.BOLD;
        }

        label.setText(labelText);
        label.setStyleRange(headingStyling);

        Point prefSize = label.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
        int prefWidth = Math.min(500, prefSize.x);
        GridDataFactory.fillDefaults().hint(prefWidth, SWT.DEFAULT).applyTo(label);

        return container;
    }

    /**
     * Constructs the tooltip heading based on an occurred event.
     * 
     * @param event
     *            The occurred event.
     * @return The heading text.
     */
    protected abstract String getToolTipHeading(Event event);

    /**
     * Returns the correctly typed item from a given event.
     * 
     * @param event
     *            The occurred event.
     * @param clazz
     *            The class representing the required type of the item.
     * @param <T>
     *            The required type of the item.
     * @return The correctly typed item or null if the item has an incompatible type.
     */
    @SuppressWarnings("unchecked")
    protected <T> T getCorrectlyTypedItem(Event event, Class<T> clazz) {
        Object item = getItem(event, new Class<?>[] { clazz });
        if (item == null) {
            return null;
        }
        return (T) item;
    }

    private Object getItem(Event event, Class<?>[] classes) {
        TreeItem item = getTreeItemFromEvent(event);
        if (item == null || item.getData() == null) {
            return null;
        }

        for (Class<?> c : classes) {
            if (c.isAssignableFrom(item.getData().getClass())) {
                return item.getData();
            }
        }
        return null;
    }

    private TreeItem getTreeItemFromEvent(Event event) {
        return viewer.getTree().getItem(new Point(event.x, event.y));
    }

    private <T extends Control> T setDefaultLayout(T control, Event event) {
        control.setBackground(getBackgroundColor(event));
        control.setForeground(getForegroundColor(event));
        control.setFont(getFont(event));
        return control;
    }
}
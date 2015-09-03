/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.refactoring;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.splevo.refactoring.TodoTagCustomizer;

import com.google.common.collect.Lists;

/**
 * ToDO tag customizer for JaMoPP models. 
 * The customizer adds a special SPLEVO refactoring task customizer to the properties.
 */
public class JaMoPPTodoTagCustomizer implements TodoTagCustomizer {

    private static final String JDT_PROPERTIES_QUALIFIER = "org.eclipse.jdt.core";
    private static final String TODO_TASK_TAG = "SPLEVO_REFACTORING";
    private static final String TODO_TASK_TAG_PROPERTY_QUALIFIER = "org.eclipse.jdt.core.compiler.taskTags";
    private static final String TODO_TASK_PRIORITY = "NORMAL";
    private static final String TODO_TASK_PRIORITY_PROPERTY_QUALIFIER = "org.eclipse.jdt.core.compiler.taskPriorities";
    
    private static final Logger LOGGER = Logger.getLogger(JaMoPPTodoTagCustomizer.class);
    
    /**
     * @return The tag that is added to the ToDo task tags.
     */
    public static String getTodoTaskTag() {
        return TODO_TASK_TAG;
    }
    
    @Override
    public void adjustTodoTags() {
        ScopedPreferenceStore jdt = new ScopedPreferenceStore(InstanceScope.INSTANCE, JDT_PROPERTIES_QUALIFIER);
        
        String taskTags = jdt.getString(TODO_TASK_TAG_PROPERTY_QUALIFIER);
        if (taskTags.contains(TODO_TASK_TAG)) {
            return;
        }
        taskTags = taskTags + "," + TODO_TASK_TAG;
        jdt.putValue(TODO_TASK_TAG_PROPERTY_QUALIFIER, taskTags);
        
        String taskPriorities = jdt.getString(TODO_TASK_PRIORITY_PROPERTY_QUALIFIER);
        taskPriorities = taskPriorities + "," + TODO_TASK_PRIORITY;
        jdt.putValue(TODO_TASK_PRIORITY_PROPERTY_QUALIFIER, taskPriorities);
        
        try {
            jdt.save();
        } catch (IOException e) {
            LOGGER.warn("Could not save preferences.", e);
        }
    }

    @Override
    public Iterable<String> getTodoTags() {
        return Lists.newArrayList(getTodoTaskTag());
    }

}

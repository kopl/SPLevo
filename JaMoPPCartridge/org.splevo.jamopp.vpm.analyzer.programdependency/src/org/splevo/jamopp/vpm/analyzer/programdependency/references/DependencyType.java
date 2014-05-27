/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt
 *******************************************************************************/
package org.splevo.jamopp.vpm.analyzer.programdependency.references;

/**
 * The type of a detected dependency.
 */
public enum DependencyType {

    SHARED,
    IGNORE,

    // ROBILLARD TYPES
    StatementChecksClass,
    StatementCallsMethod,
    StatementCreatesClass,
    ClassDeclaresField,
    ClassDeclaresMethod,
    StatementReadsField,
    ClassSuperTypeClass,
    StatementWritesField,

    // ROBILLARD EXTENDED TYPES
    FieldCallsMethod,
    FieldCreatesClass,
    FieldReadsField,
    FieldTypedClass,
    FieldTypedInterface,
    FieldTypedEnumeration,
    FieldImportClass,
    FieldImportInterface,
    FieldImportEnumeration,
    InterfaceSuperTypeClass,
    InterfaceSuperTypeInterface,
    InterfaceSuperTypeEnumeration,
    MethodDeclaresParameter,
    MethodTypedClass,
    MethodTypedInterface,
    MethodTypedEnumeration,
    MethodImportClass,
    MethodImportInterface,
    MethodImportEnumeration,
    ParameterTypedClass,
    ParameterTypedInterface,
    ParameterTypedEnumeration,
    StatementChecksInterface,
    StatementChecksEnumeration,
    StatementDeclaresVariable,
    StatementModifiesField,
    StatementModifiesVariable,
    StatementModifiesParameter,
    StatementReadsVariable,
    StatementReadsParameter,
    StatementWritesVariable,
    StatementWritesParameter,
    StatementImportClass,
    StatementImportInterface,
    StatementImportEnumeration,
    StatementTypedClass,
    StatementTypedInterface,
    StatementTypedEnumeration;
}

/**
 * <h1>IF-Else with Static Configuration Class (OPTOR)</h1>
 * 
 * <h2>Summary</h2>
 * An OPTOR variability refactoring that merges existing variants in a single code base with if-Else conditional statements using a Java code based compiled configuration.
 * 
 * <h2>Configuration Mechanism</h2>
 * The variant configuration is realized using a configuration class containing constants to choose the variants at compile time.
 * 
 * <h2>Motivation</h2>
 * The resulting code contains the implementation of all variants with less flexibility (e.g. no extensibility and early binding) but improves comprehension of the code being executed. 
 * 
 * <h2>Characteristics</h2>
 * <table>
 * <tr>
 *   <td>Binding Time</td>
 *   <td>Compile</td>
 * </tr>
 * <tr>
 *   <td>Variability Type</td>
 *   <td>OPTOR</td>
 * </tr>
 * <tr>
 *   <td>Extensible</td>
 *   <td>No</td>
 * </tr>
 * <tr>
 *   <td>Supported Elements</td>
 *   <td><ul><li>Class</li><li>Condition</li><li>Class</li><li>Constructor</li><li>Enumeration</li><li>Field</li><li>Import</li><li>Interface</li><li>Method</li><li>Statement</li></ul></td>
 * </tr>
 * </table>
 */
package org.splevo.jamopp.refactoring.java.ifelse.optor;
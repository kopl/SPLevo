/**
 * <h1>IF-Else with Static Configuration Class (XOR)</h1>
 * 
 * <h2>Summary</h2>
 * An XOR variability refactoring that merges existing variants in a single code base with if-Else conditional statements using a Java code based compiled configuration.
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
 *   <td>XOR</td>
 * </tr>
 * <tr>
 *   <td>Extensible</td>
 *   <td>No</td>
 * </tr>
 * <tr>
 *   <td>Supported Elements</td>
 *   <td><ul><li>Import</li><li>Constructor</li><li></li>Statement</ul></td>
 * </tr>
 * </table>
 * 
 * <h2>Alternative Refactorings</h2>
 * <ul>
 * <li>org.splevo.jamopp.refactoring.java.ifelse.optor</li>
 * <li>org.splevo.jamopp.refactoring.java.ifelse.optxor</li>
 * <li>org.splevo.jamopp.refactoring.java.ifelse.or</li>
 * </ul>
 * <h2>Limitations</h2>
 * Variable Class-, Interface- and Enumeration Signatures are not supported since they can neither co-exist, nor can they be handled by If-Else constructs.
 * 
 * Methods are not supported because methods with equal names but varying return types cannot be realized using If-Else constructs.
 * 
 * Fields are not supported since in case of equal names but different types, they cannot co-exist, nor varied by If-Else constructs.
 */
package org.splevo.jamopp.refactoring.java.ifelse.xor;
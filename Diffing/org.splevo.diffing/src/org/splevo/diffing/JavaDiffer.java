/*******************************************************************************
 * Copyright (c) 2013
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.diffing;

/**
 * Differs specific for the Java plattform.
 */
public abstract class JavaDiffer implements Differ {

	/** Option key for java packages to ignore. */
	public static final String OPTION_JAVA_IGNORE_PACKAGES = "java.ignorePackages";

}

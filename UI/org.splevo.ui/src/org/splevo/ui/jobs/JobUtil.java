/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.ui.jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.extraction.DefaultExtractionService;
import org.splevo.project.SPLevoProject;

import com.google.common.collect.Lists;

/**
 * Utility class for common tasks performed by jobs.
 */
public final class JobUtil {

	/** Disabled constructor to use utility class in a static manner. */
	private JobUtil() {
	}

	/**
	 * Initialize the resource set including preparation by the source model
	 * extractors for specific source models.
	 * 
	 * @param splevoProject
	 *            The {@link SPLevoProject} to get required configurations from.
	 * 
	 * @return The initialized resource set.
	 */
	public static ResourceSetImpl initResourceSet(SPLevoProject splevoProject) {

		ResourceSetImpl resourceSet = new ResourceSetImpl();

		List<String> sourceModelPaths = Lists.newArrayList();
		sourceModelPaths.add(splevoProject.getSourceModelPathLeading());
		sourceModelPaths.add(splevoProject.getSourceModelPathIntegration());

		DefaultExtractionService extractionService = new DefaultExtractionService();
		extractionService.prepareResourceSet(resourceSet, sourceModelPaths);

		return resourceSet;
	}

	/**
	 * Get the current human readable timestamp. For example, to be used in
	 * logging.
	 * 
	 * @return The string representation of the timestamp.
	 */
	public static String getTimestamp() {

		/** The date format to use in job logging etc. */
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss:S");
		
		return (dateFormat.format(new Date()));
	}

}

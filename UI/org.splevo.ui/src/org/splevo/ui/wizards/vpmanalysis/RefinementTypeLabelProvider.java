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
package org.splevo.ui.wizards.vpmanalysis;

import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.LabelProvider;
import org.splevo.vpm.refinement.RefinementType;

/**
 * A {@link LabelProvider} that generates a textual representation for an ID. 
 * The strings are generated as follows:
 * 
 * [First letter of ref type]: [[label1]] [[label2]]
 * 
 * @author Daniel Kojic
 *
 */
public class RefinementTypeLabelProvider extends LabelProvider {

	/**
	 * Stores the ID's {@link String} labels.
	 */
	private Map<Integer, Set<String>> labelsToGroupID;

	/**
	 * Stores the ID's {@link RefinementType}.
	 */
	private Map<Integer, RefinementType> refinementTypeToGroupID;

	/**
	 * Stores the ID's labels and refinement type for later access.
	 * 
	 * @param labelsToGroupID The ID's {@link String} labels
	 * @param refinementTypeToGroupID The ID's {@link RefinementType}.
	 */
	public RefinementTypeLabelProvider(
			Map<Integer, Set<String>> labelsToGroupID,
			Map<Integer, RefinementType> refinementTypeToGroupID) {
		this.labelsToGroupID = labelsToGroupID;
		this.refinementTypeToGroupID = refinementTypeToGroupID;
	}

	/**
	 * Get the text for the Map elements.
	 * 
	 * @param element
	 *            The object which is expected to be an VPMRefinementAnalyzer
	 * @return The name of the VPM refinement analyzer as the label to present..
	 */
	@Override
	public String getText(Object element) {
		Integer id = (Integer) element;
		return getFirstCharOfRefinementType(id) + ": " + getLabelsString(id);
	}

	/**
	 * Generates a {@link String} representation of the ID's labels: [l1] [l2]
	 * ...
	 * 
	 * @param id
	 *            The label's ID.
	 * @return The String.
	 */
	private String getLabelsString(Integer id) {
		Set<String> labels = labelsToGroupID.get(id);
		StringBuilder result = new StringBuilder();
		for (String label : labels) {
			result.append("[").append(label.substring(0, 3)).append("] ");
		}
		return result.toString();
	}

	/**
	 * Gets the first character of the {@link RefinementType} with the given ID.
	 * 
	 * @param id
	 *            The ID.
	 * @return The character.
	 */
	private char getFirstCharOfRefinementType(Integer id) {
		return refinementTypeToGroupID.get(id).name().charAt(0);
	}
}

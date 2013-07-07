package org.splevo.vpm.analyzer.semantic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This container stores links between textual IDs.
 * Every stored ID can be connected to several different IDs.
 * 
 * @author Daniel Kojic
 *
 */
public class StructuredMap {
	
	/** This {@link Set} stores all IDs this {@link StructuredMap} contains. */
	private Set<String> allIds;

	/** This {@link Map} stores the relationships of the given String(key) in a {@link Set} (value). */
	private Map<String, Set<String>> links;
	
	/** This {@link Map} contains the IDs of the related nodes as keys and a explanation as value. */
	private Map<String, Set<String>> explanations;
	
	/**
	 * Gets the explanations.
	 * 
	 * @return A {@link Map} containing the IDs for the explanations in the form "id1 id2" and their explanations.
	 */
	public Map<String, Set<String>> getExplanations() {
		return explanations;
	}

	/**
	 * Sets explanations.
	 * 
	 * @param explanations The {@link Map} containing the IDs for the explanations in the form "id1 id2" and their explanations.
	 */
	public void setExplanations(Map<String, Set<String>> explanations) {
		this.explanations = explanations;
	}

	/**
	 * This constructor initializes the container objects.
	 */
	public StructuredMap() {
		allIds = new HashSet<String>();
		links = new HashMap<String, Set<String>>();
		explanations = new HashMap<String, Set<String>>();
	}
	
	/**
	 * Creates a link between the given ID and all IDs in the list.
	 * 
	 * @param id The id on the left side of the edge.
	 * @param ids The IDs on the right sight of the edge.
	 */
	public void addLinks(String id, Set<String> ids) {
		if (id == null || ids == null || ids.size() == 0) {
			throw new IllegalArgumentException();
		}
		
		allIds.add(id);
		for (String s : ids) {
			allIds.add(s);
		}
		links.put(id, ids);
	}
	
	/**
	 * Add a relationship between the specified IDs and stores an explanation
	 * that describes the relationship.
	 * 
	 * @param id1 The first ID.
	 * @param id2 The second ID.
	 * @param explanation The explanation.
	 */
	public void addLink(String id1, String id2, String explanation) {
		if (id1 == null || id2 == null) {
			throw new IllegalArgumentException();
		}
		
		allIds.add(id1);
		allIds.add(id2);
		
		// Sort the IDs ascending.
		if (id1.compareTo(id2) > 0) {
			String tmp = id1;
			id1 = id2;
			id2 = tmp;
		}
		
		// Create a new Set if not yet existing.
		if (!links.containsKey(id1)) {
			links.put(id1, new HashSet<String>());
		}
		
		// Add the relationship.
		links.get(id1).add(id2);
		
		addExplaination(id1, id2, explanation);
	}
	
	/**
	 * Adds the given explanation to the given link.
	 * 
	 * @param id1 The ID of the first node.
	 * @param id2 The ID of the second node.
	 * @param explanation The explanation.
	 */
	private void addExplaination(String id1, String id2, String explanation) {
		if (id1.compareTo(id2) > 0) {
			String tmp = id1;
			id1 = id2;
			id2 = tmp;
		}
		String mapID = id1 + " " + id2;
		
		if (explanations.get(mapID) == null) {
			explanations.put(mapID, new HashSet<String>());
		}
		
		Set<String> explanationList = this.explanations.get(mapID);
		explanationList.add("Reason: " + explanation);
	}
	
	/**
	 * Gets the explanation of the given nodes.
	 * 
	 * @param id1 The id of the first node.
	 * @param id2 The ID of the second node.
	 * @return The explanation. Null if none existing or invalid IDs.
	 */
	public String getExplanation(String id1, String id2) {
		if (id1.compareTo(id2) > 0) {
			String tmp = id1;
			id1 = id2;
			id2 = tmp;
		}
		
		String mapID = id1 + " " + id2;
		
		Set<String> resultList = this.explanations.get(mapID);
		if (resultList == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		for (String item : resultList) {
			sb.append(item + "\n");
		}
		
		return sb.toString();
	}

	/**
	 * Delete all content this {@link StructuredMap} stores.
	 */
	public void clear() {
		allIds.clear();
		links.clear();
		explanations.clear();
	}
	
	/**
	 * Gets an {@link Array} containing all IDs that this {@link StructuredMap} holds.
	 * 
	 * @return All IDs.
	 */
	public String[] getAllIds() {
		return allIds.toArray(new String[0]);
	}
	
	
	/**
	 * Gets all the relationships this {@link StructuredMap} holds.
	 * To get all Relationship edges to a given ID, just get its values.
	 * 
	 * @return A {@link Map} containing the relationships.
	 */
	public Map<String, Set<String>> getAllLinks() {
		return links;
	}
}

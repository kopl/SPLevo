package org.splevo.vpm.analyzer.semantic;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StructuredMap {
	
	/** This {@link Set} stores all IDs this {@link StructuredMap} contains. */
	private Set<String> allIds;

	/** This {@link Map} stores the relationships of the given String(key) in a {@link Set} (value). */
	private Map<String, Set<String>> links;
	
	public StructuredMap(){
		allIds = new HashSet<String>();
		links = new HashMap<String, Set<String>>();
	}
	
	/**
	 * Add a relationship between the specified IDs.
	 * 
	 * @param id1 The first ID.
	 * @param id2 The second ID.
	 */
	public void addLink(String id1, String id2){
		allIds.add(id1);
		allIds.add(id2);
		
		// Sort the IDs ascending.
		if(id1.compareTo(id2) > 0){
			String tmp = id1;
			id1 = id2;
			id2 = tmp;
		}
		
		// Create a new Set if not yet existing.
		if(!links.containsKey(id1)){
			links.put(id1, new HashSet<String>());
		}
		
		// Add the relationship.
		links.get(id1).add(id2);
	}
	
	/**
	 * Delete all content this {@link StructuredMap} stores.
	 */
	public void clear(){
		allIds.clear();
		links.clear();
	}
	
	/**
	 * Gets an {@link Array} containing all IDs that this {@link StructuredMap} holds.
	 * 
	 * @return All IDs.
	 */
	public String[] getAllIds(){
		return allIds.toArray(new String[0]);
	}
	
	
	/**
	 * Gets all the relationships this {@link StructuredMap} holds.
	 * To get all Relationship edges to a given ID, just get its values.
	 * 
	 * @return A {@link Map} containing the relationships.
	 */
	public Map<String, Set<String>> getAllLinks(){
		return links;
	}
}

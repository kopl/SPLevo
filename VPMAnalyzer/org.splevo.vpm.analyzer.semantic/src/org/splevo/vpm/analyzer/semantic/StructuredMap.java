package org.splevo.vpm.analyzer.semantic;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * This container stores links between textual IDs.
 * Every stored ID can be connected to several different IDs.
 * 
 * @author Daniel Kojic
 *
 */
public class StructuredMap {
	
//	/** The logger for this class. */
//    private Logger logger = Logger.getLogger(StructuredMap.class);
	
	/** This {@link Set} stores all IDs this {@link StructuredMap} contains. */
	private Set<String> allIds;

	/** This {@link Map} stores the relationships of the given String(key) in a {@link Set} (value). */
	private Map<String, Set<String>> links;
	
//	public enum MergeStrategy{
//		AND,
//		OR
//	}
	
	/**
	 * This constructor initializes the container objects.
	 */
	public StructuredMap() {
		allIds = new HashSet<String>();
		links = new HashMap<String, Set<String>>();
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
		
		links.put(id, ids);
	}
	
	/**
	 * Add a relationship between the specified IDs.
	 * 
	 * @param id1 The first ID.
	 * @param id2 The second ID.
	 */
	public void addLink(String id1, String id2) {
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
	}
	
	/**
	 * Delete all content this {@link StructuredMap} stores.
	 */
	public void clear() {
		allIds.clear();
		links.clear();
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
	
//	public static StructuredMap Merge(StructuredMap map1, StructuredMap map2, MergeStrategy strategy){
//		if(map1 == null || map2 == null || strategy == null){
//			throw new IllegalArgumentException();
//		}
//		
//		switch (strategy) {
//		case AND:
//			return andMerge(map1, map2);
//		case OR:
//			return orMerge(map1, map2);
//
//		default:
//			throw new IllegalArgumentException();
//		}
//	}
//
//	private static StructuredMap orMerge(StructuredMap map1, StructuredMap map2) {
//		if(map1 == null || map2 == null){
//			throw new IllegalArgumentException();
//		}
//		
//		StructuredMap result = new StructuredMap();
//		for (String relationshipId : map1.getAllLinks().keySet()) {
//			result.addLinks(relationshipId, map1.getAllLinks().get(relationshipId));
//		}
//		
//		for (String relationshipId : map2.getAllLinks().keySet()) {
//			result.addLinks(relationshipId, map2.getAllLinks().get(relationshipId));
//		}
//		
//		return result;
//	}
//
//	private static StructuredMap andMerge(StructuredMap map1, StructuredMap map2) {
//		if(map1 == null || map2 == null){
//			throw new IllegalArgumentException();
//		}
//		// TODO
//		StructuredMap result = new StructuredMap();
//		for (String relationshipId : map1.getAllLinks().keySet()) {
//			result.addLinks(relationshipId, map1.getAllLinks().get(relationshipId));
//		}
//		
//		for (String relationshipId : map2.getAllLinks().keySet()) {
//			result.addLinks(relationshipId, map2.getAllLinks().get(relationshipId));
//		}
//		
//		return result;
//	}
}

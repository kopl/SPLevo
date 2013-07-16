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
public class VPLinkContainer {
	
	/** This {@link Set} stores all IDs this {@link VPLinkContainer} contains. */
	private Set<String> allIds;

	/** This {@link Map} stores the relationships of the given String(key) in a {@link Set} (value). */
	private Map<String, Set<String>> links;
	
	/** This {@link Map} contains the IDs of the related nodes as keys and a explanation as value. */
	private Map<String, Set<String>> explanations;

	/**
	 * This constructor initializes the container objects.
	 */
	public VPLinkContainer() {
		allIds = new HashSet<String>();
		links = new HashMap<String, Set<String>>();
		explanations = new HashMap<String, Set<String>>();
	}
	
	/**
	 * Add a relationship between the specified IDs and stores an explanation
	 * that describes the relationship.
	 * 
	 * @param vpID1 The first ID.
	 * @param vpID2 The second ID.
	 * @param explanation The explanation.
	 */
	public void addLink(String vpID1, String vpID2, String explanation) {
		if (vpID1 == null || vpID2 == null || explanation == null) {
			throw new IllegalArgumentException();
		}
		
		if (vpID1.equals(vpID2)) {
			return;
		}
		
		allIds.add(vpID1);
		allIds.add(vpID2);
		
		// Sort the IDs ascending.
		if (vpID1.compareTo(vpID2) > 0) {
			String tmp = vpID1;
			vpID1 = vpID2;
			vpID2 = tmp;
		}
		
		// Create a new Set if not yet existing.
		if (!links.containsKey(vpID1)) {
			links.put(vpID1, new HashSet<String>());
		}
		
		// Add the relationship.
		links.get(vpID1).add(vpID2);
		
		addExplaination(vpID1, vpID2, explanation);
	}
	
	/**
	 * Adds the given explanation to the given link.
	 * 
	 * @param vpID1 The ID of the first node.
	 * @param vpID2 The ID of the second node.
	 * @param explanation The explanation.
	 */
	private void addExplaination(String vpID1, String vpID2, String explanation) {
		if (vpID1.compareTo(vpID2) > 0) {
			String tmp = vpID1;
			vpID1 = vpID2;
			vpID2 = tmp;
		}
		
		if (vpID1.equals(vpID2)) {
			return;
		}
		
		String mapID = buildMapID(vpID1, vpID2);
		
		if (explanations.get(mapID) == null) {
			explanations.put(mapID, new HashSet<String>());
		}
		
		Set<String> explanationList = this.explanations.get(mapID);
		explanationList.add(explanation);
	}

	/**
	 * Builds a unique id out of two VP ids.
	 * 
	 * @param vpID1 The first ID.
	 * @param vpID2 The second ID.
	 * @return The generated id.
	 */
	private String buildMapID(String vpID1, String vpID2) {
		return vpID1 + " " + vpID2;
	}
	
	/**
	 * Gets the explanation of the given nodes.
	 * 
	 * @param vpID1 The id of the first node.
	 * @param vpID2 The ID of the second node.
	 * @return A {@link String}-Array containing the explanations. Null if none existing or invalid IDs.
	 */
	public String[] getExplanations(String vpID1, String vpID2) {
		if (vpID1.compareTo(vpID2) > 0) {
			String tmp = vpID1;
			vpID1 = vpID2;
			vpID2 = tmp;
		}
		
		String mapID = buildMapID(vpID1, vpID2);
		
		Set<String> resultList = this.explanations.get(mapID);
		if (resultList == null) {
			return null;
		}
		
		return resultList.toArray(new String[0]);
	}
	
	/**
	 * Gets an {@link Array} containing all IDs that this {@link VPLinkContainer} holds.
	 * 
	 * @return All IDs.
	 */
	public String[] getAllIds() {
		return allIds.toArray(new String[0]);
	}
	
	
	/**
	 * Gets all the relationships this {@link VPLinkContainer} holds.
	 * To get all Relationship edges to a given ID, just get its values.
	 * 
	 * @return A {@link Map} containing the relationships.
	 */
	public Map<String, Set<String>> getAllLinks() {
		return links;
	}
	
	/**
	 * Merges the content of two {@link VPLinkContainer}s.
	 * 
	 * @param container1 The first container.
	 * @param container2 The second container.
	 * @return A {@link VPLinkContainer} containing all links given by the argument maps.
	 */
	 public static VPLinkContainer merge(VPLinkContainer container1, VPLinkContainer container2) {
		if (container1 == null || container2 == null) {
			throw new IllegalArgumentException();
		}
		
		// The result map.
		VPLinkContainer result = new VPLinkContainer();
		
		// Add all links from the parameters to the result map.
		for (String key : container1.getAllLinks().keySet()) {
			for (String key2 : container1.getAllLinks().get(key)) {
				String[] explanations = container1.getExplanations(key, key2);
				for (String expl : explanations) {
					result.addLink(key, key2, expl);
				}
			}
		}
		for (String key : container2.getAllLinks().keySet()) {
			for (String key2 : container2.getAllLinks().get(key)) {
				String[] explanations = container2.getExplanations(key, key2);
				for (String expl : explanations) {
					result.addLink(key, key2, expl);
				}
				
			}
		}
		
		return result;
	}
}

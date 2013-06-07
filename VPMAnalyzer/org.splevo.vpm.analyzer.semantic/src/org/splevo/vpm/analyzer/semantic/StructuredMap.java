package org.splevo.vpm.analyzer.semantic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StructuredMap {
	
	private Set<String> allIds;
	private Map<String, Set<String>> links;
	
	public StructuredMap(){
		allIds = new HashSet<String>();
		links = new HashMap<String, Set<String>>();
	}
	
	public void addLink(String id1, String id2){
		allIds.add(id1);
		allIds.add(id2);
		
		if(id1.compareTo(id2) > 0){
			String tmp = id1;
			id1 = id2;
			id2 = tmp;
		}
		
		if(!links.containsKey(id1)){
			links.put(id1, new HashSet<String>());
		}
		
		links.get(id1).add(id2);
	}
	
	public void clear(){
		allIds.clear();
		links.clear();
	}
	
	public String[] getAllIds(){
		return allIds.toArray(new String[0]);
	}
	
	public Map<String, Set<String>> getAllLinks(){
		return links;
	}
}

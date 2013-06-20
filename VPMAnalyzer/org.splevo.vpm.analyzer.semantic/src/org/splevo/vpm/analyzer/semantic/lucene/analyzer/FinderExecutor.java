package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.StructuredMap;

public class FinderExecutor {
	
	private List<AbstractRelationshipFinder> finders;
	
	public FinderExecutor(){		
		finders = new LinkedList<AbstractRelationshipFinder>();
	}
	
	public void addAnalyzer(AbstractRelationshipFinder finder){
		if(finder == null) {
			throw new IllegalArgumentException();
		}
		
		finders.add(finder);
	}
	
	public StructuredMap executeAnalysis(){
		StructuredMap result = new StructuredMap();
		
		for (AbstractRelationshipFinder analyzer : finders) {
			StructuredMap tmpResult = analyzer.findSimilarEntries();
			merge(result, tmpResult);
		}
		
		return result;
	}

	/**
	 * Adds the content of the second container to the first.
	 * 
	 * @param mainContainer The first container.
	 * @param toBeAdded The second container.
	 */
	private void merge(StructuredMap mainContainer, StructuredMap toBeAdded) {
		if(mainContainer == null || toBeAdded == null) {
			throw new IllegalArgumentException();
		}
		
		for (String key : toBeAdded.getAllLinks().keySet()) {
			mainContainer.addLinks(key, toBeAdded.getAllLinks().get(key));
		}
	}

}

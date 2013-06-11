package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.index.DirectoryReader;
import org.splevo.vpm.analyzer.semantic.StructuredMap;

public class AnalysisExecutor {
	
	private List<AbstractRelationshipAnalyzer> analyzers;
	private DirectoryReader reader;
	
	public AnalysisExecutor(DirectoryReader reader){
		if(reader == null) {
			throw new IllegalArgumentException();
		}
		
		this.reader = reader;
		analyzers = new LinkedList<AbstractRelationshipAnalyzer>();
	}
	
	public void addAnalyzer(AbstractRelationshipAnalyzer analyzer){
		if(analyzer == null) {
			throw new IllegalArgumentException();
		}
		
		analyzers.add(analyzer);
	}
	
	public StructuredMap executeAnalysis(){
		StructuredMap result = new StructuredMap();
		
		for (AbstractRelationshipAnalyzer analyzer : analyzers) {
			StructuredMap tmpResult = analyzer.findSimilarEntries(reader);
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

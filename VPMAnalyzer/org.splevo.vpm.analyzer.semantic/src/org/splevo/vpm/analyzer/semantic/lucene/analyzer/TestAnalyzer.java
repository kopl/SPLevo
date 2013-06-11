package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.index.AtomicReader;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.DocsEnum;
import org.apache.lucene.index.SlowCompositeReaderWrapper;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.util.BytesRef;
import org.splevo.vpm.analyzer.semantic.Constants;
import org.splevo.vpm.analyzer.semantic.StructuredMap;

public class TestAnalyzer extends AbstractRelationshipAnalyzer{

	@Override
	public StructuredMap findSimilarEntries(DirectoryReader reader, double minSimilarity) {
		StructuredMap result = new StructuredMap();
		try {
			// Get max doc count
			int maxDoc = reader.maxDoc();
			
			// Get atomic reader to extract termDocsEnum
			AtomicReader atomicReader = SlowCompositeReaderWrapper.wrap(reader);
						
			Terms termVector = reader.getTermVector(0, Constants.INDEX_CONTENT);
			
			// All terms
			TermsEnum termsEnum = null;
			termsEnum = termVector.iterator(termsEnum);
			
			// Iterate through all 
			BytesRef b;
			while ((b = termsEnum.next()) != null) {
				
				Term term = new Term(Constants.INDEX_CONTENT, b);
				DocsEnum termDocsEnum = atomicReader.termDocsEnum(term);
				Set<String> ids = new HashSet<String>();
				int nextDoc;
				while((nextDoc = termDocsEnum.nextDoc()) != DocsEnum.NO_MORE_DOCS){
					String vpID = reader.document(nextDoc).get(Constants.INDEX_VARIATIONPOINT);
					ids.add(vpID);
				}
				
				addLinksToResult(result, ids);
				ids.clear();				
			}
		} catch (IOException e) {
		}
		return result;
	}

	private void addLinksToResult(StructuredMap result, Set<String> ids) {
		String[] a1 = ids.toArray(new String[0]);
		
		for(int i = 0; i < a1.length; i++){
			for(int q = 0; q < a1.length; q++){
				if(i == q){
					continue;
				}				
				result.addLink(a1[i], a1[q]);
			}
		}
	}

}

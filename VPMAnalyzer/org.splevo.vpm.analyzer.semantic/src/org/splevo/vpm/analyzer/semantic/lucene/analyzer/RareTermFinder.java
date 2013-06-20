package org.splevo.vpm.analyzer.semantic.lucene.analyzer;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.splevo.vpm.analyzer.semantic.Constants;

public class RareTermFinder extends AbstractBooleanQueryFinder {

	private float maxPercentage;

	public RareTermFinder(DirectoryReader reader, float maxPercentage) {
		super(reader);
		this.maxPercentage = maxPercentage;
	}

	public RareTermFinder(DirectoryReader reader) {
		super(reader);
		this.maxPercentage = Constants.RAREFINDER_DEFAULT_PERCENTAGE;
	}

	@Override
	protected Query buildQuery(Map<String, Integer> termFrequencies) {
		BooleanQuery finalQuery = new BooleanQuery();
		Integer min = Collections.min(termFrequencies.values());
		int sum = getSum(termFrequencies.values());
		for (String key : termFrequencies.keySet()) {
			float percentageShare = (float)termFrequencies.get(key)/(float)sum;
			if (termFrequencies.get(key) == min || percentageShare < this.maxPercentage) {
				Term t = new Term(Constants.INDEX_CONTENT, key);
				TermQuery termQuery = new TermQuery(t);
				finalQuery.add(termQuery, Occur.MUST);
			}
		}
		
		return finalQuery;
	}

	private int getSum(Collection<Integer> values) {
		int sum = 0;
		for (Integer integer : values) {
			sum += integer;
		}
		return sum;
	}

}

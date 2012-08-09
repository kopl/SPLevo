package org.splevo.diffing.emfcompare.diff;

import java.util.List;

public class JavaModelDiffEngineData {
	public List<String> ignorePackages;

	public JavaModelDiffEngineData(List<String> ignorePackages) {
		this.ignorePackages = ignorePackages;
	}
}
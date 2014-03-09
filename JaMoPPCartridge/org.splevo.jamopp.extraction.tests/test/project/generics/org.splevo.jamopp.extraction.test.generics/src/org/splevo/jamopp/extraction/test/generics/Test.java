package org.splevo.jamopp.extraction.test.generics;

import org.splevo.jamopp.extraction.test.generics.lib.Tupel;

public class Test {

	private Tupel<Integer, String> tuple;

	public void main() {
		int result = tuple.getS1().intValue();
	}
}

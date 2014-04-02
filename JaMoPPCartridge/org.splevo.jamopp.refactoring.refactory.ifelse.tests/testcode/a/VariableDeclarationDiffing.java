package org.splevo.tests.importdeclaration;


public class ImportDiffing {
	public void helloWorld() {
		String common = null;
		if ("leading".equals(Config.getConfig("helloWorld()"))) {
			common = "a";
		}else if ("integration".equals(Config.getConfig("helloWorld()"))) {
			common = "b";
		}
		System.out.println(common);
	}
}




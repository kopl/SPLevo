package org.splevo.jamopp.diffing.tests.access;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;

public class ExternalTypeAccess {

    @SuppressWarnings("unused")
    private XPath path;

	public void typeAsListType() {
		List<XPath> paths = new ArrayList<XPath>();
		System.out.println(paths);
	}

    public void typeAsArrayParameter(XPath[] extents){
        System.out.println(extents);
    }

    /**
     * An unresolveable reference in a java doc comment tag that is not changed.
     *
     * @see org.netbeans.lib.jmi.xmi.XmiContext#XmiContext(javax.xml.xpath.XPath[], org.netbeans.api.xmi.XMIInputConfig)
     */
    public void typeInJavaDocTag(){
    }

    @SuppressWarnings("unused")
    public void typeInMethod(){
        XPath path;
    }

}

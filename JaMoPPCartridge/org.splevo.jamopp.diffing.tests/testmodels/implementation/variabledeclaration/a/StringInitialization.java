package org.splevo.tests.variabledeclaration;

public class StringInitialization {

    private static final String EXPORTER_NAME = "Test Exporter";
    private static final String EXPORTER_VERSION = "1.4";
    private static final String UML_VERSION = "1.4";

    private String version = "1";

    public void doSth() {
        String header =
                "    <XMI.documentation>\n"
              + "      <XMI.exporter>ArgoUML"
                      + " (using "  + EXPORTER_NAME
                      + " version " + EXPORTER_VERSION
                      + ")</XMI.exporter>\n"
              + "      <XMI.exporterVersion>" + version
                      + " revised on "
                      + "$Date: 2010-09-26 00:23:13 +0200 (Sun, 26 Sep 2010) $ "
                      + "</XMI.exporterVersion>\n"
              + "    </XMI.documentation>\n"
              + "    <XMI.metamodel xmi.name=\"UML\" xmi.version=\""
                      + UML_VERSION + "\"/>";
    }

}

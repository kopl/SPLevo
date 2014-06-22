package org.splevo.test;

public class TestClass {
    private static void initializeSubsystems() {

        SubsystemUtility.initSubsystem(new InitClassDiagram());

        //@#$LPS-USECASEDIAGRAM:GranularityType:Statement
        SubsystemUtility.initSubsystem(new InitUseCaseDiagram());

        SubsystemUtility.initSubsystem(new InitUmlUI());

    }
}
package org.splevo.test;

public class TestClass {
    private static void initializeSubsystems() {

        SubsystemUtility.initSubsystem(new InitClassDiagram());

        SubsystemUtility.initSubsystem(new InitUmlUI());

    }
}
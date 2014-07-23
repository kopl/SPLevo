package org.splevo.tests.statements;

public class EnumAccesses {

    public void doSth() {
        Container.TestEnum var = Container.TestEnum.EnumItem1;
    }
}

class Container {

    public enum TestEnum {
        EnumItem1,
        EnumItem2
    }
}

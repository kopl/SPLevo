package org.splevo.tests.statements;

import java.util.Iterator;
import java.util.LinkedList;

public class ChangeInsideSiblingBlock {

    protected void doSth() {

        if(true) {
            1 + 1;
            2 + 2; // added

            if(true) {
                3 + 3;
                4 + 4; // added
            }
        }

    }

}
package org.qualog;

import junit.framework.TestCase;
import org.incava.lgtest.QlTimee;

public class TestLogTimer extends TestCase {
    public TestLogTimer(String name) {
        super(name);
    }

    public void test() {
        QlTimee qte = new QlTimee();
        qte.go();
    }    
}

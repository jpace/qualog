package org.qualog;

import junit.framework.TestCase;
import org.incava.lgtest.QlTimee;

public class LogTimerTest extends TestCase {
    public LogTimerTest(String name) {
        super(name);
    }

    public void test() {
        QlTimee qte = new QlTimee();
        qte.go();
    }    
}

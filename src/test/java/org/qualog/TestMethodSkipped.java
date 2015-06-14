package org.qualog;

import java.io.StringWriter;
import junit.framework.TestCase;

public class TestMethodSkipped extends LogTestCase {
    public TestMethodSkipped(String name) {
        super(name);
    }
    
    public void testFiltered() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 15, 5, 25, 17, true);
        new org.incava.lgtest.LgMethodFiltered().run();

        String expected = "" +
            "[LgMethodFilter-    11] {o.i.l.LgMethodFiltered   #run              } one: 1\n" +
            "[                   12] {                         #                 } pi: 3.14\n";
        
        System.out.println("sw: " + sw);
        
        compare(expected, sw.toString());
        
        assertStringsEqual(expected, sw.toString());
    }
}

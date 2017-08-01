package org.qualog;

import java.io.StringWriter;
import junit.framework.TestCase;

public class LogScalarTest extends LogTestCase {
    public LogScalarTest(String name) {
        super(name);
    }
    
    public void testScalar() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 15, 5, 25, 17, true);
        org.incava.lgtest.LgScalar.run();

        String expected = "" +
            "[LgScalar            5] {o.i.l.LgScalar           #run              } one: 1\n" +
            "[                    6] {                         #                 } pi: 3.14\n";
        
        System.out.println("sw: " + sw);
        
        compare(expected, sw.toString());
        
        assertStringsEqual(expected, sw.toString());
    }
}

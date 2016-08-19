package org.qualog;    

import java.io.StringWriter;
import junit.framework.TestCase;

public class TestLogException extends LogTestCase {
    public TestLogException(String name) {
        super(name);
    }
    
    public void testLogMethod() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 15, 5, 25, 17, true);
        org.incava.lgtest.LgException.run();

        String expected = "" +
            "[LgException         6] {o.i.l.LgException        #run              } re: java.lang.RuntimeException: rue\n" +
            "[                    8] {                         #                 } npe: java.lang.NullPointerException: nope\n";
        
        System.out.println("sw: " + sw);
        
        compare(expected, sw.toString());
        
        assertStringsEqual(expected, sw.toString());
    }
    
    public void testStackMethod() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 15, 5, 25, 17, true);
        org.incava.lgtest.LgException.execute();

        String expected = "" +
            "[LgException        13] {o.i.l.LgException        #execute          } iae: java.lang.IllegalArgumentException: badarg\n" +
            "[                   13] {                         #                 } iae[0]: org.incava.lgtest.LgException.execute(LgException.java:12)\n" +
            "[                   13] {                         #                 } iae[1]: org.qualog.TestLogException.testStackMethod(TestLogException.java:28)\n" +
            "[                   13] {                         #                 } iae[2]: sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
            "[                   13] {                         #                 } iae[3]: sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
            "";        
        
        System.out.println("<<<sw: " + sw);

        String[] expArray = expected.split("\n");
        String[] actArray = sw.toString().split("\n");

        for (int idx = 0; idx < expArray.length; ++idx) {
            assertEquals("line #" + idx, expArray[idx], actArray[idx]);
        }
    }
}

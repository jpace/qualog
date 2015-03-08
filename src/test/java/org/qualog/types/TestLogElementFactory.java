package org.qualog.types;

import java.io.StringWriter;
import junit.framework.TestCase;
import org.qualog.Log;
import org.qualog.LogTestCase;
import org.qualog.LogSplitter;

public class TestLogElementFactory extends LogTestCase {
    public TestLogElementFactory(String name) {
        super(name);
    }

    public void testCustomElement() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 25, 17, true);
        LogElementFactory.add(org.incava.lgtest.CustomElement.class, LogSplitter.class);
        org.incava.lgtest.LgCustomElement.run();

        String expected = "" +
            "[LgCustomElement              11] {o.i.l.LgCustomElement    #run              } ce: h,e,l,l,o, ,w,o,r,l,d\n" +
            "[                             14] {                         #                 } dce: f,o,o,:, ,b,a,r\n";

        System.out.println("sw: \n" + sw);
        
        compare(expected, sw.toString());
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testCustomElementCollection() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 25, 17, true);
        LogElementFactory.add(org.incava.lgtest.CustomElement.class, LogSplitter.class);
        org.incava.lgtest.LgCustomElement.runCollection();

        String expected = "" +
            "[LgCustomElement              31] {o.i.l.LgCustomElement    #runCollection    } numbers[English][0]: z,e,r,o\n" +
            "[                             31] {                         #                 } numbers[English][1]: f,o,o,:, ,o,n,e\n" +
            "[                             31] {                         #                 } numbers[English][2]: f,o,o,:, ,t,w,o\n" +
            "[                             31] {                         #                 } numbers[Spanish][0]: c,e,r,o\n" +
            "[                             31] {                         #                 } numbers[Spanish][1]: f,o,o,:, ,u,n,o\n" +
            "[                             31] {                         #                 } numbers[Spanish][2]: f,o,o,:, ,d,o,s\n";
        
        System.out.println("sw: \n" + sw);
        
        compare(expected, sw.toString());
        
        assertStringsEqual(expected, sw.toString());
    }
}

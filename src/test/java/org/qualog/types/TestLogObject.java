package org.qualog.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;
import junit.framework.TestCase;

public class TestLogObject extends TestCase {
    public TestLogObject(String name) {
        super(name);
    }
    
    public void testInspectSimple() {
        Object obj = new Double(3.14);
        Map<String, Object> attrs = LogObject.inspect(obj);
        Map<String, Object> exp = new TreeMap<String, Object>();
        exp.put("value", 3.14);
        
        assertEquals(exp, attrs);
    }

    public void testInspectTwo() {
        Map<String, Object> attrs = LogObject.inspect(new java.awt.Point(666, 777));
        Map<String, Object> exp = new TreeMap<String, Object>();
        exp.put("x", 666);
        exp.put("y", 777);
        
        assertEquals(exp, attrs);
    }

    public void testInspectSimpleIncludeStatic() {
        Object obj = new Double(3.14);
        Map<String, Object> attrs = LogObject.inspect(EnumSet.of(LogObject.InspectOptionType.INCLUDE_STATIC), obj);
        Map<String, Object> exp = new TreeMap<String, Object>();
        exp.put("value", 3.14);        
        exp.put("serialVersionUID", -9172774392245257468L); // at least as of 04 Nov 11

        exp.put("BYTES", Integer.valueOf(8));
        exp.put("POSITIVE_INFINITY", Double.POSITIVE_INFINITY);
        exp.put("NEGATIVE_INFINITY", Double.NEGATIVE_INFINITY);
        exp.put("NaN", Double.NaN);
        exp.put("MAX_VALUE", Double.MAX_VALUE);
        exp.put("MIN_NORMAL", Double.MIN_NORMAL);
        exp.put("MIN_VALUE", Double.MIN_VALUE);
        exp.put("MAX_EXPONENT", Double.MAX_EXPONENT);
        exp.put("MIN_EXPONENT", Double.MIN_EXPONENT);
        exp.put("SIZE", Double.SIZE);
        exp.put("TYPE", Double.TYPE);

        if (System.getProperty("java.version").startsWith("1.8")) {
            exp.put("BYTES", Double.BYTES);
        }
        
        assertEquals(exp, attrs);
    }

    public void testInspectTwoIncludeStatic() {
        Map<String, Object> attrs = LogObject.inspect(EnumSet.of(LogObject.InspectOptionType.INCLUDE_STATIC), new java.awt.Point(666, 777));
        Map<String, Object> exp = new TreeMap<String, Object>();
        exp.put("x", 666);
        exp.put("y", 777);
        exp.put("serialVersionUID", -5276940640259749850L);
        
        assertEquals(exp, attrs);
    }

    public void testInspectThree() {
        Map<String, Object> attrs = LogObject.inspect(new ArrayList<String>(Arrays.asList(new String[] { "this", "is", "a", "test" })));
        tr.Ace.log("attrs", attrs);
        Map<String, Object> exp = new TreeMap<String, Object>();
        exp.put("elementData", new Object[] { "this", "is", "a", "test" });
        exp.put("size", 4);
        
        assertEquals(exp.keySet(), attrs.keySet());
        assertEquals(exp.get("size"), attrs.get("size"));
        Object[] elmtData = (Object[])attrs.get("elementData");
        assertEquals(Arrays.asList(new String[] { "this", "is", "a", "test" }), Arrays.asList(elmtData));        
    }
}

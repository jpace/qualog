package org.qualog.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;
import junit.framework.TestCase;
import org.incava.ijdk.collect.Hash;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogObjectTest {
    @Test
    public void testInspectSimple() {
        Object obj = new Double(3.14);
        Map<String, Object> attrs = LogObject.inspect(obj);
        Map<String, Object> exp = Hash.of("value", 3.14);
        
        assertThat(attrs, equalTo(exp));
    }

    @Test
    public void testInspectTwo() {
        Map<String, Object> attrs = LogObject.inspect(new java.awt.Point(666, 777));
        Map<String, Object> exp = Hash.of("x", 666, "y", 777);
        
        assertThat(attrs, equalTo(exp));
    }

    @Test
    public void testInspectSimpleIncludeStatic() {
        Object obj = new Double(3.14);
        Map<String, Object> attrs = LogObject.inspect(EnumSet.of(LogObject.InspectOptionType.INCLUDE_STATIC), obj);
        Map<String, Object> exp = Hash.empty();
        exp.put("value", 3.14);        
        exp.put("serialVersionUID", -9172774392245257468L); // at least as of 1.8

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
        
        assertThat(attrs, equalTo(exp));
    }
}

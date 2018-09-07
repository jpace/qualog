package org.qualog.types;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Hash;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void linesFromObject(StringArray expected, String name, Object obj) {
        StringArray result = new Formatter().lines(name, obj);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromObject() {
        return paramsList(params(StringArray.of("abc: def"), "abc", "def"),
                          params(StringArray.of("abc: null"), "abc", null));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromArray(StringArray expected, String name, Object[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new String[] { }),
                          params(StringArray.of("abc[0]: d"), "abc", new String[] { "d" }),
                          params(StringArray.of("def[0]: d"), "def", new String[] { "d" }),
                          params(StringArray.of("abc[0]: d", "abc[1]: e"), "abc", new String[] { "d", "e" }));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromException(StringArray expected, String name, Throwable thr, Integer numFrames) {
        StringArray result = new Formatter().lines(name, thr, numFrames);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromException() {
        Throwable t = ExampleException.createNestedException("abc");
        
        return paramsList(params(StringArray.of("java.lang.Exception: abc"), "def", t, 0),
                          params(StringArray.of("java.lang.Exception: abc",
                                                "def[0]: org.qualog.types.ExampleException.createException(ExampleException.java:9)"), "def", t, 1),
                          params(StringArray.of("java.lang.Exception: abc",
                                                "def[0]: org.qualog.types.ExampleException.createException(ExampleException.java:9)",
                                                "def[1]: org.qualog.types.ExampleException.createNestedException(ExampleException.java:5)"), "def", t, 2));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <K, V> void linesFromMap(StringArray expected, String name, Map<K, V> map) {
        StringArray result = new Formatter().lines(name, map);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromMap() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", Hash.empty()),
                          params(StringArray.of("abc[x]: 1"), "abc", Hash.of("x", "1")),
                          params(StringArray.of("abc[x]: 1", "abc[y]: 2"), "abc", Hash.of("x", "1", "y", "2")));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void linesFromIterator(StringArray expected, String name, Iterator<T> iterator) {
        StringArray result = new Formatter().lines(name, iterator);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromIterator() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", StringArray.empty().iterator()),
                          params(StringArray.of("abc[0]: x"), "abc", StringArray.of("x").iterator()),
                          params(StringArray.of("abc[0]: x", "abc[1]: y"), "abc", StringArray.of("x", "y").iterator()));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void linesFromEnumeration(StringArray expected, String name, Enumeration<T> iterator) {
        StringArray result = new Formatter().lines(name, iterator);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromEnumeration() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new Vector<String>(StringArray.empty()).elements()),
                          params(StringArray.of("abc[0]: x"), "abc", new Vector<String>(StringArray.of("x")).elements()),
                          params(StringArray.of("abc[0]: x", "abc[1]: y"), "abc", new Vector<String>(StringArray.of("x", "y")).elements()));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromObjectArray(StringArray expected, String name, Object[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromObjectArray() {
        return paramsList(params(StringArray.of("abc[0]: 7", "abc[1]: 11", "abc[2]: 3"), "abc", new Integer[] { 7, 11, 3 }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromBooleanArray(StringArray expected, String name, boolean[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromBooleanArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new boolean[] { }),
                          params(StringArray.of("abc[0]: true", "abc[1]: false"), "abc", new boolean[] { true, false }),
                          params(StringArray.of("abc[0]: false", "abc[1]: true"), "abc", new boolean[] { false, true }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromByteArray(StringArray expected, String name, byte[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromByteArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new byte[] { }),
                          params(StringArray.of("abc[0]: 1", "abc[1]: 2"), "abc", new byte[] { 1, 2 }),
                          params(StringArray.of("abc[0]: 2", "abc[1]: 1"), "abc", new byte[] { 2, 1 }));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromCharArray(StringArray expected, String name, char[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromCharArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new char[] { }),
                          params(StringArray.of("abc[0]: a", "abc[1]: b"), "abc", new char[] { 'a', 'b' }),
                          params(StringArray.of("abc[0]: b", "abc[1]: a"), "abc", new char[] { 'b', 'a' }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromDoubleArray(StringArray expected, String name, double[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromDoubleArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new double[] { }),
                          params(StringArray.of("abc[0]: 1.2", "abc[1]: 3.4"), "abc", new double[] { 1.2, 3.4 }),
                          params(StringArray.of("abc[0]: 3.4", "abc[1]: 1.2"), "abc", new double[] { 3.4, 1.2 }));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromFloatArray(StringArray expected, String name, float[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromFloatArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new float[] { }),
                          params(StringArray.of("abc[0]: 1.2", "abc[1]: 3.4"), "abc", new float[] { 1.2F, 3.4F }),
                          params(StringArray.of("abc[0]: 3.4", "abc[1]: 1.2"), "abc", new float[] { 3.4F, 1.2F }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromIntArray(StringArray expected, String name, int[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromIntArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new int[] { }),
                          params(StringArray.of("abc[0]: 1", "abc[1]: 2"), "abc", new int[] { 1, 2 }),
                          params(StringArray.of("abc[0]: 2", "abc[1]: 1"), "abc", new int[] { 2, 1 }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromLongArray(StringArray expected, String name, long[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromLongArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new long[] { }),
                          params(StringArray.of("abc[0]: 1", "abc[1]: 2"), "abc", new long[] { 1L, 2L }),
                          params(StringArray.of("abc[0]: 2", "abc[1]: 1"), "abc", new long[] { 2L, 1L }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void linesFromShortArray(StringArray expected, String name, short[] ary) {
        StringArray result = new Formatter().lines(name, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLinesFromShortArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new short[] { }),
                          params(StringArray.of("abc[0]: 1", "abc[1]: 2"), "abc", new short[] { 1, 2 }),
                          params(StringArray.of("abc[0]: 2", "abc[1]: 1"), "abc", new short[] { 2, 1 }));
    }    
}

package org.qualog.types;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.Hash;
import org.incava.ijdk.collect.StringArray;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromObject(StringArray expected, String key, Object obj) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, obj);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromObject() {
        return paramsList(params(StringArray.of("abc: def"), "abc", "def"),
                          params(StringArray.of("abc: null"), "abc", null));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromObjectArrayWithLimit(StringArray expected, String key, Object[] ary, Integer limit) {
        StringArray result = StringArray.empty();
        new Formatter(result, limit).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromObjectArrayWithLimit() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new String[] { }, 1),
                          params(StringArray.of("abc[0]: d"), "abc", new String[] { "d" }, 1),
                          params(StringArray.empty(), "def", new String[] { "d" }, 0),
                          params(StringArray.of("abc[0]: d", "abc[1]: e"), "abc", new String[] { "d", "e" }, 2),
                          params(StringArray.of("abc[0]: d", "abc[1]: e"), "abc", new String[] { "d", "e" }, null),
                          params(StringArray.of("abc[0]: d"), "abc", new String[] { "d" }, 1),
                          params(StringArray.of("abc[0]: 7", "abc[1]: 11", "abc[2]: 3"), "abc", new Integer[] { 7, 11, 3 }, 4));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromObjectArrayWithoutLimit(StringArray expected, String key, Object[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromObjectArrayWithoutLimit() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new String[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: d"), "abc", new String[] { "d" }),
                          params(StringArray.of("def[0]: d"), "def", new String[] { "d" }),
                          params(StringArray.of("abc[0]: d", "abc[1]: e"), "abc", new String[] { "d", "e" }),
                          params(StringArray.of("abc[0]: 7", "abc[1]: 11", "abc[2]: 3"), "abc", new Integer[] { 7, 11, 3 }));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromExceptionWithLimit(StringArray expected, String key, Throwable thr, Integer numFrames) {
        StringArray result = StringArray.empty();
        new Formatter(result, numFrames).format(key, thr);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromExceptionWithLimit() {
        Throwable t = ExampleException.createNestedException("abc");
        
        return paramsList(params(StringArray.of("java.lang.Exception: abc"), "def", t, 0),
                          params(StringArray.of("java.lang.Exception: abc",
                                                "def[0]: org.qualog.types.ExampleException.createException(ExampleException.java:9)"), "def", t, 1),
                          params(StringArray.of("java.lang.Exception: abc",
                                                "def[0]: org.qualog.types.ExampleException.createException(ExampleException.java:9)",
                                                "def[1]: org.qualog.types.ExampleException.createNestedException(ExampleException.java:5)"), "def", t, 2));
    }
    
    @Ignore @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromExceptionWithoutLimit(StringArray expected, String key, Throwable thr) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, thr);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromExceptionWithoutLimit() {
        Throwable t = ExampleException.createNestedException("abc");
        
        return paramsList(params(StringArray.of("java.lang.Exception: abc",
                                                "def[0]: org.qualog.types.ExampleException.createException(ExampleException.java:9)",
                                                "def[1]: org.qualog.types.ExampleException.createNestedException(ExampleException.java:5)"), "def", t));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <K, V> void fromMapWithLimit(StringArray expected, String key, Map<K, V> map, Integer limit) {
        StringArray result = StringArray.empty();
        new Formatter(result, limit).format(key, map);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromMapWithLimit() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", Hash.empty(), 0),
                          params(StringArray.empty(), "abc", Hash.of("x", "1"), 0),
                          params(StringArray.of("abc[x]: 1"), "abc", Hash.of("x", "1"), 1),
                          params(StringArray.of("abc[x]: 1"), "abc", Hash.of("x", "1", "y", "2"), 1),
                          params(StringArray.of("abc[x]: 1", "abc[y]: 2"), "abc", Hash.of("x", "1", "y", "2"), 2));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <K, V> void fromMapWithoutLimit(StringArray expected, String key, Map<K, V> map) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, map);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromMapWithoutLimit() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", Hash.empty()),
                          params(StringArray.of("abc[x]: 1"), "abc", Hash.of("x", "1")),
                          params(StringArray.of("abc[x]: 1", "abc[y]: 2"), "abc", Hash.of("x", "1", "y", "2")));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromIterableWithLimit(StringArray expected, String key, Iterable<T> iterable, Integer limit) {
        StringArray result = StringArray.empty();
        new Formatter(result, limit).format(key, iterable);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromIterableWithLimit() {
        List<Object[]> pl = paramsList();

        pl.add(params(StringArray.of("abc[0]: ghi"),  "abc", StringArray.of("ghi"), 1));
        pl.add(params(StringArray.empty(),  "abc", StringArray.of("ghi"), 0));
        pl.add(params(StringArray.of("abc[0]: ghi", "abc[1]: jkl"),  "abc", StringArray.of("ghi", "jkl"), 2));
        pl.add(params(StringArray.of("abc[0]: ghi"),  "abc", StringArray.of("ghi", "jkl"), 1));
        
        return pl;
    }    
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromIterableWithoutLimit(StringArray expected, String key, Iterable<T> iterable) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, iterable);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromIterableWithoutLimit() {
        List<Object[]> pl = paramsList();

        pl.add(params(StringArray.of("abc[0]: ghi"),  "abc", StringArray.of("ghi")));
        pl.add(params(StringArray.of("abc[0]: ghi", "abc[1]: jkl"),  "abc", StringArray.of("ghi", "jkl")));
        
        return pl;
    }    
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromBooleanArray(StringArray expected, String key, boolean[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromBooleanArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new boolean[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: true", "abc[1]: false"), "abc", new boolean[] { true, false }),
                          params(StringArray.of("abc[0]: false", "abc[1]: true"), "abc", new boolean[] { false, true }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromByteArray(StringArray expected, String key, byte[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromByteArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new byte[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: 1", "abc[1]: 2"), "abc", new byte[] { 1, 2 }),
                          params(StringArray.of("abc[0]: 2", "abc[1]: 1"), "abc", new byte[] { 2, 1 }));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromCharArray(StringArray expected, String key, char[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromCharArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new char[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: a", "abc[1]: b"), "abc", new char[] { 'a', 'b' }),
                          params(StringArray.of("abc[0]: b", "abc[1]: a"), "abc", new char[] { 'b', 'a' }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromDoubleArray(StringArray expected, String key, double[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromDoubleArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new double[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: 1.2", "abc[1]: 3.4"), "abc", new double[] { 1.2, 3.4 }),
                          params(StringArray.of("abc[0]: 3.4", "abc[1]: 1.2"), "abc", new double[] { 3.4, 1.2 }));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromFloatArray(StringArray expected, String key, float[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromFloatArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new float[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: 1.2", "abc[1]: 3.4"), "abc", new float[] { 1.2F, 3.4F }),
                          params(StringArray.of("abc[0]: 3.4", "abc[1]: 1.2"), "abc", new float[] { 3.4F, 1.2F }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromIntArray(StringArray expected, String key, int[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromIntArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new int[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: 1", "abc[1]: 2"), "abc", new int[] { 1, 2 }),
                          params(StringArray.of("abc[0]: 2", "abc[1]: 1"), "abc", new int[] { 2, 1 }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromLongArray(StringArray expected, String key, long[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromLongArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new long[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: 1", "abc[1]: 2"), "abc", new long[] { 1L, 2L }),
                          params(StringArray.of("abc[0]: 2", "abc[1]: 1"), "abc", new long[] { 2L, 1L }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromShortArray(StringArray expected, String key, short[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromShortArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new short[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: 1", "abc[1]: 2"), "abc", new short[] { 1, 2 }),
                          params(StringArray.of("abc[0]: 2", "abc[1]: 1"), "abc", new short[] { 2, 1 }));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromNestedObjectArray(StringArray expected, String key, Object[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromNestedObjectArray() {
        List<Object[]> pl = paramsList();

        pl.add(params(StringArray.of("abc[0][0]: xyz"),  "abc", new Object[] { new String[] { "xyz" } }));
        pl.add(params(StringArray.of("abc[0][0]: true"), "abc", new Object[] { new boolean[] { true } }));
        pl.add(params(StringArray.of("abc[0][0]: 1"),    "abc", new Object[] { new byte[] { 1 } }));
        pl.add(params(StringArray.of("abc[0][0]: a"),    "abc", new Object[] { new char[] { 'a' } }));
        pl.add(params(StringArray.of("abc[0][0]: 1.2"),  "abc", new Object[] { new double[] { 1.2 } }));
        pl.add(params(StringArray.of("abc[0][0]: 1.2"),  "abc", new Object[] { new float[] { 1.2F } }));
        pl.add(params(StringArray.of("abc[0][0]: 1"),    "abc", new Object[] { new int[] { 1 } }));
        pl.add(params(StringArray.of("abc[0][0]: 1"),    "abc", new Object[] { new long[] { 1L } }));
        pl.add(params(StringArray.of("abc[0][0]: 1"),    "abc", new Object[] { new short[] { 1 } }));
        
        return pl;
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromNestedIterable(StringArray expected, String key, Object[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromNestedIterable() {
        List<Object[]> pl = paramsList();

        pl.add(params(StringArray.of("abc[0][0]: ghi"),  "abc", new Object[] { StringArray.of("ghi") }));
        pl.add(params(StringArray.of("abc[0][0]: ghi", "abc[0][1]: jkl"),  "abc", new Object[] { StringArray.of("ghi", "jkl") }));
        
        return pl;
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromNestedMap(StringArray expected, String key, Object[] ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromNestedMap() {
        List<Object[]> pl = paramsList();

        pl.add(params(StringArray.of("abc[0][one]: 1"),  "abc", new Object[] { Hash.of("one", "1") }));
        pl.add(params(StringArray.of("abc[0][one]: 1", "abc[0][two]: 2"),  "abc", new Object[] { Hash.of("one", "1", "two", "2") }));
        
        return pl;
    }
     
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromObjectArrayAsObject(StringArray expected, String key, Object ary) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromObjectArrayAsObject() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new String[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: d"), "abc", new String[] { "d" }),
                          params(StringArray.of("def[0]: d"), "def", new String[] { "d" }),
                          params(StringArray.of("abc[0]: d", "abc[1]: e"), "abc", new String[] { "d", "e" }),
                          params(StringArray.of("abc[0]: 7", "abc[1]: 11", "abc[2]: 3"), "abc", new Integer[] { 7, 11, 3 }));
    }    
}

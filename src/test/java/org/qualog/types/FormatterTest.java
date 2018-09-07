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
}

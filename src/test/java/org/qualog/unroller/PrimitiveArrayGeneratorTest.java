package org.qualog.unroller;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PrimitiveArrayGeneratorTest extends GeneratorTestCase {
    private PrimitiveArrayGenerator createPrimitiveArrayGenerator(StringArray result) {
        StringGenerator sg = createGenerator(result);
        return new PrimitiveArrayGenerator(sg);
    }    
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromBooleanArray(StringArray expected, String key, boolean[] ary) {
        StringArray result = StringArray.empty();
        createPrimitiveArrayGenerator(result).generate(key, ary);
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
        createPrimitiveArrayGenerator(result).generate(key, ary);
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
        createPrimitiveArrayGenerator(result).generate(key, ary);
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
        createPrimitiveArrayGenerator(result).generate(key, ary);
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
        createPrimitiveArrayGenerator(result).generate(key, ary);
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
        createPrimitiveArrayGenerator(result).generate(key, ary);
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
        createPrimitiveArrayGenerator(result).generate(key, ary);
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
        createPrimitiveArrayGenerator(result).generate(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromShortArray() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new short[] { }),
                          params(StringArray.of("abc: null"), "abc", null),
                          params(StringArray.of("abc[0]: 1", "abc[1]: 2"), "abc", new short[] { 1, 2 }),
                          params(StringArray.of("abc[0]: 2", "abc[1]: 1"), "abc", new short[] { 2, 1 }));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withFormat(StringArray expected, String format, String key, int[] ary) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(format, result);
        new PrimitiveArrayGenerator(sg).generate(key, ary);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithFormat() {
        return paramsList(params(StringArray.of("abc[0]: 1"), "%s: %s", "abc", new int[] { 1 }),
                          params(StringArray.of("<<abc[0]>>: {{1}}"), "<<%s>>: {{%s}}", "abc", new int[] { 1 }));
    }    
}

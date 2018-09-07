package org.qualog.types;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectArrayFactoryTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void toObjectArray(Object[] expected, Object arg) {
        ObjectArrayFactory saf = new ObjectArrayFactory();
        Object[] result = saf.toObjectArray(arg);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForToObjectArray() {
        return paramsList(params(new Object[] { 1, 2, 3 },         new Integer[] { 1, 2, 3 }),
                          params(new Object[] { "true", "false" }, new boolean[] { true, false }),
                          params(new Object[] { true, false },     new Boolean[] { true, false }),
                          params(new Object[] { "1", "2" },        new byte[] { 1, 2 }),
                          params(new Object[] { "a", "b" },        new char[] { 'a', 'b' }),
                          params(new Object[] { "1.2", "3.4" },    new double[] { 1.2, 3.4 }),
                          params(new Object[] { "1.2", "3.4" },    new float[] { 1.2F, 3.4F }),
                          params(new Object[] { "1", "2" },        new int[] { 1, 2 }),
                          params(new Object[] { "1", "2" },        new long[] { 1L, 2L }),
                          params(new Object[] { "1", "2" },        new short[] { 1, 2 }));
    }
}

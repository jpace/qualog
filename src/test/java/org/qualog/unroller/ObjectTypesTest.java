package org.qualog.unroller;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectTypesTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void isUndecorated(Boolean expected, Object obj) {
        Boolean result = new ObjectTypes().isUndecorated(obj);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForIsUndecorated() {
        return paramsList(params(true,  "abc"),
                          params(true,  new Double(1.0)),
                          params(false, new StringBuilder()));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void toStringObject(String expected, Object obj) {
        String result = new ObjectTypes().toString(obj);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForToStringObject() {
        List<Object[]> pl = paramsList();
        pl.add(params("def", "def"));
        pl.add(params("null", null));
        
        StringBuilder sb = new StringBuilder("xyz");
        pl.add(params(createExpectedString("xyz", sb), sb));

        return pl;
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void toStringObjectString(String expected, Object obj, String msg) {
        String result = new ObjectTypes().toString(obj, msg);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForToStringObjectString() {
        StringBuilder sb = new StringBuilder("xyz");
        return paramsList(params(createExpectedString("m1", sb), sb, "m1"),
                          params(createExpectedString("m2", sb), sb, "m2"));
    }

    private String createExpectedString(String msg, StringBuilder sb) {
        return msg + " (java.lang.StringBuilder) #" + Integer.toHexString(sb.hashCode());
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void addUndecoratedType(Boolean expected, Class<T> cls, Object obj) {
        ObjectTypes types = new ObjectTypes();
        types.addUndecoratedType(cls);
        Boolean result = types.isUndecorated(obj);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForAddUndecoratedType() {
        StringBuilder sb = new StringBuilder("xyz");
        return paramsList(params(true,  StringBuilder.class, new StringBuilder()),
                          params(true,  StringBuffer.class,  new StringBuffer()),
                          params(false, StringBuffer.class,  new StringBuilder()));
    }    
}

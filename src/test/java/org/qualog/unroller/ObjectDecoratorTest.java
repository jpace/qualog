package org.qualog.unroller;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectDecoratorTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void toStringObject(String expected, Object obj) {
        String result = new ObjectDecorator().toString(obj);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForToStringObject() {
        List<Object[]> pl = paramsList();
        
        StringBuilder sbl = new StringBuilder("abc");
        String exp = "abc (java.lang.StringBuilder) #" + Integer.toHexString(sbl.hashCode());
        pl.add(params(exp, sbl));

        StringBuffer sbf = new StringBuffer("def");
        exp = "def (java.lang.StringBuffer) #" + Integer.toHexString(sbf.hashCode());
        pl.add(params(exp, sbf));

        return pl;
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void toStringObjectString(String expected, Object obj, String msg) {
        String result = new ObjectDecorator().toString(obj, msg);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForToStringObjectString() {
        List<Object[]> pl = paramsList();
        
        StringBuilder sbl = new StringBuilder("abc");
        String exp = "m1 (java.lang.StringBuilder) #" + Integer.toHexString(sbl.hashCode());
        pl.add(params(exp, sbl, "m1"));

        exp = "m2 (java.lang.StringBuilder) #" + Integer.toHexString(sbl.hashCode());
        pl.add(params(exp, sbl, "m2"));

        return pl;
    }    
}

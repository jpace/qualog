package org.qualog.io;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;
import org.qualog.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StatementTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void init(Stack expStack, String expContextID, String expKey, Object expValue, Statement stmt) {
        assertThat(stmt.getStack(),     equalTo(expStack));
        assertThat(stmt.getContextID(), equalTo(expContextID));
        assertThat(stmt.getKey(),       equalTo(expKey));
        assertThat(stmt.getValue(),     equalTo(expValue));
    }
    
    private java.util.List<Object[]> parametersForInit() {
        Stack xs = new Stack();
        String xc = "c-x";
        String xk = "k-x";
        Statement x = new Statement(xs, xc, xk);

        Stack ys = new Stack();
        String yc = "c-y";
        String yk = "k-y";
        Object yv = new Long(13L);
        Statement y = new Statement(ys, yc, yk, yv);
        
        return paramsList(params(xs, xc, xk, Statement.NONE, x),
                          params(ys, yc, yk, yv, y));
    }
}

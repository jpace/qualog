package org.qualog.output;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class ClassNameTest extends Parameterized {
    @Test
    public void init() {
        ANSIColor color = null;
        StackElements stackElements = null;
        int classWidth = 1;
        ClassName cn = new ClassName(color, stackElements, classWidth);
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void asConcise(String expected, String className) {
        ClassName cn = new ClassName(null, null, 1);
        String result = cn.asConcise(className);
        assertThat(result, withContext(message("className", className), equalTo(expected)));
    }
    
    private Object[] parametersForAsConcise() {
        return params(params("a.d.g.Jkl", "abc.def.ghi.Jkl"),
                      params("a.d.Jkl", "abc.def.Jkl"),
                      params("a.Jkl", "abc.Jkl"));
    }
}

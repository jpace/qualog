package org.qualog.util;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;

public class StackTest extends Parameterized {
    @Test
    public void init() {
        Stack obj = new org.incava.qltest.Ql().methodOne();
        Array<StackTraceElement> elements = obj.getElements();
        StackTraceElement it = elements.get(1);
        assertThat(it.getClassName(), equalTo("org.incava.qltest.Ql"));
        assertThat(it.getMethodName(), equalTo("methodThree"));
        assertThat(it.getFileName(), equalTo("Ql.java"));
        assertThat(it.getLineNumber(), equalTo(15));

        it = elements.get(2);
        assertThat(it.getClassName(), equalTo("org.incava.qltest.Ql"));
        assertThat(it.getMethodName(), equalTo("methodTwo"));
        assertThat(it.getFileName(), equalTo("Ql.java"));
        assertThat(it.getLineNumber(), equalTo(11));

        it = elements.get(3);
        assertThat(it.getClassName(), equalTo("org.incava.qltest.Ql"));
        assertThat(it.getMethodName(), equalTo("methodOne"));
        assertThat(it.getFileName(), equalTo("Ql.java"));
        assertThat(it.getLineNumber(), equalTo(7));        
    }
}

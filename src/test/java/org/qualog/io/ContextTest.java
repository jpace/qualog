package org.qualog.io;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;
import org.qualog.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;

public class ContextTest extends Parameterized {
    @Test
    public void initKeyValue() {
        Stack stack = new Stack();
        String contextID = "ctx1";
        String key = "key1";
        Object value = new Long(13L);
        Context obj = new Context(stack, contextID, key, value);
        assertThat(obj.getStack(), equalTo(stack));
        assertThat(obj.getContextID(), equalTo(contextID));
        assertThat(obj.getKey(), equalTo(key));
        assertThat(obj.getValue(), equalTo(value));
    }

    @Test
    public void initKeyOnly() {
        Stack stack = new Stack();
        String contextID = "ctx1";
        String key = "key1";
        Context obj = new Context(stack, contextID, key);
        assertThat(obj.getStack(), equalTo(stack));
        assertThat(obj.getContextID(), equalTo(contextID));
        assertThat(obj.getKey(), equalTo(key));
        assertThat(obj.getValue(), equalTo(Context.NONE));
    }
}

package org.qualog.format;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;

public class LocationTest extends Parameterized {
    @Test
    public void init() {
        Location loc = new Location("abc", 1, "def", "ghi");
        assertThat(loc.getFileName(),   equalTo("abc"));
        assertThat(loc.getLineNumber(), equalTo(1));
        assertThat(loc.getClassName(),  equalTo("def"));
        assertThat(loc.getMethodName(), equalTo("ghi"));
    }
}

package org.qualog.writer;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import org.junit.Ignore;
import org.junit.Test;
import org.qualog.format.ContextIdFormatter;
import org.qualog.format.MessageFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;

public class LogWriterTest extends Parameterized {
    @Test
    public void stack() {
        StringArray lines = StringArray.empty();
        ContextIdFormatter formatter = new ContextIdFormatter("ctx-xyz");
        StringWriter stringWriter = new StringArrayWriter(lines);
        LogWriter lw = new LogWriter();
        lw.stack("k-abc", "v-def");
        lw.stack("k-ghi", Array.of(new StringBuilder("sbd-1"), new StringBuffer("sbf-2"), "str-3"));
        // assertThat(lines, equalTo(StringArray.of("k-abc: v-def")));
    }

    @Test
    public void log() {
        StringArray lines = StringArray.empty();
        ContextIdFormatter formatter = new ContextIdFormatter("ctx-xyz");
        // StringWriter stringWriter = new StringArrayWriter(lines);
        LogWriter lw = new LogWriter();
        lw.log("k-abc", "v-def");
        lw.log("k-ghi", "v-jkl");
        // assertThat(lines, equalTo(StringArray.of("ctx-xyz - k-abc: v-def")));
    }
    
}


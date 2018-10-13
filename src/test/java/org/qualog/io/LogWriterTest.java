package org.qualog.io;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import org.junit.Ignore;
import org.junit.Test;
import org.qualog.format.ContextStringFormatter;
import org.qualog.format.MessageFormatter;
import org.qualog.unroller.StringArrayWriter;
import org.qualog.unroller.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;

public class LogWriterTest extends Parameterized {
    // @Test @Ignore
    // public void stack() {
    //     StringArray lines = StringArray.empty();
    //     ContextStringFormatter formatter = new ContextStringFormatter("ctx-xyz");
    //     StringWriter stringWriter = new StringArrayWriter(lines);
    //     LogWriter lw = new LogWriter(messageFormatter, stringWriter);
    //     lw.stack("k-abc", "v-def");
    //     assertThat(lines, equalTo(StringArray.of("k-abc: v-def")));
    // }

    @Test
    public void log() {
        StringArray lines = StringArray.empty();
        ContextStringFormatter formatter = new ContextStringFormatter("ctx-xyz");
        StringWriter stringWriter = new StringArrayWriter(lines);
        LogWriter lw = new LogWriter(formatter, stringWriter);
        lw.log("k-abc", "v-def");
        assertThat(lines, equalTo(StringArray.of("ctx-xyz - k-abc: v-def")));
    }
    
}


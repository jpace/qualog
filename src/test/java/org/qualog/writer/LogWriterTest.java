package org.qualog.writer;

import java.io.PrintWriter;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;
import org.qualog.format.Formats;
import org.qualog.format.LineFormatter;
import org.qualog.output.StdOut;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogWriterTest {
    private LogWriter create() {
        Formats formats = new Formats();
        PrintWriter printWriter = new StdOut();
        LineFormatter lineFmt = new LineFormatter(null, formats.location());
        LineWriter lineWriter = new LineWriter("", lineFmt, printWriter);
        return new LogWriter(lineWriter, formats);
    }
    
    @Test
    public void stack() {
        StringArray lines = StringArray.empty();
        LogWriter lw = create();
        lw.stack("sk-abc", "sv-def");
        lw.stack("sk-ghi", Array.of(new StringBuilder("sbd-1"), new StringBuffer("sbf-2"), "str-3"));
        // assertThat(lines, equalTo(StringArray.of("k-abc: v-def")));
    }

    @Test
    public void log() {
        StringArray lines = StringArray.empty();
        LogWriter lw = create();
        lw.log("lk-abc", "lv-def");
        lw.log("lk-ghi", "lv-jkl");
        // assertThat(lines, equalTo(StringArray.of("ctx-xyz - k-abc: v-def")));
    }
}

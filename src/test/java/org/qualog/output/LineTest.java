package org.qualog.output;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;
import org.qualog.config.Configuration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.ExistsMatcher.exists;

public class LineTest extends Parameterized {
    @Test
    public void init() {
        String message = "m1";
        ItemColors colors = null;
        StackTraceElement stackElement = null;
        StackTraceElement previousStackElement = null;
        Configuration config = null;

        Line result = new Line(message, colors, stackElement, previousStackElement, config);
        assertThat(result, exists(true));
    }

    @Test
    public void getLine() {
        String message = "m1";
        ANSIColorList msgColors = new ANSIColorList();
        ANSIColor fileColor = null;
        ANSIColor classColor = null;
        ANSIColor methodColor = null;
        ItemColors colors = new ItemColors(msgColors, fileColor, classColor, methodColor);

        StackElements stackElements = stes(ste("a.B", "c", "B.java", 1),
                                           ste("d.E", "d", "E.java", 5));

        Configuration config = new Configuration();

        Line line = new Line(message, colors, stackElements, config);
        String result = line.getLine(false, true);
        assertThat(result, equalTo("[B                  1] {a.B                      #c              } m1"));
    }

    private StackTraceElement ste(String className, String methodName, String fileName, int lineNumber) {
        return new StackTraceElement(className, methodName, fileName, lineNumber);
    }

    private StackElements stes(StackTraceElement current, StackTraceElement previous) {
        return new StackElements(current, previous);
    }    
}

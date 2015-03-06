package org.qualog;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;
import org.qualog.output.OutputType;

public class LogTestCase extends TestCase {
    public LogTestCase(String name) {
        super(name);
    }

    public void compare(String expected, String actual) {
        int i = 0;
        while (i < expected.length() && i < actual.length()) {
            // System.err.println("char[" + i + "]: " + expected.charAt(i) + " <=> " + actual.charAt(i));
            if (expected.charAt(i) != actual.charAt(i)) {
                System.err.println("mismatch");
                return;
            }
            ++i;
        }
        if (i < expected.length()) {
            System.err.println("i not at end of expected");
        }
        else if (i < actual.length()) {
            System.err.println("i not at end of actual");
        }
    }

    public StringWriter reset(OutputType type, 
                              Level level,
                              int fileWidth,
                              int lineWidth,
                              int classWidth,
                              int functionWidth,
                              boolean columns) {
        Log.clear();
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        Log.setOut(pw);

        Log.setOutput(type, level);

        Configuration cfg = Log.getConfiguration();

        cfg.setFileWidth(fileWidth);
        cfg.setLineWidth(lineWidth);
        cfg.setClassWidth(classWidth);
        cfg.setFunctionWidth(functionWidth);
        Log.setColumns(columns);

        return sw;
    }

    public void assertStringsEqual(String exp, String act) {
        if (!exp.equals(act)) {
            System.err.println("expected output:\n" + exp);
            System.err.println("log output:\n" + act);

            String[] expLines = exp.split("\n");
            String[] actLines = act.split("\n");

            int nLines = Math.max(expLines.length, actLines.length);
            for (int li = 0; li < nLines; ++li) {
                String expLine = li < expLines.length ? expLines[li] : null;
                String actLine = li < actLines.length ? actLines[li] : null;
                
                if (!expLine.equals(actLine)) {
                    compare(expLine, actLine);
                }

                assertEquals("line[" + li + "]", expLine, actLine);
            }
        }
    }

    public void testNothing() {
    }
}

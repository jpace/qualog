package org.qualog;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.Assert;
import org.qualog.config.Configuration;
import org.qualog.config.WidthConfig;
import org.qualog.output.OutputType;

public class LogUtil {
    public static StringWriter reset(OutputType type, 
                                     Level level,
                                     int fileWidth,
                                     int lineWidth,
                                     int classWidth,
                                     int functionWidth,
                                     boolean useColumns) {
        Log.clear();
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        Log.setOut(pw);

        Log.setOutput(type, level);

        Configuration cfg = Log.getConfiguration();
        WidthConfig widths = cfg.getWidthConfig();

        widths.setFileWidth(fileWidth);
        widths.setLineWidth(lineWidth);
        widths.setClassWidth(classWidth);
        widths.setFunctionWidth(functionWidth);

        cfg.setUseColumns(useColumns);

        return sw;
    }

    public static void compare(String expected, String actual) {
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

    public static void assertStringsEqual(String exp, String act) {
        // System.out.println(act);
        
        if (!exp.equals(act)) {
            System.err.println("expected output:\n" + exp);
            System.err.println("log output:\n" + act);

            String[] expLines = exp.split("\n");
            String[] actLines = act.split("\n");

            int nLines = Math.max(expLines.length, actLines.length);
            for (int li = 0; li < nLines; ++li) {
                String expLine = li < expLines.length ? expLines[li] : null;
                String actLine = li < actLines.length ? actLines[li] : null;

                Assert.assertNotNull("expLine[" + li + "]", expLine);
                Assert.assertNotNull("actLine[" + li + "]", actLine);
                
                if (!expLine.equals(actLine)) {
                    compare(expLine, actLine);
                }

                Assert.assertEquals("line[" + li + "]", expLine, actLine);
            }
        }
    }
}

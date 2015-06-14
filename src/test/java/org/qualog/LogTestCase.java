package org.qualog;

import java.io.StringWriter;
import junit.framework.TestCase;
import org.qualog.output.OutputType;

public class LogTestCase extends TestCase {
    public LogTestCase(String name) {
        super(name);
    }

    public void compare(String expected, String actual) {
        LogUtil.compare(expected, actual);
    }

    public StringWriter reset(OutputType type, 
                              Level level,
                              int fileWidth,
                              int lineWidth,
                              int classWidth,
                              int functionWidth,
                              boolean useColumns) {
        return LogUtil.reset(type, level, fileWidth, lineWidth, classWidth, functionWidth, useColumns);
    }

    public void assertStringsEqual(String exp, String act) {
        LogUtil.assertStringsEqual(exp, act);
    }

    public void testNothing() {
    }
}

package org.qualog;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class LogSqueezerTest extends TestCase {
    public LogSqueezerTest(String name) {
        super(name);
    }

    public void testAll() {
        String str = "this is a test";
        assertEquals("ths s  tst", LogSqueezer.squeeze(str));
    }
    
}

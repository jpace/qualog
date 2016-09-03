package org.qualog;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestLogSqueezer extends TestCase {
    public TestLogSqueezer(String name) {
        super(name);
    }

    public void testAll() {
        String str = "this is a test";
        assertEquals("ths s  tst", LogSqueezer.squeeze(str));
    }
    
}

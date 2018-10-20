package org.qualog.writer;

public class StdoutWriter implements StringWriter {
    public void write(String msg) {
        System.out.println(msg);
    }
}

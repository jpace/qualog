package org.qualog.output;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class StdOut extends PrintWriter {
    public StdOut() {
        super(new PrintStream(new FileOutputStream(FileDescriptor.out)), true);
    }
}

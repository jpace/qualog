package org.qualog;

import java.io.PrintWriter;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.Hash;
import org.incava.ijdk.collect.StringArray;
import org.qualog.format.Formats;
import org.qualog.format.LineFormatter;
import org.qualog.writer.LineWriter;
import org.qualog.writer.LogWriter;
import org.qualog.output.StdOut;

public class Logs {
    private static Logs instance = null;

    public static Logs getInstance() {
        if (instance == null) {
            instance = new Logs();
        }
        return instance;
    }

    private final Hash<String, LogWriter> writers;
    
    public Logs() {
        this.writers = Hash.empty();
    }

    public LogWriter getWriter(String name) {
        LogWriter writer = writers.get(name);
        return writer == null ? addWriter(name) : writer;
    }

    public boolean hasWriter(String name) {
        LogWriter writer = writers.get(name);
        return writer != null;
    }

    /**
     * Adds a writer, using the default formats, standard output, and no context ID.
     */
    public LogWriter addWriter(String name) {
        Formats formats = new Formats(null, Formats.LOCATION, Formats.LINE, Formats.MESSAGE);
        return addWriter(name, formats, new StdOut(), "");
    }

    /**
     * Adds a writer, using the default formats and standard output.
     */
    public LogWriter addWriter(String name, Formats formats, PrintWriter printWriter, String contextId) {
        LineFormatter lineFmt = new LineFormatter(formats.contextId(), formats.location());
        LineWriter lineWriter = new LineWriter(contextId, lineFmt, printWriter);
        LogWriter writer = new LogWriter(lineWriter, formats);
        writers.put(name, writer);
        return writer;
    }
}

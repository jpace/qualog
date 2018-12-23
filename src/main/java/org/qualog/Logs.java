package org.qualog;

import java.io.PrintWriter;
import org.incava.ijdk.collect.Hash;
import org.qualog.format.Formats;
import org.qualog.format.LineFormatter;
import org.qualog.format.MessageFormatter;
import org.qualog.output.StdOut;
import org.qualog.writer.LineWriter;
import org.qualog.writer.LogWriter;

public class Logs {
    private static Logs instance = null;

    public static Logs getInstance() {
        if (instance == null) {
            instance = new Logs();
        }
        return instance;
    }

    private final Hash<String, Logger> loggers;
    
    public Logs() {
        this.loggers = Hash.empty();
    }

    public Logger getLogger(String name) {
        Logger logger = loggers.get(name);
        return logger == null ? addLogger(name) : logger;
    }

    public boolean hasLogger(String name) {
        Logger logger = loggers.get(name);
        return logger != null;
    }

    /**
     * Adds or replaces a logger, using the default formats, standard output, and no context ID.
     *
     * @param name the logger name
     * @return the new logger
     */
    public Logger addLogger(String name) {
        Formats formats = new Formats(null, Formats.LOCATION, Formats.LINE, Formats.MESSAGE);
        return addLogger(name, formats, new StdOut(), "");
    }

    /**
     * Adds or replaces a logger, using the given key/value format (and otherwise default formats),
     * standard output, and no context ID.
     *
     * @param name the logger name
     * @param keyValueFormat the format for key/value combinations in messages
     * @return the new logger
     */
    public Logger addLogger(String name, String keyValueFormat) {
        Formats formats = new Formats(null, Formats.LOCATION, Formats.LINE, new MessageFormatter(keyValueFormat, "%s"));
        return addLogger(name, formats, new StdOut(), "");
    }    

    /**
     * Adds or replaces a logger, using the default formats and standard output.
     *
     * @param name the logger name
     * @param formats the formats for output
     * @param printWriter for destination output
     * @param contextId the context ID of the new logger
     * @return the new logger
     */
    public Logger addLogger(String name, Formats formats, PrintWriter printWriter, String contextId) {
        LineFormatter lineFmt = new LineFormatter(formats.contextId(), formats.location());
        LineWriter lineWriter = new LineWriter(contextId, lineFmt, printWriter);
        Logger logger = createLogger(lineWriter, formats);
        loggers.put(name, logger);
        return logger;
    }

    /**
     * Creates a logger, and does not add it to the set. This is to allow overriding of the default
     * logger class.
     *
     * @param lineWriter the writer of logging lines
     * @param formats the formats
     * @return the new logger
     */
    public Logger createLogger(LineWriter lineWriter, Formats formats) {
        return new Logger(lineWriter, formats);
    }
}

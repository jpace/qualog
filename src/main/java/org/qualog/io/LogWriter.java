package org.qualog.io;

import org.qualog.format.MessageFormatter;
import org.qualog.unroller.Generator;
import org.qualog.unroller.StringArrayWriter;
import org.qualog.unroller.StringGenerator;
import org.qualog.unroller.StringWriter;

public class LogWriter {
    private final Generator generator;

    public LogWriter(MessageFormatter messageFormatter, StringWriter writer) {
        this(new Generator(new StringGenerator(messageFormatter, writer)));
    }

    public LogWriter(Generator generator) {
        this.generator = generator;
    }

    public boolean stack(String key, Object value) {
        generator.format(key, value);
        return true;
    }

    public boolean log(String key, Object value) {
        generator.format(key, value);
        return true;
    }
}

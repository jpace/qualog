package org.qualog;

import org.qualog.format.Fields;
import org.qualog.format.Formats;
import org.qualog.unroller.Generator;
import org.qualog.writer.LineWriter;
import org.qualog.writer.LogWriter;
import org.qualog.writer.Statement;

/**
 * A logger that filters based on verbosity level.
 */
public class Logger extends LogWriter {
    private Level level;

    public Logger(Level level, LineWriter lineWriter, Formats formats) {
        super(lineWriter, formats);
        
        this.level = level;
    }

    public Logger(Level level, Generator generator, LineWriter lineWriter, Fields fields) {
        super(generator, lineWriter, fields);
        
        this.level = level;
    }    

    public Logger(LineWriter lineWriter, Formats formats) {
        this(new Level(0), lineWriter, formats);
    }

    public Logger(Generator generator, LineWriter lineWriter, Fields fields) {
        this(new Level(0), generator, lineWriter, fields);
    }    

    public void setVerbose() {
        setVerbose(true);
    }

    public void setVerbose(boolean verbose) {
        this.level = new Level(verbose ? 5 : 0);
    }

    public boolean isVerbose() {
        return this.level.compareTo(new Level(5)) >= 0;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean stack(Integer depth, Statement stmt) {
        return isVerbose() ? super.stack(depth, stmt) : true;
    }
}

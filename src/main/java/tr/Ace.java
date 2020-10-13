package tr;

import java.util.EnumSet;
import org.qualog.LegacyLogger;
import org.qualog.Level;
import org.qualog.Logger;
import org.qualog.Logs;
import org.qualog.output.ANSIColor;
import org.qualog.writer.Statement;

/**
 * Short method and field names.
 *
 * Allows name of tr.Ace.XXX("...").
 */
public class Ace extends LegacyLogger {
    static class Delegate implements LegacyLogger.LegacyDelegate {
        public boolean stack(Level level, EnumSet<ANSIColor> msgColors, String name, Object obj, int numFrames) {
            String nameStr = colorize(msgColors, name);
            return Ace.stack(numFrames, nameStr, obj);
        }
                
        public boolean log(Level level, EnumSet<ANSIColor> msgColors, String name, Object obj) {
            String nameStr = colorize(msgColors, name);
            return Ace.log(nameStr, obj);
        }

        public String colorize(EnumSet<ANSIColor> msgColors, String str) {
            if (msgColors == null) {
                return str;
            }
            else {
                String cstr = str;
                for (ANSIColor it : msgColors) {
                    cstr = it.toString(cstr);
                }
                return cstr;
            }
        }
    }

    private static final Logs logs;
    private static Logger logger;

    static {
        logs = new Logs();
        logger = logs.getLogger("app");

        LegacyLogger.LegacyDelegate delegate = new Delegate();
        LegacyLogger.setDelegate(delegate);
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setVerbose(boolean verbose) {
        logger.setVerbose(verbose);
    }

    public static void setFormat(String format) {
        // this is only the message format
        logger = logs.addLogger("app", format);
    }

    public static boolean stack(Integer depth, String key, Object value) {
        return logger.stack(depth, key, value);
    }

    public static boolean stack(Integer depth, String msg) {
        return logger.stack(depth, msg);
    }

    public static boolean stack(Integer depth, Statement stmt) {
        return logger.stack(depth, stmt);
    }

    public static boolean stack(String key, Object value) {
        return logger.stack(key, value);
    }

    public static boolean stack(String msg) {
        return logger.stack(msg);
    }

    public static boolean stack(Statement stmt) {
        return logger.stack(stmt);
    }

    public static boolean log(String key, Object value) {
        return logger.log(key, value);
    }

    public static boolean log(String msg) {
        return logger.log(msg);
    }

    public static boolean log(Statement stmt) {
        return logger.log(stmt);
    }    
}

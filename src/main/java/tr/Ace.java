package tr;

import org.qualog.Logs;
import org.qualog.Logger;
import org.qualog.writer.Statement;

/**
 * Short method and field names.
 *
 * Allows name of tr.Ace.XXX("...").
 */
public class Ace {
    private static Logger logger = null;

    static {
        logger = Logs.getInstance().getLogger("app");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setVerbose(boolean verbose) {
        logger.setVerbose(verbose);
    }

    public static void setFormat(String format) {
        // this is only the message format
        logger = Logs.getInstance().addLogger("app", format);
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

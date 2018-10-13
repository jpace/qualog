package org.qualog.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.incava.ijdk.lang.ICore;

public class TimestampStringFormatter implements StringFormatter {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String DEFAULT_LINE_FORMAT = "%s %s";
    
    private final StringFormatter strFormat;
    private final String lineFormat;
    private final DateFormat dateFormat;

    public TimestampStringFormatter() {
        this(new MessageFormatter(), DEFAULT_LINE_FORMAT, DEFAULT_DATE_FORMAT);
    }

    public TimestampStringFormatter(String lineFormat) {
        this(new MessageFormatter(), lineFormat, null);
    }

    public TimestampStringFormatter(String lineFormat, String dateFormat) {
        this(new MessageFormatter(), lineFormat, dateFormat);
    }

    public TimestampStringFormatter(StringFormatter strFormat, String lineFormat, String dateFormat) {
        this.strFormat = strFormat;
        
        this.lineFormat = ICore.or(lineFormat, DEFAULT_LINE_FORMAT);
        this.dateFormat = new SimpleDateFormat(ICore.or(dateFormat, DEFAULT_DATE_FORMAT));
    }
    
    public String format(String key, String value) {
        String msgStr = strFormat.format(key, value);
        String dstr = getDateString();
        return String.format(this.lineFormat, dstr, msgStr);
    }
    
    public String format(String msg) {
        String msgStr = strFormat.format(msg);
        String dstr = getDateString();
        return String.format(this.lineFormat, dstr, msg);
    }

    private String getDateString() {
        Date now = new Date();
        return dateFormat.format(now);
    }
}

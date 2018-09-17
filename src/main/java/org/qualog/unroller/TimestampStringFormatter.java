package org.qualog.unroller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampStringFormatter extends StringFormatter {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String DEFAULT_LINE_FORMAT = "%s %s: %s";
    
    private final String lineFormat;
    private final DateFormat dateFormat;

    public TimestampStringFormatter() {
        this(DEFAULT_FORMAT);
    }

    public TimestampStringFormatter(String msgFormat) {
        super(msgFormat);
        
        this.lineFormat = "%s " + msgFormat;
        this.dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    }
    
    public String format(String key, String value) {
        String dstr = getDateString();
        return String.format(this.lineFormat, dstr, key, value);
    }
    
    public String format(String msg) {
        String dstr = getDateString();
        return dstr + " " + msg;
    }

    private String getDateString() {
        Date now = new Date();
        return dateFormat.format(now);
    }
}

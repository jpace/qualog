package org.qualog.output;

/**
 * Provides constants that produce colorized output on ANSI terminals.
 */
public enum ANSIColor {
    NONE(0),
    RESET(0),
    BOLD(1),
    UNDERSCORE(4),
    UNDERLINE(4),
    BLINK(5),
    REVERSE(7),
    CONCEALED(8),
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    MAGENTA(35),
    CYAN(36),
    WHITE(37),
    ON_BLACK(40),
    ON_RED(41),
    ON_GREEN(42),
    ON_YELLOW(43),
    ON_BLUE(44),
    ON_MAGENTA(45),
    ON_CYAN(46),
    ON_WHITE(47);

    private final String str;

    ANSIColor(int n) {
        //$$$ todo: make this OS and environment dependent
        StringBuilder sb = new StringBuilder();
        sb.append((char)27);
        sb.append('[');
        sb.append(n);
        sb.append('m');
        
        this.str = sb.toString();
    }

    /**
     * Returns the color as the escape string.
     *
     * @return the escape string
     */
    public String toString() {
        return this.str;
    }

    /**
     * Wraps the given string in this color, appending NONE (reset).
     * 
     * @param str the string to wrap
     * @return the wrapped string
     */
    public String toString(String str) {
        StringBuffer sb = new StringBuffer(this.str);
        sb.append(str);
        sb.append(NONE);
        return sb.toString();
    }
}

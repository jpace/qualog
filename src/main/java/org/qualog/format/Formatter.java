package org.qualog.format;

public class Formatter {
    public String format(String str, int width, boolean leftJustify) {
        return format(str, width, leftJustify, null);
    }

    public String format(String str, int width, boolean leftJustify, String snipStr) {
        int len = str.length();
        int diff = width - len;
        
        if (diff == 0) {
            return str;
        }
        else if (diff > 0) {
            int insertPoint = leftJustify ? len : 0;
            return insert(str, insertPoint, diff, " ");
        }
        else if (snipStr == null) {
            return str.substring(0, width);
        }
        else {
            StringBuilder sb = new StringBuilder(width);
            sb.append(str.substring(0, len - 1 - snipStr.length()));
            sb.append(snipStr);
            return sb.toString();
        }
    }

    public String insert(String str, int insertPoint, int nTimes, String t) {
        int size = str.length() + nTimes * t.length();
        StringBuilder sb = new StringBuilder(size);
        sb.append(str);
        for (int i = 0; i < nTimes; ++i) {
            sb.insert(insertPoint, t);
        }
        return sb.toString();
    }
}

package org.qualog;

import org.incava.ijdk.lang.StringExt;
import org.qualog.output.ItemColors;
import org.qualog.types.LogElement;
import org.qualog.types.ElementParams;

public class LogSqueezer extends LogElement {
    public static String squeeze(String str) {
        return str.replaceAll("[aeiou]", "");
    }
    
    public LogSqueezer(ElementParams params, Object obj) {
        super(params, squeeze(String.valueOf(obj)));
    }
}

package org.qldemo;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public void announce() {
        String[] ary = new String[] { "I", "am", "an", "array" };
        tr.Ace.log("ary", ary);
    }

    public void disclose() {
        List<String> list = Arrays.asList("And", "I'm", "a", "list");
        tr.Ace.log("list", list);
    }

    public void blather() {
        speak();
    }

    public void speak() {
        utter();
    }

    public void utter() {
        int value = 317;
        tr.Ace.stack("value", value);
    }
}

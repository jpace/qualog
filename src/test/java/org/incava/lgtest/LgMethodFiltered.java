package org.incava.lgtest;

import java.util.*;

public class LgMethodFiltered {
    public void log(String msg, Object obj) {
        tr.Ace.log(msg, obj);
    }

    public void run() {
        log("one", 1);
        log("pi", 3.14);
    }
}

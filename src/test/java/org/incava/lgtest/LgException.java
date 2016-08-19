package org.incava.lgtest;

public class LgException {
    public static void run() {
        Exception re = new RuntimeException("rue");
        tr.Ace.log("re", re);
        Exception npe = new NullPointerException("nope");
        tr.Ace.log("npe", npe);
    }
    
    public static void execute() {
        Exception iae = new IllegalArgumentException("badarg");
        tr.Ace.stack("iae", iae);
    }
}

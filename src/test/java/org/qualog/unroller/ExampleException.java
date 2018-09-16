package org.qualog.unroller;

public class ExampleException {
    public static Throwable createNestedException(String msg) {
        return createException(msg);
    }
    
    public static Throwable createException(String msg) {
        return new Exception(msg);
    }
}

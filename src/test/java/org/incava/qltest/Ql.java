package org.incava.qltest;

import org.qualog.util.Stack;

public class Ql {
    public Stack methodOne() {
        return methodTwo();
    }

    public Stack methodTwo() {
        return methodThree();
    }

    public Stack methodThree() {
        return new Stack();
    }    
}

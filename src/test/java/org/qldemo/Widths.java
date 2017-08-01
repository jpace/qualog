package org.qldemo;

public class Widths {
    public void shortName() {
        tr.Ace.log("this", this);
    }
    
    public void aFarLongerNameThatWillBeSnipped() {
        tr.Ace.log("this", this);
    }
    
    public void run() {
        shortName();
        aFarLongerNameThatWillBeSnipped();
    }
}

package org.qualog.output;

import org.incava.ijdk.lang.Strings;
import org.junit.Assert;
import org.junit.Test;

public class ItemTest {
    public void shortName() {
        tr.Ace.log("this", this);
    }
    
    public void aFarLongerNameThatWillBeSnipped() {
        tr.Ace.log("this", this);
    }
    
    @Test
    public void testGetSnipped() {
        tr.Ace.setVerbose(true);
        String str = "abcdef";
        int width = 5;
        String result = Strings.snip(str, width);
        tr.Ace.log("result", result);

        new org.qldemo.Widths().run();
    }
}

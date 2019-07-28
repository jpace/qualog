package org.qualog.output;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;

public class ItemColorsTest {
    @Test
    public void init() {
        ANSIColorList msgColors = new ANSIColorList();
        ANSIColor fileColor     = ANSIColor.RED;
        ANSIColor classColor    = ANSIColor.GREEN;
        ANSIColor methodColor   = ANSIColor.BLUE;
        ItemColors ic           = new ItemColors(msgColors, fileColor, classColor, methodColor);
        
        assertThat(ic.getMessageColors(), sameInstance(msgColors));
        assertThat(ic.getFileColor(),     sameInstance(fileColor));
        assertThat(ic.getClassColor(),    sameInstance(classColor));
        assertThat(ic.getMethodColor(),   sameInstance(methodColor));
    }
}

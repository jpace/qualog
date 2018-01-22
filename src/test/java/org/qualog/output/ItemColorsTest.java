package org.qualog.output;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;

public class ItemColorsTest extends Parameterized {
    @Test
    public void init() {
        ANSIColorList msgColors = null;
        ANSIColor fileColor = null;
        ANSIColor classColor = null;
        ANSIColor methodColor = null;
        ItemColors ic = new ItemColors(msgColors, fileColor, classColor, methodColor);
    }
}

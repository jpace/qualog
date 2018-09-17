package org.qualog.unroller;

import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;

public class GeneratorTestCase extends Parameterized {
    public StringGenerator createGenerator(String format, StringArray lines) {
        return new StringGenerator(new StringFormatter(format), new StringArrayWriter(lines));
    }    
    
    public StringGenerator createGenerator(StringArray lines) {
        return new StringGenerator(new StringFormatter(StringFormatter.DEFAULT_FORMAT), new StringArrayWriter(lines));
    }
}

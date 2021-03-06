package org.qualog.unroller;

import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.qualog.format.MessageFormatter;
import org.qualog.writer.StringArrayWriter;

public class GeneratorTestCase extends Parameterized {
    public StringGenerator createGenerator(String keyValueFormat, StringArray lines) {
        return new StringGenerator(new MessageFormatter(keyValueFormat, null), new StringArrayWriter(lines));
    }    
    
    public StringGenerator createGenerator(StringArray lines) {
        return new StringGenerator(new MessageFormatter(), new StringArrayWriter(lines));
    }
}

package org.qualog.types;

import java.util.Collections;
import java.util.Enumeration;
import org.qualog.Level;
import org.qualog.output.ItemColors;

/**
 * Wraps Enumerations for output.
 */
public class LogEnumeration extends LogCollection {
    public LogEnumeration(ElementParams params, Enumeration<?> en) {
        super(params, Collections.list(en));
    }
}

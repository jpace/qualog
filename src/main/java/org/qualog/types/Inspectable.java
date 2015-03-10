package org.qualog.types;

import java.util.List;

/**
 * An object that can be inspected.
 */
public interface Inspectable {
    /**
     * Returns a list of the strings summarizing the state of this object.
     */
    public List<String> inspect();
}
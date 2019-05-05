package org.qualog.output;

/**
 * The current and previous stack trace elements.
 */
public class StackElements {
    private final StackTraceElement current;
    private final StackTraceElement previous;
    
    /**
     * Creates a pair of elements.
     *
     * @param current the current element
     * @param previous the previous element
     */
    public StackElements(StackTraceElement current, StackTraceElement previous) {
        this.current = current;
        this.previous = previous;
    }

    /**
     * Returns the current stack trace element
     *
     * @return the current element
     */
    public StackTraceElement getCurrent() {
        return current;
    }

    /**
     * Returns the previous stack trace element
     *
     * @return the previous element
     */
    public StackTraceElement getPrevious() {
        return previous;
    }

    /**
     * Returns a new StackTrace with its previous element as this object's current, and "current" as
     * the new current element.
     *
     * @param newCurrent the new current element
     * @return the newly created set of stack elements
     */
    public StackElements create(StackTraceElement newCurrent) {
        return new StackElements(newCurrent, this.current);
    }
}

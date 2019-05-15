package org.qualog.output;

import org.incava.ijdk.collect.Iterate;
import org.incava.ijdk.lang.Objects;
import org.incava.ijdk.lang.Strings;

public abstract class Item {
    private final ANSIColorList colors;
    private final StackElements stackElements;

    protected final Integer width;
    
    public Item(ANSIColor color, StackElements stackElements, Integer width) {
        this(color == null ? null : new ANSIColorList(color), stackElements, width);
    }

    public Item(ANSIColorList colors, StackElements stackElements, Integer width) {
        this.colors = colors;
        this.stackElements = stackElements;
        this.width = width;
    }

    /**
     * Returns the value, as this item is represented in the logging line.
     *
     * @param stackElement the element from which to extract the value
     * @return the value for the stack element
     */
    protected abstract Object getValue(StackTraceElement stackElement);

    protected ANSIColorList getColors() {
        return colors;
    }

    /**
     * Returns the value from the current stack element, as this item represents in the logging
     * line.
     *
     * @return the value for the stack element
     */
    public Object getValue() {
        return getValue(stackElements.getCurrent());
    }

    /**
     * Returns whether the element is repeated from the previous one.
     *
     * @return whether the item is repeated
     */
    public boolean isRepeated() {
        return stackElements.getPrevious() != null && Objects.equal(getStackField(stackElements.getPrevious()), getStackField(stackElements.getCurrent()));
    }

    public String getSnipped(String str) {
        return Strings.snip(str, width);
    }

    public int getWidth() {
        return width;
    }

    public boolean justifyLeft() {
        return true;
    }

    public boolean snipIfLong() {
        return true;
    }

    /**
     * Returns the element in the stack that this item represents.
     *
     * @param stackElement the element to check
     * @return the field in the stack element
     */
    public abstract String getStackField(StackTraceElement stackElement);

    public String getFormatted() {
        ANSIColorList colors = isRepeated() ? null : this.colors;
        
        Object value = getValue(stackElements.getCurrent());
        String str = String.valueOf(value);
        if (width == null) {    
            return colors == null ? str : colors.toString(str);
        }
        
        int nSpaces = 0;
        int strlen = str.length();
        if (snipIfLong() && strlen > width) {
            str = Strings.snip(str, width);
        }
        else {
            nSpaces = width - strlen;
        }
        String colstr = colors == null ? str : colors.toString(str);
        StringBuilder sb = new StringBuilder(colstr);
        int insertPoint = justifyLeft() ? sb.length() : 0;
        for (Integer i : Iterate.count(nSpaces)) {
            sb.insert(insertPoint, ' ');
        }
        return sb.toString();
    }
}

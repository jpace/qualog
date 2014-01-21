package org.incava.qualog;

/**
 * <p>Represents a logging/output level. Is essentially a wrapper around an
 * Integer.</p>
 */
class QlLevel implements Comparable<QlLevel> {    
    private Integer level = null;
    
    public QlLevel(int level) {
        this(new Integer(level));
    }

    public QlLevel(Integer level) {
        this.level = level;
    }

    public int compareTo(QlLevel other) {
        return this.level.compareTo(other.level);
    }

    public boolean equals(Object other) {
        return other instanceof QlLevel && compareTo((QlLevel)other) == 0;
    }

    public String toString() {
        return level.toString();
    }
}

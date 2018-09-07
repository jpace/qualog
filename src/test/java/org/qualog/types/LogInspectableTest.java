package org.qualog.types;

import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;
import junit.framework.TestCase;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogInspectableTest extends Parameterized {
    public static class One {
        private final static String s = "def";
        
        private final String x;

        public One(String x) {
            this.x = x;
        }
    }
    
    public static class Two extends One {
        private final String y;

        public Two(String x, String y) {
            super(x);
            this.y = y;
        }
    }
    
    @Test
    public void withStatic() {
        Map<String, Object> expected = new TreeMap<String, Object>();
        expected.put("x", "abc");
        expected.put("s", "def");
        
        Object obj = new One("abc");
        Map<String, Object> result = LogInspectable.inspect(EnumSet.of(LogInspectable.InspectOptionType.INCLUDE_STATIC), obj);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void withoutStatic() {
        Map<String, Object> expected = new TreeMap<String, Object>();
        expected.put("x", "abc");
        
        Object obj = new One("abc");
        Map<String, Object> result = LogInspectable.inspect(obj);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void withoutSuperclass() {
        Map<String, Object> expected = new TreeMap<String, Object>();
        expected.put("y", "ghi");
        
        Object obj = new Two("abc", "ghi");
        Map<String, Object> result = LogInspectable.inspect(obj);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void withSuperclass() {
        Map<String, Object> expected = new TreeMap<String, Object>();
        expected.put("x", "abc");
        expected.put("y", "ghi");
        
        Object obj = new Two("abc", "ghi");
        Map<String, Object> result = LogInspectable.inspect(EnumSet.of(LogInspectable.InspectOptionType.INCLUDE_SUPERCLASSES), obj);
        assertThat(result, equalTo(expected));
    }
}

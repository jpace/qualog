package org.qldemo;

import java.util.*;
import org.qualog.config.ColorConfig;
import org.qualog.config.Configuration;
import org.qualog.config.WidthConfig;

import static org.incava.ijdk.util.IUtil.list;
import static tr.Ace.*;

public class Example {
    private final String name;

    public Example(String name) {
        this.name = name;
    }

    public void greet() {
        tr.Ace.log("hello, world");
    }

    public void declare() {
        String str = "I'm a string";
        tr.Ace.log("str", str);
    }

    public void reveal() {
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("pi", "3.14");
        map.put("e", "2.818");
        map.put("i", "sqrt(-1)");
        map.put("fruits", new String[] { "apple", "banana", "cucumber" });
        map.put("squares", new int[] { 1, 4, 9, 16, 25 });

        Map<String, List<String>> cartoons = new LinkedHashMap<String, List<String>>();
        cartoons.put("simpsons", list("bart", "maggie", "marge", "lisa", "homer"));
        cartoons.put("flintstones", list("wilma", "fred", "barney", "betty"));
        cartoons.put("jetsons", list("jane", "george", "elroy", "judy"));
        map.put("cartoons", cartoons);
        
        tr.Ace.log("map", map);
    }

    public void process() {
        greet();
        declare();

        Demo demo = new Demo();
        demo.announce();
        demo.disclose();

        reveal();

        tr.Ace.log("this", this);

        demo.blather();
    }

    public String toString() {
        return "name: '" + name + "'";
    }
    
    public static void main(String[] args) {
        tr.Ace.setVerbose(true);
        tr.Ace.setConfiguration(new Configuration(new ColorConfig(), WidthConfig.NARROW, true, true, true, true));
        Example ex = new Example("demo");
        ex.process();
    }
}

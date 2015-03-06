package org.incava.lgtest;

import java.util.*;


public class QLoggeeThree
{
    public void showOutput()
    {
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("key0", "value0");
        map.put("key1", "value1");
        map.put("key2", new String[] { "an", "enclosed", "array" });
        map.put("key4", new int[] { 1, 2, 3, 4 });

        tr.Ace.log("map", map);
        
        Collection<String> c = new ArrayList<String>();
        c.add("zero");
        c.add("the");
        c.add("hero");
        tr.Ace.log("it", c.iterator());

        Vector<String> v = new Vector<String>();
        v.addElement("voi");
        v.addElement("allage");
        v.addElement("clitic");
        tr.Ace.log("en", v.elements());

    }

}

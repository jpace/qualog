package org.incava.lgtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LgCustomElement {
    public static void run() {
        CustomElement ce = new CustomElement("hello world");
        tr.Ace.log("ce", ce);

        DerivedCustomElement dce = new DerivedCustomElement("bar");
        tr.Ace.log("dce", dce);
    }

    public static void runCollection() {
        Map<String, List<CustomElement>> numbers = new TreeMap<String, List<CustomElement>>();
        List<CustomElement> firstList = new ArrayList<CustomElement>();
        firstList.add(new CustomElement("zero"));
        firstList.add(new DerivedCustomElement("one"));
        firstList.add(new DerivedCustomElement("two"));
        numbers.put("English", firstList);
        
        List<CustomElement> secondList = new ArrayList<CustomElement>();
        secondList.add(new CustomElement("cero"));
        secondList.add(new DerivedCustomElement("uno"));
        secondList.add(new DerivedCustomElement("dos"));
        numbers.put("Spanish", secondList);
        
        tr.Ace.log("numbers", numbers);
    }
}

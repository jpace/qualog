# Overview

Qualog (Quasi Log) is a simple library for producing logging statements from Java code. It resembles
log4j, with reduced functionality and a simpler configuration.

The GitHub project [qualog](http://github.com/jpace/qualog "Qualog") contains the full project.

Qualog requires [ijdk](http://github.com/jpace/ijdk "IJDK").

The class tr.Ace is the same (a subclass, in fact) as org.qualog.Log, just with a shorter name.

# Usage

    tr.Ace.setVerbose(true);

    tr.Ace.log("hello, world");

    String str = "I'm a string";
    tr.Ace.log("str", str);

    String[] ary = new String[] { "I", "am", "an", "array" };
    tr.Ace.log("ary", ary);

    List<String> list = Arrays.asList("And", "I'm", "a", "list");
    tr.Ace.log("list", list);

    // Collections are fully unrolled through their subelements:
    Map<String, Object> map = new TreeMap<String, Object>();
    map.put("key0", "value0");
    map.put("key1", "value1");
    map.put("key2", new String[] { "an", "enclosed", "array" });
    map.put("key4", new int[] { 1, 2, 3, 4 });
    tr.Ace.log("map", map);

    [QLoggeeTh-   16] {o.i.l.QLoggeeThree  #showOutput} map[key0]: value0
    [             16] {                    #          } map[key1]: value1
    [             16] {                    #          } map[key2][0]: an
    [             16] {                    #          } map[key2][1]: enclosed
    [             16] {                    #          } map[key2][2]: array
    [             16] {                    #          } map[key4][0]: 1
    [             16] {                    #          } map[key4][1]: 2
    [             16] {                    #          } map[key4][2]: 3
    [             16] {                    #          } map[key4][3]: 4

# Help

Please email me at jeugenepace at gmail dot com if you have questions about Qualog.

# Contributing

I'm eager to learn how others use this library, and what additional features
they would like. Please email me at the above address.

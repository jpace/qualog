# Overview

Qualog (Quasi Log) is a simple library for producing logging statements from Java code. It resembles
log4j, with reduced functionality and a simpler configuration.

The GitHub project [qualog](http://github.com/jpace/qualog "Qualog project at Github") contains the
full project.

Qualog requires [ijdk](http://github.com/jpace/ijdk "IJDK project at Github").

The class tr.Ace is the equivalent (a subclass, in fact) of org.qualog.Log, just with a shorter
name.

# Usage

    tr.Ace.setVerbose(true);

    // a variety of configuration options:
    tr.Ace.setConfiguration(new Configuration(new ColorConfig(), WidthConfig.NARROW));

    // Simple message:
    tr.Ace.log("hello, world");

    // A message/object pair:
    String str = "I'm a string";
    tr.Ace.log("str", str);

    // C style array:
    String[] ary = new String[] { "I", "am", "an", "array" };
    tr.Ace.log("ary", ary);

    // java.util.Collection:
    List<String> list = Arrays.asList("And", "I'm", "a", "list");
    tr.Ace.log("list", list);

    // Collections are fully unrolled through their subelements:
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

    // stack is also supported, showing the last five frames by default:
    int value = 317;
    tr.Ace.stack("value", value);

    [Example      17] {o.q.Example    #greet          } hello, world
    [             22] {               #declare        } str: I'm a string
    [Demo          8] {o.q.Demo       #announce       } ary[0]: I
    [              8] {               #               } ary[1]: am
    [              8] {               #               } ary[2]: an
    [              8] {               #               } ary[3]: array
    [             13] {               #disclose       } list[0]: And
    [             13] {               #               } list[1]: I'm
    [             13] {               #               } list[2]: a
    [             13] {               #               } list[3]: list
    [Example      39] {o.q.Example    #reveal         } map[cartoons][simpsons][0]: bart
    [             39] {               #               } map[cartoons][simpsons][1]: maggie
    [             39] {               #               } map[cartoons][simpsons][2]: marge
    [             39] {               #               } map[cartoons][simpsons][3]: lisa
    [             39] {               #               } map[cartoons][simpsons][4]: homer
    [             39] {               #               } map[cartoons][flintstones][0]: wilma
    [             39] {               #               } map[cartoons][flintstones][1]: fred
    [             39] {               #               } map[cartoons][flintstones][2]: barney
    [             39] {               #               } map[cartoons][flintstones][3]: betty
    [             39] {               #               } map[cartoons][jetsons][0]: jane
    [             39] {               #               } map[cartoons][jetsons][1]: george
    [             39] {               #               } map[cartoons][jetsons][2]: elroy
    [             39] {               #               } map[cartoons][jetsons][3]: judy
    [             39] {               #               } map[e]: 2.818
    [             39] {               #               } map[fruits][0]: apple
    [             39] {               #               } map[fruits][1]: banana
    [             39] {               #               } map[fruits][2]: cucumber
    [             39] {               #               } map[i]: sqrt(-1)
    [             39] {               #               } map[pi]: 3.14
    [             39] {               #               } map[squares][0]: 1
    [             39] {               #               } map[squares][1]: 4
    [             39] {               #               } map[squares][2]: 9
    [             39] {               #               } map[squares][3]: 16
    [             39] {               #               } map[squares][4]: 25
    [             52] {               #process        } this: name: 'demo' (org.qldemo.Example) #7a84639c
    [Demo         26] {o.q.Demo       #utter          } value
    [             21] {               #speak          } ""
    [             17] {               #blather        } ""
    [Example      54] {o.q.Example    #process        } ""
    [             66] {               #main           } ""

Qualog writes to standard output, bypassing Gradle and Ant filters, and thus is always displayed.

All log and stack methods return true, so they can be used inside conditionals:

    if ((x > 3 && tr.Ace.log("x", x)) || (y > 17 && tr.Ace.log("y", y)) || z > 37) {
    }
    
# Help

Please email me at jeugenepace at gmail dot com if you have questions about Qualog.

# Contributing

I'm eager to learn how others use this library, and what additional features
they would like. Please email me at the above address.

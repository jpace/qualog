package org.incava.lgtest;


public class LgInspector {

    public static void runDefault() {
        LgInspObj inspObj = new LgInspObj(true, 'j');
        tr.Ace.inspect("inspObj", inspObj);
    }

    // public static void runIncludeStatic() {
    //     LgInspObj inspObj = new LgInspObj(true, 'j');
    //$$$ not yet supported in main Log class:
    //     tr.Ace.inspect(InspectOptionType.INCLUDE_STATIC, "inspObj", inspObj);
    // }
}

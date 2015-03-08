package org.qualog;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.qualog.config.ColorConfig;

public class TestLog extends LogTestCase {
    public TestLog(String name) {
        super(name);
    }

    public void testColumnar() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);

        (new org.incava.lgtest.QLoggee()).showOutput();

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      24] {o.i.l.QLoggee              #showOu-} hello 0\n" +
            "[                             25] {                           #       } hello again\n" +
            "[                             38] {                           #aMetho-} I'm here\n" +
            "[                             13] {o.i.l.QLoggee$Inner        #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             13] {                           #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             32] {o.i.l.QLoggee              #showOu-} hello 1\n";

        assertStringsEqual(expected, sw.toString());
    }

    public void testColumnarInsufficentClassWidth() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 16, 7, true);

        (new org.incava.lgtest.QLoggee()).showOutput();

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      24] {o.i.l.QLoggee   #showOu-} hello 0\n" +
            "[                             25] {                #       } hello again\n" +
            "[                             38] {                #aMetho-} I'm here\n" +
            "[                             13] {o.i.l.QLoggee$I-#anothe-} hola\n" +
            "[                             18] {                #terse  } hola\n" +
            "[                             13] {                #anothe-} hola\n" +
            "[                             18] {                #terse  } hola\n" +
            "[                             32] {o.i.l.QLoggee   #showOu-} hello 1\n";
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testNonColumnar() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 20, 5, 16, 7, false);

        (new org.incava.lgtest.QLoggee()).showOutput();

        String expected = "" +
            //0         1         2       0         1         2     0
            //01234567890123456789012345  0123456789012345678901234 0123456
            "[QLoggee:24          ] {o.i.l.QLoggee   #showOu-} hello 0\n" +
            "[       :25          ] {                #       } hello again\n" +
            "[       :38          ] {                #aMetho-} I'm here\n" +
            "[       :13          ] {o.i.l.QLoggee$I-#anothe-} hola\n" +
            "[       :18          ] {                #terse  } hola\n" +
            "[       :13          ] {                #anothe-} hola\n" +
            "[       :18          ] {                #terse  } hola\n" +
            "[       :32          ] {o.i.l.QLoggee   #showOu-} hello 1\n";
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testNonColumnarZeroClassWidth() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 20, 5, 0, 7, false);

        (new org.incava.lgtest.QLoggee()).showOutput();

        String expected = "" +
            //0         1         2        0        0
            //0123456789012345678901234    0123456  0123456
            "[QLoggee:24          ] {#showOu-} hello 0\n" +
            "[       :25          ] {#       } hello again\n" +
            "[       :38          ] {#aMetho-} I'm here\n" +
            "[       :13          ] {#anothe-} hola\n" +
            "[       :18          ] {#terse  } hola\n" +
            "[       :13          ] {#anothe-} hola\n" +
            "[       :18          ] {#terse  } hola\n" +
            "[       :32          ] {#showOu-} hello 1\n";
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testQuietLevel5() {
        StringWriter sw = reset(Log.QUIET, Log.LEVEL5, 25, 5, 0, 7, false);

        (new org.incava.lgtest.QLoggee()).showOutput();

        String expected = "" +
            "hello 0\n" +
            "hello again\n" +
            "I'm here\n" +
            "hola\n" +
            "hola\n" +
            "hola\n" +
            "hola\n" +
            "hello 1\n";
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testQuietLevel9() {
        StringWriter sw = reset(Log.QUIET, Log.LEVEL9, 25, 5, 0, 7, false);

        (new org.incava.lgtest.QLoggee()).showOutput();

        String expected = "" +
            "hello 0\n" +
            "hello again\n" +
            "I'm here\n" +
            "hola\n" +
            "hola\n" +
            "hola\n" +
            "hola\n" +
            "hello 1\n" +
            "hello: world\n";

        assertStringsEqual(expected, sw.toString());
    }

    public void testNoOutput() {
        StringWriter sw = reset(Log.NO_OUTPUT, Log.LEVEL9, 25, 5, 0, 7, false);

        (new org.incava.lgtest.QLoggee()).showOutput();

        String expected = "";
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testObjectArray() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);

        Object[] ary = new String[] { "1", "3", "5", "7", "9", "10", "8", "6", "4", "2", "0" };
        
        (new org.incava.lgtest.QLoggee()).showObjectLog("ary", ary);

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      43] {o.i.l.QLoggee              #showOb-} ary[0]: 1\n" +
            "[                             43] {                           #       } ary[1]: 3\n" +
            "[                             43] {                           #       } ary[2]: 5\n" +
            "[                             43] {                           #       } ary[3]: 7\n" +
            "[                             43] {                           #       } ary[4]: 9\n" +
            "[                             43] {                           #       } ary[5]: 10\n" +
            "[                             43] {                           #       } ary[6]: 8\n" +
            "[                             43] {                           #       } ary[7]: 6\n" +
            "[                             43] {                           #       } ary[8]: 4\n" +
            "[                             43] {                           #       } ary[9]: 2\n" +
            "[                             43] {                           #       } ary[10]: 0\n";

        assertStringsEqual(expected, sw.toString());
    }


    public void testIntCollection() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);

        Integer[] ary = new Integer[] { 1, 3, 5, 7, 9, 10, 8, 6, 4, 2, 0 };

        List<Integer> list = Arrays.asList(ary);
        
        (new org.incava.lgtest.QLoggee()).showObjectLog("list", list);

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      43] {o.i.l.QLoggee              #showOb-} list[0]: 1\n" +
            "[                             43] {                           #       } list[1]: 3\n" +
            "[                             43] {                           #       } list[2]: 5\n" +
            "[                             43] {                           #       } list[3]: 7\n" +
            "[                             43] {                           #       } list[4]: 9\n" +
            "[                             43] {                           #       } list[5]: 10\n" +
            "[                             43] {                           #       } list[6]: 8\n" +
            "[                             43] {                           #       } list[7]: 6\n" +
            "[                             43] {                           #       } list[8]: 4\n" +
            "[                             43] {                           #       } list[9]: 2\n" +
            "[                             43] {                           #       } list[10]: 0\n";
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testIntArray() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);

        int[] ary = new int[] { 1, 3, 5, 7, 9, 10, 8, 6, 4, 2, 0 };
        
        (new org.incava.lgtest.QLoggee()).showObjectLog("ary", ary);

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      43] {o.i.l.QLoggee              #showOb-} ary[0]: 1\n" +
            "[                             43] {                           #       } ary[1]: 3\n" +
            "[                             43] {                           #       } ary[2]: 5\n" +
            "[                             43] {                           #       } ary[3]: 7\n" +
            "[                             43] {                           #       } ary[4]: 9\n" +
            "[                             43] {                           #       } ary[5]: 10\n" +
            "[                             43] {                           #       } ary[6]: 8\n" +
            "[                             43] {                           #       } ary[7]: 6\n" +
            "[                             43] {                           #       } ary[8]: 4\n" +
            "[                             43] {                           #       } ary[9]: 2\n" +
            "[                             43] {                           #       } ary[10]: 0\n";
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testDoubleCollection() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 8, true);

        double[] ary = new double[] { 3.1415, 2.818, 6.02E23 };
        
        (new org.incava.lgtest.QLoggee()).showObjectLog("dary", ary);

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      43] {o.i.l.QLoggee              #showObj-} dary[0]: 3.1415\n" +
            "[                             43] {                           #        } dary[1]: 2.818\n" +
            "[                             43] {                           #        } dary[2]: 6.02E23\n";
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testTwoDimensionArray() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);

        int[][] ary = new int[][] { 
            new int[] { 1, 3, 5 },
            new int[] { 7, 9 },
            new int[] { 13, 15, 17, 19 },
        };
        
        (new org.incava.lgtest.QLoggee()).showObjectLog("ary", ary);

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      43] {o.i.l.QLoggee              #showOb-} ary[0][0]: 1\n" +
            "[                             43] {                           #       } ary[0][1]: 3\n" +
            "[                             43] {                           #       } ary[0][2]: 5\n" +
            "[                             43] {                           #       } ary[1][0]: 7\n" +
            "[                             43] {                           #       } ary[1][1]: 9\n" +
            "[                             43] {                           #       } ary[2][0]: 13\n" +
            "[                             43] {                           #       } ary[2][1]: 15\n" +
            "[                             43] {                           #       } ary[2][2]: 17\n" +
            "[                             43] {                           #       } ary[2][3]: 19\n";
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testMultiDimensionObjectArray() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);

        List<Object> objs = new ArrayList<Object>();
        objs.add(new int[] { 0, 1, 1, 2, 3, 5, 8 });
        objs.add(Arrays.asList(new Object[] { 
                                   new String[] { "vote", "for", "pedro" },
                                   Arrays.asList(new Double[] { new Double(1.4D), new Double(3.8D), new Double(5.1D) })
                               }));
        objs.add("sweet");
        
        (new org.incava.lgtest.QLoggee()).showObjectLog("objs", objs);

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      43] {o.i.l.QLoggee              #showOb-} objs[0][0]: 0\n" +
            "[                             43] {                           #       } objs[0][1]: 1\n" +
            "[                             43] {                           #       } objs[0][2]: 1\n" +
            "[                             43] {                           #       } objs[0][3]: 2\n" +
            "[                             43] {                           #       } objs[0][4]: 3\n" +
            "[                             43] {                           #       } objs[0][5]: 5\n" +
            "[                             43] {                           #       } objs[0][6]: 8\n" +
            "[                             43] {                           #       } objs[1][0][0]: vote\n" +
            "[                             43] {                           #       } objs[1][0][1]: for\n" +
            "[                             43] {                           #       } objs[1][0][2]: pedro\n" +
            "[                             43] {                           #       } objs[1][1][0]: 1.4\n" +
            "[                             43] {                           #       } objs[1][1][1]: 3.8\n" +
            "[                             43] {                           #       } objs[1][1][2]: 5.1\n" +
            "[                             43] {                           #       } objs[2]: sweet\n";
        
        assertStringsEqual(expected, sw.toString());
    }


    public void testMessageColor() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 15, 5, 17, 17, true);

        (new org.incava.lgtest.QLoggee()).showColorMessage("hat", Log.RED);

        String expected = "" +
            //0         1         2   0         1       0         1          
            //012345678901234567890   01234567890123456 01234567890123456
            "[QLoggee            53] {o.i.l.QLoggee    #showColorMessage } " + (char)27 + "[31mhat" + (char)27 + "[0m\n";
       
        assertStringsEqual(expected, sw.toString());
    }

    public void testClassColor() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);
        Configuration cfg = Log.getConfiguration();
        ColorConfig colcfg = cfg.getColorConfig();

        colcfg.setClassColor(org.incava.lgtest.QLoggee.class.getName(), Log.RED);

        (new org.incava.lgtest.QLoggee()).showOutput();

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      24] {" + (char)27 + "[31mo.i.l.QLoggee" + (char)27 + "[0m              #showOu-} " + (char)27 + "[31mhello 0" + (char)27 + "[0m\n" +
            "[                             25] {                           #       } " + (char)27 + "[31mhello again" + (char)27 + "[0m\n" +
            "[                             38] {                           #aMetho-} " + (char)27 + "[31mI'm here" + (char)27 + "[0m\n" +
            "[                             13] {o.i.l.QLoggee$Inner        #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             13] {                           #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             32] {" + (char)27 + "[31mo.i.l.QLoggee" + (char)27 + "[0m              #showOu-} " + (char)27 + "[31mhello 1" + (char)27 + "[0m\n";

        assertStringsEqual(expected, sw.toString());
    }

    public void testEnableDisable() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);
        Configuration cfg = Log.getConfiguration();
        ColorConfig colcfg = cfg.getColorConfig();

        colcfg.setClassColor(org.incava.lgtest.QLoggee.class.getName(), null);
        
        (new org.incava.lgtest.QLoggee()).showOutput();
        Log.setDisabled(org.incava.lgtest.QLoggeeTwo.class);

        (new org.incava.lgtest.QLoggeeTwo()).showOutput();

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      24] {o.i.l.QLoggee              #showOu-} hello 0\n" +
            "[                             25] {                           #       } hello again\n" +
            "[                             38] {                           #aMetho-} I'm here\n" +
            "[                             13] {o.i.l.QLoggee$Inner        #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             13] {                           #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             32] {o.i.l.QLoggee              #showOu-} hello 1\n" +
            "[QLoggeeTwo                   13] {o.i.l.QLoggeeTwo$Inner     #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             13] {                           #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n";

        assertStringsEqual(expected, sw.toString());
    }

    public void testColorsMultiClasses() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);
        
        Configuration cfg = Log.getConfiguration();
        ColorConfig colcfg = cfg.getColorConfig();

        colcfg.setClassColor(org.incava.lgtest.QLoggee.class.getName(), Log.RED);
        colcfg.setClassColor(org.incava.lgtest.QLoggeeTwo.class.getName(), Log.BOLD);

        (new org.incava.lgtest.QLoggee()).showOutput();
        (new org.incava.lgtest.QLoggeeTwo()).showOutput();

        String expected = "" +
            "[QLoggee                      24] {" + (char)27 + "[31mo.i.l.QLoggee" + (char)27 + "[0m              #showOu-} " + (char)27 + "[31mhello 0" + (char)27 + "[0m\n" +
            "[                             25] {                           #       } " + (char)27 + "[31mhello again" + (char)27 + "[0m\n" +
            "[                             38] {                           #aMetho-} " + (char)27 + "[31mI'm here" + (char)27 + "[0m\n" +
            "[                             13] {o.i.l.QLoggee$Inner        #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             13] {                           #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             32] {" + (char)27 + "[31mo.i.l.QLoggee" + (char)27 + "[0m              #showOu-} " + (char)27 + "[31mhello 1" + (char)27 + "[0m\n" +
            "[QLoggeeTwo                   24] {" + (char)27 + "[1mo.i.l.QLoggeeTwo" + (char)27 + "[0m           #showOu-} " + (char)27 + "[1mhello 0" + (char)27 + "[0m\n" +
            "[                             25] {                           #       } " + (char)27 + "[1mhello again" + (char)27 + "[0m\n" +
            "[                             37] {                           #aMetho-} " + (char)27 + "[1mI'm here" + (char)27 + "[0m\n" +
            "[                             13] {o.i.l.QLoggeeTwo$Inner     #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             13] {                           #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             32] {" + (char)27 + "[1mo.i.l.QLoggeeTwo" + (char)27 + "[0m           #showOu-} " + (char)27 + "[1mhello 1" + (char)27 + "[0m\n";

        assertStringsEqual(expected, sw.toString());
    }

    public void testEnableDisableRefined() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);
        Configuration cfg = Log.getConfiguration();
        ColorConfig colcfg = cfg.getColorConfig();

        colcfg.setClassColor(org.incava.lgtest.QLoggee.class.getName(), null);

        // Log.setFilter(new LogFilter(Log.LEVEL5, "org.incava.lgtest.QLoggee"));
        
        (new org.incava.lgtest.QLoggee()).showOutput();
        Log.setDisabled(org.incava.lgtest.QLoggeeTwo.class);

        (new org.incava.lgtest.QLoggeeTwo()).showOutput();

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggee                      24] {o.i.l.QLoggee              #showOu-} hello 0\n" +
            "[                             25] {                           #       } hello again\n" +
            "[                             38] {                           #aMetho-} I'm here\n" +
            "[                             13] {o.i.l.QLoggee$Inner        #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             13] {                           #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             32] {o.i.l.QLoggee              #showOu-} hello 1\n" +
            "[QLoggeeTwo                   13] {o.i.l.QLoggeeTwo$Inner     #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n" +
            "[                             13] {                           #anothe-} hola\n" +
            "[                             18] {                           #terse  } hola\n";

        assertStringsEqual(expected, sw.toString());
    }

    public void testQLoggeeThree() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 27, 7, true);
        
        (new org.incava.lgtest.QLoggeeThree()).showOutput();

        String expected = "" +
            //0         1         2         3   0         1         2       0
            //0123456789012345678901234567890   01234567890123456789012345  0123456
            "[QLoggeeThree                 16] {o.i.l.QLoggeeThree         #showOu-} map[key0]: value0\n" +
            "[                             16] {                           #       } map[key1]: value1\n" +
            "[                             16] {                           #       } map[key2][0]: an\n" +
            "[                             16] {                           #       } map[key2][1]: enclosed\n" +
            "[                             16] {                           #       } map[key2][2]: array\n" +
            "[                             16] {                           #       } map[key4][0]: 1\n" +
            "[                             16] {                           #       } map[key4][1]: 2\n" +
            "[                             16] {                           #       } map[key4][2]: 3\n" +
            "[                             16] {                           #       } map[key4][3]: 4\n" +
            "[                             22] {                           #       } it[0]: zero\n" +
            "[                             22] {                           #       } it[1]: the\n" +
            "[                             22] {                           #       } it[2]: hero\n" +
            "[                             28] {                           #       } en[0]: voi\n" +
            "[                             28] {                           #       } en[1]: allage\n" +
            "[                             28] {                           #       } en[2]: clitic\n";
        
        // compare(expected, sw.toString());
        
        assertStringsEqual(expected, sw.toString());
    }

    public void testInspect() {
        StringWriter sw = reset(Log.VERBOSE, Log.LEVEL5, 25, 5, 25, 17, true);
        org.incava.lgtest.LgInspector.runDefault();

        String expected = "" +
            "[LgInspector                   8] {o.i.l.LgInspector        #runDefault       } inspObj[priv]: j\n" +
            "[                              8] {                         #                 } inspObj[pub]: true\n";
        
        assertStringsEqual(expected, sw.toString());
    }
}

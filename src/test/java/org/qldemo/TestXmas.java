package org.incava.ijdk.example;

import java.util.List;
import junit.framework.TestCase;
import org.incava.ijdk.util.ListExt;
import org.qualog.Configuration;
import org.qualog.config.WidthConfig;
import org.qualog.output.ANSIColor;
import static org.incava.ijdk.util.IUtil.list;

public class TestXmas extends TestCase {
    public TestXmas(String name) {
        super(name);
    }

    public void testDisplay() {
        // uncomment the below section for ASCII gaudiness
        /*
        tr.Ace.setVerbose(true);
        Configuration cfg = tr.Ace.getConfiguration();
        WidthConfig wc = cfg.getWidthConfig();
        wc.setFileWidth(1);
        wc.setFunctionWidth(1);
        wc.setLineWidth(3);
        wc.setClassWidth(1);
        */

        /*
        // prototyping here ...
        tr.Ace.setFormat("[%file:1% %line:3%] { %class:1% %function:1% } %message%");
        */
        
        List<String> lines = list("                              $",
                                  "                              :$$",
                                  "                         seeee$$$Neeee",
                                  "                           R$$$F$$$$F",
                                  "                             $$$$$$",
                                  "                            @$$P*$$B",
                                  "                           z$#\"  $#$b",
                                  "                           \" d   'N \"",
                                  "                            @\"     ?r",
                                  "                          xF .       \"N",
                                  "                       .$> P54.R       `$",
                                  "                     $*   '*\"$$$  uoP***~",
                                  "                      #Noo \"?$N\"   #oL",
                                  "                         f       o$#$$\"e.",
                                  "                        $  @b    hoR$$r ^\"$$b",
                                  "                     .M   ?B$E   *.B$$       .R",
                                  "                   .*     *\\ *.4*R         ..*",
                                  "                oo#     ooL    d#R.     P##~",
                                  "                $c    .\"\"P#$  @   P     k",
                                  "                  R$r ''?L$$  P  \"r     'N",
                                  "                    ^$ \"$$$` $.....JL     \"N.",
                                  "                  .$\\           * P5\"LR      $..",
                                  "               ..* 4*R     xr    'PFN$$   .k    \"*****.",
                                  "            od#\"   d#*.  \"*$$P~   \"?$*\" '#$$$\"       u\"",
                                  "         e\"\"      f   M   @F\"$  ec       x$\"$.     :\"",
                                  "         M        >  \"d       $$$$?$           .$$F`",
                                  "          \"P..  .$.....$L $$.4$$. \"   @#3$$   $E.",
                                  "             '**..  *   R..$$ `R$*k.  fdM$$>     *..",
                                  "               J\"       *k$$$~  \"*$**o$o$$P        '*oo.",
                                  "              P           #        \"$$$#*o          >  '####*oooo",
                                  "           .e\"            :e$$e.  F3  ^\"$P  :$$s :e@$ee        s\"",
                                  "         $P` M7>    $P$$k \"$\"?$3 @\"#N      CxN$$> .$$$       .P",
                                  "      M$~   J\\##   44N>$$  .d$.$d   @&      `$$$  F  .8..$$$*",
                                  "  .***     :   JM   *d$$*.$$.P  M  .P5     M          **.",
                                  "  \"oo      J  .dP    ud$$od#   $oooooo$  oo$oo           ###ou",
                                  "     \"####$beeeee$.'$eeP#~        \"\"      $$$.    e$$$o       #heeee",
                                  "        :\"    \" z$r ^            o$N     '\"  \"   4$z>$$             \"\"\"#$$$",
                                  "       .~      F$4$B       r    F @#$.       ..   $8$$P M7                $",
                                  "     .*  $     8 $$B     .J$..  hP$$$F     .'PB$       J~##             .d~",
                                  "   .P  *$$$*    \"*\"       $$$    #**~      hdM$$>     <   JM.......*****",
                                  " .P     $#*k       .o#>  P\" \"k   ..         '$$P      d  .JP'h",
                                  "\"\"\"hr ^        xe\"\"  >          \"\"c           ee    @beeeee$.)",
                                  "      \"\"\"t$$$$F\"      M        $`   R          > \"$r     \"     \"c",
                                  "                              oooooooooo",
                                  "                              z        z",
                                  "                              z.,ze.$$$z");

        List<ANSIColor> colors = list(ANSIColor.NONE,
                                      ANSIColor.RESET,
                                      ANSIColor.BOLD,
                                      ANSIColor.UNDERSCORE,
                                      ANSIColor.UNDERLINE,
                                      ANSIColor.BLINK,
                                      ANSIColor.REVERSE,
                                      ANSIColor.CONCEALED,
                                      ANSIColor.BLACK,
                                      ANSIColor.RED,
                                      ANSIColor.GREEN,
                                      ANSIColor.YELLOW,
                                      ANSIColor.BLUE,
                                      ANSIColor.MAGENTA,
                                      ANSIColor.CYAN,
                                      ANSIColor.WHITE,
                                      ANSIColor.ON_BLACK,
                                      ANSIColor.ON_RED,
                                      ANSIColor.ON_GREEN,
                                      ANSIColor.ON_YELLOW,
                                      ANSIColor.ON_BLUE,
                                      ANSIColor.ON_MAGENTA,
                                      ANSIColor.ON_CYAN,
                                      ANSIColor.ON_WHITE);

        for (String line : lines) {
            StringBuffer sb = new StringBuffer();
            for (int idx = 0; idx < line.length(); ++idx) {
                char ch = line.charAt(idx);
                if (ch == ' ') {
                    sb.append(' ');
                }
                else {
                    ANSIColor color = ListExt.getRandomElement(colors);
                    if (Math.random() < 0.3) {
                        // blinkenlights! (not on all terminals; XTerm still supports blink)
                        sb.append(ANSIColor.BLINK);
                    }
                    sb.append(color);
                    sb.append(ch);
                    sb.append(ANSIColor.RESET);
                }
            }            
            tr.Ace.log(sb.toString());
        }
    }
}

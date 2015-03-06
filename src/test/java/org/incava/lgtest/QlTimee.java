package org.incava.lgtest;

import java.util.*;
import org.qualog.timer.Timer;


public class QlTimee
{
    public void go()
    {
        Timer t = new Timer();

        t.start("riley");

        // churn up some time here ...

        double num = 1.0;
        for (int ni = 0; ni < 10000; ++ni) {
            num = Math.acos(num);
        }   

        t.end("riley");
    }

}

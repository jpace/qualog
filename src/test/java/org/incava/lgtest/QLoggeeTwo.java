package org.incava.lgtest;

import java.util.*;
import org.qualog.output.*;


public class QLoggeeTwo
{
    public class Inner
    {
        public void anotherMethodWithALongName()
        {
            tr.Ace.log("hola");
        }

        public void terse()
        {
            tr.Ace.log("hola");
        }
    }

    public void showOutput()
    {
        tr.Ace.log("hello 0");
        tr.Ace.log("hello again");
        aMethodWithAnExceptionallyLongName();
        Inner i = new Inner();
        i.anotherMethodWithALongName();
        i.terse();
        i.anotherMethodWithALongName();
        i.terse();
        tr.Ace.log("hello 1");
    }

    public void aMethodWithAnExceptionallyLongName()
    {
        tr.Ace.log("I'm here");
    }

    public void showObjectLog(String msg, Object obj)
    {
        tr.Ace.log(msg, obj);
    }

    public void showStack(String msg, Object obj)
    {
        tr.Ace.stack(msg, obj);
    }

    public void showColorMessage(String msg, ANSIColor color)
    {
        tr.Ace.log(color, msg);
    }

}

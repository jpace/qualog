package org.qualog.timer;

import java.util.ArrayList;
import java.util.List;
import org.incava.ijdk.collect.Iterate;
import org.incava.ijdk.lang.Objects;
import org.incava.ijdk.tuple.Pair;

public class Timer {
    private final List<TimedPeriod> periods;

    public Timer() {
        periods = new ArrayList<TimedPeriod>();
    }

    public boolean start() {
        return start(null);
    }

    public boolean end() {
        return end(null);        
    }

    public boolean start(String msg) {
        StackTraceElement ste = getFrame();

        String className  = ste.getClassName();
        String methodName = ste.getMethodName();
        int    lineNumber = ste.getLineNumber();
        String fileName   = ste.getFileName();

        TimedPeriod qtp = new TimedPeriod(fileName, className, methodName, lineNumber, msg);
        periods.add(qtp);

        return true;
    }

    /**
     * Returns 1 if the objects are equal, and otherwise zero.
     */
    private int getScore(Object x, Object y) {
        return Objects.equal(x, y) ? 1 : 0;
    }

    private Integer findBestMatch(StackTraceElement ste, String msg) {
        String className  = ste.getClassName();
        String methodName = ste.getMethodName();
        String fileName   = ste.getFileName();

        // first is index, second is score.
        Pair<Integer, Integer> bestMatch = new Pair<Integer, Integer>(-1, -1);
        
        for (Integer idx : Iterate.count(periods.size())) {
            TimedPeriod qtp       = periods.get(idx);
            int         matchness = (getScore(msg,        qtp.getMessage()) +
                                     getScore(className,  qtp.getClassName()) + 
                                     getScore(fileName,   qtp.getFileName()) +
                                     getScore(methodName, qtp.getMethodName()));
            
            if (matchness >= bestMatch.getSecond()) {
                bestMatch = Pair.of(idx, matchness);
            }
        }

        return bestMatch.getFirst();
    }

    public boolean end(String msg) {
        long endTime = System.currentTimeMillis();

        StackTraceElement ste = getFrame();

        // index of periods:
        Integer bestMatch = findBestMatch(ste, msg);

        if (bestMatch >= 0) {
            TimedPeriod qtp     = periods.remove(bestMatch.intValue());
            long        elapsed = endTime - qtp.getStartTime();
            String      str     = getMessage(ste, msg, elapsed);

            // log(str);
        }
        else {
            System.err.println("ERROR no matching start!");
        }

        return true;
    }

    private String getMessage(StackTraceElement ste, String msg, long elapsed) {
        String className  = ste.getClassName();
        String methodName = ste.getMethodName();
        int    lineNumber = ste.getLineNumber();
        String fileName   = ste.getFileName();

        StringBuilder sb = new StringBuilder();

        sb.append(format(elapsed));
        sb.append("; ");
                
        if (msg != null) {
            sb.append(msg);
            sb.append("; ");
        }            
            
        sb.append("from: [");
        sb.append(fileName);
        sb.append(":");
        sb.append(Integer.toString(lineNumber));
        sb.append("]");
        sb.append(" ");

        sb.append("{");
        sb.append(className);
        sb.append("#");
        sb.append(methodName);
        sb.append("}");

        return sb.toString();
    }

    protected StackTraceElement getFrame() {
        StackTraceElement[] stack = (new Exception("")).getStackTrace();
        // int stIdx = LegacyLogger.findStackStart(stack);
        int stIdx = -1;
        return stack[stIdx];
    }

    public String format(long duration) {
        StringBuilder sb = new StringBuilder();
        if (duration < 10000) {
            sb.append(Long.toString(duration));
            sb.append(" ms");
        }
        else if (duration < 100000) {
            double nSecs = duration / 1000.0;
            sb.append(Double.toString(nSecs));
            sb.append(" sec");
        }
        else if (duration < 1000000) {
            double nMin = Math.floor(duration / (60 * 1000.0));
            double nSec = (duration - 60.0 * nMin) / 1000.0;
            sb.append(Double.toString(nMin));
            sb.append(":");
            sb.append(Double.toString(nSec));
        }
        else {
            // convert to HH:MM:SS, etc.
            sb.append(Long.toString(duration));
        }
        return sb.toString();
    }
}

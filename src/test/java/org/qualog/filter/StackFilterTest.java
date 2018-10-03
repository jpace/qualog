package org.qualog.filter;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StackFilterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void isClassSkipped(Boolean expected, StackFilter filter, String clsName) {
        Boolean result = filter.isClassSkipped(clsName);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForIsClassSkipped() {
        StackFilter x = new StackFilter();
        StackFilter y = new StackFilter();
        y.addClassSkipped("gr.Ace");
        StackFilter z = new StackFilter(StringArray.empty(), StringArray.of("abc.Def"), StringArray.empty());
        return paramsList(params(true,  x, "tr.Ace"),
                          params(false, x, "gr.Ace"),
                          params(true,  y, "tr.Ace"),
                          params(true,  y, "gr.Ace"),
                          params(false, z, "tr.Ace"),
                          params(true,  z, "abc.Def"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void isMethodSkipped(Boolean expected, StackFilter filter, String methodName) {
        Boolean result = filter.isMethodSkipped(methodName);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForIsMethodSkipped() {
        StackFilter x = new StackFilter();
        StackFilter y = new StackFilter();
        y.addMethodSkipped("log");
        StackFilter z = new StackFilter(StringArray.empty(), StringArray.empty(), StringArray.of("mabc"));
        return paramsList(params(false, x, "log"),
                          params(true,  y, "log"),
                          params(false, y, "slog"),
                          params(false, z, "log"),
                          params(true,  z, "mabc"));
    }

    @Test @Parameters @TestCaseName("{package}(...) #{index} [{params}]")
    public void isPackageSkipped(Boolean expected, StackFilter filter, String clsName) {
        Boolean result = filter.isPackageSkipped(clsName);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForIsPackageSkipped() {
        StackFilter x = new StackFilter();
        StackFilter y = new StackFilter();
        y.addPackageSkipped("org.incava");
        StackFilter z = new StackFilter(StringArray.of("abc"), StringArray.empty(), StringArray.empty());
        return paramsList(params(false, x, "org.incava.Abc"),
                          params(true,  x, "org.qualog.Abc"),
                          params(true,  x, "org.qualog.abc.Def"),
                          params(false, x, "org.qualogx.Abc"),
                          params(true,  y, "org.incava.Abc"),
                          params(false, z, "tr.Ace"),
                          params(true,  z, "abc.Def"));
    }

    @Test @Parameters @TestCaseName("{package}(...) #{index} [{params}]")
    public void isSkipped(Boolean expected, StackFilter filter, String clsName, String methodName) {
        Boolean result = filter.isSkipped(clsName, methodName);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForIsSkipped() {
        StackFilter x = new StackFilter();
        StackFilter y = new StackFilter();
        y.addMethodSkipped("log");
        y.addPackageSkipped("org.incava");
        return paramsList(params(true,  x, "org.qualog.Abc", "xyz"),
                          params(true,  x, "tr.Ace",         "xyz"),
                          params(false, x, "org.incava.Abc", "xyz"),
                          params(false, x, "org.xyz.Abc",    "log"),
                          
                          params(true,  y, "org.qualog.Abc", "xyz"),
                          params(true,  y, "tr.Ace",         "xyz"),
                          params(true,  y, "org.incava.Abc", "xyz"),
                          params(true,  y, "org.xyz.Abc",    "log"));
    }    
}

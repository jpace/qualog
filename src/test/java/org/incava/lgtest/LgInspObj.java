package org.incava.lgtest;


public class LgInspObj {
    public static final String PUB_ST_FIN_CONSTANT = "strvalue1";

    private static final Double PRIV_ST_FIN_CONSTANT = 3.17;

    public static Integer PUB_ST_CONSTANT = 7;

    private static Long PRIV_ST_CONSTANT = 42L;

    public Boolean pub;

    private Character priv;

    public LgInspObj(Boolean pub, Character priv) {
        this.pub = pub;
        this.priv = priv;
    }
}

package org.qualog.format;

public class ClassUtil {
    public String getShortName(String clsName) {
        String[] comps = clsName.split("\\.");

        if (comps.length > 1) {
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < comps.length - 1; ++idx) {
                sb.append(comps[idx].substring(0, 1)).append('.');
            }
            sb.append(comps[comps.length - 1]);
            return sb.toString();
        }
        else {
            return clsName;
        }
    }
}

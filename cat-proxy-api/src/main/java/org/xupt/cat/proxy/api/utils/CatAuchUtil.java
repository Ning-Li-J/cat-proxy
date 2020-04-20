package org.xupt.cat.proxy.api.utils;

/**
 * @author lining
 * @data 2020-04-19 下午3:27
 */
public class CatAuchUtil {
    private static final String SP = "|";

    public static String buildAuchCookie() {
        StringBuilder sb = new StringBuilder();

        sb.append("admin" + SP);
        sb.append("admin" + SP);
        sb.append(System.currentTimeMillis() + SP);
        sb.append("127.0.0.1" + SP);
        Integer code = sb.toString().hashCode();
        sb.append(code);
        return "ct=" + sb.toString();
    }
}

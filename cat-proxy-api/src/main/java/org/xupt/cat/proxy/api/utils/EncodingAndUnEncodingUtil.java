package org.xupt.cat.proxy.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncodingAndUnEncodingUtil {

    public static String unicodeToUTF8(String data) {
        Pattern pattern = Pattern.compile("(\\\\u[0-9A-Fa-f]{4})");
        Matcher matcher = pattern.matcher(data);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {

            String unStr = matcher.group();
            System.out.println("--" + unStr);
            String utfStr = (char)Integer.parseInt(unStr.substring(2), 16) + "";
            System.out.println(utfStr);
            matcher.appendReplacement(sb, utfStr);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}

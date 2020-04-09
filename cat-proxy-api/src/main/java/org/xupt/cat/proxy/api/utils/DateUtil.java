package org.xupt.cat.proxy.api.utils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lining
 * @data 2020-04-08 上午10:12
 */
public class DateUtil {

    private static final DecimalFormat twoIntDF = new DecimalFormat("00");



    public static String nowYYYYMMDDHH() {
        return YYYYMMDDHH(LocalDateTime.now());
    }

    public static String nowYYYYMMDDHHRetreat(int step) {
        return YYYYMMDDHH(LocalDateTime.now().minusHours(step));
    }

    private static String YYYYMMDDHH(LocalDateTime date) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHH");
        /*String year = date.getYear() + "";
        String month = twoIntDF.format(date.getMonth());
        String day = twoIntDF.format(date.getDayOfMonth());
        String hour = twoIntDF.format(date.getHour());
*/
        return dtf.format(date);
    }
}

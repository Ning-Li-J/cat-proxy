package org.xupt.cat.proxy.api.constant;

/**
 * @author lining
 * @data 2020-04-10 下午12:16
 */
public class TargetConstant {

    public static final String TITLE_DISTRIBUTION = "请求持续时间分布";

    public static final String TITLE_HITS_TIME = "请求命中分布";

    public static final String TITLE_AVERAGE = "请求平均持续时间";

    public static final String TITLE_FAILURES_TIME = "失败请求分布";


    public static final String UNITS_MINUTE = "分钟";

    public static final String UNITS_MILL = "毫秒";

    public static final String UNITS_COUNT = "个";


    public static final String[] INDEX_MINUTE = { "0",  "1",  "2",  "3",  "4",
            "5",  "6",  "7",  "8",  "9",
            "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24",
            "25", "26", "27", "28", "29",
            "30", "31", "32", "33", "34",
            "35", "36", "37", "38", "39",
            "40", "41", "42", "43", "44",
            "45", "46", "47", "48", "49",
            "50", "51", "52", "53", "54",
            "55", "56", "57", "58", "59"
    };

    public static final String[] INDEX_MILL = { "0",     "1",     "2",      "4",      "8",
            "16",    "32",    "64",     "128",    "256",
            "512",   "1024",  "2048",   "4096",   "8192",
            "16384", "32768", "65536"
    };
}
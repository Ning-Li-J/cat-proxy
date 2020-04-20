package org.xupt.cat.proxy.api.constant;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-06 下午4:32
 */
@Slf4j
public class SystemConstant {
    public static final Map<String, String> USER_MAP = new HashMap();

    // cat-proxy端口
    public static Integer PORT = 7000;

    // cat-proxy上下文
    public static String CONTEXT_PATH = "/cat-proxy";

    // cat地址
    public static String CAT_IP_PORT = "http://localhost:8080/cat";

    private static final String PORT_NAME = "appPort";
    private static final String CONTEXT_PATH_NAME = "appContext";
    private static final String CAT_IP_PORT_NAME = "catIpPort";
    private static final String UID_PWD = "userRoot";

    static {
        USER_MAP.put("root", "admin");
    }

    public static void parseParam(String[] args) {
        if (Objects.isNull(args) || args.length == 0) {
            log.info("System parse param. Param is empty!");
            return;
        }

        for (String str : args) {
            if (StringUtils.isEmpty(str)) {
                continue;
            }
            String[] values = str.split("=");
            if (values.length != 2 || StringUtils.isEmpty(values[0])) {
                log.error("System parse param. Param format error :{}!", str);
                continue;
            }

            switch (values[0]) {
                case PORT_NAME:
                    if (!StringUtils.isNumeric(values[1])) {
                        log.error("System parse param. Param appPort value format error, :{}!", str);
                        continue;
                    }
                    PORT = Integer.valueOf(values[1]);
                    log.info("System parse param. Set port value is :{}", values[1]);
                    break;
                case CONTEXT_PATH_NAME:
                    CONTEXT_PATH = values[1];
                    log.info("System parse param. Set context value is :{}", values[1]);
                    break;
                case CAT_IP_PORT_NAME:
                    CAT_IP_PORT = values[1];
                    log.info("System parse param. Set cat_ip_port value is :{}", values[1]);
                    break;
                case UID_PWD:
                    if (StringUtils.isEmpty(values[1])) {
                        log.error("System parse param. Param userRoot value format error, :{}", values[1]);
                        continue;
                    }
                    String[] strArry = values[1].split(",");
                    if (strArry.length != 2 || StringUtils.isEmpty(strArry[0]) || StringUtils.isEmpty(strArry[1])) {
                        log.error("System parse param. Param userRoot value format error, :{}", values[1]);
                        continue;
                    }
                    USER_MAP.put(strArry[0], strArry[1]);
                    break;
                default:
                    log.info("System parse param. Invalid param :{}.", str);
                    break;
            }
        }
    }
}

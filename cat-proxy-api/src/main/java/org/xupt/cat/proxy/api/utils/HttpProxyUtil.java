package org.xupt.cat.proxy.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.xupt.cat.proxy.api.constant.SystemConstant;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


/**
 * @author lining
 * @data 2020-04-06 下午4:30
 */
@Slf4j
public class HttpProxyUtil {

    public static Document sendHttp(String uri, Map<String, String> params, Map cookies) throws IOException {
        StringBuilder paramBuild = new StringBuilder();
        boolean isFirst = true;
        if (Objects.nonNull(params)) {
            for (String key : params.keySet()) {
                String value = params.get(key);
                if (StringUtils.isEmpty(value)) {
                    continue;
                }
                if (!isFirst) {
                    paramBuild.append("&");
                }
                paramBuild.append(key + "=" + params.get(key));
                isFirst = false;
            }
        }

        String url = SystemConstant.CAT_IP_PORT + uri + "?" + paramBuild.toString();
        log.info("start connect url :{}", url);
        Connection connection = Jsoup.connect(url);
        connection.header("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        if ( !CollectionUtils.isEmpty(cookies)) {
            connection.cookies(cookies);
        }

        Response response = connection.execute();
        return Jsoup.parse(response.body());
    }
}

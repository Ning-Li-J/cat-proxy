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
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;


/**
 * @author lining
 * @data 2020-04-06 下午4:30
 */
@Slf4j
public class HttpProxyUtil {

    public static Response sendGetHttpResponse(String uri, Map params, String cookieValue) throws IOException {
        String url = SystemConstant.CAT_IP_PORT + uri;
        log.info("start connect url :{}, param :{}", url, JsonUtil.toJson(params));
        Connection connection = Jsoup.connect(url);
        connection.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        if (!StringUtils.isEmpty(cookieValue)) {
            connection.header("Cookie", cookieValue);
        }
        if (Objects.nonNull(params)) {
            params.forEach((k, v) -> {
                if (Objects.nonNull(k) && Objects.nonNull(v)) {
                    connection.data(k.toString(), v.toString());
                }
            });
        }

        Response response = connection.execute();
        return response;
    }

    public static Document sendGetHttp(String uri, Map params, String cookieValue) throws IOException {
        return Jsoup.parse(sendGetHttpResponse(uri, params, cookieValue).body());
    }
}
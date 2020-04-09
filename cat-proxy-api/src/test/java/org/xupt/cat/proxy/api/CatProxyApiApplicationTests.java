package org.xupt.cat.proxy.api;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xupt.cat.proxy.api.utils.DateUtil;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        CatProxyApiApplication.class
})
public class CatProxyApiApplicationTests {
//    @Test
    public void test() throws IOException {
        //String url = "http://localhost:8080/cat/r/t?domain=cat&ip=All&type=URL";

        LocalDateTime dateTime = LocalDateTime.now();
        int hour = dateTime.getHour();
        String value = new DecimalFormat("00").format(hour);
        String url = "http://localhost:8080/cat/r/e?domain=cat&date=2020040813&ip=All&type=ReloadLocal";
        String param = "";
        Map cookies = new HashMap();
        //cookies.put("CAT_DOMAINS", "cat|thoughtcoding-api");
        //cookies.put("JSESSIONID", "E5EB0B8FEEA0FC1265F8A36C192CFD26");
        //cookies.put("_ga", "GA1.1.286861500.1569143707");


        Connection connection = Jsoup.connect(url);
        connection.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");

        connection.cookies(cookies);

        Connection.Response response = connection.execute();
        Document document = Jsoup.parse(response.body());

        Elements elements = document.getElementsByClass(" right");
        boolean isFirst = true;
        for (Element e : elements) {
            if (isFirst) {
                isFirst = false;
                continue;
            }
            Elements tdElements = e.getElementsByTag("td");
            tdElements.get(0);
        }
    }


    @Test
    public void tests() {
        String[] a = "=aa".split("=");
        System.out.println(Arrays.toString(a));

    }

}

package org.xupt.cat.proxy.api;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xupt.cat.proxy.api.domain.responses.TargetInfo;
import org.xupt.cat.proxy.api.service.ICore;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        CatProxyApiApplication.class
})
public class CatProxyApiApplicationTests {

    @Autowired
    private ICore core;
    //@Test
    public void test() throws IOException {
        //String url = "http://localhost:8080/cat/r/t?domain=cat&ip=All&type=URL";

        LocalDateTime dateTime = LocalDateTime.now();
        int hour = dateTime.getHour();
        String value = new DecimalFormat("00").format(hour);
        String url = "http://localhost:8080/cat/r/h?domain=&ip=All&date=2020041016&reportType=day&op=view";
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

        Elements groupElements = document.getElementById("heartGroup").getElementsByTag("tr");
        for (int i = 0; i < groupElements.size(); i += 2) {

            Element groupNameElement = groupElements.get(i).getElementById("groupTitle");
            System.out.println(groupNameElement.text());


            List<TargetInfo> targetInfoList = core.parseTargetList(groupElements.get(i + 1));
            for (TargetInfo targetInfo : targetInfoList) {
                System.out.println();
                System.out.println(targetInfo);
            }
        }

    }

    @Test
    public void tests() {
        String[] a = "=aa".split("=");
        System.out.println(Arrays.toString(a));

    }

}

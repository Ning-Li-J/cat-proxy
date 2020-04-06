package org.xupt.cat.proxy.api;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xupt.cat.proxy.api.service.TransactionService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        CatProxyApiApplication.class
})
public class CatProxyApiApplicationTests {
    @Autowired
    private TransactionService service;

    @Test
    public void test() throws IOException {
        String url = "http://localhost:8080/cat/r/t?domain=cat&ip=All&type=URL";
        String param = "";
        Map cookies = new HashMap();
        //cookies.put("CAT_DOMAINS", "cat|thoughtcoding-api");
        //cookies.put("JSESSIONID", "E5EB0B8FEEA0FC1265F8A36C192CFD26");
        //cookies.put("_ga", "GA1.1.286861500.1569143707");


        Connection connection = Jsoup.connect(url);
        connection.header("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");

        connection.cookies(cookies);

        Connection.Response response = connection.execute();
        Document document = Jsoup.parse(response.body());
        Elements element = document.getElementsByClass(" right");
        System.out.println(element.size());
        for (int i = 1; i < element.size(); i++) {

            System.out.println("----------------------");
            Elements elements = element.get(i).getElementsByTag("td");
            Element typeElement = elements.get(0);
            String type = typeElement.childNode(2).toString();

            System.out.println("size:" + elements.size());
            System.out.println("index0:" + type);
            System.out.println("index1:" + elements.get(1).childNode(0));

        }
    }
}

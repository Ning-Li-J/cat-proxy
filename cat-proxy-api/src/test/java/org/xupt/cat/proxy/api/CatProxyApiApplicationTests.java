package org.xupt.cat.proxy.api;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionInfoResponse;
import org.xupt.cat.proxy.api.utils.JsonUtil;

import java.io.IOException;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        CatProxyApiApplication.class
})
public class CatProxyApiApplicationTests {
    @Test
    public void test() throws IOException {
        //String url = "http://localhost:8080/cat/r/t?domain=cat&ip=All&type=URL";
        String url = "http://localhost:8080/cat/r/t?op=graphs&domain=cat&date=2020040713&ip=All&type=Checkpoint";
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

        List<TransactionInfoResponse.BranchInfo> branchInfoList = new ArrayList<>();
        Elements branchElement = document.getElementsByClass(" right");
        for (Element element : branchElement) {
            Elements branchIndoElements = element.getElementsByTag("td");

            TransactionInfoResponse.BranchInfo branchInfo = new TransactionInfoResponse.BranchInfo();
            branchInfo.setIp(branchIndoElements.get(0).childNode(0).toString());
            branchInfo.setTotal(branchIndoElements.get(1).childNode(0).toString());
            branchInfo.setFailure(branchIndoElements.get(2).childNode(0).toString());
            branchInfo.setMin(branchIndoElements.get(4).childNode(0).toString());
            branchInfo.setMax(branchIndoElements.get(5).childNode(0).toString());
            branchInfo.setAvg(branchIndoElements.get(7).childNode(0).toString());
            branchInfo.setStd(branchIndoElements.get(7).childNode(0).toString());
            branchInfoList.add(branchInfo);
        }
        System.out.println(JsonUtil.toJson(branchInfoList));




    }

    private TransactionInfoResponse.Point[] parseData(Element element) {
        List<TransactionInfoResponse.Point> list = new ArrayList<>();
        Elements elements = element.getElementsByTag("rect");
        for (Element e : elements) {
            TransactionInfoResponse.Point point = new TransactionInfoResponse.Point();
            point.setX(e.attr("xValue"));
            point.setY(e.attr("yValue"));
            list.add(point);
        }

        TransactionInfoResponse.Point[] rest = new TransactionInfoResponse.Point[list.size()];
        return list.toArray(rest);
    }


}

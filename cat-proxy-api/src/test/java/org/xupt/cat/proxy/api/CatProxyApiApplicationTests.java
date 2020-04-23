package org.xupt.cat.proxy.api;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.util.StringUtils;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.AlertRule;
import org.xupt.cat.proxy.api.domain.Condition;
import org.xupt.cat.proxy.api.domain.responses.alert.AlertRuleSimpResponse;
import org.xupt.cat.proxy.api.service.ICore;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        CatProxyApiApplication.class
})
@Slf4j
public class CatProxyApiApplicationTests {

    @Autowired
    private ICore core;
    //@Test
    public void test() throws IOException {
        Map<String, String> param = new HashMap<>();
        param.put("op", "eventRuleDelete");
        param.put("ruleId", "cat;URL;URL.Server;count");

        //String cookie = "ct=admin|admin|1587211029247|127.0.0.1|526355560";
        String cookie = "ct=" + buildCookie();

        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp("/s/config", param, cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(document);
    }

    @Test
    public void login() {
        String data = "[{\"starttime\":\"00:00\",\"endtime\":\"24:00\",\"conditions\":[{\"minute\":2,\"alertType\":\"warning\",\"sub-conditions\":[{\"type\":\"MinVal\",\"text\":\"100\"},{\"type\":\"MinVal\",\"text\":\"100\"}]},{\"minute\":2,\"alertType\":\"warning\",\"sub-conditions\":[{\"type\":\"MaxVal\",\"text\":\"20\"}]}]},{\"starttime\":\"00:00\",\"endtime\":\"24:00\",\"conditions\":[{\"minute\":3,\"alertType\":\"error\",\"sub-conditions\":[{\"type\":\"MaxVal\",\"text\":\"100\"}]}]}]";

        System.out.println();
        System.out.println(data.replaceAll("sub-conditions", "subConditions"));


    }

    private static class Text{
        private List<Condition.SubCondition> subConditions;
    }

    public static class MyTe{
        private String name;
    }

    private String buildCookie() {
        StringBuilder sb = new StringBuilder();
        sb.append("admin|");
        sb.append("admin|");
        sb.append(System.currentTimeMillis() + "|");
        sb.append("127.0.0.1" + "|");
        Integer code = sb.toString().hashCode();
        sb.append(code);
        return sb.toString();
    }
}

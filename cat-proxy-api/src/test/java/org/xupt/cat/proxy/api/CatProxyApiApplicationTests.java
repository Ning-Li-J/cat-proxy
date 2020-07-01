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

    @Test
    public void test() throws IOException {
        String value = "[CAT Transaction\\u544A\\u8B66] [\\u9879\\u76EE: thoughtcoding-api] [\\u76D1\\u63A7\\u9879: URL-/thoughtcoding/api    /auth/login-count],[CAT Transaction\\u544A\\u8B66: thoughtcoding-api URL /thoughtcoding/api/auth/login] : [\\u5B9E\\u9645\\u503C:20 ] [\\u5B9E\\u9645\\u503C\\u603B\\u548C:20] [\\u603B\\u548C\\u670    0\\u5927\\u9608\\u503C: 10 ][\\u544A\\u8B66\\u65F6\\u95F4:2020-06-03 19:28:03]<br/>[\\u65F6\\u95F4: 2020-06-03 19:28] \\n<a href='http://cat-web-server/cat/r/t?domain=thoughtcoding-api&type=URL    &name=/thoughtcoding/api/auth/login&date=2020060319'>\\u70B9\\u51FB\\u6B64\\u5904\\u67E5\\u770B\\u8BE6\\u60C5</a><br/><br/>[\\u544A\\u8B66\\u95F4\\u9694\\u65F6\\u95F4]1\\u5206\\u949F";
        System.out.println(value);
    }

    @Test
    public void login() {
        String data = "[{\"starttime\":\"00:00\",\"endtime\":\"24:00\",\"conditions\":[{\"minute\":2,\"alertType\":\"warning\",\"sub-conditions\":[{\"type\":\"MinVal\",\"text\":\"100\"},{\"type\":\"MinVal\",\"text\":\"100\"}]},{\"minute\":2,\"alertType\":\"warning\",\"sub-conditions\":[{\"type\":\"MaxVal\",\"text\":\"20\"}]}]},{\"starttime\":\"00:00\",\"endtime\":\"24:00\",\"conditions\":[{\"minute\":3,\"alertType\":\"error\",\"sub-conditions\":[{\"type\":\"MaxVal\",\"text\":\"100\"}]}]}]";

        System.out.println();
        System.out.println(data.replaceAll("sub-conditions", "subConditions"));


    }

    private static class Text {
        private List<Condition.SubCondition> subConditions;
    }

    public static class MyTe {
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

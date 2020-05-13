package org.xupt.cat.proxy.api.service.alert.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.xupt.cat.proxy.api.domain.AlertRule;
import org.xupt.cat.proxy.api.domain.responses.alert.AlertRuleResponse;
import org.xupt.cat.proxy.api.domain.responses.alert.AlertRuleSimpResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.alert.IAlertCore;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lining
 * @data 2020-04-19 下午3:33
 */
@Service
@Slf4j
public class AlertCoreImp implements IAlertCore {
    private static final Pattern RULE_PATTERN = Pattern.compile("configsText = '(.+)';");


    @Override
    public AlertRuleSimpResponse covertRuleSimpList(Document document) {
        Elements elements = document.getElementsByTag("table");
        Elements trElements = elements.get(0).getElementsByTag("tbody").get(0).getElementsByTag("tr");

        List<AlertRuleSimpResponse.RuleSimpInfo> ruleSimpInfoList = new ArrayList<>();
        for (Element e : trElements) {
            Elements tdElements = e.getElementsByTag("td");

            AlertRuleSimpResponse.RuleSimpInfo ruleSimpInfo = new AlertRuleSimpResponse.RuleSimpInfo();
            ruleSimpInfo.setDomain(tdElements.get(0).text());
            ruleSimpInfo.setType(tdElements.get(1).text());
            ruleSimpInfo.setName(tdElements.get(2).text());
            ruleSimpInfo.setItem(tdElements.get(3).text());
            ruleSimpInfo.setAvailable(tdElements.get(4).text());

            ruleSimpInfoList.add(ruleSimpInfo);
        }
        AlertRuleSimpResponse response = new AlertRuleSimpResponse();
        response.setRuleSimpInfoList(ruleSimpInfoList);

        return response;
    }

    @Override
    public AlertRuleResponse covertRule(Document document) {
        log.info("start covert rule ......");
        AlertRuleResponse response = new AlertRuleResponse();

        Matcher matcher = RULE_PATTERN.matcher(document.toString());
        String json = null;
        while (matcher.find()) {
            json = matcher.group(1);
        }
        if (StringUtils.isEmpty(json)) {
            log.error("covert rule info, but document not have config");
            return response;

        }

        json = json.replaceAll("sub-conditions", "subConditions");
        List<AlertRule> alertRuleList = JsonUtil.fromJson(json, new TypeReference<List<AlertRule>>() {
        });

        response.setAlertRuleList(alertRuleList);
        return response;
    }
}

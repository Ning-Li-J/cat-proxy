package org.xupt.cat.proxy.api.service.alert;

import org.jsoup.nodes.Document;
import org.xupt.cat.proxy.api.domain.responses.alert.AlertRuleResponse;
import org.xupt.cat.proxy.api.domain.responses.alert.AlertRuleSimpResponse;

/**
 * @author lining
 * @data 2020-04-19 下午3:32
 */
public interface IAlertCore {

    AlertRuleSimpResponse covertRuleSimpList(Document document);

    AlertRuleResponse covertRule(Document document);
}

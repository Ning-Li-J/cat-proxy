package org.xupt.cat.proxy.api.domain.responses.alert;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.AlertRule;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-19 下午5:50
 */
@Data
public class AlertRuleResponse {

    private List<AlertRule> alertRuleList;
}

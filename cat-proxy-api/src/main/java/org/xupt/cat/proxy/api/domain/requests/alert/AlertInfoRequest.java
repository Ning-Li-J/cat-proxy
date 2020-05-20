package org.xupt.cat.proxy.api.domain.requests.alert;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.AlertRule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lining
 * @data 2020-04-19 下午6:57
 */
@Data
public class AlertInfoRequest extends AlertBaseRequest {

    @NotNull
    private List<AlertRule> alertRuleList;

    @NotBlank
    private String ruleId;

    @NotNull
    private Boolean available;
}

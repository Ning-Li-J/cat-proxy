package org.xupt.cat.proxy.api.domain.requests.alert;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-19 下午6:20
 */
@Data
public class AlertRequest extends AlertBaseRequest {

    @NotBlank
    private String ruleId;
}

package org.xupt.cat.proxy.api.domain.requests.alert;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-19 下午6:20
 */
@Getter
@Setter
public class AlertRequest extends AlertBaseRequest {

    @NotBlank
    private String ruleId;
}

package org.xupt.cat.proxy.api.domain.requests.alert;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author lining
 * @data 2020-04-19 下午6:12
 */
@Data
public class AlertBaseRequest {

    @Pattern(regexp = "transaction|event")
    private String operation;
}

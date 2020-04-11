package org.xupt.cat.proxy.api.domain.requests.host;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-11 下午3:53
 */
@Data
public class HostIpRequest {

    @NotBlank
    private String domain;

    /**
     * 相对当前时间后退多少小时
     * step <= 0
     */
    @Max(value = 0, message = "step mast <= 0!")
    private Integer step;
}

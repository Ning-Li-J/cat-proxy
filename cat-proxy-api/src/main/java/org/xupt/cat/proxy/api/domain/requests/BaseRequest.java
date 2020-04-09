package org.xupt.cat.proxy.api.domain.requests;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-08 上午9:54
 */
@Data
public abstract class BaseRequest {
    @NotBlank(message = "ip can not be blank!")
    private String ip;

    @NotBlank(message = "domain can not be blank!")
    private String domain;

    /**
     * 相对当前时间后退多少小时
     * step <= 0
     */
    @Max(value = 0, message = "step mast <= 0!")
    private Integer step;

}

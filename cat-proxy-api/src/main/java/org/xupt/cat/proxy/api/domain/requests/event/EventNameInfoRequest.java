package org.xupt.cat.proxy.api.domain.requests.event;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.requests.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-08 下午2:04
 */
@Data
public class EventNameInfoRequest extends BaseRequest {
    @NotBlank
    private String type;

    @NotBlank
    private String name;
}

package org.xupt.cat.proxy.api.domain.requests.event;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.requests.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-08 下午12:37
 */
@Data
public class EventAllNameRequest extends BaseRequest {

    @NotBlank(message = "type can not be empty!")
    private String type;

}

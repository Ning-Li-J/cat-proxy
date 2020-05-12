package org.xupt.cat.proxy.api.domain.requests.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xupt.cat.proxy.api.domain.requests.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-08 下午12:37
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EventAllNameRequest extends BaseRequest {

    @NotBlank(message = "type can not be empty!")
    private String type;

}

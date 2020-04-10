package org.xupt.cat.proxy.api.domain.requests.heart;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.requests.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-10 下午12:45
 */
@Data
public class HeartRequest extends BaseRequest {

    @NotBlank
    private String reportType;

}

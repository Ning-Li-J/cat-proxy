package org.xupt.cat.proxy.api.domain.requests.transaction;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.requests.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-07 下午12:33
 */
@Data
public class TransactionNameInfoRequest extends BaseRequest {

    @NotBlank
    private String type;

    @NotBlank
    private String name;

}

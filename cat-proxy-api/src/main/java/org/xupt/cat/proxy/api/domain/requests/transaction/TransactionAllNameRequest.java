package org.xupt.cat.proxy.api.domain.requests.transaction;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.requests.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-06 下午7:35
 */
@Data
public class TransactionAllNameRequest extends BaseRequest {
    @NotBlank(message = "type can not be empty!")
    private String type;
}

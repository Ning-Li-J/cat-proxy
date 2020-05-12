package org.xupt.cat.proxy.api.domain.requests.transaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xupt.cat.proxy.api.domain.requests.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-07 下午12:33
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TransactionTypeInfoRequest extends BaseRequest {

    @NotBlank
    private String type;
}

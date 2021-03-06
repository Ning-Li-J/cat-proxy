package org.xupt.cat.proxy.api.domain.requests.transaction;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.requests.BaseRequest;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-06 下午4:47
 */
@Data
public class TransactionAllTypeRequest extends BaseRequest {

    @NotBlank
    private String reportType;
}

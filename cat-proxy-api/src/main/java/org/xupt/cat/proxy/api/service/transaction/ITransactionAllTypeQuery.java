package org.xupt.cat.proxy.api.service.transaction;

import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionAllTypeRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-06 下午4:27
 */
public interface ITransactionAllTypeQuery {

    BaseResponse queryAllType(TransactionAllTypeRequest request);


}

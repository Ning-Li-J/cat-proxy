package org.xupt.cat.proxy.api.service.transaction;

import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionTypeInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-07 下午12:19
 */
public interface ITransactionTypeInfoQuery {

    BaseResponse queryTypeInfo(TransactionTypeInfoRequest request);
}

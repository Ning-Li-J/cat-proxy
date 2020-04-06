package org.xupt.cat.proxy.api.service.transaction;

import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionAllNameRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-06 下午7:38
 */
public interface ITransactionAllNameQuery {

    BaseResponse queryAllName(TransactionAllNameRequest request);
}

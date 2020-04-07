package org.xupt.cat.proxy.api.service.transaction;

import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionNameInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-07 下午2:42
 */
public interface ITransactionNameInfoQuery {

    BaseResponse queryNameInfo(TransactionNameInfoRequest request);
}

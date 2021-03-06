package org.xupt.cat.proxy.api.service.transaction;

import org.jsoup.nodes.Document;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionInfoResponse;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionResponse;

/**
 * @author lining
 * @data 2020-04-06 下午7:40
 */
public interface ITransactionCore {

    TransactionResponse covertTransaction(Document document, boolean isName);

    TransactionInfoResponse coverTransactionInfo(Document document);
}

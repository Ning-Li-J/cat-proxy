package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionResponse;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lining
 * @data 2020-04-06 下午7:40
 */
@Service
@Slf4j
public class TransactionCoreImp implements ITransactionCore {

    @Override
    public TransactionResponse covertTransactionDocu(Document document, boolean isName) {
        List<TransactionResponse.Transaction> transactions = new ArrayList<>();

        Elements elements = document.getElementsByClass(" right");
        for (int i = 0; i < elements.size(); i++) {
            Elements trElements = elements.get(i).getElementsByTag("td");
            TransactionResponse.Transaction transaction = new TransactionResponse.Transaction();

            String type = null;
            //name 第一行为汇总的
            if (isName) {
                if (i == 0) {
                    //isName 跳过第一行汇总的
                    continue;
                }
                type = trElements.get(0).childNode(2).toString();
            } else {
                type = trElements.get(0).getElementsByTag("a").get(1).childNode(0).toString();
            }

            transaction.setType(type);
            transaction.setTotal(trElements.get(1).childNode(0).toString());
            transaction.setFailure(trElements.get(2).childNode(0).toString());
            transaction.setMin(trElements.get(5).childNode(0).toString());
            transaction.setMax(trElements.get(6).childNode(0).toString());
            transaction.setAvg(trElements.get(7).childNode(0).toString());
            transaction.setLine95(trElements.get(8).childNode(0).toString());
            transaction.setLine999(trElements.get(9).childNode(0).toString());
            transaction.setStd(trElements.get(10).childNode(0).toString());
            transaction.setQps(trElements.get(11).childNode(0).toString());
            transactions.add(transaction);
        }

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactions(transactions);
        return transactionResponse;
    }
}

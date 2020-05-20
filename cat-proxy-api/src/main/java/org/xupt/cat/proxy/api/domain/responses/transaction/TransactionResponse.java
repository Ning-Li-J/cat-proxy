package org.xupt.cat.proxy.api.domain.responses.transaction;

import lombok.Data;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-06 下午5:18
 */
@Data
public class TransactionResponse {
    private List<Transaction> transactionList;

    @Data
    public static class Transaction {
        private String name;
        private String total;
        private String failure;
        private String min;
        private String max;
        private String avg;
        private String line95;
        private String line999;
        private String std;
        private String qps;
    }
}

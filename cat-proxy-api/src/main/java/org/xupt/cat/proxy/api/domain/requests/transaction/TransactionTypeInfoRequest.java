package org.xupt.cat.proxy.api.domain.requests.transaction;

import lombok.Data;

/**
 * @author lining
 * @data 2020-04-07 下午12:33
 */
@Data
public class TransactionTypeInfoRequest {
    private String domain;

    private String op;

    private String date;

    private String ip;

    private String type;
}

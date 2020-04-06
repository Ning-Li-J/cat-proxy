package org.xupt.cat.proxy.api.domain.requests.transaction;

import lombok.Data;

/**
 * @author lining
 * @data 2020-04-06 下午4:47
 */
@Data
public class TransactionAllTypeRequest {
    /*
    domain: cat
    ip: All
    date: 2020040616
    reportType: day
    op: view
    */
    private String domain;
    private String ip;
    private String data;
    private String reportType;
    private String op;
}

package org.xupt.cat.proxy.api.domain.requests.transaction;

import lombok.Data;

/**
 * @author lining
 * @data 2020-04-06 下午7:35
 */
@Data
public class TransactionAllNameRequest {
/*
    domain: cat
    date: 2020040619
    ip: All
    type: Dumper
    */
    private String domain;
    private String date;
    private String ip;
    private String type;
}

package org.xupt.cat.proxy.api.controller;

import javafx.scene.chart.ValueAxis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionAllNameRequest;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionAllTypeRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.service.transaction.ITransactionAllNameQuery;
import org.xupt.cat.proxy.api.service.transaction.ITransactionAllTypeQuery;
import org.xupt.cat.proxy.api.utils.JsonUtil;

@RequestMapping("/transaction")
@RestController
@Slf4j
public class TransactionQuery {

    @Autowired
    private ITransactionAllTypeQuery transactionAllTypeQuery;

    @Autowired
    private ITransactionAllNameQuery transactionAllNameQuery;

    @RequestMapping(value = "/allType", method = RequestMethod.GET)
    public BaseResponse queryAllType(@RequestBody TransactionAllTypeRequest request) {
        log.info("query transaction all type. request :{}", JsonUtil.toJson(request));
        BaseResponse response = transactionAllTypeQuery.queryAllType(request);
        log.info("query transaction all type. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public BaseResponse queryType() {

        return null;
    }

    @RequestMapping(value = "/allName", method = RequestMethod.GET)
    public BaseResponse queryAllName(@RequestBody TransactionAllNameRequest request) {
        log.info("query transaction all name. request :{}", JsonUtil.toJson(request));
        BaseResponse response = transactionAllNameQuery.queryAllName(request);
        log.info("query transaction all name. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public BaseResponse queryName() {
        return null;
    }




}

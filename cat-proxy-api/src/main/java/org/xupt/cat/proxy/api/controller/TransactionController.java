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
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionNameInfoRequest;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionTypeInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.service.transaction.ITransactionAllNameQuery;
import org.xupt.cat.proxy.api.service.transaction.ITransactionAllTypeQuery;
import org.xupt.cat.proxy.api.service.transaction.ITransactionNameInfoQuery;
import org.xupt.cat.proxy.api.service.transaction.ITransactionTypeInfoQuery;
import org.xupt.cat.proxy.api.utils.JsonUtil;

import javax.validation.Valid;

@RequestMapping("/transaction")
@RestController
@Slf4j
public class TransactionController {

    @Autowired
    private ITransactionAllTypeQuery transactionAllTypeQuery;

    @Autowired
    private ITransactionAllNameQuery transactionAllNameQuery;

    @Autowired
    private ITransactionTypeInfoQuery transactionTypeInfoQuery;

    @Autowired
    private ITransactionNameInfoQuery transactionNameInfoQuery;

    @RequestMapping(value = "/allType", method = RequestMethod.POST)
    public BaseResponse queryAllType(@Valid @RequestBody TransactionAllTypeRequest request) {
        log.info("query transaction all type. request :{}", JsonUtil.toJson(request));
        BaseResponse response = transactionAllTypeQuery.queryAllType(request);
        log.info("query transaction all type. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @RequestMapping(value = "/typeInfo", method = RequestMethod.POST)
    public BaseResponse queryType(@Valid @RequestBody TransactionTypeInfoRequest request) {
        log.info("query transaction type info. request :{}", JsonUtil.toJson(request));
        BaseResponse response = transactionTypeInfoQuery.queryTypeInfo(request);
        log.info("query transaction type info. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @RequestMapping(value = "/allName", method = RequestMethod.POST)
    public BaseResponse queryAllName(@Valid @RequestBody TransactionAllNameRequest request) {
        log.info("query transaction all name. request :{}", JsonUtil.toJson(request));
        BaseResponse response = transactionAllNameQuery.queryAllName(request);
        log.info("query transaction all name. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @RequestMapping(value = "/nameInfo", method = RequestMethod.POST)
    public BaseResponse queryName(@Valid @RequestBody TransactionNameInfoRequest request) {
        log.info("query transaction name info. request :{}", JsonUtil.toJson(request));
        BaseResponse response = transactionNameInfoQuery.queryNameInfo(request);
        log.info("query transaction name info. response :{}", JsonUtil.toJson(response));
        return response;
    }


}

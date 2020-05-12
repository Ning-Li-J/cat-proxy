package org.xupt.cat.proxy.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xupt.cat.proxy.api.controller.annocation.Auch;
import org.xupt.cat.proxy.api.domain.requests.alert.AlertBaseRequest;
import org.xupt.cat.proxy.api.domain.requests.alert.AlertInfoRequest;
import org.xupt.cat.proxy.api.domain.requests.alert.AlertRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.service.alert.IAlertCommand;
import org.xupt.cat.proxy.api.service.alert.IAlertQuery;
import org.xupt.cat.proxy.api.utils.JsonUtil;

import javax.validation.Valid;

/**
 * @author lining
 * @data 2020-04-19 上午11:53
 */
@RequestMapping("/alert")
@RestController
@Slf4j
@Validated
public class AlertController {

    @Autowired
    private IAlertCommand alertCommand;

    @Autowired
    private IAlertQuery alertQuery;

    @Auch
    @RequestMapping(value = "/simpRule", method = RequestMethod.POST)
    public BaseResponse queryRuleAll(@Valid @RequestBody AlertBaseRequest request) {
        log.info("query all rule simp info. request :{}", JsonUtil.toJson(request));
        BaseResponse response = alertQuery.queryAllAlert(request);
        log.info("query all rule simp info. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @Auch
    @RequestMapping(value = "/ruleInfo", method = RequestMethod.POST)
    public BaseResponse queryRuleInfo(@Valid @RequestBody AlertRequest request) {
        log.info("query rule info. request :{}", JsonUtil.toJson(request));
        BaseResponse response = alertQuery.queryAlert(request);
        log.info("query rule info. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @Auch
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResponse deleteRule(@Valid @RequestBody AlertRequest request) {
        log.info("delete rule. request :{}", JsonUtil.toJson(request));
        BaseResponse response = alertCommand.deletAlertRule(request);
        log.info("delete rule. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @Auch
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BaseResponse createUpdate(@Valid @RequestBody AlertInfoRequest request) {
        log.info("create/update rule. request :{}", JsonUtil.toJson(request));
        BaseResponse response = alertCommand.createAlertRule(request);
        log.info("create/update rule. response :{}", JsonUtil.toJson(response));
        return response;
    }



}

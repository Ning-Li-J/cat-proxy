package org.xupt.cat.proxy.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xupt.cat.proxy.api.domain.requests.host.HostIpRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.service.host.IHostIpQuery;
import org.xupt.cat.proxy.api.utils.JsonUtil;

import javax.validation.Valid;

/**
 * @author lining
 * @data 2020-04-11 下午2:01
 */
@RequestMapping("/host")
@RestController
@Slf4j
public class HostController {

    @Autowired
    private IHostIpQuery hostIpQuery;

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public BaseResponse queryHostIp(@Valid @RequestBody HostIpRequest hostIpRequest) {
        log.info("query host ip. request :{}", JsonUtil.toJson(hostIpRequest));
        BaseResponse response = hostIpQuery.queryHostIp(hostIpRequest);
        log.info("query host ip. response :{}", JsonUtil.toJson(response));
        return response;
    }
}

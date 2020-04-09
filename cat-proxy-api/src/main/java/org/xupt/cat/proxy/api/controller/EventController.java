package org.xupt.cat.proxy.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xupt.cat.proxy.api.domain.requests.event.EventAllNameRequest;
import org.xupt.cat.proxy.api.domain.requests.event.EventAllTypeRequest;
import org.xupt.cat.proxy.api.domain.requests.event.EventNameInfoRequest;
import org.xupt.cat.proxy.api.domain.requests.event.EventTypeInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.service.event.IEventAllNameQuery;
import org.xupt.cat.proxy.api.service.event.IEventAllTypeQuery;
import org.xupt.cat.proxy.api.service.event.IEventNameInfoQuery;
import org.xupt.cat.proxy.api.service.event.IEventTypeInfoQuery;
import org.xupt.cat.proxy.api.utils.JsonUtil;

import javax.validation.Valid;

/**
 * @author lining
 * @data 2020-04-08 下午12:30
 */

@RequestMapping("/event")
@RestController
@Slf4j
public class EventController {

    @Autowired
    private IEventAllNameQuery eventAllNameQuery;

    @Autowired
    private IEventAllTypeQuery eventAllTypeQuery;

    @Autowired
    private IEventTypeInfoQuery eventTypeInfoQuery;

    @Autowired
    private IEventNameInfoQuery eventNameInfoQuery;

    @RequestMapping(value = "/allType", method = RequestMethod.GET)
    public BaseResponse queryAllType(@Valid @RequestBody EventAllTypeRequest request) {
        log.info("query event all type. request :{}", JsonUtil.toJson(request));
        BaseResponse response = eventAllTypeQuery.queryAllType(request);
        log.info("query event all type. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @RequestMapping(value = "/typeInfo", method = RequestMethod.GET)
    public BaseResponse queryType(@Valid @RequestBody EventTypeInfoRequest request) {
        log.info("query event type info. request :{}", JsonUtil.toJson(request));
        BaseResponse response = eventTypeInfoQuery.queryTypeInfo(request);
        log.info("query event type info. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @RequestMapping(value = "/allName", method = RequestMethod.GET)
    public BaseResponse queryAllName(@Valid @RequestBody EventAllNameRequest request) {
        log.info("query event all name. request :{}", JsonUtil.toJson(request));
        BaseResponse response = eventAllNameQuery.queryAllName(request);
        log.info("query event all name. response :{}", JsonUtil.toJson(response));
        return response;
    }

    @RequestMapping(value = "/nameInfo", method = RequestMethod.GET)
    public BaseResponse queryName(@Valid @RequestBody EventNameInfoRequest request) {
        log.info("query event name info. request :{}", JsonUtil.toJson(request));
        BaseResponse response = eventNameInfoQuery.queryNameInfo(request);
        log.info("query event name info. response :{}", JsonUtil.toJson(response));
        return response;
    }
}

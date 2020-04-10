package org.xupt.cat.proxy.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xupt.cat.proxy.api.domain.requests.event.EventAllTypeRequest;
import org.xupt.cat.proxy.api.domain.requests.heart.HeartRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.service.heart.IHeartQuery;
import org.xupt.cat.proxy.api.utils.JsonUtil;

import javax.validation.Valid;

/**
 * @author lining
 * @data 2020-04-10 下午12:41
 */
@RequestMapping("/heart")
@RestController
@Slf4j
public class HeartController {

    @Autowired
    private IHeartQuery heartQuery;

    @RequestMapping(value = "/hostHeart", method = RequestMethod.GET)
    public BaseResponse queryHostHeart(@Valid @RequestBody HeartRequest request) {
        log.info("query host info. request :{}", JsonUtil.toJson(request));
        BaseResponse response = heartQuery.queryHostHeart(request);
        log.info("query host info. response :{}", JsonUtil.toJson(response));
        return response;
    }
}

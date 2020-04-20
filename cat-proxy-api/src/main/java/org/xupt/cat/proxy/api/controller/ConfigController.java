package org.xupt.cat.proxy.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.Map;

/**
 * @author lining
 * @data 2020-04-17 上午10:04
 */
@RequestMapping("/config")
@RestController
@Slf4j
public class ConfigController {

    @RequestMapping(value = "/mail/send", method = {RequestMethod.POST, RequestMethod.GET})
    public BaseResponse sendMail(@RequestParam Map map) {
        log.info("send mail :{}", JsonUtil.toJson(map));
        return ResponseUtil.buildSuccessResponce();
    }
}
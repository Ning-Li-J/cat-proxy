package org.xupt.cat.proxy.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xupt.cat.proxy.api.domain.requests.auch.LoginRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.service.auch.ILoginCommand;
import org.xupt.cat.proxy.api.utils.JsonUtil;

import javax.validation.Valid;

/**
 * @author lining
 * @data 2020-04-18 下午6:51
 */
@RequestMapping("/auth")
@RestController
@Slf4j
public class AuchController {

    @Autowired
    private ILoginCommand loginCommand;

    /**
     * 登陆
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse login(@Valid @RequestBody LoginRequest request) {
        log.info("/api/auth login, request :{}", JsonUtil.toJson(request));
        BaseResponse response = loginCommand.verifyLogin(request);
        log.info("/api/auth login, response :{}", JsonUtil.toJson(response));
        return response;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseResponse logout() {
        log.info("/api/auth logout.");
        BaseResponse response = loginCommand.logout();
        log.info("/api/auth logout, response :{}", JsonUtil.toJson(response));
        return response;
    }

}

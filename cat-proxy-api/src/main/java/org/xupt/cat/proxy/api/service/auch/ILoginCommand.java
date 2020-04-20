package org.xupt.cat.proxy.api.service.auch;

import org.xupt.cat.proxy.api.domain.requests.auch.LoginRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-18 下午6:53
 */
public interface ILoginCommand {

    BaseResponse verifyLogin(LoginRequest request);
}

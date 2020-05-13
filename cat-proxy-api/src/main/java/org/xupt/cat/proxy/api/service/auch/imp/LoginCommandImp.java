package org.xupt.cat.proxy.api.service.auch.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.domain.requests.auch.LoginRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.auch.ILoginCommand;
import org.xupt.cat.proxy.api.utils.HttpSessionUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-18 下午6:53
 */
@Slf4j
@Service
public class LoginCommandImp implements ILoginCommand {
    @Override
    public BaseResponse verifyLogin(LoginRequest request) {
        BaseResponse baseResponse = checkParam(request);
        if (Objects.nonNull(baseResponse)) {
            return baseResponse;
        }

        HttpSessionUtil.setSessionId(System.currentTimeMillis() + "");
        return ResponseUtil.buildSuccessResponce();
    }

    @Override
    public BaseResponse logout() {

        try {
            HttpSessionUtil.invalidate();
        } catch (Exception e) {
            log.info("logout error. e :{}", e);
            return ResponseUtil.buildFailResponce(ErrorCode.LOGOUT_ERROR);
        }
        return ResponseUtil.buildSuccessResponce();
    }

    private BaseResponse checkParam(LoginRequest request) {
        if (!SystemConstant.USER_MAP.containsKey(request.getUid())) {
            return ResponseUtil.buildFailResponce(ErrorCode.USER_NO_EXIST);
        }

        if (!SystemConstant.USER_MAP.get(request.getUid()).equals(request.getPwd())) {
            return ResponseUtil.buildFailResponce(ErrorCode.PWD_ERROR);
        }

        return null;
    }

    /*private BaseResponse parseResult(Connection.Response response) {
        if (Objects.isNull(response)) {
            // todo 添加日志
            log.error("");
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }

        String cookieValue = response.header("Set-Cookie");
        if (Objects.isNull(cookieValue)) {
            log.error("");
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }

        Matcher matcher = PATTERN.matcher(cookieValue);
        String sessionIdValue = null;
        if (matcher.find()) {
            sessionIdValue = matcher.group(1);
        }
        if (Objects.isNull(sessionIdValue)) {
            log.error("");
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }

        HttpSessionUtil.setSessionId(sessionIdValue);

        return null;
    }*/


}

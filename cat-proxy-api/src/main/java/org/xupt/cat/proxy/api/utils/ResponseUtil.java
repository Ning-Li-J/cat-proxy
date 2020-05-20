package org.xupt.cat.proxy.api.utils;

import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;

/**
 * @author lining
 * @data 2019/12/31
 */
public class ResponseUtil {

    /**
     * 构建失败响应
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse buildFailResponce(ErrorCode errorCode) {
        return BaseResponse.newFailResponse()
                .errorCode(errorCode.getCode())
                .errorMsg(errorCode.getDesc())
                .build();
    }

    /**
     * 构建成功响应
     *
     * @param result
     * @return
     */
    public static BaseResponse buildSuccessResponce(Object result) {
        return BaseResponse.newSuccResponse()
                .errorCode(ErrorCode.SUCCESS.getCode())
                .errorMsg(ErrorCode.SUCCESS.getDesc())
                .result(result)
                .build();
    }

    public static BaseResponse buildSuccessResponce() {
        return BaseResponse.newSuccResponse()
                .errorCode(ErrorCode.SUCCESS.getCode())
                .errorMsg(ErrorCode.SUCCESS.getDesc())
                .build();
    }
}

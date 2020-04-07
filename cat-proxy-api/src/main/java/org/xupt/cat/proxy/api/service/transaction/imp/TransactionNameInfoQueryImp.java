package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionNameInfoRequest;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionTypeInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionInfoResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;
import org.xupt.cat.proxy.api.service.transaction.ITransactionNameInfoQuery;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-07 下午2:43
 */
@Service
@Slf4j
public class TransactionNameInfoQueryImp implements ITransactionNameInfoQuery {

    @Autowired
    private ITransactionCore transactionCore;

    @Override
    public BaseResponse queryNameInfo(TransactionNameInfoRequest request) {
        //校验参数
        BaseResponse response = checkParam(request);
        if (Objects.nonNull(response)) {
            return response;
        }

        //发送请求
        Document document = null;
        try {
            document = HttpProxyUtil.sendHttp(SystemConstant.TRANSACTION_URI,
                    JsonUtil.toMap(request), null);
        } catch (Exception e) {
            //TODO 添加日志
            log.error("Query transaction name info error! param: {} e :{}", JsonUtil.toJson(request), e);
        }

        //解析结果
        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        TransactionInfoResponse transactionInfoResponse = transactionCore.coverTransactionInfo(document);
        return ResponseUtil.buildSuccessResponce(transactionInfoResponse);
    }

    // todo 校验参数
    private BaseResponse checkParam(TransactionNameInfoRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        if (StringUtils.isEmpty(request.getDomain())) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }


        return null;
    }
}


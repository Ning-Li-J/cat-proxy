package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.TransactionConstant;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionAllNameRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.transaction.ITransactionAllNameQuery;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-06 下午7:39
 */
@Service
@Slf4j
public class TransactionAllNameQueryImp implements ITransactionAllNameQuery {

    @Autowired
    private ITransactionCore transactionCore;

    @Override
    public BaseResponse queryAllName(TransactionAllNameRequest request) {
        //校验参数
        BaseResponse response = checkParam(request);
        if (Objects.nonNull(response)) {
            return response;
        }

        //发送请求
        Document document= null;
        try {
            document = HttpProxyUtil.sendHttp(TransactionConstant.ALL_TYPE_URI,
                    JsonUtil.toMap(request), null);
        } catch (IOException e) {
            log.error("Query transaction all name error! param: {} e :{}", JsonUtil.toJson(request), e);
        }

        //解析结果
        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        return ResponseUtil.buildSuccessResponce(transactionCore.covertTransactionDocu(document, true));
    }


    private BaseResponse checkParam(TransactionAllNameRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        return null;
    }
}

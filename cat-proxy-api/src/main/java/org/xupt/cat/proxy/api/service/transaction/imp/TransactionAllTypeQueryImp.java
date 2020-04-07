package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionAllTypeRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.transaction.ITransactionAllTypeQuery;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-06 下午4:27
 */
@Service
@Slf4j
public class TransactionAllTypeQueryImp implements ITransactionAllTypeQuery {

    @Autowired
    private ITransactionCore transactionCore;

    @Override
    public BaseResponse queryAllType(TransactionAllTypeRequest request) {
        //校验参数
        BaseResponse response = checkParam(request);
        if (Objects.nonNull(response)) {
            return response;
        }

        //发送请求
        Document document= null;
        try {
            document = HttpProxyUtil.sendHttp(SystemConstant.TRANSACTION_URI,
                    JsonUtil.toMap(request), null);
        } catch (IOException e) {
            log.error("Query transaction all type error! param: {} e :{}", JsonUtil.toJson(request), e);
        }

        //解析结果
        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        return ResponseUtil.buildSuccessResponce(transactionCore.covertTransaction(document, false));
    }

    private BaseResponse checkParam(TransactionAllTypeRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        return null;
    }

}

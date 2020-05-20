package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionTypeInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionInfoResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;
import org.xupt.cat.proxy.api.service.transaction.ITransactionTypeInfoQuery;
import org.xupt.cat.proxy.api.utils.DateUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-07 下午12:35
 */
@Service
@Slf4j
public class TransactionTypeInfoQueryImp implements ITransactionTypeInfoQuery {

    @Autowired
    private ITransactionCore transactionCore;

    @Override
    public BaseResponse queryTypeInfo(TransactionTypeInfoRequest request) {
        //校验参数
        BaseResponse response = checkParam(request);
        if (Objects.nonNull(response)) {
            return response;
        }

        //发送请求
        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.TRANSACTION_URI,
                    JsonUtil.toMap(covert(request)), null);
        } catch (Exception e) {
            log.error("Query transaction type info error! param: {} e :{}", JsonUtil.toJson(request), e);
        }

        //解析结果
        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        TransactionInfoResponse transactionInfoResponse = transactionCore.coverTransactionInfo(document);
        return ResponseUtil.buildSuccessResponce(transactionInfoResponse);
    }

    private BaseResponse checkParam(TransactionTypeInfoRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        return null;
    }

    private CatDTO covert(TransactionTypeInfoRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setDomain(request.getDomain());
        catDTO.setIp(request.getIp());
        catDTO.setOp(CatConstant.OP_GRAPHS);
        catDTO.setType(request.getType());
        if (Objects.nonNull(request.getStep())) {
            catDTO.setDate(DateUtil.nowYYYYMMDDHHRetreat(-request.getStep()));
        } else {
            catDTO.setDate(DateUtil.nowYYYYMMDDHH());
        }

        return catDTO;
    }


}

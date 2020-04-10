package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionNameInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionInfoResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;
import org.xupt.cat.proxy.api.service.transaction.ITransactionNameInfoQuery;
import org.xupt.cat.proxy.api.utils.DateUtil;
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
            document = HttpProxyUtil.sendHttp(CatConstant.TRANSACTION_URI,
                    JsonUtil.toMap(covert(request)), null);
        } catch (Exception e) {
            log.error("Query transaction name info error! param: {} e :{}", JsonUtil.toJson(request), e);
        }

        //解析结果
        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        TransactionInfoResponse transactionInfoResponse = transactionCore.coverTransactionInfo(document);
        return ResponseUtil.buildSuccessResponce(transactionInfoResponse);
    }

    private BaseResponse checkParam(TransactionNameInfoRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        return null;
    }

    private CatDTO covert(TransactionNameInfoRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setDomain(request.getDomain());
        catDTO.setIp(request.getIp());
        catDTO.setType(request.getType());
        catDTO.setName(request.getName());
        catDTO.setOp(CatConstant.OP_GRAPHS);
        if (Objects.nonNull(request.getStep())) {
            catDTO.setDate(DateUtil.nowYYYYMMDDHHRetreat( - request.getStep()));
        } else {
            catDTO.setDate(DateUtil.nowYYYYMMDDHH());
        }

        return catDTO;
    }
}


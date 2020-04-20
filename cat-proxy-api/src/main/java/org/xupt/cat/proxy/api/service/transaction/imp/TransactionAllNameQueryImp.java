package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionAllNameRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.transaction.ITransactionAllNameQuery;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;
import org.xupt.cat.proxy.api.utils.DateUtil;
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

        //转换http参数
        CatDTO catDTO = covert(request);

        //发送请求
        Document document= null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.TRANSACTION_URI,
                    JsonUtil.toMap(catDTO), null);
        } catch (IOException e) {
            log.error("Query transaction all name error! param: {} e :{}", JsonUtil.toJson(request), e);
        }

        //解析结果
        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        return ResponseUtil.buildSuccessResponce(transactionCore.covertTransaction(document, true));
    }


    private BaseResponse checkParam(TransactionAllNameRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        return null;
    }

    private CatDTO covert(TransactionAllNameRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setDomain(request.getDomain());
        catDTO.setIp(request.getIp());
        catDTO.setType(request.getType());
        catDTO.setDate(DateUtil.nowYYYYMMDDHH());

        if (Objects.nonNull(request.getStep())) {
            catDTO.setStep(request.getStep() + "");
        }
        return catDTO;
    }
}

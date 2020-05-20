package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.transaction.TransactionAllTypeRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.transaction.ITransactionAllTypeQuery;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;
import org.xupt.cat.proxy.api.utils.DateUtil;
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

        //转换参数
        CatDTO catDTO = covert(request);

        //发送请求
        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.TRANSACTION_URI,
                    JsonUtil.toMap(catDTO), null);
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

    private CatDTO covert(TransactionAllTypeRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setDomain(request.getDomain());
        catDTO.setIp(request.getIp());
        catDTO.setReportType(request.getReportType());
        catDTO.setOp(CatConstant.OP_VIEW);
        catDTO.setDate(DateUtil.nowYYYYMMDDHH());

        if (Objects.nonNull(request.getStep())) {
            catDTO.setStep(request.getStep() + "");
        }
        return catDTO;
    }

}

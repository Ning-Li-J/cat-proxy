package org.xupt.cat.proxy.api.service.alert.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.alert.AlertBaseRequest;
import org.xupt.cat.proxy.api.domain.requests.alert.AlertRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.alert.AlertRuleResponse;
import org.xupt.cat.proxy.api.domain.responses.alert.AlertRuleSimpResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.alert.IAlertCore;
import org.xupt.cat.proxy.api.service.alert.IAlertQuery;
import org.xupt.cat.proxy.api.utils.CatAuchUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-19 下午12:36
 */
@Service
@Slf4j
public class AlertQueryImp implements IAlertQuery {
    @Autowired
    private IAlertCore alertCore;

    @Override
    public BaseResponse queryAllAlert(AlertBaseRequest request) {

        CatDTO catDTO = new CatDTO();
        switch (request.getOperation()) {
            case "transaction":
                catDTO.setOp(CatConstant.OP_RULE_TRANSACTION);
                break;
            case "event":
                catDTO.setOp(CatConstant.OP_RULE_EVENT);
                break;
            default:
                return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.CONFIG, JsonUtil.toMap(catDTO), CatAuchUtil.buildAuchCookie());
        } catch (Exception e) {
            log.error("query alert all rule simp info error! e:{}", e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        AlertRuleSimpResponse response = alertCore.covertRuleSimpList(document);
        return ResponseUtil.buildSuccessResponce(response);
    }

    @Override
    public BaseResponse queryAlert(AlertRequest request) {
        CatDTO catDTO = new CatDTO();
        switch (request.getOperation()) {
            case "transaction":
                catDTO.setOp(CatConstant.OP_RULE_TRAN_UPDATE);
                break;
            case "event":
                catDTO.setOp(CatConstant.OP_RULE_EVENT_UPDATE);
                break;
            default:
                return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }
        catDTO.setRuleId(request.getRuleId());

        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.CONFIG, JsonUtil.toMap(catDTO), CatAuchUtil.buildAuchCookie());
        } catch (Exception e) {
            log.error("query alert rule error! e :{}", e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        AlertRuleResponse response = alertCore.covertRule(document);
        return ResponseUtil.buildSuccessResponce(response);
    }

}

package org.xupt.cat.proxy.api.service.alert.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.alert.AlertInfoRequest;
import org.xupt.cat.proxy.api.domain.requests.alert.AlertRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.alert.IAlertCommand;
import org.xupt.cat.proxy.api.utils.CatAuchUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-19 下午6:35
 */
@Slf4j
@Service
public class AlertCommandImp implements IAlertCommand {
    @Override
    public BaseResponse deletAlertRule(AlertRequest request) {
        CatDTO catDTO = new CatDTO();
        switch (request.getOperation()) {
            case "transaction":
                catDTO.setOp(CatConstant.OP_RULE_TRAN_DELETE);
                break;
            case "event":
                catDTO.setOp(CatConstant.OP_RULE_EVENT_DELETE);
                break;
            default:
                return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }
        catDTO.setRuleId(request.getRuleId());

        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.CONFIG, JsonUtil.toMap(catDTO), CatAuchUtil.buildAuchCookie());
        } catch (Exception e) {
            log.error("delete alert rule error! e:{}", e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        return ResponseUtil.buildSuccessResponce();
    }

    @Override
    public BaseResponse createAlertRule(AlertInfoRequest request) {
        CatDTO catDTO = new CatDTO();
        switch (request.getOperation()) {
            case "transaction":
                catDTO.setOp(CatConstant.OP_RULE_TRAN_CREATE);
                break;
            case "event":
                catDTO.setOp(CatConstant.OP_RULE_EVENT_CREATE);
                break;
            default:
                return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }
        catDTO.setRuleId(request.getRuleId());
        catDTO.setAvailable(request.getAvailable());
        catDTO.setConfigs(JsonUtil.toJson(request.getAlertRuleList()));

        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.CONFIG, JsonUtil.toMap(catDTO), CatAuchUtil.buildAuchCookie());
        } catch (Exception e) {
            log.error("create alert rule error! e:{}", e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }

        return ResponseUtil.buildSuccessResponce();
    }

}

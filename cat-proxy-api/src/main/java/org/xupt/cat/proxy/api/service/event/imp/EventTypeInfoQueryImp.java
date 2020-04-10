package org.xupt.cat.proxy.api.service.event.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.event.EventTypeInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.event.EventInfoResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.event.IEventCore;
import org.xupt.cat.proxy.api.service.event.IEventTypeInfoQuery;
import org.xupt.cat.proxy.api.utils.DateUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-08 下午2:23
 */
@Service
@Slf4j
public class EventTypeInfoQueryImp implements IEventTypeInfoQuery {

    @Autowired
    private IEventCore eventCore;

    @Override
    public BaseResponse queryTypeInfo(EventTypeInfoRequest request) {
        BaseResponse response = check(request);
        if (Objects.nonNull(response)) {
            return response;
        }

        Document document = null;
        try {
            document = HttpProxyUtil.sendHttp(CatConstant.EVENT_URI,
                    JsonUtil.toMap(covert(request)), null);
        } catch (Exception e) {
            log.error("query event type info error.", e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        EventInfoResponse eventInfoResponse = eventCore.covertEventInfo(document);
        return ResponseUtil.buildSuccessResponce(eventInfoResponse);
    }

    private BaseResponse check(EventTypeInfoRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        return null;
    }

    private CatDTO covert(EventTypeInfoRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setDomain(request.getDomain());
        catDTO.setIp(request.getIp());
        catDTO.setType(request.getType());
        catDTO.setOp(CatConstant.OP_GRAPHS);
        if (Objects.isNull(request.getStep())) {
            catDTO.setDate(DateUtil.nowYYYYMMDDHH());
        } else {
            catDTO.setDate(DateUtil.nowYYYYMMDDHHRetreat( -request.getStep()));
        }
        return catDTO;
    }
}

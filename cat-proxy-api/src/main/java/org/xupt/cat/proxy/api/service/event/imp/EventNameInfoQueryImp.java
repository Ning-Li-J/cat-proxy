package org.xupt.cat.proxy.api.service.event.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.event.EventNameInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.event.EventInfoResponse;
import org.xupt.cat.proxy.api.domain.responses.event.EventResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.event.IEventCore;
import org.xupt.cat.proxy.api.service.event.IEventNameInfoQuery;
import org.xupt.cat.proxy.api.utils.DateUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-08 下午2:14
 */
@Service
@Slf4j
public class EventNameInfoQueryImp implements IEventNameInfoQuery {

    @Autowired
    private IEventCore eventCore;

    @Override
    public BaseResponse queryNameInfo(EventNameInfoRequest request) {
        BaseResponse response = check(request);
        if (Objects.nonNull(response)) {
            return response;
        }

        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.EVENT_URI,
                    JsonUtil.toMap(covert(request)), null);
        } catch (Exception e) {
            log.error("query event name info error!", e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        EventInfoResponse eventInfoResponse = eventCore.covertEventInfo(document);
        return ResponseUtil.buildSuccessResponce(eventInfoResponse);
    }

    private BaseResponse check(EventNameInfoRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildSuccessResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }
        return null;
    }

    public CatDTO covert(EventNameInfoRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setIp(request.getIp());
        catDTO.setDomain(request.getDomain());
        catDTO.setOp(CatConstant.OP_GRAPHS);
        catDTO.setType(request.getType());
        catDTO.setName(request.getName());
        if (Objects.isNull(request.getStep())) {
            catDTO.setDate(DateUtil.nowYYYYMMDDHH());
        } else {
            catDTO.setDate(DateUtil.nowYYYYMMDDHHRetreat( -request.getStep()));
        }
        return catDTO;
    }


}

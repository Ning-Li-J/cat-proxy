package org.xupt.cat.proxy.api.service.event.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.event.EventAllNameRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.event.EventResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.event.IEventAllNameQuery;
import org.xupt.cat.proxy.api.service.event.IEventCore;
import org.xupt.cat.proxy.api.utils.DateUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-08 下午12:49
 */
@Service
@Slf4j
public class EventAllNameQueryImp implements IEventAllNameQuery {

    @Autowired
    private IEventCore eventCore;

    @Override
    public BaseResponse queryAllName(EventAllNameRequest request) {
        BaseResponse response = check(request);
        if (Objects.nonNull(response)) {
            return response;
        }

        Document document = null;
        try {
            document = HttpProxyUtil.sendHttp(CatConstant.EVENT_URI,
                    JsonUtil.toMap(covert(request)), null);
        } catch (Exception e) {
            log.error("query event all name error!", e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        EventResponse eventResponse = eventCore.covertEvent(document, true);
        return ResponseUtil.buildSuccessResponce(eventResponse);
    }

    private BaseResponse check(EventAllNameRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        return null;
    }

    private CatDTO covert(EventAllNameRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setIp(request.getIp());
        catDTO.setDomain(request.getDomain());
        catDTO.setType(request.getType());
        catDTO.setDate(DateUtil.nowYYYYMMDDHH());
        if (Objects.nonNull(request.getStep())) {
            catDTO.setStep(request.getStep() + "");
        }
        return catDTO;
    }
}

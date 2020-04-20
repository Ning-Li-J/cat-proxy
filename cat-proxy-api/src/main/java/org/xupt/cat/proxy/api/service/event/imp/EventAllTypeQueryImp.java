package org.xupt.cat.proxy.api.service.event.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.constant.SystemConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.event.EventAllTypeRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.event.EventResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.event.IEventAllTypeQuery;
import org.xupt.cat.proxy.api.service.event.IEventCore;
import org.xupt.cat.proxy.api.utils.DateUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-08 下午12:46
 */
@Slf4j
@Service
public class EventAllTypeQueryImp implements IEventAllTypeQuery {

    @Autowired
    private IEventCore eventCore;

    @Override
    public BaseResponse queryAllType(EventAllTypeRequest request) {
        BaseResponse response = check(request);
        //校验参数
        if (Objects.nonNull(response)) {
            return response;
        }

        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.EVENT_URI,
                    JsonUtil.toMap(covert(request)), null);
        } catch (Exception e) {
            log.error("query event all type error!", e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        EventResponse eventResponse = eventCore.covertEvent(document, false);
        return ResponseUtil.buildSuccessResponce(eventResponse);
    }

    private BaseResponse check(EventAllTypeRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        return null;
    }

    private CatDTO covert(EventAllTypeRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setIp(request.getIp());
        catDTO.setDomain(request.getDomain());
        catDTO.setOp(CatConstant.OP_VIEW);
        catDTO.setReportType(request.getReportType());
        catDTO.setDate(DateUtil.nowYYYYMMDDHH());
        if (Objects.nonNull(request.getStep())) {
            catDTO.setStep(request.getStep() + "");
        }
        return catDTO;
    }
}

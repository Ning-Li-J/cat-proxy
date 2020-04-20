package org.xupt.cat.proxy.api.service.host.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.host.HostIpRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.host.HostIpResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.host.IHostIpQuery;
import org.xupt.cat.proxy.api.utils.DateUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-11 下午3:55
 */
@Service
@Slf4j
public class HostIpQueryImp implements IHostIpQuery {

    @Override
    public BaseResponse queryHostIp(HostIpRequest request) {

        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.QUERY_PROJECT_URI,
                    JsonUtil.toMap(covert(request)), null);
        } catch (Exception e) {
            log.info("Query host ip error! e :{}",  e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        HostIpResponse hostIpResponse = covertHostIp(document);
        return ResponseUtil.buildSuccessResponce(hostIpResponse);
    }

    private CatDTO covert(HostIpRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setIp("All");
        catDTO.setDomain("cat");
        catDTO.setReportType("day");
        catDTO.setOp(CatConstant.OP_VIEW);
        catDTO.setDate(DateUtil.nowYYYYMMDDHH());
        if (Objects.nonNull(request.getStep())) {
            catDTO.setStep(request.getStep() + "");
        }
        return catDTO;
    }

    private HostIpResponse covertHostIp(Document document) {
        log.info("start covert HostIp");

        List<String> ipList = new ArrayList<>();

        Elements elements = document.getElementsByClass("machines").get(0).getElementsByTag("a");
        for (Element element : elements) {
            ipList.add(element.text());
        }

        HostIpResponse hostIpResponse = new HostIpResponse();
        hostIpResponse.setIpList(ipList);
        log.info("end covert HostIp");
        return hostIpResponse;
    }

}



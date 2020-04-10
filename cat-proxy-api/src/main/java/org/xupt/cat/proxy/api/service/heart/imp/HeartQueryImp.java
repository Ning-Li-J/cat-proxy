package org.xupt.cat.proxy.api.service.heart.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.requests.heart.HeartRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.TargetInfo;
import org.xupt.cat.proxy.api.domain.responses.heart.HeartResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.enums.GroupNameEnum;
import org.xupt.cat.proxy.api.service.ICore;
import org.xupt.cat.proxy.api.service.heart.IHeartQuery;
import org.xupt.cat.proxy.api.utils.DateUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-10 下午12:44
 */
@Service
@Slf4j
public class HeartQueryImp implements IHeartQuery {

    @Autowired
    private ICore core;

    @Override
    public BaseResponse queryHostHeart(HeartRequest request) {
        BaseResponse response = check(request);
        if (Objects.nonNull(response)) {
            return response;
        }

        Document document = null;
        try {
            document = HttpProxyUtil.sendHttp(CatConstant.HEART_URI,
                    JsonUtil.toMap(covert(request)), null);
        } catch (Exception e) {
            log.error("Query host heart error! param: {} e :{}", JsonUtil.toJson(request), e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        HeartResponse heartResponse = covertHeart(document);
        return ResponseUtil.buildSuccessResponce(heartResponse);
    }

    private BaseResponse check(HeartRequest request) {
        if (Objects.isNull(request)) {
            return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);
        }

        return null;
    }

    private CatDTO covert(HeartRequest request) {
        CatDTO catDTO = new CatDTO();
        catDTO.setDomain(request.getDomain());
        catDTO.setIp(request.getIp());
        catDTO.setOp(CatConstant.OP_VIEW);
        catDTO.setReportType(request.getReportType());
        catDTO.setDate(DateUtil.nowYYYYMMDDHH());
        if (Objects.nonNull(request.getStep())) {
            catDTO.setStep("" + request.getStep());
        }
        return catDTO;
    }

    private HeartResponse covertHeart(Document document) {
        log.info("start covert heart info.");

        List<HeartResponse.TargetGroup> targetGroupList = new ArrayList<>();

        Elements groupElements = document.getElementById("heartGroup").getElementsByTag("tr");
        for (int i = 0; i < groupElements.size(); i += 2) {
            HeartResponse.TargetGroup targetGroup = new HeartResponse.TargetGroup();

            String groupName = groupElements.get(i).getElementById("groupTitle").text();
            GroupNameEnum groupNameEnum = GroupNameEnum.getEnum(groupName);
            if (Objects.isNull(groupNameEnum)) {
                targetGroup.setGroupName(groupName);
            } else {
                targetGroup.setGroupName(groupNameEnum.getNowGroup());
            }

            List<TargetInfo> targetInfoList = core.parseTargetList(groupElements.get(i + 1));
            targetGroup.setTargetInfoList(targetInfoList);

            targetGroupList.add(targetGroup);
        }

        HeartResponse heartResponse = new HeartResponse();
        heartResponse.setTargetGroups(targetGroupList);

        log.info("end covert heart info.");
        return heartResponse;
    }
}

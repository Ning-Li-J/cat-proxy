package org.xupt.cat.proxy.api.service.event.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.constant.TargetConstant;
import org.xupt.cat.proxy.api.domain.responses.TargetInfo;
import org.xupt.cat.proxy.api.domain.responses.event.EventInfoResponse;
import org.xupt.cat.proxy.api.domain.responses.event.EventResponse;
import org.xupt.cat.proxy.api.service.ICore;
import org.xupt.cat.proxy.api.service.event.IEventCore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lining
 * @data 2020-04-08 下午12:48
 */
@Service
@Slf4j
public class EventCoreImp implements IEventCore {

    @Autowired
    private ICore core;

    @Override
    public EventResponse covertEvent(Document document, boolean isName) {
        log.info("start covert event.");

        List<EventResponse.Event> eventList = new ArrayList<>();

        boolean isFirst = true;
        Elements elements = document.getElementsByClass("right");
        for (Element element : elements) {
            if (!"tr".equals(element.tagName())) {
                continue;
            }

            EventResponse.Event event = new EventResponse.Event();
            Elements tdElements = element.getElementsByTag("td");
            if (isName) {
                //跳过name第一行的汇总
                if (isFirst) {
                    isFirst = false;
                    continue;
                }
                event.setName(tdElements.get(0).childNode(2).toString());
            } else {
                event.setName(tdElements.get(0).getElementsByTag("a").get(2).childNode(0).toString());
            }
            event.setTotal(tdElements.get(1).childNode(0).toString());
            event.setFailure(tdElements.get(2).childNode(0).toString());
            event.setQps(tdElements.get(5).childNode(0).toString());

            eventList.add(event);
        }

        EventResponse response = new EventResponse();
        response.setEventList(eventList);

        log.info("end covert event.");
        return response;
    }

    @Override
    public EventInfoResponse covertEventInfo(Document document) {
        log.info("start covert event info.");

        List<TargetInfo> targetInfoList = core.parseTargetList(document);

        List<EventInfoResponse.BranchInfo> branchInfoList = new ArrayList<>();
        Elements branchElements = document.getElementsByClass(" right");
        for (Element element : branchElements) {
            Elements branchIndoElements = element.getElementsByTag("td");

            EventInfoResponse.BranchInfo branchInfo = new EventInfoResponse.BranchInfo();
            branchInfo.setIp(branchIndoElements.get(0).childNode(0).toString());
            branchInfo.setTotal(branchIndoElements.get(1).childNode(0).toString());
            branchInfo.setFailure(branchIndoElements.get(2).childNode(0).toString());
            branchInfoList.add(branchInfo);
        }

        EventInfoResponse eventInfoResponse = new EventInfoResponse();
        eventInfoResponse.setTargetInfoList(targetInfoList);
        eventInfoResponse.setBranchInfoList(branchInfoList);

        log.info("end covert event info.");
        return eventInfoResponse;
    }

    /*
    private String[] parseYt(Element element) {
        Elements elements = element.getElementsByTag("text");
        List<String> list = new ArrayList<>();
        for (Element e : elements) {
            list.add(e.childNode(0).toString().trim());
        }

        String[] rest = new String[list.size()];
        return list.toArray(rest);
    }
    */
}

/*

        Element[] dataElements = new Element[2];
        Element[] yIndexElements = new Element[2];
        Elements elements = document.getElementsByTag("g");
        int yIndex = 0;
        int dataIndex = 0;
        for (Element element : elements) {
            if ("yt".equals(element.id())) {
                yIndexElements[yIndex++] = element;
            }
            if ("bar".equals(element.id())) {
                dataElements[dataIndex++] = element;
            }
        }

        TargetInfo hitsNum = new TargetInfo();
        hitsNum.setTitle(TargetConstant.TITLE_HITS_TIME);
        hitsNum.setXUnits(TargetConstant.UNITS_MINUTE);
        hitsNum.setXIndex(TargetConstant.INDEX_MINUTE);
        hitsNum.setYUnits(TargetConstant.UNITS_COUNT);
        hitsNum.setYIndex(core.parseYt(yIndexElements[0]));
        hitsNum.setData(core.parseData(dataElements[0]));
        eventInfoResponse.setHitsNum(hitsNum);

        TargetInfo failuresNum = new TargetInfo();
        failuresNum.setTitle(TargetConstant.TITLE_FAILURES_TIME);
        failuresNum.setXUnits(TargetConstant.UNITS_MINUTE);
        failuresNum.setXIndex(TargetConstant.INDEX_MINUTE);
        failuresNum.setYUnits(TargetConstant.UNITS_COUNT);
        failuresNum.setYIndex(core.parseYt(yIndexElements[1]));
        failuresNum.setData(core.parseData(dataElements[1]));
        eventInfoResponse.setFailuresNum(failuresNum);
 */

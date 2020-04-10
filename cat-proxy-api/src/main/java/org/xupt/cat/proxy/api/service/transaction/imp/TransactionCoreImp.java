package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.domain.responses.TargetInfo;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionInfoResponse;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionResponse;
import org.xupt.cat.proxy.api.service.ICore;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lining
 * @data 2020-04-06 下午7:40
 */
@Service
@Slf4j
public class TransactionCoreImp implements ITransactionCore {

    @Autowired
    private ICore core;

    @Override
    public TransactionResponse covertTransaction(Document document, boolean isName) {
        log.info("start cover transaction.");
        List<TransactionResponse.Transaction> transactions = new ArrayList<>();

        Elements elements = document.getElementsByClass(" right");
        for (int i = 0; i < elements.size(); i++) {
            Elements trElements = elements.get(i).getElementsByTag("td");
            TransactionResponse.Transaction transaction = new TransactionResponse.Transaction();

            String type = null;
            //name 第一行为汇总的
            if (isName) {
                //isName 跳过第一行汇总的
                if (i == 0) {
                    continue;
                }
                type = trElements.get(0).childNode(2).toString();
            } else {
                type = trElements.get(0).getElementsByTag("a").get(1).text();
            }

            transaction.setName(type);
            transaction.setTotal(trElements.get(1).text());
            transaction.setFailure(trElements.get(2).text());
            transaction.setMin(trElements.get(5).text());
            transaction.setMax(trElements.get(6).text());
            transaction.setAvg(trElements.get(7).text());
            transaction.setLine95(trElements.get(8).text());
            transaction.setLine999(trElements.get(9).text());
            transaction.setStd(trElements.get(10).text());
            transaction.setQps(trElements.get(11).text());
            transactions.add(transaction);
        }

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionList(transactions);

        log.info("end cover transaction .");
        return transactionResponse;
    }

    @Override
    public TransactionInfoResponse coverTransactionInfo(Document document) {
        log.info("start cover transaction info.");

        List<TargetInfo> targetInfoList = core.parseTargetList(document);

        //解析 分布统计
        List<TransactionInfoResponse.BranchInfo> branchInfoList = new ArrayList<>();
        Elements branchElement = document.getElementsByClass(" right");
        for (Element element : branchElement) {
            Elements branchIndoElements = element.getElementsByTag("td");

            TransactionInfoResponse.BranchInfo branchInfo = new TransactionInfoResponse.BranchInfo();
            branchInfo.setIp(branchIndoElements.get(0).text());
            branchInfo.setTotal(branchIndoElements.get(1).text());
            branchInfo.setFailure(branchIndoElements.get(2).text());
            branchInfo.setMin(branchIndoElements.get(4).text());
            branchInfo.setMax(branchIndoElements.get(5).text());
            branchInfo.setAvg(branchIndoElements.get(6).text());
            branchInfo.setStd(branchIndoElements.get(7).text());
            branchInfoList.add(branchInfo);
        }

        TransactionInfoResponse infoResponse = new TransactionInfoResponse();
        infoResponse.setBranchInfoList(branchInfoList);
        infoResponse.setTargetInfoList(targetInfoList);

        log.info("end cover transaction info.");
        return infoResponse;
    }

}
/*

        Element[] yIndexElements = new Element[4];
        Element[] dataElements = new Element[4];
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

        //解析 持续时间分布
        TargetInfo durationDistribution = new TargetInfo();
        durationDistribution.setTitle(TargetConstant.TITLE_DISTRIBUTION);
        durationDistribution.setXUnits(TargetConstant.UNITS_MILL);
        durationDistribution.setXIndex(TargetConstant.INDEX_MILL);
        durationDistribution.setYUnits(TargetConstant.UNITS_COUNT);
        durationDistribution.setYIndex(core.parseYt(yIndexElements[0]));
        durationDistribution.setData(core.parseData(dataElements[0]));
        infoResponse.setDurationDistribution(durationDistribution);

        //解析 命中数量
        TargetInfo hitsNum = new TargetInfo();
        hitsNum.setTitle(TargetConstant.TITLE_HITS_TIME);
        hitsNum.setXUnits(TargetConstant.UNITS_MINUTE);
        hitsNum.setXIndex(TargetConstant.INDEX_MINUTE);
        hitsNum.setYUnits(TargetConstant.UNITS_COUNT);
        hitsNum.setYIndex(core.parseYt(yIndexElements[1]));
        hitsNum.setData(core.parseData(dataElements[1]));
        infoResponse.setHitsNum(hitsNum);

        //解析 平均持续时间
        TargetInfo averageDurationTime = new TargetInfo();
        averageDurationTime.setTitle(TargetConstant.TITLE_AVERAGE);
        averageDurationTime.setXUnits(TargetConstant.UNITS_MINUTE);
        averageDurationTime.setXIndex(TargetConstant.INDEX_MINUTE);
        averageDurationTime.setYUnits(TargetConstant.UNITS_MILL);
        averageDurationTime.setYIndex(core.parseYt(yIndexElements[2]));
        averageDurationTime.setData(core.parseData(dataElements[2]));
        infoResponse.setAverageDurationTime(averageDurationTime);

        //解析 失败数量
        TargetInfo failuresNum = new TargetInfo();
        failuresNum.setTitle(TargetConstant.TITLE_FAILURES_TIME);
        failuresNum.setXUnits(TargetConstant.UNITS_MINUTE);
        failuresNum.setXIndex(TargetConstant.INDEX_MINUTE);
        failuresNum.setYUnits(TargetConstant.UNITS_COUNT);
        failuresNum.setYIndex(core.parseYt(yIndexElements[3]));
        failuresNum.setData(core.parseData(dataElements[3]));
        infoResponse.setFailuresNum(failuresNum);


 */

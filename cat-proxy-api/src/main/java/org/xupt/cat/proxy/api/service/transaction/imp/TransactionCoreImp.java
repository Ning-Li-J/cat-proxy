package org.xupt.cat.proxy.api.service.transaction.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.TransactionConstant;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionInfoResponse;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionResponse;
import org.xupt.cat.proxy.api.service.transaction.ITransactionCore;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lining
 * @data 2020-04-06 下午7:40
 */
@Service
@Slf4j
public class TransactionCoreImp implements ITransactionCore {

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
                type = trElements.get(0).getElementsByTag("a").get(1).childNode(0).toString();
            }

            transaction.setType(type);
            transaction.setTotal(trElements.get(1).childNode(0).toString());
            transaction.setFailure(trElements.get(2).childNode(0).toString());
            transaction.setMin(trElements.get(5).childNode(0).toString());
            transaction.setMax(trElements.get(6).childNode(0).toString());
            transaction.setAvg(trElements.get(7).childNode(0).toString());
            transaction.setLine95(trElements.get(8).childNode(0).toString());
            transaction.setLine999(trElements.get(9).childNode(0).toString());
            transaction.setStd(trElements.get(10).childNode(0).toString());
            transaction.setQps(trElements.get(11).childNode(0).toString());
            transactions.add(transaction);
        }

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactions(transactions);

        log.info("end cover transaction .");
        return transactionResponse;
    }

    @Override
    public TransactionInfoResponse coverTransactionInfo(Document document) {
        log.info("start cover transaction info.");

        TransactionInfoResponse infoResponse = new TransactionInfoResponse();

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
        TransactionInfoResponse.TargetInfo durationDistribution = new TransactionInfoResponse.TargetInfo();
        durationDistribution.setXIndex(TransactionConstant.MILL_ARRAY);
        durationDistribution.setYIndex(parseYt(yIndexElements[0]));
        durationDistribution.setData(parseData(dataElements[0]));
        infoResponse.setDurationDistribution(durationDistribution);

        //解析 命中数量
        TransactionInfoResponse.TargetInfo hitsNum = new TransactionInfoResponse.TargetInfo();
        hitsNum.setXIndex(TransactionConstant.MINUTE_ARRAY);
        hitsNum.setYIndex(parseYt(yIndexElements[1]));
        hitsNum.setData(parseData(dataElements[1]));
        infoResponse.setHitsNum(hitsNum);

        //解析 平均持续时间
        TransactionInfoResponse.TargetInfo averageDurationTime = new TransactionInfoResponse.TargetInfo();
        averageDurationTime.setXIndex(TransactionConstant.MINUTE_ARRAY);
        averageDurationTime.setYIndex(parseYt(yIndexElements[2]));
        averageDurationTime.setData(parseData(dataElements[2]));
        infoResponse.setAverageDurationTime(averageDurationTime);

        //解析 失败数量
        TransactionInfoResponse.TargetInfo failuresNum = new TransactionInfoResponse.TargetInfo();
        failuresNum.setXIndex(TransactionConstant.MINUTE_ARRAY);
        failuresNum.setYIndex(parseYt(yIndexElements[3]));
        failuresNum.setData(parseData(dataElements[3]));
        infoResponse.setFailuresNum(failuresNum);

        //解析 分布统计
        List<TransactionInfoResponse.BranchInfo> branchInfoList = new ArrayList<>();
        Elements branchElement = document.getElementsByClass(" right");
        for (Element element : branchElement) {
            Elements branchIndoElements = element.getElementsByTag("td");

            TransactionInfoResponse.BranchInfo branchInfo = new TransactionInfoResponse.BranchInfo();
            branchInfo.setIp(branchIndoElements.get(0).childNode(0).toString());
            branchInfo.setTotal(branchIndoElements.get(1).childNode(0).toString());
            branchInfo.setFailure(branchIndoElements.get(2).childNode(0).toString());
            branchInfo.setMin(branchIndoElements.get(4).childNode(0).toString());
            branchInfo.setMax(branchIndoElements.get(5).childNode(0).toString());
            branchInfo.setAvg(branchIndoElements.get(6).childNode(0).toString());
            branchInfo.setStd(branchIndoElements.get(7).childNode(0).toString());
            branchInfoList.add(branchInfo);
        }
        infoResponse.setBranchInfoList(branchInfoList);

        log.info("end cover transaction info.");
        return infoResponse;
    }

    private String[] parseYt(Element element) {
        Elements elements = element.getElementsByTag("text");
        List<String> list = new ArrayList<>();
        for (Element e : elements) {
            list.add(e.childNode(0).toString().trim());
        }

        String[] rest = new String[list.size()];
        return list.toArray(rest);
    }

    private TransactionInfoResponse.Point[] parseData(Element element) {
        List<TransactionInfoResponse.Point> list = new ArrayList<>();
        Elements elements = element.getElementsByTag("rect");
        for (Element e : elements) {
            TransactionInfoResponse.Point point = new TransactionInfoResponse.Point();
            point.setX(e.attr("xValue"));
            point.setY(e.attr("yValue"));
            list.add(point);
        }

        TransactionInfoResponse.Point[] rest = new TransactionInfoResponse.Point[list.size()];
        return list.toArray(rest);
    }
}

package org.xupt.cat.proxy.api.service;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.constant.TargetConstant;
import org.xupt.cat.proxy.api.domain.responses.Point;
import org.xupt.cat.proxy.api.domain.responses.TargetInfo;
import org.xupt.cat.proxy.api.enums.TitleNameEnum;
import org.xupt.cat.proxy.api.enums.UnitsEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-08 下午3:53
 */
@Service
public class CoreImp implements ICore {

    @Override
    public List<TargetInfo> parseTargetList(Element element) {
        Elements elements = element.getElementsByTag("g");

        List<Element> unitsElements = new ArrayList<>(8);
        List<Element> yIndexElements = new ArrayList<>(8);
        List<Element> xIndexElements = new ArrayList<>(8);
        List<Element> dataElements = new ArrayList<>(8);
        for (Element e : elements) {
            switch (e.id().trim()) {
                case "units":
                    unitsElements.add(e);
                    break;
                case "xt":
                    xIndexElements.add(e);
                    break;
                case "yt":
                    yIndexElements.add(e);
                    break;
                case "bar":
                    dataElements.add(e);
                    break;
                default:
                    break;
            }
        }

        List<TargetInfo> targetInfoList = new ArrayList<>(8);
        for (int i = 0; i < unitsElements.size(); i++) {
            TargetInfo targetInfo = new TargetInfo();

            parseUnits(unitsElements.get(i), targetInfo);
            targetInfo.setXIndex(parseXt(xIndexElements.get(i)));
            targetInfo.setYIndex(parseYt(yIndexElements.get(i)));
            targetInfo.setData(parseData(dataElements.get(i)));

            targetInfoList.add(targetInfo);
        }

        return targetInfoList;
    }

    private void parseUnits(Element element, TargetInfo targetInfo) {
        Elements textElements = element.getElementsByTag("text");

        //解析x轴单位
        String xUntis = textElements.get(0).text();
        UnitsEnum xUnitsEnum = UnitsEnum.getEnum(xUntis);
        if (Objects.isNull(xUnitsEnum)) {
            targetInfo.setXUnits(xUntis);
        } else {
            targetInfo.setXUnits(xUnitsEnum.getNowUnits());
        }

        //解析y轴单位
        String yUntis = textElements.get(1).text();
        UnitsEnum yUnitsEnum = UnitsEnum.getEnum(yUntis);
        if (Objects.isNull(yUnitsEnum)) {
            targetInfo.setYUnits(yUntis);
        } else {
            targetInfo.setYUnits(yUnitsEnum.getNowUnits());
        }

        //解析标题
        String title = textElements.get(2).text();
        TitleNameEnum titleNameEnum = TitleNameEnum.getEnum(title);
        if (Objects.isNull(titleNameEnum)) {
            targetInfo.setTitle(title);
        } else {
            targetInfo.setTitle(titleNameEnum.getNowTitle());
        }
    }

    public String[] parseXt(Element element) {
        Elements textElements = element.getElementsByTag("text");
        String value = textElements.get(textElements.size() - 1).childNode(0).toString();

        if ("60".equals(value.trim())) {
            return TargetConstant.INDEX_MINUTE;
        } else {
            return TargetConstant.INDEX_MILL;
        }
    }

    @Override
    public String[] parseYt(Element element) {
        Elements elements = element.getElementsByTag("text");
        List<String> list = new ArrayList<>();
        for (Element e : elements) {
            list.add(e.childNode(0).toString().trim());
        }

        String[] rest = new String[list.size()];
        return list.toArray(rest);
    }

    @Override
    public Point[] parseData(Element element) {
        List<Point> list = new ArrayList<>();
        Elements elements = element.getElementsByTag("rect");
        for (Element e : elements) {
            Point point = new Point();
            point.setX(e.attr("xValue"));
            point.setY(e.attr("yValue"));
            list.add(point);
        }

        Point[] rest = new Point[list.size()];
        return list.toArray(rest);
    }


}

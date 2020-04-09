package org.xupt.cat.proxy.api.service;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.domain.responses.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lining
 * @data 2020-04-08 下午3:53
 */
@Service
public class CoreImp implements ICore {
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
}

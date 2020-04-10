package org.xupt.cat.proxy.api.service;

import org.jsoup.nodes.Element;
import org.xupt.cat.proxy.api.domain.responses.Point;
import org.xupt.cat.proxy.api.domain.responses.TargetInfo;

import java.util.List;


/**
 * @author lining
 * @data 2020-04-08 下午3:52
 */
public interface ICore {

    List<TargetInfo> parseTargetList(Element element);

    Point[] parseData(Element element);

    String[] parseYt(Element element);


}

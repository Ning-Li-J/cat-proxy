package org.xupt.cat.proxy.api.service;

import org.jsoup.nodes.Element;
import org.xupt.cat.proxy.api.domain.responses.Point;

/**
 * @author lining
 * @data 2020-04-08 下午3:52
 */
public interface ICore {

    Point[] parseData(Element element);

    String[] parseYt(Element element);


}

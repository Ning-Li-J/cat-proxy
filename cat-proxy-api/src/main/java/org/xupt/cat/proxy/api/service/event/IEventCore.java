package org.xupt.cat.proxy.api.service.event;

import org.jsoup.nodes.Document;
import org.xupt.cat.proxy.api.domain.responses.event.EventInfoResponse;
import org.xupt.cat.proxy.api.domain.responses.event.EventResponse;

/**
 * @author lining
 * @data 2020-04-08 下午12:44
 */
public interface IEventCore {

    EventResponse covertEvent(Document document, boolean isName);

    EventInfoResponse covertEventInfo(Document document);
}

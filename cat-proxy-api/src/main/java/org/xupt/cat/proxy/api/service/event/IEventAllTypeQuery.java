package org.xupt.cat.proxy.api.service.event;

import org.xupt.cat.proxy.api.domain.requests.event.EventAllTypeRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-08 下午12:45
 */
public interface IEventAllTypeQuery {

    BaseResponse queryAllType(EventAllTypeRequest request);
}

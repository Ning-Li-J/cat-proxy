package org.xupt.cat.proxy.api.service.event;

import org.xupt.cat.proxy.api.domain.requests.event.EventTypeInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-08 下午2:03
 */
public interface IEventTypeInfoQuery {

    BaseResponse queryTypeInfo(EventTypeInfoRequest request);
}

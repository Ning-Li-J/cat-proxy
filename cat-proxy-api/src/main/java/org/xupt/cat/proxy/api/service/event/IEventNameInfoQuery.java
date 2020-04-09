package org.xupt.cat.proxy.api.service.event;

import org.xupt.cat.proxy.api.domain.requests.event.EventNameInfoRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-08 下午2:13
 */
public interface IEventNameInfoQuery {

    BaseResponse queryNameInfo(EventNameInfoRequest request);
}

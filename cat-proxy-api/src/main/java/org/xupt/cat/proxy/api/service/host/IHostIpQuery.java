package org.xupt.cat.proxy.api.service.host;

import org.xupt.cat.proxy.api.domain.requests.host.HostIpRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-11 下午3:55
 */
public interface IHostIpQuery {

    BaseResponse queryHostIp(HostIpRequest request);
}

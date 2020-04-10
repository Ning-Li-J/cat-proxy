package org.xupt.cat.proxy.api.service.heart;

import org.xupt.cat.proxy.api.domain.requests.heart.HeartRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-10 下午12:44
 */
public interface IHeartQuery {

    BaseResponse queryHostHeart(HeartRequest request);
}

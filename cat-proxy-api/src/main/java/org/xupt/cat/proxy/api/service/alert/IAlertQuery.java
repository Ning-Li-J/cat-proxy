package org.xupt.cat.proxy.api.service.alert;

import org.xupt.cat.proxy.api.domain.requests.alert.AlertBaseRequest;
import org.xupt.cat.proxy.api.domain.requests.alert.AlertRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-19 下午12:34
 */
public interface IAlertQuery {

    BaseResponse queryAllAlert(AlertBaseRequest request);

    BaseResponse queryAlert(AlertRequest request);
}

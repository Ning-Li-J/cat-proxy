package org.xupt.cat.proxy.api.service.alert;

import org.xupt.cat.proxy.api.domain.requests.alert.AlertInfoRequest;
import org.xupt.cat.proxy.api.domain.requests.alert.AlertRequest;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;

/**
 * @author lining
 * @data 2020-04-19 下午6:35
 */
public interface IAlertCommand {

    BaseResponse deletAlertRule(AlertRequest request);

    BaseResponse createAlertRule(AlertInfoRequest request);
}

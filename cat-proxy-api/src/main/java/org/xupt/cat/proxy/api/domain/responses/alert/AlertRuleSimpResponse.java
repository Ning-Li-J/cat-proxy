package org.xupt.cat.proxy.api.domain.responses.alert;

import lombok.Data;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-19 下午2:42
 */
@Data
public class AlertRuleSimpResponse {

    private List<RuleSimpInfo > ruleSimpInfoList;

    @Data
    public static class RuleSimpInfo {
        private String domain;

        private String type;

        private String name;

        private String item;

        private String isAlert;
    }
}

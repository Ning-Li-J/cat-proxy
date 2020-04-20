package org.xupt.cat.proxy.api.domain;

import lombok.Data;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-19 下午5:39
 */
@Data
public class AlertRule {

    private String starttime;

    private String endtime;

    private List<Condition> conditions;

    @Data
    public static class Condition {

        private List<SubCondition> subConditions;

        private Integer minute;

        private String alertType;
    }

    @Data
    public static class SubCondition {
        private String type;

        private String text;
    }

}

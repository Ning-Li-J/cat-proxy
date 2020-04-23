package org.xupt.cat.proxy.api.domain;

import lombok.Data;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-23 下午7:14
 */
@Data
public class Condition {
    private List<SubCondition> subConditions;

    private Integer minute;

    private String alertType;


    @Data
    public static class SubCondition {
        private String type;

        private String text;
    }
}

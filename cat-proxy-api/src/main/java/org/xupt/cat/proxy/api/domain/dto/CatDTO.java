package org.xupt.cat.proxy.api.domain.dto;

import lombok.Data;

/**
 * @author lining
 * @data 2020-04-08 上午10:00
 */
@Data
public class CatDTO {
    private String domain;

    private String ip;

    private String date;

    private String step;

    private String op;

    private String reportType;

    private String type;

    private String name;

}

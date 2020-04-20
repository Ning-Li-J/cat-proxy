package org.xupt.cat.proxy.api.domain.requests.project;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-19 上午10:27
 */
@Data
public class ProjectRequest{

    @NotBlank
    private String department;

    @NotBlank
    private String productLine;

    @NotBlank
    private String domain;
}

package org.xupt.cat.proxy.api.domain.requests.auch;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lining
 * @data 2020-04-18 下午6:52
 */
@Data
public class LoginRequest {
    @NotBlank
    private String uid;

    @NotBlank
    private String pwd;
}

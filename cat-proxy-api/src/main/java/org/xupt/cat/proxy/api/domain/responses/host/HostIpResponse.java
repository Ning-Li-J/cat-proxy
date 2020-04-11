package org.xupt.cat.proxy.api.domain.responses.host;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-11 下午4:02
 */
@Data
public class HostIpResponse {

    @Autowired
    private List<String> ipList;
}

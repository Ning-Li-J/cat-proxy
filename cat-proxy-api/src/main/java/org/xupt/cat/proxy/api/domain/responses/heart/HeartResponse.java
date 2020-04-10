package org.xupt.cat.proxy.api.domain.responses.heart;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.responses.TargetInfo;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-10 下午12:54
 */
@Data
public class HeartResponse {

    private List<TargetGroup> targetGroups;

    @Data
    public static class TargetGroup {
        private String groupName;

        private List<TargetInfo> targetInfoList;
    }

}

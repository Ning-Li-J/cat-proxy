package org.xupt.cat.proxy.api.domain.responses.transaction;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.responses.Point;
import org.xupt.cat.proxy.api.domain.responses.TargetInfo;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-07 下午12:20
 */
@Data
public class TransactionInfoResponse {

    private List<TargetInfo> targetInfoList;

    private List<BranchInfo> branchInfoList;

    @Data
    public static class BranchInfo {
        private String ip;

        private String total;

        private String failure;

        private String min;

        private String max;

        private String avg;

        private String std;
    }

}

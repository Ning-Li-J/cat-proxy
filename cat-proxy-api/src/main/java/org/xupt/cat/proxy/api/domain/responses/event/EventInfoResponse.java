package org.xupt.cat.proxy.api.domain.responses.event;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.responses.Point;
import org.xupt.cat.proxy.api.domain.responses.TargetInfo;
import org.xupt.cat.proxy.api.domain.responses.transaction.TransactionInfoResponse;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-08 下午12:54
 */
@Data
public class EventInfoResponse {

    //命中数量
    private TargetInfo hitsNum;

    //命中失败的数量
    private TargetInfo failuresNum;

    private List<BranchInfo> branchInfoList;

    @Data
    public static class BranchInfo {
        private String ip;

        private String total;

        private String failure;
    }
}

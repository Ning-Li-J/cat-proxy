package org.xupt.cat.proxy.api.domain.responses;

import lombok.Data;

/**
 * @author lining
 * @data 2020-04-08 下午4:01
 */
@Data
public class TargetInfo {

    private String[] xIndex;

    private String[] yIndex;

    //数据内容 [xIndex][yValue]
    private Point[] data;
}

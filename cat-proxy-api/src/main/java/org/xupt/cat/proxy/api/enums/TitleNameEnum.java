package org.xupt.cat.proxy.api.enums;

import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-10 下午5:49
 */
public enum TitleNameEnum {

    // Transaction and Event
    DURATION_DISTRIBUTION("Duration Distribution", "请求持续时间分布"),
    HITS_OVER_TIME("Hits Over Time", "请求命中分布"),
    AVERAGE_DURATION_OVER_TIME("Average Duration Over Time", "请求平均持续时间"),
    FAILURES_OVER_TIME("Failures Over Time", "失败请求分布"),

    // System Info
    LOAD_AVERAGE("LoadAverage", "还没想好-"),
    FREE_PHYSICAL_MEMORY("FreePhysicalMemory", "还没想好"),
    FREE_SWAP_SPACE_SIZE("FreeSwapSpaceSize", "还没想好"),

    // GC Info
    OLD_GC_COUNT("OldGcCount", "还没想好"),
    PS_SCAVENGE_COUNT("PS ScavengeCount", "还没想好"),
    PS_SCAVENGE_TIME("PS ScavengeTime", "还没想好"),
    PS_MARK_SWEEP_TIME("PS MarkSweepTime", "还没想好"),

    //JVMHeap Info
    CODE_CACHE("Code Cache", "还没想好"),
    METASPACE("Metaspace", "还没想好"),
    COMPRESSED_CLASS_SPACE("Compressed Class Space", "还没想好"),
    PS_EDEN_SPACE("PS Eden Space", "还没想好"),
    PS_SURVIVOR_SPACE("PS Survivor Space", "还没想好"),
    PS_OLD_GEN("PS Old Gen", "还没想好"),

    //FrameworkThread Info
    HTTP_THREAD("HttpThread", "还没想好"),
    PIGEON_THREAD("PigeonThread", "还没想好"),
    ACTIVE_THREAD("ActiveThread", "还没想好"),
    CAT_THREAD("CatThread", "还没想好"),
    STARTED_THREAD("StartedThread", "还没想好"),

    //Disk Info
    FREE("/ Free", "还没想好"),
    DATA_FREE("/data Free", "还没想好"),

    //CatUsage Info
    PRODUCED("Produced", "还没想好"),
    OVERFLOWED("Overflowed", "还没想好"),
    BYTES("Bytes", "还没想好"),

    //client-send-queue Info
    ATOMIC_QUEUE("atomic-queue", "还没想好"),
    MSG_QUEUE("msg-queue", "还没想好"),
    ;

    private String catTitle;
    private String nowTitle;
    TitleNameEnum(String catTitle, String nowTitle) {
        this.catTitle = catTitle;
        this.nowTitle = nowTitle;
    }

    public String getName() {
        return name();
    }

    public String getNowTitle() {
        return this.nowTitle;
    }

    public String getCatTitle() {
        return this.catTitle;
    }

    public static TitleNameEnum getEnum(String catTitle) {
        catTitle = catTitle.replaceAll("\r\n|\r|\n", "").trim();
        for (TitleNameEnum titleNameEnum : values()) {
            if (titleNameEnum.catTitle.equals(catTitle)) {
                return titleNameEnum;
            }
        }
        return null;
    }
}

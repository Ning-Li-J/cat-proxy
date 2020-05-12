package org.xupt.cat.proxy.api.enums;

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
    FREE_PHYSICAL_MEMORY("FreePhysicalMemory", "空闲物理内存"),
    FREE_SWAP_SPACE_SIZE("FreeSwapSpaceSize", "空闲交换空间"),

    // GC Info
    OLD_GC_COUNT("OldGcCount", "Old GC统计"),
    PS_SCAVENGE_COUNT("PS ScavengeCount", "PS ScavengeCount"),
    PS_SCAVENGE_TIME("PS ScavengeTime", "PS ScavengeTime"),
    PS_MARK_SWEEP_TIME("PS MarkSweepTime", "标记清理时间"),

    //JVMHeap Info
    CODE_CACHE("Code Cache", "还没想好"),
    METASPACE("Metaspace", "元空间"),
    COMPRESSED_CLASS_SPACE("Compressed Class Space", "Compressed Class Space"),
    PS_EDEN_SPACE("PS Eden Space", "Eden区"),
    PS_SURVIVOR_SPACE("PS Survivor Space", "Survivor区"),
    PS_OLD_GEN("PS Old Gen", "老年代"),

    //FrameworkThread Info
    HTTP_THREAD("HttpThread", "Http线程数"),
    PIGEON_THREAD("PigeonThread", "PigeonThread"),
    ACTIVE_THREAD("ActiveThread", "连接线程数"),
    CAT_THREAD("CatThread", "CatThread"),
    STARTED_THREAD("StartedThread", "启动线程数"),

    //Disk Info
    FREE("/ Free", "空闲磁盘"),
    DATA_FREE("/data Free", "/data目录磁盘"),

    //CatUsage Info
    PRODUCED("Produced", "Produced"),
    OVERFLOWED("Overflowed", "Overflowed"),
    BYTES("Bytes", "Bytes"),

    //client-send-queue Info
    ATOMIC_QUEUE("atomic-queue", "atomic-queue"),
    MSG_QUEUE("msg-queue", "msg-queue"),
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

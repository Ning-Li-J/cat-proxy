package org.xupt.cat.proxy.api.enums;

/**
 * @author lining
 * @data 2020-04-10 下午7:19
 */
public enum GroupNameEnum {
    //
    SYSTEM_INFO("System Info", "系统指标"),
    GC_INFO("GC Info", "JVM GC指标"),
    JVM_HEAP_INFO("JVMHeap Info", "JVM堆栈指标"),
    FRAMEWORK_THREAD_INFO("FrameworkThread Info", "应用线程指标"),
    DISK_INFO("Disk Info", "磁盘指标"),
    CAT_USAGE_INFO("CatUsage Info", "监控系统指标"),
    CLIENT_SEND_QUEUE_INFO("client-send-queue Info", "客户端队列指标");

    private String catGroup;
    private String nowGroup;

    GroupNameEnum(String catGroup, String nowGroup) {
        this.catGroup = catGroup;
        this.nowGroup = nowGroup;
    }

    public String getName() {
        return name();
    }

    public String getCatGroup() {
        return this.catGroup;
    }

    public String getNowGroup() {
        return this.nowGroup;
    }

    public static GroupNameEnum getEnum(String catGroup) {
        catGroup = catGroup.replaceAll("\r\n|\r|\n", "").trim();

        for (GroupNameEnum groupNameEnum : values()) {
            if (groupNameEnum.catGroup.equals(catGroup)) {
                return groupNameEnum;
            }
        }
        return null;
    }
}

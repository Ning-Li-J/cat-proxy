package org.xupt.cat.proxy.api.enums;

/**
 * @author lining
 * @data 2020-04-10 下午7:19
 */
public enum GroupNameEnum {

    SYSTEM_INFO("System Info", "系统详情"),
    GC_INFO("GC Info", "虚拟机GC详情"),
    JVM_HEAP_INFO("JVMHeap Info", "虚拟机堆栈详情"),
    FRAMEWORK_THREAD_INFO("FrameworkThread Info", "我还没想好"),
    DISK_INFO("Disk Info", "磁盘使用详情"),
    CAT_USAGE_INFO("CatUsage Info", "我还没想好"),
    CLIENT_SEND_QUEUE_INFO("client-send-queue Info", "我还没想好")
    ;

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

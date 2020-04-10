package org.xupt.cat.proxy.api.enums;

/**
 * @author lining
 * @data 2020-04-10 下午5:48
 */
public enum UnitsEnum {

    // transaction and event
    DURATION("Duration (ms)", "毫秒"),
    COUNT("Count", "个数"),
    TIME("Time (min)", "分钟"),
    AVERAGE_DURATION("Average Duration (ms)", "毫秒"),

    // heart
    MINUTE("Minute", "分钟"),
    ;

    private String catUnits;
    private String nowUnits;

    UnitsEnum(String catUnits, String nowUnits) {
        this.catUnits = catUnits;
        this.nowUnits = nowUnits;
    }

    public String getName() {
        return name();
    }

    public String getNowUnits() {
        return this.nowUnits;
    }

    public String getCatUnits() {
        return this.catUnits;
    }

    public static UnitsEnum getEnum(String catUnits) {
        catUnits = catUnits.replaceAll("\r\n|\r|\n", "").trim();
        for (UnitsEnum unitsEnum : values()) {
            if (unitsEnum.catUnits.equals(catUnits)) {
                return unitsEnum;
            }
        }
        return null;
    }
}

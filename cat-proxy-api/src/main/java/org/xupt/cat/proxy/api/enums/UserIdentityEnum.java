package org.xupt.cat.proxy.api.enums;

/**
 * 用户身份
 * @author lining
 * @data 2020-04-11 下午6:13
 */
public enum UserIdentityEnum implements BaseEnum {

    // 管理员
    ADMIN(0, "admin"),
    ;

    private Integer code;
    private String msg;

    UserIdentityEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getDesc() {
        return this.msg;
    }

    public static UserIdentityEnum getEnum(Integer code) {
        for (UserIdentityEnum identityEnum : values()) {
            if (identityEnum.code.equals(code)) {
                return identityEnum;
            }
        }
        return null;
    }
}

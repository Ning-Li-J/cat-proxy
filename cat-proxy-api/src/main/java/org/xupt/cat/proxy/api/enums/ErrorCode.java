package org.xupt.cat.proxy.api.enums;

/**
 * @author lining
 * @data 2019/12/31
 */
public enum ErrorCode implements BaseEnum {
    //系统
    SUCCESS(100000, "SUCCESS"),
    SYSTEM_ERROR(500000, "System error"),
    REQUEST_PARAM_ERROR(500001, "Request Param Error!"),
    CAT_RESPONSE_EMPTY(500002, "Cat response error!"),
    USER_NO_EXIST(500003, "uid not exist"),
    PWD_ERROR(500004, "wrong pwd"),
    NO_LOGIN(500005, "not log in, please log in!"),
    LOGOUT_ERROR(500006, "登出失败"),
    SEND_EMAIL_ERROR(500007, "发送邮件失败");


    private int code;
    private String desc;

    ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
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
        return this.desc;
    }

    public ErrorCode getByCode(int code) {
        for (ErrorCode errorCode : values()) {
            if (code == errorCode.getCode()) {
                return errorCode;
            }
        }

        throw new IllegalArgumentException("Code not exist.");
    }
}

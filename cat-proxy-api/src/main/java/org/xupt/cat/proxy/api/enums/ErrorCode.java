package org.xupt.cat.proxy.api.enums;

/**
 * @author lining
 * @data 2019/12/31
 */
public enum ErrorCode implements BaseEnum {
    //系统
    SUCCESS(100000, "SUCCESS"),
    SYSTEM_ERROR(500000, "System error"),
    REQUEST_BAD(500001, "Request Bad"),
    REQUEST_PARAM_ERROR(5000002, "Request Param Error!"),
    CAT_RESPONSE_EMPTY(500003, "Cat response empty!");


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
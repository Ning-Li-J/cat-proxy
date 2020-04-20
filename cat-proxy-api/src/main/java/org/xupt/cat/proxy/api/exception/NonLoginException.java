package org.xupt.cat.proxy.api.exception;

/**
 * @author lining
 * @data 2020-04-15 下午5:27
 */
public class NonLoginException extends RuntimeException {

    public NonLoginException(String message) {
        super(message);
    }

    public NonLoginException(String message, Throwable e) {
        super(message, e);
    }

}

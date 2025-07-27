package com.zhora.common.exception;

import com.zhora.common.errcode.ErrorCode;

/**
 * 业务根异常处理器
 * @author zhehen.lu
 * @date 2024/6/24 17:43
 */
public abstract class AbstractException extends RuntimeException implements ErrorCode {
    protected AbstractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AbstractException(Throwable cause) {
        super(cause);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException() {
        super();
    }
}

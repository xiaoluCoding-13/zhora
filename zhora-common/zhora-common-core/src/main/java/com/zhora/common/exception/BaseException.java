package com.zhora.common.exception;

import com.zhora.common.errcode.ErrorCode;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * 基础通用异常
 * @author zhehen.lu
 * @date 2024/6/24 18:07
 */
public class BaseException extends AbstractException {
    @Setter
    private String msg;

    private ErrorCode errorCode;

    public BaseException(ErrorCode errorCode, String msg) {
        super(msg);
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public Integer getCode() {
        return errorCode.getCode();
    }

    @Override
    public String getMsg() {
        if (! StringUtils.isEmpty(msg)) {
            return msg;
        }

        return errorCode.getMsg();
    }
}

package com.zhora.common.errcode;

/**
 * 错误码 具体业务实现此接口
 * @author zhehen.lu
 * @date 2024/6/24 17:45
 */
public interface ErrorCode {
    Integer getCode();
    String getMsg();
}

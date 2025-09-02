package com.zhora.admin.errcode;


import com.zhora.common.errcode.ErrorCode;

import java.text.MessageFormat;

/**
 * admin 模块错误码
 *
 * 10000 - 19999 用户模块错误码
 * 20000 - 29999 基础模块错误码
 * 30000 - 39099 支付模块错误码
 * 40000 - 41000 authority模块错误码
 * 40000 - 49999 优惠券模块错误码
 * 50000 - 59999 金融模块错误码
 * 60000 - 60000 cms模块错误码
 * 760000 - 79999 积分模块错误码
 * @author by sherlock
 * @date 2023/11/22 9:43
 */
public enum AdminErrorCode implements ErrorCode {
    USER_NAME_MORE_THAN(10000, "用户名称太长"),
    USER_NOT_EXIST(10001, "用户不存在"),
    NOT_URL_ADDRESS(10002, "不是合法的url地址"),
    USER_OR_PASSWORD_ERROR(10002, "用户或密码错误"),
    YOU_HAVE_LOGGED_IN_ELSEWHERE_PLEASE_CHANGE_YOUR_PASSWORD_OR_LOG_IN_AGAIN(10002, "您已在别处登录，请您修改密码或重新登录"),


    ;

    private Integer code;
    private String msg;

    AdminErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg(Object... args) {
        return MessageFormat.format(getMsg(),args);
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

package com.zhora.common.errcode;

import java.text.MessageFormat;

/**
 * 通用code枚举
 * @author zhehen.lu
 * @date 2024/6/24 17:48
 */
public enum CommonCode implements ErrorCode {
    // 只使用0-1999之间的错误码
    SUCCESS(0, "成功"),
    DATE_FORMAT_ERROR(1000, "日期格式错误"),
    PARAM_ERROR(1001, "参数错误"),
    THIRD_INTERFACE_ERR(1002, "第三方接口调用失败"),
    METHOD_NOT_ALLOW(1003, "方法不允许"),
    MYSQL_ERROR(1004, "mysql错误"),
    MYSQL_UPDATE_ERROR(1005, "mysql更新错误"),
    SERVER_ERROR(1006, "服务器正在开小差,请稍后重试..."),
    UPLOAD_FILE_IS_EMPTY(1007, "上传文件为空"),
    REDIS_ERROR(1008, "redis错误"),
    IO_ERROR(1009, "IO处理失败"),
    UPLOAD_FILE_FORMAT_NO_SUPPORT(1010, "不支持该格式"),
    UPLOAD_FILE_TOO_LARGE(1011, "文件太大"),
    NO_AUTHORIZE(1012, "没有授权"),
    CLIENT_ERROR(1013, "客户端错误"),
    FEIGN_ERROR(1014, "远程调用错误"),
    PARMA_NOT_NULL(1015, "方法入参不能为空"),
    NO_AUTHORIZATION(1016, "没有认证"),
    SQL_ERROR(1017, "sql错误"),
    MYSQL_DATA_OUT_OF_RANGE(1018, "sql字段超长"),
    METHOD_NOT_FOUND(1019, "方法未找到"),
    EXPORT_PARAM_ERROR(1020, "导出参数错误"),
    IMPORT_PARAM_ERROR(1021, "导入参数错误"),
    NO_AUTH(1022, "没有权限"),
    ICE_INTERFACE_ERROR(1023, "ICE接口错误"),
    ES_ERROR(1024, "ES错误"),
    PAY_ERROR(1025, "支付相关错误"),

    ILLEGAL_REQUEST(1023, "非法请求"),
    EXPIRE_REQUEST(1024, "过期的请求"),
    REPEAT_REQUEST(1025, "重复的请求"),
    SIGN_ERROR(1026, "签名错误的请求"),

    APP_ID_ERROR(1027, "应用ID缺失！"),
    APP_SECRET_ERROR(1028, "应用私钥缺失！"),
    MQ_SEND_ERROR(1029, "MQ发送失败！"),
    ;

    private Integer code;
    private String msg;

    CommonCode(Integer code, String msg) {
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

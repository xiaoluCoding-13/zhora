package com.zhora.common.constant;

/**
 * 公共常量
 *
 * @author zhehen.lu
 * @date 2024/6/24 10:53
 */
public interface CommonConstant {
    /**
     * traceId字段 跟log-spring-boot-starter 里面MDC设置的变量一致
     */
    public static final String TRACE_ID = "traceId";

    /**
     * 授权头
     */
    String AUTHORIZATION = "Authorization";
}

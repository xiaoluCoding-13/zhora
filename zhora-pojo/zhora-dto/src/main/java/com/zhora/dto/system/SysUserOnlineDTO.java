package com.zhora.dto.system;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 在线用户记录(sys_user_online)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-28 17:26:01
 */
@Data
@Schema(name = "SysUserOnlineDTO", description = "在线用户记录信息模型")
public class SysUserOnlineDTO {
    /**
     * 用户会话id
     */
    @Schema(description = "用户会话id")
    private String sessionId;

    /**
     * 登录账号
     */
    @Schema(description = "登录账号")
    private String loginName;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 登录IP地址
     */
    @Schema(description = "登录IP地址")
    private String ipaddr;

    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Schema(description = "浏览器类型")
    private String browser;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    private String os;

    /**
     * 在线状态on_line在线off_line离线
     */
    @Schema(description = "在线状态 on_line在线 off_line离线")
    private String status;

    /**
     * session创建时间
     */
    @Schema(description = "session创建时间")
    private Date startTimestamp;

    /**
     * session最后访问时间
     */
    @Schema(description = "session最后访问时间")
    private Date lastAccessTime;

    /**
     * 超时时间，单位为分钟
     */
    @Schema(description = "超时时间，单位为分钟")
    private Long expireTime;

}


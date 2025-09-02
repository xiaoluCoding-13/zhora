package com.zhora.entity.system;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 在线用户记录(sys_user_online)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-28 17:25:45
 */
@Data
@TableName(value = "sys_user_online")
public class SysUserOnlineEntity implements Serializable {
    private static final long serialVersionUID = 858291608768038412L;
    /**
     * 用户会话id
     */
    @TableId(value = "session_id", type = IdType.ASSIGN_ID)
    private String sessionId;

    /**
     * 登录账号
     */
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 登录IP地址
     */
    @TableField(value = "ipaddr")
    private String ipaddr;

    /**
     * 登录地点
     */
    @TableField(value = "login_location")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    private String os;

    /**
     * 在线状态on_line在线off_line离线
     */
    @TableField(value = "status")
    private String status;

    /**
     * session创建时间
     */
    @TableField(value = "start_timestamp")
    private Date startTimestamp;

    /**
     * session最后访问时间
     */
    @TableField(value = "last_access_time")
    private Date lastAccessTime;

    /**
     * 超时时间，单位为分钟
     */
    @TableField(value = "expire_time")
    private Long expireTime;

}


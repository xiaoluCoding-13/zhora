package com.zhora.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 *
 * @author zhehen.lu
 * @date 2025/8/21 19:37
 */
@Data
@Schema(name = "SysUserDTO",description = "用户信息模型")
public class SysUserDTO {
    /** 用户ID */
    @Schema(description = "用户ID")
    private Long userId;

    /** 部门ID */
    @Schema(description = "部门ID")
    private Long deptId;

    /** 登录名称 */
    @Schema(description = "登录名称")
    private String loginName;

    /** 用户名称 */
    @Schema(description = "用户名称")
    private String userName;

    /** 用户类型 */
    @Schema(description = "用户类型")
    private String userType;

    /** 用户邮箱 */
    @Schema(description = "用户邮箱")
    private String email;

    /** 手机号码 */
    @Schema(description = "手机号码")
    private String phonenumber;

    /** 用户性别 */
    @Schema(description = "用户性别")
    private String sex;

    /** 用户头像 */
    @Schema(description = "用户头像")
    private String avatar;

    /** 密码 */
    @Schema(description = "密码")
    private String password;

    /** 盐加密 */
    @Schema(description = "盐加密")
    private String salt;

    /** 账号状态（0正常 1停用） */
    @Schema(description = "账号状态（0正常 1停用）")
    private String status;

    /** 最后登录IP */
    @Schema(description = "最后登录IP")
    private String loginIp;

    /** 最后登录时间 */
    @Schema(description = "最后登录时间")
    private Date loginDate;

    /** 密码最后更新时间 */
    @Schema(description = "密码最后更新时间")
    private Date pwdUpdateDate;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    /**
     * 删除标记,true:已删除,false:正常
     */
    @Schema(description = "删除标记,true:已删除,false:正常")
    private Boolean delFlag;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }
}

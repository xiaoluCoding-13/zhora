package com.zhora.admin.v1.system.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户新增
 *
 * @author zhehen.lu
 * @date 2025/8/29 11:19
 */
@Data
public class SysUserRegisterVO {
    /** 登录名称 */
    @Schema(description = "登录名称")
    private String loginName;

    /** 用户名称 */
    @Schema(description = "用户名称")
    private String userName;

    /** 密码 */
    @Schema(description = "密码")
    private String password;

    /** 盐加密 */
    @Schema(description = "盐加密")
    private String salt;

    /** 密码最后更新时间 */
    @Schema(description = "密码最后更新时间")
    private Date pwdUpdateDate;

}

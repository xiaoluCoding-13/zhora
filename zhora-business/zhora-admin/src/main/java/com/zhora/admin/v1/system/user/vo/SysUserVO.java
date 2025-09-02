package com.zhora.admin.v1.system.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户信息
 *
 * @author zhehen.lu
 * @date 2025/8/26 13:43
 */
@Data
public class SysUserVO {

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
}

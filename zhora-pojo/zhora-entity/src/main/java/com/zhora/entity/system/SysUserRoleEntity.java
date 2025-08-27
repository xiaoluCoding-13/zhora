package com.zhora.entity.system;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户和角色关联表(sys_user_role)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-27 15:05:50
 */
@Data
@TableName(value = "sys_user_role", autoResultMap = true)
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = 395539149596842921L;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 删除标记,true:已删除,false:正常
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Boolean delFlag;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}


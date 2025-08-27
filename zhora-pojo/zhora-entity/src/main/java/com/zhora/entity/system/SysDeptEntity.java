package com.zhora.entity.system;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门表(sys_dept)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-26 16:43:57
 */
@Data
@TableName(value = "sys_dept")
public class SysDeptEntity implements Serializable {
    private static final long serialVersionUID = 423654491769486795L;
    /**
     * 部门id
     */
    @TableId(value = "dept_id", type = IdType.ASSIGN_ID)
    private Long deptId;

    /**
     * 父部门id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 祖级列表
     */
    @TableField(value = "ancestors")
    private String ancestors;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 负责人
     */
    @TableField(value = "leader")
    private String leader;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 部门状态（0正常 1停用）
     */
    @TableField(value = "status")
    private String status;

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


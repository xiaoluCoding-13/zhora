package com.zhora.entity.system;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单权限表(sys_menu)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-28 16:21:01
 */
@Data
@TableName(value = "sys_menu")
public class SysMenuEntity implements Serializable {
    private static final long serialVersionUID = -31173088268779688L;
    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.ASSIGN_ID)
    private Long menuId;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 请求地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    @TableField(value = "target")
    private String target;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField(value = "menu_type")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField(value = "visible")
    private String visible;

    /**
     * 是否刷新（0刷新 1不刷新）
     */
    @TableField(value = "is_refresh")
    private String refresh;

    /**
     * 权限标识
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

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


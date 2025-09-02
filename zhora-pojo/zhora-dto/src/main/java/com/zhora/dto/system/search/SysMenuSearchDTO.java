package com.zhora.dto.system.search;

import java.util.Date;

import com.zhora.db.common.dto.PageBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单权限表(sys_menu)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-28 16:21:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SysMenuSearchDTO", description = "菜单权限表信息搜索模型")
public class SysMenuSearchDTO extends PageBaseDTO {
    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private Long menuId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;

    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private Long parentId;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
     * 请求地址
     */
    @Schema(description = "请求地址")
    private String url;

    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    @Schema(description = "打开方式（menuItem页签 menuBlank新窗口）")
    private String target;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @Schema(description = "菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @Schema(description = "菜单状态（0显示 1隐藏）")
    private String visible;

    /**
     * 是否刷新（0刷新 1不刷新）
     */
    @Schema(description = "是否刷新（0刷新 1不刷新）")
    private String isRefresh;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String perms;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 备注
     */
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

}


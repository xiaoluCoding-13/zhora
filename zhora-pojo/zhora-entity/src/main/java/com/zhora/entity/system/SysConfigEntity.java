package com.zhora.entity.system;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 参数配置表(sys_config)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-28 15:15:00
 */
@Data
@TableName(value = "sys_config")
public class SysConfigEntity implements Serializable {
    private static final long serialVersionUID = -65886717607633131L;
    /**
     * 参数主键
     */
    @TableId(value = "config_id", type = IdType.ASSIGN_ID)
    private Long configId;

    /**
     * 参数名称
     */
    @TableField(value = "config_name")
    private String configName;

    /**
     * 参数键名
     */
    @TableField(value = "config_key")
    private String configKey;

    /**
     * 参数键值
     */
    @TableField(value = "config_value")
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @TableField(value = "config_type")
    private String configType;

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


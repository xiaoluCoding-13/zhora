package com.zhora.entity.ai;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 知识库(ai_library)实体类
 *
 * @author zhehen.lu
 * @since 2025-09-10 14:57:12
 */
@Data
@TableName(value = "ai_library")
public class AiLibraryEntity implements Serializable {
    private static final long serialVersionUID = -51711661006614016L;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 知识库名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 知识库描述
     */
    @TableField(value = "description")
    private String description;

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


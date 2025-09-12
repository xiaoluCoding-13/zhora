package com.zhora.ai.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 知识库(ai_library)实体类
 *
 * @author zhehen.lu
 * @since 2025-09-10 14:57:45
 */
@Data
@Schema(name = "AiLibraryDTO", description = "知识库信息列表响应模型")
public class AiLibraryListDTO {
    @Schema(description = "id")
    private Long id;

    /**
     * 知识库名称
     */
    @Schema(description = "知识库名称")
    private String name;

    /**
     * 知识库描述
     */
    @Schema(description = "知识库描述")
    private String description;

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


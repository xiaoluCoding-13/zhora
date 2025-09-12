package com.zhora.dto.ai.search;

import java.util.Date;

import com.zhora.db.common.dto.PageBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 知识库(ai_library)实体类
 *
 * @author zhehen.lu
 * @since 2025-09-10 14:58:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "AiLibrarySearchDTO", description = "知识库信息搜索模型")
public class AiLibrarySearchDTO extends PageBaseDTO {
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


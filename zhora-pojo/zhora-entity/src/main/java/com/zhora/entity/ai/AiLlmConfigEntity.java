package com.zhora.entity.ai;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhora.db.common.config.PgEnumTypeHandler;
import com.zhora.enums.ai.LlmCodeEnum;
import com.zhora.enums.ai.LlmTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author zhehen.lu
 * @date 2025/7/29 14:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_llm_config")
public class AiLlmConfigEntity extends BaseEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 模型厂家
     */
    private String name;
    /**
     * 模型code
     */
    private LlmCodeEnum code;
    /**
     * 模型名称
     */
    private String modelName;
    /**
     * 模型类型
     */
    private LlmTypeEnum type;
    /**
     * 模型秘钥
     */
    private String apiKey;
    /**
     * 模型base地址
     */
    private String url;
    /**
     * 是否禁用
     */
    private Boolean enable;
    /**
     * 优先级
     */
    private Short priority;

    /**
     * 删除标记,true:已删除,false:正常
     */
    @TableLogic
    private Boolean delFlag;
}

package com.zhora.dto.ai;

import com.zhora.enums.ai.LlmCodeEnum;
import com.zhora.enums.ai.LlmTypeEnum;
import lombok.Data;

/**
 * 
 * @author zhehen.lu
 * @date 2025/8/22 13:59
 */
@Data
public class AiLlmConfigSearchDTO {
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
    private Boolean delFlag;
}

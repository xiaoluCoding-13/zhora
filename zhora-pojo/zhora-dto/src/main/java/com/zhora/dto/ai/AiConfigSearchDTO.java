package com.zhora.dto.ai;

import com.zhora.enums.ai.CodeEnum;
import com.zhora.enums.ai.TypeEnum;
import lombok.Data;

/**
 * 
 * @author zhehen.lu
 * @date 2025/7/29 13:59
 */
@Data
public class AiConfigSearchDTO {
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
    private CodeEnum code;
    /**
     * 模型名称
     */
    private String modelName;
    /**
     * 模型类型
     */
    private TypeEnum type;
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
     * 删除状态[0-正常;1-逻辑删除]
     */
    private Boolean delFlag;
}

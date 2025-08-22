package com.zhora.service.ai;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.dto.ai.AiLlmConfigDTO;
import com.zhora.dto.ai.AiLlmConfigSearchDTO;
import com.zhora.entity.ai.AiLlmConfigEntity;

import java.util.Optional;

/**
 * 
 * @author zhehen.lu
 * @date 2025/8/22 14:04
 */
public interface IAiLlmConfigService extends IService<AiLlmConfigEntity> {

    AiLlmConfigDTO getDetailById(Long id);

    AiLlmConfigDTO getDetail(AiLlmConfigSearchDTO searchDTO);

    Optional<AiLlmConfigEntity> getPrecedenceChatLlmBy(Boolean enable);
}

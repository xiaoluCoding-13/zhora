package com.zhora.service.ai;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.dto.ai.AiConfigDTO;
import com.zhora.dto.ai.AiConfigSearchDTO;
import com.zhora.entity.ai.AiConfigEntity;

import java.util.Optional;

/**
 * 
 * @author zhehen.lu
 * @date 2025/7/29 14:04
 */
public interface IAiConfigService extends IService<AiConfigEntity> {

    AiConfigDTO getDetailById(Long id);

    AiConfigDTO getDetail(AiConfigSearchDTO searchDTO);

    Optional<AiConfigEntity> getPrecedenceChatLlmBy(Boolean enable);
}

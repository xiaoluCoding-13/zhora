package com.zhora.service.ai.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhora.common.utils.NumberUtil;
import com.zhora.dto.ai.AiLlmConfigDTO;
import com.zhora.dto.ai.AiLlmConfigSearchDTO;
import com.zhora.entity.ai.AiLlmConfigEntity;
import com.zhora.enums.ai.LlmTypeEnum;
import com.zhora.mapper.ai.AiLlmConfigMapper;
import com.zhora.service.ai.IAiLlmConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author zhehen.lu
 * @date 2025/8/22 14:06
 */
@Service
public class AiLlmConfigServiceImpl extends ServiceImpl<AiLlmConfigMapper, AiLlmConfigEntity> implements IAiLlmConfigService {

    @Override
    public AiLlmConfigDTO getDetailById(Long id) {
        AiLlmConfigSearchDTO searchDTO = new AiLlmConfigSearchDTO();
        searchDTO.setDelFlag(Boolean.FALSE);
        return getDetail(searchDTO);
    }

    @Override
    public AiLlmConfigDTO getDetail(AiLlmConfigSearchDTO searchDTO) {
        searchDTO.setDelFlag(Boolean.FALSE);
        LambdaQueryWrapper<AiLlmConfigEntity> wrapper = getWrapper(searchDTO);
        AiLlmConfigEntity entity = this.getOne(wrapper);
        return BeanUtil.copyProperties(entity, AiLlmConfigDTO.class);
    }

    public Optional<AiLlmConfigEntity> getPrecedenceChatLlmBy(Boolean enable) {
        AiLlmConfigSearchDTO searchDTO = new AiLlmConfigSearchDTO();
        searchDTO.setEnable(enable);
        searchDTO.setDelFlag(Boolean.FALSE);

        List<AiLlmConfigEntity> aiLlmConfigEntityList = list(getWrapper(searchDTO));
        return aiLlmConfigEntityList.stream()
                .filter(aiLlmConfig -> LlmTypeEnum.CHAT.equals(aiLlmConfig.getType()))
                .max(Comparator.comparingInt(AiLlmConfigEntity::getPriority));
    }

    /**
     * 获取wrapper
     *
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper<  AiLlmConfigEntity >}
     * @date 2025/8/22 23:03
     * @author zhehen.lu
     */
    private LambdaQueryWrapper<AiLlmConfigEntity> getWrapper(AiLlmConfigSearchDTO searchDTO) {
        LambdaQueryWrapper<AiLlmConfigEntity> wrapper = Wrappers.lambdaQuery();
        return wrapper
                .eq(NumberUtil.isGtZero(searchDTO.getId()), AiLlmConfigEntity::getId, searchDTO.getId())
                .eq(StringUtils.isNotEmpty(searchDTO.getName()), AiLlmConfigEntity::getName, searchDTO.getName())
                .eq(null != searchDTO.getCode(), AiLlmConfigEntity::getCode, searchDTO.getCode())
                .eq(StringUtils.isNotEmpty(searchDTO.getModelName()), AiLlmConfigEntity::getModelName, searchDTO.getModelName())
                .eq(null != searchDTO.getType(), AiLlmConfigEntity::getType, searchDTO.getType())
                .eq(StringUtils.isNotEmpty(searchDTO.getApiKey()), AiLlmConfigEntity::getApiKey, searchDTO.getApiKey())
                .eq(StringUtils.isNotEmpty(searchDTO.getUrl()), AiLlmConfigEntity::getUrl, searchDTO.getUrl())
                .eq(null != searchDTO.getEnable(), AiLlmConfigEntity::getEnable, searchDTO.getEnable())
                .eq(null != searchDTO.getPriority(), AiLlmConfigEntity::getPriority, searchDTO.getPriority())
                ;
    }

}
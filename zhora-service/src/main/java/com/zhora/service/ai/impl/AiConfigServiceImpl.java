package com.zhora.service.ai.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhora.common.utils.NumberUtil;
import com.zhora.dto.ai.AiConfigDTO;
import com.zhora.dto.ai.AiConfigSearchDTO;
import com.zhora.entity.ai.AiConfigEntity;
import com.zhora.enums.ai.TypeEnum;
import com.zhora.mapper.ai.AiConfigMapper;
import com.zhora.service.ai.IAiConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author zhehen.lu
 * @date 2025/7/29 14:06
 */
@Service
public class AiConfigServiceImpl extends ServiceImpl<AiConfigMapper, AiConfigEntity> implements IAiConfigService {

    @Override
    public AiConfigDTO getDetailById(Long id) {
        AiConfigSearchDTO searchDTO = new AiConfigSearchDTO();
        searchDTO.setDelFlag(Boolean.FALSE);
        return getDetail(searchDTO);
    }

    @Override
    public AiConfigDTO getDetail(AiConfigSearchDTO searchDTO) {
        searchDTO.setDelFlag(Boolean.FALSE);
        LambdaQueryWrapper<AiConfigEntity> wrapper = getWrapper(searchDTO);
        AiConfigEntity entity = this.getOne(wrapper);
        return BeanUtil.copyProperties(entity, AiConfigDTO.class);
    }

    public Optional<AiConfigEntity> getPrecedenceChatLlmBy(Boolean enable) {
        AiConfigSearchDTO searchDTO = new AiConfigSearchDTO();
        searchDTO.setEnable(enable);
        searchDTO.setDelFlag(Boolean.FALSE);

        List<AiConfigEntity> aiConfigEntityList = list(getWrapper(searchDTO));
        return aiConfigEntityList.stream()
                .filter(aiLlmConfig -> TypeEnum.CHAT.equals(aiLlmConfig.getType()))
                .max(Comparator.comparingInt(AiConfigEntity::getPriority));
    }

    /**
     * 获取wrapper
     *
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper< AiConfigEntity>}
     * @date 2025/7/28 23:03
     * @author zhehen.lu
     */
    private LambdaQueryWrapper<AiConfigEntity> getWrapper(AiConfigSearchDTO searchDTO) {
        LambdaQueryWrapper<AiConfigEntity> wrapper = Wrappers.lambdaQuery();
        return wrapper
                .eq(NumberUtil.isGtZero(searchDTO.getId()), AiConfigEntity::getId, searchDTO.getId())
                .eq(StringUtils.isNotEmpty(searchDTO.getName()), AiConfigEntity::getName, searchDTO.getName())
                .eq(null != searchDTO.getCode(), AiConfigEntity::getCode, searchDTO.getCode())
                .eq(StringUtils.isNotEmpty(searchDTO.getModelName()), AiConfigEntity::getModelName, searchDTO.getModelName())
                .eq(null != searchDTO.getType(), AiConfigEntity::getType, searchDTO.getType())
                .eq(StringUtils.isNotEmpty(searchDTO.getApiKey()), AiConfigEntity::getApiKey, searchDTO.getApiKey())
                .eq(StringUtils.isNotEmpty(searchDTO.getUrl()), AiConfigEntity::getUrl, searchDTO.getUrl())
                .eq(null != searchDTO.getEnable(), AiConfigEntity::getEnable, searchDTO.getEnable())
                .eq(null != searchDTO.getPriority(), AiConfigEntity::getPriority, searchDTO.getPriority())
                ;
    }

}
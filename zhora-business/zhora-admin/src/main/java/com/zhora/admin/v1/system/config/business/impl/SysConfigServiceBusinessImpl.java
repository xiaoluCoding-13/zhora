package com.zhora.admin.v1.system.config.business.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zhora.admin.v1.system.config.business.ISysConfigBusinessService;
import com.zhora.admin.v1.system.config.dto.SysConfigDetailDTO;
import com.zhora.admin.v1.system.config.vo.SysConfigCheckVO;
import com.zhora.admin.v1.system.config.vo.SysConfigCreateVO;
import com.zhora.admin.v1.system.config.vo.SysConfigListVO;
import com.zhora.admin.v1.system.config.vo.SysConfigUpdateVO;
import com.zhora.common.exception.ServiceException;
import com.zhora.common.utils.CacheUtils;
import com.zhora.common.utils.Convert;
import com.zhora.constant.Constants;
import com.zhora.constant.UserConstants;
import com.zhora.dto.system.SysConfigDTO;
import com.zhora.dto.system.search.SysConfigSearchDTO;
import com.zhora.service.system.ISysConfigService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 参数配置 服务层实现
 *
 * @author ruoyi
 */
@Service
public class SysConfigServiceBusinessImpl implements ISysConfigBusinessService {
    @Autowired
    private ISysConfigService configService;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init() {
        loadingConfigCache();
    }

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public SysConfigDetailDTO selectConfigById(Long configId) {
        SysConfigSearchDTO searchDTO = new SysConfigSearchDTO();
        searchDTO.setConfigId(configId);
        searchDTO.setDelFlag(Boolean.FALSE);

        return BeanUtil.copyProperties(configService.getDetail(searchDTO), SysConfigDetailDTO.class);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = Convert.toStr(CacheUtils.get(getCacheName(), getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }
        SysConfigSearchDTO searchDTO = new SysConfigSearchDTO();
        searchDTO.setConfigKey(configKey);
        searchDTO.setDelFlag(Boolean.FALSE);

        SysConfigDTO retConfig = configService.getDetail(searchDTO);
        if (Objects.nonNull(retConfig)) {
            CacheUtils.put(getCacheName(), getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfigDTO> selectConfigList(SysConfigListVO config) {
        SysConfigSearchDTO searchDTO = BeanUtil.copyProperties(config, SysConfigSearchDTO.class);
        return configService.list(searchDTO);
    }

    /**
     * 新增参数配置
     *
     * @param createVO 参数配置信息
     * @return 结果
     */
    @Override
    public Long insertConfig(SysConfigCreateVO createVO) {
        Long row = configService.create(BeanUtil.copyProperties(createVO, SysConfigDTO.class));
        if (row > 0) {
            CacheUtils.put(getCacheName(), getCacheKey(createVO.getConfigKey()), createVO.getConfigValue());
        }
        return row;
    }

    /**
     * 修改参数配置
     *
     * @param updateVO 参数配置信息
     * @return 结果
     */
    @Override
    public Long updateConfig(SysConfigUpdateVO updateVO) {
        SysConfigDTO temp = configService.getDetailById(updateVO.getConfigId());
        if (!StringUtils.equals(temp.getConfigKey(), updateVO.getConfigKey())) {
            CacheUtils.remove(getCacheName(), getCacheKey(temp.getConfigKey()));
        }

        Long row = configService.updateById(BeanUtil.copyProperties(updateVO, SysConfigDTO.class));
        if (row > 0) {
            CacheUtils.put(getCacheName(), getCacheKey(updateVO.getConfigKey()), updateVO.getConfigValue());
        }
        return row;
    }

    /**
     * 批量删除参数配置对象
     *
     * @param ids 需要删除的数据ID
     */
    @Override
    public void deleteConfigByIds(String ids) {
        Long[] configIds = Convert.toLongArray(ids);
        for (Long configId : configIds) {
            SysConfigDetailDTO config = selectConfigById(configId);
            if (StringUtils.equals(UserConstants.YES, config.getConfigType())) {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            configService.removeById(configId);
            CacheUtils.remove(getCacheName(), getCacheKey(config.getConfigKey()));
        }
    }

    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache() {
        SysConfigSearchDTO searchDTO = new SysConfigSearchDTO();
        List<SysConfigDTO> configsList = configService.list(searchDTO);
        for (SysConfigDTO config : configsList) {
            CacheUtils.put(getCacheName(), getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache() {
        CacheUtils.removeAll(getCacheName());
    }

    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param checkVO 参数配置信息
     * @return 结果
     */
    @Override
    public boolean checkConfigKeyUnique(SysConfigCheckVO checkVO) {
        Long configId = null == checkVO.getConfigId() ? -1L : checkVO.getConfigId();

        SysConfigSearchDTO searchDTO = new SysConfigSearchDTO();
        searchDTO.setConfigKey(checkVO.getConfigKey());
        searchDTO.setDelFlag(Boolean.FALSE);

        SysConfigDTO info = configService.getDetail(searchDTO);
        if (Objects.nonNull(info) && info.getConfigId().longValue() != configId.longValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    private String getCacheName() {
        return Constants.SYS_CONFIG_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}

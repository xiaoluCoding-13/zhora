package com.zhora.admin.v1.system.config.business;

import com.zhora.admin.v1.system.config.dto.SysConfigDetailDTO;
import com.zhora.admin.v1.system.config.vo.SysConfigCheckVO;
import com.zhora.admin.v1.system.config.vo.SysConfigCreateVO;
import com.zhora.admin.v1.system.config.vo.SysConfigListVO;
import com.zhora.admin.v1.system.config.vo.SysConfigUpdateVO;
import com.zhora.dto.system.SysConfigDTO;

import java.util.List;

/**
 * 参数配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysConfigBusinessService {
    /**
     * 查询参数配置信息
     * 
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    SysConfigDetailDTO selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数键值
     */
    String selectConfigByKey(String configKey);

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<SysConfigDTO> selectConfigList(SysConfigListVO listVO);

    /**
     * 新增参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    Long insertConfig(SysConfigCreateVO createVO);

    /**
     * 修改参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    Long updateConfig(SysConfigUpdateVO updateVO);

    /**
     * 批量删除参数配置信息
     * 
     * @param ids 需要删除的数据ID
     */
    void deleteConfigByIds(String ids);

    /**
     * 加载参数缓存数据
     */
    void loadingConfigCache();

    /**
     * 清空参数缓存数据
     */
    void clearConfigCache();

    /**
     * 重置参数缓存数据
     */
    void resetConfigCache();

    /**
     * 校验参数键名是否唯一
     * 
     * @param config 参数信息
     * @return 结果
     */
    boolean checkConfigKeyUnique(SysConfigCheckVO checkVO);
}

package com.zhora.service.system.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.utils.NumberUtil;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.db.common.util.PageDataGridRespUtil;
import com.zhora.db.common.util.PageQueryUtil;
import com.zhora.dto.system.SysConfigDTO;
import com.zhora.dto.system.search.SysConfigSearchDTO;
import com.zhora.entity.system.SysConfigEntity;
import com.zhora.mapper.system.SysConfigMapper;
import com.zhora.service.system.ISysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参数配置表(sys_config)表服务实现类
 *
 * @author zhehen.lu
 * @since 2025-08-28 15:19:07
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfigEntity> implements ISysConfigService {

    /**
     * 根据条件分页查询参数配置表列表
     *
     * @param searchDTO
     * @return {@link PageDataGridRespDTO< SysConfigDTO>}
     * @date 2025-08-28 15:19:07
     * @author zhehen.lu
     */
    @Override
    public PageDataGridRespDTO<SysConfigDTO> listPage(SysConfigSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysConfigEntity> wrapper = getWrapper(searchDTO);

        PageQueryUtil<SysConfigEntity, SysConfigSearchDTO> pageQueryUtil = new PageQueryUtil<>(
                SysConfigEntity.class,
                PageQueryUtil.TypeEnum.PARAM_PRIORITY_DEFAULT_PRIMARY,
                searchDTO
        );

        IPage<SysConfigEntity> page = wrapper.page(pageQueryUtil.buildPageObj());

        return PageDataGridRespUtil.convert(page, SysConfigDTO.class);
    }

    /**
     * 创建参数配置表
     *
     * @param dto
     * @date 2025-08-28 15:19:07
     * @author zhehen.lu
     */
    @Override
    public Long create(SysConfigDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysConfigEntity entity = BeanUtil.copyProperties(dto, SysConfigEntity.class);

        save(entity);

        return entity.getConfigId();
    }

    /**
     * 依据ID获取参数配置表详情
     *
     * @param id
     * @return {@link SysConfigDTO}
     * @date 2025-08-28 15:19:07
     * @author zhehen.lu
     */
    @Override
    public SysConfigDTO getDetailById(Long id) {
        SysConfigSearchDTO searchDTO = new SysConfigSearchDTO();
        searchDTO.setConfigId(id);
        searchDTO.setDelFlag(Boolean.FALSE);

        return getDetail(searchDTO);
    }

    /**
     * 获取参数配置表详情
     *
     * @param searchDTO
     * @return {@link SysConfigDTO}
     * @date 2025-08-28 15:19:07
     * @author zhehen.lu
     */
    @Override
    public SysConfigDTO getDetail(SysConfigSearchDTO searchDTO) {
        LambdaQueryChainWrapper<SysConfigEntity> wrapper = getWrapper(searchDTO);
        SysConfigEntity entity = wrapper
                .last("LIMIT 1")
                .one();

        return BeanUtil.copyProperties(entity, SysConfigDTO.class);
    }

    /**
     * 依据ID更新参数配置表数据
     *
     * @param dto
     * @date 2025-08-28 15:19:07
     * @author zhehen.lu
     */
    @Override
    public Long updateById(SysConfigDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysConfigEntity entity = BeanUtil.copyProperties(dto, SysConfigEntity.class);

        updateById(entity);

        return entity.getConfigId();
    }

    /**
     * 获取参数配置表列表
     *
     * @param searchDTO
     * @return {@link List< SysConfigDTO>}
     * @date 2025-08-28 15:19:07
     * @author zhehen.lu
     */
    @Override
    public List<SysConfigDTO> list(SysConfigSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysConfigEntity> wrapper = getWrapper(searchDTO);

        List<SysConfigEntity> entityList = wrapper.list();

        return BeanUtil.copyToList(entityList, SysConfigDTO.class);
    }

    /**
     * 获取参数配置表的Wrapper对象
     *
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper< SysConfigEntity>}
     * @date 2025-08-28 15:19:07
     * @author zhehen.lu
     */
    private LambdaQueryChainWrapper<SysConfigEntity> getWrapper(SysConfigSearchDTO searchDTO) {
        return lambdaQuery()
                .eq(NumberUtil.isGtZero(searchDTO.getConfigId()), SysConfigEntity::getConfigId, searchDTO.getConfigId())
                .eq(StringUtils.isNoneEmpty(searchDTO.getConfigKey()), SysConfigEntity::getConfigKey, searchDTO.getConfigKey())
                .eq(null != searchDTO.getDelFlag(), SysConfigEntity::getDelFlag, searchDTO.getDelFlag());
    }

}

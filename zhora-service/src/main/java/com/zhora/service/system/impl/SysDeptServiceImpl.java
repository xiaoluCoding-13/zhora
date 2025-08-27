package com.zhora.service.system.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.db.common.util.PageDataGridRespUtil;
import com.zhora.db.common.util.PageQueryUtil;
import com.zhora.dto.system.SysDeptDTO;
import com.zhora.dto.system.search.SysDeptSearchDTO;
import com.zhora.entity.system.SysDeptEntity;
import com.zhora.mapper.system.SysDeptMapper;
import com.zhora.service.system.ISysDeptService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门表(sys_dept)表服务实现类
 *
 * @author zhehen.lu
 * @since 2025-08-26 18:02:15
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements ISysDeptService {

    /**
     * 根据条件分页查询部门表列表
     *
     * @param searchDTO
     * @return {@link PageDataGridRespDTO< SysDeptDTO>}
     * @date 2025-08-26 18:02:15
     * @author zhehen.lu
     */
    @Override
    public PageDataGridRespDTO<SysDeptDTO> listPage(SysDeptSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysDeptEntity> wrapper = getWrapper(searchDTO);

        PageQueryUtil<SysDeptEntity, SysDeptSearchDTO> pageQueryUtil = new PageQueryUtil<>(
                SysDeptEntity.class,
                PageQueryUtil.TypeEnum.PARAM_PRIORITY_DEFAULT_PRIMARY,
                searchDTO
        );

        IPage<SysDeptEntity> page = wrapper.page(pageQueryUtil.buildPageObj());

        return PageDataGridRespUtil.convert(page, SysDeptDTO.class);
    }

    /**
     * 创建部门表
     *
     * @param dto
     * @date 2025-08-26 18:02:15
     * @author zhehen.lu
     */
    @Override
    public void create(SysDeptDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysDeptEntity entity = BeanUtil.copyProperties(dto, SysDeptEntity.class);

        save(entity);
    }

    /**
     * 依据ID获取部门表详情
     *
     * @param id
     * @return {@link SysDeptDTO}
     * @date 2025-08-26 18:02:15
     * @author zhehen.lu
     */
    @Override
    public SysDeptDTO getDetailById(Long id) {
        SysDeptSearchDTO searchDTO = new SysDeptSearchDTO();
        searchDTO.setDeptId(id);
        searchDTO.setDelFlag(Boolean.FALSE);

        return getDetail(searchDTO);
    }

    /**
     * 获取部门表详情
     *
     * @param searchDTO
     * @return {@link SysDeptDTO}
     * @date 2025-08-26 18:02:15
     * @author zhehen.lu
     */
    @Override
    public SysDeptDTO getDetail(SysDeptSearchDTO searchDTO) {
        LambdaQueryChainWrapper<SysDeptEntity> wrapper = getWrapper(searchDTO);
        SysDeptEntity entity = wrapper
                .last("LIMIT 1")
                .one();

        return BeanUtil.copyProperties(entity, SysDeptDTO.class);
    }

    /**
     * 依据ID更新部门表数据
     *
     * @param dto
     * @date 2025-08-26 18:02:15
     * @author zhehen.lu
     */
    @Override
    public void updateById(SysDeptDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysDeptEntity entity = BeanUtil.copyProperties(dto, SysDeptEntity.class);

        updateById(entity);
    }

    /**
     * 获取部门表列表
     *
     * @param searchDTO
     * @return {@link List< SysDeptDTO>}
     * @date 2025-08-26 18:02:15
     * @author zhehen.lu
     */
    @Override
    public List<SysDeptDTO> list(SysDeptSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysDeptEntity> wrapper = getWrapper(searchDTO);

        List<SysDeptEntity> entityList = wrapper.list();

        return BeanUtil.copyToList(entityList, SysDeptDTO.class);
    }

    /**
     * 获取部门表的Wrapper对象
     *
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper< SysDeptEntity>}
     * @date 2025-08-26 18:02:15
     * @author zhehen.lu
     */
    private LambdaQueryChainWrapper<SysDeptEntity> getWrapper(SysDeptSearchDTO searchDTO) {
        return lambdaQuery()
                .in(CollectionUtils.isNotEmpty(searchDTO.getDeptIdList()), SysDeptEntity::getDeptId, searchDTO.getDeptIdList())
                .eq(null != searchDTO.getDelFlag(), SysDeptEntity::getDelFlag, Boolean.FALSE);
    }

}

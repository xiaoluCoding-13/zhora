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
import com.zhora.dto.system.SysUserRoleDTO;
import com.zhora.dto.system.search.SysUserRoleSearchDTO;
import com.zhora.entity.system.SysUserRoleEntity;
import com.zhora.mapper.system.SysUserRoleMapper;
import com.zhora.service.system.ISysUserRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户和角色关联表(sys_user_role)表服务实现类
 *
 * @author zhehen.lu
 * @since 2025-08-27 15:28:37
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity> implements ISysUserRoleService {

    /**
     * 根据条件分页查询用户和角色关联表列表
     *
     * @param searchDTO
     * @return {@link PageDataGridRespDTO< SysUserRoleDTO>}
     * @date 2025-08-27 15:28:37
     * @author zhehen.lu
     */
    @Override
    public PageDataGridRespDTO<SysUserRoleDTO> listPage(SysUserRoleSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysUserRoleEntity> wrapper = getWrapper(searchDTO);

        PageQueryUtil<SysUserRoleEntity, SysUserRoleSearchDTO> pageQueryUtil = new PageQueryUtil<>(
                SysUserRoleEntity.class,
                PageQueryUtil.TypeEnum.PARAM_PRIORITY_DEFAULT_PRIMARY,
                searchDTO
        );

        IPage<SysUserRoleEntity> page = wrapper.page(pageQueryUtil.buildPageObj());

        return PageDataGridRespUtil.convert(page, SysUserRoleDTO.class);
    }

    /**
     * 创建用户和角色关联表
     *
     * @param dto
     * @date 2025-08-27 15:28:37
     * @author zhehen.lu
     */
    @Override
    public void create(SysUserRoleDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysUserRoleEntity entity = BeanUtil.copyProperties(dto, SysUserRoleEntity.class);

        save(entity);
    }

    /**
     * 依据ID获取用户和角色关联表详情
     *
     * @param id
     * @return {@link SysUserRoleDTO}
     * @date 2025-08-27 15:28:37
     * @author zhehen.lu
     */
    @Override
    public SysUserRoleDTO getDetailByUserId(Long id) {
        SysUserRoleSearchDTO searchDTO = new SysUserRoleSearchDTO();
        searchDTO.setUserId(id);
        searchDTO.setDelFlag(Boolean.FALSE);

        return getDetail(searchDTO);
    }

    /**
     * 依据ID获取用户和角色关联表详情
     *
     * @param id
     * @return {@link SysUserRoleDTO}
     * @date 2025-08-27 15:28:37
     * @author zhehen.lu
     */
    @Override
    public SysUserRoleDTO getDetailByRoleId(Long id) {
        SysUserRoleSearchDTO searchDTO = new SysUserRoleSearchDTO();
        searchDTO.setRoleId(id);
        searchDTO.setDelFlag(Boolean.FALSE);

        return getDetail(searchDTO);
    }

    /**
     * 获取用户和角色关联表详情
     *
     * @param searchDTO
     * @return {@link SysUserRoleDTO}
     * @date 2025-08-27 15:28:37
     * @author zhehen.lu
     */
    @Override
    public SysUserRoleDTO getDetail(SysUserRoleSearchDTO searchDTO) {
        LambdaQueryChainWrapper<SysUserRoleEntity> wrapper = getWrapper(searchDTO);
        SysUserRoleEntity entity = wrapper
                .last("LIMIT 1")
                .one();

        return BeanUtil.copyProperties(entity, SysUserRoleDTO.class);
    }

    /**
     * 依据ID更新用户和角色关联表数据
     *
     * @param dto
     * @date 2025-08-27 15:28:37
     * @author zhehen.lu
     */
    @Override
    public void updateById(SysUserRoleDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysUserRoleEntity entity = BeanUtil.copyProperties(dto, SysUserRoleEntity.class);

        updateById(entity);
    }

    /**
     * 获取用户和角色关联表列表
     *
     * @param searchDTO
     * @return {@link List< SysUserRoleDTO>}
     * @date 2025-08-27 15:28:37
     * @author zhehen.lu
     */
    @Override
    public List<SysUserRoleDTO> list(SysUserRoleSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysUserRoleEntity> wrapper = getWrapper(searchDTO);

        List<SysUserRoleEntity> entityList = wrapper.list();

        return BeanUtil.copyToList(entityList, SysUserRoleDTO.class);
    }

    /**
     * 获取用户和角色关联表的Wrapper对象
     *
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper< SysUserRoleEntity>}
     * @date 2025-08-27 15:28:37
     * @author zhehen.lu
     */
    private LambdaQueryChainWrapper<SysUserRoleEntity> getWrapper(SysUserRoleSearchDTO searchDTO) {
        return lambdaQuery()
                .in(CollectionUtils.isNotEmpty(searchDTO.getUserIdList()), SysUserRoleEntity::getUserId, searchDTO.getUserIdList())
                .eq(null != searchDTO.getDelFlag(), SysUserRoleEntity::getDelFlag, Boolean.FALSE);
    }

}

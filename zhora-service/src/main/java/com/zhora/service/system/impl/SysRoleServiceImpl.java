package com.zhora.service.system.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.db.common.util.PageDataGridRespUtil;
import com.zhora.db.common.util.PageQueryUtil;
import com.zhora.dto.system.SysRoleDTO;
import com.zhora.dto.system.search.SysRoleSearchDTO;
import com.zhora.entity.system.SysRoleEntity;
import com.zhora.mapper.system.SysRoleMapper;
import com.zhora.service.system.ISysRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 角色信息表(sys_role)表服务实现类
 *
 * @author zhehen.lu
 * @since 2025-08-26 18:59:58
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {

    /**
     * 根据条件分页查询角色信息表列表
     *
     * @param searchDTO
     * @return {@link PageDataGridRespDTO< SysRoleDTO>}
     * @date 2025-08-26 18:59:58
     * @author zhehen.lu
     */
    @Override
    public PageDataGridRespDTO<SysRoleDTO> listPage(SysRoleSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysRoleEntity> wrapper = getWrapper(searchDTO);

        PageQueryUtil<SysRoleEntity, SysRoleSearchDTO> pageQueryUtil = new PageQueryUtil<>(
                SysRoleEntity.class,
                PageQueryUtil.TypeEnum.PARAM_PRIORITY_DEFAULT_PRIMARY,
                searchDTO
        );

        IPage<SysRoleEntity> page = wrapper.page(pageQueryUtil.buildPageObj());

        return PageDataGridRespUtil.convert(page, SysRoleDTO.class);
    }

    /**
     * 创建角色信息表
     *
     * @param dto
     * @date 2025-08-26 18:59:58
     * @author zhehen.lu
     */
    @Override
    public void create(SysRoleDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysRoleEntity entity = BeanUtil.copyProperties(dto, SysRoleEntity.class);

        save(entity);
    }

    /**
     * 依据ID获取角色信息表详情
     *
     * @param id
     * @return {@link SysRoleDTO}
     * @date 2025-08-26 18:59:58
     * @author zhehen.lu
     */
    @Override
    public SysRoleDTO getDetailById(Long id) {
        SysRoleSearchDTO searchDTO = new SysRoleSearchDTO();
        searchDTO.setRoleId(id);
        searchDTO.setDelFlag(Boolean.FALSE);

        return getDetail(searchDTO);
    }

    /**
     * 获取角色信息表详情
     *
     * @param searchDTO
     * @return {@link SysRoleDTO}
     * @date 2025-08-26 18:59:58
     * @author zhehen.lu
     */
    @Override
    public SysRoleDTO getDetail(SysRoleSearchDTO searchDTO) {
        LambdaQueryChainWrapper<SysRoleEntity> wrapper = getWrapper(searchDTO);
        SysRoleEntity entity = wrapper
                .last("LIMIT 1")
                .one();

        return BeanUtil.copyProperties(entity, SysRoleDTO.class);
    }

    /**
     * 依据ID更新角色信息表数据
     *
     * @param dto
     * @date 2025-08-26 18:59:58
     * @author zhehen.lu
     */
    @Override
    public void updateById(SysRoleDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysRoleEntity entity = BeanUtil.copyProperties(dto, SysRoleEntity.class);

        updateById(entity);
    }

    /**
     * 获取角色信息表列表
     *
     * @param searchDTO
     * @return {@link List< SysRoleDTO>}
     * @date 2025-08-26 18:59:58
     * @author zhehen.lu
     */
    @Override
    public List<SysRoleDTO> list(SysRoleSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysRoleEntity> wrapper = getWrapper(searchDTO);

        List<SysRoleEntity> entityList = wrapper.list();

        return BeanUtil.copyToList(entityList, SysRoleDTO.class);
    }

    @Override
    public Set<String> selectRoleKeys(Long userId) {
        List<SysRoleEntity> perms = this.baseMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRoleEntity perm : perms) {
            if (Objects.nonNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(StringPool.COMMA)));
            }
        }
        return permsSet;
    }

    /**
     * 获取角色信息表的Wrapper对象
     *
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper< SysRoleEntity>}
     * @date 2025-08-26 18:59:58
     * @author zhehen.lu
     */
    private LambdaQueryChainWrapper<SysRoleEntity> getWrapper(SysRoleSearchDTO searchDTO) {
        return lambdaQuery()
                .in(CollectionUtils.isNotEmpty(searchDTO.getRoleIdList()), SysRoleEntity::getRoleId, searchDTO.getRoleIdList())
                .eq(null != searchDTO.getDelFlag(), SysRoleEntity::getDelFlag, Boolean.FALSE);
    }

}

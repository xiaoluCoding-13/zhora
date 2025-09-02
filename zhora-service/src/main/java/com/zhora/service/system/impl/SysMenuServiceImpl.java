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
import com.zhora.dto.system.SysMenuDTO;
import com.zhora.dto.system.search.SysMenuSearchDTO;
import com.zhora.entity.system.SysMenuEntity;
import com.zhora.mapper.system.SysMenuMapper;
import com.zhora.service.system.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单权限表(sys_menu)表服务实现类
 *
 * @author zhehen.lu
 * @since 2025-08-28 16:22:18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements ISysMenuService {

    /**
     * 根据条件分页查询菜单权限表列表
     *
     * @param searchDTO
     * @return {@link PageDataGridRespDTO< SysMenuDTO>}
     * @date 2025-08-28 16:22:18
     * @author zhehen.lu
     */
    @Override
    public PageDataGridRespDTO<SysMenuDTO> listPage(SysMenuSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysMenuEntity> wrapper = getWrapper(searchDTO);

        PageQueryUtil<SysMenuEntity, SysMenuSearchDTO> pageQueryUtil = new PageQueryUtil<>(
                SysMenuEntity.class,
                PageQueryUtil.TypeEnum.PARAM_PRIORITY_DEFAULT_PRIMARY,
                searchDTO
        );

        IPage<SysMenuEntity> page = wrapper.page(pageQueryUtil.buildPageObj());

        return PageDataGridRespUtil.convert(page, SysMenuDTO.class);
    }

    /**
     * 创建菜单权限表
     *
     * @param dto
     * @date 2025-08-28 16:22:18
     * @author zhehen.lu
     */
    @Override
    public void create(SysMenuDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysMenuEntity entity = BeanUtil.copyProperties(dto, SysMenuEntity.class);

        save(entity);
    }

    /**
     * 依据ID获取菜单权限表详情
     *
     * @param id
     * @return {@link SysMenuDTO}
     * @date 2025-08-28 16:22:18
     * @author zhehen.lu
     */
    @Override
    public SysMenuDTO getDetailById(Long id) {
        SysMenuSearchDTO searchDTO = new SysMenuSearchDTO();
        searchDTO.setMenuId(id);
        searchDTO.setDelFlag(Boolean.FALSE);

        return getDetail(searchDTO);
    }

    /**
     * 获取菜单权限表详情
     *
     * @param searchDTO
     * @return {@link SysMenuDTO}
     * @date 2025-08-28 16:22:18
     * @author zhehen.lu
     */
    @Override
    public SysMenuDTO getDetail(SysMenuSearchDTO searchDTO) {
        LambdaQueryChainWrapper<SysMenuEntity> wrapper = getWrapper(searchDTO);
        SysMenuEntity entity = wrapper
                .last("LIMIT 1")
                .one();

        return BeanUtil.copyProperties(entity, SysMenuDTO.class);
    }

    /**
     * 依据ID更新菜单权限表数据
     *
     * @param dto
     * @date 2025-08-28 16:22:18
     * @author zhehen.lu
     */
    @Override
    public void updateById(SysMenuDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysMenuEntity entity = BeanUtil.copyProperties(dto, SysMenuEntity.class);

        updateById(entity);
    }

    /**
     * 获取菜单权限表列表
     *
     * @param searchDTO
     * @return {@link List< SysMenuDTO>}
     * @date 2025-08-28 16:22:18
     * @author zhehen.lu
     */
    @Override
    public List<SysMenuDTO> list(SysMenuSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysMenuEntity> wrapper = getWrapper(searchDTO);

        List<SysMenuEntity> entityList = wrapper.list();

        return BeanUtil.copyToList(entityList, SysMenuDTO.class);
    }

    @Override
    public List<SysMenuDTO> selectMenuAll() {
        return List.of();
    }

    @Override
    public List<SysMenuDTO> selectMenuAllByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<SysMenuDTO> selectMenuNormalAll() {
        return List.of();
    }

    @Override
    public List<SysMenuDTO> selectMenusByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<String> selectPermsByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<String> selectPermsByRoleId(Long roleId) {
        return List.of();
    }

    @Override
    public List<String> selectMenuTree(Long roleId) {
        return List.of();
    }

    @Override
    public List<SysMenuDTO> selectMenuList(SysMenuDTO menu) {
        return List.of();
    }

    @Override
    public List<SysMenuDTO> selectMenuListByUserId(SysMenuDTO menu) {
        return List.of();
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public SysMenuDTO selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public int selectCountMenuByParentId(Long parentId) {
        return 0;
    }

    @Override
    public int insertMenu(SysMenuDTO menu) {
        return 0;
    }

    @Override
    public int updateMenu(SysMenuDTO menu) {
        return 0;
    }

    @Override
    public void updateMenuSort(SysMenuDTO menu) {

    }

    @Override
    public SysMenuDTO checkMenuNameUnique(String menuName, Long parentId) {
        return null;
    }

    /**
     * 获取菜单权限表的Wrapper对象
     *
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper< SysMenuEntity>}
     * @date 2025-08-28 16:22:18
     * @author zhehen.lu
     */
    private LambdaQueryChainWrapper<SysMenuEntity> getWrapper(SysMenuSearchDTO searchDTO) {
        return lambdaQuery()
                .eq(NumberUtil.isGtZero(searchDTO.getMenuId()), SysMenuEntity::getMenuId, searchDTO.getMenuId());
    }

}

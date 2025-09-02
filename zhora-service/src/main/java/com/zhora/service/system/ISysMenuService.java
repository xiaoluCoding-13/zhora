package com.zhora.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.dto.system.SysMenuDTO;
import com.zhora.dto.system.search.SysMenuSearchDTO;
import com.zhora.entity.system.SysMenuEntity;

import java.util.List;

/**
 * 菜单权限表(sys_menu)表服务接口
 *
 * @author zhehen.lu
 * @since 2025-08-28 16:22:04
 */
public interface ISysMenuService extends IService<SysMenuEntity> {

    PageDataGridRespDTO<SysMenuDTO> listPage(SysMenuSearchDTO searchDTO);

    void create(SysMenuDTO dto);

    SysMenuDTO getDetailById(Long id);

    SysMenuDTO getDetail(SysMenuSearchDTO searchDTO);

    void updateById(SysMenuDTO dto);

    List<SysMenuDTO> list(SysMenuSearchDTO searchDTO);

    /**
     * 查询系统所有菜单（含按钮）
     *
     * @return 菜单列表
     */
    List<SysMenuDTO> selectMenuAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuDTO> selectMenuAllByUserId(Long userId);

    /**
     * 查询系统正常显示菜单（不含按钮）
     *
     * @return 菜单列表
     */
    List<SysMenuDTO> selectMenuNormalAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuDTO> selectMenusByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<String> selectPermsByRoleId(Long roleId);

    /**
     * 根据角色ID查询菜单
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<String> selectMenuTree(Long roleId);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenuDTO> selectMenuList(SysMenuDTO menu);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenuDTO> selectMenuListByUserId(SysMenuDTO menu);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenuDTO selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     *
     * @param parentId 菜单父ID
     * @return 结果
     */
    int selectCountMenuByParentId(Long parentId);

    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenuDTO menu);

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(SysMenuDTO menu);

    /**
     * 保存菜单排序
     *
     * @param menuIds   菜单ID
     * @param orderNums 排序ID
     */
    void updateMenuSort(SysMenuDTO menu);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    SysMenuDTO checkMenuNameUnique(String menuName, Long parentId);

}

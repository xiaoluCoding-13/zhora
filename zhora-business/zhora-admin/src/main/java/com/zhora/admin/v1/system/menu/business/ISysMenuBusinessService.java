package com.zhora.admin.v1.system.menu.business;


import com.zhora.admin.v1.system.menu.dto.SysMenuDetailDTO;
import com.zhora.admin.v1.system.menu.dto.SysMenuListDTO;
import com.zhora.admin.v1.system.menu.vo.SysMenuCheckVO;
import com.zhora.admin.v1.system.menu.vo.SysMenuCreateVO;
import com.zhora.admin.v1.system.menu.vo.SysMenuListVO;
import com.zhora.admin.v1.system.menu.vo.SysMenuUpdateVO;
import com.zhora.admin.v1.system.user.dto.SysUserDetailDTO;
import com.zhora.common.domain.Ztree;
import com.zhora.dto.system.SysRoleDTO;
import com.zhora.dto.system.SysUserDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单 业务层
 *
 * @author ruoyi
 */
public interface ISysMenuBusinessService {
    /**
     * 根据用户ID查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<SysMenuListDTO> selectMenusByUser(SysUserDTO user);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysMenuListDTO> selectMenuList(SysMenuListVO menu, Long userId);

    /**
     * 查询菜单集合
     *
     * @param userId 用户ID
     * @return 所有菜单信息
     */
    public List<SysMenuListDTO> selectMenuAll(Long userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    public Set<String> selectPermsByRoleId(Long roleId);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Ztree> roleMenuTreeData(SysRoleDTO role, Long userId);

    /**
     * 查询所有菜单信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Ztree> menuTreeData(Long userId);

    /**
     * 查询系统所有权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Map<String, String> selectPermsAll(Long userId);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public SysMenuDetailDTO selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     *
     * @param parentId 菜单父ID
     * @return 结果
     */
    public int selectCountMenuByParentId(Long parentId);

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(SysMenuCreateVO menu);

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysMenuUpdateVO menu);

    /**
     * 保存菜单排序
     *
     * @param menuIds 菜单ID
     * @param orderNums 排序ID
     */
    public void updateMenuSort(String[] menuIds, String[] orderNums);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean checkMenuNameUnique(SysMenuCheckVO menu);
}

package com.zhora.admin.v1.system.menu.business.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zhora.admin.v1.system.menu.business.ISysMenuBusinessService;
import com.zhora.admin.v1.system.menu.dto.SysMenuDetailDTO;
import com.zhora.admin.v1.system.menu.dto.SysMenuListDTO;
import com.zhora.admin.v1.system.menu.vo.SysMenuCheckVO;
import com.zhora.admin.v1.system.menu.vo.SysMenuCreateVO;
import com.zhora.admin.v1.system.menu.vo.SysMenuListVO;
import com.zhora.admin.v1.system.menu.vo.SysMenuUpdateVO;
import com.zhora.common.domain.Ztree;
import com.zhora.dto.system.SysMenuDTO;
import com.zhora.dto.system.SysRoleDTO;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.service.system.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysMenuBusinessServiceImpl implements ISysMenuBusinessService {

    @Autowired
    private ISysMenuService menuService;

    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuListDTO> getChildPerms(List<SysMenuDTO> list, int parentId) {
        List<SysMenuListDTO> returnList = new ArrayList<SysMenuListDTO>();
        for (Iterator<SysMenuDTO> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuDTO t = (SysMenuDTO) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(BeanUtil.toBean(t, SysMenuListDTO.class));
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenuDTO> list, SysMenuDTO t) {
        // 得到子节点列表
        List<SysMenuDTO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuDTO tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuDTO> getChildList(List<SysMenuDTO> list, SysMenuDTO t) {
        List<SysMenuDTO> tlist = new ArrayList<SysMenuDTO>();
        Iterator<SysMenuDTO> it = list.iterator();
        while (it.hasNext()) {
            SysMenuDTO n = (SysMenuDTO) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuDTO> list, SysMenuDTO t) {
        return getChildList(list, t).size() > 0;
    }

    @Override
    public List<SysMenuListDTO> selectMenusByUser(SysUserDTO user) {
        return List.of();
    }

    @Override
    public List<SysMenuListDTO> selectMenuList(SysMenuListVO menu, Long userId) {
        return List.of();
    }

    @Override
    public List<SysMenuListDTO> selectMenuAll(Long userId) {
        return List.of();
    }

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        return Set.of();
    }

    @Override
    public Set<String> selectPermsByRoleId(Long roleId) {
        return Set.of();
    }

    @Override
    public List<Ztree> roleMenuTreeData(SysRoleDTO role, Long userId) {
        return List.of();
    }

    @Override
    public List<Ztree> menuTreeData(Long userId) {
        return List.of();
    }

    @Override
    public Map<String, String> selectPermsAll(Long userId) {
        return Map.of();
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public SysMenuDetailDTO selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public int selectCountMenuByParentId(Long parentId) {
        return 0;
    }

    @Override
    public int selectCountRoleMenuByMenuId(Long menuId) {
        return 0;
    }

    @Override
    public int insertMenu(SysMenuCreateVO menu) {
        return 0;
    }

    @Override
    public int updateMenu(SysMenuUpdateVO menu) {
        return 0;
    }

    @Override
    public void updateMenuSort(String[] menuIds, String[] orderNums) {

    }

    @Override
    public boolean checkMenuNameUnique(SysMenuCheckVO menu) {
        return false;
    }
}

package com.zhora.admin.v1.system.user.business.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zhora.admin.v1.system.dept.dto.SysDeptDetailDTO;
import com.zhora.admin.v1.system.user.business.ISysUserBusinessService;
import com.zhora.admin.v1.system.user.dto.SysUserDetailDTO;
import com.zhora.admin.v1.system.user.dto.SysUserListDTO;
import com.zhora.admin.v1.system.user.vo.*;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.common.utils.ListUtils;
import com.zhora.common.utils.PageDataGridCommonRespUtil;
import com.zhora.dto.system.SysDeptDTO;
import com.zhora.dto.system.SysRoleDTO;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.dto.system.SysUserRoleDTO;
import com.zhora.dto.system.search.SysDeptSearchDTO;
import com.zhora.dto.system.search.SysRoleSearchDTO;
import com.zhora.dto.system.search.SysUserRoleSearchDTO;
import com.zhora.dto.system.search.SysUserSearchDTO;
import com.zhora.service.system.ISysDeptService;
import com.zhora.service.system.ISysRoleService;
import com.zhora.service.system.ISysUserRoleService;
import com.zhora.service.system.ISysUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用户业务层
 *
 * @author zhehen.lu
 * @date 2025/8/27 14:30
 */
@Service
public class SysUserBusinessService implements ISysUserBusinessService {

    @Autowired
    ISysUserService userService;

    @Autowired
    ISysDeptService deptService;

    @Autowired
    ISysUserRoleService userRoleService;

    @Autowired
    ISysRoleService roleService;

    @Override
    public PageDataGridRespDTO<SysUserListDTO> listPage(SysUserListVO listVO) {
        SysUserSearchDTO searchDTO = BeanUtil.copyProperties(listVO, SysUserSearchDTO.class);
        searchDTO.setDelFlag(Boolean.FALSE);

        PageDataGridRespDTO<SysUserDTO> page = userService.listPage(searchDTO);

        if (CollectionUtils.isEmpty(page.getList())) {
            return PageDataGridCommonRespUtil.convert(page, new ArrayList<>());
        }

        Set<Long> deptIds = new HashSet<>();
        Set<Long> userIds = new HashSet<>();
        page.getList().forEach(v -> {
            deptIds.add(v.getDeptId());
            userIds.add(v.getUserId());
        });

        //对应的部门信息
        SysDeptSearchDTO deptSearchDTO = new SysDeptSearchDTO();
        deptSearchDTO.setDeptIdList(deptIds.stream().toList());
        deptSearchDTO.setDelFlag(Boolean.FALSE);
        Map<Long, SysDeptDTO> deptListMap = deptService.list(deptSearchDTO)
                .stream()
                .collect(Collectors.toMap(SysDeptDTO::getDeptId, Function.identity(), (v1, v2) -> v1));

        //对应的用户角色信息
        SysUserRoleSearchDTO userRoleSearchDTO = new SysUserRoleSearchDTO();
        userRoleSearchDTO.setUserIdList(userIds.stream().toList());
        userRoleSearchDTO.setDelFlag(Boolean.FALSE);
        List<SysUserRoleDTO> userRoleList = userRoleService.list(userRoleSearchDTO);
        Map<Long, List<SysUserRoleDTO>> roleListGroupMap = userRoleList
                .stream()
                .collect(Collectors.groupingBy(SysUserRoleDTO::getUserId));

        //角色ID
        List<Long> roleIdList = userRoleList.stream().map(SysUserRoleDTO::getRoleId)
                .distinct()
                .toList();

        SysRoleSearchDTO roleSearchDTO = new SysRoleSearchDTO();
        roleSearchDTO.setRoleIdList(roleIdList);
        roleSearchDTO.setDelFlag(Boolean.FALSE);
        List<SysRoleDTO> roleList = roleService.list(roleSearchDTO);

        List<SysUserListDTO> collect = page
                .getList()
                .stream()
                .map(v -> {
                    SysUserListDTO responseDTO = BeanUtil.copyProperties(v, SysUserListDTO.class);

                    //部门相关信息
                    Optional.ofNullable(deptListMap.get(v.getDeptId()))
                            .ifPresent(v1 -> responseDTO.setDept(BeanUtil.copyProperties(v1, SysDeptDetailDTO.class)));

                    //角色相关信息
                    Optional.ofNullable(roleListGroupMap.get(v.getUserId()))
                            .ifPresent(v1 -> {
                                List<SysRoleDTO> userRoleDTOList = BeanUtil.copyToList(v1, SysRoleDTO.class);

                                // 合并两个列表，根据id取交集
                                List<SysRoleDTO> unionRoleList = ListUtils.getIntersectionByFieldStream(roleList, userRoleDTOList, SysRoleDTO::getRoleId);
                                if (CollectionUtils.isNotEmpty(unionRoleList)) {
                                    responseDTO.setRoles(unionRoleList);
                                }
                            });

                    return responseDTO;
                })
                .collect(Collectors.toList());

        return PageDataGridCommonRespUtil.convert(page, collect);
    }

    @Override
    public List<SysUserListDTO> selectUserList(SysUserListVO listVO) {
        return List.of();
    }

    @Override
    public List<SysUserListDTO> selectAllocatedList(SysUserListVO listVO) {
        return List.of();
    }

    @Override
    public List<SysUserListDTO> selectUnallocatedList(SysUserListVO listVO) {
        return List.of();
    }

    @Override
    public SysUserDetailDTO selectUserByLoginName(String userName) {
        return null;
    }

    @Override
    public SysUserDetailDTO selectUserByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public SysUserDetailDTO selectUserByEmail(String email) {
        return null;
    }

    @Override
    public SysUserDetailDTO selectUserById(Long userId) {
        return null;
    }

    @Override
    public List<SysUserListDTO> selectUserRoleByUserId(Long userId) {
        return List.of();
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public int deleteUserByIds(String ids) {
        return 0;
    }

    @Override
    public int insertUser(SysUserCreateVO createVO) {
        return 0;
    }

    @Override
    public boolean registerUser(SysUserRegisterVO registerVO) {
        return false;
    }

    @Override
    public int updateUser(SysUserUpdateVO updateVO) {
        return 0;
    }

    @Override
    public int updateUserInfo(SysUserUpdateVO updateVO) {
        return 0;
    }

    @Override
    public boolean updateUserAvatar(Long userId, String avatar) {
        return false;
    }

    @Override
    public void insertUserAuth(Long userId, Long[] roleIds) {

    }

    @Override
    public int resetUserPwd(SysUserUpdateVO updateVO) {
        return 0;
    }

    @Override
    public boolean checkLoginNameUnique(SysUserCheckVO checkVO) {
        return false;
    }

    @Override
    public boolean checkPhoneUnique(SysUserCheckVO checkVO) {
        return false;
    }

    @Override
    public boolean checkEmailUnique(SysUserCheckVO checkVO) {
        return false;
    }

    @Override
    public void checkUserAllowed(SysUserCheckVO checkVO) {

    }

    @Override
    public void checkUserDataScope(Long userId) {

    }

    @Override
    public String selectUserRoleGroup(Long userId) {
        return "";
    }

    @Override
    public String selectUserPostGroup(Long userId) {
        return "";
    }

    @Override
    public String importUser(List<SysUserListVO> userList, Boolean isUpdateSupport, String operName) {
        return "";
    }

    @Override
    public int changeStatus(SysUserUpdateVO updateVO) {
        return 0;
    }

}

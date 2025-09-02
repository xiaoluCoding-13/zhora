package com.zhora.admin.v1.system.role.business.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zhora.admin.v1.system.role.business.ISysRoleBusinessService;
import com.zhora.admin.v1.system.role.dto.SysRoleDetailDTO;
import com.zhora.dto.system.SysRoleDTO;
import com.zhora.dto.system.SysUserRoleDTO;
import com.zhora.dto.system.search.SysRoleSearchDTO;
import com.zhora.dto.system.search.SysUserRoleSearchDTO;
import com.zhora.service.system.ISysRoleService;
import com.zhora.service.system.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色
 *
 * @author zhehen.lu
 * @date 2025/8/29 10:44
 */
@Service
public class SysRoleBusinessService implements ISysRoleBusinessService {

    @Autowired
    ISysRoleService sysRoleService;

    @Autowired
    ISysUserRoleService userRoleService;

    @Override
    public List<SysRoleDetailDTO> getRolesByUserId(Long userId) {
        //对应的用户角色信息
        SysUserRoleSearchDTO userRoleSearchDTO = new SysUserRoleSearchDTO();
        userRoleSearchDTO.setUserId(userId);
        userRoleSearchDTO.setDelFlag(Boolean.FALSE);
        List<SysUserRoleDTO> userRoleList = userRoleService.list(userRoleSearchDTO);

        List<Long> roleIds = userRoleList.stream()
                .map(SysUserRoleDTO::getRoleId)
                .toList();

        SysRoleSearchDTO roleSearchDTO = new SysRoleSearchDTO();
        roleSearchDTO.setRoleIdList(roleIds);
        roleSearchDTO.setDelFlag(Boolean.FALSE);

        List<SysRoleDTO> roleDTOList = sysRoleService.list(roleSearchDTO);

        return BeanUtil.copyToList(roleDTOList, SysRoleDetailDTO.class);
    }
}

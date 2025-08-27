package com.zhora.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.dto.system.search.SysUserSearchDTO;
import com.zhora.entity.system.SysUserEntity;

import java.util.List;

/**
 * 用户 业务层
 * 
 * @author ruoyi
 */
public interface ISysUserService extends IService<SysUserEntity> {

    PageDataGridRespDTO<SysUserDTO> listPage(SysUserSearchDTO searchDTO);

    void create(SysUserDTO dto);

    SysUserDTO getDetailById(Long userId);

    SysUserDTO getDetail(SysUserSearchDTO searchDTO);

    void updateById(SysUserDTO dto);

    List<SysUserDTO> list(SysUserSearchDTO searchDTO);
}

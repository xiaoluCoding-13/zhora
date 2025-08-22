package com.zhora.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.dto.system.search.SysUserSearchDTO;
import com.zhora.entity.system.SysUserEntity;

/**
 * 用户 业务层
 * 
 * @author ruoyi
 */
public interface ISysUserService extends IService<SysUserEntity> {
    /**
     * 根据条件分页查询用户列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public PageDataGridRespDTO<SysUserDTO> selectUserList(SysUserSearchDTO searchDTO);
}
